package com.xiaomi.push.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcelable;
import android.text.TextUtils;
import com.alipay.mobilesecuritysdk.deviceID.Profile;
import com.alipay.sdk.data.Response;
import com.xiaomi.b.a.H;
import com.xiaomi.c.i;
import com.xiaomi.d.c.f;
import com.xiaomi.d.c.g;
import com.xiaomi.d.c.h;
import com.xiaomi.d.j;
import com.xiaomi.d.k;
import com.xiaomi.d.o;
import com.xiaomi.d.r;
import com.xiaomi.d.s;
import com.xiaomi.d.u;
import com.xiaomi.d.v;
import com.xiaomi.d.x;
import com.xiaomi.f.a.c.b;
import com.xiaomi.push.service.a.a;
import com.xiaomi.push.service.q.c;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class XMPushService extends Service implements u {
    public static int a;
    private r MM;
    private k MN;
    private H MO;
    private x MP;
    private j MQ;
    private s MR;
    private w MS;
    private T MT;
    private a MU;
    private u MV;
    private o MW;

    static {
        com.xiaomi.c.j.p("app.chat.xiaomi.net", "58.68.235.14");
        com.xiaomi.c.j.p("app.chat.xiaomi.net", "app01.nodes.gslb.mi-idc.com");
        com.xiaomi.c.j.p("app.chat.xiaomi.net", "59.151.110.251");
        com.xiaomi.c.j.p("app.chat.xiaomi.net", "120.132.153.233");
        com.xiaomi.c.j.p("app.chat.xiaomi.net", "223.202.255.20");
        com.xiaomi.c.j.p("app.chat.xiaomi.net", "app02.nodes.gslb.mi-idc.com");
        x.c = true;
        if (com.xiaomi.f.a.b.a.b || com.xiaomi.f.a.b.a.e || com.xiaomi.f.a.b.a.c || com.xiaomi.f.a.b.a.g) {
            b.a(0);
        }
        a = 1;
    }

    public XMPushService() {
        this.MT = null;
        this.MU = null;
        this.MV = null;
        this.MW = new J(this);
    }

    private g a(g gVar, String str, String str2, boolean z) {
        q fp = q.fp();
        List bn = fp.bn(str);
        if (bn.isEmpty()) {
            b.a("open channel should be called first before sending a packet, pkg=" + str);
        } else {
            String l = gVar.l();
            if (TextUtils.isEmpty(l)) {
                l = (String) bn.get(0);
                gVar.cx(l);
            }
            m r = fp.r(l, gVar.iW());
            if (!f()) {
                b.a("drop a packet as the channel is not connected, chid=" + l);
            } else if (r == null || r.kJ != c.binded) {
                b.a("drop a packet as the channel is not opened, chid=" + l);
            } else if (TextUtils.equals(str2, r.j)) {
                return ((gVar instanceof h) && z) ? a((h) gVar, r.i) : gVar;
            } else {
                b.a("invalid session. " + str2);
            }
        }
        return null;
    }

    private h a(h hVar, String str) {
        byte[] s = G.s(str, hVar.k());
        h hVar2 = new h();
        hVar2.cz(hVar.iW());
        hVar2.cy(hVar.m());
        hVar2.cw(hVar.k());
        hVar2.cx(hVar.l());
        hVar2.b(true);
        String a = G.a(s, com.xiaomi.d.e.g.c(hVar.a()));
        com.xiaomi.d.c.j jVar = new com.xiaomi.d.c.j("s", null, (String[]) null, (String[]) null);
        jVar.b(a);
        hVar2.a(jVar);
        return hVar2;
    }

    private String a(String str) {
        return "<iq to='" + str + "' id='0' chid='0' type='get'><ping xmlns='urn:xmpp:ping'>%1$s</ping></iq>";
    }

    private void ao() {
        if (this.MR != null && this.MR.i()) {
            b.c("try to connect while connecting.");
        } else if (this.MR == null || !this.MR.j()) {
            this.MM.b(com.xiaomi.f.a.d.a.f(this));
            if (this.MP.gu()) {
                bu();
                if (this.MR == null || this.MR.hy() == 2) {
                    cG();
                }
            } else {
                cG();
                if (this.MR == null || this.MR.hy() == 2) {
                    bu();
                }
            }
            if (this.MR == null) {
                R.a();
                q.fp().a((Context) this);
            }
        } else {
            b.c("try to connect while is connected.");
        }
    }

    private m b(String str, Intent intent) {
        m r = q.fp().r(str, intent.getStringExtra(F.l));
        if (r == null) {
            r = new m(this);
        }
        r.h = intent.getStringExtra(F.m);
        r.b = intent.getStringExtra(F.l);
        r.c = intent.getStringExtra(F.o);
        r.a = intent.getStringExtra(F.u);
        r.f = intent.getStringExtra(F.s);
        r.g = intent.getStringExtra(F.t);
        r.e = intent.getBooleanExtra(F.r, false);
        r.i = intent.getStringExtra(F.q);
        r.d = intent.getStringExtra(F.p);
        r.kH = this.MS;
        r.kI = getApplicationContext();
        q.fp().c(r);
        return r;
    }

    private void bu() {
        try {
            i ba = com.xiaomi.c.j.en().ba("mibind.chat.gslb.mi-idc.com");
            if (ba != null) {
                this.MN.c(ba);
            }
            this.MQ.a();
            this.MQ.a(this.MW, new s(this));
            this.MR = this.MQ;
        } catch (Throwable e) {
            b.b("fail to create BOSH connection", e);
            this.MQ.a(new f(f.b.unavailable), 3, e);
        }
    }

    private void cF() {
        if (!c()) {
            this.MU.a();
        } else if (!this.MU.b()) {
            this.MU.a(true);
        }
    }

    private void cG() {
        try {
            this.MP.ia();
            this.MP.a(this.MW, new p(this));
            this.MR = this.MP;
        } catch (Exception e) {
            b.b("fail to create xmpp connection", e);
            this.MP.a(new f(f.b.unavailable), 3, e);
        }
    }

    private void d(String str, int i) {
        Collection<m> bo = q.fp().bo(str);
        if (bo != null) {
            for (m mVar : bo) {
                if (mVar != null) {
                    a(new k(this, mVar, i, null, null));
                }
            }
        }
        q.fp().a(str);
    }

    private void j() {
        if (z.V(getApplicationContext()) != null) {
            m a = z.V(getApplicationContext()).a(this);
            c(a);
            q.fp().c(a);
            if (com.xiaomi.f.a.d.a.Z(getApplicationContext())) {
                a(true);
            }
        }
    }

    public boolean I(int i) {
        return this.MV.I(i);
    }

    public void a() {
        this.MO.a();
        Iterator it = q.fp().b().iterator();
        while (it.hasNext()) {
            a(new f(this, (m) it.next()));
        }
    }

    public void a(int i) {
        this.MV.a(i);
    }

    public void a(int i, Exception exception) {
        a(false);
    }

    public void a(g gVar) {
        if (this.MR != null) {
            this.MR.a(gVar);
            return;
        }
        throw new v("try send msg while connection is null.");
    }

    public void a(b bVar) {
        a(bVar, 0);
    }

    public void a(b bVar, long j) {
        this.MV.a(bVar, j);
    }

    public void a(Exception exception) {
        a(false);
    }

    public void a(String str, String str2, int i, String str3, String str4) {
        m r = q.fp().r(str, str2);
        if (r != null) {
            a(new k(this, r, i, str4, str3));
        }
        q.fp().a(str, str2);
    }

    public void a(String str, byte[] bArr) {
        if (this.MR != null) {
            g m = m(bArr);
            if (m != null) {
                this.MR.a(m);
                return;
            } else {
                L.a(this, str, bArr, 70000003, "not a valid message");
                return;
            }
        }
        throw new v("try send msg while connection is null.");
    }

    public void a(boolean z) {
        this.MO.a(z);
    }

    public void a(g[] gVarArr) {
        if (this.MR != null) {
            this.MR.a(gVarArr);
            return;
        }
        throw new v("try send msg while connection is null.");
    }

    public x b(r rVar) {
        return new x(this, rVar);
    }

    public void b() {
        b.b("begin to connect...");
    }

    public void b(int i, Exception exception) {
        b.a("disconnect " + hashCode() + ", " + (this.MR == null ? null : Integer.valueOf(this.MR.hashCode())));
        if (this.MR != null) {
            this.MR.a(new f(f.b.unavailable), i, exception);
            this.MR = null;
        }
        a(7);
        a(4);
        q.fp().e(this, i);
    }

    public void b(b bVar) {
        this.MV.a(bVar.d, (Object) bVar);
    }

    public void c(m mVar) {
        mVar.a(new r(this));
    }

    public boolean c() {
        return com.xiaomi.f.a.d.a.Z(this) && q.fp().c() > 0;
    }

    public void d(m mVar) {
        if (mVar != null) {
            long a = mVar.a();
            b.a("schedule rebind job in " + (a / 1000));
            a(new f(this, mVar), a);
        }
    }

    public h e(com.xiaomi.e.a.h hVar) {
        try {
            h hVar2 = new h();
            hVar2.cx("5");
            hVar2.cy("xiaomi.com");
            hVar2.cz(z.V(this).a);
            hVar2.b(true);
            hVar2.f("push");
            String str = z.V(this).a;
            hVar.g.b = str.substring(0, str.indexOf("@"));
            hVar.g.d = str.substring(str.indexOf("/") + 1);
            String valueOf = String.valueOf(com.xiaomi.f.a.a.a.d(G.c(G.s(z.V(this).c, hVar2.k()), com.xiaomi.e.a.u.a(hVar))));
            com.xiaomi.d.c.j jVar = new com.xiaomi.d.c.j("s", null, (String[]) null, (String[]) null);
            jVar.b(valueOf);
            hVar2.a(jVar);
            b.a("try send mi push message. packagename:" + hVar.f + " action:" + hVar.a);
            return hVar2;
        } catch (Throwable e) {
            b.a(e);
            return null;
        }
    }

    public void f(com.xiaomi.e.a.h hVar) {
        if (this.MR != null) {
            g e = e(hVar);
            if (e != null) {
                this.MR.a(e);
                return;
            }
            return;
        }
        throw new v("try send msg while connection is null.");
    }

    public boolean f() {
        return this.MR != null && this.MR.j();
    }

    public void fa() {
        a(new t(this, 10), 120000);
    }

    public boolean g() {
        return this.MR != null && this.MR.i();
    }

    public w jj() {
        return new w();
    }

    public w jk() {
        return this.MS;
    }

    public s jl() {
        return this.MR;
    }

    public h m(byte[] bArr) {
        com.xiaomi.e.a.h hVar = new com.xiaomi.e.a.h();
        try {
            com.xiaomi.e.a.u.a(hVar, bArr);
            return e(hVar);
        } catch (Throwable e) {
            b.a(e);
            return null;
        }
    }

    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onCreate() {
        super.onCreate();
        com.xiaomi.c.j.a(this, null, null, Profile.devicever, "push", "2.1");
        this.MM = new r(null, 5222, "xiaomi.com", null);
        this.MM.a(true);
        this.MP = b(this.MM);
        this.MP.b(a("xiaomi.com"));
        this.MN = new k(false, new i("mibind.chat.gslb.mi-idc.com"), 80, "mibind/http-bind", "xiaomi.com", null);
        System.setProperty(H.class.getName() + ".emptyRequestDelay", String.valueOf(Response.a));
        this.MQ = new j(this, this.MN);
        this.MS = jj();
        this.MS.a((Context) this);
        this.MU = new a(this);
        this.MP.a((u) this);
        this.MQ.a((u) this);
        this.MT = new T(this);
        this.MO = new H(this);
        new v().a();
        this.MV = new u("Connection Controller Thread");
        this.MV.start();
        a(new K(this, 11));
        q fp = q.fp();
        fp.e();
        fp.a(new D(this));
    }

    public void onDestroy() {
        this.MV.a();
        a(new o(this, 2));
        a(new a(this));
        q.fp().e();
        q.fp().e(this, 15);
        q.fp().d();
        this.MP.b(this);
        this.MQ.b(this);
        this.MU.a();
        super.onDestroy();
        b.a("Service destroyed");
    }

    public void onStart(Intent intent, int i) {
        m mVar = null;
        int i2 = 0;
        if (intent == null) {
            b.c("onStart() with intent NULL");
        } else {
            b.b("onStart() with intent.Action = " + intent.getAction());
        }
        q fp = q.fp();
        if (intent != null && intent.getAction() != null) {
            String stringExtra;
            if (F.a.equalsIgnoreCase(intent.getAction()) || F.g.equalsIgnoreCase(intent.getAction())) {
                stringExtra = intent.getStringExtra(F.m);
                Object stringExtra2 = intent.getStringExtra(F.v);
                if (stringExtra != null) {
                    m b = b(stringExtra, intent);
                    boolean z = (TextUtils.isEmpty(b.j) || TextUtils.equals(stringExtra2, b.j)) ? false : true;
                    b.j = stringExtra2;
                    if (!com.xiaomi.f.a.d.a.Z(this)) {
                        this.MS.a(this, b, false, 2, null);
                        return;
                    } else if (!f()) {
                        a(true);
                        return;
                    } else if (z) {
                        a(new i(this, b));
                        return;
                    } else if (b.kJ == c.binding) {
                        b.a(String.format("the client is binding. %1$s %2$s.", new Object[]{b.h, b.b}));
                        return;
                    } else if (b.kJ == c.binded) {
                        this.MS.a(this, b, true, 0, null);
                        return;
                    } else {
                        a(new f(this, b));
                        return;
                    }
                }
                b.c("channel id is empty, do nothing!");
            } else if (F.f.equalsIgnoreCase(intent.getAction())) {
                stringExtra = intent.getStringExtra(F.u);
                r2 = intent.getStringExtra(F.m);
                Object stringExtra3 = intent.getStringExtra(F.l);
                if (TextUtils.isEmpty(r2)) {
                    for (String stringExtra4 : fp.bn(stringExtra4)) {
                        d(stringExtra4, 2);
                    }
                } else if (TextUtils.isEmpty(stringExtra3)) {
                    d(r2, 2);
                } else {
                    a(r2, stringExtra3, 2, null, null);
                }
            } else if (F.b.equalsIgnoreCase(intent.getAction())) {
                g a = a(new h(intent.getBundleExtra("ext_packet")), intent.getStringExtra(F.u), intent.getStringExtra(F.v), intent.getBooleanExtra("ext_encrypt", true));
                if (a != null) {
                    a(new I(this, a));
                }
            } else if (F.d.equalsIgnoreCase(intent.getAction())) {
                r1 = intent.getStringExtra(F.u);
                r2 = intent.getStringExtra(F.v);
                Parcelable[] parcelableArrayExtra = intent.getParcelableArrayExtra("ext_packets");
                h[] hVarArr = new h[parcelableArrayExtra.length];
                boolean booleanExtra = intent.getBooleanExtra("ext_encrypt", true);
                while (i2 < parcelableArrayExtra.length) {
                    hVarArr[i2] = new h((Bundle) parcelableArrayExtra[i2]);
                    hVarArr[i2] = (h) a(hVarArr[i2], r1, r2, booleanExtra);
                    if (hVarArr[i2] != null) {
                        i2++;
                    } else {
                        return;
                    }
                }
                a(new x(this, hVarArr));
            } else if (F.c.equalsIgnoreCase(intent.getAction())) {
                stringExtra4 = intent.getStringExtra(F.u);
                r1 = intent.getStringExtra(F.v);
                r2 = new com.xiaomi.d.c.i(intent.getBundleExtra("ext_packet"));
                if (a(r2, stringExtra4, r1, false) != null) {
                    a(new I(this, r2));
                }
            } else if (F.e.equalsIgnoreCase(intent.getAction())) {
                stringExtra4 = intent.getStringExtra(F.u);
                r1 = intent.getStringExtra(F.v);
                r2 = new f(intent.getBundleExtra("ext_packet"));
                if (a(r2, stringExtra4, r1, false) != null) {
                    a(new I(this, r2));
                }
            } else if ("com.xiaomi.push.timer".equalsIgnoreCase(intent.getAction())) {
                b.a("Service called on timer");
                if (this.MV.b()) {
                    b.c("ERROR, the job controller is blocked.");
                    q.fp().e(this, 14);
                    stopSelf();
                } else if (!f()) {
                    a(false);
                } else if (this.MR.bx()) {
                    a(new h(this));
                } else {
                    a(new c(this, 17, null));
                }
            } else if ("com.xiaomi.push.network_status_changed".equalsIgnoreCase(intent.getAction())) {
                NetworkInfo activeNetworkInfo;
                try {
                    activeNetworkInfo = ((ConnectivityManager) getSystemService("connectivity")).getActiveNetworkInfo();
                } catch (Throwable e) {
                    b.a(e);
                    activeNetworkInfo = null;
                }
                if (activeNetworkInfo != null) {
                    b.a("network changed, " + activeNetworkInfo.toString());
                } else {
                    b.a("network changed, no active network");
                }
                this.MP.gv();
                this.MQ.gv();
                if (!com.xiaomi.f.a.d.a.Z(this)) {
                    a(new c(this, 2, null));
                } else if (!(f() || g())) {
                    this.MV.a(1);
                    a(new d(this));
                }
                cF();
            } else if (F.h.equals(intent.getAction())) {
                stringExtra4 = intent.getStringExtra(F.m);
                if (stringExtra4 != null) {
                    b(stringExtra4, intent).j = intent.getStringExtra(F.v);
                }
                a(new j(this));
            } else if (F.i.equals(intent.getAction())) {
                stringExtra4 = intent.getStringExtra(F.u);
                List bn = fp.bn(stringExtra4);
                if (bn.isEmpty()) {
                    b.a("open channel should be called first before update info, pkg=" + stringExtra4);
                    return;
                }
                stringExtra4 = intent.getStringExtra(F.m);
                Object stringExtra5 = intent.getStringExtra(F.l);
                if (TextUtils.isEmpty(stringExtra4)) {
                    stringExtra4 = (String) bn.get(0);
                }
                if (TextUtils.isEmpty(stringExtra5)) {
                    r0 = fp.bo(stringExtra4);
                    if (!(r0 == null || r0.isEmpty())) {
                        mVar = (m) r0.iterator().next();
                    }
                } else {
                    mVar = fp.r(stringExtra4, stringExtra5);
                }
                if (mVar != null) {
                    if (intent.hasExtra(F.s)) {
                        mVar.f = intent.getStringExtra(F.s);
                    }
                    if (intent.hasExtra(F.t)) {
                        mVar.g = intent.getStringExtra(F.t);
                    }
                }
            } else if ("com.xiaomi.mipush.REGISTER_APP".equals(intent.getAction())) {
                byte[] byteArrayExtra = intent.getByteArrayExtra("mipush_payload");
                String stringExtra6 = intent.getStringExtra("mipush_app_package");
                if (byteArrayExtra == null) {
                    L.a(this, stringExtra6, byteArrayExtra, 70000003, "null payload");
                    b.a("register request without payload");
                    return;
                }
                com.xiaomi.e.a.h hVar = new com.xiaomi.e.a.h();
                try {
                    com.xiaomi.e.a.u.a(hVar, byteArrayExtra);
                    if (hVar.a == com.xiaomi.e.a.a.Registration) {
                        com.xiaomi.e.a.j jVar = new com.xiaomi.e.a.j();
                        try {
                            com.xiaomi.e.a.u.a(jVar, hVar.go());
                            L.a(hVar.j(), byteArrayExtra);
                            a(new y(this, hVar.j(), jVar.d(), jVar.h(), byteArrayExtra));
                            return;
                        } catch (Throwable e2) {
                            b.a(e2);
                            L.a(this, stringExtra6, byteArrayExtra, 70000003, " data action error.");
                            return;
                        }
                    }
                    L.a(this, stringExtra6, byteArrayExtra, 70000003, " registration action required.");
                    b.a("register request with invalid payload");
                } catch (Throwable e22) {
                    b.a(e22);
                    L.a(this, stringExtra6, byteArrayExtra, 70000003, " data container error.");
                }
            } else if ("com.xiaomi.mipush.SEND_MESSAGE".equals(intent.getAction()) || "com.xiaomi.mipush.UNREGISTER_APP".equals(intent.getAction())) {
                r1 = intent.getStringExtra("mipush_app_package");
                byte[] byteArrayExtra2 = intent.getByteArrayExtra("mipush_payload");
                r0 = q.fp().bo("5");
                if (r0.isEmpty()) {
                    L.b(r1, byteArrayExtra2);
                } else if (((m) r0.iterator().next()).kJ != c.binded) {
                    L.b(r1, byteArrayExtra2);
                } else {
                    a(new C(this, 4, r1, byteArrayExtra2));
                }
            }
        }
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        onStart(intent, i2);
        return a;
    }
}
