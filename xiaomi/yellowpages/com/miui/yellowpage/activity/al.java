package com.miui.yellowpage.activity;

import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import com.miui.yellowpage.base.utils.XiaomiAccount;

/* compiled from: LoginActivity */
class al implements AccountManagerCallback {
    final /* synthetic */ g Di;

    al(g gVar) {
        this.Di = gVar;
    }

    public void run(AccountManagerFuture accountManagerFuture) {
        if (XiaomiAccount.hasLogin(this.Di.ir.mActivity)) {
            this.Di.ir.gR();
        } else if (!this.Di.ir.mActivity.isFinishing()) {
            this.Di.ir.jc();
        }
    }
}
