package com.miui.yellowpage.ui;

import android.accounts.Account;
import com.miui.yellowpage.base.utils.XiaomiAccount;

/* compiled from: AccountFragment */
class cw implements Runnable {
    final /* synthetic */ aT mS;

    cw(aT aTVar) {
        this.mS = aTVar;
    }

    public void run() {
        Account account = XiaomiAccount.getAccount(this.mS.mActivity);
        if (account != null) {
            this.mS.mActivity.runOnUiThread(new aI(this, account));
        }
    }
}
