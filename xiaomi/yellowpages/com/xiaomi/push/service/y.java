package com.xiaomi.push.service;

import com.xiaomi.f.a.c.b;
import com.xiaomi.push.service.q.c;
import java.util.Collection;

public class y extends b {
    private String c;
    private XMPushService cf;
    private String e;
    private String f;
    private byte[] vx;

    public y(XMPushService xMPushService, String str, String str2, String str3, byte[] bArr) {
        super(9);
        this.cf = xMPushService;
        this.c = str;
        this.vx = bArr;
        this.e = str2;
        this.f = str3;
    }

    public void a() {
        A b;
        Collection bo;
        m mVar;
        A V = z.V(this.cf);
        if (V == null) {
            try {
                b = z.b(this.cf, this.c, this.e, this.f);
            } catch (Throwable e) {
                b.a(e);
                b = V;
            } catch (Throwable e2) {
                b.a(e2);
            }
            if (b != null) {
                b.c("no account for mipush");
                L.a(this.cf, 70000002, "no account.");
            }
            bo = q.fp().bo("5");
            if (bo.isEmpty()) {
                mVar = (m) bo.iterator().next();
            } else {
                mVar = b.a(this.cf);
                this.cf.c(mVar);
                q.fp().c(mVar);
            }
            if (this.cf.f()) {
                this.cf.a(true);
                return;
            }
            try {
                if (mVar.kJ == c.binded) {
                    this.cf.a(this.c, this.vx);
                    return;
                } else if (mVar.kJ == c.unbind) {
                    XMPushService xMPushService = this.cf;
                    XMPushService xMPushService2 = this.cf;
                    xMPushService2.getClass();
                    xMPushService.a(new f(xMPushService2, mVar));
                    return;
                } else {
                    return;
                }
            } catch (Exception e3) {
                b.a((Throwable) e3);
                this.cf.b(10, e3);
                return;
            }
        }
        b = V;
        if (b != null) {
            bo = q.fp().bo("5");
            if (bo.isEmpty()) {
                mVar = (m) bo.iterator().next();
            } else {
                mVar = b.a(this.cf);
                this.cf.c(mVar);
                q.fp().c(mVar);
            }
            if (this.cf.f()) {
                this.cf.a(true);
                return;
            } else if (mVar.kJ == c.binded) {
                this.cf.a(this.c, this.vx);
                return;
            } else if (mVar.kJ == c.unbind) {
                XMPushService xMPushService3 = this.cf;
                XMPushService xMPushService22 = this.cf;
                xMPushService22.getClass();
                xMPushService3.a(new f(xMPushService22, mVar));
                return;
            } else {
                return;
            }
        }
        b.c("no account for mipush");
        L.a(this.cf, 70000002, "no account.");
    }

    public String b() {
        return "register app";
    }
}
