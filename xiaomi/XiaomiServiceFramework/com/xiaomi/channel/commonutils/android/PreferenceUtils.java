package com.xiaomi.channel.commonutils.android;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;
import com.xiaomi.channel.commonutils.logger.MyLog;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public abstract class PreferenceUtils {
    private static final ArrayList<PrefObserver> sPrefObs;

    public interface PrefObserver {
        void notifyPrefChange(String str, Object obj);
    }

    static {
        sPrefObs = new ArrayList();
    }

    public static void addPrefObserver(PrefObserver ob) {
        sPrefObs.add(ob);
    }

    public static void removePrefObserver(PrefObserver obToRm) {
        Iterator i$ = sPrefObs.iterator();
        while (i$.hasNext()) {
            PrefObserver ob = (PrefObserver) i$.next();
            if (ob == obToRm) {
                sPrefObs.remove(ob);
                return;
            }
        }
    }

    public static void notifyPrefChange(String key, Object value) {
        Iterator i$ = sPrefObs.iterator();
        while (i$.hasNext()) {
            ((PrefObserver) i$.next()).notifyPrefChange(key, value);
        }
    }

    public static String getSettingString(Context c, String key, String defaultValue) {
        checkProcess(c);
        return PreferenceManager.getDefaultSharedPreferences(c).getString(key, defaultValue);
    }

    public static void setSettingString(Context c, String key, String value) {
        checkProcess(c);
        PreferenceManager.getDefaultSharedPreferences(c).edit().putString(key, value).commit();
    }

    public static boolean getSettingBoolean(Context c, String key, boolean defaultValue) {
        checkProcess(c);
        return PreferenceManager.getDefaultSharedPreferences(c).getBoolean(key, defaultValue);
    }

    public static boolean hasKey(Context c, String key) {
        checkProcess(c);
        return PreferenceManager.getDefaultSharedPreferences(c).contains(key);
    }

    public static void setSettingBoolean(Context c, String key, boolean value) {
        checkProcess(c);
        PreferenceManager.getDefaultSharedPreferences(c).edit().putBoolean(key, value).commit();
    }

    public static void setSettingInt(Context c, String key, int value) {
        checkProcess(c);
        PreferenceManager.getDefaultSharedPreferences(c).edit().putInt(key, value).commit();
    }

    public static void increaseSettingInt(Context c, String key) {
        checkProcess(c);
        increaseSettingInt(PreferenceManager.getDefaultSharedPreferences(c), key);
    }

    public static void setSettingInt(SharedPreferences sp, String key, int value) {
        sp.edit().putInt(key, value).commit();
    }

    public static void increaseSettingInt(SharedPreferences sp, String key) {
        sp.edit().putInt(key, sp.getInt(key, 0) + 1).commit();
    }

    public static void increaseSettingInt(SharedPreferences sp, String key, int increment) {
        sp.edit().putInt(key, sp.getInt(key, 0) + increment).commit();
    }

    public static int getSettingInt(Context c, String key, int defaultValue) {
        checkProcess(c);
        return PreferenceManager.getDefaultSharedPreferences(c).getInt(key, defaultValue);
    }

    public static void setSettingFloat(Context c, String key, float value) {
        checkProcess(c);
        PreferenceManager.getDefaultSharedPreferences(c).edit().putFloat(key, value).commit();
    }

    public static float getSettingFloat(Context c, String key, float defaultValue) {
        checkProcess(c);
        return PreferenceManager.getDefaultSharedPreferences(c).getFloat(key, defaultValue);
    }

    public static void setSettingLong(Context c, String key, long value) {
        try {
            checkProcess(c);
            PreferenceManager.getDefaultSharedPreferences(c).edit().putLong(key, value).commit();
        } catch (Throwable e) {
            MyLog.e(e);
        }
    }

    public static long getSettingLong(Context c, String key, long defaultValue) {
        checkProcess(c);
        return PreferenceManager.getDefaultSharedPreferences(c).getLong(key, defaultValue);
    }

    public static void increaseSettingLong(SharedPreferences sp, String key, long increment) {
        sp.edit().putLong(key, sp.getLong(key, 0) + increment).commit();
    }

    public static void removePreference(Context context, String key) {
        checkProcess(context);
        PreferenceManager.getDefaultSharedPreferences(context).edit().remove(key).commit();
    }

    public static void clearPreference(SharedPreferences p) {
        Editor editor = p.edit();
        editor.clear();
        editor.commit();
    }

    public static void dumpDefaultPreference(Context context) {
        checkProcess(context);
        dumpPreference(PreferenceManager.getDefaultSharedPreferences(context), "default preference:");
    }

    public static void dumpDefaultPreference(Context context, String preference) {
        dumpPreference(context.getSharedPreferences(preference, 0), preference);
    }

    private static void dumpPreference(SharedPreferences sp, String name) {
        StringBuffer sb = new StringBuffer();
        sb.append(name);
        sb.append("\n");
        Map<String, ?> preferenceSettings = sp.getAll();
        for (String key : preferenceSettings.keySet()) {
            sb.append(key);
            sb.append(":");
            sb.append(preferenceSettings.get(key));
            sb.append("\n");
        }
        MyLog.warn(sb.toString());
    }

    public static void checkProcess(Context context) {
    }
}
