package com.miui.yellowpage.activity;

import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;

/* compiled from: BalanceInquiryRoutingActivity */
class T implements OnCancelListener {
    final /* synthetic */ BalanceInquiryRoutingActivity uH;

    T(BalanceInquiryRoutingActivity balanceInquiryRoutingActivity) {
        this.uH = balanceInquiryRoutingActivity;
    }

    public void onCancel(DialogInterface dialogInterface) {
        this.uH.finish();
    }
}
