package com.xiaomi.push.service;

import android.text.TextUtils;
import com.alipay.mobilesecuritysdk.deviceID.Profile;
import com.alipay.sdk.cons.GlobalConstants;
import com.alipay.sdk.cons.GlobalDefine;
import com.alipay.sdk.cons.MiniDefine;
import com.xiaomi.c.i;
import com.xiaomi.d.B;
import com.xiaomi.d.b;
import com.xiaomi.d.c.g;
import com.xiaomi.d.c.h;
import com.xiaomi.d.c.j;
import com.xiaomi.d.r;
import com.xiaomi.d.s;
import com.xiaomi.d.x;
import com.xiaomi.push.service.q.c;

public class T {
    private XMPushService cf;

    T(XMPushService xMPushService) {
        this.cf = xMPushService;
    }

    private void a(j jVar) {
        Object c = jVar.c();
        if (!TextUtils.isEmpty(c)) {
            String[] split = c.split(";");
            i ba = com.xiaomi.c.j.en().ba(r.d());
            if (ba != null && split.length > 0) {
                ba.c(split);
                this.cf.b(20, null);
                this.cf.a(true);
            }
        }
    }

    public void a(g gVar) {
        m r;
        if (gVar instanceof b) {
            b bVar = (b) gVar;
            B eX = bVar.eX();
            String l = bVar.l();
            String m = bVar.m();
            if (!TextUtils.isEmpty(l)) {
                r = q.fp().r(l, m);
                if (r == null) {
                    return;
                }
                if (eX == B.vd) {
                    r.a(c.binded, 1, 0, null, null);
                    com.xiaomi.f.a.c.b.a("SMACK: channel bind succeeded, chid=" + l);
                    return;
                }
                com.xiaomi.d.c.c iX = bVar.iX();
                com.xiaomi.f.a.c.b.a("SMACK: channel bind failed, error=" + iX.d());
                if (iX != null) {
                    if ("auth".equals(iX.b())) {
                        r.a(c.unbind, 1, 5, iX.a(), iX.b());
                        q.fp().a(l, m);
                    } else if ("cancel".equals(iX.b())) {
                        r.a(c.unbind, 1, 7, iX.a(), iX.b());
                        q.fp().a(l, m);
                    } else if ("wait".equals(iX.b())) {
                        this.cf.d(r);
                        r.a(c.unbind, 1, 7, iX.a(), iX.b());
                    }
                    com.xiaomi.f.a.c.b.a("SMACK: channel bind failed, chid=" + l + " reason=" + iX.a());
                    return;
                }
                return;
            }
            return;
        }
        String l2 = gVar.l();
        if (TextUtils.isEmpty(l2)) {
            l2 = GlobalConstants.d;
        }
        if (!l2.equals(Profile.devicever)) {
            j cA;
            if (gVar instanceof com.xiaomi.d.c.i) {
                cA = gVar.cA("kick");
                if (cA != null) {
                    String m2 = gVar.m();
                    String a = cA.a(MiniDefine.m);
                    String a2 = cA.a("reason");
                    com.xiaomi.f.a.c.b.a("kicked by server, chid=" + l2 + " userid=" + m2 + " type=" + a + " reason=" + a2);
                    if ("wait".equals(a)) {
                        r = q.fp().r(l2, m2);
                        if (r != null) {
                            this.cf.d(r);
                            r.a(c.unbind, 3, 0, a2, a);
                            return;
                        }
                        return;
                    }
                    this.cf.a(l2, m2, 3, a2, a);
                    q.fp().a(l2, m2);
                    return;
                }
            } else if (gVar instanceof h) {
                h hVar = (h) gVar;
                if ("redir".equals(hVar.b())) {
                    cA = hVar.cA("hosts");
                    if (cA != null) {
                        a(cA);
                        return;
                    }
                    return;
                }
            }
            this.cf.jk().a(this.cf, l2, gVar);
        } else if ((gVar instanceof com.xiaomi.d.c.i) && Profile.devicever.equals(gVar.k()) && GlobalDefine.g.equals(((com.xiaomi.d.c.i) gVar).jg().toString())) {
            s jl = this.cf.jl();
            if (jl instanceof x) {
                ((x) jl).ie();
            }
        }
    }
}
