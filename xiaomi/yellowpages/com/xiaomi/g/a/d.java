package com.xiaomi.g.a;

import com.xiaomi.d.e.h;
import com.xiaomi.f.a.c.b;
import java.util.Date;

class d implements h {
    final /* synthetic */ e ug;

    d(e eVar) {
        this.ug = eVar;
    }

    public void a(String str) {
        b.b("SMACK " + this.ug.ul.format(new Date()) + " SENT (" + this.ug.um.hashCode() + "): " + str);
    }
}
