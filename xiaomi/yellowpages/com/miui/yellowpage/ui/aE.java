package com.miui.yellowpage.ui;

import android.text.TextUtils;
import com.miui.yellowpage.base.utils.BusinessStatistics;

/* compiled from: RechargeFragment */
class aE implements Runnable {
    final /* synthetic */ aL ie;

    aE(aL aLVar) {
        this.ie = aLVar;
    }

    public void run() {
        if (!TextUtils.isEmpty(this.ie.iq)) {
            BusinessStatistics.uploadOrderRefInfo(this.ie.mActivity, this.ie.iq, "0-7");
        }
    }
}
