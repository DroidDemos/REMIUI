package com.xiaomi.d;

import com.xiaomi.d.c.g;

class d implements Runnable {
    final /* synthetic */ j Af;
    private g eM;

    public d(j jVar, g gVar) {
        this.Af = jVar;
        this.eM = gVar;
    }

    public void run() {
        for (a a : this.Af.ev.values()) {
            a.a(this.eM);
        }
    }
}
