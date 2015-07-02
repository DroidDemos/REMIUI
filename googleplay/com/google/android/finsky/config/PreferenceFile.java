package com.google.android.finsky.config;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Build.VERSION;

public class PreferenceFile {
    private static Context sContext;
    private final int mMode;
    private final String mName;

    public static abstract class SharedPreference<T> {
        final T mDefaultValue;
        PreferenceFile mFile;
        final String mKey;

        protected abstract T read(SharedPreferences sharedPreferences);

        protected abstract void write(Editor editor, T t);

        protected SharedPreference(PreferenceFile file, String key, T defaultValue) {
            this.mFile = file;
            this.mKey = key;
            this.mDefaultValue = defaultValue;
        }

        public final T get() {
            return read(this.mFile.open());
        }

        public final String getKey() {
            return this.mKey;
        }

        public final boolean exists() {
            return this.mFile.open().contains(this.mKey);
        }

        public final void put(T value) {
            Editor editor = this.mFile.open().edit();
            write(editor, value);
            PreferenceFile.commit(editor);
        }

        public final void remove() {
            PreferenceFile.commit(this.mFile.open().edit().remove(this.mKey));
        }

        public final void override(PreferenceFile file) {
            this.mFile = file;
        }
    }

    public static abstract class PrefixSharedPreference<T> {
        protected final T mDefaultValue;
        protected final String mPrefix;

        protected abstract SharedPreference<T> valueWithKey(String str);

        protected PrefixSharedPreference(String prefix, T defaultValue) {
            this.mPrefix = prefix;
            this.mDefaultValue = defaultValue;
        }

        public SharedPreference<T> get(String suffix) {
            return valueWithKey(this.mPrefix + suffix);
        }

        public SharedPreference<T> get(int suffix) {
            return valueWithKey(this.mPrefix + suffix);
        }
    }

    public static void init(Context context) {
        sContext = context;
    }

    public PreferenceFile(String name, int mode) {
        this.mName = name;
        this.mMode = mode;
    }

    public SharedPreferences open() {
        return sContext.getSharedPreferences(this.mName, this.mMode);
    }

    public static boolean commit(Editor editor) {
        if (VERSION.SDK_INT < 9) {
            return editor.commit();
        }
        editor.apply();
        return true;
    }

    public SharedPreference<Long> value(String key, Long defaultValue) {
        return new SharedPreference<Long>(this, key, defaultValue) {
            protected Long read(SharedPreferences sp) {
                return sp.contains(this.mKey) ? Long.valueOf(sp.getLong(this.mKey, 0)) : (Long) this.mDefaultValue;
            }

            protected void write(Editor editor, Long value) {
                if (value == null) {
                    throw new IllegalArgumentException("null cannot be written for <Long>");
                }
                editor.putLong(this.mKey, value.longValue());
            }
        };
    }

    public SharedPreference<String> value(String key, String defaultValue) {
        return new SharedPreference<String>(this, key, defaultValue) {
            protected String read(SharedPreferences sp) {
                return sp.contains(this.mKey) ? sp.getString(this.mKey, null) : (String) this.mDefaultValue;
            }

            protected void write(Editor editor, String value) {
                editor.putString(this.mKey, value);
            }
        };
    }

    public SharedPreference<Boolean> value(String key, Boolean defaultValue) {
        return new SharedPreference<Boolean>(this, key, defaultValue) {
            protected Boolean read(SharedPreferences sp) {
                return sp.contains(this.mKey) ? Boolean.valueOf(sp.getBoolean(this.mKey, false)) : (Boolean) this.mDefaultValue;
            }

            protected void write(Editor editor, Boolean value) {
                if (value == null) {
                    throw new IllegalArgumentException("null cannot be written for <Boolean>");
                }
                editor.putBoolean(this.mKey, value.booleanValue());
            }
        };
    }

    public SharedPreference<Integer> value(String key, Integer defaultValue) {
        return new SharedPreference<Integer>(this, key, defaultValue) {
            protected Integer read(SharedPreferences sp) {
                return sp.contains(this.mKey) ? Integer.valueOf(sp.getInt(this.mKey, 0)) : (Integer) this.mDefaultValue;
            }

            protected void write(Editor editor, Integer value) {
                if (value == null) {
                    throw new IllegalArgumentException("null cannot be written for <Integer>");
                }
                editor.putInt(this.mKey, value.intValue());
            }
        };
    }

    public PrefixSharedPreference<Long> prefixPreference(String prefix, Long defaultValue) {
        return new PrefixSharedPreference<Long>(prefix, defaultValue) {
            protected SharedPreference<Long> valueWithKey(String key) {
                return PreferenceFile.this.value(key, (Long) this.mDefaultValue);
            }
        };
    }

    public PrefixSharedPreference<String> prefixPreference(String prefix, String defaultValue) {
        return new PrefixSharedPreference<String>(prefix, defaultValue) {
            protected SharedPreference<String> valueWithKey(String key) {
                return PreferenceFile.this.value(key, (String) this.mDefaultValue);
            }
        };
    }

    public PrefixSharedPreference<Boolean> prefixPreference(String prefix, Boolean defaultValue) {
        return new PrefixSharedPreference<Boolean>(prefix, defaultValue) {
            protected SharedPreference<Boolean> valueWithKey(String key) {
                return PreferenceFile.this.value(key, (Boolean) this.mDefaultValue);
            }
        };
    }

    public PrefixSharedPreference<Integer> prefixPreference(String prefix, Integer defaultValue) {
        return new PrefixSharedPreference<Integer>(prefix, defaultValue) {
            protected SharedPreference<Integer> valueWithKey(String key) {
                return PreferenceFile.this.value(key, (Integer) this.mDefaultValue);
            }
        };
    }
}
