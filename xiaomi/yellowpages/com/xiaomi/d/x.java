package com.xiaomi.d;

import android.os.SystemClock;
import com.alipay.mobilesecuritysdk.constant.ConfigConstant;
import com.miui.yellowpage.ui.bw;
import com.xiaomi.c.i;
import com.xiaomi.c.j;
import com.xiaomi.d.c.f;
import com.xiaomi.d.c.g;
import com.xiaomi.f.a.c.b;
import com.xiaomi.f.a.d.a;
import com.xiaomi.push.service.XMPushService;
import com.xiaomi.push.service.m;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;

public class x extends s {
    public Exception IQ;
    protected Socket IR;
    p IS;
    n IT;
    private XMPushService IU;
    private volatile long IV;
    String o;
    private String r;
    private String s;
    private String t;
    private volatile long w;
    private final String x;
    private volatile long y;
    private int z;

    public x(XMPushService xMPushService, r rVar) {
        super(xMPushService, rVar);
        this.IQ = null;
        this.o = null;
        this.r = null;
        this.s = ConfigConstant.WIRELESS_FILENAME;
        this.IV = 0;
        this.w = 0;
        this.x = "<pf><p>t:%1$d</p></pf>";
        this.y = 0;
        this.IU = xMPushService;
    }

    private void a(r rVar) {
        d(rVar.h(), rVar.g());
    }

    private void a(Exception exception) {
        if (SystemClock.elapsedRealtime() - this.y >= ConfigConstant.REQUEST_LOCATE_INTERVAL) {
            this.z = 0;
        } else if (a.Z(this.IU)) {
            this.z++;
            if (this.z >= 2) {
                String e = e();
                b.a("max short conn time reached, sink down current host:" + e);
                a(e, 0, exception);
                this.z = 0;
            }
        }
    }

    private void a(String str, long j, Exception exception) {
        i ba = j.en().ba(r.d());
        if (ba != null) {
            ba.a(str, j, 0, exception);
            j.en().et();
        }
    }

    private void bF() {
        synchronized (this) {
            hZ();
            this.IS = new p(this);
            this.IT = new n(this);
            if (this.Ge.i()) {
                a(this.Gb.eC(), null);
                if (this.Gb.eD() != null) {
                    b(this.Gb.eD(), null);
                }
            }
            this.IS.c();
            this.IT.b();
        }
    }

    private void d(String str, int i) {
        Object obj;
        Exception exception;
        Object obj2;
        Throwable th;
        Throwable th2;
        Object obj3 = null;
        this.IQ = null;
        ArrayList arrayList = new ArrayList();
        int intValue = b.cd("get bucket for host : " + str).intValue();
        i ck = ck(str);
        b.a(Integer.valueOf(intValue));
        if (ck != null) {
            arrayList = ck.dD();
        }
        if (arrayList.isEmpty()) {
            arrayList.add(str);
        }
        this.y = 0;
        StringBuilder stringBuilder = new StringBuilder();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            String str2 = (String) it.next();
            long currentTimeMillis = System.currentTimeMillis();
            this.d++;
            try {
                b.a("begin to connect to " + str2);
                this.IR = ic();
                this.IR.bind(null);
                this.IR.connect(new InetSocketAddress(str2, i), bw.FILE_CHOOSER_RESULT_CODE);
                this.IR.setTcpNoDelay(true);
                this.t = str2;
                bF();
                try {
                    this.e = System.currentTimeMillis() - currentTimeMillis;
                    if (ck != null) {
                        ck.a(str2, this.e, 0);
                    }
                    this.y = SystemClock.elapsedRealtime();
                    b.a("connected to " + str2 + " in " + this.e);
                    obj3 = 1;
                    j.en().et();
                    if (obj3 == null) {
                        throw new v(stringBuilder.toString());
                    }
                } catch (Exception e) {
                    obj = 1;
                    exception = e;
                } catch (Exception e2) {
                    obj = 1;
                    exception = e2;
                    if (ck != null) {
                        ck.a(str2, System.currentTimeMillis() - currentTimeMillis, 0, exception);
                    }
                    this.IQ = exception;
                    b.c("SMACK: Could not connect to:" + str2);
                    stringBuilder.append("SMACK: Could not connect to ").append(str2).append(" port:").append(i).append(" ").append(exception.getMessage()).append("\n");
                    obj2 = obj;
                    obj3 = obj2;
                } catch (Throwable th3) {
                    th = th3;
                    int i2 = 1;
                    th2 = th;
                    b.a(th2);
                    obj3 = obj2;
                }
            } catch (Exception e3) {
                obj = obj3;
                exception = e3;
                if (ck != null) {
                    ck.a(str2, System.currentTimeMillis() - currentTimeMillis, 0, exception);
                }
                this.IQ = exception;
                b.c("SMACK: Could not connect to:" + str2);
                stringBuilder.append("SMACK: Could not connect to ").append(str2).append(" port:").append(i).append(" ").append(exception.getMessage()).append("\n");
                obj2 = obj;
                obj3 = obj2;
            } catch (Exception e32) {
                obj = obj3;
                exception = e32;
                if (ck != null) {
                    ck.a(str2, System.currentTimeMillis() - currentTimeMillis, 0, exception);
                }
                this.IQ = exception;
                b.c("SMACK: Could not connect to:" + str2);
                stringBuilder.append("SMACK: Could not connect to ").append(str2).append(" port:").append(i).append(" ").append(exception.getMessage()).append("\n");
                obj2 = obj;
                obj3 = obj2;
            } catch (Throwable th32) {
                th = th32;
                obj2 = obj3;
                th2 = th;
                b.a(th2);
                obj3 = obj2;
            }
        }
        j.en().et();
        if (obj3 == null) {
            throw new v(stringBuilder.toString());
        }
    }

    private void hZ() {
        try {
            this.Gc = new BufferedReader(new InputStreamReader(this.IR.getInputStream(), "UTF-8"), 4096);
            this.Gd = new BufferedWriter(new OutputStreamWriter(this.IR.getOutputStream(), "UTF-8"));
            if (this.Gc != null && this.Gd != null) {
                b();
            }
        } catch (Throwable e) {
            throw new v("Error to init reader and writer", e);
        }
    }

    public String a() {
        return this.k;
    }

    public void a(int i, Exception exception) {
        this.IU.a(new A(this, 2, i, exception));
    }

    public void a(f fVar, int i, Exception exception) {
        b(fVar, i, exception);
        if (exception != null && this.y != 0) {
            a(exception);
        }
    }

    public void a(g gVar) {
        if (this.IS != null) {
            this.IS.a(gVar);
            return;
        }
        throw new v("the writer is null.");
    }

    public void a(String str, String str2) {
        synchronized (this) {
            g fVar = new f(f.b.unavailable);
            fVar.cx(str);
            fVar.cz(str2);
            if (this.IS != null) {
                this.IS.a(fVar);
            }
        }
    }

    public void a(g[] gVarArr) {
        for (g a : gVarArr) {
            a(a);
        }
    }

    protected void b(f fVar, int i, Exception exception) {
        synchronized (this) {
            if (hy() != 2) {
                a(2, i, exception);
                this.k = ConfigConstant.WIRELESS_FILENAME;
                if (this.IT != null) {
                    this.IT.c();
                    this.IT.d();
                    this.IT = null;
                }
                if (this.IS != null) {
                    try {
                        this.IS.b();
                    } catch (Throwable e) {
                        b.a(e);
                    }
                    this.IS.a();
                    this.IS = null;
                }
                try {
                    this.IR.close();
                } catch (Exception e2) {
                }
                if (this.Gc != null) {
                    try {
                        this.Gc.close();
                    } catch (Throwable th) {
                    }
                    this.Gc = null;
                }
                if (this.Gd != null) {
                    try {
                        this.Gd.close();
                    } catch (Throwable th2) {
                    }
                    this.Gd = null;
                }
                this.IV = 0;
                this.w = 0;
            }
        }
    }

    public void b(String str) {
        this.s = str;
    }

    public void c() {
        if (this.IS != null) {
            this.IS.d();
            this.IU.a(new w(this, 13, System.currentTimeMillis()), 15000);
            return;
        }
        throw new v("the packetwriter is null.");
    }

    public void c(m mVar) {
        synchronized (this) {
            new y().a(mVar, a(), this);
        }
    }

    public i ck(String str) {
        return j.en().ba(str);
    }

    public String e() {
        return this.t;
    }

    public void ia() {
        synchronized (this) {
            try {
                if (j() || i()) {
                    b.a("WARNING: current xmpp has connected");
                } else {
                    a(0, 0, null);
                    a(this.Ge);
                }
            } catch (Throwable e) {
                throw new v(e);
            }
        }
    }

    public String ib() {
        String str;
        if (this.w == 0 || this.IV == 0) {
            str = ConfigConstant.WIRELESS_FILENAME;
        } else {
            str = String.format("<pf><p>t:%1$d</p></pf>", new Object[]{Long.valueOf(this.w - this.IV)});
        }
        return String.format(this.s, new Object[]{str});
    }

    public Socket ic() {
        return new Socket();
    }

    public void id() {
        this.IV = SystemClock.uptimeMillis();
    }

    public void ie() {
        this.w = SystemClock.uptimeMillis();
    }
}
