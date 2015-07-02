package com.google.android.finsky.receivers;

import android.content.Context;
import android.content.Intent;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.download.DownloadReceiver;
import com.google.android.finsky.utils.FinskyLog;

public class DeclineAssetReceiver extends DownloadReceiver {
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("com.google.android.c2dm.intent.RECEIVE")) {
            setResultCode(-1);
            if (intent.getStringExtra("from").equals("google.com")) {
                String packageName = intent.getStringExtra("asset_package");
                String declinedReasonStr = intent.getStringExtra("decline_reason");
                int reason = -1;
                if (declinedReasonStr != null) {
                    try {
                        reason = Integer.valueOf(declinedReasonStr).intValue();
                    } catch (NumberFormatException e) {
                        FinskyLog.w("Non-numeric decline reason: %s", declinedReasonStr);
                    }
                }
                FinskyLog.d("Received PURCHASE_DECLINED tickle for %s reason=%d", packageName, Integer.valueOf(reason));
                FinskyApp.get().getEventLogger().logBackgroundEvent(200, packageName, String.valueOf(reason), 0, null, null);
            }
        }
    }
}
