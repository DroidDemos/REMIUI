package com.google.android.finsky.utils;

import android.accounts.Account;
import android.content.Context;
import android.content.res.Resources;
import android.net.NetworkInfo;
import android.os.Build.VERSION;
import android.provider.Settings.Global;
import android.provider.Settings.Secure;
import android.provider.Settings.SettingNotFoundException;
import android.text.TextUtils;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.analytics.PlayStore.PlayStoreSessionData;
import com.google.android.finsky.api.AccountHandler;
import com.google.android.finsky.billing.PromptForFopHelper;
import com.google.android.finsky.config.G;
import com.google.android.finsky.config.PurchaseAuth;
import com.google.android.finsky.receivers.NetworkStateChangedReceiver;

public class SessionStatsLogger {
    private static boolean sHaveLoggedSessionStats;

    public static void logSessionStatsIfNecessary(Context context) {
        if (!sHaveLoggedSessionStats && ((Boolean) G.enableSessionStatsLog.get()).booleanValue()) {
            sHaveLoggedSessionStats = true;
            try {
                logSessionStatsImpl(context);
            } catch (Exception e) {
                FinskyLog.wtf(e, "Fatal exception while logging session stats", new Object[0]);
            }
        }
    }

    private static void logSessionStatsImpl(Context context) {
        int unknownSourcesValue;
        PlayStoreSessionData sessionData = new PlayStoreSessionData();
        sessionData.globalAutoUpdateEnabled = ((Boolean) FinskyPreferences.AUTO_UPDATE_ENABLED.get()).booleanValue();
        sessionData.hasGlobalAutoUpdateEnabled = true;
        sessionData.globalAutoUpdateOverWifiOnly = ((Boolean) FinskyPreferences.AUTO_UPDATE_WIFI_ONLY.get()).booleanValue();
        sessionData.hasGlobalAutoUpdateOverWifiOnly = true;
        sessionData.autoUpdateCleanupDialogNumTimesShown = ((Integer) FinskyPreferences.autoUpdateMigrationDialogShownCount.get()).intValue();
        sessionData.hasAutoUpdateCleanupDialogNumTimesShown = true;
        Account[] accounts = AccountHandler.getAccounts(context);
        if (accounts != null) {
            sessionData.numAccountsOnDevice = accounts.length;
            sessionData.hasNumAccountsOnDevice = true;
        }
        NetworkInfo info = NetworkStateChangedReceiver.getCachedNetworkInfo(context);
        if (info != null) {
            sessionData.networkType = info.getType();
            sessionData.hasNetworkType = true;
            sessionData.networkSubType = info.getSubtype();
            sessionData.hasNetworkSubType = true;
        }
        String currentAccountName = FinskyApp.get().getCurrentAccountName();
        if (!TextUtils.isEmpty(currentAccountName)) {
            sessionData.purchaseAuthType = PurchaseAuth.getPurchaseAuthType(currentAccountName);
            sessionData.hasPurchaseAuthType = true;
        }
        sessionData.contentFilterLevel = ((Integer) FinskyPreferences.contentFilterLevel.get()).intValue();
        sessionData.hasContentFilterLevel = true;
        if (VERSION.SDK_INT >= 17) {
            unknownSourcesValue = Global.getInt(FinskyApp.get().getContentResolver(), "install_non_market_apps", -1);
        } else {
            unknownSourcesValue = Secure.getInt(FinskyApp.get().getContentResolver(), "install_non_market_apps", -1);
        }
        if (unknownSourcesValue == -1) {
            FinskyLog.wtf("Couldn't obtain INSTALL_NON_MARKET_APPS value", new Object[0]);
        } else {
            boolean z;
            if (unknownSourcesValue != 0) {
                z = true;
            } else {
                z = false;
            }
            sessionData.allowUnknownSources = z;
            sessionData.hasAllowUnknownSources = true;
        }
        sessionData.promptForFopData = PromptForFopHelper.getSessionLoggingData(currentAccountName);
        int resId = FinskyApp.get().getResources().getIdentifier("config_downloadDataDirSize", "integer", "android");
        if (resId != 0) {
            sessionData.downloadDataDirSizeMb = Resources.getSystem().getInteger(resId);
            sessionData.hasDownloadDataDirSizeMb = true;
        }
        try {
            sessionData.recommendedMaxDownloadOverMobileBytes = Secure.getLong(FinskyApp.get().getContentResolver(), "download_manager_max_bytes_over_mobile");
            sessionData.hasRecommendedMaxDownloadOverMobileBytes = true;
        } catch (SettingNotFoundException e) {
        }
        FinskyApp.get().getEventLogger().logSessionData(sessionData);
    }
}
