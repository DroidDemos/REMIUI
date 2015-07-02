package com.miui.yellowpage.ui;

import com.miui.yellowpage.model.j;
import com.miui.yellowpage.request.BaseResult;

/* compiled from: RechargeOrderDetailFragment */
class k extends BaseResult {
    public j dy;
    final /* synthetic */ af dz;

    private k(af afVar) {
        this.dz = afVar;
    }

    public boolean hasData() {
        return this.dy != null;
    }

    public BaseResult shallowClone() {
        BaseResult kVar = new k(this.dz);
        kVar.dy = this.dy;
        return kVar;
    }
}
