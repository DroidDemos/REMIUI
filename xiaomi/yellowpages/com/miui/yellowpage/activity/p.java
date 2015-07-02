package com.miui.yellowpage.activity;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

/* compiled from: RemarkActivity */
class p implements OnClickListener {
    final /* synthetic */ RemarkActivity lx;

    p(RemarkActivity remarkActivity) {
        this.lx = remarkActivity;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        this.lx.setResult(0);
    }
}
