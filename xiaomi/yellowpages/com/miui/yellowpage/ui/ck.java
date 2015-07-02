package com.miui.yellowpage.ui;

import com.miui.yellowpage.request.BaseResult;
import java.util.ArrayList;

/* compiled from: RechargeOrderListFragment */
class ck extends BaseResult {
    public ArrayList eK;
    final /* synthetic */ dR lL;

    private ck(dR dRVar) {
        this.lL = dRVar;
    }

    public boolean hasData() {
        return getCount() > 0;
    }

    public int getCount() {
        return this.eK == null ? 0 : this.eK.size();
    }

    public BaseResult shallowClone() {
        BaseResult ckVar = new ck(this.lL);
        ckVar.eK = this.eK;
        return ckVar;
    }
}
