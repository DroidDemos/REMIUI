package com.miui.yellowpage.utils.antispam.dataprocessor;

import java.math.BigInteger;

/* compiled from: Reader */
class g {
    private BigInteger rw;
    private BigInteger rx;
    private int ry;
    final /* synthetic */ d this$0;

    public g(d dVar, BigInteger bigInteger) {
        this.this$0 = dVar;
        this.rw = bigInteger;
        this.rx = null;
        this.ry = -2;
    }

    public BigInteger dy() {
        return this.rx;
    }

    public int getPageIndex() {
        return this.ry;
    }

    public g dz() {
        int i = 0;
        while (i < d.kZ.size()) {
            int compareTo = this.rw.compareTo((BigInteger) d.kZ.get(i));
            if (compareTo == -1) {
                if (i == 0) {
                    this.ry = -1;
                } else {
                    this.ry = i - 1;
                    this.rx = (BigInteger) d.kZ.get(i - 1);
                }
            } else if (compareTo == 0) {
                this.ry = i;
                this.rx = (BigInteger) d.kZ.get(i);
                break;
            } else {
                i++;
            }
        }
        if (this.ry == -2) {
            this.ry = d.kZ.size() - 1;
            this.rx = (BigInteger) d.kZ.get(d.kZ.size() - 1);
        }
        return this;
    }
}
