package com.miui.yellowpage.business.recharge;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;

/* compiled from: RechargeFragment */
class O implements TextWatcher {
    final /* synthetic */ D dw;

    private O(D d) {
        this.dw = d;
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public synchronized void afterTextChanged(Editable editable) {
        if (TextUtils.isEmpty(editable)) {
            if (!this.dw.qv) {
                this.dw.cQ();
            }
        } else if (this.dw.qv) {
            this.dw.cR();
        }
    }
}
