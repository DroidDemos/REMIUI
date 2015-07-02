package com.xiaomi.push.service;

class a extends b {
    final /* synthetic */ XMPushService cf;

    public a(XMPushService xMPushService) {
        this.cf = xMPushService;
        super(5);
    }

    public void a() {
        this.cf.MV.quit();
    }

    public String b() {
        return "ask the job queue to quit";
    }
}
