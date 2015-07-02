package com.miui.yellowpage.ui;

import android.accounts.Account;
import miui.preference.ValuePreference;

/* compiled from: AccountFragment */
class aI implements Runnable {
    final /* synthetic */ cw pV;
    final /* synthetic */ Account val$account;

    aI(cw cwVar, Account account) {
        this.pV = cwVar;
        this.val$account = account;
    }

    public void run() {
        ValuePreference valuePreference = (ValuePreference) this.pV.mS.findPreference("pref_account_info");
        valuePreference.setValue(this.val$account.name);
        valuePreference.setShowRightArrow(true);
    }
}
