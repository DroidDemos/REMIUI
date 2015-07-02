package com.miui.yellowpage.ui;

import com.miui.yellowpage.request.BaseResult;

/* compiled from: ExpressInquiryProgressFragment */
class an extends BaseResult {
    String mDescription;
    boolean mResult;
    final /* synthetic */ ExpressInquiryProgressFragment mY;

    private an(ExpressInquiryProgressFragment expressInquiryProgressFragment) {
        this.mY = expressInquiryProgressFragment;
    }

    public boolean hasData() {
        return true;
    }

    public BaseResult shallowClone() {
        BaseResult anVar = new an(this.mY);
        anVar.mResult = this.mResult;
        anVar.mDescription = this.mDescription;
        return anVar;
    }
}
