package com.xiaomi.d;

import android.util.Pair;
import com.alipay.mobilesecuritysdk.constant.ConfigConstant;
import com.xiaomi.d.c.f;
import com.xiaomi.d.c.g;
import com.xiaomi.d.d.a;
import com.xiaomi.f.a.c.b;
import com.xiaomi.g.a.e;
import com.xiaomi.push.service.F;
import com.xiaomi.push.service.XMPushService;
import com.xiaomi.push.service.m;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class s {
    private static final AtomicInteger FZ;
    public static boolean c;
    private LinkedList Ga;
    protected a Gb;
    protected Reader Gc;
    protected Writer Gd;
    protected r Ge;
    protected XMPushService Gf;
    private final Collection Gg;
    private long Gh;
    protected int d;
    protected long e;
    protected final Map ev;
    protected String k;
    protected final int l;
    private int p;
    protected final Map yk;

    static {
        FZ = new AtomicInteger(0);
        c = false;
        try {
            c = Boolean.getBoolean("smack.debugEnabled");
        } catch (Exception e) {
        }
        z.a();
    }

    protected s(XMPushService xMPushService, r rVar) {
        this.d = 0;
        this.e = -1;
        this.Ga = new LinkedList();
        this.Gg = new CopyOnWriteArrayList();
        this.ev = new ConcurrentHashMap();
        this.yk = new ConcurrentHashMap();
        this.Gb = null;
        this.k = ConfigConstant.WIRELESS_FILENAME;
        this.p = 2;
        this.l = FZ.getAndIncrement();
        this.Gh = 0;
        this.Ge = rVar;
        this.Gf = xMPushService;
    }

    private void W(int i) {
        synchronized (this.Ga) {
            if (i == 1) {
                this.Ga.clear();
            } else {
                this.Ga.add(new Pair(Integer.valueOf(i), Long.valueOf(System.currentTimeMillis())));
                if (this.Ga.size() > 6) {
                    this.Ga.remove(0);
                }
            }
        }
    }

    private String a(int i) {
        return i == 1 ? "connected" : i == 0 ? "connecting" : i == 2 ? "disconnected" : "unknown";
    }

    public void a(int i, int i2, Exception exception) {
        if (i != this.p) {
            b.a(String.format("update the connection status. %1$s -> %2$s : %3$s ", new Object[]{a(this.p), a(i), F.a(i2)}));
        }
        if (com.xiaomi.f.a.d.a.Z(this.Gf)) {
            W(i);
        }
        if (i == 1) {
            this.Gf.a(10);
            if (this.p != 0) {
                b.a("try set connected while not connecting.");
            }
            this.p = i;
            for (u a : this.Gg) {
                a.a();
            }
        } else if (i == 0) {
            this.Gf.fa();
            if (this.p != 2) {
                b.a("try set connecting while not disconnected.");
            }
            this.p = i;
            for (u a2 : this.Gg) {
                a2.b();
            }
        } else if (i == 2) {
            this.Gf.a(10);
            if (this.p == 0) {
                for (u a22 : this.Gg) {
                    a22.a(exception == null ? new CancellationException("disconnect while connecting") : exception);
                }
            } else if (this.p == 1) {
                for (u a222 : this.Gg) {
                    a222.a(i2, exception);
                }
            }
            this.p = i;
        }
    }

    public abstract void a(f fVar, int i, Exception exception);

    public abstract void a(g gVar);

    public void a(o oVar, com.xiaomi.d.a.a aVar) {
        if (oVar == null) {
            throw new NullPointerException("Packet listener is null.");
        }
        this.ev.put(oVar, new a(oVar, aVar));
    }

    public void a(u uVar) {
        if (uVar != null && !this.Gg.contains(uVar)) {
            this.Gg.add(uVar);
        }
    }

    public void a(String str) {
        this.k = str;
        a(1, 0, null);
    }

    public abstract void a(String str, String str2);

    public abstract void a(g[] gVarArr);

    public void ao() {
        this.d = 0;
    }

    protected void b() {
        Class cls = null;
        if (this.Gc != null && this.Gd != null && this.Ge.i()) {
            if (this.Gb == null) {
                String property;
                try {
                    property = System.getProperty("smack.debuggerClass");
                } catch (Throwable th) {
                    Object obj = cls;
                }
                if (property != null) {
                    try {
                        cls = Class.forName(property);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                if (cls == null) {
                    this.Gb = new e(this, this.Gd, this.Gc);
                    this.Gc = this.Gb.eA();
                    this.Gd = this.Gb.eB();
                    return;
                }
                try {
                    this.Gb = (a) cls.getConstructor(new Class[]{s.class, Writer.class, Reader.class}).newInstance(new Object[]{this, this.Gd, this.Gc});
                    this.Gc = this.Gb.eA();
                    this.Gd = this.Gb.eB();
                    return;
                } catch (Throwable e2) {
                    throw new IllegalArgumentException("Can't initialize the configured debugger!", e2);
                }
            }
            this.Gc = this.Gb.a(this.Gc);
            this.Gd = this.Gb.a(this.Gd);
        }
    }

    public void b(o oVar, com.xiaomi.d.a.a aVar) {
        if (oVar == null) {
            throw new NullPointerException("Packet listener is null.");
        }
        this.yk.put(oVar, new a(oVar, aVar));
    }

    public void b(u uVar) {
        this.Gg.remove(uVar);
    }

    public void bu() {
        this.e = -1;
    }

    public void bw() {
        this.Gh = System.currentTimeMillis();
    }

    public boolean bx() {
        return System.currentTimeMillis() - this.Gh < ((long) z.c());
    }

    public abstract void c();

    public abstract void c(m mVar);

    public String d() {
        return this.Ge.e();
    }

    protected void d(g gVar) {
        for (a a : this.yk.values()) {
            a.a(gVar);
        }
    }

    public String e() {
        return this.Ge.h();
    }

    public String f() {
        return this.Ge.f();
    }

    public int g() {
        return this.Ge.g();
    }

    public boolean gu() {
        boolean z;
        synchronized (this.Ga) {
            Collection arrayList = new ArrayList();
            Iterator it = this.Ga.iterator();
            while (it.hasNext()) {
                Pair pair = (Pair) it.next();
                if (System.currentTimeMillis() - ((Long) pair.second).longValue() < 1800000) {
                    arrayList.add(pair);
                }
            }
            this.Ga.clear();
            this.Ga.addAll(arrayList);
            z = this.Ga.size() >= 6;
        }
        return z;
    }

    public void gv() {
        synchronized (this.Ga) {
            this.Ga.clear();
        }
    }

    public Collection hw() {
        return this.Gg;
    }

    public long hx() {
        return this.e;
    }

    public int hy() {
        return this.p;
    }

    public boolean i() {
        return this.p == 0;
    }

    public boolean j() {
        return this.p == 1;
    }

    public int k() {
        return this.d;
    }

    public boolean p(long j) {
        return this.Gh >= j;
    }
}
