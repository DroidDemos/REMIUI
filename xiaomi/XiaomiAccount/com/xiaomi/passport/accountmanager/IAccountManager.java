package com.xiaomi.passport.accountmanager;

import android.accounts.Account;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.accounts.AuthenticatorDescription;
import android.accounts.AuthenticatorException;
import android.accounts.OnAccountsUpdateListener;
import android.accounts.OperationCanceledException;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import java.io.IOException;

public interface IAccountManager {
    AccountManagerFuture<Bundle> addAccount(String str, String str2, String[] strArr, Bundle bundle, Activity activity, AccountManagerCallback<Bundle> accountManagerCallback, Handler handler);

    void addOnAccountsUpdatedListener(OnAccountsUpdateListener onAccountsUpdateListener, Handler handler, boolean z);

    String blockingGetAuthToken(Account account, String str, boolean z) throws OperationCanceledException, IOException, AuthenticatorException;

    void clearPassword(Account account);

    AccountManagerFuture<Bundle> confirmCredentials(Account account, Bundle bundle, Activity activity, AccountManagerCallback<Bundle> accountManagerCallback, Handler handler);

    AccountManagerFuture<Bundle> editProperties(String str, Activity activity, AccountManagerCallback<Bundle> accountManagerCallback, Handler handler);

    Account[] getAccounts();

    Account[] getAccountsByType(String str);

    AccountManagerFuture<Account[]> getAccountsByTypeAndFeatures(String str, String[] strArr, AccountManagerCallback<Account[]> accountManagerCallback, Handler handler);

    AccountManagerFuture<Bundle> getAuthToken(Account account, String str, Bundle bundle, Activity activity, AccountManagerCallback<Bundle> accountManagerCallback, Handler handler);

    AccountManagerFuture<Bundle> getAuthToken(Account account, String str, Bundle bundle, boolean z, AccountManagerCallback<Bundle> accountManagerCallback, Handler handler);

    AccountManagerFuture<Bundle> getAuthTokenByFeatures(String str, String str2, String[] strArr, Activity activity, Bundle bundle, Bundle bundle2, AccountManagerCallback<Bundle> accountManagerCallback, Handler handler);

    AuthenticatorDescription[] getAuthenticatorTypes();

    AccountManagerFuture<Boolean> hasFeatures(Account account, String[] strArr, AccountManagerCallback<Boolean> accountManagerCallback, Handler handler);

    void invalidateAuthToken(String str, String str2);

    AccountManagerFuture<Boolean> removeAccount(Account account, AccountManagerCallback<Boolean> accountManagerCallback, Handler handler);

    void removeOnAccountsUpdatedListener(OnAccountsUpdateListener onAccountsUpdateListener);

    AccountManagerFuture<Bundle> updateCredentials(Account account, String str, Bundle bundle, Activity activity, AccountManagerCallback<Bundle> accountManagerCallback, Handler handler);
}
