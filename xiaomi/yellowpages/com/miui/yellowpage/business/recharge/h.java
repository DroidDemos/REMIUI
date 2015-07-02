package com.miui.yellowpage.business.recharge;

import com.miui.yellowpage.base.utils.Sim;

/* compiled from: RechargeFragment */
class h implements Runnable {
    final /* synthetic */ D dw;
    final /* synthetic */ int mw;

    h(D d, int i) {
        this.dw = d;
        this.mw = i;
    }

    public void run() {
        this.dw.mActivity.runOnUiThread(new d(this, Sim.getPhoneNumber(this.dw.mActivity, this.mw)));
    }
}
