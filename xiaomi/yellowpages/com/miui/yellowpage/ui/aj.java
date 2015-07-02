package com.miui.yellowpage.ui;

import com.miui.yellowpage.request.BaseResult;
import java.util.ArrayList;

/* compiled from: FavoriteYellowPageFragment */
class aj extends BaseResult {
    public ArrayList hR;
    final /* synthetic */ bc mE;

    private aj(bc bcVar) {
        this.mE = bcVar;
    }

    public boolean hasData() {
        return this.hR != null && this.hR.size() > 0;
    }

    public BaseResult shallowClone() {
        BaseResult ajVar = new aj(this.mE);
        ajVar.hR = this.hR;
        return ajVar;
    }
}
