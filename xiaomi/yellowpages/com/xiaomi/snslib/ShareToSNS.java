package com.xiaomi.snslib;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.miui.yellowpage.R;
import com.ta.utdid2.core.persistent.TransactionXMLFile;

public class ShareToSNS {

    public enum SNSType {
        SINA
    }

    public static void a(Context context, String str, String str2, SNSType sNSType, j jVar) {
        a(context, str, str2, null, null, sNSType, jVar, false, true);
    }

    public static void a(Context context, String str, String str2, String str3, String str4, SNSType sNSType, j jVar, boolean z, boolean z2) {
        a(context, str, str2, str3, str4, sNSType, jVar, false, true, false);
    }

    public static void a(Context context, String str, String str2, String str3, String str4, SNSType sNSType, j jVar, boolean z, boolean z2, boolean z3) {
        switch (c.du[sNSType.ordinal()]) {
            case TransactionXMLFile.MODE_WORLD_READABLE /*1*/:
                a(context, str, str2, str3, str4, jVar, z, z2, z3);
                return;
            default:
                return;
        }
    }

    private static void a(Context context, String str, String str2, String str3, String str4, j jVar, boolean z, boolean z2, boolean z3) {
        new e(z3, context, z, str, str2, jVar, str3, str4, z2).execute(new String[]{str, str2, str3, str4});
    }

    private static boolean H(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return (activeNetworkInfo == null || !activeNetworkInfo.isConnected() || connectivityManager.isActiveNetworkMetered()) ? false : true;
    }

    private static int a(SNSType sNSType, String str) {
        switch (c.du[sNSType.ordinal()]) {
            case TransactionXMLFile.MODE_WORLD_READABLE /*1*/:
                return ab(str);
            default:
                return R.string.sina_share_fail;
        }
    }

    private static int ab(String str) {
        if (TextUtils.isEmpty(str) || !TextUtils.isDigitsOnly(str)) {
            return R.string.sina_share_fail;
        }
        switch (Integer.parseInt(str)) {
            case 20019:
                return R.string.sina_share_fail_content_repeat;
            case 21315:
            case 21327:
                return R.string.sina_token_expired_warning;
            case 21319:
            case 21332:
            case 21501:
                return R.string.sina_token_invalid_warning;
            default:
                return R.string.sina_share_fail;
        }
    }

    private static void b(Context context, int i) {
        new Handler(Looper.getMainLooper()).post(new b(context, i));
    }

    private static boolean ac(String str) {
        if (!TextUtils.isEmpty(str) && TextUtils.isDigitsOnly(str)) {
            switch (Integer.parseInt(str)) {
                case 21315:
                case 21319:
                case 21327:
                case 21332:
                case 21501:
                    return true;
            }
        }
        return false;
    }
}
