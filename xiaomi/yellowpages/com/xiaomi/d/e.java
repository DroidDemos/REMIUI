package com.xiaomi.d;

import com.xiaomi.b.a.L;
import com.xiaomi.b.a.w;

class e implements L {
    final /* synthetic */ j Af;

    private e(j jVar) {
        this.Af = jVar;
    }

    public void a(w wVar) {
        if (!wVar.a()) {
            this.Af.a(2, 0, null);
            this.Af.a((Exception) wVar.cK());
        }
        synchronized (this.Af.FO) {
            this.Af.FO.notifyAll();
        }
    }
}
