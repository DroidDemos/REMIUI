package com.xiaomi.push.service;

import com.xiaomi.f.a.c.b;

public class d extends b {
    final /* synthetic */ XMPushService cf;

    d(XMPushService xMPushService) {
        this.cf = xMPushService;
        super(1);
    }

    public void a() {
        if (this.cf.c()) {
            this.cf.ao();
        } else {
            b.a("should not connect. quit the job.");
        }
    }

    public String b() {
        return "do reconnect..";
    }
}
