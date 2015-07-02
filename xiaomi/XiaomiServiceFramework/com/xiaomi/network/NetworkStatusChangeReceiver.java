package com.xiaomi.network;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class NetworkStatusChangeReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        if (HostManager.getInstance().isWIFIConnected()) {
            context.startService(new Intent(context, HostRefreshService.class));
        }
    }
}
