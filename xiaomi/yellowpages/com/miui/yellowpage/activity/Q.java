package com.miui.yellowpage.activity;

import miui.yellowpage.YellowPageUtils;

/* compiled from: MarkNumberActivity */
class Q implements Runnable {
    final /* synthetic */ B uE;
    final /* synthetic */ int uF;

    Q(B b, int i) {
        this.uE = b;
        this.uF = i;
    }

    public void run() {
        YellowPageUtils.markAntiSpam(this.uE.rR, this.uE.rR.mNumber, this.uF, false);
    }
}
