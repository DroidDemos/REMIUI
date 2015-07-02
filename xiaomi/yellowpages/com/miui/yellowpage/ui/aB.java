package com.miui.yellowpage.ui;

import com.miui.yellowpage.base.utils.Sim;

/* compiled from: RechargeFragment */
class aB implements Runnable {
    final /* synthetic */ aL ie;
    final /* synthetic */ int mw;

    aB(aL aLVar, int i) {
        this.ie = aLVar;
        this.mw = i;
    }

    public void run() {
        this.ie.mActivity.runOnUiThread(new bj(this, Sim.getPhoneNumber(this.ie.mActivity, this.mw)));
    }
}
