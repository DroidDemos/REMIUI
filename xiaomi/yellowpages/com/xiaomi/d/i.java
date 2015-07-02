package com.xiaomi.d;

import java.io.IOException;

class i extends Thread {
    final /* synthetic */ j Af;
    private Thread FG;
    private int c;

    i(j jVar) {
        this.Af = jVar;
        this.FG = this;
        this.c = 1024;
    }

    public void run() {
        try {
            char[] cArr = new char[this.c];
            while (this.Af.FN == this.FG && !this.Af.FK) {
                this.Af.Gc.read(cArr, 0, this.c);
            }
        } catch (IOException e) {
        }
    }
}
