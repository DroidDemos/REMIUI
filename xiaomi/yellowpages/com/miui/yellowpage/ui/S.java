package com.miui.yellowpage.ui;

import com.miui.yellowpage.request.BaseResult;
import java.util.ArrayList;

/* compiled from: NearbyYellowPageFragment */
class S extends BaseResult {
    final /* synthetic */ db cg;
    public ArrayList iV;

    private S(db dbVar) {
        this.cg = dbVar;
    }

    public boolean hasData() {
        return getCount() > 0;
    }

    public int getCount() {
        return this.iV == null ? 0 : this.iV.size();
    }

    public BaseResult shallowClone() {
        BaseResult s = new S(this.cg);
        s.iV = this.iV;
        return s;
    }
}
