package com.xiaomi.account;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

public class GreenConnectivityReceiver extends BroadcastReceiver {
    public static final String TAG = "GreenConnectivityReceiver";

    public void onReceive(Context context, Intent intent) {
        if ("android.net.conn.CONNECTIVITY_CHANGE".equals(intent.getAction())) {
            NetworkInfo info = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (info != null && info.isConnected()) {
                Log.i(TAG, "network resumed, report sim binding");
            }
        }
    }

    public static void setEnabled(Context context, boolean enabled) {
        context.getPackageManager().setComponentEnabledSetting(new ComponentName(context, GreenConnectivityReceiver.class), enabled ? 1 : 2, 1);
    }
}
