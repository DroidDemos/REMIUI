package com.miui.yellowpage.ui;

import com.miui.yellowpage.request.BaseResult;
import java.util.ArrayList;

/* compiled from: OrderFragment */
class dl extends BaseResult {
    public ArrayList Ic;
    final /* synthetic */ OrderFragment nh;

    private dl(OrderFragment orderFragment) {
        this.nh = orderFragment;
    }

    public boolean hasData() {
        return this.Ic != null && this.Ic.size() > 0;
    }

    public int getCount() {
        return hasData() ? this.Ic.size() : 0;
    }

    public BaseResult shallowClone() {
        BaseResult dlVar = new dl(this.nh);
        dlVar.Ic = this.Ic;
        return dlVar;
    }
}
