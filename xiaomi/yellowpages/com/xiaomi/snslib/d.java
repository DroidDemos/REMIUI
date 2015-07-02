package com.xiaomi.snslib;

import android.accounts.AccountManager;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import com.alipay.mobilesecuritysdk.constant.ConfigConstant;
import com.alipay.sdk.cons.MiniDefine;
import com.ta.utdid2.core.persistent.TransactionXMLFile;
import com.xiaomi.snslib.ShareToSNS.SNSType;
import java.io.File;
import miui.accounts.ExtraAccountManager;

/* compiled from: SNSUtil */
public class d {
    public static boolean a(Context context, SNSType sNSType) {
        try {
            if (context.getPackageManager().getPackageInfo(c(sNSType), 0) != null) {
                return true;
            }
            return false;
        } catch (NameNotFoundException e) {
            return false;
        }
    }

    public static void a(Context context, String str, String str2, SNSType sNSType) {
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setPackage(c(sNSType));
        intent.setType("image/*");
        intent.putExtra("android.intent.extra.TEXT", str);
        if (!TextUtils.isEmpty(str2)) {
            intent.putExtra("android.intent.extra.STREAM", Uri.fromFile(new File(str2)));
        }
        context.startActivity(intent);
    }

    public static com.weibo.sdk.android.d a(String str, String str2, String str3, String str4, String str5) {
        com.weibo.sdk.android.d dVar = new com.weibo.sdk.android.d();
        dVar.t("access_token", str);
        dVar.t(MiniDefine.b, str2);
        if (!TextUtils.isEmpty(str3)) {
            dVar.t("pic", str3);
        }
        if (!(TextUtils.isEmpty(str4) || TextUtils.isEmpty(str5))) {
            dVar.t("lat", str4);
            dVar.t("long", str5);
        }
        return dVar;
    }

    public static String a(SNSType sNSType) {
        String str = ConfigConstant.WIRELESS_FILENAME;
        switch (a.du[sNSType.ordinal()]) {
            case TransactionXMLFile.MODE_WORLD_READABLE /*1*/:
                return "extra_sina_weibo_access_token";
            default:
                return str;
        }
    }

    public static String b(SNSType sNSType) {
        String str = ConfigConstant.WIRELESS_FILENAME;
        switch (a.du[sNSType.ordinal()]) {
            case TransactionXMLFile.MODE_WORLD_READABLE /*1*/:
                return "sina";
            default:
                return str;
        }
    }

    public static void a(Activity activity, SNSType sNSType) {
        if (ExtraAccountManager.getXiaomiAccount(activity) == null) {
            b(activity, sNSType);
        } else {
            b((Context) activity, sNSType);
        }
    }

    public static void b(Activity activity, SNSType sNSType) {
        Bundle bundle = new Bundle();
        bundle.putInt("extra_bind_sns_type", d(sNSType));
        AccountManager.get(activity).addAccount("com.xiaomi", null, null, bundle, activity, null, null);
    }

    public static void b(Context context, SNSType sNSType) {
        Intent intent = new Intent();
        intent.setAction("android.app.action.ADD_SNS_ACCOUNT");
        intent.putExtra("extra_bind_sns_type", d(sNSType));
        if (!(context instanceof Activity)) {
            intent.setFlags(268435456);
        }
        context.startActivity(intent);
    }

    private static String c(SNSType sNSType) {
        String str = ConfigConstant.WIRELESS_FILENAME;
        switch (a.du[sNSType.ordinal()]) {
            case TransactionXMLFile.MODE_WORLD_READABLE /*1*/:
                return "com.sina.weibo";
            default:
                return str;
        }
    }

    private static int d(SNSType sNSType) {
        switch (a.du[sNSType.ordinal()]) {
            case TransactionXMLFile.MODE_WORLD_READABLE /*1*/:
                return 1;
            default:
                return -1;
        }
    }
}
