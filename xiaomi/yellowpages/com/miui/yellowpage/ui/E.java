package com.miui.yellowpage.ui;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;

/* compiled from: ExpressInquiryFragment */
class E implements OnClickListener {
    final /* synthetic */ ExpressInquiryFragment hP;

    private E(ExpressInquiryFragment expressInquiryFragment) {
        this.hP = expressInquiryFragment;
    }

    public void onClick(View view) {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.scanbarcode");
        intent.putExtra("isBackToThirdApp", true);
        this.hP.startActivityForResult(intent, 0);
    }
}
