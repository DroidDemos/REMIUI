package com.miui.yellowpage.business.recharge;

import android.text.Editable;
import android.text.TextWatcher;
import com.alipay.mobilesecuritysdk.constant.ConfigConstant;

/* compiled from: RechargeFragment */
class g implements TextWatcher {
    final /* synthetic */ D dw;

    private g(D d) {
        this.dw = d;
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void afterTextChanged(Editable editable) {
        this.dw.qz = this.dw.normalizeNumber(editable.toString());
        if (this.dw.au(this.dw.qz)) {
            this.dw.qm.a(this.dw.b(this.dw.qz, 5), new x(this.dw, this.dw.qz));
        } else {
            this.dw.qt.setText(ConfigConstant.WIRELESS_FILENAME);
        }
    }
}
