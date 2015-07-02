package com.miui.yellowpage.activity;

import com.miui.yellowpage.utils.o;

/* compiled from: QuickYellowPageActivity */
class K implements Runnable {
    final /* synthetic */ QuickYellowPageActivity bU;

    K(QuickYellowPageActivity quickYellowPageActivity) {
        this.bU = quickYellowPageActivity;
    }

    public void run() {
        if (o.a(this.bU, this.bU.HM)) {
            this.bU.runOnUiThread(new Y(this));
        } else {
            this.bU.runOnUiThread(new Z(this));
        }
    }
}
