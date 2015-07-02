package com.miui.yellowpage.ui;

import android.content.Context;
import android.view.animation.Interpolator;
import android.widget.Scroller;

/* compiled from: BannerView */
class cI extends Scroller {
    final /* synthetic */ BannerView fI;

    public cI(BannerView bannerView, Context context, Interpolator interpolator) {
        this.fI = bannerView;
        super(context, interpolator);
    }

    public void startScroll(int i, int i2, int i3, int i4) {
        super.startScroll(i, i2, i3, i4, this.fI.Iy);
    }

    public void startScroll(int i, int i2, int i3, int i4, int i5) {
        super.startScroll(i, i2, i3, i4, this.fI.Iy);
    }
}
