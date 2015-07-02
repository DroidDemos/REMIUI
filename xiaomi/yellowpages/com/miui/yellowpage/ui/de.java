package com.miui.yellowpage.ui;

/* compiled from: NearbyYellowPageFragment */
class de implements Runnable {
    final /* synthetic */ db cg;

    de(db dbVar) {
        this.cg = dbVar;
    }

    public void run() {
        this.cg.hD();
        if (this.cg.isAdded()) {
            this.cg.mActivity.runOnUiThread(new av(this));
        }
    }
}
