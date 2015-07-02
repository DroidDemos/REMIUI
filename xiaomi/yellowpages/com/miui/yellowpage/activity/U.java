package com.miui.yellowpage.activity;

import com.miui.yellowpage.base.utils.Sim;
import com.miui.yellowpage.utils.C;

/* compiled from: BalanceInquiryRoutingActivity */
class U implements C {
    final /* synthetic */ BalanceInquiryRoutingActivity uH;

    U(BalanceInquiryRoutingActivity balanceInquiryRoutingActivity) {
        this.uH = balanceInquiryRoutingActivity;
    }

    public void v(int i) {
        this.uH.b(i, Sim.getSimOpCode(this.uH, i));
        this.uH.finish();
    }

    public void onCancel() {
        this.uH.finish();
    }
}
