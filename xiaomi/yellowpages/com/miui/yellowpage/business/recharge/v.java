package com.miui.yellowpage.business.recharge;

import com.miui.yellowpage.request.BaseResult;

/* compiled from: PaymentResultFragment */
class v extends BaseResult {
    final /* synthetic */ C cX;
    private int tH;
    private String tI;
    private String title;

    private v(C c) {
        this.cX = c;
        this.tH = -1;
    }

    public boolean hasData() {
        return this.tH != -1;
    }

    public BaseResult shallowClone() {
        BaseResult vVar = new v(this.cX);
        vVar.tH = this.tH;
        return vVar;
    }
}
