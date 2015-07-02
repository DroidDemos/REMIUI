package com.xiaomi.push.service;

import com.xiaomi.d.c.h;
import com.xiaomi.f.a.c.b;

public class x extends b {
    private XMPushService cf;
    private h[] vw;

    public x(XMPushService xMPushService, h[] hVarArr) {
        super(4);
        this.cf = null;
        this.cf = xMPushService;
        this.vw = hVarArr;
    }

    public void a() {
        try {
            this.cf.a(this.vw);
        } catch (Exception e) {
            b.a((Throwable) e);
            this.cf.b(10, e);
        }
    }

    public String b() {
        return "batch send message.";
    }
}
