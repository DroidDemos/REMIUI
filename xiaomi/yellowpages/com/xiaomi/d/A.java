package com.xiaomi.d;

import com.xiaomi.push.service.b;

class A extends b {
    final /* synthetic */ x Je;
    final /* synthetic */ int a;
    final /* synthetic */ Exception ch;

    A(x xVar, int i, int i2, Exception exception) {
        this.Je = xVar;
        this.a = i2;
        this.ch = exception;
        super(i);
    }

    public void a() {
        this.Je.IU.b(this.a, this.ch);
    }

    public String b() {
        return "shutdown the connection. " + this.a + ", " + this.ch;
    }
}
