package com.miui.yellowpage.activity;

import android.accounts.AccountManager;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

/* compiled from: LoginActivity */
class g implements OnClickListener {
    final /* synthetic */ LoginActivity ir;

    g(LoginActivity loginActivity) {
        this.ir = loginActivity;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        AccountManager.get(this.ir.mActivity).addAccount("com.xiaomi", this.ir.MD, null, null, this.ir.mActivity, new al(this), null);
    }
}
