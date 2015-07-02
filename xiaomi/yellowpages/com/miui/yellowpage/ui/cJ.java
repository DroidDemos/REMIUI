package com.miui.yellowpage.ui;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;

/* compiled from: RechargeFragment */
class cJ implements TextWatcher {
    final /* synthetic */ aL ie;

    private cJ(aL aLVar) {
        this.ie = aLVar;
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public synchronized void afterTextChanged(Editable editable) {
        if (TextUtils.isEmpty(editable)) {
            if (this.ie.mClearButton != null) {
                this.ie.mClearButton.setVisibility(4);
            }
        } else if (this.ie.mClearButton != null) {
            this.ie.mClearButton.setVisibility(0);
        }
    }
}
