package com.miui.yellowpage.ui;

/* compiled from: RechargeFragment */
class aA implements Runnable {
    final /* synthetic */ aL ie;

    aA(aL aLVar) {
        this.ie = aLVar;
    }

    public void run() {
        if (this.ie.qA.isEmpty()) {
            this.ie.b(this.ie.qA);
            this.ie.mActivity.runOnUiThread(new bK(this));
        }
    }
}
