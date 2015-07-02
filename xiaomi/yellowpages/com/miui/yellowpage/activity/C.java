package com.miui.yellowpage.activity;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

/* compiled from: MarkNumberActivity */
class C implements OnClickListener {
    final /* synthetic */ MarkNumberActivity rR;

    C(MarkNumberActivity markNumberActivity) {
        this.rR = markNumberActivity;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        if (this.rR.mDialog.isShowing()) {
            this.rR.mDialog.dismiss();
        }
    }
}
