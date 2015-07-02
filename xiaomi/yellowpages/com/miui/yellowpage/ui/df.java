package com.miui.yellowpage.ui;

/* compiled from: NearbyYellowPageFragment */
class df implements Runnable {
    final /* synthetic */ db cg;

    df(db dbVar) {
        this.cg = dbVar;
    }

    public void run() {
        if (this.cg.GD != null && !this.cg.GD.iI()) {
            this.cg.GD.forceLoad();
        }
    }
}
