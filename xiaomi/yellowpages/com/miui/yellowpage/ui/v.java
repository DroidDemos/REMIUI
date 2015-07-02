package com.miui.yellowpage.ui;

import android.support.v4.view.af;
import com.ta.utdid2.core.persistent.TransactionXMLFile;

/* compiled from: BannerView */
class v implements af {
    final /* synthetic */ BannerView fI;

    v(BannerView bannerView) {
        this.fI = bannerView;
    }

    public void onPageScrolled(int i, float f, int i2) {
    }

    public void onPageSelected(int i) {
        this.fI.Is.x(i);
    }

    public void onPageScrollStateChanged(int i) {
        switch (i) {
            case TransactionXMLFile.MODE_PRIVATE /*0*/:
                if (this.fI.Iw) {
                    this.fI.Iw = false;
                    this.fI.hU();
                    return;
                }
                return;
            case TransactionXMLFile.MODE_WORLD_READABLE /*1*/:
                this.fI.Iw = true;
                this.fI.hU();
                return;
            default:
                return;
        }
    }
}
