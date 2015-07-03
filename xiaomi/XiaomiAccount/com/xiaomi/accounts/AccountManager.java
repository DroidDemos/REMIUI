package com.xiaomi.accounts;

import android.accounts.Account;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.accounts.AuthenticatorDescription;
import android.accounts.AuthenticatorException;
import android.accounts.OnAccountsUpdateListener;
import android.accounts.OperationCanceledException;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.SQLException;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcelable;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.collect.Maps;
import com.xiaomi.accounts.IAccountManagerResponse.Stub;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class AccountManager {
    public static final String ACTION_AUTHENTICATOR_INTENT = "com.xiaomi.accounts.AccountAuthenticator";
    public static final String AUTHENTICATOR_ATTRIBUTES_NAME = "account-authenticator";
    public static final String AUTHENTICATOR_META_DATA_NAME = "com.xiaomi.accounts.AccountAuthenticator";
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
    public static final String KEY_ANDROID_PACKAGE_NAME = "androidPackageName";
    public static final String KEY_AUTHENTICATOR_TYPES = "authenticator_types";
    public static final String KEY_AUTHTOKEN = "authtoken";
    public static final String KEY_AUTH_FAILED_MESSAGE = "authFailedMessage";
    public static final String KEY_AUTH_TOKEN_LABEL = "authTokenLabelKey";
    public static final String KEY_BOOLEAN_RESULT = "booleanResult";
    public static final String KEY_CALLER_PID = "callerPid";
    public static final String KEY_CALLER_UID = "callerUid";
    public static final String KEY_ERROR_CODE = "errorCode";
    public static final String KEY_ERROR_MESSAGE = "errorMessage";
    public static final String KEY_INTENT = "intent";
    public static final String KEY_NOTIFY_ON_FAILURE = "notifyOnAuthFailure";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_USERDATA = "userdata";
    public static final String LOGIN_ACCOUNTS_CHANGED_ACTION = "android.accounts.LOGIN_ACCOUNTS_CHANGED";
    private static final String TAG = "AccountManager";
    private static volatile AccountManager sThis;
    private final BroadcastReceiver mAccountsChangedBroadcastReceiver;
    private final HashMap<OnAccountsUpdateListener, Handler> mAccountsUpdatedListeners;
    private final Context mContext;
    private final Handler mMainHandler;
    private AccountManagerService mService;

    private abstract class AmsTask extends FutureTask<Bundle> implements AccountManagerFuture<Bundle> {
        final Activity mActivity;
        final AccountManagerCallback<Bundle> mCallback;
        final Handler mHandler;
        final IAccountManagerResponse mResponse;

        class AnonymousClass1 implements Callable<Bundle> {
            final /* synthetic */ AccountManager val$this$0;

            AnonymousClass1(AccountManager accountManager) {
                this.val$this$0 = accountManager;
            }

            public Bundle call() throws Exception {
                throw new IllegalStateException("this should never be called");
            }
        }

        private class Response extends Stub {
            private Response() {
            }

            public void onResult(Bundle bundle) {
                Intent intent = (Intent) bundle.getParcelable(AccountManager.KEY_INTENT);
                if (intent != null && AmsTask.this.mActivity != null) {
                    AmsTask.this.mActivity.startActivity(intent);
                } else if (bundle.getBoolean("retry")) {
                    try {
                        AmsTask.this.doWork();
                    } catch (RemoteException e) {
                    }
                } else {
                    AmsTask.this.set(bundle);
                }
            }

            public void onError(int code, String message) {
                if (code == AccountManager.ERROR_CODE_CANCELED) {
                    AmsTask.this.cancel(true);
                } else {
                    AmsTask.this.setException(AccountManager.this.convertErrorToException(code, message));
                }
            }

            public void onRequestContinued() throws RemoteException {
            }
        }

        public abstract void doWork() throws RemoteException;

        public AmsTask(Activity activity, Handler handler, AccountManagerCallback<Bundle> callback) {
            super(new AnonymousClass1(AccountManager.this));
            this.mHandler = handler;
            this.mCallback = callback;
            this.mActivity = activity;
            this.mResponse = new Response();
        }

        public final AccountManagerFuture<Bundle> start() {
            try {
                doWork();
            } catch (RemoteException e) {
                setException(e);
            }
            return this;
        }

        protected void set(Bundle bundle) {
            if (bundle == null) {
                Log.e(AccountManager.TAG, "the bundle must not be null", new Exception());
            }
            super.set(bundle);
        }

        private Bundle internalGetResult(Long timeout, TimeUnit unit) throws OperationCanceledException, IOException, AuthenticatorException {
            Bundle bundle;
            if (!isDone()) {
                AccountManager.this.ensureNotOnMainThread();
            }
            if (timeout == null) {
                try {
                    bundle = (Bundle) get();
                    cancel(true);
                } catch (CancellationException e) {
                    throw new OperationCanceledException();
                } catch (TimeoutException e2) {
                    cancel(true);
                    throw new OperationCanceledException();
                } catch (InterruptedException e3) {
                    cancel(true);
                    throw new OperationCanceledException();
                } catch (ExecutionException e4) {
                    Throwable cause = e4.getCause();
                    if (cause instanceof IOException) {
                        throw ((IOException) cause);
                    } else if (cause instanceof UnsupportedOperationException) {
                        throw new AuthenticatorException(cause);
                    } else if (cause instanceof AuthenticatorException) {
                        throw ((AuthenticatorException) cause);
                    } else if (cause instanceof RuntimeException) {
                        throw ((RuntimeException) cause);
                    } else if (cause instanceof Error) {
                        throw ((Error) cause);
                    } else {
                        throw new IllegalStateException(cause);
                    }
                } catch (Throwable th) {
                    cancel(true);
                }
            } else {
                bundle = (Bundle) get(timeout.longValue(), unit);
                cancel(true);
            }
            return bundle;
        }

        public Bundle getResult() throws OperationCanceledException, IOException, AuthenticatorException {
            return internalGetResult(null, null);
        }

        public Bundle getResult(long timeout, TimeUnit unit) throws OperationCanceledException, IOException, AuthenticatorException {
            return internalGetResult(Long.valueOf(timeout), unit);
        }

        protected void done() {
            if (this.mCallback != null) {
                AccountManager.this.postToHandler(this.mHandler, this.mCallback, (AccountManagerFuture) this);
            }
        }
    }

    private abstract class BaseFutureTask<T> extends FutureTask<T> {
        final Handler mHandler;
        public final IAccountManagerResponse mResponse;

        class AnonymousClass1 implements Callable<T> {
            final /* synthetic */ AccountManager val$this$0;

            AnonymousClass1(AccountManager accountManager) {
                this.val$this$0 = accountManager;
            }

            public T call() throws Exception {
                throw new IllegalStateException("this should never be called");
            }
        }

        protected class Response extends Stub {
            protected Response() {
            }

            public void onResult(Bundle bundle) {
                try {
                    T result = BaseFutureTask.this.bundleToResult(bundle);
                    if (result != null) {
                        BaseFutureTask.this.set(result);
                    }
                } catch (ClassCastException e) {
                    onError(AccountManager.ERROR_CODE_INVALID_RESPONSE, "no result in response");
                } catch (AuthenticatorException e2) {
                    onError(AccountManager.ERROR_CODE_INVALID_RESPONSE, "no result in response");
                }
            }

            public void onError(int code, String message) {
                if (code == AccountManager.ERROR_CODE_CANCELED) {
                    BaseFutureTask.this.cancel(true);
                } else {
                    BaseFutureTask.this.setException(AccountManager.this.convertErrorToException(code, message));
                }
            }

            public void onRequestContinued() throws RemoteException {
            }
        }

        public abstract T bundleToResult(Bundle bundle) throws AuthenticatorException;

        public abstract void doWork() throws RemoteException;

        public BaseFutureTask(Handler handler) {
            super(new AnonymousClass1(AccountManager.this));
            this.mHandler = handler;
            this.mResponse = new Response();
        }

        protected void postRunnableToHandler(Runnable runnable) {
            (this.mHandler == null ? AccountManager.this.mMainHandler : this.mHandler).post(runnable);
        }

        protected void startTask() {
            try {
                doWork();
            } catch (RemoteException e) {
                setException(e);
            }
        }
    }

    private abstract class Future2Task<T> extends BaseFutureTask<T> implements AccountManagerFuture<T> {
        final AccountManagerCallback<T> mCallback;

        public Future2Task(Handler handler, AccountManagerCallback<T> callback) {
            super(handler);
            this.mCallback = callback;
        }

        protected void done() {
            if (this.mCallback != null) {
                postRunnableToHandler(new Runnable() {
                    public void run() {
                        Future2Task.this.mCallback.run(Future2Task.this);
                    }
                });
            }
        }

        public Future2Task<T> start() {
            startTask();
            return this;
        }

        private T internalGetResult(Long timeout, TimeUnit unit) throws OperationCanceledException, IOException, AuthenticatorException {
            T t;
            if (!isDone()) {
                AccountManager.this.ensureNotOnMainThread();
            }
            if (timeout == null) {
                try {
                    t = get();
                    cancel(true);
                } catch (InterruptedException e) {
                    cancel(true);
                    throw new OperationCanceledException();
                } catch (TimeoutException e2) {
                    cancel(true);
                    throw new OperationCanceledException();
                } catch (CancellationException e3) {
                    cancel(true);
                    throw new OperationCanceledException();
                } catch (ExecutionException e4) {
                    Throwable cause = e4.getCause();
                    if (cause instanceof IOException) {
                        throw ((IOException) cause);
                    } else if (cause instanceof UnsupportedOperationException) {
                        throw new AuthenticatorException(cause);
                    } else if (cause instanceof AuthenticatorException) {
                        throw ((AuthenticatorException) cause);
                    } else if (cause instanceof RuntimeException) {
                        throw ((RuntimeException) cause);
                    } else if (cause instanceof Error) {
                        throw ((Error) cause);
                    } else {
                        throw new IllegalStateException(cause);
                    }
                } catch (Throwable th) {
                    cancel(true);
                }
            } else {
                t = get(timeout.longValue(), unit);
                cancel(true);
            }
            return t;
        }

        public T getResult() throws OperationCanceledException, IOException, AuthenticatorException {
            return internalGetResult(null, null);
        }

        public T getResult(long timeout, TimeUnit unit) throws OperationCanceledException, IOException, AuthenticatorException {
            return internalGetResult(Long.valueOf(timeout), unit);
        }
    }

    private class GetAuthTokenByTypeAndFeaturesTask extends AmsTask implements AccountManagerCallback<Bundle> {
        final String mAccountType;
        final Bundle mAddAccountOptions;
        final String mAuthTokenType;
        final String[] mFeatures;
        volatile AccountManagerFuture<Bundle> mFuture;
        final Bundle mLoginOptions;
        final AccountManagerCallback<Bundle> mMyCallback;
        private volatile int mNumAccounts;

        GetAuthTokenByTypeAndFeaturesTask(String accountType, String authTokenType, String[] features, Activity activityForPrompting, Bundle addAccountOptions, Bundle loginOptions, AccountManagerCallback<Bundle> callback, Handler handler) {
            super(activityForPrompting, handler, callback);
            this.mFuture = null;
            this.mNumAccounts = 0;
            if (accountType == null) {
                throw new IllegalArgumentException("account type is null");
            }
            this.mAccountType = accountType;
            this.mAuthTokenType = authTokenType;
            this.mFeatures = features;
            this.mAddAccountOptions = addAccountOptions;
            this.mLoginOptions = loginOptions;
            this.mMyCallback = this;
        }

        public void doWork() throws RemoteException {
            AccountManager.this.getAccountsByTypeAndFeatures(this.mAccountType, this.mFeatures, new AccountManagerCallback<Account[]>() {
                public void run(AccountManagerFuture<Account[]> future) {
                    try {
                        Account[] accounts = (Account[]) future.getResult();
                        GetAuthTokenByTypeAndFeaturesTask.this.mNumAccounts = accounts.length;
                        Bundle result;
                        if (accounts.length == 0) {
                            if (GetAuthTokenByTypeAndFeaturesTask.this.mActivity != null) {
                                GetAuthTokenByTypeAndFeaturesTask.this.mFuture = AccountManager.this.addAccount(GetAuthTokenByTypeAndFeaturesTask.this.mAccountType, GetAuthTokenByTypeAndFeaturesTask.this.mAuthTokenType, GetAuthTokenByTypeAndFeaturesTask.this.mFeatures, GetAuthTokenByTypeAndFeaturesTask.this.mAddAccountOptions, GetAuthTokenByTypeAndFeaturesTask.this.mActivity, GetAuthTokenByTypeAndFeaturesTask.this.mMyCallback, GetAuthTokenByTypeAndFeaturesTask.this.mHandler);
                                return;
                            }
                            result = new Bundle();
                            result.putString(AccountManager.KEY_ACCOUNT_NAME, null);
                            result.putString(AccountManager.KEY_ACCOUNT_TYPE, null);
                            result.putString(AccountManager.KEY_AUTHTOKEN, null);
                            try {
                                GetAuthTokenByTypeAndFeaturesTask.this.mResponse.onResult(result);
                            } catch (RemoteException e) {
                            }
                        } else if (accounts.length == AccountManager.ERROR_CODE_REMOTE_EXCEPTION) {
                            if (GetAuthTokenByTypeAndFeaturesTask.this.mActivity == null) {
                                GetAuthTokenByTypeAndFeaturesTask.this.mFuture = AccountManager.this.getAuthToken(accounts[0], GetAuthTokenByTypeAndFeaturesTask.this.mAuthTokenType, false, GetAuthTokenByTypeAndFeaturesTask.this.mMyCallback, GetAuthTokenByTypeAndFeaturesTask.this.mHandler);
                                return;
                            }
                            GetAuthTokenByTypeAndFeaturesTask.this.mFuture = AccountManager.this.getAuthToken(accounts[0], GetAuthTokenByTypeAndFeaturesTask.this.mAuthTokenType, GetAuthTokenByTypeAndFeaturesTask.this.mLoginOptions, GetAuthTokenByTypeAndFeaturesTask.this.mActivity, GetAuthTokenByTypeAndFeaturesTask.this.mMyCallback, GetAuthTokenByTypeAndFeaturesTask.this.mHandler);
                        } else if (GetAuthTokenByTypeAndFeaturesTask.this.mActivity != null) {
                            IAccountManagerResponse chooseResponse = new Stub() {
                                public void onResult(Bundle value) throws RemoteException {
                                    Account account = new Account(value.getString(AccountManager.KEY_ACCOUNT_NAME), value.getString(AccountManager.KEY_ACCOUNT_TYPE));
                                    GetAuthTokenByTypeAndFeaturesTask.this.mFuture = AccountManager.this.getAuthToken(account, GetAuthTokenByTypeAndFeaturesTask.this.mAuthTokenType, GetAuthTokenByTypeAndFeaturesTask.this.mLoginOptions, GetAuthTokenByTypeAndFeaturesTask.this.mActivity, GetAuthTokenByTypeAndFeaturesTask.this.mMyCallback, GetAuthTokenByTypeAndFeaturesTask.this.mHandler);
                                }

                                public void onError(int errorCode, String errorMessage) throws RemoteException {
                                    GetAuthTokenByTypeAndFeaturesTask.this.mResponse.onError(errorCode, errorMessage);
                                }

                                public void onRequestContinued() throws RemoteException {
                                }
                            };
                            Intent intent = new Intent();
                            intent.setClassName("android", "android.accounts.ChooseAccountActivity");
                            intent.putExtra(AccountManager.KEY_ACCOUNTS, accounts);
                            intent.putExtra(AccountManager.KEY_ACCOUNT_MANAGER_RESPONSE, new AccountManagerResponse(chooseResponse));
                            GetAuthTokenByTypeAndFeaturesTask.this.mActivity.startActivity(intent);
                        } else {
                            result = new Bundle();
                            result.putString(AccountManager.KEY_ACCOUNTS, null);
                            try {
                                GetAuthTokenByTypeAndFeaturesTask.this.mResponse.onResult(result);
                            } catch (RemoteException e2) {
                            }
                        }
                    } catch (OperationCanceledException e3) {
                        GetAuthTokenByTypeAndFeaturesTask.this.setException(e3);
                    } catch (IOException e4) {
                        GetAuthTokenByTypeAndFeaturesTask.this.setException(e4);
                    } catch (AuthenticatorException e5) {
                        GetAuthTokenByTypeAndFeaturesTask.this.setException(e5);
                    }
                }
            }, this.mHandler);
        }

        public void run(AccountManagerFuture<Bundle> future) {
            try {
                Bundle result = (Bundle) future.getResult();
                if (this.mNumAccounts == 0) {
                    String accountName = result.getString(AccountManager.KEY_ACCOUNT_NAME);
                    String accountType = result.getString(AccountManager.KEY_ACCOUNT_TYPE);
                    if (TextUtils.isEmpty(accountName) || TextUtils.isEmpty(accountType)) {
                        setException(new AuthenticatorException("account not in result"));
                        return;
                    }
                    Account account = new Account(accountName, accountType);
                    this.mNumAccounts = AccountManager.ERROR_CODE_REMOTE_EXCEPTION;
                    AccountManager.this.getAuthToken(account, this.mAuthTokenType, null, this.mActivity, this.mMyCallback, this.mHandler);
                    return;
                }
                set(result);
            } catch (OperationCanceledException e) {
                cancel(true);
            } catch (IOException e2) {
                setException(e2);
            } catch (AuthenticatorException e3) {
                setException(e3);
            }
        }
    }

    private AccountManager(Context context) {
        this.mAccountsUpdatedListeners = Maps.newHashMap();
        this.mAccountsChangedBroadcastReceiver = new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                Account[] accounts = AccountManager.this.getAccounts();
                synchronized (AccountManager.this.mAccountsUpdatedListeners) {
                    for (Entry<OnAccountsUpdateListener, Handler> entry : AccountManager.this.mAccountsUpdatedListeners.entrySet()) {
                        AccountManager.this.postToHandler((Handler) entry.getValue(), (OnAccountsUpdateListener) entry.getKey(), accounts);
                    }
                }
            }
        };
        this.mContext = context;
        this.mMainHandler = new Handler(this.mContext.getMainLooper());
        this.mService = new AccountManagerService(context);
    }

    public static Bundle sanitizeResult(Bundle result) {
        if (result == null || !result.containsKey(KEY_AUTHTOKEN) || TextUtils.isEmpty(result.getString(KEY_AUTHTOKEN))) {
            return result;
        }
        Bundle newResult = new Bundle(result);
        newResult.putString(KEY_AUTHTOKEN, "<omitted for logging purposes>");
        return newResult;
    }

    public static AccountManager get(Context context) {
        if (context == null) {
            throw new IllegalArgumentException("context is null");
        }
        if (sThis == null) {
            synchronized (AccountManager.class) {
                if (sThis == null) {
                    sThis = new AccountManager(context.getApplicationContext());
                }
            }
        }
        return sThis;
    }

    public String getPassword(Account account) {
        if (account != null) {
            return this.mService.getPassword(account);
        }
        throw new IllegalArgumentException("account is null");
    }

    public String getUserData(Account account, String key) {
        if (account == null) {
            throw new IllegalArgumentException("account is null");
        } else if (key != null) {
            return this.mService.getUserData(account, key);
        } else {
            throw new IllegalArgumentException("key is null");
        }
    }

    public AuthenticatorDescription[] getAuthenticatorTypes() {
        return this.mService.getAuthenticatorTypes();
    }

    public Account[] getAccounts() {
        return this.mService.getAccounts(null);
    }

    public Account[] getAccountsByType(String type) {
        return this.mService.getAccounts(type);
    }

    public void updateAppPermission(Account account, String authTokenType, int uid, boolean value) {
        try {
            this.mService.updateAppPermission(account, authTokenType, uid, value);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public AccountManagerFuture<String> getAuthTokenLabel(String accountType, String authTokenType, AccountManagerCallback<String> callback, Handler handler) {
        if (accountType == null) {
            throw new IllegalArgumentException("accountType is null");
        } else if (authTokenType == null) {
            throw new IllegalArgumentException("authTokenType is null");
        } else {
            final String str = accountType;
            final String str2 = authTokenType;
            return new Future2Task<String>(handler, callback) {
                public void doWork() throws RemoteException {
                    AccountManager.this.mService.getAuthTokenLabel(this.mResponse, str, str2);
                }

                public String bundleToResult(Bundle bundle) throws AuthenticatorException {
                    if (bundle.containsKey(AccountManager.KEY_AUTH_TOKEN_LABEL)) {
                        return bundle.getString(AccountManager.KEY_AUTH_TOKEN_LABEL);
                    }
                    throw new AuthenticatorException("no result in response");
                }
            }.start();
        }
    }

    public AccountManagerFuture<Boolean> hasFeatures(Account account, String[] features, AccountManagerCallback<Boolean> callback, Handler handler) {
        if (account == null) {
            throw new IllegalArgumentException("account is null");
        } else if (features == null) {
            throw new IllegalArgumentException("features is null");
        } else {
            final Account account2 = account;
            final String[] strArr = features;
            return new Future2Task<Boolean>(handler, callback) {
                public void doWork() throws RemoteException {
                    AccountManager.this.mService.hasFeatures(this.mResponse, account2, strArr);
                }

                public Boolean bundleToResult(Bundle bundle) throws AuthenticatorException {
                    if (bundle.containsKey(AccountManager.KEY_BOOLEAN_RESULT)) {
                        return Boolean.valueOf(bundle.getBoolean(AccountManager.KEY_BOOLEAN_RESULT));
                    }
                    throw new AuthenticatorException("no result in response");
                }
            }.start();
        }
    }

    public AccountManagerFuture<Account[]> getAccountsByTypeAndFeatures(String type, String[] features, AccountManagerCallback<Account[]> callback, Handler handler) {
        if (type == null) {
            throw new IllegalArgumentException("type is null");
        }
        final String str = type;
        final String[] strArr = features;
        return new Future2Task<Account[]>(handler, callback) {
            public void doWork() throws RemoteException {
                AccountManager.this.mService.getAccountsByFeatures(this.mResponse, str, strArr);
            }

            public Account[] bundleToResult(Bundle bundle) throws AuthenticatorException {
                if (bundle.containsKey(AccountManager.KEY_ACCOUNTS)) {
                    Parcelable[] parcelables = bundle.getParcelableArray(AccountManager.KEY_ACCOUNTS);
                    Account[] descs = new Account[parcelables.length];
                    for (int i = 0; i < parcelables.length; i += AccountManager.ERROR_CODE_REMOTE_EXCEPTION) {
                        descs[i] = (Account) parcelables[i];
                    }
                    return descs;
                }
                throw new AuthenticatorException("no result in response");
            }
        }.start();
    }

    public boolean addAccountExplicitly(Account account, String password, Bundle userdata) {
        if (account != null) {
            return this.mService.addAccount(account, password, userdata);
        }
        throw new IllegalArgumentException("account is null");
    }

    public AccountManagerFuture<Boolean> removeAccount(final Account account, AccountManagerCallback<Boolean> callback, Handler handler) {
        if (account != null) {
            return new Future2Task<Boolean>(handler, callback) {
                public void doWork() throws RemoteException {
                    AccountManager.this.mService.removeAccount(this.mResponse, account);
                }

                public Boolean bundleToResult(Bundle bundle) throws AuthenticatorException {
                    if (bundle.containsKey(AccountManager.KEY_BOOLEAN_RESULT)) {
                        return Boolean.valueOf(bundle.getBoolean(AccountManager.KEY_BOOLEAN_RESULT));
                    }
                    throw new AuthenticatorException("no result in response");
                }
            }.start();
        }
        throw new IllegalArgumentException("account is null");
    }

    public void invalidateAuthToken(String accountType, String authToken) {
        if (accountType == null) {
            throw new IllegalArgumentException("accountType is null");
        } else if (authToken != null) {
            this.mService.invalidateAuthToken(accountType, authToken);
        }
    }

    public String peekAuthToken(Account account, String authTokenType) {
        if (account == null) {
            throw new IllegalArgumentException("account is null");
        } else if (authTokenType != null) {
            return this.mService.peekAuthToken(account, authTokenType);
        } else {
            throw new IllegalArgumentException("authTokenType is null");
        }
    }

    public void setPassword(Account account, String password) {
        if (account == null) {
            throw new IllegalArgumentException("account is null");
        }
        this.mService.setPassword(account, password);
    }

    public void clearPassword(Account account) {
        if (account == null) {
            throw new IllegalArgumentException("account is null");
        }
        this.mService.clearPassword(account);
    }

    public void setUserData(Account account, String key, String value) {
        if (account == null) {
            throw new IllegalArgumentException("account is null");
        } else if (key == null) {
            throw new IllegalArgumentException("key is null");
        } else {
            this.mService.setUserData(account, key, value);
        }
    }

    public void setAuthToken(Account account, String authTokenType, String authToken) {
        if (account == null) {
            throw new IllegalArgumentException("account is null");
        } else if (authTokenType == null) {
            throw new IllegalArgumentException("authTokenType is null");
        } else {
            this.mService.setAuthToken(account, authTokenType, authToken);
        }
    }

    public String blockingGetAuthToken(Account account, String authTokenType, boolean notifyAuthFailure) throws OperationCanceledException, IOException, AuthenticatorException {
        if (account == null) {
            throw new IllegalArgumentException("account is null");
        } else if (authTokenType == null) {
            throw new IllegalArgumentException("authTokenType is null");
        } else {
            Bundle bundle = (Bundle) getAuthToken(account, authTokenType, notifyAuthFailure, null, null).getResult();
            if (bundle != null) {
                return bundle.getString(KEY_AUTHTOKEN);
            }
            Log.e(TAG, "blockingGetAuthToken: null was returned from getResult() for " + account + ", authTokenType " + authTokenType);
            return null;
        }
    }

    public AccountManagerFuture<Bundle> getAuthToken(Account account, String authTokenType, Bundle options, Activity activity, AccountManagerCallback<Bundle> callback, Handler handler) {
        if (account == null) {
            throw new IllegalArgumentException("account is null");
        } else if (authTokenType == null) {
            throw new IllegalArgumentException("authTokenType is null");
        } else {
            final Bundle optionsIn = new Bundle();
            if (options != null) {
                optionsIn.putAll(options);
            }
            optionsIn.putString(KEY_ANDROID_PACKAGE_NAME, this.mContext.getPackageName());
            final Account account2 = account;
            final String str = authTokenType;
            return new AmsTask(activity, handler, callback) {
                public void doWork() throws RemoteException {
                    AccountManager.this.mService.getAuthToken(this.mResponse, account2, str, false, true, optionsIn);
                }
            }.start();
        }
    }

    @Deprecated
    public AccountManagerFuture<Bundle> getAuthToken(Account account, String authTokenType, boolean notifyAuthFailure, AccountManagerCallback<Bundle> callback, Handler handler) {
        return getAuthToken(account, authTokenType, null, notifyAuthFailure, (AccountManagerCallback) callback, handler);
    }

    public AccountManagerFuture<Bundle> getAuthToken(Account account, String authTokenType, Bundle options, boolean notifyAuthFailure, AccountManagerCallback<Bundle> callback, Handler handler) {
        if (account == null) {
            throw new IllegalArgumentException("account is null");
        } else if (authTokenType == null) {
            throw new IllegalArgumentException("authTokenType is null");
        } else {
            final Bundle optionsIn = new Bundle();
            if (options != null) {
                optionsIn.putAll(options);
            }
            optionsIn.putString(KEY_ANDROID_PACKAGE_NAME, this.mContext.getPackageName());
            final Account account2 = account;
            final String str = authTokenType;
            final boolean z = notifyAuthFailure;
            return new AmsTask(null, handler, callback) {
                public void doWork() throws RemoteException {
                    AccountManager.this.mService.getAuthToken(this.mResponse, account2, str, z, false, optionsIn);
                }
            }.start();
        }
    }

    public AccountManagerFuture<Bundle> addAccount(String accountType, String authTokenType, String[] requiredFeatures, Bundle addAccountOptions, Activity activity, AccountManagerCallback<Bundle> callback, Handler handler) {
        if (accountType == null) {
            throw new IllegalArgumentException("accountType is null");
        }
        final Bundle optionsIn = new Bundle();
        if (addAccountOptions != null) {
            optionsIn.putAll(addAccountOptions);
        }
        optionsIn.putString(KEY_ANDROID_PACKAGE_NAME, this.mContext.getPackageName());
        final String str = accountType;
        final String str2 = authTokenType;
        final String[] strArr = requiredFeatures;
        final Activity activity2 = activity;
        return new AmsTask(activity, handler, callback) {
            public void doWork() throws RemoteException {
                AccountManager.this.mService.addAcount(this.mResponse, str, str2, strArr, activity2 != null, optionsIn);
            }
        }.start();
    }

    public AccountManagerFuture<Bundle> confirmCredentials(Account account, Bundle options, Activity activity, AccountManagerCallback<Bundle> callback, Handler handler) {
        if (account == null) {
            throw new IllegalArgumentException("account is null");
        }
        final Account account2 = account;
        final Bundle bundle = options;
        final Activity activity2 = activity;
        return new AmsTask(activity, handler, callback) {
            public void doWork() throws RemoteException {
                AccountManager.this.mService.confirmCredentials(this.mResponse, account2, bundle, activity2 != null);
            }
        }.start();
    }

    public AccountManagerFuture<Bundle> updateCredentials(Account account, String authTokenType, Bundle options, Activity activity, AccountManagerCallback<Bundle> callback, Handler handler) {
        if (account == null) {
            throw new IllegalArgumentException("account is null");
        }
        final Account account2 = account;
        final String str = authTokenType;
        final Activity activity2 = activity;
        final Bundle bundle = options;
        return new AmsTask(activity, handler, callback) {
            public void doWork() throws RemoteException {
                AccountManager.this.mService.updateCredentials(this.mResponse, account2, str, activity2 != null, bundle);
            }
        }.start();
    }

    public AccountManagerFuture<Bundle> editProperties(String accountType, Activity activity, AccountManagerCallback<Bundle> callback, Handler handler) {
        if (accountType == null) {
            throw new IllegalArgumentException("accountType is null");
        }
        final String str = accountType;
        final Activity activity2 = activity;
        return new AmsTask(activity, handler, callback) {
            public void doWork() throws RemoteException {
                AccountManager.this.mService.editProperties(this.mResponse, str, activity2 != null);
            }
        }.start();
    }

    private void ensureNotOnMainThread() {
        Looper looper = Looper.myLooper();
        if (looper != null && looper == this.mContext.getMainLooper()) {
            IllegalStateException exception = new IllegalStateException("calling this from your main thread can lead to deadlock");
            Log.e(TAG, "calling this from your main thread can lead to deadlock and/or ANRs", exception);
            if (this.mContext.getApplicationInfo().targetSdkVersion >= ERROR_CODE_BAD_REQUEST) {
                throw exception;
            }
        }
    }

    private void postToHandler(Handler handler, final AccountManagerCallback<Bundle> callback, final AccountManagerFuture<Bundle> future) {
        if (handler == null) {
            handler = this.mMainHandler;
        }
        handler.post(new Runnable() {
            public void run() {
                callback.run(future);
            }
        });
    }

    private void postToHandler(Handler handler, final OnAccountsUpdateListener listener, Account[] accounts) {
        final Account[] accountsCopy = new Account[accounts.length];
        System.arraycopy(accounts, 0, accountsCopy, 0, accountsCopy.length);
        if (handler == null) {
            handler = this.mMainHandler;
        }
        handler.post(new Runnable() {
            public void run() {
                try {
                    listener.onAccountsUpdated(accountsCopy);
                } catch (SQLException e) {
                    Log.e(AccountManager.TAG, "Can't update accounts", e);
                }
            }
        });
    }

    private Exception convertErrorToException(int code, String message) {
        if (code == ERROR_CODE_NETWORK_ERROR) {
            return new IOException(message);
        }
        if (code == ERROR_CODE_UNSUPPORTED_OPERATION) {
            return new UnsupportedOperationException(message);
        }
        if (code == ERROR_CODE_INVALID_RESPONSE) {
            return new AuthenticatorException(message);
        }
        if (code == ERROR_CODE_BAD_ARGUMENTS) {
            return new IllegalArgumentException(message);
        }
        return new AuthenticatorException(message);
    }

    public AccountManagerFuture<Bundle> getAuthTokenByFeatures(String accountType, String authTokenType, String[] features, Activity activity, Bundle addAccountOptions, Bundle getAuthTokenOptions, AccountManagerCallback<Bundle> callback, Handler handler) {
        if (accountType == null) {
            throw new IllegalArgumentException("account type is null");
        } else if (authTokenType == null) {
            throw new IllegalArgumentException("authTokenType is null");
        } else {
            GetAuthTokenByTypeAndFeaturesTask task = new GetAuthTokenByTypeAndFeaturesTask(accountType, authTokenType, features, activity, addAccountOptions, getAuthTokenOptions, callback, handler);
            task.start();
            return task;
        }
    }

    public void addOnAccountsUpdatedListener(OnAccountsUpdateListener listener, Handler handler, boolean updateImmediately) {
        if (listener == null) {
            throw new IllegalArgumentException("the listener is null");
        }
        synchronized (this.mAccountsUpdatedListeners) {
            if (this.mAccountsUpdatedListeners.containsKey(listener)) {
                throw new IllegalStateException("this listener is already added");
            }
            boolean wasEmpty = this.mAccountsUpdatedListeners.isEmpty();
            this.mAccountsUpdatedListeners.put(listener, handler);
            if (wasEmpty) {
                IntentFilter intentFilter = new IntentFilter();
                intentFilter.addAction(LOGIN_ACCOUNTS_CHANGED_ACTION);
                intentFilter.addAction("android.intent.action.DEVICE_STORAGE_OK");
                this.mContext.registerReceiver(this.mAccountsChangedBroadcastReceiver, intentFilter);
            }
        }
        if (updateImmediately) {
            postToHandler(handler, listener, getAccounts());
        }
    }

    public void removeOnAccountsUpdatedListener(OnAccountsUpdateListener listener) {
        if (listener == null) {
            throw new IllegalArgumentException("listener is null");
        }
        synchronized (this.mAccountsUpdatedListeners) {
            if (this.mAccountsUpdatedListeners.containsKey(listener)) {
                this.mAccountsUpdatedListeners.remove(listener);
                if (this.mAccountsUpdatedListeners.isEmpty()) {
                    this.mContext.unregisterReceiver(this.mAccountsChangedBroadcastReceiver);
                }
                return;
            }
            Log.e(TAG, "Listener was not previously added");
        }
    }
}
