package com.miui.yellowpage.ui;

/* compiled from: RechargeFragment */
class bj implements Runnable {
    final /* synthetic */ aB uZ;
    final /* synthetic */ String val$number;

    bj(aB aBVar, String str) {
        this.uZ = aBVar;
        this.val$number = str;
    }

    public void run() {
        this.uZ.ie.qo.setText(this.val$number);
    }
}
