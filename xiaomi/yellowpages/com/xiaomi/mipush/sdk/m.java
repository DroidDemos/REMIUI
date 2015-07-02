package com.xiaomi.mipush.sdk;

import android.content.Context;

public interface m {
    void onCommandResult(Context context, MiPushCommandMessage miPushCommandMessage);

    void onReceiveMessage(Context context, MiPushMessage miPushMessage);
}
