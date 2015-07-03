package com.xiaomi.passport.interfaces;

import android.accounts.Account;
import android.content.Intent;

public interface PassportInterface {
    String getActivatePackageName();

    Intent getConfirmCredentialIntent();

    String getDeviceId();

    int getSimCount();

    String getSimOperatorName(int i);

    int getSlotCount();

    boolean isSimInserted(int i);

    boolean onGetRemoveAccountAllowed(Account account);

    void onPostAddAccount(Account account);

    void onPostRefreshAccount(Account account);

    void onPostRemoveAccount(Account account);

    void onPreAddAccount(Account account);

    void onPreRemoveAccount(Account account);

    boolean useUplinkRegister();

    @Deprecated
    boolean verifyCallerUidAgainstWebSso(int i);
}
