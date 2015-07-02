package com.miui.yellowpage.ui;

import com.miui.yellowpage.base.model.FlowOfPackages;
import com.miui.yellowpage.request.BaseResult;

/* compiled from: FlowOfPackageFragment */
class n extends BaseResult {
    public FlowOfPackages fa;
    final /* synthetic */ cf fb;

    private n(cf cfVar) {
        this.fb = cfVar;
    }

    public boolean hasData() {
        return this.fa != null;
    }

    public BaseResult shallowClone() {
        BaseResult nVar = new n(this.fb);
        nVar.fa = this.fa;
        return nVar;
    }
}
