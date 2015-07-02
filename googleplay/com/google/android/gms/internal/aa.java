package com.google.android.gms.internal;

import android.os.Bundle;

@fd
public class aa {
    private a mn;
    private boolean mo;
    private boolean mp;

    public interface a {
        void g(String str);
    }

    public aa() {
        boolean z = false;
        Bundle bN = gf.bN();
        if (bN != null && bN.getBoolean("gads:block_autoclicks", false)) {
            z = true;
        }
        this.mp = z;
    }

    public aa(boolean z) {
        this.mp = z;
    }

    public boolean az() {
        return !this.mp || this.mo;
    }

    public void f(String str) {
        gw.d("Action was blocked because no click was detected.");
        if (this.mn != null) {
            this.mn.g(str);
        }
    }
}
