package com.miui.yellowpage.ui;

import com.miui.yellowpage.request.BaseResult;
import java.util.ArrayList;

/* compiled from: RechargeFragment */
class cY extends BaseResult {
    public ArrayList DO;
    final /* synthetic */ aL ie;

    private cY(aL aLVar) {
        this.ie = aLVar;
    }

    public boolean hasData() {
        return this.DO != null && this.DO.size() > 0;
    }

    public BaseResult shallowClone() {
        BaseResult cYVar = new cY(this.ie);
        cYVar.DO = this.DO;
        return cYVar;
    }
}
