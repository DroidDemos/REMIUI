package com.miui.yellowpage.ui;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.net.Uri;

/* compiled from: ExpressInquiryProgressListItem */
class bo implements OnClickListener {
    final /* synthetic */ ExpressInquiryProgressListItem vr;
    final /* synthetic */ String vs;

    bo(ExpressInquiryProgressListItem expressInquiryProgressListItem, String str) {
        this.vr = expressInquiryProgressListItem;
        this.vs = str;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        this.vr.mContext.startActivity(new Intent("android.intent.action.CALL_PRIVILEGED", Uri.parse("tel:" + this.vs)));
    }
}
