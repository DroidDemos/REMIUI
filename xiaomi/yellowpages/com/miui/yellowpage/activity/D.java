package com.miui.yellowpage.activity;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import miui.yellowpage.YellowPageUtils;

/* compiled from: MarkNumberActivity */
class D implements OnClickListener {
    final /* synthetic */ MarkNumberActivity rR;

    D(MarkNumberActivity markNumberActivity) {
        this.rR = markNumberActivity;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        YellowPageUtils.markAntiSpam(this.rR, this.rR.mNumber, -1, true);
        if (this.rR.mDialog.isShowing()) {
            this.rR.mDialog.dismiss();
        }
    }
}
