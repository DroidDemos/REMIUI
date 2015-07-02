package com.xiaomi.push.service;

import com.xiaomi.f.a.c.b;
import com.xiaomi.push.service.q.c;

class i extends b {
    m co;
    final /* synthetic */ XMPushService cr;

    public i(XMPushService xMPushService, m mVar) {
        this.cr = xMPushService;
        super(4);
        this.co = null;
        this.co = mVar;
    }

    public void a() {
        try {
            this.co.a(c.unbind, 1, 16, null, null);
            this.cr.MR.a(this.co.h, this.co.b);
            this.co.a(c.binding, 1, 16, null, null);
            this.cr.MR.c(this.co);
        } catch (Exception e) {
            b.a((Throwable) e);
            this.cr.b(10, e);
        }
    }

    public String b() {
        return "bind the client. " + this.co.h + ", " + this.co.b;
    }
}
