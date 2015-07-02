package com.miui.yellowpage.activity;

import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;

/* compiled from: LoginActivity */
class j implements OnCancelListener {
    final /* synthetic */ LoginActivity ir;

    j(LoginActivity loginActivity) {
        this.ir = loginActivity;
    }

    public void onCancel(DialogInterface dialogInterface) {
        this.ir.setResult(0);
        this.ir.finish();
    }
}
