package com.google.android.finsky.receivers;

import android.accounts.Account;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.api.AccountHandler;
import com.google.android.finsky.services.ContentSyncService;
import com.google.android.finsky.services.DailyHygiene;
import com.google.android.finsky.services.RestoreService;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.FinskyPreferences;
import com.google.android.finsky.utils.VendingPreferences;

public class AccountsChangedReceiver extends BroadcastReceiver {
    public void onReceive(final Context context, Intent intent) {
        FinskyPreferences.autoUpdateFirstTimeForAccounts.put(Boolean.valueOf(true));
        FinskyApp.get().getLibraries().load(null);
        restoreNewAccountApps(context);
        DailyHygiene.schedule(context, DailyHygiene.IMMEDIATE_DELAY_MS);
        ContentSyncService.get().scheduleSync();
        FinskyApp.get().clearCacheAsync(new Runnable() {
            public void run() {
                String currentAccountName = FinskyApp.get().getCurrentAccountName();
                if (currentAccountName != null && !AccountHandler.hasAccount(currentAccountName, context)) {
                    System.exit(0);
                }
            }
        });
    }

    private void restoreNewAccountApps(Context context) {
        Account[] accounts = AccountHandler.getAccounts(context);
        String sourceAndroidId = (String) VendingPreferences.RESTORED_ANDROID_ID.get();
        if (!TextUtils.isEmpty(sourceAndroidId)) {
            String[] newAccountNames = VendingPreferences.getNewAccounts(accounts);
            if (newAccountNames.length > 0) {
                FinskyLog.d("Restoring apps for %d new accounts.", Integer.valueOf(newAccountNames.length));
                RestoreService.restoreAccounts(context, sourceAndroidId, newAccountNames[0]);
            }
        }
        VendingPreferences.saveCurrentAccountList(accounts);
    }
}
