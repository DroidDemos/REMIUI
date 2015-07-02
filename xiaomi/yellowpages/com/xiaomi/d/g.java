package com.xiaomi.d;

import com.xiaomi.b.a.G;
import com.xiaomi.b.a.ab;

class g implements G {
    final /* synthetic */ j Af;

    g(j jVar) {
        this.Af = jVar;
    }

    public boolean b(ab abVar) {
        if (abVar.cf() != null) {
            try {
                this.Af.FM.write(abVar.cf().b());
                this.Af.FM.flush();
            } catch (Exception e) {
            }
        }
        return false;
    }
}
