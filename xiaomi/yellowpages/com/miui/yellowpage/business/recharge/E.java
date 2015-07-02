package com.miui.yellowpage.business.recharge;

import com.miui.yellowpage.request.BaseResult;
import java.util.ArrayList;

/* compiled from: RechargeFragment */
class E extends BaseResult {
    public ArrayList DO;
    final /* synthetic */ D dw;

    private E(D d) {
        this.dw = d;
    }

    public boolean hasData() {
        return this.DO != null && this.DO.size() > 0;
    }

    public BaseResult shallowClone() {
        BaseResult e = new E(this.dw);
        e.DO = this.DO;
        return e;
    }
}
