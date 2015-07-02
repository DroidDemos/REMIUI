package com.xiaomi.push.service;

public class c extends b {
    public int a;
    public Exception ch;
    final /* synthetic */ XMPushService ci;

    c(XMPushService xMPushService, int i, Exception exception) {
        this.ci = xMPushService;
        super(2);
        this.a = i;
        this.ch = exception;
    }

    public void a() {
        this.ci.b(this.a, this.ch);
    }

    public String b() {
        return "disconnect the connection.";
    }
}
