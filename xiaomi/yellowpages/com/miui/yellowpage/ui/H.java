package com.miui.yellowpage.ui;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

/* compiled from: RechargeFragment */
class H implements OnClickListener {
    final /* synthetic */ aL ie;
    final /* synthetic */ String ih;

    H(aL aLVar, String str) {
        this.ie = aLVar;
        this.ih = str;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        this.ie.m(this.ih, this.ie.qt.getText().toString());
        this.ie.dc();
        this.ie.mRequestLoader.a(this.ie.S(this.ie.qx.lD), new Z(this.ie));
    }
}
