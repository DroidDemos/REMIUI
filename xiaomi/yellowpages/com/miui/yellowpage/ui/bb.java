package com.miui.yellowpage.ui;

import com.miui.yellowpage.request.BaseResult;

/* compiled from: RechargeOrderListFragment */
public class bb extends BaseResult {
    public String key;
    public String lD;
    final /* synthetic */ dR lL;
    public String nm;

    public bb(dR dRVar) {
        this.lL = dRVar;
    }

    public boolean hasData() {
        return (this.key == null || this.lD == null || this.nm == null) ? false : true;
    }

    public BaseResult shallowClone() {
        BaseResult bbVar = new bb(this.lL);
        bbVar.key = this.key;
        bbVar.lD = this.lD;
        bbVar.nm = this.nm;
        return bbVar;
    }
}
