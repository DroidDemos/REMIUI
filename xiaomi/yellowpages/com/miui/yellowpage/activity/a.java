package com.miui.yellowpage.activity;

import android.text.TextUtils;
import com.miui.yellowpage.base.utils.BusinessStatistics;

/* compiled from: SendExpressActivity */
class a implements Runnable {
    final /* synthetic */ SendExpressActivity bG;

    a(SendExpressActivity sendExpressActivity) {
        this.bG = sendExpressActivity;
    }

    public void run() {
        if (!TextUtils.isEmpty(this.bG.getStatisticsContext().getSource())) {
            BusinessStatistics.uploadOrderRefInfo(this.bG, this.bG.getStatisticsContext().getSource(), "0-6");
        }
    }
}
