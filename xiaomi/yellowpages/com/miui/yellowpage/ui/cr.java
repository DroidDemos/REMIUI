package com.miui.yellowpage.ui;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;

/* compiled from: RechargeFragment */
class cr implements TextWatcher {
    final /* synthetic */ aL ie;

    private cr(aL aLVar) {
        this.ie = aLVar;
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public synchronized void afterTextChanged(Editable editable) {
        if (TextUtils.isEmpty(editable)) {
            if (!this.ie.qv) {
                this.ie.cQ();
            }
        } else if (this.ie.qv) {
            this.ie.cR();
        }
    }
}
