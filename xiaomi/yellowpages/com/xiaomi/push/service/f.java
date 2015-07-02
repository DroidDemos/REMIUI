package com.xiaomi.push.service;

import com.xiaomi.f.a.c.b;
import com.xiaomi.push.service.q.c;

class f extends b {
    m co;
    final /* synthetic */ XMPushService cr;

    public f(XMPushService xMPushService, m mVar) {
        this.cr = xMPushService;
        super(9);
        this.co = null;
        this.co = mVar;
    }

    public void a() {
        try {
            if (!this.cr.f()) {
                b.c("trying bind while the connection is not created, quit!");
            } else if (this.co.kJ == c.unbind) {
                this.co.a(c.binding, 0, 0, null, null);
                this.cr.MR.c(this.co);
            } else {
                b.a("trying duplicate bind, ingore! " + this.co.kJ);
            }
        } catch (Exception e) {
            b.a((Throwable) e);
            this.cr.b(10, e);
        }
    }

    public String b() {
        return "bind the client. " + this.co.h + ", " + this.co.b;
    }
}
