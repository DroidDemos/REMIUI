package com.google.android.finsky.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.utils.FinskyLog;

public class ClearCacheReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        FinskyLog.d("Received %s. Clearing cache and exiting.", intent.getAction());
        FinskyApp.get().clearCacheAsync(new Runnable() {
            public void run() {
                System.exit(0);
            }
        });
    }
}
