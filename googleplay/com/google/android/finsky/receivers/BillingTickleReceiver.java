package com.google.android.finsky.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.google.android.finsky.billing.iab.MarketBillingService;
import com.google.android.finsky.utils.FinskyLog;

public class BillingTickleReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        String packageName = intent.getStringExtra("asset_package");
        setResultCode(-1);
        if (!intent.hasCategory("com.android.vending.billing.IN_APP_NOTIFY")) {
            FinskyLog.w("Intent does not contain a supported category; package: %s", packageName);
            setResultCode(0);
        } else if (!MarketBillingService.sendNotify(context, packageName, intent.getStringExtra("notification_id"))) {
            setResultCode(0);
        }
    }
}
