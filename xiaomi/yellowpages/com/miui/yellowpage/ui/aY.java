package com.miui.yellowpage.ui;

import com.miui.yellowpage.request.BaseResult;

/* compiled from: PaymentResultFragment */
class aY extends BaseResult {
    final /* synthetic */ aq fy;
    private int tH;
    private String tI;
    private String title;

    private aY(aq aqVar) {
        this.fy = aqVar;
        this.tH = -1;
    }

    public boolean hasData() {
        return this.tH != -1;
    }

    public BaseResult shallowClone() {
        BaseResult aYVar = new aY(this.fy);
        aYVar.tH = this.tH;
        return aYVar;
    }
}
