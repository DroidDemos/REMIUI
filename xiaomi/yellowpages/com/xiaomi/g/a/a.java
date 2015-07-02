package com.xiaomi.g.a;

import com.xiaomi.d.c.g;
import com.xiaomi.d.o;
import com.xiaomi.f.a.c.b;
import java.util.Date;

class a implements o {
    final /* synthetic */ e ug;

    a(e eVar) {
        this.ug = eVar;
    }

    public void a(g gVar) {
        if (e.a) {
            b.b("SMACK " + this.ug.ul.format(new Date()) + " RCV PKT (" + this.ug.um.hashCode() + "): " + gVar.a());
        }
    }
}
