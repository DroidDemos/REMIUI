package com.xiaomi.push.service;

import com.xiaomi.f.a.c.b;

class h extends b {
    final /* synthetic */ XMPushService cf;

    public h(XMPushService xMPushService) {
        this.cf = xMPushService;
        super(4);
    }

    public void a() {
        if (this.cf.f()) {
            try {
                this.cf.MR.c();
            } catch (Exception e) {
                b.a((Throwable) e);
                this.cf.b(10, e);
            }
        }
    }

    public String b() {
        return "send ping..";
    }
}
