package com.miui.yellowpage.ui;

import android.text.TextUtils;
import com.miui.yellowpage.request.BaseResult;

/* compiled from: ExpressContactEditorFragment */
public class dd extends BaseResult {
    String Hb;
    String Hc;
    int Hd;
    String address;
    String name;

    protected dd() {
    }

    public boolean hasData() {
        return (TextUtils.isEmpty(this.name) && TextUtils.isEmpty(this.Hb) && TextUtils.isEmpty(this.address) && TextUtils.isEmpty(this.Hc)) ? false : true;
    }

    public BaseResult shallowClone() {
        BaseResult ddVar = new dd();
        ddVar.name = this.name;
        ddVar.Hb = this.Hb;
        ddVar.address = this.address;
        ddVar.Hc = this.Hc;
        return ddVar;
    }
}
