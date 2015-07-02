package com.xiaomi.push.service;

class g extends b {
    final /* synthetic */ XMPushService cf;
    private com.xiaomi.d.c.g eM;

    public g(XMPushService xMPushService, com.xiaomi.d.c.g gVar) {
        this.cf = xMPushService;
        super(8);
        this.eM = null;
        this.eM = gVar;
    }

    public void a() {
        this.cf.MT.a(this.eM);
    }

    public String b() {
        return "receive a message.";
    }
}
