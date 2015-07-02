package com.alipay.sdk.auth;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.widget.Toast;
import com.alipay.sdk.cons.MiniDefine;

public class AuthHelper {
    private static final String a = "com.eg.android.AlipayGphone";
    private static final int b = 65;

    private static boolean a(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(a, 128);
            if (packageInfo != null && packageInfo.versionCode >= b) {
                return true;
            }
            return false;
        } catch (NameNotFoundException e) {
            return false;
        }
    }

    public static void a(Activity activity, APAuthInfo aPAuthInfo) {
        if (a(activity)) {
            b(activity, aPAuthInfo);
        } else {
            c(activity, aPAuthInfo);
        }
    }

    private static void b(Activity activity, APAuthInfo aPAuthInfo) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("alipayauth://platformapi/startapp");
        stringBuilder.append("?appId=20000122");
        stringBuilder.append("&approveType=005");
        stringBuilder.append("&scope=kuaijie");
        stringBuilder.append("&productId=");
        stringBuilder.append(aPAuthInfo.getProductId());
        stringBuilder.append("&thirdpartyId=");
        stringBuilder.append(aPAuthInfo.getAppId());
        stringBuilder.append("&redirectUri=");
        stringBuilder.append(aPAuthInfo.getRedirectUri());
        a(activity, stringBuilder.toString());
    }

    private static void c(Activity activity, APAuthInfo aPAuthInfo) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("app_id=");
        stringBuilder.append(aPAuthInfo.getAppId());
        stringBuilder.append("&partner=");
        stringBuilder.append(aPAuthInfo.getPid());
        stringBuilder.append("&scope=kuaijie");
        stringBuilder.append("&login_goal=auth");
        stringBuilder.append("&redirect_url=");
        stringBuilder.append(aPAuthInfo.getRedirectUri());
        stringBuilder.append("&view=wap");
        stringBuilder.append("&prod_code=");
        stringBuilder.append(aPAuthInfo.getProductId());
        Intent intent = new Intent(activity, AuthActivity.class);
        intent.putExtra(MiniDefine.aM, stringBuilder.toString());
        intent.putExtra("redirectUri", aPAuthInfo.getRedirectUri());
        activity.startActivity(intent);
    }

    public static void a(Activity activity, String str) {
        try {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setData(Uri.parse(str));
            activity.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(activity, e.getMessage(), 1).show();
        }
    }
}
