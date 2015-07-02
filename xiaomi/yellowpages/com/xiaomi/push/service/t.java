package com.xiaomi.push.service;

class t extends b {
    final /* synthetic */ XMPushService cf;

    t(XMPushService xMPushService, int i) {
        this.cf = xMPushService;
        super(i);
    }

    public void a() {
        this.cf.b(18, null);
    }

    public String b() {
        return "disconnect because of connecting timeout";
    }
}
