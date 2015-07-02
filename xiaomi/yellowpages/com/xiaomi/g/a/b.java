package com.xiaomi.g.a;

import com.xiaomi.d.u;
import java.util.Date;

class b implements u {
    final /* synthetic */ e ug;

    b(e eVar) {
        this.ug = eVar;
    }

    public void a() {
        com.xiaomi.f.a.c.b.b("SMACK " + this.ug.ul.format(new Date()) + " Connection reconnected (" + this.ug.um.hashCode() + ")");
    }

    public void a(int i, Exception exception) {
        com.xiaomi.f.a.c.b.b("SMACK " + this.ug.ul.format(new Date()) + " Connection closed (" + this.ug.um.hashCode() + ")");
    }

    public void a(Exception exception) {
        com.xiaomi.f.a.c.b.b("SMACK " + this.ug.ul.format(new Date()) + " Reconnection failed due to an exception (" + this.ug.um.hashCode() + ")");
        exception.printStackTrace();
    }

    public void b() {
        com.xiaomi.f.a.c.b.b("SMACK " + this.ug.ul.format(new Date()) + " Connection started (" + this.ug.um.hashCode() + ")");
    }
}
