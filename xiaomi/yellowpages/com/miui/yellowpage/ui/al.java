package com.miui.yellowpage.ui;

import com.miui.yellowpage.model.q;
import com.miui.yellowpage.request.BaseResult;

/* compiled from: AccountFragment */
class al extends BaseResult {
    q mR;
    final /* synthetic */ aT mS;

    private al(aT aTVar) {
        this.mS = aTVar;
    }

    public boolean hasData() {
        return this.mR != null;
    }

    public BaseResult shallowClone() {
        BaseResult alVar = new al(this.mS);
        alVar.mR = this.mR;
        return alVar;
    }
}
