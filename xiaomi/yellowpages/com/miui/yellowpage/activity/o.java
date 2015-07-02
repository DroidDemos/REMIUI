package com.miui.yellowpage.activity;

import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;

/* compiled from: RemarkActivity */
class o implements OnDismissListener {
    final /* synthetic */ RemarkActivity lx;

    o(RemarkActivity remarkActivity) {
        this.lx = remarkActivity;
    }

    public void onDismiss(DialogInterface dialogInterface) {
        this.lx.finish();
    }
}
