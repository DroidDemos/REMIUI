package com.miui.yellowpage.activity;

import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;

/* compiled from: MarkNumberActivity */
class E implements OnDismissListener {
    final /* synthetic */ MarkNumberActivity rR;

    E(MarkNumberActivity markNumberActivity) {
        this.rR = markNumberActivity;
    }

    public void onDismiss(DialogInterface dialogInterface) {
        this.rR.finish();
    }
}
