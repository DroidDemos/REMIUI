package com.xiaomi.push.service;

import com.xiaomi.f.a.c.b;
import com.xiaomi.push.service.q.c;

class k extends b {
    int b;
    String c;
    m co;
    String e;
    final /* synthetic */ XMPushService eN;

    public k(XMPushService xMPushService, m mVar, int i, String str, String str2) {
        this.eN = xMPushService;
        super(9);
        this.co = null;
        this.co = mVar;
        this.b = i;
        this.c = str;
        this.e = str2;
    }

    public void a() {
        if (!(this.co.kJ == c.unbind || this.eN.MR == null)) {
            try {
                this.eN.MR.a(this.co.h, this.co.b);
            } catch (Exception e) {
                b.a((Throwable) e);
                this.eN.b(10, e);
            }
        }
        this.co.a(c.unbind, this.b, 0, this.e, this.c);
    }

    public String b() {
        return "unbind the channel. " + this.co.h + ", " + this.co.b;
    }
}
