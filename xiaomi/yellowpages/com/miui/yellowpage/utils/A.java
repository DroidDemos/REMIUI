package com.miui.yellowpage.utils;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import com.alipay.sdk.app.PayTask;

/* compiled from: Payment */
final class A implements Runnable {
    final /* synthetic */ String CC;
    final /* synthetic */ Handler CD;
    final /* synthetic */ Activity val$activity;

    A(Activity activity, String str, Handler handler) {
        this.val$activity = activity;
        this.CC = str;
        this.CD = handler;
    }

    public void run() {
        String pay = new PayTask(this.val$activity).pay(this.CC);
        Message message = new Message();
        message.what = 1;
        message.obj = pay;
        this.CD.sendMessage(message);
    }
}
