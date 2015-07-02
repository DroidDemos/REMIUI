package com.xiaomi.b.a;

import android.content.Context;
import android.text.TextUtils;
import com.alipay.mobilesecuritysdk.constant.ConfigConstant;
import com.alipay.sdk.cons.GlobalConstants;
import com.xiaomi.f.a.c.b;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public final class H {
    static final /* synthetic */ boolean a;
    private static final int b;
    private static final int c;
    private static final boolean d;
    private Context oY;
    private final Set oZ;
    private final Set pa;
    private final Set pb;
    private final ReentrantLock pc;
    private final Condition pd;
    private final Condition pe;
    private final Condition pf;
    private long pg;
    private final N ph;
    private final Runnable pi;
    private final a pj;
    private final c pk;
    private final ScheduledExecutorService pl;
    private ThreadPoolExecutor pm;
    private ScheduledFuture pn;
    private e po;
    private Queue pp;
    private SortedSet pq;
    private Long pr;
    private List ps;
    private volatile long y;
    private volatile long z;

    static {
        boolean z = false;
        a = !H.class.desiredAssertionStatus();
        b = Integer.getInteger(H.class.getName() + ".emptyRequestDelay", 100).intValue();
        c = Integer.getInteger(H.class.getName() + ".pauseMargin", 500).intValue();
        String str = H.class.getSimpleName() + ".assertionsEnabled";
        if (System.getProperty(str) != null) {
            z = Boolean.getBoolean(str);
        } else if (!a) {
            z = true;
        }
        d = z;
    }

    private H(N n, Context context) {
        this.oZ = new CopyOnWriteArraySet();
        this.pa = new CopyOnWriteArraySet();
        this.pb = new CopyOnWriteArraySet();
        this.pc = new ReentrantLock();
        this.pd = this.pc.newCondition();
        this.pe = this.pc.newCondition();
        this.pf = this.pc.newCondition();
        this.pg = 0;
        this.pi = new I(this);
        this.pj = new E();
        this.pk = new c();
        this.pl = Executors.newSingleThreadScheduledExecutor();
        this.pp = new LinkedList();
        this.pq = new TreeSet();
        this.pr = Long.valueOf(-1);
        this.ps = new ArrayList();
        this.y = 0;
        this.z = 0;
        this.ph = n;
        this.oY = context.getApplicationContext();
        c();
    }

    public static H a(N n, Context context) {
        if (n != null) {
            return new H(n, context);
        }
        throw new IllegalArgumentException("Client configuration may not be null");
    }

    private d a(long j, d dVar) {
        cF();
        P bZ = dVar.bZ();
        bZ.b(K.pR, this.ph.b());
        bZ.b(K.pu, this.ph.d());
        bZ.b(K.pT, s.cr().toString());
        bZ.b(K.pU, "300");
        bZ.b(K.pC, GlobalConstants.d);
        bZ.b(K.pL, Long.toString(j));
        a(bZ);
        b(bZ);
        bZ.b(K.px, GlobalConstants.d);
        bZ.b(K.pO, null);
        return bZ.ij();
    }

    private n a(int i, z zVar) {
        cF();
        return a(zVar) ? n.aa(zVar.a(K.pz)) : (this.po == null || this.po.cc() != null) ? null : n.y(i);
    }

    private void a(long j) {
        cF();
        if (j < 0) {
            throw new IllegalArgumentException("Empty request delay must be >= 0 (was: " + j + ")");
        }
        am();
        if (d()) {
            b.b("SMACK-BOSH: Scheduling empty request in " + j + "ms");
            try {
                this.pn = this.pl.schedule(this.pi, j, TimeUnit.MILLISECONDS);
            } catch (Throwable e) {
                b.b("SMACK-BOSH: Could not schedule empty request", e);
            }
        }
    }

    private void a(P p) {
        cF();
        String e = this.ph.e();
        if (e != null) {
            p.b(K.pM, e);
        }
    }

    private void a(f fVar) {
        this.pp.add(fVar);
        this.pm.execute(new M(this));
    }

    private void a(z zVar, int i) {
        n a = a(i, zVar);
        if (a != null) {
            throw new aa("Terminal binding condition encountered: " + a.a() + "  (" + a.b() + ")");
        }
    }

    private void a(Throwable th) {
        ao();
        this.pc.lock();
        try {
            if (this.pm != null) {
                this.pm.shutdownNow();
                this.pm = null;
                this.pc.unlock();
                if (th == null) {
                    bu();
                } else {
                    b(th);
                }
                this.pc.lock();
                try {
                    am();
                    this.pp = null;
                    this.po = null;
                    this.pq = null;
                    this.ps = null;
                    this.pd.signalAll();
                    this.pe.signalAll();
                    this.pf.signalAll();
                    this.pj.a();
                } finally {
                    this.pc.unlock();
                }
            }
        } finally {
            this.pc.unlock();
        }
    }

    private static boolean a(z zVar) {
        return "terminate".equals(zVar.a(K.pS));
    }

    private void am() {
        cF();
        if (this.pn != null) {
            this.pn.cancel(false);
            this.pn = null;
        }
    }

    private void ao() {
        if (d && this.pc.isHeldByCurrentThread()) {
            throw new AssertionError("Lock is held by current thread");
        }
    }

    private d b(long j, d dVar) {
        cF();
        P bZ = dVar.bZ();
        bZ.b(K.pO, this.po.ca().toString());
        bZ.b(K.pL, Long.toString(j));
        return bZ.ij();
    }

    private void b(P p) {
        cF();
        String c = this.ph.c();
        if (c != null) {
            p.b(K.pB, c);
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void b(com.xiaomi.b.a.f r11) {
        /*
        r10 = this;
        r0 = 0;
        r8 = 0;
        r10.ao();
        r1 = r11.cg();	 Catch:{ aa -> 0x0083, InterruptedException -> 0x008d }
        r2 = r1.bV();	 Catch:{ aa -> 0x0083, InterruptedException -> 0x008d }
        r3 = r1.c();	 Catch:{ aa -> 0x0083, InterruptedException -> 0x008d }
        r4 = r10.pc;
        r4.lock();
        r4 = r1.bW();	 Catch:{ InterruptedException -> 0x010d }
        r6 = r10.y;	 Catch:{ InterruptedException -> 0x010d }
        r1 = (r6 > r4 ? 1 : (r6 == r4 ? 0 : -1));
        if (r1 != 0) goto L_0x0025;
    L_0x0021:
        r6 = 0;
        r10.y = r6;	 Catch:{ InterruptedException -> 0x010d }
    L_0x0025:
        r6 = r10.pg;	 Catch:{ InterruptedException -> 0x010d }
        r1 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1));
        if (r1 > 0) goto L_0x0097;
    L_0x002b:
        r1 = r10.pf;	 Catch:{ InterruptedException -> 0x010d }
        r1.signalAll();	 Catch:{ InterruptedException -> 0x010d }
    L_0x0030:
        r4 = 1;
        r6 = r10.pg;	 Catch:{ InterruptedException -> 0x010d }
        r4 = r4 + r6;
        r10.pg = r4;	 Catch:{ InterruptedException -> 0x010d }
        r1 = r10.pc;
        r1.unlock();
        r10.g(r2);
        r4 = r11.cf();
        r1 = r10.pc;
        r1.lock();
        r1 = r10.d();	 Catch:{ aa -> 0x01bd, InterruptedException -> 0x0210 }
        if (r1 != 0) goto L_0x012d;
    L_0x004e:
        r0 = r10.pc;	 Catch:{ aa -> 0x01bd, InterruptedException -> 0x0210 }
        r0.unlock();	 Catch:{ aa -> 0x01bd, InterruptedException -> 0x0210 }
        r0 = r10.pc;
        r0 = r0.isHeldByCurrentThread();
        if (r0 == 0) goto L_0x0126;
    L_0x005b:
        r0 = r10.pp;	 Catch:{ all -> 0x011f }
        if (r0 == 0) goto L_0x0078;
    L_0x005f:
        r0 = r10.pp;	 Catch:{ all -> 0x011f }
        r0 = r0.isEmpty();	 Catch:{ all -> 0x011f }
        if (r0 == 0) goto L_0x0078;
    L_0x0067:
        r0 = r10.g();	 Catch:{ all -> 0x011f }
        if (r0 != 0) goto L_0x0078;
    L_0x006d:
        r0 = r10.c(r4);	 Catch:{ all -> 0x011f }
        r2 = (r0 > r8 ? 1 : (r0 == r8 ? 0 : -1));
        if (r2 <= 0) goto L_0x0078;
    L_0x0075:
        r10.a(r0);	 Catch:{ all -> 0x011f }
    L_0x0078:
        r0 = r10.pe;	 Catch:{ all -> 0x011f }
        r0.signalAll();	 Catch:{ all -> 0x011f }
        r0 = r10.pc;
        r0.unlock();
    L_0x0082:
        return;
    L_0x0083:
        r0 = move-exception;
        r1 = "SMACK-BOSH: Could not obtain response";
        com.xiaomi.f.a.c.b.b(r1, r0);
        r10.a(r0);
        goto L_0x0082;
    L_0x008d:
        r0 = move-exception;
        r1 = "Interrupted";
        com.xiaomi.f.a.c.b.b(r1, r0);
        r10.a(r0);
        goto L_0x0082;
    L_0x0097:
        r1 = new java.lang.StringBuilder;	 Catch:{ InterruptedException -> 0x010d }
        r1.<init>();	 Catch:{ InterruptedException -> 0x010d }
        r6 = "SMACK-BOSH: responded rid(";
        r1 = r1.append(r6);	 Catch:{ InterruptedException -> 0x010d }
        r1 = r1.append(r4);	 Catch:{ InterruptedException -> 0x010d }
        r4 = ") is not expected (";
        r1 = r1.append(r4);	 Catch:{ InterruptedException -> 0x010d }
        r4 = r10.pg;	 Catch:{ InterruptedException -> 0x010d }
        r1 = r1.append(r4);	 Catch:{ InterruptedException -> 0x010d }
        r4 = "), wait";
        r1 = r1.append(r4);	 Catch:{ InterruptedException -> 0x010d }
        r1 = r1.toString();	 Catch:{ InterruptedException -> 0x010d }
        com.xiaomi.f.a.c.b.b(r1);	 Catch:{ InterruptedException -> 0x010d }
        r1 = r10.pf;	 Catch:{ InterruptedException -> 0x010d }
        r4 = 30;
        r6 = java.util.concurrent.TimeUnit.SECONDS;	 Catch:{ InterruptedException -> 0x010d }
        r1 = r1.await(r4, r6);	 Catch:{ InterruptedException -> 0x010d }
        if (r1 != 0) goto L_0x0030;
    L_0x00cb:
        r0 = new java.lang.StringBuilder;	 Catch:{ InterruptedException -> 0x010d }
        r0.<init>();	 Catch:{ InterruptedException -> 0x010d }
        r1 = "SMACK-BOSH: wait for ";
        r0 = r0.append(r1);	 Catch:{ InterruptedException -> 0x010d }
        r1 = r10.pg;	 Catch:{ InterruptedException -> 0x010d }
        r0 = r0.append(r1);	 Catch:{ InterruptedException -> 0x010d }
        r1 = " timeout, terminate!";
        r0 = r0.append(r1);	 Catch:{ InterruptedException -> 0x010d }
        r0 = r0.toString();	 Catch:{ InterruptedException -> 0x010d }
        com.xiaomi.f.a.c.b.c(r0);	 Catch:{ InterruptedException -> 0x010d }
        r0 = new com.xiaomi.b.a.aa;	 Catch:{ InterruptedException -> 0x010d }
        r1 = new java.lang.StringBuilder;	 Catch:{ InterruptedException -> 0x010d }
        r1.<init>();	 Catch:{ InterruptedException -> 0x010d }
        r2 = "wait timeout for rid";
        r1 = r1.append(r2);	 Catch:{ InterruptedException -> 0x010d }
        r2 = r10.pg;	 Catch:{ InterruptedException -> 0x010d }
        r1 = r1.append(r2);	 Catch:{ InterruptedException -> 0x010d }
        r1 = r1.toString();	 Catch:{ InterruptedException -> 0x010d }
        r0.<init>(r1);	 Catch:{ InterruptedException -> 0x010d }
        r10.a(r0);	 Catch:{ InterruptedException -> 0x010d }
        r0 = r10.pc;
        r0.unlock();
        goto L_0x0082;
    L_0x010d:
        r0 = move-exception;
        r10.a(r0);	 Catch:{ all -> 0x0118 }
        r0 = r10.pc;
        r0.unlock();
        goto L_0x0082;
    L_0x0118:
        r0 = move-exception;
        r1 = r10.pc;
        r1.unlock();
        throw r0;
    L_0x011f:
        r0 = move-exception;
        r1 = r10.pc;
        r1.unlock();
        throw r0;
    L_0x0126:
        r0 = "SMACK-BOSH: lock is not held by this thread, don't schedule empty request";
        com.xiaomi.f.a.c.b.b(r0);
        goto L_0x0082;
    L_0x012d:
        r1 = r10.po;	 Catch:{ aa -> 0x01bd, InterruptedException -> 0x0210 }
        if (r1 != 0) goto L_0x013a;
    L_0x0131:
        r1 = com.xiaomi.b.a.e.a(r4, r2);	 Catch:{ aa -> 0x01bd, InterruptedException -> 0x0210 }
        r10.po = r1;	 Catch:{ aa -> 0x01bd, InterruptedException -> 0x0210 }
        r10.cG();	 Catch:{ aa -> 0x01bd, InterruptedException -> 0x0210 }
    L_0x013a:
        r5 = r10.po;	 Catch:{ aa -> 0x01bd, InterruptedException -> 0x0210 }
        r10.a(r2, r3);	 Catch:{ aa -> 0x01bd, InterruptedException -> 0x0210 }
        r1 = a(r2);	 Catch:{ aa -> 0x01bd, InterruptedException -> 0x0210 }
        if (r1 == 0) goto L_0x018d;
    L_0x0145:
        r0 = r10.pc;	 Catch:{ aa -> 0x01bd, InterruptedException -> 0x0210 }
        r0.unlock();	 Catch:{ aa -> 0x01bd, InterruptedException -> 0x0210 }
        r0 = 0;
        r10.a(r0);	 Catch:{ aa -> 0x01bd, InterruptedException -> 0x0210 }
        r0 = r10.pc;
        r0 = r0.isHeldByCurrentThread();
        if (r0 == 0) goto L_0x0186;
    L_0x0156:
        r0 = r10.pp;	 Catch:{ all -> 0x017f }
        if (r0 == 0) goto L_0x0173;
    L_0x015a:
        r0 = r10.pp;	 Catch:{ all -> 0x017f }
        r0 = r0.isEmpty();	 Catch:{ all -> 0x017f }
        if (r0 == 0) goto L_0x0173;
    L_0x0162:
        r0 = r10.g();	 Catch:{ all -> 0x017f }
        if (r0 != 0) goto L_0x0173;
    L_0x0168:
        r0 = r10.c(r4);	 Catch:{ all -> 0x017f }
        r2 = (r0 > r8 ? 1 : (r0 == r8 ? 0 : -1));
        if (r2 <= 0) goto L_0x0173;
    L_0x0170:
        r10.a(r0);	 Catch:{ all -> 0x017f }
    L_0x0173:
        r0 = r10.pe;	 Catch:{ all -> 0x017f }
        r0.signalAll();	 Catch:{ all -> 0x017f }
        r0 = r10.pc;
        r0.unlock();
        goto L_0x0082;
    L_0x017f:
        r0 = move-exception;
        r1 = r10.pc;
        r1.unlock();
        throw r0;
    L_0x0186:
        r0 = "SMACK-BOSH: lock is not held by this thread, don't schedule empty request";
        com.xiaomi.f.a.c.b.b(r0);
        goto L_0x0082;
    L_0x018d:
        r1 = b(r2);	 Catch:{ aa -> 0x01bd, InterruptedException -> 0x0210 }
        if (r1 == 0) goto L_0x02a8;
    L_0x0193:
        r1 = new java.util.ArrayList;	 Catch:{ aa -> 0x01bd, InterruptedException -> 0x0210 }
        r0 = r10.pp;	 Catch:{ aa -> 0x01bd, InterruptedException -> 0x0210 }
        r0 = r0.size();	 Catch:{ aa -> 0x01bd, InterruptedException -> 0x0210 }
        r1.<init>(r0);	 Catch:{ aa -> 0x01bd, InterruptedException -> 0x0210 }
        r0 = r10.pp;	 Catch:{ aa -> 0x01bd, InterruptedException -> 0x0210 }
        r2 = r0.iterator();	 Catch:{ aa -> 0x01bd, InterruptedException -> 0x0210 }
    L_0x01a4:
        r0 = r2.hasNext();	 Catch:{ aa -> 0x01bd, InterruptedException -> 0x0210 }
        if (r0 == 0) goto L_0x01fc;
    L_0x01aa:
        r3 = new com.xiaomi.b.a.f;	 Catch:{ aa -> 0x01bd, InterruptedException -> 0x0210 }
        r0 = r2.next();	 Catch:{ aa -> 0x01bd, InterruptedException -> 0x0210 }
        r0 = (com.xiaomi.b.a.f) r0;	 Catch:{ aa -> 0x01bd, InterruptedException -> 0x0210 }
        r0 = r0.cf();	 Catch:{ aa -> 0x01bd, InterruptedException -> 0x0210 }
        r3.<init>(r0);	 Catch:{ aa -> 0x01bd, InterruptedException -> 0x0210 }
        r1.add(r3);	 Catch:{ aa -> 0x01bd, InterruptedException -> 0x0210 }
        goto L_0x01a4;
    L_0x01bd:
        r0 = move-exception;
        r1 = "SMACK-BOSH: Could not process response";
        com.xiaomi.f.a.c.b.b(r1, r0);	 Catch:{ all -> 0x02c1 }
        r1 = r10.pc;	 Catch:{ all -> 0x02c1 }
        r1.unlock();	 Catch:{ all -> 0x02c1 }
        r10.a(r0);	 Catch:{ all -> 0x02c1 }
        r0 = r10.pc;
        r0 = r0.isHeldByCurrentThread();
        if (r0 == 0) goto L_0x0306;
    L_0x01d3:
        r0 = r10.pp;	 Catch:{ all -> 0x02ff }
        if (r0 == 0) goto L_0x01f0;
    L_0x01d7:
        r0 = r10.pp;	 Catch:{ all -> 0x02ff }
        r0 = r0.isEmpty();	 Catch:{ all -> 0x02ff }
        if (r0 == 0) goto L_0x01f0;
    L_0x01df:
        r0 = r10.g();	 Catch:{ all -> 0x02ff }
        if (r0 != 0) goto L_0x01f0;
    L_0x01e5:
        r0 = r10.c(r4);	 Catch:{ all -> 0x02ff }
        r2 = (r0 > r8 ? 1 : (r0 == r8 ? 0 : -1));
        if (r2 <= 0) goto L_0x01f0;
    L_0x01ed:
        r10.a(r0);	 Catch:{ all -> 0x02ff }
    L_0x01f0:
        r0 = r10.pe;	 Catch:{ all -> 0x02ff }
        r0.signalAll();	 Catch:{ all -> 0x02ff }
        r0 = r10.pc;
        r0.unlock();
        goto L_0x0082;
    L_0x01fc:
        r2 = r1.iterator();	 Catch:{ aa -> 0x01bd, InterruptedException -> 0x0210 }
    L_0x0200:
        r0 = r2.hasNext();	 Catch:{ aa -> 0x01bd, InterruptedException -> 0x0210 }
        if (r0 == 0) goto L_0x024f;
    L_0x0206:
        r0 = r2.next();	 Catch:{ aa -> 0x01bd, InterruptedException -> 0x0210 }
        r0 = (com.xiaomi.b.a.f) r0;	 Catch:{ aa -> 0x01bd, InterruptedException -> 0x0210 }
        r10.a(r0);	 Catch:{ aa -> 0x01bd, InterruptedException -> 0x0210 }
        goto L_0x0200;
    L_0x0210:
        r0 = move-exception;
        r1 = "SMACK-BOSH: Could not process response";
        com.xiaomi.f.a.c.b.b(r1, r0);	 Catch:{ all -> 0x02c1 }
        r1 = r10.pc;	 Catch:{ all -> 0x02c1 }
        r1.unlock();	 Catch:{ all -> 0x02c1 }
        r10.a(r0);	 Catch:{ all -> 0x02c1 }
        r0 = r10.pc;
        r0 = r0.isHeldByCurrentThread();
        if (r0 == 0) goto L_0x0314;
    L_0x0226:
        r0 = r10.pp;	 Catch:{ all -> 0x030d }
        if (r0 == 0) goto L_0x0243;
    L_0x022a:
        r0 = r10.pp;	 Catch:{ all -> 0x030d }
        r0 = r0.isEmpty();	 Catch:{ all -> 0x030d }
        if (r0 == 0) goto L_0x0243;
    L_0x0232:
        r0 = r10.g();	 Catch:{ all -> 0x030d }
        if (r0 != 0) goto L_0x0243;
    L_0x0238:
        r0 = r10.c(r4);	 Catch:{ all -> 0x030d }
        r2 = (r0 > r8 ? 1 : (r0 == r8 ? 0 : -1));
        if (r2 <= 0) goto L_0x0243;
    L_0x0240:
        r10.a(r0);	 Catch:{ all -> 0x030d }
    L_0x0243:
        r0 = r10.pe;	 Catch:{ all -> 0x030d }
        r0.signalAll();	 Catch:{ all -> 0x030d }
        r0 = r10.pc;
        r0.unlock();
        goto L_0x0082;
    L_0x024f:
        r0 = r1;
    L_0x0250:
        r1 = r10.pc;
        r1 = r1.isHeldByCurrentThread();
        if (r1 == 0) goto L_0x02f9;
    L_0x0258:
        r1 = r10.pp;	 Catch:{ all -> 0x02f2 }
        if (r1 == 0) goto L_0x0275;
    L_0x025c:
        r1 = r10.pp;	 Catch:{ all -> 0x02f2 }
        r1 = r1.isEmpty();	 Catch:{ all -> 0x02f2 }
        if (r1 == 0) goto L_0x0275;
    L_0x0264:
        r1 = r10.g();	 Catch:{ all -> 0x02f2 }
        if (r1 != 0) goto L_0x0275;
    L_0x026a:
        r1 = r10.c(r4);	 Catch:{ all -> 0x02f2 }
        r3 = (r1 > r8 ? 1 : (r1 == r8 ? 0 : -1));
        if (r3 <= 0) goto L_0x0275;
    L_0x0272:
        r10.a(r1);	 Catch:{ all -> 0x02f2 }
    L_0x0275:
        r1 = r10.pe;	 Catch:{ all -> 0x02f2 }
        r1.signalAll();	 Catch:{ all -> 0x02f2 }
        r1 = r10.pc;
        r1.unlock();
    L_0x027f:
        if (r0 == 0) goto L_0x0082;
    L_0x0281:
        r1 = r0.iterator();
    L_0x0285:
        r0 = r1.hasNext();
        if (r0 == 0) goto L_0x0082;
    L_0x028b:
        r0 = r1.next();
        r0 = (com.xiaomi.b.a.f) r0;
        r2 = r10.pj;
        r3 = r0.cf();
        r4 = r10.oY;
        r2 = r2.a(r5, r3, r4);
        r0.a(r2);
        r0 = r0.cf();
        r10.f(r0);
        goto L_0x0285;
    L_0x02a8:
        r10.b(r4, r2);	 Catch:{ aa -> 0x01bd, InterruptedException -> 0x0210 }
        r10.d(r4);	 Catch:{ aa -> 0x01bd, InterruptedException -> 0x0210 }
        r1 = r10.e(r2);	 Catch:{ aa -> 0x01bd, InterruptedException -> 0x0210 }
        if (r1 == 0) goto L_0x0250;
    L_0x02b4:
        r0 = new java.util.ArrayList;	 Catch:{ aa -> 0x01bd, InterruptedException -> 0x0210 }
        r2 = 1;
        r0.<init>(r2);	 Catch:{ aa -> 0x01bd, InterruptedException -> 0x0210 }
        r0.add(r1);	 Catch:{ aa -> 0x01bd, InterruptedException -> 0x0210 }
        r10.a(r1);	 Catch:{ aa -> 0x01bd, InterruptedException -> 0x0210 }
        goto L_0x0250;
    L_0x02c1:
        r0 = move-exception;
        r1 = r10.pc;
        r1 = r1.isHeldByCurrentThread();
        if (r1 == 0) goto L_0x0322;
    L_0x02ca:
        r1 = r10.pp;	 Catch:{ all -> 0x031b }
        if (r1 == 0) goto L_0x02e7;
    L_0x02ce:
        r1 = r10.pp;	 Catch:{ all -> 0x031b }
        r1 = r1.isEmpty();	 Catch:{ all -> 0x031b }
        if (r1 == 0) goto L_0x02e7;
    L_0x02d6:
        r1 = r10.g();	 Catch:{ all -> 0x031b }
        if (r1 != 0) goto L_0x02e7;
    L_0x02dc:
        r1 = r10.c(r4);	 Catch:{ all -> 0x031b }
        r3 = (r1 > r8 ? 1 : (r1 == r8 ? 0 : -1));
        if (r3 <= 0) goto L_0x02e7;
    L_0x02e4:
        r10.a(r1);	 Catch:{ all -> 0x031b }
    L_0x02e7:
        r1 = r10.pe;	 Catch:{ all -> 0x031b }
        r1.signalAll();	 Catch:{ all -> 0x031b }
        r1 = r10.pc;
        r1.unlock();
    L_0x02f1:
        throw r0;
    L_0x02f2:
        r0 = move-exception;
        r1 = r10.pc;
        r1.unlock();
        throw r0;
    L_0x02f9:
        r1 = "SMACK-BOSH: lock is not held by this thread, don't schedule empty request";
        com.xiaomi.f.a.c.b.b(r1);
        goto L_0x027f;
    L_0x02ff:
        r0 = move-exception;
        r1 = r10.pc;
        r1.unlock();
        throw r0;
    L_0x0306:
        r0 = "SMACK-BOSH: lock is not held by this thread, don't schedule empty request";
        com.xiaomi.f.a.c.b.b(r0);
        goto L_0x0082;
    L_0x030d:
        r0 = move-exception;
        r1 = r10.pc;
        r1.unlock();
        throw r0;
    L_0x0314:
        r0 = "SMACK-BOSH: lock is not held by this thread, don't schedule empty request";
        com.xiaomi.f.a.c.b.b(r0);
        goto L_0x0082;
    L_0x031b:
        r0 = move-exception;
        r1 = r10.pc;
        r1.unlock();
        throw r0;
    L_0x0322:
        r1 = "SMACK-BOSH: lock is not held by this thread, don't schedule empty request";
        com.xiaomi.f.a.c.b.b(r1);
        goto L_0x02f1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.b.a.H.b(com.xiaomi.b.a.f):void");
    }

    private void b(z zVar, z zVar2) {
        cF();
        if (this.po.f() && zVar2.a(K.pJ) == null) {
            String a = zVar2.a(K.px);
            Long valueOf = a == null ? Long.valueOf(Long.parseLong(zVar.a(K.pL))) : Long.valueOf(Long.parseLong(a));
            Iterator it = this.ps.iterator();
            while (it.hasNext()) {
                if (Long.valueOf(Long.parseLong(((z) it.next()).a(K.pL))).compareTo(valueOf) <= 0) {
                    it.remove();
                }
            }
        }
    }

    private void b(Throwable th) {
        ao();
        w wVar = null;
        for (L l : this.oZ) {
            if (wVar == null) {
                wVar = w.a(this, this.ps, th);
            }
            try {
                l.a(wVar);
            } catch (Throwable e) {
                b.b("SMACK-BOSH:Unhandled Exception", e);
            }
        }
    }

    private static boolean b(z zVar) {
        return ConfigConstant.LOG_JSON_STR_ERROR.equals(zVar.a(K.pS));
    }

    private void bu() {
        ao();
        w wVar = null;
        for (L l : this.oZ) {
            if (wVar == null) {
                wVar = w.f(this);
            }
            try {
                l.a(wVar);
            } catch (Throwable e) {
                b.b("SMACK-BOSH:Unhandled Exception", e);
            }
        }
    }

    private long c(z zVar) {
        cF();
        if (!(this.po == null || this.po.ce() == null)) {
            try {
                o ae = o.ae(zVar.a(K.pH));
                if (ae != null) {
                    long c = (long) (ae.c() - c);
                    return c < 0 ? (long) b : c;
                }
            } catch (Throwable e) {
                b.b("SMACK-BOSH: Could not extract", e);
            }
        }
        return cE();
    }

    private void c() {
        ao();
        this.pc.lock();
        try {
            this.pj.a(this.ph);
            this.pm = new ThreadPoolExecutor(2, 2, 60, TimeUnit.SECONDS, new LinkedBlockingQueue());
        } finally {
            this.pc.unlock();
        }
    }

    private f cD() {
        f fVar;
        ao();
        this.pc.lock();
        do {
            if (this.pp == null) {
                this.pc.unlock();
                return null;
            }
            try {
                fVar = (f) this.pp.poll();
                if (fVar == null) {
                    this.pd.await();
                    continue;
                }
            } catch (Throwable e) {
                b.b("Interrupted", e);
                continue;
            } catch (Throwable th) {
                this.pc.unlock();
            }
        } while (fVar == null);
        this.pc.unlock();
        return fVar;
    }

    private long cE() {
        cF();
        v cd = this.po.cd();
        long j = (long) b;
        if (cd != null) {
            long c = (long) cd.c();
            if (c > j) {
                return c;
            }
        }
        return j;
    }

    private void cF() {
        if (d && !this.pc.isHeldByCurrentThread()) {
            throw new AssertionError("Lock is not held by current thread");
        }
    }

    private void cG() {
        boolean isHeldByCurrentThread = this.pc.isHeldByCurrentThread();
        if (isHeldByCurrentThread) {
            this.pc.unlock();
        }
        try {
            w wVar = null;
            for (L l : this.oZ) {
                if (wVar == null) {
                    wVar = w.e(this);
                }
                l.a(wVar);
            }
            if (isHeldByCurrentThread) {
                this.pc.lock();
            }
        } catch (Throwable e) {
            b.b("SMACK-BOSH:Unhandled Exception", e);
        } catch (Throwable th) {
            if (isHeldByCurrentThread) {
                this.pc.lock();
            }
        }
    }

    private void d(z zVar) {
        cF();
        Long valueOf = Long.valueOf(Long.parseLong(zVar.a(K.pL)));
        if (this.pr.equals(Long.valueOf(-1))) {
            this.pr = valueOf;
            return;
        }
        this.pq.add(valueOf);
        valueOf = Long.valueOf(this.pr.longValue() + 1);
        while (!this.pq.isEmpty() && valueOf.equals(this.pq.first())) {
            this.pr = valueOf;
            this.pq.remove(valueOf);
            valueOf = Long.valueOf(valueOf.longValue() + 1);
        }
    }

    private boolean d() {
        cF();
        return this.pm != null;
    }

    private f e(z zVar) {
        cF();
        String a = zVar.a(K.pJ);
        if (a == null) {
            return null;
        }
        Long valueOf = Long.valueOf(Long.parseLong(a));
        Long.valueOf(Long.parseLong(zVar.a(K.pQ)));
        Iterator it = this.ps.iterator();
        z zVar2 = null;
        while (it.hasNext() && zVar2 == null) {
            z zVar3 = (z) it.next();
            if (!valueOf.equals(Long.valueOf(Long.parseLong(zVar3.a(K.pL))))) {
                zVar3 = zVar2;
            }
            zVar2 = zVar3;
        }
        if (zVar2 == null) {
            throw new aa("Report of missing message with RID '" + a + "' but local copy of that request was not found");
        }
        f fVar = new f(zVar2);
        a(fVar);
        this.pd.signalAll();
        return fVar;
    }

    private void e() {
        f cD = cD();
        if (cD != null) {
            this.pc.lock();
            try {
                long longValue = Long.valueOf(cD.cf().a(K.pL)).longValue();
                if (this.pg == 0) {
                    this.pg = longValue;
                }
                this.pc.unlock();
                b(cD);
            } catch (Throwable th) {
                this.pc.unlock();
            }
        }
    }

    private void f(z zVar) {
        ao();
        ab abVar = null;
        for (F f : this.pa) {
            if (abVar == null) {
                abVar = ab.a(this, zVar);
            }
            try {
                f.a(abVar);
            } catch (Throwable e) {
                b.b("SMACK-BOSH:Unhandled Exception", e);
            }
        }
    }

    private void g(z zVar) {
        ao();
        ab abVar = null;
        for (G g : this.pb) {
            if (abVar == null) {
                abVar = ab.b(this, zVar);
            }
            try {
                g.b(abVar);
            } catch (Throwable e) {
                b.b("SMACK-BOSH:Unhandled Exception", e);
            }
        }
    }

    private boolean g() {
        return !(this.pn == null || this.pn.isDone()) || this.y > 0;
    }

    private void j() {
        ao();
        try {
            b(d.bX().ij());
        } catch (Throwable e) {
            a(e);
        }
    }

    public void a() {
        a(new aa("Session explicitly closed by caller"));
    }

    public void a(F f) {
        if (f == null) {
            throw new IllegalArgumentException("Listener may not be null");
        }
        this.pa.add(f);
    }

    public void a(G g) {
        if (g == null) {
            throw new IllegalArgumentException("Listener may not be null");
        }
        this.pb.add(g);
    }

    public void a(L l) {
        if (l == null) {
            throw new IllegalArgumentException("Listener may not be null");
        }
        this.oZ.add(l);
    }

    public void b() {
        if (this.pm != null) {
            if (System.currentTimeMillis() - this.z > 30000 && this.pm.getActiveCount() > 1) {
                a(new aa("SMACK-BOSH: request timeout happened, reset connection"));
            } else if (this.pm.getActiveCount() <= 0 || g()) {
                this.pc.lock();
                try {
                    a(0);
                } finally {
                    this.pc.unlock();
                }
            }
        }
    }

    public void b(d dVar) {
        f fVar;
        ao();
        if (dVar == null) {
            throw new IllegalArgumentException("Message body may not be null");
        }
        this.pc.lock();
        if (d() || a((z) dVar)) {
            try {
                z a;
                long a2 = this.pk.a();
                if (TextUtils.isEmpty(dVar.f())) {
                    this.y = a2;
                }
                e eVar = this.po;
                if (eVar == null && this.pp.isEmpty()) {
                    a = a(a2, dVar);
                } else {
                    a = b(a2, dVar);
                    if (this.po.f()) {
                        this.ps.add(a);
                    }
                }
                fVar = new f(a);
                a(fVar);
                this.pd.signalAll();
                am();
                a = fVar.cf();
                fVar.a(this.pj.a(eVar, a, this.oY));
                f(a);
            } finally {
                fVar = this.pc;
                fVar.unlock();
            }
        } else {
            throw new aa("Cannot send message when session is closed");
        }
    }

    public void c(d dVar) {
        if (dVar == null) {
            throw new IllegalArgumentException("Message body may not be null");
        }
        P bZ = dVar.bZ();
        bZ.b(K.pS, "terminate");
        b(bZ.ij());
    }
}
