package com.miui.yellowpage.activity;

import miui.yellowpage.YellowPageUtils;

/* compiled from: MarkNumberActivity */
class ag implements Runnable {
    final /* synthetic */ String zQ;
    final /* synthetic */ P zR;

    ag(P p, String str) {
        this.zR = p;
        this.zQ = str;
    }

    public void run() {
        YellowPageUtils.markAntiSpam(this.zR.uE.rR, this.zR.uE.rR.mNumber, YellowPageUtils.createAntispamCategory(this.zR.uE.rR, this.zQ), false);
    }
}
