package com.xiaomi.accounts;

import android.accounts.Account;
import android.accounts.NetworkErrorException;
import android.content.Context;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.xiaomi.accounts.IAccountAuthenticator.Stub;
import com.xiaomi.passport.Constants;
import com.xiaomi.passport.accountmanager.MiAccountManager;
import java.util.Arrays;

public abstract class AbstractAccountAuthenticator {
    private static final String TAG = "AccountAuthenticator";
    private final Context mContext;
    private Transport mTransport;

    private class Transport extends Stub {
        private Transport() {
        }

        public void addAccount(IAccountAuthenticatorResponse response, String accountType, String authTokenType, String[] features, Bundle options) throws RemoteException {
            if (Log.isLoggable(AbstractAccountAuthenticator.TAG, 2)) {
                Log.v(AbstractAccountAuthenticator.TAG, "addAccount: accountType " + accountType + ", authTokenType " + authTokenType + ", features " + (features == null ? "[]" : Arrays.toString(features)));
            }
            AbstractAccountAuthenticator.this.checkBinderPermission();
            try {
                Bundle result = AbstractAccountAuthenticator.this.addAccount(new AccountAuthenticatorResponse(response), accountType, authTokenType, features, options);
                if (Log.isLoggable(AbstractAccountAuthenticator.TAG, 2)) {
                    result.keySet();
                    Log.v(AbstractAccountAuthenticator.TAG, "addAccount: result " + AccountManager.sanitizeResult(result));
                }
                if (result != null) {
                    response.onResult(result);
                }
            } catch (Exception e) {
                AbstractAccountAuthenticator.this.handleException(response, "addAccount", accountType, e);
            }
        }

        public void confirmCredentials(IAccountAuthenticatorResponse response, Account account, Bundle options) throws RemoteException {
            if (Log.isLoggable(AbstractAccountAuthenticator.TAG, 2)) {
                Log.v(AbstractAccountAuthenticator.TAG, "confirmCredentials: " + account);
            }
            AbstractAccountAuthenticator.this.checkBinderPermission();
            try {
                Bundle result = AbstractAccountAuthenticator.this.confirmCredentials(new AccountAuthenticatorResponse(response), account, options);
                if (Log.isLoggable(AbstractAccountAuthenticator.TAG, 2)) {
                    result.keySet();
                    Log.v(AbstractAccountAuthenticator.TAG, "confirmCredentials: result " + AccountManager.sanitizeResult(result));
                }
                if (result != null) {
                    response.onResult(result);
                }
            } catch (Exception e) {
                AbstractAccountAuthenticator.this.handleException(response, "confirmCredentials", account.toString(), e);
            }
        }

        public void getAuthTokenLabel(IAccountAuthenticatorResponse response, String authTokenType) throws RemoteException {
            if (Log.isLoggable(AbstractAccountAuthenticator.TAG, 2)) {
                Log.v(AbstractAccountAuthenticator.TAG, "getAuthTokenLabel: authTokenType " + authTokenType);
            }
            AbstractAccountAuthenticator.this.checkBinderPermission();
            try {
                Bundle result = new Bundle();
                result.putString(MiAccountManager.KEY_AUTH_TOKEN_LABEL, AbstractAccountAuthenticator.this.getAuthTokenLabel(authTokenType));
                if (Log.isLoggable(AbstractAccountAuthenticator.TAG, 2)) {
                    result.keySet();
                    Log.v(AbstractAccountAuthenticator.TAG, "getAuthTokenLabel: result " + AccountManager.sanitizeResult(result));
                }
                response.onResult(result);
            } catch (Exception e) {
                AbstractAccountAuthenticator.this.handleException(response, "getAuthTokenLabel", authTokenType, e);
            }
        }

        public void getAuthToken(IAccountAuthenticatorResponse response, Account account, String authTokenType, Bundle loginOptions) throws RemoteException {
            if (Log.isLoggable(AbstractAccountAuthenticator.TAG, 2)) {
                Log.v(AbstractAccountAuthenticator.TAG, "getAuthToken: " + account + ", authTokenType " + authTokenType);
            }
            AbstractAccountAuthenticator.this.checkBinderPermission();
            try {
                Bundle result = AbstractAccountAuthenticator.this.getAuthToken(new AccountAuthenticatorResponse(response), account, authTokenType, loginOptions);
                if (Log.isLoggable(AbstractAccountAuthenticator.TAG, 2)) {
                    result.keySet();
                    Log.v(AbstractAccountAuthenticator.TAG, "getAuthToken: result " + AccountManager.sanitizeResult(result));
                }
                if (result != null) {
                    response.onResult(result);
                }
            } catch (Exception e) {
                AbstractAccountAuthenticator.this.handleException(response, "getAuthToken", account.toString() + Constants.SPLITER_SMS_GATEWAY + authTokenType, e);
            }
        }

        public void updateCredentials(IAccountAuthenticatorResponse response, Account account, String authTokenType, Bundle loginOptions) throws RemoteException {
            if (Log.isLoggable(AbstractAccountAuthenticator.TAG, 2)) {
                Log.v(AbstractAccountAuthenticator.TAG, "updateCredentials: " + account + ", authTokenType " + authTokenType);
            }
            AbstractAccountAuthenticator.this.checkBinderPermission();
            try {
                Bundle result = AbstractAccountAuthenticator.this.updateCredentials(new AccountAuthenticatorResponse(response), account, authTokenType, loginOptions);
                if (Log.isLoggable(AbstractAccountAuthenticator.TAG, 2)) {
                    result.keySet();
                    Log.v(AbstractAccountAuthenticator.TAG, "updateCredentials: result " + AccountManager.sanitizeResult(result));
                }
                if (result != null) {
                    response.onResult(result);
                }
            } catch (Exception e) {
                AbstractAccountAuthenticator.this.handleException(response, "updateCredentials", account.toString() + Constants.SPLITER_SMS_GATEWAY + authTokenType, e);
            }
        }

        public void editProperties(IAccountAuthenticatorResponse response, String accountType) throws RemoteException {
            AbstractAccountAuthenticator.this.checkBinderPermission();
            try {
                Bundle result = AbstractAccountAuthenticator.this.editProperties(new AccountAuthenticatorResponse(response), accountType);
                if (result != null) {
                    response.onResult(result);
                }
            } catch (Exception e) {
                AbstractAccountAuthenticator.this.handleException(response, "editProperties", accountType, e);
            }
        }

        public void hasFeatures(IAccountAuthenticatorResponse response, Account account, String[] features) throws RemoteException {
            AbstractAccountAuthenticator.this.checkBinderPermission();
            try {
                Bundle result = AbstractAccountAuthenticator.this.hasFeatures(new AccountAuthenticatorResponse(response), account, features);
                if (result != null) {
                    response.onResult(result);
                }
            } catch (Exception e) {
                AbstractAccountAuthenticator.this.handleException(response, "hasFeatures", account.toString(), e);
            }
        }

        public void getAccountRemovalAllowed(IAccountAuthenticatorResponse response, Account account) throws RemoteException {
            AbstractAccountAuthenticator.this.checkBinderPermission();
            try {
                Bundle result = AbstractAccountAuthenticator.this.getAccountRemovalAllowed(new AccountAuthenticatorResponse(response), account);
                if (result != null) {
                    response.onResult(result);
                }
            } catch (Exception e) {
                AbstractAccountAuthenticator.this.handleException(response, "getAccountRemovalAllowed", account.toString(), e);
            }
        }
    }

    public abstract Bundle addAccount(AccountAuthenticatorResponse accountAuthenticatorResponse, String str, String str2, String[] strArr, Bundle bundle) throws NetworkErrorException;

    public abstract Bundle confirmCredentials(AccountAuthenticatorResponse accountAuthenticatorResponse, Account account, Bundle bundle) throws NetworkErrorException;

    public abstract Bundle editProperties(AccountAuthenticatorResponse accountAuthenticatorResponse, String str);

    public abstract Bundle getAuthToken(AccountAuthenticatorResponse accountAuthenticatorResponse, Account account, String str, Bundle bundle) throws NetworkErrorException;

    public abstract String getAuthTokenLabel(String str);

    public abstract Bundle hasFeatures(AccountAuthenticatorResponse accountAuthenticatorResponse, Account account, String[] strArr) throws NetworkErrorException;

    public abstract Bundle updateCredentials(AccountAuthenticatorResponse accountAuthenticatorResponse, Account account, String str, Bundle bundle) throws NetworkErrorException;

    public AbstractAccountAuthenticator(Context context) {
        this.mTransport = new Transport();
        this.mContext = context;
    }

    private void handleException(IAccountAuthenticatorResponse response, String method, String data, Exception e) throws RemoteException {
        if (e instanceof NetworkErrorException) {
            if (Log.isLoggable(TAG, 2)) {
                Log.v(TAG, method + "(" + data + ")", e);
            }
            response.onError(3, e.getMessage());
        } else if (e instanceof UnsupportedOperationException) {
            if (Log.isLoggable(TAG, 2)) {
                Log.v(TAG, method + "(" + data + ")", e);
            }
            response.onError(6, method + " not supported");
        } else if (e instanceof IllegalArgumentException) {
            if (Log.isLoggable(TAG, 2)) {
                Log.v(TAG, method + "(" + data + ")", e);
            }
            response.onError(7, method + " not supported");
        } else {
            Log.w(TAG, method + "(" + data + ")", e);
            response.onError(1, method + " failed");
        }
    }

    private void checkBinderPermission() {
        int uid = Binder.getCallingUid();
        if (this.mContext.getApplicationInfo().uid != uid) {
            String perm = "android.permission.ACCOUNT_MANAGER";
            if (this.mContext.checkCallingOrSelfPermission("android.permission.ACCOUNT_MANAGER") != 0) {
                throw new SecurityException("caller uid " + uid + " lacks " + "android.permission.ACCOUNT_MANAGER");
            }
        }
    }

    public final IBinder getIBinder() {
        return this.mTransport.asBinder();
    }

    public Bundle getAccountRemovalAllowed(AccountAuthenticatorResponse response, Account account) throws NetworkErrorException {
        Bundle result = new Bundle();
        result.putBoolean(MiAccountManager.KEY_BOOLEAN_RESULT, true);
        return result;
    }
}
