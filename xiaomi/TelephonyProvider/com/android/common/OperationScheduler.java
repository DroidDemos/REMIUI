package com.android.common;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.http.AndroidHttpClient;
import android.text.format.Time;
import java.util.Iterator;
import java.util.TreeSet;

public class OperationScheduler {
    private static final String PREFIX = "OperationScheduler_";
    private final SharedPreferences mStorage;

    public static class Options {
        public int backoffExponentialMillis;
        public long backoffFixedMillis;
        public long backoffIncrementalMillis;
        public long maxMoratoriumMillis;
        public long minTriggerMillis;
        public long periodicIntervalMillis;

        public Options() {
            this.backoffFixedMillis = 0;
            this.backoffIncrementalMillis = 5000;
            this.backoffExponentialMillis = 0;
            this.maxMoratoriumMillis = 86400000;
            this.minTriggerMillis = 0;
            this.periodicIntervalMillis = 0;
        }

        public String toString() {
            if (this.backoffExponentialMillis > 0) {
                return String.format("OperationScheduler.Options[backoff=%.1f+%.1f+%.1f max=%.1f min=%.1f period=%.1f]", new Object[]{Double.valueOf(((double) this.backoffFixedMillis) / 1000.0d), Double.valueOf(((double) this.backoffIncrementalMillis) / 1000.0d), Double.valueOf(((double) this.backoffExponentialMillis) / 1000.0d), Double.valueOf(((double) this.maxMoratoriumMillis) / 1000.0d), Double.valueOf(((double) this.minTriggerMillis) / 1000.0d), Double.valueOf(((double) this.periodicIntervalMillis) / 1000.0d)});
            }
            return String.format("OperationScheduler.Options[backoff=%.1f+%.1f max=%.1f min=%.1f period=%.1f]", new Object[]{Double.valueOf(((double) this.backoffFixedMillis) / 1000.0d), Double.valueOf(((double) this.backoffIncrementalMillis) / 1000.0d), Double.valueOf(((double) this.maxMoratoriumMillis) / 1000.0d), Double.valueOf(((double) this.minTriggerMillis) / 1000.0d), Double.valueOf(((double) this.periodicIntervalMillis) / 1000.0d)});
        }
    }

    public OperationScheduler(SharedPreferences storage) {
        this.mStorage = storage;
    }

    public static Options parseOptions(String spec, Options options) throws IllegalArgumentException {
        for (String param : spec.split(" +")) {
            if (param.length() != 0) {
                if (param.startsWith("backoff=")) {
                    String[] pieces = param.substring(8).split("\\+");
                    if (pieces.length > 3) {
                        throw new IllegalArgumentException("bad value for backoff: [" + spec + "]");
                    }
                    if (pieces.length > 0 && pieces[0].length() > 0) {
                        options.backoffFixedMillis = parseSeconds(pieces[0]);
                    }
                    if (pieces.length > 1 && pieces[1].length() > 0) {
                        options.backoffIncrementalMillis = parseSeconds(pieces[1]);
                    }
                    if (pieces.length > 2 && pieces[2].length() > 0) {
                        options.backoffExponentialMillis = (int) parseSeconds(pieces[2]);
                    }
                } else if (param.startsWith("max=")) {
                    options.maxMoratoriumMillis = parseSeconds(param.substring(4));
                } else if (param.startsWith("min=")) {
                    options.minTriggerMillis = parseSeconds(param.substring(4));
                } else if (param.startsWith("period=")) {
                    options.periodicIntervalMillis = parseSeconds(param.substring(7));
                } else {
                    options.periodicIntervalMillis = parseSeconds(param);
                }
            }
        }
        return options;
    }

    private static long parseSeconds(String param) throws NumberFormatException {
        return (long) (Float.parseFloat(param) * 1000.0f);
    }

    public long getNextTimeMillis(Options options) {
        if (!this.mStorage.getBoolean("OperationScheduler_enabledState", true)) {
            return Long.MAX_VALUE;
        }
        if (this.mStorage.getBoolean("OperationScheduler_permanentError", false)) {
            return Long.MAX_VALUE;
        }
        int errorCount = this.mStorage.getInt("OperationScheduler_errorCount", 0);
        long now = currentTimeMillis();
        long lastSuccessTimeMillis = getTimeBefore("OperationScheduler_lastSuccessTimeMillis", now);
        long lastErrorTimeMillis = getTimeBefore("OperationScheduler_lastErrorTimeMillis", now);
        long triggerTimeMillis = this.mStorage.getLong("OperationScheduler_triggerTimeMillis", Long.MAX_VALUE);
        long moratoriumSetMillis = getTimeBefore("OperationScheduler_moratoriumSetTimeMillis", now);
        long moratoriumTimeMillis = getTimeBefore("OperationScheduler_moratoriumTimeMillis", options.maxMoratoriumMillis + moratoriumSetMillis);
        long time = triggerTimeMillis;
        if (options.periodicIntervalMillis > 0) {
            time = Math.min(time, options.periodicIntervalMillis + lastSuccessTimeMillis);
        }
        time = Math.max(Math.max(time, moratoriumTimeMillis), options.minTriggerMillis + lastSuccessTimeMillis);
        if (errorCount <= 0) {
            return time;
        }
        int shift = errorCount - 1;
        if (shift > 30) {
            shift = 30;
        }
        long backoff = (options.backoffFixedMillis + (options.backoffIncrementalMillis * ((long) errorCount))) + (((long) options.backoffExponentialMillis) << shift);
        if (moratoriumTimeMillis > 0 && backoff > moratoriumTimeMillis) {
            backoff = moratoriumTimeMillis;
        }
        return Math.max(time, lastErrorTimeMillis + backoff);
    }

    public long getLastSuccessTimeMillis() {
        return this.mStorage.getLong("OperationScheduler_lastSuccessTimeMillis", 0);
    }

    public long getLastAttemptTimeMillis() {
        return Math.max(this.mStorage.getLong("OperationScheduler_lastSuccessTimeMillis", 0), this.mStorage.getLong("OperationScheduler_lastErrorTimeMillis", 0));
    }

    private long getTimeBefore(String name, long max) {
        long time = this.mStorage.getLong(name, 0);
        if (time <= max) {
            return time;
        }
        time = max;
        SharedPreferencesCompat.apply(this.mStorage.edit().putLong(name, time));
        return time;
    }

    public void setTriggerTimeMillis(long millis) {
        SharedPreferencesCompat.apply(this.mStorage.edit().putLong("OperationScheduler_triggerTimeMillis", millis));
    }

    public void setMoratoriumTimeMillis(long millis) {
        SharedPreferencesCompat.apply(this.mStorage.edit().putLong("OperationScheduler_moratoriumTimeMillis", millis).putLong("OperationScheduler_moratoriumSetTimeMillis", currentTimeMillis()));
    }

    public boolean setMoratoriumTimeHttp(String retryAfter) {
        try {
            setMoratoriumTimeMillis(currentTimeMillis() + (Long.valueOf(retryAfter).longValue() * 1000));
            return true;
        } catch (NumberFormatException e) {
            try {
                setMoratoriumTimeMillis(AndroidHttpClient.parseDate(retryAfter));
                return true;
            } catch (IllegalArgumentException e2) {
                return false;
            }
        }
    }

    public void setEnabledState(boolean enabled) {
        SharedPreferencesCompat.apply(this.mStorage.edit().putBoolean("OperationScheduler_enabledState", enabled));
    }

    public void onSuccess() {
        resetTransientError();
        resetPermanentError();
        SharedPreferencesCompat.apply(this.mStorage.edit().remove("OperationScheduler_errorCount").remove("OperationScheduler_lastErrorTimeMillis").remove("OperationScheduler_permanentError").remove("OperationScheduler_triggerTimeMillis").putLong("OperationScheduler_lastSuccessTimeMillis", currentTimeMillis()));
    }

    public void onTransientError() {
        Editor editor = this.mStorage.edit();
        editor.putLong("OperationScheduler_lastErrorTimeMillis", currentTimeMillis());
        editor.putInt("OperationScheduler_errorCount", this.mStorage.getInt("OperationScheduler_errorCount", 0) + 1);
        SharedPreferencesCompat.apply(editor);
    }

    public void resetTransientError() {
        SharedPreferencesCompat.apply(this.mStorage.edit().remove("OperationScheduler_errorCount"));
    }

    public void onPermanentError() {
        SharedPreferencesCompat.apply(this.mStorage.edit().putBoolean("OperationScheduler_permanentError", true));
    }

    public void resetPermanentError() {
        SharedPreferencesCompat.apply(this.mStorage.edit().remove("OperationScheduler_permanentError"));
    }

    public String toString() {
        StringBuilder out = new StringBuilder("[OperationScheduler:");
        Iterator i$ = new TreeSet(this.mStorage.getAll().keySet()).iterator();
        while (i$.hasNext()) {
            String key = (String) i$.next();
            if (key.startsWith(PREFIX)) {
                if (key.endsWith("TimeMillis")) {
                    Time time = new Time();
                    time.set(this.mStorage.getLong(key, 0));
                    out.append(" ").append(key.substring(PREFIX.length(), key.length() - 10));
                    out.append("=").append(time.format("%Y-%m-%d/%H:%M:%S"));
                } else {
                    out.append(" ").append(key.substring(PREFIX.length()));
                    out.append("=").append(this.mStorage.getAll().get(key).toString());
                }
            }
        }
        return out.append("]").toString();
    }

    protected long currentTimeMillis() {
        return System.currentTimeMillis();
    }
}
