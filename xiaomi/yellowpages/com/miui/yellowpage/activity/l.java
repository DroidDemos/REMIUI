package com.miui.yellowpage.activity;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

/* compiled from: LoginActivity */
class l implements OnClickListener {
    final /* synthetic */ LoginActivity ir;

    l(LoginActivity loginActivity) {
        this.ir = loginActivity;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        this.ir.setResult(0);
        this.ir.finish();
    }
}
