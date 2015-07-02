package com.google.android.finsky.billing.carrierbilling.flow.association;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Looper;
import android.telephony.SmsManager;
import android.text.TextUtils;
import com.google.android.finsky.utils.FinskyLog;

public class SmsSender {
    private static SmsSendListener mListener;

    public interface SmsSendListener {

        public enum SmsSenderResult {
            RESULT_OK,
            RESULT_ERROR
        }

        void onResult(SmsSenderResult smsSenderResult);
    }

    public static void send(Context context, String destinationAddress, String serviceCenterAddress, String text, SmsSendListener listener) {
        mListener = listener;
        if (TextUtils.isEmpty(destinationAddress)) {
            FinskyLog.w("Send sms failed invalid destination address", new Object[0]);
            dispatch(SmsSenderResult.RESULT_ERROR);
            return;
        }
        PendingIntent sentIntent = null;
        if (listener != null) {
            sentIntent = PendingIntent.getBroadcast(context, 0, new Intent("android.finsky.SMS_SENT"), 0);
            context.registerReceiver(new BroadcastReceiver() {
                public void onReceive(Context context, Intent intent) {
                    context.unregisterReceiver(this);
                    if (getResultCode() == -1) {
                        SmsSender.dispatch(SmsSenderResult.RESULT_OK);
                        return;
                    }
                    FinskyLog.w("Error while sending sms " + getResultCode(), new Object[0]);
                    SmsSender.dispatch(SmsSenderResult.RESULT_ERROR);
                }
            }, new IntentFilter("android.finsky.SMS_SENT"));
        }
        SmsManager.getDefault().sendTextMessage(destinationAddress, serviceCenterAddress, text, sentIntent, null);
    }

    private static void dispatch(final SmsSenderResult result) {
        if (mListener != null) {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                public void run() {
                    SmsSender.mListener.onResult(result);
                }
            });
        }
    }
}
