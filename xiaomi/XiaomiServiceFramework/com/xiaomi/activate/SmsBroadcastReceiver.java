package com.xiaomi.activate;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import java.util.concurrent.CountDownLatch;

public class SmsBroadcastReceiver extends BroadcastReceiver {
    private static final boolean DEBUG = true;
    private static final String TAG = "SmsBroadcastReceiver";
    private volatile CountDownLatch countDownLatch;
    private volatile int result;

    public void onReceive(Context context, Intent intent) {
        this.result = getResultCode();
        Log.d(TAG, "sms sent, result:" + this.result);
        this.countDownLatch.countDown();
    }

    public void setCountDownLatch(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    public int getResult() {
        return this.result;
    }

    public void reset() {
        this.result = 4;
    }
}
