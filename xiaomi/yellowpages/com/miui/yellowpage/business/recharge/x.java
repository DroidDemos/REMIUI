package com.miui.yellowpage.business.recharge;

import android.text.TextUtils;
import com.miui.yellowpage.request.BaseResult;

/* compiled from: RechargeFragment */
class x extends BaseResult {
    final /* synthetic */ D dw;
    private String mName;
    private String mNumber;

    public x(D d, String str) {
        this.dw = d;
        this.mNumber = str;
    }

    public boolean hasData() {
        return !TextUtils.isEmpty(this.mName);
    }

    public BaseResult shallowClone() {
        BaseResult xVar = new x(this.dw, this.mNumber);
        xVar.mName = this.mName;
        return xVar;
    }
}
