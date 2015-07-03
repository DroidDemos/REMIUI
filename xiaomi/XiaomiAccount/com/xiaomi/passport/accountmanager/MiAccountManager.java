package com.xiaomi.passport.accountmanager;

import android.accounts.Account;
import android.accounts.AccountManager;
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
import android.text.TextUtils;
import com.xiaomi.passport.widget.AlertDialog;
import java.io.IOException;

public class MiAccountManager implements IAccountManagerInternal {
    public static final String ACTION_AUTHENTICATOR_INTENT = "android.accounts.AccountAuthenticator";
    public static final String AUTHENTICATOR_ATTRIBUTES_NAME = "account-authenticator";
    public static final String AUTHENTICATOR_META_DATA_NAME = "android.accounts.AccountAuthenticator";
    public static final int ERROR_CODE_BAD_ARGUMENTS = 7;
    public static final int ERROR_CODE_BAD_REQUEST = 8;
    public static final int ERROR_CODE_CANCELED = 4;
    public static final int ERROR_CODE_INVALID_RESPONSE = 5;
    public static final int ERROR_CODE_NETWORK_ERROR = 3;
    public static final int ERROR_CODE_REMOTE_EXCEPTION = 1;
    public static final int ERROR_CODE_UNSUPPORTED_OPERATION = 6;
    public static final String KEY_ACCOUNTS = "accounts";
    public static final String KEY_ACCOUNT_AUTHENTICATOR_RESPONSE = "accountAuthenticatorResponse";
    public static final String KEY_ACCOUNT_MANAGER_RESPONSE = "accountManagerResponse";
    public static final String KEY_ACCOUNT_NAME = "authAccount";
    public static final String KEY_ACCOUNT_TYPE = "accountType";
    public static final String KEY_AUTHENTICATOR_TYPES = "authenticator_types";
    public static final String KEY_AUTHTOKEN = "authtoken";
    public static final String KEY_AUTH_FAILED_MESSAGE = "authFailedMessage";
    public static final String KEY_AUTH_TOKEN_LABEL = "authTokenLabelKey";
    public static final String KEY_BOOLEAN_RESULT = "booleanResult";
    public static final String KEY_ERROR_CODE = "errorCode";
    public static final String KEY_ERROR_MESSAGE = "errorMessage";
    public static final String KEY_INTENT = "intent";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_USERDATA = "userdata";
    public static final String LOGIN_ACCOUNTS_CHANGED_ACTION = "account-authenticator";
    public static final String XIAOMI_ACCOUNT_TYPE = "com.xiaomi";
    private static volatile MiAccountManager sThis;
    private AccountAuthenticator mAccountAuthenticator;
    private boolean mCanUseMiliaoAccount;
    private boolean mCanUseSystemAccount;
    private final Context mContext;
    private LocalAccountManagerAdapter mLocalAccountAdapter;
    private SystemAccountManagerAdapter mSystemAccountAdapter;
    private IAccountManagerInternal mXmsfAccountAdapter;

    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$xiaomi$passport$accountmanager$MiAccountManager$AccountAuthenticator;

        static {
            $SwitchMap$com$xiaomi$passport$accountmanager$MiAccountManager$AccountAuthenticator = new int[AccountAuthenticator.values().length];
            try {
                $SwitchMap$com$xiaomi$passport$accountmanager$MiAccountManager$AccountAuthenticator[AccountAuthenticator.SYSTEM.ordinal()] = MiAccountManager.ERROR_CODE_REMOTE_EXCEPTION;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$xiaomi$passport$accountmanager$MiAccountManager$AccountAuthenticator[AccountAuthenticator.LOCAL.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
        }
    }

    public enum AccountAuthenticator {
        LOCAL,
        SYSTEM
    }

    private MiAccountManager(Context context) {
        this.mContext = context.getApplicationContext();
        this.mCanUseSystemAccount = canUseSystemAccount(context);
        setUseSystem();
    }

    public boolean canUseSystem() {
        return this.mCanUseSystemAccount;
    }

    public boolean canUseMiliao() {
        return this.mCanUseMiliaoAccount;
    }

    public boolean isUseSystem() {
        return this.mAccountAuthenticator == AccountAuthenticator.SYSTEM;
    }

    public boolean isUseLocal() {
        return this.mAccountAuthenticator == AccountAuthenticator.LOCAL;
    }

    public void setUseSystem() {
        setUpAccountAdapter(AccountAuthenticator.SYSTEM);
    }

    public void setUseLocal() {
        setUpAccountAdapter(AccountAuthenticator.LOCAL);
    }

    private void setUpAccountAdapter(AccountAuthenticator authenticator) {
        switch (AnonymousClass1.$SwitchMap$com$xiaomi$passport$accountmanager$MiAccountManager$AccountAuthenticator[authenticator.ordinal()]) {
            case ERROR_CODE_REMOTE_EXCEPTION /*1*/:
                if (!this.mCanUseSystemAccount) {
                    this.mAccountAuthenticator = AccountAuthenticator.LOCAL;
                    break;
                } else {
                    this.mAccountAuthenticator = AccountAuthenticator.SYSTEM;
                    break;
                }
            case AlertDialog.THEME_DARK /*2*/:
                this.mAccountAuthenticator = AccountAuthenticator.LOCAL;
                break;
            default:
                throw new IllegalArgumentException();
        }
        switch (AnonymousClass1.$SwitchMap$com$xiaomi$passport$accountmanager$MiAccountManager$AccountAuthenticator[this.mAccountAuthenticator.ordinal()]) {
            case ERROR_CODE_REMOTE_EXCEPTION /*1*/:
                if (this.mSystemAccountAdapter == null) {
                    this.mSystemAccountAdapter = new SystemAccountManagerAdapter(this.mContext);
                }
                this.mXmsfAccountAdapter = this.mSystemAccountAdapter;
                return;
            case AlertDialog.THEME_DARK /*2*/:
                if (this.mLocalAccountAdapter == null) {
                    this.mLocalAccountAdapter = new LocalAccountManagerAdapter(this.mContext);
                }
                this.mXmsfAccountAdapter = this.mLocalAccountAdapter;
                return;
            default:
                throw new IllegalArgumentException();
        }
    }

    private boolean canUseSystemAccount(Context context) {
        AuthenticatorDescription[] arr$ = AccountManager.get(context).getAuthenticatorTypes();
        int len$ = arr$.length;
        for (int i$ = 0; i$ < len$; i$ += ERROR_CODE_REMOTE_EXCEPTION) {
            if (TextUtils.equals(arr$[i$].type, XIAOMI_ACCOUNT_TYPE)) {
                return true;
            }
        }
        return false;
    }

    public static MiAccountManager get(Context context) {
        if (context == null) {
            throw new IllegalArgumentException("context is null");
        }
        if (sThis == null) {
            synchronized (MiAccountManager.class) {
                if (sThis == null) {
                    sThis = new MiAccountManager(context);
                }
            }
        }
        return sThis;
    }

    public AuthenticatorDescription[] getAuthenticatorTypes() {
        return this.mXmsfAccountAdapter.getAuthenticatorTypes();
    }

    public Account[] getAccounts() {
        return this.mXmsfAccountAdapter.getAccounts();
    }

    public Account[] getAccountsByType(String type) {
        return this.mXmsfAccountAdapter.getAccountsByType(type);
    }

    public AccountManagerFuture<Boolean> hasFeatures(Account account, String[] features, AccountManagerCallback<Boolean> callback, Handler handler) {
        return this.mXmsfAccountAdapter.hasFeatures(account, features, callback, handler);
    }

    public AccountManagerFuture<Account[]> getAccountsByTypeAndFeatures(String type, String[] features, AccountManagerCallback<Account[]> callback, Handler handler) {
        return this.mXmsfAccountAdapter.getAccountsByTypeAndFeatures(type, features, callback, handler);
    }

    public AccountManagerFuture<Boolean> removeAccount(Account account, AccountManagerCallback<Boolean> callback, Handler handler) {
        return this.mXmsfAccountAdapter.removeAccount(account, callback, handler);
    }

    public void invalidateAuthToken(String accountType, String authToken) {
        this.mXmsfAccountAdapter.invalidateAuthToken(accountType, authToken);
    }

    public void clearPassword(Account account) {
        this.mXmsfAccountAdapter.clearPassword(account);
    }

    public String blockingGetAuthToken(Account account, String authTokenType, boolean notifyAuthFailure) throws OperationCanceledException, IOException, AuthenticatorException {
        return this.mXmsfAccountAdapter.blockingGetAuthToken(account, authTokenType, notifyAuthFailure);
    }

    public AccountManagerFuture<Bundle> getAuthToken(Account account, String authTokenType, Bundle options, Activity activity, AccountManagerCallback<Bundle> callback, Handler handler) {
        return this.mXmsfAccountAdapter.getAuthToken(account, authTokenType, options, activity, (AccountManagerCallback) callback, handler);
    }

    public AccountManagerFuture<Bundle> getAuthToken(Account account, String authTokenType, Bundle options, boolean notifyAuthFailure, AccountManagerCallback<Bundle> callback, Handler handler) {
        return this.mXmsfAccountAdapter.getAuthToken(account, authTokenType, options, notifyAuthFailure, (AccountManagerCallback) callback, handler);
    }

    public AccountManagerFuture<Bundle> addAccount(String accountType, String authTokenType, String[] requiredFeatures, Bundle addAccountOptions, Activity activity, AccountManagerCallback<Bundle> callback, Handler handler) {
        return this.mXmsfAccountAdapter.addAccount(accountType, authTokenType, requiredFeatures, addAccountOptions, activity, callback, handler);
    }

    public AccountManagerFuture<Bundle> confirmCredentials(Account account, Bundle options, Activity activity, AccountManagerCallback<Bundle> callback, Handler handler) {
        return this.mXmsfAccountAdapter.confirmCredentials(account, options, activity, callback, handler);
    }

    public AccountManagerFuture<Bundle> updateCredentials(Account account, String authTokenType, Bundle options, Activity activity, AccountManagerCallback<Bundle> callback, Handler handler) {
        return this.mXmsfAccountAdapter.updateCredentials(account, authTokenType, options, activity, callback, handler);
    }

    public AccountManagerFuture<Bundle> editProperties(String accountType, Activity activity, AccountManagerCallback<Bundle> callback, Handler handler) {
        return this.mXmsfAccountAdapter.editProperties(accountType, activity, callback, handler);
    }

    public AccountManagerFuture<Bundle> getAuthTokenByFeatures(String accountType, String authTokenType, String[] features, Activity activity, Bundle addAccountOptions, Bundle getAuthTokenOptions, AccountManagerCallback<Bundle> callback, Handler handler) {
        return this.mXmsfAccountAdapter.getAuthTokenByFeatures(accountType, authTokenType, features, activity, addAccountOptions, getAuthTokenOptions, callback, handler);
    }

    public void addOnAccountsUpdatedListener(OnAccountsUpdateListener listener, Handler handler, boolean updateImmediately) {
        this.mXmsfAccountAdapter.addOnAccountsUpdatedListener(listener, handler, updateImmediately);
    }

    public void removeOnAccountsUpdatedListener(OnAccountsUpdateListener listener) {
        this.mXmsfAccountAdapter.removeOnAccountsUpdatedListener(listener);
    }

    public Account getXiaomiAccount() {
        Account[] accounts = this.mXmsfAccountAdapter.getAccountsByType(XIAOMI_ACCOUNT_TYPE);
        if (accounts.length > 0) {
            return accounts[0];
        }
        return null;
    }

    public void addXiaomiAccount(String authTokenType, Activity activity, AccountManagerCallback<Bundle> callback, Handler handler) {
        addXiaomiAccount(authTokenType, null, null, activity, callback, handler);
    }

    public void addXiaomiAccount(String authTokenType, String[] requiredFeatures, Bundle addAccountOptions, Activity activity, AccountManagerCallback<Bundle> callback, Handler handler) {
        Account[] arr$ = getAccounts();
        int len$ = arr$.length;
        int i$ = 0;
        while (i$ < len$) {
            if (!TextUtils.equals(arr$[i$].type, XIAOMI_ACCOUNT_TYPE)) {
                i$ += ERROR_CODE_REMOTE_EXCEPTION;
            } else {
                return;
            }
        }
        addAccount(XIAOMI_ACCOUNT_TYPE, authTokenType, requiredFeatures, addAccountOptions, activity, callback, handler);
    }

    public void removeXiaomiAccount(AccountManagerCallback<Boolean> callback, Handler handler) {
        Account[] arr$ = getAccounts();
        int len$ = arr$.length;
        for (int i$ = 0; i$ < len$; i$ += ERROR_CODE_REMOTE_EXCEPTION) {
            Account account = arr$[i$];
            if (TextUtils.equals(account.type, XIAOMI_ACCOUNT_TYPE)) {
                removeAccount(account, callback, handler);
            }
        }
    }

    public String getPassword(Account account) {
        return this.mXmsfAccountAdapter.getPassword(account);
    }

    public String getUserData(Account account, String key) {
        return this.mXmsfAccountAdapter.getUserData(account, key);
    }

    public boolean addAccountExplicitly(Account account, String password, Bundle userdata) {
        return this.mXmsfAccountAdapter.addAccountExplicitly(account, password, userdata);
    }

    public String peekAuthToken(Account account, String authTokenType) {
        return this.mXmsfAccountAdapter.peekAuthToken(account, authTokenType);
    }

    public void setPassword(Account account, String password) {
        this.mXmsfAccountAdapter.setPassword(account, password);
    }

    public void setUserData(Account account, String key, String value) {
        this.mXmsfAccountAdapter.setUserData(account, key, value);
    }

    public void setAuthToken(Account account, String authTokenType, String authToken) {
        this.mXmsfAccountAdapter.setAuthToken(account, authTokenType, authToken);
    }
}
