package com.miui.yellowpage.activity;

import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;

/* compiled from: RemarkActivity */
class q implements OnCancelListener {
    final /* synthetic */ RemarkActivity lx;

    q(RemarkActivity remarkActivity) {
        this.lx = remarkActivity;
    }

    public void onCancel(DialogInterface dialogInterface) {
        this.lx.setResult(0);
    }
}
