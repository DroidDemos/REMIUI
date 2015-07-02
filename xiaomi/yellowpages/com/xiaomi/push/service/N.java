package com.xiaomi.push.service;

import com.xiaomi.d.c.g;
import com.xiaomi.e.a.a;
import com.xiaomi.e.a.h;
import com.xiaomi.e.a.i;
import com.xiaomi.f.a.c.b;
import org.apache.thrift.TBase;

final class N extends b {
    final /* synthetic */ XMPushService cf;
    final /* synthetic */ h wh;

    N(int i, XMPushService xMPushService, h hVar) {
        this.cf = xMPushService;
        this.wh = hVar;
        super(i);
    }

    private h b(h hVar) {
        TBase iVar = new i();
        iVar.bH(hVar.e);
        iVar.bI("package uninstalled");
        iVar.bG(g.j());
        iVar.B(false);
        return M.a(hVar, iVar, a.Notification);
    }

    public void a() {
        try {
            this.cf.f(b(this.wh));
        } catch (Exception e) {
            b.a((Throwable) e);
            this.cf.b(10, e);
        }
    }

    public String b() {
        return "send app absent message.";
    }
}
