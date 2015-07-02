package com.miui.yellowpage.business.recharge;

import com.miui.yellowpage.request.BaseResult;
import java.util.ArrayList;

/* compiled from: RechargeOrderListFragment */
class c extends BaseResult {
    public ArrayList eK;
    final /* synthetic */ N eL;

    private c(N n) {
        this.eL = n;
    }

    public boolean hasData() {
        return getCount() > 0;
    }

    public int getCount() {
        return this.eK == null ? 0 : this.eK.size();
    }

    public BaseResult shallowClone() {
        BaseResult cVar = new c(this.eL);
        cVar.eK = this.eK;
        return cVar;
    }
}
