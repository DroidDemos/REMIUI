package com.miui.yellowpage.ui;

import com.miui.yellowpage.model.ExpressOrder;
import com.miui.yellowpage.request.BaseResult;

/* compiled from: ExpressOrderItemFragment */
class W extends BaseResult {
    final /* synthetic */ ExpressOrderItemFragment hJ;
    ExpressOrder kG;

    private W(ExpressOrderItemFragment expressOrderItemFragment) {
        this.hJ = expressOrderItemFragment;
    }

    public boolean hasData() {
        return this.kG != null;
    }

    public BaseResult shallowClone() {
        BaseResult w = new W(this.hJ);
        w.kG = this.kG;
        return w;
    }
}
