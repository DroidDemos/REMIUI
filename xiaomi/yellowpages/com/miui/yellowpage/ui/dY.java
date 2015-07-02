package com.miui.yellowpage.ui;

/* compiled from: RechargeOrderListFragment */
class dY implements Runnable {
    final /* synthetic */ dR lL;

    dY(dR dRVar) {
        this.lL = dRVar;
    }

    public void run() {
        if (this.lL.mLoader != null && !this.lL.mLoader.iI()) {
            this.lL.mLoader.forceLoad();
        }
    }
}
