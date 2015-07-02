package com.miui.yellowpage.business.recharge;

/* compiled from: RechargeFragment */
class d implements Runnable {
    final /* synthetic */ h gb;
    final /* synthetic */ String val$number;

    d(h hVar, String str) {
        this.gb = hVar;
        this.val$number = str;
    }

    public void run() {
        this.gb.dw.qo.setText(this.val$number);
    }
}
