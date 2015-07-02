package com.xiaomi.xmsf.activate;

import android.content.Context;
import android.content.SharedPreferences.Editor;

public class PrefUtils {
    private static final String PREF_NAME = "pref_com_xiaomi_xmsf";

    public static void saveString(Context context, String key, String value) {
        Editor editor = context.getSharedPreferences(PREF_NAME, 0).edit();
        editor.putString(key, value);
        editor.commit();
    }

    public static void remove(Context context, String key) {
        Editor editor = context.getSharedPreferences(PREF_NAME, 0).edit();
        editor.remove(key);
        editor.commit();
    }

    public static String getString(Context context, String key) {
        return context.getSharedPreferences(PREF_NAME, 0).getString(key, null);
    }

    public static void saveLong(Context context, String key, long value) {
        Editor editor = context.getSharedPreferences(PREF_NAME, 0).edit();
        editor.putLong(key, value);
        editor.commit();
    }

    public static long getLong(Context context, String key, long defaultValue) {
        return context.getSharedPreferences(PREF_NAME, 0).getLong(key, defaultValue);
    }

    public static void saveBoolean(Context context, String key, boolean value) {
        Editor editor = context.getSharedPreferences(PREF_NAME, 0).edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    public static boolean getBoolean(Context context, String key, boolean defaultValue) {
        return context.getSharedPreferences(PREF_NAME, 0).getBoolean(key, defaultValue);
    }

    public static void saveInt(Context context, String key, int value) {
        Editor editor = context.getSharedPreferences(PREF_NAME, 0).edit();
        editor.putInt(key, value);
        editor.commit();
    }

    public static int getInt(Context context, String key, int defaultValue) {
        return context.getSharedPreferences(PREF_NAME, 0).getInt(key, defaultValue);
    }
}
