package com.miui.yellowpage.ui;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.miui.yellowpage.base.utils.Preference;

/* compiled from: ExpressInquiryProgressFragment */
class bG implements OnClickListener {
    final /* synthetic */ ExpressInquiryProgressFragment mY;

    bG(ExpressInquiryProgressFragment expressInquiryProgressFragment) {
        this.mY = expressInquiryProgressFragment;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        Preference.setBoolean(this.mY.JP, "pref_express_show_trace_notice", false);
        dialogInterface.dismiss();
        this.mY.dL();
    }
}
