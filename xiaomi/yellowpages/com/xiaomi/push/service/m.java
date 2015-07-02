package com.xiaomi.push.service;

import android.content.Context;
import com.alipay.sdk.protocol.WindowData;
import com.ta.utdid2.core.persistent.TransactionXMLFile;
import com.xiaomi.f.a.c.b;
import com.xiaomi.push.service.q.c;
import java.util.ArrayList;
import java.util.List;

public class m {
    public String a;
    public String b;
    public String c;
    public String d;
    public boolean e;
    public String f;
    public String g;
    public String h;
    public String i;
    public String j;
    public w kH;
    public Context kI;
    c kJ;
    private List kK;
    private XMPushService kL;
    private e kM;
    private int n;

    public m(XMPushService xMPushService) {
        this.kJ = c.unbind;
        this.n = 0;
        this.kK = new ArrayList();
        this.kM = new e(this);
        this.kL = xMPushService;
        a(new E(this));
    }

    public long a() {
        return 1000 * (((long) ((20.0d * Math.random()) - 10.0d)) + ((long) ((this.n + 1) * 15)));
    }

    public String a(int i) {
        switch (i) {
            case TransactionXMLFile.MODE_WORLD_READABLE /*1*/:
                return "OPEN";
            case TransactionXMLFile.MODE_WORLD_WRITEABLE /*2*/:
                return "CLOSE";
            case WindowData.d /*3*/:
                return "KICK";
            default:
                return "unknown";
        }
    }

    public void a(n nVar) {
        this.kK.add(nVar);
    }

    public void a(c cVar, int i, int i2, String str, String str2) {
        boolean z = true;
        for (n a : this.kK) {
            a.a(this.kJ, cVar, i2);
        }
        if (this.kJ != cVar) {
            b.a(String.format("update the client %7$s status. %1$s->%2$s %3$s %4$s %5$s %6$s", new Object[]{this.kJ, cVar, a(i), F.a(i2), str, str2, this.h}));
            this.kJ = cVar;
        }
        if (this.kH == null) {
            b.c("status changed while the client dispatcher is missing");
        } else if (i == 2) {
            this.kH.a(this.kI, this, i2);
        } else if (i == 3) {
            this.kH.a(this.kI, this, str2, str);
        } else if (i == 1) {
            if (cVar != c.binded) {
                z = false;
            }
            if (!z && "wait".equals(str2)) {
                this.n++;
            } else if (z) {
                this.n = 0;
            }
            this.kH.a(this.kI, this, z, i2, str);
        }
    }
}
