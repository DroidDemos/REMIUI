package com.miui.yellowpage.ui;

import android.text.TextUtils;
import com.miui.yellowpage.request.BaseResult;

/* compiled from: RechargeFragment */
class Z extends BaseResult {
    final /* synthetic */ aL ie;
    public String lB;
    public boolean lC;
    public String lD;
    public String lE;
    public String message;

    private Z(aL aLVar) {
        this.ie = aLVar;
    }

    public boolean hasData() {
        return !TextUtils.isEmpty(this.lB);
    }

    public BaseResult shallowClone() {
        BaseResult z = new Z(this.ie);
        z.lB = this.lB;
        z.lC = this.lC;
        z.lD = this.lD;
        z.lE = this.lE;
        return z;
    }
}
