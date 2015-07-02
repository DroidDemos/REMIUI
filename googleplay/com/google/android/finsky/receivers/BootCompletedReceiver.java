package com.google.android.finsky.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.api.AccountHandler;
import com.google.android.finsky.appstate.GmsCoreHelper;
import com.google.android.finsky.billing.iab.PendingNotificationsService;
import com.google.android.finsky.services.DailyHygiene;

public class BootCompletedReceiver extends BroadcastReceiver {
    public void onReceive(final Context context, Intent intent) {
        GmsCoreHelper.onBootCompleted();
        if (AccountHandler.getFirstAccount(context) != null) {
            FinskyApp.get().clearCacheAsync(new Runnable() {
                public void run() {
                    Intent serviceIntent = new Intent(PendingNotificationsService.ACTION_RESTART_ALARM);
                    serviceIntent.setClass(context, PendingNotificationsService.class);
                    context.startService(serviceIntent);
                    DailyHygiene.schedule(context, DailyHygiene.BOOT_DELAY_MS);
                }
            });
        }
    }
}
