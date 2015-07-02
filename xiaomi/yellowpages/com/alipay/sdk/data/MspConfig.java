package com.alipay.sdk.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.widget.TextView;
import com.alipay.mobilesecuritysdk.constant.ConfigConstant;
import com.alipay.mobilesecuritysdk.face.SecurityClientMobile;
import com.alipay.sdk.cons.GlobalConstants;
import com.alipay.sdk.cons.GlobalDefine;
import com.alipay.sdk.cons.MiniDefine;
import com.alipay.sdk.net.MspHttpClient;
import com.alipay.sdk.sys.GlobalContext;
import com.alipay.sdk.sys.UserLocation;
import com.alipay.sdk.tid.TidInfo;
import com.alipay.sdk.util.BaseHelper;
import com.alipay.sdk.util.DeviceInfo;
import java.util.HashMap;
import java.util.Random;

public class MspConfig {
    private static final String a = "virtualImeiAndImsi";
    private static final String b = "virtual_imei";
    private static final String c = "virtual_imsi";
    private static MspConfig d;
    private String e;
    private String f;
    private String g;
    private String h;

    public String a() {
        return this.h;
    }

    private MspConfig() {
        this.g = "sdk-and-lite";
    }

    public static MspConfig b() {
        if (d == null) {
            d = new MspConfig();
        }
        return d;
    }

    public String c() {
        return GlobalConstants.c;
    }

    public void a(String str) {
        if (!TextUtils.isEmpty(str)) {
            PreferenceManager.getDefaultSharedPreferences(GlobalContext.a().b()).edit().putString(GlobalDefine.n, str).commit();
            GlobalConstants.c = str;
        }
    }

    public String d() {
        return GlobalConstants.d;
    }

    public String a(Context context) {
        return Float.toString(new TextView(context).getTextSize());
    }

    public String a(TidInfo tidInfo) {
        String g;
        String a;
        String a2;
        String c;
        String b;
        Context b2 = GlobalContext.a().b();
        DeviceInfo a3 = DeviceInfo.a(b2);
        if (TextUtils.isEmpty(this.e)) {
            g = DeviceInfo.g();
            a = BaseHelper.a();
            a2 = BaseHelper.a(b2);
            c = BaseHelper.c();
            b = BaseHelper.b(b2);
            this.e = "Msp/9.1.0" + " (" + g + ";" + a + ";" + a2 + ";" + c + ";" + b + ";" + a(b2);
        }
        String b3 = DeviceInfo.b(b2).b();
        g = BaseHelper.d(b2);
        a = d();
        a2 = a3.b();
        c = a3.c();
        b = f();
        String e = e();
        this.h = tidInfo.b();
        String replace = Build.MANUFACTURER.replace(";", " ");
        String replace2 = Build.MODEL.replace(";", " ");
        boolean g2 = GlobalContext.g();
        String e2 = a3.e();
        String c2 = c(b2);
        String d = d(b2);
        StringBuilder stringBuilder = new StringBuilder();
        StringBuilder append = stringBuilder.append(this.e).append(";");
        r16.append(b3).append(";").append(g).append(";").append(a).append(";").append(a2).append(";").append(c).append(";").append(this.h).append(";").append(replace).append(";").append(replace2).append(";").append(g2).append(";").append(e2).append(";").append(UserLocation.c()).append(";").append(this.g).append(";").append(b).append(";").append(e).append(";").append(c2).append(";").append(d);
        if (tidInfo != null) {
            HashMap hashMap = new HashMap();
            hashMap.put(MiniDefine.ak, tidInfo.a());
            hashMap.put(GlobalDefine.l, GlobalContext.a().k());
            Object a4 = a(b2, hashMap);
            if (!TextUtils.isEmpty(a4)) {
                stringBuilder.append(";").append(a4);
            }
        }
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    public String b(Context context) {
        if (TextUtils.isEmpty(this.f)) {
            String g = DeviceInfo.g();
            String a = BaseHelper.a();
            String a2 = BaseHelper.a(context);
            this.f = " (" + g + ";" + a + ";" + a2 + ";" + ";" + BaseHelper.b(context) + ")" + "(sdk android)";
        }
        return this.f;
    }

    public void b(String str) {
        this.g = str;
    }

    public String e() {
        Context b = GlobalContext.a().b();
        SharedPreferences sharedPreferences = b.getSharedPreferences(a, 0);
        String string = sharedPreferences.getString(b, null);
        if (TextUtils.isEmpty(string)) {
            if (TextUtils.isEmpty(TidInfo.d().a())) {
                string = h();
            } else {
                string = DeviceInfo.a(b).c();
            }
            sharedPreferences.edit().putString(b, string).commit();
        }
        return string;
    }

    public String f() {
        Context b = GlobalContext.a().b();
        SharedPreferences sharedPreferences = b.getSharedPreferences(a, 0);
        String string = sharedPreferences.getString(c, null);
        if (TextUtils.isEmpty(string)) {
            if (TextUtils.isEmpty(TidInfo.d().a())) {
                Object k = GlobalContext.a().k();
                if (TextUtils.isEmpty(k)) {
                    string = h();
                } else {
                    string = k.substring(3, 18);
                }
            } else {
                string = DeviceInfo.a(b).b();
            }
            sharedPreferences.edit().putString(c, string).commit();
        }
        return string;
    }

    private String h() {
        return Long.toHexString(System.currentTimeMillis()) + (new Random().nextInt(9000) + Response.a);
    }

    public String c(Context context) {
        WifiInfo connectionInfo = ((WifiManager) context.getSystemService(ConfigConstant.JSON_SECTION_WIFI)).getConnectionInfo();
        if (connectionInfo != null) {
            return connectionInfo.getSSID();
        }
        return "-1";
    }

    public String d(Context context) {
        WifiInfo connectionInfo = ((WifiManager) context.getSystemService(ConfigConstant.JSON_SECTION_WIFI)).getConnectionInfo();
        if (connectionInfo != null) {
            return connectionInfo.getBSSID();
        }
        return "00";
    }

    public String a(Context context, HashMap hashMap) {
        return SecurityClientMobile.GetApdid(context, hashMap);
    }

    public String g() {
        return MspHttpClient.a;
    }
}
