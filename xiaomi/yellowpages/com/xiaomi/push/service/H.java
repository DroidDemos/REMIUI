package com.xiaomi.push.service;

import com.alipay.mobilesecuritysdk.constant.ConfigConstant;
import com.alipay.sdk.data.Response;
import com.xiaomi.f.a.c.b;

public class H {
    private static int e;
    private int b;
    private XMPushService cf;
    private int d;
    private long vG;

    static {
        e = 300;
    }

    public H(XMPushService xMPushService) {
        this.d = 0;
        this.cf = xMPushService;
        this.b = 10;
        this.vG = 0;
    }

    private int ah() {
        int i = 40;
        if (this.d > 13) {
            return 300;
        }
        if (this.d > 7) {
            return 60;
        }
        if (this.d >= 1) {
            return 10;
        }
        if (this.vG == 0) {
            return 0;
        }
        long currentTimeMillis = System.currentTimeMillis() - this.vG;
        if (currentTimeMillis < ConfigConstant.REQUEST_LOCATE_INTERVAL) {
            if (this.b >= e) {
                return this.b;
            }
            i = this.b;
            this.b = (int) (1.5d * ((double) this.b));
            return i;
        } else if (currentTimeMillis < 900000) {
            if (this.b < 40) {
                i = this.b;
            }
            this.b = i;
            return this.b;
        } else if (currentTimeMillis < 1800000) {
            this.b = this.b < 20 ? this.b : 20;
            return this.b;
        } else {
            this.b = 10;
            return this.b;
        }
    }

    public void a() {
        this.vG = System.currentTimeMillis();
        this.cf.a(1);
        this.d = 0;
    }

    public void a(boolean z) {
        if (!this.cf.c()) {
            b.b("should not reconnect as no client or network.");
        } else if (z) {
            this.cf.a(1);
            XMPushService xMPushService = this.cf;
            r1 = this.cf;
            r1.getClass();
            xMPushService.a(new d(r1));
            this.d++;
        } else if (!this.cf.I(1)) {
            int ah = ah();
            b.a("schedule reconnect in " + ah + "s");
            r1 = this.cf;
            XMPushService xMPushService2 = this.cf;
            xMPushService2.getClass();
            r1.a(new d(xMPushService2), (long) (ah * Response.a));
            this.d++;
        }
    }
}
