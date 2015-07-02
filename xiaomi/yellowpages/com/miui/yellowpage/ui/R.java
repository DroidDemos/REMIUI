package com.miui.yellowpage.ui;

import android.text.Editable;
import android.text.TextWatcher;
import com.alipay.mobilesecuritysdk.constant.ConfigConstant;

/* compiled from: RechargeFragment */
class R implements TextWatcher {
    final /* synthetic */ aL ie;

    private R(aL aLVar) {
        this.ie = aLVar;
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void afterTextChanged(Editable editable) {
        this.ie.qz = this.ie.normalizeNumber(editable.toString());
        if (this.ie.au(this.ie.qz)) {
            this.ie.qm.a(this.ie.b(this.ie.qz, 5), new cL(this.ie, this.ie.qz));
        } else {
            this.ie.qt.setText(ConfigConstant.WIRELESS_FILENAME);
        }
    }
}
