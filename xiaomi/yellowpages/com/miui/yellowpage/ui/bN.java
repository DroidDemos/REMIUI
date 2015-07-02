package com.miui.yellowpage.ui;

import com.miui.yellowpage.request.BaseResult;

/* compiled from: SendExpressResultFragment */
class bN extends BaseResult {
    String description;
    final /* synthetic */ dn ho;
    boolean zV;

    private bN(dn dnVar) {
        this.ho = dnVar;
        this.zV = false;
    }

    public boolean hasData() {
        return true;
    }

    public BaseResult shallowClone() {
        BaseResult bNVar = new bN(this.ho);
        bNVar.zV = this.zV;
        return bNVar;
    }
}
