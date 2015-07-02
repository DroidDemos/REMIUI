package com.xiaomi.push.service.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.xiaomi.push.service.PushServiceConstants;
import com.xiaomi.push.service.XMPushService;

public class PkgUninstallReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        if (intent != null && "android.intent.action.PACKAGE_REMOVED".equals(intent.getAction())) {
            Uri data = intent.getData();
            if (data != null) {
                Intent serviceIntent = new Intent(context, XMPushService.class);
                serviceIntent.setAction(PushServiceConstants.ACTION_UNINSTALL);
                serviceIntent.putExtra(PushServiceConstants.EXTRA_UNINSTALL_PKG_NAME, data.getEncodedSchemeSpecificPart());
                context.startService(serviceIntent);
            }
        }
    }
}
