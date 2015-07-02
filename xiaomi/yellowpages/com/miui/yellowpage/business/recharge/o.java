package com.miui.yellowpage.business.recharge;

/* compiled from: RechargeFragment */
class o implements Runnable {
    final /* synthetic */ D dw;

    o(D d) {
        this.dw = d;
    }

    public void run() {
        if (this.dw.qA.isEmpty()) {
            this.dw.b(this.dw.qA);
            this.dw.mActivity.runOnUiThread(new y(this));
        }
    }
}
