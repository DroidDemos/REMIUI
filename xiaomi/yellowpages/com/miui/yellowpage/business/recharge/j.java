package com.miui.yellowpage.business.recharge;

import android.text.TextUtils;
import com.miui.yellowpage.base.utils.BusinessStatistics;

/* compiled from: RechargeFragment */
class j implements Runnable {
    final /* synthetic */ D dw;

    j(D d) {
        this.dw = d;
    }

    public void run() {
        if (!TextUtils.isEmpty(this.dw.iq)) {
            BusinessStatistics.uploadOrderRefInfo(this.dw.mActivity, this.dw.iq, "0-7");
        }
    }
}
