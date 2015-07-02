package com.xiaomi.push.service;

import com.xiaomi.d.c.g;
import com.xiaomi.f.a.c.b;

public class I extends b {
    private XMPushService cf;
    private g eM;

    public I(XMPushService xMPushService, g gVar) {
        super(4);
        this.cf = null;
        this.cf = xMPushService;
        this.eM = gVar;
    }

    public void a() {
        try {
            this.cf.a(this.eM);
        } catch (Exception e) {
            b.a((Throwable) e);
            this.cf.b(10, e);
        }
    }

    public String b() {
        return "send a message.";
    }
}
