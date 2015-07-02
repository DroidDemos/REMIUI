package com.xiaomi.push.service;

import com.xiaomi.d.c.f;
import com.xiaomi.d.c.f.b;

class o extends b {
    final /* synthetic */ XMPushService cf;

    o(XMPushService xMPushService, int i) {
        this.cf = xMPushService;
        super(i);
    }

    public void a() {
        if (this.cf.MR != null) {
            this.cf.MR.a(new f(b.unavailable), 15, null);
            this.cf.MR = null;
        }
    }

    public String b() {
        return "disconnect for service destroy.";
    }
}
