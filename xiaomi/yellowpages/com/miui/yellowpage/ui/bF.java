package com.miui.yellowpage.ui;

import com.miui.yellowpage.request.BaseResult;

/* compiled from: ExpressInquiryProgressFragment */
class bF extends BaseResult {
    String mDescription;
    boolean mResult;
    int mStatus;
    final /* synthetic */ ExpressInquiryProgressFragment mY;

    private bF(ExpressInquiryProgressFragment expressInquiryProgressFragment) {
        this.mY = expressInquiryProgressFragment;
    }

    public boolean hasData() {
        return true;
    }

    public BaseResult shallowClone() {
        BaseResult bFVar = new bF(this.mY);
        bFVar.mResult = this.mResult;
        bFVar.mDescription = this.mDescription;
        bFVar.mStatus = this.mStatus;
        return bFVar;
    }
}
