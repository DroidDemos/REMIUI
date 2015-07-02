package com.miui.yellowpage.ui;

import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;

/* compiled from: RechargeFragment */
class az implements OnClickListener {
    final /* synthetic */ aL ie;

    az(aL aLVar) {
        this.ie = aLVar;
    }

    public void onClick(View view) {
        if (!TextUtils.isEmpty(this.ie.qo.getText())) {
            return;
        }
        if (this.ie.qv) {
            this.ie.cR();
        } else {
            this.ie.cQ();
        }
    }
}
