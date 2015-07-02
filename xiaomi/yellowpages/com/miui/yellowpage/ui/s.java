package com.miui.yellowpage.ui;

/* compiled from: BannerView */
class s implements Runnable {
    final /* synthetic */ BannerView fI;

    s(BannerView bannerView) {
        this.fI = bannerView;
    }

    public void run() {
        this.fI.hX();
        this.fI.mHandler.postDelayed(this.fI.Iz, this.fI.Ix);
    }
}
