package com.miui.yellowpage.business.recharge;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

/* compiled from: RechargeFragment */
class L implements OnClickListener {
    final /* synthetic */ D dw;
    final /* synthetic */ String ih;

    L(D d, String str) {
        this.dw = d;
        this.ih = str;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        this.dw.m(this.ih, this.dw.qt.getText().toString());
        this.dw.dc();
        this.dw.mRequestLoader.a(this.dw.S(this.dw.DM.lD), new F(this.dw));
    }
}
