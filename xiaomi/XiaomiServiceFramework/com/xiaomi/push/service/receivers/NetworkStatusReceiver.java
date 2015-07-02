package com.xiaomi.push.service.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.xiaomi.channel.commonutils.logger.MyLog;
import com.xiaomi.channel.commonutils.network.Network;
import com.xiaomi.mipush.sdk.AppInfoHolder;
import com.xiaomi.mipush.sdk.PushServiceClient;
import com.xiaomi.push.service.PushServiceConstants;
import com.xiaomi.push.service.XMPushService;

public class NetworkStatusReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        if (!(PushServiceClient.getInstance(context).shouldUseMIUIPush() || !AppInfoHolder.getInstance(context).appRegistered() || AppInfoHolder.getInstance(context).invalidated())) {
            try {
                Intent serviceIntent = new Intent(context, XMPushService.class);
                serviceIntent.setAction(PushServiceConstants.ACTION_NETWORK_STATUS_CHANGED);
                context.startService(serviceIntent);
            } catch (Throwable e) {
                MyLog.e(e);
            }
        }
        if (Network.hasNetwork(context) && PushServiceClient.getInstance(context).isProvisioned()) {
            PushServiceClient.getInstance(context).processRegisterTask();
        }
    }
}
