package com.miui.yellowpage.ui;

import com.miui.yellowpage.request.BaseResult;
import java.util.ArrayList;

/* compiled from: ExpressAddressListFragment */
class aS extends BaseResult {
    final /* synthetic */ ExpressAddressListFragment ep;
    ArrayList rS;

    private aS(ExpressAddressListFragment expressAddressListFragment) {
        this.ep = expressAddressListFragment;
    }

    public boolean hasData() {
        return true;
    }

    public BaseResult shallowClone() {
        BaseResult aSVar = new aS(this.ep);
        aSVar.rS = this.rS;
        return aSVar;
    }
}
