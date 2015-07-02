package com.miui.yellowpage.ui;

import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;

/* compiled from: ExpressInquiryProgressFragment */
class bI implements OnDismissListener {
    final /* synthetic */ ExpressInquiryProgressFragment mY;

    bI(ExpressInquiryProgressFragment expressInquiryProgressFragment) {
        this.mY = expressInquiryProgressFragment;
    }

    public void onDismiss(DialogInterface dialogInterface) {
        if (this.mY.sB) {
            this.mY.JP.onBackPressed();
        }
        this.mY.sK = null;
    }
}
