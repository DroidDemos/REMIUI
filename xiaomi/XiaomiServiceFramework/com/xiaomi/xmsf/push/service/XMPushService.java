package com.xiaomi.xmsf.push.service;

import android.app.IntentService;
import android.content.ComponentName;
import android.content.Intent;
import com.xiaomi.push.service.PushConstants;

public class XMPushService extends IntentService {
    public XMPushService() {
        super("XMPushService Bridge");
    }

    protected void onHandleIntent(Intent intent) {
        Intent newIntent = new Intent();
        newIntent.setComponent(new ComponentName(getPackageName(), PushConstants.PUSH_SERVICE_CLASS_NAME_JAR));
        newIntent.setAction(intent.getAction());
        newIntent.putExtras(intent);
        startService(newIntent);
    }
}
