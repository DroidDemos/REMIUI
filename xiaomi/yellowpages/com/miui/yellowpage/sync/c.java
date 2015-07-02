package com.miui.yellowpage.sync;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.ContentResolver;
import android.content.Context;
import android.os.Bundle;
import com.alipay.sdk.cons.MiniDefine;
import com.miui.yellowpage.base.utils.Log;
import com.miui.yellowpage.providers.yellowpage.p;
import com.xiaomi.micloudsdk.request.Request;
import java.util.concurrent.TimeUnit;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: Sync */
public class c {
    public static void x(Context context) {
        Request.init(context);
        a(context, true);
    }

    public static Account a(Context context, boolean z) {
        Account[] accountsByType = AccountManager.get(context).getAccountsByType("miui.yellowpage");
        if (accountsByType != null && accountsByType.length > 0) {
            return accountsByType[0];
        }
        if (z) {
            return y(context);
        }
        return null;
    }

    public static Account y(Context context) {
        if (!p.hz()) {
            return null;
        }
        AccountManager accountManager = AccountManager.get(context);
        Account account = new Account("default_account", "miui.yellowpage");
        long toSeconds = TimeUnit.HOURS.toSeconds(48);
        ContentResolver.setIsSyncable(account, "miui.yellowpage", 1);
        ContentResolver.setSyncAutomatically(account, "miui.yellowpage", true);
        accountManager.addAccountExplicitly(account, null, new Bundle());
        Bundle bundle = new Bundle();
        bundle.putString(MiniDefine.at, Q("ugc"));
        bundle.putBoolean("triggered_by_push", true);
        bundle.putBoolean("immortal", true);
        ContentResolver.addPeriodicSync(account, "miui.yellowpage", bundle, TimeUnit.DAYS.toSeconds(1));
        bundle = new Bundle();
        bundle.putString(MiniDefine.at, Q("subscriber"));
        bundle.putBoolean("triggered_by_push", true);
        bundle.putBoolean("immortal", true);
        ContentResolver.addPeriodicSync(account, "miui.yellowpage", bundle, toSeconds);
        bundle = new Bundle();
        bundle.putString(MiniDefine.at, Q("pull_task_daemon"));
        bundle.putBoolean("triggered_by_push", true);
        bundle.putBoolean("immortal", true);
        ContentResolver.addPeriodicSync(account, "miui.yellowpage", bundle, toSeconds);
        return account;
    }

    public static void z(Context context) {
        AccountManager accountManager = AccountManager.get(context);
        Account[] accountsByType = accountManager.getAccountsByType("miui.yellowpage");
        for (int i = 0; i < accountsByType.length; i++) {
            Log.d("Sync", "account type:" + accountsByType[i].type);
            if (accountsByType[i].type.intern() == "miui.yellowpage") {
                Log.d("Sync", "removing account:" + accountsByType[0]);
                accountManager.removeAccount(accountsByType[i], null, null);
            }
        }
    }

    private static String Q(String str) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(MiniDefine.m, str);
            return jSONObject.toString();
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
