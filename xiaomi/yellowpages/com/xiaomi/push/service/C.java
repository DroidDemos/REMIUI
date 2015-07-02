package com.xiaomi.push.service;

import com.xiaomi.f.a.c.b;

class C extends b {
    final /* synthetic */ String a;
    final /* synthetic */ XMPushService ci;
    final /* synthetic */ byte[] vx;

    C(XMPushService xMPushService, int i, String str, byte[] bArr) {
        this.ci = xMPushService;
        this.a = str;
        this.vx = bArr;
        super(i);
    }

    public void a() {
        try {
            this.ci.a(this.a, this.vx);
        } catch (Exception e) {
            b.a((Throwable) e);
            this.ci.b(10, e);
        }
    }

    public String b() {
        return "send mi push message";
    }
}
