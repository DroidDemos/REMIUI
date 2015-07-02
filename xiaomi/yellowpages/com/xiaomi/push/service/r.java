package com.xiaomi.push.service;

import com.xiaomi.push.service.q.c;

class r implements n {
    final /* synthetic */ XMPushService cf;

    r(XMPushService xMPushService) {
        this.cf = xMPushService;
    }

    public void a(c cVar, c cVar2, int i) {
        if (cVar2 == c.binded) {
            L.b(this.cf);
            L.c(this.cf);
        } else if (cVar2 == c.unbind) {
            L.a(this.cf, 70000001, " the push is not connected.");
        }
    }
}
