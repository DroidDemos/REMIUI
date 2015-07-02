package com.miui.yellowpage.utils;

import android.accounts.Account;
import android.content.Context;
import android.text.TextUtils;
import com.miui.yellowpage.base.utils.Log;
import com.miui.yellowpage.base.utils.XiaomiAccount;
import com.xiaomi.mipush.sdk.q;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import miui.yellowpage.YellowPageUtils;

/* compiled from: Push */
public class u {
    private static final Pattern uM;

    static {
        uM = Pattern.compile("miui-yellowpage-sid-(\\d*)");
    }

    public static void x(Context context) {
        Log.d("Push", "initiate");
        if (P(context)) {
            q.h(context.getApplicationContext(), "2882303761517267647", "5201726799647");
        }
    }

    public static boolean P(Context context) {
        boolean isYellowPageEnable = YellowPageUtils.isYellowPageEnable(context);
        if (!isYellowPageEnable) {
            Log.d("Push", "yellow page not enabled, quit");
        }
        return isYellowPageEnable;
    }

    public static void Q(Context context) {
        R(context);
    }

    private static void R(Context context) {
        q.j(context, "miui-yellowpage-builtin-data", null);
        Account account = XiaomiAccount.getAccount(context);
        if (account != null && !TextUtils.isEmpty(account.name)) {
            q.i(context, account.name, null);
        }
    }

    public static void g(Context context, long j) {
        q.k(context, String.format("miui-yellowpage-sid-%d", new Object[]{Long.valueOf(j)}), null);
    }

    public static void h(Context context, long j) {
        q.j(context, String.format("miui-yellowpage-sid-%d", new Object[]{Long.valueOf(j)}), null);
    }

    public static long bc(String str) {
        Matcher matcher = uM.matcher(str);
        if (matcher.matches()) {
            return Long.parseLong(matcher.group(1));
        }
        Log.d("Push", "no match");
        return -1;
    }
}
