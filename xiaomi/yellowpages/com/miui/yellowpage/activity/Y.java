package com.miui.yellowpage.activity;

/* compiled from: QuickYellowPageActivity */
class Y implements Runnable {
    final /* synthetic */ K vb;

    Y(K k) {
        this.vb = k;
    }

    public void run() {
        this.vb.bU.HF.addView(this.vb.bU.b(this.vb.bU.HF));
        this.vb.bU.d(this.vb.bU.HM);
        this.vb.bU.C(true);
        this.vb.bU.hO();
        this.vb.bU.show();
    }
}
