package com.google.android.finsky.utils;

import android.accounts.Account;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Build.VERSION;
import com.google.android.finsky.config.PreferenceFile;
import com.google.android.finsky.config.PreferenceFile.SharedPreference;
import java.util.List;
import java.util.Map;

public class VendingPreferences {
    public static final String[] ACCOUNT_SPECIFIC_PREFIXES;
    public static SharedPreference<Boolean> AUTO_ADD_SHORTCUTS;
    @Deprecated
    public static SharedPreference<Boolean> AUTO_UPDATE_BY_DEFAULT;
    @Deprecated
    public static SharedPreference<Boolean> AUTO_UPDATE_WIFI_ONLY;
    public static SharedPreference<Boolean> BACKED_UP;
    public static SharedPreference<String> CACHED_GL_EXTENSIONS;
    private static final String[] EMPTY_STRING_ARRAY;
    public static SharedPreference<Boolean> GL_DRIVER_CRASHED;
    public static SharedPreference<Boolean> INTEREST_BASED_ADS_ENABLED;
    public static SharedPreference<String> LAST_BUILD_FINGERPRINT;
    public static SharedPreference<Boolean> NOTIFY_UPDATES;
    public static SharedPreference<String> RESTORED_ANDROID_ID;
    private static PreferenceFile sPrefs;

    static {
        EMPTY_STRING_ARRAY = new String[0];
        ACCOUNT_SPECIFIC_PREFIXES = new String[]{"last_checkin_hash_", "last_systems_apps_hash_", "last_market_alarm_timeout_", "last_market_alarm_start_time_", "account_exists_"};
        sPrefs = new PreferenceFile("vending_preferences", 0);
        CACHED_GL_EXTENSIONS = sPrefs.value("cached_gl_extensions", (String) null);
        GL_DRIVER_CRASHED = sPrefs.value("gl_driver_crashed", Boolean.valueOf(false));
        LAST_BUILD_FINGERPRINT = sPrefs.value("last_build_fingerprint", (String) null);
        BACKED_UP = sPrefs.value("finsky_backed_up", Boolean.valueOf(false));
        RESTORED_ANDROID_ID = sPrefs.value("finsky_restored_android_id", (String) null);
        INTEREST_BASED_ADS_ENABLED = sPrefs.value("ads_interest_based", Boolean.valueOf(true));
        NOTIFY_UPDATES = sPrefs.value("notify_updates", Boolean.valueOf(true));
        AUTO_UPDATE_WIFI_ONLY = sPrefs.value("update_over_wifi_only", Boolean.valueOf(false));
        AUTO_UPDATE_BY_DEFAULT = sPrefs.value("auto_update_default", Boolean.valueOf(false));
        AUTO_ADD_SHORTCUTS = sPrefs.value("auto_add_shortcuts", Boolean.valueOf(VERSION.SDK_INT >= 11));
    }

    public static PreferenceFile getMainPrefs() {
        return sPrefs;
    }

    public static SharedPreference<Long> getMarketAlarmTimeout(String account) {
        return sPrefs.value("last_market_alarm_timeout_" + account, Long.valueOf(0));
    }

    public static SharedPreference<Long> getMarketAlarmStartTime(String account) {
        return sPrefs.value("last_market_alarm_start_time_" + account, Long.valueOf(0));
    }

    public static boolean contains(Account[] accounts, String accountName) {
        for (Account account : accounts) {
            if (account.name.equals(accountName)) {
                return true;
            }
        }
        return false;
    }

    public static SharedPreference<Boolean> getAccountExists(String account) {
        return sPrefs.value("account_exists_" + account, Boolean.valueOf(false));
    }

    public static void saveCurrentAccountList(Account[] accounts) {
        SharedPreferences prefsFile = sPrefs.open();
        Map<String, ?> all = prefsFile.getAll();
        Editor editor = prefsFile.edit();
        boolean dirty = false;
        for (String key : all.keySet()) {
            if (key.startsWith("account_exists_") && !contains(accounts, key.substring("account_exists_".length()))) {
                editor.remove(key);
                dirty = true;
            }
        }
        if (dirty) {
            PreferenceFile.commit(editor);
        }
        for (Account account : accounts) {
            if (!getAccountExists(account.name).exists()) {
                getAccountExists(account.name).put(Boolean.valueOf(true));
            }
        }
    }

    public static String[] getNewAccounts(Account[] accounts) {
        List<String> resultList = null;
        for (Account account : accounts) {
            if (!getAccountExists(account.name).exists()) {
                if (resultList == null) {
                    resultList = Lists.newArrayList(1);
                }
                resultList.add(account.name);
            }
        }
        if (resultList == null) {
            return EMPTY_STRING_ARRAY;
        }
        return (String[]) resultList.toArray(new String[resultList.size()]);
    }
}
