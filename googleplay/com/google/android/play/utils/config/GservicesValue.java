package com.google.android.play.utils.config;

import android.content.ContentResolver;
import android.content.Context;
import com.google.android.gsf.GoogleSettingsContract.Partner;
import com.google.android.gsf.Gservices;

public abstract class GservicesValue<T> {
    private static GservicesReader sGservicesReader;
    private final String mKey;
    private T mOverride;

    private interface GservicesReader {
        Boolean getBoolean(String str, Boolean bool);

        Float getFloat(String str, Float f);

        Integer getInt(String str, Integer num);

        Long getLong(String str, Long l);

        String getPartnerString(String str, String str2);

        String getString(String str, String str2);
    }

    private static class GservicesReaderForTests implements GservicesReader {
        private GservicesReaderForTests() {
        }

        public Boolean getBoolean(String key, Boolean defaultValue) {
            return defaultValue;
        }

        public Integer getInt(String key, Integer defaultValue) {
            return defaultValue;
        }

        public Float getFloat(String key, Float defaultValue) {
            return defaultValue;
        }

        public Long getLong(String key, Long defaultValue) {
            return defaultValue;
        }

        public String getString(String key, String defaultValue) {
            return defaultValue;
        }

        public String getPartnerString(String key, String defaultValue) {
            return defaultValue;
        }
    }

    private static class GservicesReaderImpl implements GservicesReader {
        private final ContentResolver mContentResolver;

        public GservicesReaderImpl(ContentResolver contentResolver, String[] prefixesToCache) {
            this.mContentResolver = contentResolver;
            Gservices.bulkCacheByPrefix(this.mContentResolver, prefixesToCache);
        }

        public Boolean getBoolean(String key, Boolean defaultValue) {
            return Boolean.valueOf(Gservices.getBoolean(this.mContentResolver, key, defaultValue.booleanValue()));
        }

        public Integer getInt(String key, Integer defaultValue) {
            return Integer.valueOf(Gservices.getInt(this.mContentResolver, key, defaultValue.intValue()));
        }

        public Float getFloat(String key, Float defaultValue) {
            String floatStr = Gservices.getString(this.mContentResolver, key, null);
            if (floatStr != null) {
                try {
                    defaultValue = Float.valueOf(Float.parseFloat(floatStr));
                } catch (NumberFormatException e) {
                }
            }
            return defaultValue;
        }

        public Long getLong(String key, Long defaultValue) {
            return Long.valueOf(Gservices.getLong(this.mContentResolver, key, defaultValue.longValue()));
        }

        public String getString(String key, String defaultValue) {
            return Gservices.getString(this.mContentResolver, key, defaultValue);
        }

        public String getPartnerString(String key, String defaultValue) {
            return Partner.getString(this.mContentResolver, key, defaultValue);
        }
    }

    protected abstract T retrieve();

    static {
        sGservicesReader = null;
    }

    public static void init(Context context, String[] prefixesToCache) {
        sGservicesReader = new GservicesReaderImpl(context.getContentResolver(), prefixesToCache);
    }

    public static void initForTests() {
        sGservicesReader = new GservicesReaderForTests();
    }

    private GservicesValue(String key) {
        this.mOverride = null;
        this.mKey = key;
    }

    public void override(T value) {
        this.mOverride = value;
    }

    public final T get() {
        if (this.mOverride != null) {
            return this.mOverride;
        }
        return retrieve();
    }

    public String getKey() {
        return this.mKey;
    }

    public static GservicesValue<Boolean> value(final String key, final Boolean defaultValue) {
        return new GservicesValue<Boolean>(key) {
            protected Boolean retrieve() {
                return GservicesValue.sGservicesReader.getBoolean(key, defaultValue);
            }
        };
    }

    public static GservicesValue<Long> value(final String key, final Long defaultValue) {
        return new GservicesValue<Long>(key) {
            protected Long retrieve() {
                return GservicesValue.sGservicesReader.getLong(key, defaultValue);
            }
        };
    }

    public static GservicesValue<Integer> value(final String key, final Integer defaultValue) {
        return new GservicesValue<Integer>(key) {
            protected Integer retrieve() {
                return GservicesValue.sGservicesReader.getInt(key, defaultValue);
            }
        };
    }

    public static GservicesValue<Float> value(final String key, final Float defaultValue) {
        return new GservicesValue<Float>(key) {
            protected Float retrieve() {
                return GservicesValue.sGservicesReader.getFloat(key, defaultValue);
            }
        };
    }

    public static GservicesValue<String> value(final String key, final String defaultValue) {
        return new GservicesValue<String>(key) {
            protected String retrieve() {
                return GservicesValue.sGservicesReader.getString(key, defaultValue);
            }
        };
    }

    public static GservicesValue<String> partnerSetting(final String key, final String defaultValue) {
        return new GservicesValue<String>(key) {
            protected String retrieve() {
                return GservicesValue.sGservicesReader.getPartnerString(key, defaultValue);
            }
        };
    }
}
