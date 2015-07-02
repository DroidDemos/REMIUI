package com.miui.yellowpage.ui;

import android.view.View;
import android.view.View.OnClickListener;

/* compiled from: ExpressInquiryFragment */
class aV implements OnClickListener {
    final /* synthetic */ ExpressInquiryFragment hP;

    private aV(ExpressInquiryFragment expressInquiryFragment) {
        this.hP = expressInquiryFragment;
    }

    public void onClick(View view) {
        this.hP.mNumber = this.hP.dd.getEditableText().toString();
        this.hP.U();
    }
}
