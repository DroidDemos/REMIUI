package com.google.android.gsf;

import android.content.ContentResolver;
import android.content.Context;

public abstract class GservicesValue<T> {
    private static ContentResolver sContentResolver;
    protected final T mDefaultValue;
    protected final String mKey;

    protected abstract T retrieve(String str);

    static {
        sContentResolver = null;
    }

    public static void init(Context context) {
        sContentResolver = context.getContentResolver();
    }

    protected GservicesValue(String key, T defaultValue) {
        this.mKey = key;
        this.mDefaultValue = defaultValue;
    }

    public final T get() {
        return retrieve(this.mKey);
    }

    public static GservicesValue<Boolean> value(String key, boolean defaultValue) {
        return new GservicesValue<Boolean>(key, Boolean.valueOf(defaultValue)) {
            protected Boolean retrieve(String key) {
                return Boolean.valueOf(Gservices.getBoolean(GservicesValue.sContentResolver, this.mKey, ((Boolean) this.mDefaultValue).booleanValue()));
            }
        };
    }

    public static GservicesValue<Long> value(String key, Long defaultValue) {
        return new GservicesValue<Long>(key, defaultValue) {
            protected Long retrieve(String key) {
                return Long.valueOf(Gservices.getLong(GservicesValue.sContentResolver, this.mKey, ((Long) this.mDefaultValue).longValue()));
            }
        };
    }

    public static GservicesValue<Integer> value(String key, Integer defaultValue) {
        return new GservicesValue<Integer>(key, defaultValue) {
            protected Integer retrieve(String key) {
                return Integer.valueOf(Gservices.getInt(GservicesValue.sContentResolver, this.mKey, ((Integer) this.mDefaultValue).intValue()));
            }
        };
    }

    public static GservicesValue<String> value(String key, String defaultValue) {
        return new GservicesValue<String>(key, defaultValue) {
            protected String retrieve(String key) {
                return Gservices.getString(GservicesValue.sContentResolver, this.mKey, (String) this.mDefaultValue);
            }
        };
    }
}
