package com.xiaomi.push.service;

import android.content.Context;

public class A {
    public final String a;
    protected final String b;
    protected final String c;
    protected final String d;
    protected final String e;
    protected final String f;

    public A(String str, String str2, String str3, String str4, String str5, String str6) {
        this.a = str;
        this.b = str2;
        this.c = str3;
        this.d = str4;
        this.e = str5;
        this.f = str6;
    }

    private static boolean a(Context context) {
        return context.getPackageName().equals("com.xiaomi.xmsf");
    }

    public m a(XMPushService xMPushService) {
        m mVar = new m(xMPushService);
        mVar.a = xMPushService.getPackageName();
        mVar.b = this.a;
        mVar.i = this.c;
        mVar.c = this.b;
        mVar.h = "5";
        mVar.d = "XMPUSH-PASS";
        mVar.e = false;
        mVar.f = "sdk_ver:2";
        String str = a((Context) xMPushService) ? "1000271" : this.d;
        mVar.g = String.format("%1$s:%2$s,%3$s:%4$s", new Object[]{"dev_id", z.b(xMPushService), "appid", str});
        mVar.kH = xMPushService.jk();
        return mVar;
    }
}
