package com.miui.yellowpage.ui;

import com.miui.yellowpage.request.BaseResult;

/* compiled from: RechargeOrderDetailFragment */
class aK extends BaseResult {
    final /* synthetic */ af dz;
    public String key;
    public String lD;
    public String nm;

    private aK(af afVar) {
        this.dz = afVar;
    }

    public boolean hasData() {
        return (this.key == null || this.lD == null || this.nm == null) ? false : true;
    }

    public BaseResult shallowClone() {
        BaseResult aKVar = new aK(this.dz);
        aKVar.key = this.key;
        aKVar.lD = this.lD;
        aKVar.nm = this.nm;
        return aKVar;
    }
}
