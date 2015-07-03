package com.xiaomi.passport.accountmanager;

import android.accounts.Account;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.accounts.AuthenticatorDescription;
import android.accounts.AuthenticatorException;
import android.accounts.OnAccountsUpdateListener;
import android.accounts.OperationCanceledException;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import com.xiaomi.accounts.AccountManager;
import java.io.IOException;

class LocalAccountManagerAdapter implements IAccountManagerInternal {
    private AccountManager mAccountManager;

    public LocalAccountManagerAdapter(Context context) {
        this.mAccountManager = AccountManager.get(context);
    }

    public AuthenticatorDescription[] getAuthenticatorTypes() {
        return this.mAccountManager.getAuthenticatorTypes();
    }

    public String getPassword(Account account) {
        return this.mAccountManager.getPassword(account);
    }

    public String getUserData(Account account, String key) {
        return this.mAccountManager.getUserData(account, key);
    }

    public Account[] getAccounts() {
        return this.mAccountManager.getAccounts();
    }

    public Account[] getAccountsByType(String type) {
        return this.mAccountManager.getAccountsByType(type);
    }

    public AccountManagerFuture<Boolean> hasFeatures(Account account, String[] features, AccountManagerCallback<Boolean> callback, Handler handler) {
        return this.mAccountManager.hasFeatures(account, features, callback, handler);
    }

    public AccountManagerFuture<Account[]> getAccountsByTypeAndFeatures(String type, String[] features, AccountManagerCallback<Account[]> callback, Handler handler) {
        return this.mAccountManager.getAccountsByTypeAndFeatures(type, features, callback, handler);
    }

    public boolean addAccountExplicitly(Account account, String password, Bundle userdata) {
        return this.mAccountManager.addAccountExplicitly(account, password, userdata);
    }

    public AccountManagerFuture<Boolean> removeAccount(Account account, AccountManagerCallback<Boolean> callback, Handler handler) {
        return this.mAccountManager.removeAccount(account, callback, handler);
    }

    public void invalidateAuthToken(String accountType, String authToken) {
        this.mAccountManager.invalidateAuthToken(accountType, authToken);
    }

    public String peekAuthToken(Account account, String authTokenType) {
        return this.mAccountManager.peekAuthToken(account, authTokenType);
    }

    public void setPassword(Account account, String password) {
        this.mAccountManager.setPassword(account, password);
    }

    public void clearPassword(Account account) {
        this.mAccountManager.clearPassword(account);
    }

    public void setUserData(Account account, String key, String value) {
        this.mAccountManager.setUserData(account, key, value);
    }

    public void setAuthToken(Account account, String authTokenType, String authToken) {
        this.mAccountManager.setAuthToken(account, authTokenType, authToken);
    }

    public String blockingGetAuthToken(Account account, String authTokenType, boolean notifyAuthFailure) throws OperationCanceledException, IOException, AuthenticatorException {
        return this.mAccountManager.blockingGetAuthToken(account, authTokenType, notifyAuthFailure);
    }

    public AccountManagerFuture<Bundle> getAuthToken(Account account, String authTokenType, Bundle options, Activity activity, AccountManagerCallback<Bundle> callback, Handler handler) {
        return this.mAccountManager.getAuthToken(account, authTokenType, options, activity, (AccountManagerCallback) callback, handler);
    }

    public AccountManagerFuture<Bundle> getAuthToken(Account account, String authTokenType, Bundle options, boolean notifyAuthFailure, AccountManagerCallback<Bundle> callback, Handler handler) {
        return this.mAccountManager.getAuthToken(account, authTokenType, options, notifyAuthFailure, (AccountManagerCallback) callback, handler);
    }

    public AccountManagerFuture<Bundle> addAccount(String accountType, String authTokenType, String[] requiredFeatures, Bundle addAccountOptions, Activity activity, AccountManagerCallback<Bundle> callback, Handler handler) {
        return this.mAccountManager.addAccount(accountType, authTokenType, requiredFeatures, addAccountOptions, activity, callback, handler);
    }

    public AccountManagerFuture<Bundle> confirmCredentials(Account account, Bundle options, Activity activity, AccountManagerCallback<Bundle> callback, Handler handler) {
        return this.mAccountManager.confirmCredentials(account, options, activity, callback, handler);
    }

    public AccountManagerFuture<Bundle> updateCredentials(Account account, String authTokenType, Bundle options, Activity activity, AccountManagerCallback<Bundle> callback, Handler handler) {
        return this.mAccountManager.updateCredentials(account, authTokenType, options, activity, callback, handler);
    }

    public AccountManagerFuture<Bundle> editProperties(String accountType, Activity activity, AccountManagerCallback<Bundle> callback, Handler handler) {
        return this.mAccountManager.editProperties(accountType, activity, callback, handler);
    }

    public AccountManagerFuture<Bundle> getAuthTokenByFeatures(String accountType, String authTokenType, String[] features, Activity activity, Bundle addAccountOptions, Bundle getAuthTokenOptions, AccountManagerCallback<Bundle> callback, Handler handler) {
        return this.mAccountManager.getAuthTokenByFeatures(accountType, authTokenType, features, activity, addAccountOptions, getAuthTokenOptions, callback, handler);
    }

    public void addOnAccountsUpdatedListener(OnAccountsUpdateListener listener, Handler handler, boolean updateImmediately) {
        this.mAccountManager.addOnAccountsUpdatedListener(listener, handler, updateImmediately);
    }

    public void removeOnAccountsUpdatedListener(OnAccountsUpdateListener listener) {
        this.mAccountManager.removeOnAccountsUpdatedListener(listener);
    }
}
