package com.miui.yellowpage.activity;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.miui.yellowpage.base.utils.Sim;

/* compiled from: BalanceInquiryRoutingActivity */
class S implements OnClickListener {
    final /* synthetic */ BalanceInquiryRoutingActivity uH;

    S(BalanceInquiryRoutingActivity balanceInquiryRoutingActivity) {
        this.uH = balanceInquiryRoutingActivity;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        int readySimIndexOnOneInserted = Sim.getReadySimIndexOnOneInserted(this.uH);
        this.uH.b(readySimIndexOnOneInserted, Sim.getSimOpCode(this.uH, readySimIndexOnOneInserted));
        this.uH.finish();
    }
}
