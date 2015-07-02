package com.miui.yellowpage.utils;

import android.app.Dialog;
import android.view.View;
import android.view.View.OnClickListener;

/* compiled from: Ui */
final class j implements OnClickListener {
    final /* synthetic */ C lp;
    final /* synthetic */ Dialog lq;

    j(C c, Dialog dialog) {
        this.lp = c;
        this.lq = dialog;
    }

    public void onClick(View view) {
        this.lp.v(0);
        this.lq.dismiss();
    }
}
