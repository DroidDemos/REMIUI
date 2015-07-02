package com.miui.yellowpage.ui;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;

/* compiled from: ExpressInquiryProgressFragment */
class z implements OnClickListener {
    final /* synthetic */ Intent cT;
    final /* synthetic */ dm hq;

    z(dm dmVar, Intent intent) {
        this.hq = dmVar;
        this.cT = intent;
    }

    public void onClick(View view) {
        this.hq.mContext.startActivity(this.cT);
    }
}
