package com.miui.yellowpage.ui;

import android.text.TextUtils;
import com.miui.yellowpage.request.BaseResult;

/* compiled from: CityPickerFragment */
class bC extends BaseResult {
    final /* synthetic */ bD ge;
    String ym;
    String yn;

    private bC(bD bDVar) {
        this.ge = bDVar;
    }

    public boolean hasData() {
        return (TextUtils.isEmpty(this.yn) || TextUtils.isEmpty(this.ym)) ? false : true;
    }

    public BaseResult shallowClone() {
        BaseResult bCVar = new bC(this.ge);
        bCVar.ym = this.ym;
        bCVar.yn = this.yn;
        return bCVar;
    }
}
