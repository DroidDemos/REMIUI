package com.miui.yellowpage.business.recharge;

import android.text.TextUtils;
import com.miui.yellowpage.model.i;
import com.miui.yellowpage.request.BaseResult;

/* compiled from: RechargeFragment */
class F extends BaseResult {
    final /* synthetic */ D dw;
    public String lB;
    public boolean lC;
    public String lD;
    public String message;
    public i nn;

    private F(D d) {
        this.dw = d;
    }

    public boolean hasData() {
        return !TextUtils.isEmpty(this.lB);
    }

    public BaseResult shallowClone() {
        BaseResult f = new F(this.dw);
        f.lB = this.lB;
        f.lC = this.lC;
        f.lD = this.lD;
        f.nn = this.nn;
        return f;
    }
}
