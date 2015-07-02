package com.miui.yellowpage.ui;

import android.text.TextUtils;
import com.miui.yellowpage.request.BaseResult;

/* compiled from: RechargeFragment */
class cL extends BaseResult {
    final /* synthetic */ aL ie;
    private String mName;
    private String mNumber;

    public cL(aL aLVar, String str) {
        this.ie = aLVar;
        this.mNumber = str;
    }

    public boolean hasData() {
        return !TextUtils.isEmpty(this.mName);
    }

    public BaseResult shallowClone() {
        BaseResult cLVar = new cL(this.ie, this.mNumber);
        cLVar.mName = this.mName;
        return cLVar;
    }
}
