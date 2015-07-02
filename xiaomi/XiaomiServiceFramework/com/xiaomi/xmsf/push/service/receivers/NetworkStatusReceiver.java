package com.xiaomi.xmsf.push.service.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.xiaomi.channel.commonutils.network.Network;
import com.xiaomi.mipush.sdk.PushServiceClient;
import com.xiaomi.push.service.PushServiceConstants;
import com.xiaomi.push.service.XMPushService;
import com.xiaomi.smack.util.TrafficUtils;

public class NetworkStatusReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        Intent serviceIntent = new Intent(context, XMPushService.class);
        serviceIntent.setAction(PushServiceConstants.ACTION_NETWORK_STATUS_CHANGED);
        context.startService(serviceIntent);
        TrafficUtils.notifyNetworkChanage(context);
        if (Network.hasNetwork(context) && PushServiceClient.getInstance(context).isProvisioned()) {
            PushServiceClient.getInstance(context).processRegisterTask();
        }
    }
}
