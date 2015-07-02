package com.miui.yellowpage.activity;

import com.miui.yellowpage.base.model.YellowPage;
import com.miui.yellowpage.request.BaseResult;

/* compiled from: QuickYellowPageActivity */
class as extends BaseResult {
    YellowPage Cd;
    final /* synthetic */ QuickYellowPageActivity bU;

    private as(QuickYellowPageActivity quickYellowPageActivity) {
        this.bU = quickYellowPageActivity;
    }

    public boolean hasData() {
        return this.Cd != null;
    }

    public BaseResult shallowClone() {
        BaseResult asVar = new as(this.bU);
        asVar.Cd = this.Cd;
        return asVar;
    }
}
