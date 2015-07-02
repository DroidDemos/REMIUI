package com.xiaomi.mipush.sdk;

import android.content.SharedPreferences.Editor;
import android.text.TextUtils;
import com.alipay.mobilesecuritysdk.deviceID.DeviceIdModel;
import com.xiaomi.push.service.z;

class n {
    final /* synthetic */ k Hk;
    public String a;
    public String b;
    public String c;
    public String d;
    public String e;
    public String f;
    public boolean g;
    public boolean h;

    private n(k kVar) {
        this.Hk = kVar;
        this.g = true;
        this.h = false;
    }

    private String d() {
        return k.u(this.Hk.b, this.Hk.b.getPackageName());
    }

    public void a(String str, String str2) {
        this.a = str;
        this.b = str2;
        Editor edit = this.Hk.eZ().edit();
        edit.putString(DeviceIdModel.mAppId, this.a);
        edit.putString("appToken", str2);
        edit.commit();
    }

    public void a(boolean z) {
        this.h = z;
    }

    public boolean a() {
        return v(this.a, this.b);
    }

    public void b() {
        this.Hk.eZ().edit().clear().commit();
        this.a = null;
        this.b = null;
        this.c = null;
        this.d = null;
        this.f = null;
        this.e = null;
        this.g = false;
        this.h = false;
    }

    public void b(String str, String str2) {
        this.c = str;
        this.d = str2;
        this.f = z.c(this.Hk.b);
        this.e = d();
        this.g = true;
        Editor edit = this.Hk.eZ().edit();
        edit.putString("regId", str);
        edit.putString("regSec", str2);
        edit.putString("devId", this.f);
        edit.putString("vName", d());
        edit.putBoolean("valid", true);
        edit.commit();
    }

    public void c() {
        this.g = false;
        this.Hk.eZ().edit().putBoolean("valid", this.g).commit();
    }

    public boolean v(String str, String str2) {
        return TextUtils.equals(this.a, str) && TextUtils.equals(this.b, str2) && !TextUtils.isEmpty(this.c) && !TextUtils.isEmpty(this.d) && TextUtils.equals(this.e, d()) && TextUtils.equals(this.f, z.c(this.Hk.b));
    }
}
