package com.miui.yellowpage.ui;

import com.miui.yellowpage.request.BaseResult;
import java.util.ArrayList;

/* compiled from: ExpressCompanyListBaseFragment */
public class dU extends BaseResult {
    final /* synthetic */ i kR;
    ArrayList list;

    protected dU(i iVar) {
        this.kR = iVar;
    }

    public boolean hasData() {
        return this.list != null && this.list.size() > 0;
    }

    public BaseResult shallowClone() {
        BaseResult dUVar = new dU(this.kR);
        dUVar.list = this.list;
        return dUVar;
    }
}
