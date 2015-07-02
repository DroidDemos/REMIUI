package com.miui.yellowpage.ui;

import android.text.TextUtils;
import com.miui.yellowpage.request.BaseResult;

/* compiled from: ExpressInquiryProgressFragment */
class dG extends BaseResult {
    String JQ;
    final /* synthetic */ ExpressInquiryProgressFragment mY;

    private dG(ExpressInquiryProgressFragment expressInquiryProgressFragment) {
        this.mY = expressInquiryProgressFragment;
    }

    public boolean hasData() {
        return !TextUtils.isEmpty(this.JQ);
    }

    public BaseResult shallowClone() {
        BaseResult dGVar = new dG(this.mY);
        dGVar.JQ = this.JQ;
        return dGVar;
    }
}
