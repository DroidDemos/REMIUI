package com.xiaomi.mipush.sdk;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.text.TextUtils;
import com.alipay.mobilesecuritysdk.deviceID.DeviceIdModel;
import com.xiaomi.f.a.c.b;
import com.xiaomi.push.service.z;

public class k {
    private static k vl;
    private Context b;
    private n vm;

    private k(Context context) {
        this.b = context;
        ao();
    }

    public static k U(Context context) {
        if (vl == null) {
            vl = new k(context);
        }
        return vl;
    }

    private void ao() {
        this.vm = new n();
        SharedPreferences eZ = eZ();
        this.vm.a = eZ.getString(DeviceIdModel.mAppId, null);
        this.vm.b = eZ.getString("appToken", null);
        this.vm.c = eZ.getString("regId", null);
        this.vm.d = eZ.getString("regSec", null);
        this.vm.f = eZ.getString("devId", null);
        if (!TextUtils.isEmpty(this.vm.f) && this.vm.f.startsWith("a-")) {
            this.vm.f = z.c(this.b);
            eZ.edit().putString("devId", this.vm.f).commit();
        }
        this.vm.e = eZ.getString("vName", null);
        this.vm.g = eZ.getBoolean("valid", true);
        this.vm.h = eZ.getBoolean("paused", false);
    }

    public static String u(Context context, String str) {
        PackageInfo packageInfo;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(str, 16384);
        } catch (Throwable e) {
            b.a(e);
            packageInfo = null;
        }
        return packageInfo != null ? packageInfo.versionName : "1.0";
    }

    public void a(boolean z) {
        this.vm.a(z);
        eZ().edit().putBoolean("paused", z).commit();
    }

    public boolean a() {
        if (this.vm.a()) {
            return true;
        }
        b.a("Don't send message before initialization succeeded!");
        return false;
    }

    public String b() {
        return this.vm.a;
    }

    public void b(String str, String str2) {
        this.vm.a(str, str2);
    }

    public String c() {
        return this.vm.b;
    }

    public void c(String str, String str2) {
        this.vm.b(str, str2);
    }

    public String d() {
        return this.vm.c;
    }

    public String e() {
        return this.vm.d;
    }

    public SharedPreferences eZ() {
        return this.b.getSharedPreferences("mipush", 0);
    }

    public void f() {
        this.vm.b();
    }

    public void fa() {
        this.vm.c();
    }

    public boolean g() {
        return this.vm.a();
    }

    public boolean j() {
        return this.vm.h;
    }

    public boolean k() {
        return !this.vm.g;
    }

    public boolean q(String str, String str2) {
        return this.vm.v(str, str2);
    }
}
