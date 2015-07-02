package com.google.android.gms.internal;

import java.util.List;

@fd
public class aq {
    private final Object mK;
    private int nC;
    private List<ap> nD;

    public boolean a(ap apVar) {
        boolean z;
        synchronized (this.mK) {
            if (this.nD.contains(apVar)) {
                z = true;
            } else {
                z = false;
            }
        }
        return z;
    }

    public boolean b(ap apVar) {
        boolean z;
        synchronized (this.mK) {
            for (ap apVar2 : this.nD) {
                if (apVar != apVar2 && apVar2.aT().equals(apVar.aT())) {
                    this.nD.remove(apVar);
                    z = true;
                    break;
                }
            }
            z = false;
        }
        return z;
    }

    public void c(ap apVar) {
        synchronized (this.mK) {
            if (this.nD.size() >= 10) {
                gw.d("Queue is full, current size = " + this.nD.size());
                this.nD.remove(0);
            }
            int i = this.nC;
            this.nC = i + 1;
            apVar.c(i);
            this.nD.add(apVar);
        }
    }
}
