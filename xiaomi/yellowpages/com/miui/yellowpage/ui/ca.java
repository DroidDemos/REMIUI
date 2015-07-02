package com.miui.yellowpage.ui;

import com.miui.yellowpage.base.model.YellowPage;
import com.miui.yellowpage.request.BaseResult;
import java.util.ArrayList;

/* compiled from: YellowPageFragment */
class ca extends BaseResult {
    public YellowPage Cd;
    public String Ce;
    public ArrayList Cf;
    final /* synthetic */ Y iu;

    private ca(Y y) {
        this.iu = y;
    }

    public boolean hasData() {
        return this.Cd != null;
    }

    public BaseResult shallowClone() {
        BaseResult caVar = new ca(this.iu);
        caVar.Cd = this.Cd;
        caVar.Cf = this.Cf;
        caVar.Ce = this.Ce;
        return caVar;
    }
}
