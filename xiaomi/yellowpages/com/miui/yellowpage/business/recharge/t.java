package com.miui.yellowpage.business.recharge;

import com.miui.yellowpage.model.i;
import com.miui.yellowpage.request.BaseResult;

/* compiled from: RechargeOrderListFragment */
public class t extends BaseResult {
    final /* synthetic */ N eL;
    public String lD;
    public String nm;
    public i nn;

    public t(N n) {
        this.eL = n;
    }

    public boolean hasData() {
        return (this.lD == null || this.nm == null || this.nn == null || !this.nn.isValid()) ? false : true;
    }

    public BaseResult shallowClone() {
        BaseResult tVar = new t(this.eL);
        tVar.nn = this.nn;
        tVar.lD = this.lD;
        tVar.nm = this.nm;
        return tVar;
    }
}
