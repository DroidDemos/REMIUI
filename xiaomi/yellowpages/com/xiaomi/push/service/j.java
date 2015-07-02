package com.xiaomi.push.service;

class j extends b {
    final /* synthetic */ XMPushService cf;

    j(XMPushService xMPushService) {
        this.cf = xMPushService;
        super(3);
    }

    public void a() {
        this.cf.b(11, null);
        if (this.cf.c()) {
            this.cf.ao();
        }
    }

    public String b() {
        return "reset the connection.";
    }
}
