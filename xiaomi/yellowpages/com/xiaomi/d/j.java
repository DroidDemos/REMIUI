package com.xiaomi.d;

import com.alipay.mobilesecuritysdk.constant.ConfigConstant;
import com.xiaomi.b.a.H;
import com.xiaomi.b.a.R;
import com.xiaomi.b.a.d;
import com.xiaomi.d.c.c;
import com.xiaomi.d.c.f;
import com.xiaomi.d.c.g;
import com.xiaomi.f.a.c.b;
import com.xiaomi.push.service.XMPushService;
import com.xiaomi.push.service.m;
import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;
import java.net.URI;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class j extends s {
    private H FH;
    private final k FI;
    private boolean FJ;
    private boolean FK;
    private ExecutorService FL;
    private PipedWriter FM;
    private Thread FN;
    private Object FO;
    protected String a;
    protected String b;
    private boolean q;
    private boolean r;
    private boolean s;
    private String y;

    public j(XMPushService xMPushService, k kVar) {
        super(xMPushService, kVar);
        this.q = false;
        this.r = false;
        this.s = true;
        this.FJ = false;
        this.FK = false;
        this.a = null;
        this.b = null;
        this.y = null;
        this.FO = new Object();
        this.FI = kVar;
    }

    public void a() {
        if (j()) {
            b.c("SMACK-BOSH: Already connected to a server.");
            return;
        }
        long currentTimeMillis = System.currentTimeMillis();
        this.FK = false;
        this.b = null;
        this.a = null;
        try {
            a(0, 0, null);
            URI hv = this.FI.hv();
            b.b("SMACK-BOSH: connecting using uri:" + hv.toString());
            this.FH = H.a(R.a(hv, this.FI.e()).il(), this.Gf.getApplicationContext());
            this.FL = Executors.newSingleThreadExecutor(new m(this));
            this.FH.a(new e());
            this.FH.a(new h(this));
            if (this.FI.i()) {
                b();
                if (this.s) {
                    if (this.Gb.eC() != null) {
                        a(this.Gb.eC(), null);
                    }
                    if (this.Gb.eD() != null) {
                        b(this.Gb.eD(), null);
                    }
                }
            }
            this.FH.b(d.bX().b(com.xiaomi.b.a.j.j("xm", "version"), "102").ij());
            synchronized (this.FO) {
                if (!j()) {
                    try {
                        this.FO.wait((long) (z.ah() * 6));
                    } catch (InterruptedException e) {
                    }
                }
            }
            long currentTimeMillis2 = System.currentTimeMillis() - currentTimeMillis;
            if (j()) {
                this.FI.hu().a(this.FI.b(), currentTimeMillis2, 0);
                return;
            }
            this.FK = true;
            String str = "Timeout reached for the connection to " + this.FI.b() + ":" + g() + ".";
            this.FI.hu().a(this.FI.b(), currentTimeMillis2, 0, null);
            throw new v(str, new c(com.xiaomi.d.c.b.Fz, str));
        } catch (Throwable e2) {
            throw new v("Can't connect to " + d(), e2);
        }
    }

    public void a(f fVar, int i, Exception exception) {
        if (hy() != 2) {
            b(fVar, i, exception);
            this.yk.clear();
            this.ev.clear();
            this.FJ = false;
            this.s = true;
        }
    }

    public void a(g gVar) {
        if (this.FK) {
            throw new v("try send packet while the connection is done.");
        }
        try {
            b(d.bX().cl(gVar.a()).ij());
            d(gVar);
        } catch (Throwable e) {
            throw new v(e);
        }
    }

    protected void a(Exception exception) {
        b(new f(f.b.unavailable), 3, exception);
        b.e(ConfigConstant.WIRELESS_FILENAME);
        for (u a : hw()) {
            try {
                a.a(0, exception);
            } catch (Exception e) {
                b.c("SMACK-BOSH: notify connection closed error" + e);
            }
        }
    }

    public void a(String str, String str2) {
        synchronized (this) {
            g fVar = new f(f.b.unavailable);
            fVar.cx(str);
            fVar.cz(str2);
            a(fVar);
        }
    }

    public void a(g[] gVarArr) {
        int i = 0;
        if (this.FK) {
            throw new v("try send packet while connection is done.");
        }
        int length;
        StringBuilder stringBuilder = new StringBuilder();
        for (g gVar : gVarArr) {
            if (gVar == null) {
                throw new NullPointerException("Packet is null.");
            }
            stringBuilder.append(gVar.a());
        }
        try {
            b(d.bX().cl(stringBuilder.toString()).ij());
            length = gVarArr.length;
            while (i < length) {
                d(gVarArr[i]);
                i++;
            }
        } catch (Throwable e) {
            throw new v(e);
        }
    }

    protected void b() {
        this.Gd = new l(this);
        try {
            this.FM = new PipedWriter();
            this.Gc = new PipedReader(this.FM);
        } catch (IOException e) {
        }
        super.b();
        this.FH.a(new g(this));
        this.FH.a(new f(this));
        this.FN = new i(this);
        this.FN.setDaemon(true);
        this.FN.start();
    }

    protected void b(d dVar) {
        if (!j()) {
            throw new IllegalStateException("Not connected to a server!");
        } else if (dVar == null) {
            throw new NullPointerException("Body mustn't be null!");
        } else {
            if (this.b != null) {
                dVar = dVar.bZ().b(com.xiaomi.b.a.j.j("xm", "sid"), this.b).ij();
            }
            this.FH.b(dVar);
        }
    }

    protected void b(f fVar, int i, Exception exception) {
        this.a = null;
        this.b = null;
        this.FK = true;
        this.q = false;
        a(2, i, exception);
        this.s = false;
        this.k = ConfigConstant.WIRELESS_FILENAME;
        try {
            this.FH.c(d.bX().w("xmpp", "xm").ij());
            Thread.sleep(150);
        } catch (Exception e) {
        }
        if (this.FH != null) {
            this.FH.a();
            this.FH = null;
        }
        if (this.FM != null) {
            try {
                this.FM.close();
            } catch (Throwable th) {
            }
            this.Gc = null;
        }
        if (this.Gc != null) {
            try {
                this.Gc.close();
            } catch (Throwable th2) {
            }
            this.Gc = null;
        }
        if (this.Gd != null) {
            try {
                this.Gd.close();
            } catch (Throwable th3) {
            }
            this.Gd = null;
        }
        if (this.FL != null) {
            this.FL.shutdown();
        }
        for (u a : hw()) {
            try {
                a.a(i, exception);
            } catch (Throwable e2) {
                b.b("SMACK-BOSH: Error while shut down connection", e2);
            }
        }
        this.FN = null;
    }

    public void c() {
        if (j()) {
            b.b("SMACK-BOSH: scheduling empty request for ping");
            this.FH.b();
        }
    }

    protected void c(g gVar) {
        if (gVar != null) {
            this.FL.submit(new d(this, gVar));
        }
    }

    public void c(m mVar) {
        synchronized (this) {
            new y().a(mVar, this.k, this);
        }
    }
}
