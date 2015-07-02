package com.google.android.finsky.billing;

import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.api.DfeApi;
import com.google.android.finsky.config.PreferenceFile.SharedPreference;
import com.google.android.finsky.protos.Toc.BillingConfig;
import com.google.android.finsky.protos.Toc.TocResponse;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.GetTocHelper;
import com.google.android.finsky.utils.Utils;
import com.google.android.finsky.utils.VendingPreferences;

public class InAppBillingSetting {
    public static boolean isEnabled(String account, int clientVersion) {
        return isEnabled(account, clientVersion, false);
    }

    static boolean isEnabled(String account, int clientVersion, boolean debugMode) {
        if (account == null) {
            return false;
        }
        boolean z;
        SharedPreference<Integer> serverMaxVersionPref = getSharedIabMaxVersionPreference(account);
        if (!serverMaxVersionPref.exists()) {
            DfeApi dfeApi = FinskyApp.get().getDfeApi(account);
            if (dfeApi == null) {
                FinskyLog.w("Unknown account %s", account);
                return false;
            } else if (debugMode) {
                FinskyLog.w("Skipping fetching toc in debug mode.", new Object[0]);
            } else {
                TocResponse response = GetTocHelper.getTocBlocking(dfeApi);
                if (!(response == null || response.billingConfig == null)) {
                    setVersionFromBillingConfig(account, response.billingConfig);
                }
            }
        }
        if (clientVersion <= ((Integer) serverMaxVersionPref.get()).intValue()) {
            z = true;
        } else {
            z = false;
        }
        return z;
    }

    public static void setVersionFromBillingConfig(String account, BillingConfig billingConfig) {
        if (billingConfig != null && billingConfig.hasMaxIabApiVersion) {
            setVersion(account, billingConfig.maxIabApiVersion);
        }
    }

    private static void setVersion(String account, int maxIabApiVersion) {
        SharedPreference<Integer> sharedPref = getSharedIabMaxVersionPreference(account);
        if (sharedPref != null) {
            sharedPref.put(Integer.valueOf(maxIabApiVersion));
        }
    }

    private static SharedPreference<Integer> getSharedIabMaxVersionPreference(String account) {
        return VendingPreferences.getMainPrefs().value("IAB_VERSION_" + Utils.urlEncode(account), Integer.valueOf(0));
    }
}
