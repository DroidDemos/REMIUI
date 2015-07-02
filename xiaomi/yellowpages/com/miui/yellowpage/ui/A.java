package com.miui.yellowpage.ui;

import com.miui.yellowpage.request.BaseResult;

/* compiled from: ExpressOrderItemFragment */
class A extends BaseResult {
    final /* synthetic */ ExpressOrderItemFragment hJ;
    String mDescription;
    boolean mResult;

    private A(ExpressOrderItemFragment expressOrderItemFragment) {
        this.hJ = expressOrderItemFragment;
    }

    public boolean hasData() {
        return true;
    }

    public BaseResult shallowClone() {
        BaseResult a = new A(this.hJ);
        a.mResult = this.mResult;
        a.mDescription = this.mDescription;
        return a;
    }
}
