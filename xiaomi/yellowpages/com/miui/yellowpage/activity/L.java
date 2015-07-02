package com.miui.yellowpage.activity;

/* compiled from: QuickYellowPageActivity */
class L implements Runnable {
    final /* synthetic */ QuickYellowPageActivity bU;

    L(QuickYellowPageActivity quickYellowPageActivity) {
        this.bU = quickYellowPageActivity;
    }

    public void run() {
        this.bU.finish();
    }
}
