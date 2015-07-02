package com.miui.yellowpage.ui;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

/* compiled from: ExpressInquiryFragment */
class ab implements OnClickListener {
    final /* synthetic */ ExpressInquiryFragment hP;
    final /* synthetic */ CharSequence lK;

    ab(ExpressInquiryFragment expressInquiryFragment, CharSequence charSequence) {
        this.hP = expressInquiryFragment;
        this.lK = charSequence;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        this.hP.mNumber = this.lK.toString();
        this.hP.dd.setText(this.hP.mNumber);
        this.hP.U();
    }
}
