package com.miui.yellowpage.ui;

/* compiled from: BannerView */
class t extends dT {
    final /* synthetic */ BannerView fI;

    t(BannerView bannerView) {
        this.fI = bannerView;
    }

    public void onPause() {
        this.fI.D(false);
    }

    public void onResume() {
        this.fI.D(true);
    }
}
