package com.google.android.finsky.config;

import android.accounts.Account;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.analytics.FinskyEventLog;
import com.google.android.finsky.config.PreferenceFile.SharedPreference;
import com.google.android.finsky.experiments.Experiments;
import com.google.android.finsky.utils.FinskyPreferences;

public class PurchaseAuth {
    public static int getPurchaseAuthType(String accountName) {
        int purchaseAuth = ((Integer) FinskyPreferences.purchaseAuthType.get(accountName).get()).intValue();
        return purchaseAuth == -1 ? ((Integer) G.defaultPurchaseAuthentication.get()).intValue() : purchaseAuth;
    }

    public static void setAndLogPurchaseAuth(String accountName, int toSetting, Integer fromSetting, FinskyEventLog eventLog, String reason) {
        setPurchaseAuthType(FinskyPreferences.purchaseAuthType.get(accountName), FinskyPreferences.purchaseAuthVersionCode.get(accountName), toSetting);
        eventLog.logSettingsBackgroundEvent(400, Integer.valueOf(toSetting), fromSetting, reason);
    }

    static void setPurchaseAuthType(SharedPreference<Integer> purchaseAuthType, SharedPreference<Integer> purchaseAuthVersionCode, int value) {
        purchaseAuthType.put(Integer.valueOf(value));
        purchaseAuthVersionCode.put(Integer.valueOf(FinskyApp.get().getVersionCode()));
    }

    public static void migrateAndCleanupPreferences(Account[] accounts) {
        for (Account account : accounts) {
            String accountName = account.name;
            migratePreferences(FinskyPreferences.isGaiaAuthOptedOut.get(accountName), FinskyPreferences.purchaseAuthType.get(accountName));
            cleanupPreferences(FinskyPreferences.hasSeenPurchaseSessionMessage.get(accountName), FinskyPreferences.purchaseAuthType.get(accountName), FinskyPreferences.purchaseAuthVersionCode.get(accountName), FinskyApp.get().getExperiments(), FinskyApp.get().getEventLogger(accountName));
        }
    }

    static void migratePreferences(SharedPreference<Boolean> isGaiaAuthOptedOut, SharedPreference<Integer> purchaseAuthType) {
        if (isGaiaAuthOptedOut.exists()) {
            Boolean isOptedOut = (Boolean) isGaiaAuthOptedOut.get();
            if (isOptedOut != null) {
                purchaseAuthType.put(Integer.valueOf(isOptedOut.booleanValue() ? 0 : ((Integer) G.defaultPurchaseAuthentication.get()).intValue()));
                isGaiaAuthOptedOut.remove();
            }
        }
    }

    static void cleanupPreferences(SharedPreference<Boolean> hasSeenPurchaseSessionMessage, SharedPreference<Integer> purchaseAuthType, SharedPreference<Integer> purchaseAuthVersionCode, Experiments experiments, FinskyEventLog eventLog) {
        if (experiments.isEnabled("cl:billing.cleanup_auth_settings") && !((Boolean) hasSeenPurchaseSessionMessage.get()).booleanValue() && !purchaseAuthVersionCode.exists() && ((Integer) purchaseAuthType.get()).intValue() == 0) {
            purchaseAuthType.remove();
            eventLog.logSettingsBackgroundEvent(400, null, Integer.valueOf(0), "cleanup-auth-settings");
        }
    }
}
