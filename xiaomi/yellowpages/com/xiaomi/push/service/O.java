package com.xiaomi.push.service;

import android.text.TextUtils;
import com.xiaomi.e.a.a;
import com.xiaomi.e.a.c;
import com.xiaomi.e.a.e;
import com.xiaomi.e.a.h;
import com.xiaomi.f.a.c.b;
import org.apache.thrift.TBase;

final class O extends b {
    final /* synthetic */ XMPushService ci;
    final /* synthetic */ h wi;
    final /* synthetic */ c wj;

    O(int i, h hVar, c cVar, XMPushService xMPushService) {
        this.wi = hVar;
        this.wj = cVar;
        this.ci = xMPushService;
        super(i);
    }

    public void a() {
        try {
            TBase eVar = new e();
            eVar.bK(this.wi.h());
            eVar.bJ(this.wj.a());
            eVar.m(this.wj.c());
            if (!TextUtils.isEmpty(this.wj.e())) {
                eVar.bL(this.wj.e());
            }
            this.ci.f(M.a(this.wi, eVar, a.AckMessage));
        } catch (Exception e) {
            b.a((Throwable) e);
            this.ci.b(10, e);
        }
    }

    public String b() {
        return "send ack message for message.";
    }
}
