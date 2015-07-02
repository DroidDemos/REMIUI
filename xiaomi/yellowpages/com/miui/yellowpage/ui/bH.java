package com.miui.yellowpage.ui;

import com.miui.yellowpage.model.g;
import miui.yellowpage.YellowPageContract.Cache;

/* compiled from: ExpressInquiryProgressFragment */
class bH implements Runnable {
    final /* synthetic */ ExpressInquiryProgressFragment mY;
    final /* synthetic */ g zc;

    bH(ExpressInquiryProgressFragment expressInquiryProgressFragment, g gVar) {
        this.mY = expressInquiryProgressFragment;
        this.zc = gVar;
    }

    public void run() {
        this.mY.JP.getContentResolver().delete(Cache.CONTENT_URI, "cache_key=?", new String[]{this.zc.getKey()});
    }
}
