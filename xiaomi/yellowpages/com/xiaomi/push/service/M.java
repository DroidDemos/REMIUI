package com.xiaomi.push.service;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.xiaomi.d.c.g;
import com.xiaomi.d.c.j;
import com.xiaomi.e.a.a;
import com.xiaomi.e.a.c;
import com.xiaomi.e.a.d;
import com.xiaomi.e.a.h;
import com.xiaomi.e.a.u;
import com.xiaomi.f.a.c.b;
import java.nio.ByteBuffer;
import java.util.List;
import org.apache.thrift.TBase;

public class M {
    protected static h a(h hVar, TBase tBase, a aVar) {
        byte[] a = u.a(tBase);
        h hVar2 = new h();
        d dVar = new d();
        dVar.a = 5;
        dVar.b = "fakeid";
        hVar2.a(dVar);
        hVar2.g(ByteBuffer.wrap(a));
        hVar2.b(aVar);
        hVar2.A(true);
        hVar2.bF(hVar.j());
        hVar2.z(false);
        hVar2.bE(hVar.h());
        return hVar2;
    }

    private static void a(XMPushService xMPushService, h hVar) {
        xMPushService.a(new O(4, hVar, hVar.gp(), xMPushService));
    }

    private static void a(XMPushService xMPushService, byte[] bArr) {
        h hVar = new h();
        try {
            u.a(hVar, bArr);
            if (TextUtils.isEmpty(hVar.f)) {
                b.a("receive a mipush message without package name");
                return;
            }
            Intent intent = new Intent("com.xiaomi.mipush.RECEIVE_MESSAGE");
            intent.putExtra("mipush_payload", bArr);
            intent.setPackage(hVar.f);
            if (a(xMPushService, intent)) {
                c gp = hVar.gp();
                if (gp == null || !Q.a(xMPushService, hVar.f, gp.a())) {
                    if (gp == null || TextUtils.isEmpty(gp.g()) || TextUtils.isEmpty(gp.i()) || gp.h == 1 || P.t(xMPushService, hVar.f)) {
                        xMPushService.sendBroadcast(intent, w.a(hVar.f));
                    } else {
                        P.a(xMPushService, hVar, bArr);
                        a(xMPushService, hVar);
                    }
                    if (hVar.gn() == a.UnRegistration && !"com.xiaomi.xmsf".equals(xMPushService.getPackageName())) {
                        xMPushService.stopSelf();
                        return;
                    }
                    return;
                }
                b.a("drop a duplicate message, msgid=" + gp.a());
                a(xMPushService, hVar);
                return;
            }
            xMPushService.a(new N(4, xMPushService, hVar));
        } catch (Throwable e) {
            b.a(e);
        }
    }

    private static boolean a(XMPushService xMPushService, Intent intent) {
        try {
            List queryBroadcastReceivers = xMPushService.getPackageManager().queryBroadcastReceivers(intent, 32);
            return (queryBroadcastReceivers == null || queryBroadcastReceivers.isEmpty()) ? false : true;
        } catch (Exception e) {
            return true;
        }
    }

    public void a(Context context, m mVar, boolean z, int i, String str) {
        if (!z) {
            A V = z.V(context);
            if (V != null && "token-expired".equals(str)) {
                try {
                    z.b(context, V.d, V.e, V.f);
                } catch (Throwable e) {
                    b.a(e);
                } catch (Throwable e2) {
                    b.a(e2);
                }
            }
        }
    }

    public void a(XMPushService xMPushService, g gVar, m mVar) {
        if (gVar instanceof com.xiaomi.d.c.h) {
            com.xiaomi.d.c.h hVar = (com.xiaomi.d.c.h) gVar;
            j cA = hVar.cA("s");
            if (cA != null) {
                try {
                    a(xMPushService, G.b(G.s(mVar.i, hVar.k()), cA.c()));
                    return;
                } catch (Throwable e) {
                    b.a(e);
                    return;
                }
            }
            return;
        }
        b.a("not a mipush message");
    }
}
