package com.xiaomi.xmsf.push.service.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.xiaomi.xmsf.push.service.BusinessApp;
import com.xiaomi.xmsf.push.service.MiuiPushActivateService;

public class AccountChangedReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        if (intent != null && intent.getAction().equals("android.accounts.LOGIN_ACCOUNTS_CHANGED")) {
            BusinessApp.getInstance(context.getApplicationContext()).setAccountAsAlias();
            MiuiPushActivateService.awakePushActivateService(context, MiuiPushActivateService.ACTION_ACCOUNT_CHANGE);
        }
    }
}
