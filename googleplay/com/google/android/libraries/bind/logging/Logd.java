package com.google.android.libraries.bind.logging;

import android.util.Log;
import java.util.HashMap;
import java.util.Map;

public class Logd {
    private static boolean enableAll;
    protected static Map<String, Logd> existingClassLoggers;
    protected static LogHandler sysLogHandler;
    protected boolean enabled;
    protected String tag;

    static {
        existingClassLoggers = new HashMap();
        sysLogHandler = new SystemLogHandler();
    }

    public static Logd get(Class<?> clazz) {
        return get(clazz.getSimpleName());
    }

    public static Logd get(String tag) {
        if (existingClassLoggers.containsKey(tag)) {
            return (Logd) existingClassLoggers.get(tag);
        }
        Logd logger = new Logd(tag);
        existingClassLoggers.put(tag, logger);
        return logger;
    }

    public Logd(String tag) {
        this.tag = tag;
    }

    public boolean isEnabled() {
        return this.enabled || enableAll;
    }

    public void d(String format, Object... args) {
        if (isEnabled()) {
            sysLogHandler.log(3, this.tag, safeFormat(null, format, args));
        }
    }

    public void v(String format, Object... args) {
        if (isEnabled()) {
            sysLogHandler.log(2, this.tag, safeFormat(null, format, args));
        }
    }

    public void e(Throwable tr, String format, Object... args) {
        sysLogHandler.log(6, this.tag, safeFormat(tr, format, args));
    }

    public void e(String format, Object... args) {
        e(null, format, args);
    }

    public void i(Throwable tr, String format, Object... args) {
        if (isEnabled()) {
            sysLogHandler.log(4, this.tag, safeFormat(tr, format, args));
        }
    }

    public void i(String format, Object... args) {
        i(null, format, args);
    }

    private static String safeFormat(Throwable optThrowable, String msg, Object... args) {
        String formatted = (msg == null || args == null || args.length == 0) ? msg : String.format(msg, args);
        if (formatted == null) {
            formatted = "";
        }
        if (optThrowable != null) {
            return formatted + "\n" + Log.getStackTraceString(optThrowable);
        }
        return formatted;
    }
}
