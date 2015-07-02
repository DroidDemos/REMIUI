package com.miui.yellowpage.ui;

import com.miui.yellowpage.utils.s;

/* compiled from: ExpressInquiryProgressListItem */
class bm implements s {
    final /* synthetic */ String vq;
    final /* synthetic */ ExpressInquiryProgressListItem vr;

    bm(ExpressInquiryProgressListItem expressInquiryProgressListItem, String str) {
        this.vr = expressInquiryProgressListItem;
        this.vq = str;
    }

    public void onClick() {
        this.vr.cG(this.vq);
    }
}
