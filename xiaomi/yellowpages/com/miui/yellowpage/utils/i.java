package com.miui.yellowpage.utils;

import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;

/* compiled from: Ui */
final class i implements OnCancelListener {
    final /* synthetic */ C lp;

    i(C c) {
        this.lp = c;
    }

    public void onCancel(DialogInterface dialogInterface) {
        this.lp.onCancel();
    }
}
