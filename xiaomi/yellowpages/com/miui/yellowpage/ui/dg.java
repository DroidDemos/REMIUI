package com.miui.yellowpage.ui;

import com.miui.yellowpage.request.BaseResult;
import java.util.ArrayList;

/* compiled from: YellowPagePhonesFragment */
class dg extends BaseResult {
    final /* synthetic */ ap Dy;
    ArrayList list;

    private dg(ap apVar) {
        this.Dy = apVar;
    }

    public boolean hasData() {
        return this.list != null && this.list.size() > 0;
    }

    public BaseResult shallowClone() {
        BaseResult dgVar = new dg(this.Dy);
        dgVar.list = this.list;
        return dgVar;
    }
}
