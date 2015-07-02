package com.xiaomi.mipush.sdk;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public abstract class b extends BroadcastReceiver implements m {
    public final void onReceive(Context context, Intent intent) {
        i.a(context, new f(intent, this));
    }
}
