package com.miui.yellowpage.business.recharge;

import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;

/* compiled from: RechargeFragment */
class n implements OnClickListener {
    final /* synthetic */ D dw;

    n(D d) {
        this.dw = d;
    }

    public void onClick(View view) {
        if (!TextUtils.isEmpty(this.dw.qo.getText())) {
            return;
        }
        if (this.dw.qv) {
            this.dw.cR();
        } else {
            this.dw.cQ();
        }
    }
}
