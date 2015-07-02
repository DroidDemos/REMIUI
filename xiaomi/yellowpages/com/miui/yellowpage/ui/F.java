package com.miui.yellowpage.ui;

import com.miui.yellowpage.request.BaseResult;
import java.util.ArrayList;

/* compiled from: RecentYellowPageFragment */
class F extends BaseResult {
    public ArrayList hR;
    final /* synthetic */ cm hS;

    private F(cm cmVar) {
        this.hS = cmVar;
    }

    public boolean hasData() {
        return this.hR != null && this.hR.size() > 0;
    }

    public BaseResult shallowClone() {
        BaseResult f = new F(this.hS);
        f.hR = this.hR;
        return f;
    }
}
