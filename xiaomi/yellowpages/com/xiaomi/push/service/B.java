package com.xiaomi.push.service;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

class B extends Handler {
    final /* synthetic */ u vB;

    B(u uVar, Looper looper) {
        this.vB = uVar;
        super(looper);
    }

    public void handleMessage(Message message) {
        this.vB.b = true;
        this.vB.a = System.currentTimeMillis();
        if (message.obj instanceof b) {
            ((b) message.obj).c();
        }
        this.vB.b = false;
    }
}
