package com.xiaomi.push.service.receivers;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.MyLog;
import com.xiaomi.push.service.PushConstants;
import com.xiaomi.push.service.PushServiceConstants;
import com.xiaomi.push.service.XMPushService;
import com.xiaomi.push.service.timers.AlarmManagerTimer;

public class PingReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        new AlarmManagerTimer(context).registerPing(false);
        MyLog.v(intent.getPackage() + " is the package name");
        if (!PushConstants.ACTION_PING_TIMER.equals(intent.getAction())) {
            MyLog.warn("cancel the old ping timer");
            ((AlarmManager) context.getSystemService(PushServiceConstants.EXTENSION_ELEMENT_EXT_SUB_ELE_ALARM)).cancel(PendingIntent.getBroadcast(context, 0, new Intent(context, PingReceiver.class), 0));
        } else if (TextUtils.equals(context.getPackageName(), intent.getPackage())) {
            MyLog.v("Ping XMChannelService on timer");
            try {
                Intent serviceIntent = new Intent(context, XMPushService.class);
                serviceIntent.setAction(PushServiceConstants.ACTION_TIMER);
                context.startService(serviceIntent);
            } catch (Throwable e) {
                MyLog.e(e);
            }
        }
    }
}
