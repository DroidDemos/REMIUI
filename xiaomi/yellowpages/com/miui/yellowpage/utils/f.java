package com.miui.yellowpage.utils;

import android.app.Dialog;
import android.view.View;
import android.view.View.OnClickListener;

/* compiled from: Ui */
final class f implements OnClickListener {
    final /* synthetic */ C lp;
    final /* synthetic */ Dialog lq;

    f(C c, Dialog dialog) {
        this.lp = c;
        this.lq = dialog;
    }

    public void onClick(View view) {
        this.lp.v(1);
        this.lq.dismiss();
    }
}
