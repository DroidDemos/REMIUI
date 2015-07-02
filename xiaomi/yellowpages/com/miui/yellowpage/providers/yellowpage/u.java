package com.miui.yellowpage.providers.yellowpage;

/* compiled from: YellowPageProvider */
class u implements Runnable {
    final /* synthetic */ YellowPageProvider Ib;

    u(YellowPageProvider yellowPageProvider) {
        this.Ib = yellowPageProvider;
    }

    public void run() {
        this.Ib.tb.getAreaCode();
    }
}
