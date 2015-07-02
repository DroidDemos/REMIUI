package com.google.android.wallet.instrumentmanager.common.util;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Looper;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.util.Log;

public class SmsSender {
    private final Context mAppContext;
    private final SmsSendListener mListener;

    public interface SmsSendListener {
        void onResult(int i);
    }

    public SmsSender(Context appContext, SmsSendListener listener) {
        this.mAppContext = appContext;
        this.mListener = listener;
    }

    public void send(String destinationAddress, String messageBody) {
        if (TextUtils.isEmpty(destinationAddress)) {
            throw new IllegalArgumentException("SMS destination address must be provided");
        }
        PendingIntent sentIntent = null;
        if (this.mListener != null) {
            sentIntent = PendingIntent.getBroadcast(this.mAppContext, 0, new Intent("com.google.android.wallet.instrumentmanager.SMS_SENT_ACTION"), 0);
            this.mAppContext.registerReceiver(new BroadcastReceiver() {
                public void onReceive(Context context, Intent intent) {
                    context.unregisterReceiver(this);
                    int resultCode = getResultCode();
                    if (resultCode == -1) {
                        SmsSender.this.dispatch(1);
                        return;
                    }
                    Log.d("SmsSender", "SMS failed, result code: " + resultCode);
                    if (resultCode == 1) {
                        Log.d("SmsSender", "SMS failed, error code: " + intent.getIntExtra("errorCode", -1));
                    }
                    SmsSender.this.dispatch(2);
                }
            }, new IntentFilter("com.google.android.wallet.instrumentmanager.SMS_SENT_ACTION"));
        }
        SmsManager.getDefault().sendTextMessage(destinationAddress, null, messageBody, sentIntent, null);
    }

    private void dispatch(final int result) {
        if (this.mListener != null) {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                public void run() {
                    SmsSender.this.mListener.onResult(result);
                }
            });
        }
    }
}
