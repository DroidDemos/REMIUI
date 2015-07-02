package com.google.android.finsky.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkStateChangedReceiver extends BroadcastReceiver {
    private static NetworkInfo sCachedNetworkInfo;
    private static final Object sLock;

    static {
        sLock = new Object();
    }

    public void onReceive(Context context, Intent intent) {
        updateCachedNetworkInfo(context);
    }

    public static NetworkInfo getCachedNetworkInfo(Context context) {
        NetworkInfo networkInfo;
        synchronized (sLock) {
            if (sCachedNetworkInfo == null) {
                updateCachedNetworkInfo(context);
            }
            networkInfo = sCachedNetworkInfo;
        }
        return networkInfo;
    }

    public static void flushCachedState() {
        synchronized (sLock) {
            sCachedNetworkInfo = null;
        }
    }

    private static void updateCachedNetworkInfo(Context context) {
        synchronized (sLock) {
            sCachedNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        }
    }
}
