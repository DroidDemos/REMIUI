package com.miui.yellowpage.business.recharge;

/* compiled from: RechargeOrderListFragment */
class B implements Runnable {
    final /* synthetic */ N eL;

    B(N n) {
        this.eL = n;
    }

    public void run() {
        if (this.eL.mLoader != null && !this.eL.mLoader.iI()) {
            this.eL.mLoader.forceLoad();
        }
    }
}
