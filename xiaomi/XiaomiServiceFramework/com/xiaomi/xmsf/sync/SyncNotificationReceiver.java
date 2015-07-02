package com.xiaomi.xmsf.sync;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import miui.os.Build;

public class SyncNotificationReceiver extends BroadcastReceiver {
    private static final String TAG = "SyncNotificationReceiver";

    public void onReceive(Context context, Intent intent) {
        if (intent != null) {
            Log.d(TAG, Constants.ACTIONTYPE_RECEIVED + intent.getExtras());
            if (intent.getAction().equals("android.intent.action.SYNC_STATE_CHANGED") && !Build.IS_STABLE_VERSION) {
                if (intent.getBooleanExtra("active", false)) {
                    Log.i(TAG, "SyncNotificationService:begin");
                    SyncNotificationService.begin(context);
                    return;
                }
                Log.i(TAG, "SyncNotificationService:end");
                SyncNotificationService.end(context);
            }
        }
    }
}
