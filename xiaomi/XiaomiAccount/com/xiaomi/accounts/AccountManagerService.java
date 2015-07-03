package com.xiaomi.accounts;

import android.accounts.Account;
import android.accounts.AuthenticatorDescription;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.IBinder.DeathRecipient;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import android.util.SparseArray;
import com.xiaomi.account.R;
import com.xiaomi.accounts.AccountAuthenticatorCache.ServiceInfo;
import com.xiaomi.accounts.IAccountAuthenticatorResponse.Stub;
import com.xiaomi.passport.Constants;
import com.xiaomi.passport.accountmanager.MiAccountManager;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class AccountManagerService {
    private static final Intent ACCOUNTS_CHANGED_INTENT;
    private static final String ACCOUNTS_ID = "_id";
    private static final String ACCOUNTS_NAME = "name";
    private static final String ACCOUNTS_PASSWORD = "password";
    private static final String ACCOUNTS_TYPE = "type";
    private static final String ACCOUNTS_TYPE_COUNT = "count(type)";
    private static final String[] ACCOUNT_TYPE_COUNT_PROJECTION;
    private static final String AUTHTOKENS_ACCOUNTS_ID = "accounts_id";
    private static final String AUTHTOKENS_AUTHTOKEN = "authtoken";
    private static final String AUTHTOKENS_ID = "_id";
    private static final String AUTHTOKENS_TYPE = "type";
    private static final String[] COLUMNS_AUTHTOKENS_TYPE_AND_AUTHTOKEN;
    private static final String[] COLUMNS_EXTRAS_KEY_AND_VALUE;
    private static final String COUNT_OF_MATCHING_GRANTS = "SELECT COUNT(*) FROM grants, accounts WHERE accounts_id=_id AND uid=? AND auth_token_type=? AND name=? AND type=?";
    private static final String DATABASE_NAME = "accounts.db";
    private static final int DATABASE_VERSION = 4;
    private static final Account[] EMPTY_ACCOUNT_ARRAY;
    private static final String EXTRAS_ACCOUNTS_ID = "accounts_id";
    private static final String EXTRAS_ID = "_id";
    private static final String EXTRAS_KEY = "key";
    private static final String EXTRAS_VALUE = "value";
    private static final String GRANTS_ACCOUNTS_ID = "accounts_id";
    private static final String GRANTS_AUTH_TOKEN_TYPE = "auth_token_type";
    private static final String GRANTS_GRANTEE_UID = "uid";
    private static final int MESSAGE_TIMED_OUT = 3;
    private static final String META_KEY = "key";
    private static final String META_VALUE = "value";
    private static final ExecutorService REMOTE_THREAD_POOL_EXECUTOR;
    private static final String SELECTION_AUTHTOKENS_BY_ACCOUNT = "accounts_id=(select _id FROM accounts WHERE name=? AND type=?)";
    private static final String SELECTION_USERDATA_BY_ACCOUNT = "accounts_id=(select _id FROM accounts WHERE name=? AND type=?)";
    private static final String TABLE_ACCOUNTS = "accounts";
    private static final String TABLE_AUTHTOKENS = "authtokens";
    private static final String TABLE_EXTRAS = "extras";
    private static final String TABLE_GRANTS = "grants";
    private static final String TABLE_META = "meta";
    private static final String TAG = "AccountManagerService";
    private static final int TIMEOUT_DELAY_MS = 60000;
    private static AtomicReference<AccountManagerService> sThis;
    private final AccountAuthenticatorCache mAuthenticatorCache;
    private final Context mContext;
    private final MessageHandler mMessageHandler;
    private HandlerThread mMessageThread;
    private final AtomicInteger mNotificationIds;
    private final PackageManager mPackageManager;
    private final LinkedHashMap<String, Session> mSessions;
    private final SparseArray<UserAccounts> mUsers;

    private abstract class Session extends Stub implements DeathRecipient, ServiceConnection {
        final String mAccountType;
        protected final UserAccounts mAccounts;
        IAccountAuthenticator mAuthenticator;
        final long mCreationTime;
        final boolean mExpectActivityLaunch;
        private int mNumErrors;
        private int mNumRequestContinued;
        public int mNumResults;
        IAccountManagerResponse mResponse;
        private final boolean mStripAuthTokenFromResult;

        public abstract void run() throws RemoteException;

        public Session(UserAccounts accounts, IAccountManagerResponse response, String accountType, boolean expectActivityLaunch, boolean stripAuthTokenFromResult) {
            this.mNumResults = 0;
            this.mNumRequestContinued = 0;
            this.mNumErrors = 0;
            this.mAuthenticator = null;
            if (response == null) {
                throw new IllegalArgumentException("response is null");
            } else if (accountType == null) {
                throw new IllegalArgumentException("accountType is null");
            } else {
                this.mAccounts = accounts;
                this.mStripAuthTokenFromResult = stripAuthTokenFromResult;
                this.mResponse = response;
                this.mAccountType = accountType;
                this.mExpectActivityLaunch = expectActivityLaunch;
                this.mCreationTime = SystemClock.elapsedRealtime();
                synchronized (AccountManagerService.this.mSessions) {
                    AccountManagerService.this.mSessions.put(toString(), this);
                }
                try {
                    response.asBinder().linkToDeath(this, 0);
                } catch (RemoteException e) {
                    this.mResponse = null;
                    binderDied();
                }
            }
        }

        IAccountManagerResponse getResponseAndClose() {
            if (this.mResponse == null) {
                return null;
            }
            IAccountManagerResponse response = this.mResponse;
            close();
            return response;
        }

        private void close() {
            synchronized (AccountManagerService.this.mSessions) {
                if (AccountManagerService.this.mSessions.remove(toString()) == null) {
                    return;
                }
                if (this.mResponse != null) {
                    this.mResponse.asBinder().unlinkToDeath(this, 0);
                    this.mResponse = null;
                }
                cancelTimeout();
                unbind();
            }
        }

        public void binderDied() {
            this.mResponse = null;
            close();
        }

        protected String toDebugString() {
            return toDebugString(SystemClock.elapsedRealtime());
        }

        protected String toDebugString(long now) {
            return "Session: expectLaunch " + this.mExpectActivityLaunch + ", connected " + (this.mAuthenticator != null) + ", stats (" + this.mNumResults + "/" + this.mNumRequestContinued + "/" + this.mNumErrors + ")" + ", lifetime " + (((double) (now - this.mCreationTime)) / 1000.0d);
        }

        void bind() {
            if (Log.isLoggable(AccountManagerService.TAG, 2)) {
                Log.v(AccountManagerService.TAG, "initiating bind to authenticator type " + this.mAccountType);
            }
            if (!bindToAuthenticator(this.mAccountType)) {
                Log.d(AccountManagerService.TAG, "bind attempt failed for " + toDebugString());
                onError(1, "bind failure");
            }
        }

        private void unbind() {
            if (this.mAuthenticator != null) {
                this.mAuthenticator = null;
                AccountManagerService.this.mContext.unbindService(this);
            }
        }

        public void scheduleTimeout() {
            AccountManagerService.this.mMessageHandler.sendMessageDelayed(AccountManagerService.this.mMessageHandler.obtainMessage(AccountManagerService.MESSAGE_TIMED_OUT, this), 60000);
        }

        public void cancelTimeout() {
            AccountManagerService.this.mMessageHandler.removeMessages(AccountManagerService.MESSAGE_TIMED_OUT, this);
        }

        public void onServiceConnected(ComponentName name, IBinder service) {
            this.mAuthenticator = IAccountAuthenticator.Stub.asInterface(service);
            AccountManagerService.REMOTE_THREAD_POOL_EXECUTOR.execute(new Runnable() {
                public void run() {
                    try {
                        Session.this.run();
                    } catch (RemoteException e) {
                        Session.this.onError(1, "remote exception");
                    }
                }
            });
        }

        public void onServiceDisconnected(ComponentName name) {
            this.mAuthenticator = null;
            IAccountManagerResponse response = getResponseAndClose();
            if (response != null) {
                try {
                    response.onError(1, "disconnected");
                } catch (RemoteException e) {
                    if (Log.isLoggable(AccountManagerService.TAG, 2)) {
                        Log.v(AccountManagerService.TAG, "Session.onServiceDisconnected: caught RemoteException while responding", e);
                    }
                }
            }
        }

        public void onTimedOut() {
            IAccountManagerResponse response = getResponseAndClose();
            if (response != null) {
                try {
                    response.onError(1, "timeout");
                } catch (RemoteException e) {
                    if (Log.isLoggable(AccountManagerService.TAG, 2)) {
                        Log.v(AccountManagerService.TAG, "Session.onTimedOut: caught RemoteException while responding", e);
                    }
                }
            }
        }

        public void onResult(Bundle result) {
            IAccountManagerResponse response;
            this.mNumResults++;
            if (!(result == null || TextUtils.isEmpty(result.getString(AccountManagerService.AUTHTOKENS_AUTHTOKEN)))) {
                String accountName = result.getString(MiAccountManager.KEY_ACCOUNT_NAME);
                String accountType = result.getString(MiAccountManager.KEY_ACCOUNT_TYPE);
                if (!(TextUtils.isEmpty(accountName) || TextUtils.isEmpty(accountType))) {
                    AccountManagerService.this.cancelNotification(AccountManagerService.this.getSigninRequiredNotificationId(this.mAccounts, new Account(accountName, accountType)).intValue());
                }
            }
            if (this.mExpectActivityLaunch && result != null && result.containsKey(MiAccountManager.KEY_INTENT)) {
                response = this.mResponse;
            } else {
                response = getResponseAndClose();
            }
            if (response == null) {
                return;
            }
            if (result == null) {
                try {
                    if (Log.isLoggable(AccountManagerService.TAG, 2)) {
                        Log.v(AccountManagerService.TAG, getClass().getSimpleName() + " calling onError() on response " + response);
                    }
                    response.onError(5, "null bundle returned");
                    return;
                } catch (RemoteException e) {
                    if (Log.isLoggable(AccountManagerService.TAG, 2)) {
                        Log.v(AccountManagerService.TAG, "failure while notifying response", e);
                        return;
                    }
                    return;
                }
            }
            if (this.mStripAuthTokenFromResult) {
                result.remove(AccountManagerService.AUTHTOKENS_AUTHTOKEN);
            }
            if (Log.isLoggable(AccountManagerService.TAG, 2)) {
                Log.v(AccountManagerService.TAG, getClass().getSimpleName() + " calling onResult() on response " + response);
            }
            response.onResult(result);
        }

        public void onRequestContinued() {
            this.mNumRequestContinued++;
        }

        public void onError(int errorCode, String errorMessage) {
            this.mNumErrors++;
            IAccountManagerResponse response = getResponseAndClose();
            if (response != null) {
                if (Log.isLoggable(AccountManagerService.TAG, 2)) {
                    Log.v(AccountManagerService.TAG, getClass().getSimpleName() + " calling onError() on response " + response);
                }
                try {
                    response.onError(errorCode, errorMessage);
                } catch (RemoteException e) {
                    if (Log.isLoggable(AccountManagerService.TAG, 2)) {
                        Log.v(AccountManagerService.TAG, "Session.onError: caught RemoteException while responding", e);
                    }
                }
            } else if (Log.isLoggable(AccountManagerService.TAG, 2)) {
                Log.v(AccountManagerService.TAG, "Session.onError: already closed");
            }
        }

        private boolean bindToAuthenticator(String authenticatorType) {
            ServiceInfo<AuthenticatorDescription> authenticatorInfo = AccountManagerService.this.mAuthenticatorCache.getServiceInfo(AuthenticatorDescription.newKey(authenticatorType));
            if (authenticatorInfo != null) {
                Intent intent = new Intent();
                intent.setAction(AccountManager.AUTHENTICATOR_META_DATA_NAME);
                intent.setComponent(authenticatorInfo.componentName);
                if (Log.isLoggable(AccountManagerService.TAG, 2)) {
                    Log.v(AccountManagerService.TAG, "performing bindService to " + authenticatorInfo.componentName);
                }
                if (AccountManagerService.this.mContext.bindService(intent, this, 1)) {
                    return true;
                }
                if (!Log.isLoggable(AccountManagerService.TAG, 2)) {
                    return false;
                }
                Log.v(AccountManagerService.TAG, "bindService to " + authenticatorInfo.componentName + " failed");
                return false;
            } else if (!Log.isLoggable(AccountManagerService.TAG, 2)) {
                return false;
            } else {
                Log.v(AccountManagerService.TAG, "there is no authenticator for " + authenticatorType + ", bailing out");
                return false;
            }
        }
    }

    static class DatabaseHelper extends SQLiteOpenHelper {
        public DatabaseHelper(Context context, int userId) {
            super(context, AccountManagerService.getDatabaseName(context, userId), null, AccountManagerService.DATABASE_VERSION);
        }

        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE accounts ( _id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT NOT NULL, type TEXT NOT NULL, password TEXT, UNIQUE(name,type))");
            db.execSQL("CREATE TABLE authtokens (  _id INTEGER PRIMARY KEY AUTOINCREMENT,  accounts_id INTEGER NOT NULL, type TEXT NOT NULL,  authtoken TEXT,  UNIQUE (accounts_id,type))");
            createGrantsTable(db);
            db.execSQL("CREATE TABLE extras ( _id INTEGER PRIMARY KEY AUTOINCREMENT, accounts_id INTEGER, key TEXT NOT NULL, value TEXT, UNIQUE(accounts_id,key))");
            db.execSQL("CREATE TABLE meta ( key TEXT PRIMARY KEY NOT NULL, value TEXT)");
            createAccountsDeletionTrigger(db);
        }

        private void createAccountsDeletionTrigger(SQLiteDatabase db) {
            db.execSQL(" CREATE TRIGGER accountsDelete DELETE ON accounts BEGIN   DELETE FROM authtokens     WHERE accounts_id=OLD._id ;   DELETE FROM extras     WHERE accounts_id=OLD._id ;   DELETE FROM grants     WHERE accounts_id=OLD._id ; END");
        }

        private void createGrantsTable(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE grants (  accounts_id INTEGER NOT NULL, auth_token_type STRING NOT NULL,  uid INTEGER NOT NULL,  UNIQUE (accounts_id,auth_token_type,uid))");
        }

        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.e(AccountManagerService.TAG, "upgrade from version " + oldVersion + " to version " + newVersion);
            if (oldVersion == 1) {
                oldVersion++;
            }
            if (oldVersion == 2) {
                createGrantsTable(db);
                db.execSQL("DROP TRIGGER accountsDelete");
                createAccountsDeletionTrigger(db);
                oldVersion++;
            }
            if (oldVersion == AccountManagerService.MESSAGE_TIMED_OUT) {
                db.execSQL("UPDATE accounts SET type = 'com.google' WHERE type == 'com.google.GAIA'");
                oldVersion++;
            }
        }

        public void onOpen(SQLiteDatabase db) {
            if (Log.isLoggable(AccountManagerService.TAG, 2)) {
                Log.v(AccountManagerService.TAG, "opened database accounts.db");
            }
        }
    }

    private class GetAccountsByTypeAndFeatureSession extends Session {
        private volatile Account[] mAccountsOfType;
        private volatile ArrayList<Account> mAccountsWithFeatures;
        private volatile int mCurrentAccount;
        private final String[] mFeatures;

        public GetAccountsByTypeAndFeatureSession(UserAccounts accounts, IAccountManagerResponse response, String type, String[] features) {
            super(accounts, response, type, false, true);
            this.mAccountsOfType = null;
            this.mAccountsWithFeatures = null;
            this.mCurrentAccount = 0;
            this.mFeatures = features;
        }

        public void run() throws RemoteException {
            synchronized (this.mAccounts.cacheLock) {
                this.mAccountsOfType = AccountManagerService.this.getAccountsFromCacheLocked(this.mAccounts, this.mAccountType);
            }
            this.mAccountsWithFeatures = new ArrayList(this.mAccountsOfType.length);
            this.mCurrentAccount = 0;
            checkAccount();
        }

        public void checkAccount() {
            if (this.mCurrentAccount >= this.mAccountsOfType.length) {
                sendResult();
                return;
            }
            IAccountAuthenticator accountAuthenticator = this.mAuthenticator;
            if (accountAuthenticator != null) {
                try {
                    accountAuthenticator.hasFeatures(this, this.mAccountsOfType[this.mCurrentAccount], this.mFeatures);
                } catch (RemoteException e) {
                    onError(1, "remote exception");
                }
            } else if (Log.isLoggable(AccountManagerService.TAG, 2)) {
                Log.v(AccountManagerService.TAG, "checkAccount: aborting session since we are no longer connected to the authenticator, " + toDebugString());
            }
        }

        public void onResult(Bundle result) {
            this.mNumResults++;
            if (result == null) {
                onError(5, "null bundle");
                return;
            }
            if (result.getBoolean(MiAccountManager.KEY_BOOLEAN_RESULT, false)) {
                this.mAccountsWithFeatures.add(this.mAccountsOfType[this.mCurrentAccount]);
            }
            this.mCurrentAccount++;
            checkAccount();
        }

        public void sendResult() {
            IAccountManagerResponse response = getResponseAndClose();
            if (response != null) {
                try {
                    Account[] accounts = new Account[this.mAccountsWithFeatures.size()];
                    for (int i = 0; i < accounts.length; i++) {
                        accounts[i] = (Account) this.mAccountsWithFeatures.get(i);
                    }
                    if (Log.isLoggable(AccountManagerService.TAG, 2)) {
                        Log.v(AccountManagerService.TAG, getClass().getSimpleName() + " calling onResult() on response " + response);
                    }
                    Bundle result = new Bundle();
                    result.putParcelableArray(AccountManagerService.TABLE_ACCOUNTS, accounts);
                    response.onResult(result);
                } catch (RemoteException e) {
                    if (Log.isLoggable(AccountManagerService.TAG, 2)) {
                        Log.v(AccountManagerService.TAG, "failure while notifying response", e);
                    }
                }
            }
        }

        protected String toDebugString(long now) {
            return super.toDebugString(now) + ", getAccountsByTypeAndFeatures" + ", " + (this.mFeatures != null ? TextUtils.join(Constants.SPLITER_SMS_GATEWAY, this.mFeatures) : null);
        }
    }

    private class MessageHandler extends Handler {
        MessageHandler(Looper looper) {
            super(looper);
        }

        public void handleMessage(Message msg) {
            switch (msg.what) {
                case AccountManagerService.MESSAGE_TIMED_OUT /*3*/:
                    msg.obj.onTimedOut();
                    return;
                default:
                    throw new IllegalStateException("unhandled message: " + msg.what);
            }
        }
    }

    private class RemoveAccountSession extends Session {
        final Account mAccount;

        public RemoveAccountSession(UserAccounts accounts, IAccountManagerResponse response, Account account) {
            super(accounts, response, account.type, false, true);
            this.mAccount = account;
        }

        protected String toDebugString(long now) {
            return super.toDebugString(now) + ", removeAccount" + ", account " + this.mAccount;
        }

        public void run() throws RemoteException {
            this.mAuthenticator.getAccountRemovalAllowed(this, this.mAccount);
        }

        public void onResult(Bundle result) {
            if (!(result == null || !result.containsKey(MiAccountManager.KEY_BOOLEAN_RESULT) || result.containsKey(MiAccountManager.KEY_INTENT))) {
                boolean removalAllowed = result.getBoolean(MiAccountManager.KEY_BOOLEAN_RESULT);
                if (removalAllowed) {
                    AccountManagerService.this.removeAccountInternal(this.mAccounts, this.mAccount);
                }
                IAccountManagerResponse response = getResponseAndClose();
                if (response != null) {
                    if (Log.isLoggable(AccountManagerService.TAG, 2)) {
                        Log.v(AccountManagerService.TAG, getClass().getSimpleName() + " calling onResult() on response " + response);
                    }
                    Bundle result2 = new Bundle();
                    result2.putBoolean(MiAccountManager.KEY_BOOLEAN_RESULT, removalAllowed);
                    try {
                        response.onResult(result2);
                    } catch (RemoteException e) {
                    }
                }
            }
            super.onResult(result);
        }
    }

    private class TestFeaturesSession extends Session {
        private final Account mAccount;
        private final String[] mFeatures;

        public TestFeaturesSession(UserAccounts accounts, IAccountManagerResponse response, Account account, String[] features) {
            super(accounts, response, account.type, false, true);
            this.mFeatures = features;
            this.mAccount = account;
        }

        public void run() throws RemoteException {
            try {
                this.mAuthenticator.hasFeatures(this, this.mAccount, this.mFeatures);
            } catch (RemoteException e) {
                onError(1, "remote exception");
            }
        }

        public void onResult(Bundle result) {
            IAccountManagerResponse response = getResponseAndClose();
            if (response == null) {
                return;
            }
            if (result == null) {
                try {
                    response.onError(5, "null bundle");
                    return;
                } catch (RemoteException e) {
                    if (Log.isLoggable(AccountManagerService.TAG, 2)) {
                        Log.v(AccountManagerService.TAG, "failure while notifying response", e);
                        return;
                    }
                    return;
                }
            }
            if (Log.isLoggable(AccountManagerService.TAG, 2)) {
                Log.v(AccountManagerService.TAG, getClass().getSimpleName() + " calling onResult() on response " + response);
            }
            Bundle newResult = new Bundle();
            newResult.putBoolean(MiAccountManager.KEY_BOOLEAN_RESULT, result.getBoolean(MiAccountManager.KEY_BOOLEAN_RESULT, false));
            response.onResult(newResult);
        }

        protected String toDebugString(long now) {
            return super.toDebugString(now) + ", hasFeatures" + ", " + this.mAccount + ", " + (this.mFeatures != null ? TextUtils.join(Constants.SPLITER_SMS_GATEWAY, this.mFeatures) : null);
        }
    }

    static class UserAccounts {
        private final HashMap<String, Account[]> accountCache;
        private HashMap<Account, HashMap<String, String>> authTokenCache;
        private final Object cacheLock;
        private final HashMap<Pair<Pair<Account, String>, Integer>, Integer> credentialsPermissionNotificationIds;
        private final DatabaseHelper openHelper;
        private final HashMap<Account, Integer> signinRequiredNotificationIds;
        private HashMap<Account, HashMap<String, String>> userDataCache;
        private final int userId;

        UserAccounts(Context context, int userId) {
            this.credentialsPermissionNotificationIds = new HashMap();
            this.signinRequiredNotificationIds = new HashMap();
            this.cacheLock = new Object();
            this.accountCache = new LinkedHashMap();
            this.userDataCache = new HashMap();
            this.authTokenCache = new HashMap();
            this.userId = userId;
            synchronized (this.cacheLock) {
                this.openHelper = new DatabaseHelper(context, userId);
            }
        }
    }

    static {
        REMOTE_THREAD_POOL_EXECUTOR = Executors.newCachedThreadPool();
        ACCOUNT_TYPE_COUNT_PROJECTION = new String[]{AUTHTOKENS_TYPE, ACCOUNTS_TYPE_COUNT};
        COLUMNS_AUTHTOKENS_TYPE_AND_AUTHTOKEN = new String[]{AUTHTOKENS_TYPE, AUTHTOKENS_AUTHTOKEN};
        COLUMNS_EXTRAS_KEY_AND_VALUE = new String[]{META_KEY, META_VALUE};
        sThis = new AtomicReference();
        EMPTY_ACCOUNT_ARRAY = new Account[0];
        ACCOUNTS_CHANGED_INTENT = new Intent(AccountManager.LOGIN_ACCOUNTS_CHANGED_ACTION);
    }

    public static AccountManagerService getSingleton() {
        return (AccountManagerService) sThis.get();
    }

    public AccountManagerService(Context context) {
        this(context, context.getPackageManager(), new AccountAuthenticatorCache(context));
    }

    public AccountManagerService(Context context, PackageManager packageManager, AccountAuthenticatorCache authenticatorCache) {
        this.mSessions = new LinkedHashMap();
        this.mNotificationIds = new AtomicInteger(1);
        this.mUsers = new SparseArray();
        this.mContext = context;
        this.mPackageManager = packageManager;
        this.mMessageThread = new HandlerThread(TAG);
        this.mMessageThread.start();
        this.mMessageHandler = new MessageHandler(this.mMessageThread.getLooper());
        this.mAuthenticatorCache = authenticatorCache;
        sThis.set(this);
        initUser(0);
    }

    private UserAccounts initUser(int userId) {
        UserAccounts accounts;
        synchronized (this.mUsers) {
            accounts = (UserAccounts) this.mUsers.get(userId);
            if (accounts == null) {
                accounts = new UserAccounts(this.mContext, userId);
                this.mUsers.append(userId, accounts);
                purgeOldGrants(accounts);
                validateAccountsAndPopulateCache(accounts);
            }
        }
        return accounts;
    }

    private void purgeOldGrantsAll() {
        synchronized (this.mUsers) {
            for (int i = 0; i < this.mUsers.size(); i++) {
                purgeOldGrants((UserAccounts) this.mUsers.valueAt(i));
            }
        }
    }

    private void purgeOldGrants(UserAccounts accounts) {
        synchronized (accounts.cacheLock) {
            SQLiteDatabase db = accounts.openHelper.getWritableDatabase();
            Cursor cursor = db.query(TABLE_GRANTS, new String[]{GRANTS_GRANTEE_UID}, null, null, GRANTS_GRANTEE_UID, null, null);
            while (cursor.moveToNext()) {
                try {
                    boolean packageExists;
                    int uid = cursor.getInt(0);
                    if (this.mPackageManager.getPackagesForUid(uid) != null) {
                        packageExists = true;
                    } else {
                        packageExists = false;
                    }
                    if (!packageExists) {
                        Log.d(TAG, "deleting grants for UID " + uid + " because its package is no longer installed");
                        db.delete(TABLE_GRANTS, "uid=?", new String[]{Integer.toString(uid)});
                    }
                } catch (Throwable th) {
                    cursor.close();
                }
            }
            cursor.close();
        }
    }

    private void validateAccountsAndPopulateCache(UserAccounts accounts) {
        synchronized (accounts.cacheLock) {
            SQLiteDatabase db = accounts.openHelper.getWritableDatabase();
            boolean accountDeleted = false;
            String str = TABLE_ACCOUNTS;
            String[] strArr = new String[MESSAGE_TIMED_OUT];
            strArr[0] = EXTRAS_ID;
            strArr[1] = AUTHTOKENS_TYPE;
            strArr[2] = ACCOUNTS_NAME;
            Cursor cursor = db.query(str, strArr, null, null, null, null, null);
            try {
                String accountType;
                ArrayList<String> accountNames;
                accounts.accountCache.clear();
                HashMap<String, ArrayList<String>> accountNamesByType = new LinkedHashMap();
                while (cursor.moveToNext()) {
                    long accountId = cursor.getLong(0);
                    accountType = cursor.getString(1);
                    String accountName = cursor.getString(2);
                    if (this.mAuthenticatorCache.getServiceInfo(AuthenticatorDescription.newKey(accountType)) == null) {
                        Log.d(TAG, "deleting account " + accountName + " because type " + accountType + " no longer has a registered authenticator");
                        db.delete(TABLE_ACCOUNTS, "_id=" + accountId, null);
                        accountDeleted = true;
                        Account account = new Account(accountName, accountType);
                        accounts.userDataCache.remove(account);
                        accounts.authTokenCache.remove(account);
                    } else {
                        accountNames = (ArrayList) accountNamesByType.get(accountType);
                        if (accountNames == null) {
                            accountNames = new ArrayList();
                            accountNamesByType.put(accountType, accountNames);
                        }
                        accountNames.add(accountName);
                    }
                }
                for (Entry<String, ArrayList<String>> cur : accountNamesByType.entrySet()) {
                    accountType = (String) cur.getKey();
                    accountNames = (ArrayList) cur.getValue();
                    Object accountsForType = new Account[accountNames.size()];
                    int i = 0;
                    Iterator i$ = accountNames.iterator();
                    while (i$.hasNext()) {
                        accountsForType[i] = new Account((String) i$.next(), accountType);
                        i++;
                    }
                    accounts.accountCache.put(accountType, accountsForType);
                }
                cursor.close();
                if (accountDeleted) {
                    sendAccountsChangedBroadcast(accounts.userId);
                }
            } catch (Throwable th) {
                cursor.close();
                if (accountDeleted) {
                    sendAccountsChangedBroadcast(accounts.userId);
                }
            }
        }
    }

    private UserAccounts getUserAccountsForCaller() {
        return getUserAccounts(UserId.getCallingUserId());
    }

    protected UserAccounts getUserAccounts(int userId) {
        UserAccounts accounts;
        synchronized (this.mUsers) {
            accounts = (UserAccounts) this.mUsers.get(userId);
            if (accounts == null) {
                accounts = initUser(userId);
                this.mUsers.append(userId, accounts);
            }
        }
        return accounts;
    }

    public String getPassword(Account account) {
        if (Log.isLoggable(TAG, 2)) {
            Log.v(TAG, "getPassword: " + account + ", caller's uid " + Binder.getCallingUid() + ", pid " + Binder.getCallingPid());
        }
        if (account == null) {
            throw new IllegalArgumentException("account is null");
        }
        checkAuthenticateAccountsPermission(account);
        UserAccounts accounts = getUserAccountsForCaller();
        long identityToken = clearCallingIdentity();
        try {
            String readPasswordInternal = readPasswordInternal(accounts, account);
            return readPasswordInternal;
        } finally {
            restoreCallingIdentity(identityToken);
        }
    }

    private String readPasswordInternal(UserAccounts accounts, Account account) {
        if (account == null) {
            return null;
        }
        synchronized (accounts.cacheLock) {
            Cursor cursor = accounts.openHelper.getReadableDatabase().query(TABLE_ACCOUNTS, new String[]{ACCOUNTS_PASSWORD}, "name=? AND type=?", new String[]{account.name, account.type}, null, null, null);
            try {
                if (cursor.moveToNext()) {
                    String string = cursor.getString(0);
                    return string;
                }
                cursor.close();
                return null;
            } finally {
                cursor.close();
            }
        }
    }

    public String getUserData(Account account, String key) {
        if (Log.isLoggable(TAG, 2)) {
            Log.v(TAG, "getUserData: " + account + ", key " + key + ", caller's uid " + Binder.getCallingUid() + ", pid " + Binder.getCallingPid());
        }
        if (account == null) {
            throw new IllegalArgumentException("account is null");
        } else if (key == null) {
            throw new IllegalArgumentException("key is null");
        } else {
            checkAuthenticateAccountsPermission(account);
            UserAccounts accounts = getUserAccountsForCaller();
            long identityToken = clearCallingIdentity();
            try {
                String readUserDataInternal = readUserDataInternal(accounts, account, key);
                return readUserDataInternal;
            } finally {
                restoreCallingIdentity(identityToken);
            }
        }
    }

    public AuthenticatorDescription[] getAuthenticatorTypes() {
        if (Log.isLoggable(TAG, 2)) {
            Log.v(TAG, "getAuthenticatorTypes: caller's uid " + Binder.getCallingUid() + ", pid " + Binder.getCallingPid());
        }
        long identityToken = clearCallingIdentity();
        try {
            Collection<ServiceInfo<AuthenticatorDescription>> authenticatorCollection = this.mAuthenticatorCache.getAllServices();
            AuthenticatorDescription[] types = new AuthenticatorDescription[authenticatorCollection.size()];
            int i = 0;
            for (ServiceInfo<AuthenticatorDescription> authenticator : authenticatorCollection) {
                types[i] = (AuthenticatorDescription) authenticator.type;
                i++;
            }
            return types;
        } finally {
            restoreCallingIdentity(identityToken);
        }
    }

    public boolean addAccount(Account account, String password, Bundle extras) {
        if (Log.isLoggable(TAG, 2)) {
            Log.v(TAG, "addAccount: " + account + ", caller's uid " + Binder.getCallingUid() + ", pid " + Binder.getCallingPid());
        }
        if (account == null) {
            throw new IllegalArgumentException("account is null");
        }
        checkAuthenticateAccountsPermission(account);
        UserAccounts accounts = getUserAccountsForCaller();
        long identityToken = clearCallingIdentity();
        try {
            boolean addAccountInternal = addAccountInternal(accounts, account, password, extras);
            return addAccountInternal;
        } finally {
            restoreCallingIdentity(identityToken);
        }
    }

    private boolean addAccountInternal(UserAccounts accounts, Account account, String password, Bundle extras) {
        if (account == null) {
            return false;
        }
        synchronized (accounts.cacheLock) {
            SQLiteDatabase db = accounts.openHelper.getWritableDatabase();
            db.beginTransaction();
            try {
                if (DatabaseUtils.longForQuery(db, "select count(*) from accounts WHERE name=? AND type=?", new String[]{account.name, account.type}) > 0) {
                    Log.w(TAG, "insertAccountIntoDatabase: " + account + ", skipping since the account already exists");
                    return false;
                }
                ContentValues values = new ContentValues();
                values.put(ACCOUNTS_NAME, account.name);
                values.put(AUTHTOKENS_TYPE, account.type);
                values.put(ACCOUNTS_PASSWORD, password);
                long accountId = db.insert(TABLE_ACCOUNTS, ACCOUNTS_NAME, values);
                if (accountId < 0) {
                    Log.w(TAG, "insertAccountIntoDatabase: " + account + ", skipping the DB insert failed");
                    db.endTransaction();
                    return false;
                }
                if (extras != null) {
                    for (String key : extras.keySet()) {
                        if (insertExtraLocked(db, accountId, key, extras.getString(key)) < 0) {
                            Log.w(TAG, "insertAccountIntoDatabase: " + account + ", skipping since insertExtra failed for key " + key);
                            db.endTransaction();
                            return false;
                        }
                    }
                }
                db.setTransactionSuccessful();
                insertAccountIntoCacheLocked(accounts, account);
                db.endTransaction();
                sendAccountsChangedBroadcast(accounts.userId);
                return true;
            } finally {
                db.endTransaction();
            }
        }
    }

    private long insertExtraLocked(SQLiteDatabase db, long accountId, String key, String value) {
        ContentValues values = new ContentValues();
        values.put(META_KEY, key);
        values.put(GRANTS_ACCOUNTS_ID, Long.valueOf(accountId));
        values.put(META_VALUE, value);
        return db.insert(TABLE_EXTRAS, META_KEY, values);
    }

    public void hasFeatures(IAccountManagerResponse response, Account account, String[] features) {
        if (Log.isLoggable(TAG, 2)) {
            Log.v(TAG, "hasFeatures: " + account + ", response " + response + ", features " + stringArrayToString(features) + ", caller's uid " + Binder.getCallingUid() + ", pid " + Binder.getCallingPid());
        }
        if (response == null) {
            throw new IllegalArgumentException("response is null");
        } else if (account == null) {
            throw new IllegalArgumentException("account is null");
        } else if (features == null) {
            throw new IllegalArgumentException("features is null");
        } else {
            checkReadAccountsPermission();
            UserAccounts accounts = getUserAccountsForCaller();
            long identityToken = clearCallingIdentity();
            try {
                new TestFeaturesSession(accounts, response, account, features).bind();
            } finally {
                restoreCallingIdentity(identityToken);
            }
        }
    }

    public void removeAccount(IAccountManagerResponse response, Account account) {
        if (Log.isLoggable(TAG, 2)) {
            Log.v(TAG, "removeAccount: " + account + ", response " + response + ", caller's uid " + Binder.getCallingUid() + ", pid " + Binder.getCallingPid());
        }
        if (response == null) {
            throw new IllegalArgumentException("response is null");
        } else if (account == null) {
            throw new IllegalArgumentException("account is null");
        } else {
            checkManageAccountsPermission();
            UserAccounts accounts = getUserAccountsForCaller();
            long identityToken = clearCallingIdentity();
            cancelNotification(getSigninRequiredNotificationId(accounts, account).intValue());
            synchronized (accounts.credentialsPermissionNotificationIds) {
                for (Pair<Pair<Account, String>, Integer> pair : accounts.credentialsPermissionNotificationIds.keySet()) {
                    if (account.equals(((Pair) pair.first).first)) {
                        cancelNotification(((Integer) accounts.credentialsPermissionNotificationIds.get(pair)).intValue());
                    }
                }
            }
            try {
                new RemoveAccountSession(accounts, response, account).bind();
            } finally {
                restoreCallingIdentity(identityToken);
            }
        }
    }

    protected void removeAccountInternal(Account account) {
        removeAccountInternal(getUserAccountsForCaller(), account);
    }

    private void removeAccountInternal(UserAccounts accounts, Account account) {
        synchronized (accounts.cacheLock) {
            accounts.openHelper.getWritableDatabase().delete(TABLE_ACCOUNTS, "name=? AND type=?", new String[]{account.name, account.type});
            removeAccountFromCacheLocked(accounts, account);
            sendAccountsChangedBroadcast(accounts.userId);
        }
    }

    public void invalidateAuthToken(String accountType, String authToken) {
        if (Log.isLoggable(TAG, 2)) {
            Log.v(TAG, "invalidateAuthToken: accountType " + accountType + ", caller's uid " + Binder.getCallingUid() + ", pid " + Binder.getCallingPid());
        }
        if (accountType == null) {
            throw new IllegalArgumentException("accountType is null");
        } else if (authToken == null) {
            throw new IllegalArgumentException("authToken is null");
        } else {
            checkManageAccountsOrUseCredentialsPermissions();
            UserAccounts accounts = getUserAccountsForCaller();
            long identityToken = clearCallingIdentity();
            try {
                synchronized (accounts.cacheLock) {
                    SQLiteDatabase db = accounts.openHelper.getWritableDatabase();
                    db.beginTransaction();
                    try {
                        invalidateAuthTokenLocked(accounts, db, accountType, authToken);
                        db.setTransactionSuccessful();
                        db.endTransaction();
                    } catch (Throwable th) {
                        db.endTransaction();
                    }
                }
            } finally {
                restoreCallingIdentity(identityToken);
            }
        }
    }

    private void invalidateAuthTokenLocked(UserAccounts accounts, SQLiteDatabase db, String accountType, String authToken) {
        if (authToken != null && accountType != null) {
            Cursor cursor = db.rawQuery("SELECT authtokens._id, accounts.name, authtokens.type FROM accounts JOIN authtokens ON accounts._id = accounts_id WHERE authtoken = ? AND accounts.type = ?", new String[]{authToken, accountType});
            while (cursor.moveToNext()) {
                try {
                    long authTokenId = cursor.getLong(0);
                    String accountName = cursor.getString(1);
                    String authTokenType = cursor.getString(2);
                    db.delete(TABLE_AUTHTOKENS, "_id=" + authTokenId, null);
                    writeAuthTokenIntoCacheLocked(accounts, db, new Account(accountName, accountType), authTokenType, null);
                } finally {
                    cursor.close();
                }
            }
        }
    }

    private boolean saveAuthTokenToDatabase(UserAccounts accounts, Account account, String type, String authToken) {
        if (account == null || type == null) {
            return false;
        }
        cancelNotification(getSigninRequiredNotificationId(accounts, account).intValue());
        synchronized (accounts.cacheLock) {
            SQLiteDatabase db = accounts.openHelper.getWritableDatabase();
            db.beginTransaction();
            try {
                long accountId = getAccountIdLocked(db, account);
                if (accountId < 0) {
                    return false;
                }
                db.delete(TABLE_AUTHTOKENS, "accounts_id=" + accountId + " AND " + AUTHTOKENS_TYPE + "=?", new String[]{type});
                ContentValues values = new ContentValues();
                values.put(GRANTS_ACCOUNTS_ID, Long.valueOf(accountId));
                values.put(AUTHTOKENS_TYPE, type);
                values.put(AUTHTOKENS_AUTHTOKEN, authToken);
                if (db.insert(TABLE_AUTHTOKENS, AUTHTOKENS_AUTHTOKEN, values) >= 0) {
                    db.setTransactionSuccessful();
                    writeAuthTokenIntoCacheLocked(accounts, db, account, type, authToken);
                    db.endTransaction();
                    return true;
                }
                db.endTransaction();
                return false;
            } finally {
                db.endTransaction();
            }
        }
    }

    public String peekAuthToken(Account account, String authTokenType) {
        if (Log.isLoggable(TAG, 2)) {
            Log.v(TAG, "peekAuthToken: " + account + ", authTokenType " + authTokenType + ", caller's uid " + Binder.getCallingUid() + ", pid " + Binder.getCallingPid());
        }
        if (account == null) {
            throw new IllegalArgumentException("account is null");
        } else if (authTokenType == null) {
            throw new IllegalArgumentException("authTokenType is null");
        } else {
            checkAuthenticateAccountsPermission(account);
            UserAccounts accounts = getUserAccountsForCaller();
            long identityToken = clearCallingIdentity();
            try {
                String readAuthTokenInternal = readAuthTokenInternal(accounts, account, authTokenType);
                return readAuthTokenInternal;
            } finally {
                restoreCallingIdentity(identityToken);
            }
        }
    }

    public void setAuthToken(Account account, String authTokenType, String authToken) {
        if (Log.isLoggable(TAG, 2)) {
            Log.v(TAG, "setAuthToken: " + account + ", authTokenType " + authTokenType + ", caller's uid " + Binder.getCallingUid() + ", pid " + Binder.getCallingPid());
        }
        if (account == null) {
            throw new IllegalArgumentException("account is null");
        } else if (authTokenType == null) {
            throw new IllegalArgumentException("authTokenType is null");
        } else {
            checkAuthenticateAccountsPermission(account);
            UserAccounts accounts = getUserAccountsForCaller();
            long identityToken = clearCallingIdentity();
            try {
                saveAuthTokenToDatabase(accounts, account, authTokenType, authToken);
            } finally {
                restoreCallingIdentity(identityToken);
            }
        }
    }

    public void setPassword(Account account, String password) {
        if (Log.isLoggable(TAG, 2)) {
            Log.v(TAG, "setPassword: " + account + ", caller's uid " + Binder.getCallingUid() + ", pid " + Binder.getCallingPid());
        }
        if (account == null) {
            throw new IllegalArgumentException("account is null");
        }
        checkAuthenticateAccountsPermission(account);
        UserAccounts accounts = getUserAccountsForCaller();
        long identityToken = clearCallingIdentity();
        try {
            setPasswordInternal(accounts, account, password);
        } finally {
            restoreCallingIdentity(identityToken);
        }
    }

    private void setPasswordInternal(UserAccounts accounts, Account account, String password) {
        if (account != null) {
            synchronized (accounts.cacheLock) {
                SQLiteDatabase db = accounts.openHelper.getWritableDatabase();
                db.beginTransaction();
                try {
                    ContentValues values = new ContentValues();
                    values.put(ACCOUNTS_PASSWORD, password);
                    if (getAccountIdLocked(db, account) >= 0) {
                        String[] argsAccountId = new String[]{String.valueOf(getAccountIdLocked(db, account))};
                        db.update(TABLE_ACCOUNTS, values, "_id=?", argsAccountId);
                        db.delete(TABLE_AUTHTOKENS, "accounts_id=?", argsAccountId);
                        accounts.authTokenCache.remove(account);
                        db.setTransactionSuccessful();
                    }
                    sendAccountsChangedBroadcast(accounts.userId);
                } finally {
                    db.endTransaction();
                }
            }
        }
    }

    private void sendAccountsChangedBroadcast(int userId) {
        Log.i(TAG, "the accounts changed, sending broadcast of " + ACCOUNTS_CHANGED_INTENT.getAction());
        ACCOUNTS_CHANGED_INTENT.setPackage(this.mContext.getPackageName());
        this.mContext.sendBroadcast(ACCOUNTS_CHANGED_INTENT);
    }

    public void clearPassword(Account account) {
        if (Log.isLoggable(TAG, 2)) {
            Log.v(TAG, "clearPassword: " + account + ", caller's uid " + Binder.getCallingUid() + ", pid " + Binder.getCallingPid());
        }
        if (account == null) {
            throw new IllegalArgumentException("account is null");
        }
        checkManageAccountsPermission();
        UserAccounts accounts = getUserAccountsForCaller();
        long identityToken = clearCallingIdentity();
        try {
            setPasswordInternal(accounts, account, null);
        } finally {
            restoreCallingIdentity(identityToken);
        }
    }

    public void setUserData(Account account, String key, String value) {
        if (Log.isLoggable(TAG, 2)) {
            Log.v(TAG, "setUserData: " + account + ", key " + key + ", caller's uid " + Binder.getCallingUid() + ", pid " + Binder.getCallingPid());
        }
        if (key == null) {
            throw new IllegalArgumentException("key is null");
        } else if (account == null) {
            throw new IllegalArgumentException("account is null");
        } else {
            checkAuthenticateAccountsPermission(account);
            UserAccounts accounts = getUserAccountsForCaller();
            long identityToken = clearCallingIdentity();
            try {
                setUserdataInternal(accounts, account, key, value);
            } finally {
                restoreCallingIdentity(identityToken);
            }
        }
    }

    private void setUserdataInternal(UserAccounts accounts, Account account, String key, String value) {
        if (account != null && key != null) {
            synchronized (accounts.cacheLock) {
                SQLiteDatabase db = accounts.openHelper.getWritableDatabase();
                db.beginTransaction();
                try {
                    long accountId = getAccountIdLocked(db, account);
                    if (accountId < 0) {
                        return;
                    }
                    long extrasId = getExtrasIdLocked(db, accountId, key);
                    if (extrasId >= 0) {
                        ContentValues values = new ContentValues();
                        values.put(META_VALUE, value);
                        if (1 != db.update(TABLE_EXTRAS, values, "_id=" + extrasId, null)) {
                            db.endTransaction();
                            return;
                        }
                    } else if (insertExtraLocked(db, accountId, key, value) < 0) {
                        db.endTransaction();
                        return;
                    }
                    writeUserDataIntoCacheLocked(accounts, db, account, key, value);
                    db.setTransactionSuccessful();
                    db.endTransaction();
                } finally {
                    db.endTransaction();
                }
            }
        }
    }

    private void onResult(IAccountManagerResponse response, Bundle result) {
        if (result == null) {
            Log.e(TAG, "the result is unexpectedly null", new Exception());
        }
        if (Log.isLoggable(TAG, 2)) {
            Log.v(TAG, getClass().getSimpleName() + " calling onResult() on response " + response);
        }
        try {
            response.onResult(result);
        } catch (RemoteException e) {
            if (Log.isLoggable(TAG, 2)) {
                Log.v(TAG, "failure while notifying response", e);
            }
        }
    }

    public void getAuthTokenLabel(IAccountManagerResponse response, String accountType, String authTokenType) throws RemoteException {
        if (accountType == null) {
            throw new IllegalArgumentException("accountType is null");
        } else if (authTokenType == null) {
            throw new IllegalArgumentException("authTokenType is null");
        } else {
            int callingUid = getCallingUid();
            clearCallingIdentity();
            if (callingUid != 1000) {
                throw new SecurityException("can only call from system");
            }
            UserAccounts accounts = getUserAccounts(UserId.getUserId(callingUid));
            long identityToken = clearCallingIdentity();
            try {
                final String str = accountType;
                final String str2 = authTokenType;
                new Session(accounts, response, accountType, false, false) {
                    protected String toDebugString(long now) {
                        return super.toDebugString(now) + ", getAuthTokenLabel" + ", " + str + ", authTokenType " + str2;
                    }

                    public void run() throws RemoteException {
                        this.mAuthenticator.getAuthTokenLabel(this, str2);
                    }

                    public void onResult(Bundle result) {
                        if (result != null) {
                            String label = result.getString(MiAccountManager.KEY_AUTH_TOKEN_LABEL);
                            Bundle bundle = new Bundle();
                            bundle.putString(MiAccountManager.KEY_AUTH_TOKEN_LABEL, label);
                            super.onResult(bundle);
                            return;
                        }
                        super.onResult(result);
                    }
                }.bind();
            } finally {
                restoreCallingIdentity(identityToken);
            }
        }
    }

    public void getAuthToken(IAccountManagerResponse response, Account account, String authTokenType, boolean notifyOnAuthFailure, boolean expectActivityLaunch, Bundle loginOptionsIn) {
        if (Log.isLoggable(TAG, 2)) {
            Log.v(TAG, "getAuthToken: " + account + ", response " + response + ", authTokenType " + authTokenType + ", notifyOnAuthFailure " + notifyOnAuthFailure + ", expectActivityLaunch " + expectActivityLaunch + ", caller's uid " + Binder.getCallingUid() + ", pid " + Binder.getCallingPid());
        }
        if (response == null) {
            throw new IllegalArgumentException("response is null");
        } else if (account == null) {
            throw new IllegalArgumentException("account is null");
        } else if (authTokenType == null) {
            throw new IllegalArgumentException("authTokenType is null");
        } else {
            Bundle loginOptions;
            checkBinderPermission("android.permission.USE_CREDENTIALS");
            UserAccounts accounts = getUserAccountsForCaller();
            ServiceInfo<AuthenticatorDescription> authenticatorInfo = this.mAuthenticatorCache.getServiceInfo(AuthenticatorDescription.newKey(account.type));
            int callerUid = Binder.getCallingUid();
            final boolean permissionGranted = permissionIsGranted(account, authTokenType, callerUid);
            if (loginOptionsIn == null) {
                loginOptions = new Bundle();
            } else {
                loginOptions = loginOptionsIn;
            }
            loginOptions.putInt(AccountManager.KEY_CALLER_UID, callerUid);
            loginOptions.putInt(AccountManager.KEY_CALLER_PID, Binder.getCallingPid());
            if (notifyOnAuthFailure) {
                loginOptions.putBoolean(AccountManager.KEY_NOTIFY_ON_FAILURE, true);
            }
            long identityToken = clearCallingIdentity();
            if (permissionGranted) {
                try {
                    String authToken = readAuthTokenInternal(accounts, account, authTokenType);
                    if (authToken != null) {
                        Bundle result = new Bundle();
                        result.putString(AUTHTOKENS_AUTHTOKEN, authToken);
                        result.putString(MiAccountManager.KEY_ACCOUNT_NAME, account.name);
                        result.putString(MiAccountManager.KEY_ACCOUNT_TYPE, account.type);
                        onResult(response, result);
                        return;
                    }
                } finally {
                    restoreCallingIdentity(identityToken);
                }
            }
            final Account account2 = account;
            final String str = authTokenType;
            final boolean z = notifyOnAuthFailure;
            new Session(accounts, response, account.type, expectActivityLaunch, false) {
                protected String toDebugString(long now) {
                    if (loginOptions != null) {
                        loginOptions.keySet();
                    }
                    return super.toDebugString(now) + ", getAuthToken" + ", " + account2 + ", authTokenType " + str + ", loginOptions " + loginOptions + ", notifyOnAuthFailure " + z;
                }

                public void run() throws RemoteException {
                    if (permissionGranted) {
                        this.mAuthenticator.getAuthToken(this, account2, str, loginOptions);
                    } else {
                        this.mAuthenticator.getAuthTokenLabel(this, str);
                    }
                }

                public void onResult(Bundle result) {
                    if (result != null) {
                        String authToken = result.getString(AccountManagerService.AUTHTOKENS_AUTHTOKEN);
                        if (authToken != null) {
                            String name = result.getString(MiAccountManager.KEY_ACCOUNT_NAME);
                            String type = result.getString(MiAccountManager.KEY_ACCOUNT_TYPE);
                            if (TextUtils.isEmpty(type) || TextUtils.isEmpty(name)) {
                                onError(5, "the type and name should not be empty");
                                return;
                            }
                            AccountManagerService.this.saveAuthTokenToDatabase(this.mAccounts, new Account(name, type), str, authToken);
                        }
                        Intent intent = (Intent) result.getParcelable(MiAccountManager.KEY_INTENT);
                        if (intent != null && z) {
                            AccountManagerService.this.doNotification(this.mAccounts, account2, result.getString(MiAccountManager.KEY_AUTH_FAILED_MESSAGE), intent);
                        }
                    }
                    super.onResult(result);
                }
            }.bind();
            restoreCallingIdentity(identityToken);
        }
    }

    private Integer getCredentialPermissionNotificationId(Account account, String authTokenType, int uid) {
        Integer id;
        UserAccounts accounts = getUserAccounts(UserId.getUserId(uid));
        synchronized (accounts.credentialsPermissionNotificationIds) {
            Pair<Pair<Account, String>, Integer> key = new Pair(new Pair(account, authTokenType), Integer.valueOf(uid));
            id = (Integer) accounts.credentialsPermissionNotificationIds.get(key);
            if (id == null) {
                id = Integer.valueOf(this.mNotificationIds.incrementAndGet());
                accounts.credentialsPermissionNotificationIds.put(key, id);
            }
        }
        return id;
    }

    private Integer getSigninRequiredNotificationId(UserAccounts accounts, Account account) {
        Integer id;
        synchronized (accounts.signinRequiredNotificationIds) {
            id = (Integer) accounts.signinRequiredNotificationIds.get(account);
            if (id == null) {
                id = Integer.valueOf(this.mNotificationIds.incrementAndGet());
                accounts.signinRequiredNotificationIds.put(account, id);
            }
        }
        return id;
    }

    public void addAcount(IAccountManagerResponse response, String accountType, String authTokenType, String[] requiredFeatures, boolean expectActivityLaunch, Bundle optionsIn) {
        if (Log.isLoggable(TAG, 2)) {
            Log.v(TAG, "addAccount: accountType " + accountType + ", response " + response + ", authTokenType " + authTokenType + ", requiredFeatures " + stringArrayToString(requiredFeatures) + ", expectActivityLaunch " + expectActivityLaunch + ", caller's uid " + Binder.getCallingUid() + ", pid " + Binder.getCallingPid());
        }
        if (response == null) {
            throw new IllegalArgumentException("response is null");
        } else if (accountType == null) {
            throw new IllegalArgumentException("accountType is null");
        } else {
            Bundle options;
            checkManageAccountsPermission();
            UserAccounts accounts = getUserAccountsForCaller();
            int pid = Binder.getCallingPid();
            int uid = Binder.getCallingUid();
            if (optionsIn == null) {
                options = new Bundle();
            } else {
                options = optionsIn;
            }
            options.putInt(AccountManager.KEY_CALLER_UID, uid);
            options.putInt(AccountManager.KEY_CALLER_PID, pid);
            long identityToken = clearCallingIdentity();
            try {
                final String str = authTokenType;
                final String[] strArr = requiredFeatures;
                final String str2 = accountType;
                new Session(accounts, response, accountType, expectActivityLaunch, true) {
                    public void run() throws RemoteException {
                        this.mAuthenticator.addAccount(this, this.mAccountType, str, strArr, options);
                    }

                    protected String toDebugString(long now) {
                        return super.toDebugString(now) + ", addAccount" + ", accountType " + str2 + ", requiredFeatures " + (strArr != null ? TextUtils.join(Constants.SPLITER_SMS_GATEWAY, strArr) : null);
                    }
                }.bind();
            } finally {
                restoreCallingIdentity(identityToken);
            }
        }
    }

    public void confirmCredentials(IAccountManagerResponse response, Account account, Bundle options, boolean expectActivityLaunch) {
        if (Log.isLoggable(TAG, 2)) {
            Log.v(TAG, "confirmCredentials: " + account + ", response " + response + ", expectActivityLaunch " + expectActivityLaunch + ", caller's uid " + Binder.getCallingUid() + ", pid " + Binder.getCallingPid());
        }
        if (response == null) {
            throw new IllegalArgumentException("response is null");
        } else if (account == null) {
            throw new IllegalArgumentException("account is null");
        } else {
            checkManageAccountsPermission();
            UserAccounts accounts = getUserAccountsForCaller();
            long identityToken = clearCallingIdentity();
            try {
                final Account account2 = account;
                final Bundle bundle = options;
                new Session(accounts, response, account.type, expectActivityLaunch, true) {
                    public void run() throws RemoteException {
                        this.mAuthenticator.confirmCredentials(this, account2, bundle);
                    }

                    protected String toDebugString(long now) {
                        return super.toDebugString(now) + ", confirmCredentials" + ", " + account2;
                    }
                }.bind();
            } finally {
                restoreCallingIdentity(identityToken);
            }
        }
    }

    public void updateCredentials(IAccountManagerResponse response, Account account, String authTokenType, boolean expectActivityLaunch, Bundle loginOptions) {
        if (Log.isLoggable(TAG, 2)) {
            Log.v(TAG, "updateCredentials: " + account + ", response " + response + ", authTokenType " + authTokenType + ", expectActivityLaunch " + expectActivityLaunch + ", caller's uid " + Binder.getCallingUid() + ", pid " + Binder.getCallingPid());
        }
        if (response == null) {
            throw new IllegalArgumentException("response is null");
        } else if (account == null) {
            throw new IllegalArgumentException("account is null");
        } else if (authTokenType == null) {
            throw new IllegalArgumentException("authTokenType is null");
        } else {
            checkManageAccountsPermission();
            UserAccounts accounts = getUserAccountsForCaller();
            long identityToken = clearCallingIdentity();
            try {
                final Account account2 = account;
                final String str = authTokenType;
                final Bundle bundle = loginOptions;
                new Session(accounts, response, account.type, expectActivityLaunch, true) {
                    public void run() throws RemoteException {
                        this.mAuthenticator.updateCredentials(this, account2, str, bundle);
                    }

                    protected String toDebugString(long now) {
                        if (bundle != null) {
                            bundle.keySet();
                        }
                        return super.toDebugString(now) + ", updateCredentials" + ", " + account2 + ", authTokenType " + str + ", loginOptions " + bundle;
                    }
                }.bind();
            } finally {
                restoreCallingIdentity(identityToken);
            }
        }
    }

    public void editProperties(IAccountManagerResponse response, String accountType, boolean expectActivityLaunch) {
        if (Log.isLoggable(TAG, 2)) {
            Log.v(TAG, "editProperties: accountType " + accountType + ", response " + response + ", expectActivityLaunch " + expectActivityLaunch + ", caller's uid " + Binder.getCallingUid() + ", pid " + Binder.getCallingPid());
        }
        if (response == null) {
            throw new IllegalArgumentException("response is null");
        } else if (accountType == null) {
            throw new IllegalArgumentException("accountType is null");
        } else {
            checkManageAccountsPermission();
            UserAccounts accounts = getUserAccountsForCaller();
            long identityToken = clearCallingIdentity();
            try {
                final String str = accountType;
                new Session(accounts, response, accountType, expectActivityLaunch, true) {
                    public void run() throws RemoteException {
                        this.mAuthenticator.editProperties(this, this.mAccountType);
                    }

                    protected String toDebugString(long now) {
                        return super.toDebugString(now) + ", editProperties" + ", accountType " + str;
                    }
                }.bind();
            } finally {
                restoreCallingIdentity(identityToken);
            }
        }
    }

    public Account[] getAccounts(int userId) {
        checkReadAccountsPermission();
        UserAccounts accounts = getUserAccounts(userId);
        long identityToken = clearCallingIdentity();
        try {
            Account[] accountsFromCacheLocked;
            synchronized (accounts.cacheLock) {
                accountsFromCacheLocked = getAccountsFromCacheLocked(accounts, null);
            }
            return accountsFromCacheLocked;
        } finally {
            restoreCallingIdentity(identityToken);
        }
    }

    public Account[] getAccounts(String type) {
        if (Log.isLoggable(TAG, 2)) {
            Log.v(TAG, "getAccounts: accountType " + type + ", caller's uid " + Binder.getCallingUid() + ", pid " + Binder.getCallingPid());
        }
        checkReadAccountsPermission();
        UserAccounts accounts = getUserAccountsForCaller();
        long identityToken = clearCallingIdentity();
        try {
            Account[] accountsFromCacheLocked;
            synchronized (accounts.cacheLock) {
                accountsFromCacheLocked = getAccountsFromCacheLocked(accounts, type);
            }
            return accountsFromCacheLocked;
        } finally {
            restoreCallingIdentity(identityToken);
        }
    }

    public void getAccountsByFeatures(IAccountManagerResponse response, String type, String[] features) {
        if (Log.isLoggable(TAG, 2)) {
            Log.v(TAG, "getAccounts: accountType " + type + ", response " + response + ", features " + stringArrayToString(features) + ", caller's uid " + Binder.getCallingUid() + ", pid " + Binder.getCallingPid());
        }
        if (response == null) {
            throw new IllegalArgumentException("response is null");
        } else if (type == null) {
            throw new IllegalArgumentException("accountType is null");
        } else {
            Account[] accounts;
            checkReadAccountsPermission();
            UserAccounts userAccounts = getUserAccountsForCaller();
            long identityToken = clearCallingIdentity();
            if (features != null) {
                try {
                    if (features.length != 0) {
                        new GetAccountsByTypeAndFeatureSession(userAccounts, response, type, features).bind();
                        return;
                    }
                } finally {
                    restoreCallingIdentity(identityToken);
                }
            }
            synchronized (userAccounts.cacheLock) {
                accounts = getAccountsFromCacheLocked(userAccounts, type);
            }
            Bundle result = new Bundle();
            result.putParcelableArray(TABLE_ACCOUNTS, accounts);
            onResult(response, result);
            restoreCallingIdentity(identityToken);
        }
    }

    private long getAccountIdLocked(SQLiteDatabase db, Account account) {
        SQLiteDatabase sQLiteDatabase = db;
        Cursor cursor = sQLiteDatabase.query(TABLE_ACCOUNTS, new String[]{EXTRAS_ID}, "name=? AND type=?", new String[]{account.name, account.type}, null, null, null);
        try {
            if (cursor.moveToNext()) {
                long j = cursor.getLong(0);
                return j;
            }
            cursor.close();
            return -1;
        } finally {
            cursor.close();
        }
    }

    private long getExtrasIdLocked(SQLiteDatabase db, long accountId, String key) {
        Cursor cursor = db.query(TABLE_EXTRAS, new String[]{EXTRAS_ID}, "accounts_id=" + accountId + " AND " + META_KEY + "=?", new String[]{key}, null, null, null);
        try {
            if (cursor.moveToNext()) {
                long j = cursor.getLong(0);
                return j;
            }
            cursor.close();
            return -1;
        } finally {
            cursor.close();
        }
    }

    private static String getDatabaseName(Context context, int userId) {
        File dir = new File(context.getFilesDir(), "users/" + userId);
        dir.mkdirs();
        return new File(dir, DATABASE_NAME).getPath();
    }

    private void doNotification(UserAccounts accounts, Account account, CharSequence message, Intent intent) {
        long identityToken = clearCallingIdentity();
        try {
            if (Log.isLoggable(TAG, 2)) {
                Log.v(TAG, "doNotification: " + message + " intent:" + intent);
            }
            Integer notificationId = getSigninRequiredNotificationId(accounts, account);
            intent.addCategory(String.valueOf(notificationId));
            Notification n = new Notification(17301642, null, 0);
            String notificationTitleFormat = this.mContext.getText(R.string.passport_notification_title).toString();
            n.setLatestEventInfo(this.mContext, String.format(notificationTitleFormat, new Object[]{account.name}), message, PendingIntent.getActivity(this.mContext, 0, intent, 268435456));
            installNotification(notificationId.intValue(), n);
        } finally {
            restoreCallingIdentity(identityToken);
        }
    }

    protected void installNotification(int notificationId, Notification n) {
        ((NotificationManager) this.mContext.getSystemService("notification")).notify(notificationId, n);
    }

    protected void cancelNotification(int id) {
        long identityToken = clearCallingIdentity();
        try {
            ((NotificationManager) this.mContext.getSystemService("notification")).cancel(id);
        } finally {
            restoreCallingIdentity(identityToken);
        }
    }

    private void checkBinderPermission(String... permissions) {
        int uid = Binder.getCallingUid();
        if (this.mContext.getApplicationInfo().uid != uid) {
            String[] arr$ = permissions;
            int len$ = arr$.length;
            int i$ = 0;
            while (i$ < len$) {
                String perm = arr$[i$];
                if (this.mContext.checkCallingOrSelfPermission(perm) != 0) {
                    i$++;
                } else if (Log.isLoggable(TAG, 2)) {
                    Log.v(TAG, "  caller uid " + uid + " has " + perm);
                    return;
                } else {
                    return;
                }
            }
            String msg = "caller uid " + uid + " lacks any of " + TextUtils.join(Constants.SPLITER_SMS_GATEWAY, permissions);
            Log.w(TAG, "  " + msg);
            throw new SecurityException(msg);
        }
    }

    private boolean inSystemImage(int callerUid) {
        String[] arr$ = this.mPackageManager.getPackagesForUid(callerUid);
        int len$ = arr$.length;
        int i$ = 0;
        while (i$ < len$) {
            try {
                PackageInfo packageInfo = this.mPackageManager.getPackageInfo(arr$[i$], 0);
                if (packageInfo != null && (packageInfo.applicationInfo.flags & 1) != 0) {
                    return true;
                }
                i$++;
            } catch (NameNotFoundException e) {
                return false;
            }
        }
        return false;
    }

    private boolean permissionIsGranted(Account account, String authTokenType, int callerUid) {
        boolean fromAuthenticator;
        boolean hasExplicitGrants;
        boolean inSystemImage = inSystemImage(callerUid);
        if (account == null || !hasAuthenticatorUid(account.type, callerUid)) {
            fromAuthenticator = false;
        } else {
            fromAuthenticator = true;
        }
        if (account == null || !hasExplicitlyGrantedPermission(account, authTokenType, callerUid)) {
            hasExplicitGrants = false;
        } else {
            hasExplicitGrants = true;
        }
        if (Log.isLoggable(TAG, 2)) {
            Log.v(TAG, "checkGrantsOrCallingUidAgainstAuthenticator: caller uid " + callerUid + ", " + account + ": is authenticator? " + fromAuthenticator + ", has explicit permission? " + hasExplicitGrants);
        }
        if (fromAuthenticator || hasExplicitGrants || inSystemImage) {
            return true;
        }
        return false;
    }

    private boolean hasAuthenticatorUid(String accountType, int callingUid) {
        return callingUid == this.mContext.getApplicationInfo().uid;
    }

    private boolean hasExplicitlyGrantedPermission(Account account, String authTokenType, int callerUid) {
        boolean permissionGranted = true;
        if (callerUid != 1000) {
            UserAccounts accounts = getUserAccountsForCaller();
            synchronized (accounts.cacheLock) {
                SQLiteDatabase db = accounts.openHelper.getReadableDatabase();
                String[] args = new String[DATABASE_VERSION];
                args[0] = String.valueOf(callerUid);
                args[1] = authTokenType;
                args[2] = account.name;
                args[MESSAGE_TIMED_OUT] = account.type;
                if (DatabaseUtils.longForQuery(db, COUNT_OF_MATCHING_GRANTS, args) == 0) {
                    permissionGranted = false;
                }
            }
        }
        return permissionGranted;
    }

    private void checkCallingUidAgainstAuthenticator(Account account) {
        int uid = Binder.getCallingUid();
        if (account == null || !hasAuthenticatorUid(account.type, uid)) {
            String msg = "caller uid " + uid + " is different than the authenticator's uid";
            Log.w(TAG, msg);
            throw new SecurityException(msg);
        } else if (Log.isLoggable(TAG, 2)) {
            Log.v(TAG, "caller uid " + uid + " is the same as the authenticator's uid");
        }
    }

    private void checkAuthenticateAccountsPermission(Account account) {
        checkBinderPermission("android.permission.AUTHENTICATE_ACCOUNTS");
        checkCallingUidAgainstAuthenticator(account);
    }

    private void checkReadAccountsPermission() {
        checkBinderPermission("android.permission.GET_ACCOUNTS");
    }

    private void checkManageAccountsPermission() {
        checkBinderPermission("android.permission.MANAGE_ACCOUNTS");
    }

    private void checkManageAccountsOrUseCredentialsPermissions() {
        checkBinderPermission("android.permission.MANAGE_ACCOUNTS", "android.permission.USE_CREDENTIALS");
    }

    public void updateAppPermission(Account account, String authTokenType, int uid, boolean value) throws RemoteException {
        if (getCallingUid() != 1000) {
            throw new SecurityException();
        } else if (value) {
            grantAppPermission(account, authTokenType, uid);
        } else {
            revokeAppPermission(account, authTokenType, uid);
        }
    }

    private void grantAppPermission(Account account, String authTokenType, int uid) {
        if (account == null || authTokenType == null) {
            Log.e(TAG, "grantAppPermission: called with invalid arguments", new Exception());
            return;
        }
        UserAccounts accounts = getUserAccounts(UserId.getUserId(uid));
        synchronized (accounts.cacheLock) {
            SQLiteDatabase db = accounts.openHelper.getWritableDatabase();
            db.beginTransaction();
            try {
                long accountId = getAccountIdLocked(db, account);
                if (accountId >= 0) {
                    ContentValues values = new ContentValues();
                    values.put(GRANTS_ACCOUNTS_ID, Long.valueOf(accountId));
                    values.put(GRANTS_AUTH_TOKEN_TYPE, authTokenType);
                    values.put(GRANTS_GRANTEE_UID, Integer.valueOf(uid));
                    db.insert(TABLE_GRANTS, GRANTS_ACCOUNTS_ID, values);
                    db.setTransactionSuccessful();
                }
                cancelNotification(getCredentialPermissionNotificationId(account, authTokenType, uid).intValue());
            } finally {
                db.endTransaction();
            }
        }
    }

    private void revokeAppPermission(Account account, String authTokenType, int uid) {
        if (account == null || authTokenType == null) {
            Log.e(TAG, "revokeAppPermission: called with invalid arguments", new Exception());
            return;
        }
        UserAccounts accounts = getUserAccounts(UserId.getUserId(uid));
        synchronized (accounts.cacheLock) {
            SQLiteDatabase db = accounts.openHelper.getWritableDatabase();
            db.beginTransaction();
            try {
                long accountId = getAccountIdLocked(db, account);
                if (accountId >= 0) {
                    String[] strArr = new String[MESSAGE_TIMED_OUT];
                    strArr[0] = String.valueOf(accountId);
                    strArr[1] = authTokenType;
                    strArr[2] = String.valueOf(uid);
                    db.delete(TABLE_GRANTS, "accounts_id=? AND auth_token_type=? AND uid=?", strArr);
                    db.setTransactionSuccessful();
                }
                cancelNotification(getCredentialPermissionNotificationId(account, authTokenType, uid).intValue());
            } finally {
                db.endTransaction();
            }
        }
    }

    private static final String stringArrayToString(String[] value) {
        return value != null ? "[" + TextUtils.join(Constants.SPLITER_SMS_GATEWAY, value) + "]" : null;
    }

    private void removeAccountFromCacheLocked(UserAccounts accounts, Account account) {
        Account[] oldAccountsForType = (Account[]) accounts.accountCache.get(account.type);
        if (oldAccountsForType != null) {
            ArrayList<Account> newAccountsList = new ArrayList();
            for (Account curAccount : oldAccountsForType) {
                if (!curAccount.equals(account)) {
                    newAccountsList.add(curAccount);
                }
            }
            if (newAccountsList.isEmpty()) {
                accounts.accountCache.remove(account.type);
            } else {
                accounts.accountCache.put(account.type, (Account[]) newAccountsList.toArray(new Account[newAccountsList.size()]));
            }
        }
        accounts.userDataCache.remove(account);
        accounts.authTokenCache.remove(account);
    }

    private void insertAccountIntoCacheLocked(UserAccounts accounts, Account account) {
        int oldLength;
        Account[] accountsForType = (Account[]) accounts.accountCache.get(account.type);
        if (accountsForType != null) {
            oldLength = accountsForType.length;
        } else {
            oldLength = 0;
        }
        Account[] newAccountsForType = new Account[(oldLength + 1)];
        if (accountsForType != null) {
            System.arraycopy(accountsForType, 0, newAccountsForType, 0, oldLength);
        }
        newAccountsForType[oldLength] = account;
        accounts.accountCache.put(account.type, newAccountsForType);
    }

    protected Account[] getAccountsFromCacheLocked(UserAccounts userAccounts, String accountType) {
        validateAccountsAndPopulateCache(userAccounts);
        Account[] accounts;
        if (accountType != null) {
            accounts = (Account[]) userAccounts.accountCache.get(accountType);
            if (accounts == null) {
                return EMPTY_ACCOUNT_ARRAY;
            }
            return (Account[]) Arrays.copyOf(accounts, accounts.length);
        }
        int totalLength = 0;
        for (Account[] accounts2 : userAccounts.accountCache.values()) {
            totalLength += accounts2.length;
        }
        if (totalLength == 0) {
            return EMPTY_ACCOUNT_ARRAY;
        }
        accounts2 = new Account[totalLength];
        totalLength = 0;
        for (Account[] accountsOfType : userAccounts.accountCache.values()) {
            System.arraycopy(accountsOfType, 0, accounts2, totalLength, accountsOfType.length);
            totalLength += accountsOfType.length;
        }
        return accounts2;
    }

    protected void writeUserDataIntoCacheLocked(UserAccounts accounts, SQLiteDatabase db, Account account, String key, String value) {
        HashMap<String, String> userDataForAccount = (HashMap) accounts.userDataCache.get(account);
        if (userDataForAccount == null) {
            userDataForAccount = readUserDataForAccountFromDatabaseLocked(db, account);
            accounts.userDataCache.put(account, userDataForAccount);
        }
        if (value == null) {
            userDataForAccount.remove(key);
        } else {
            userDataForAccount.put(key, value);
        }
    }

    protected void writeAuthTokenIntoCacheLocked(UserAccounts accounts, SQLiteDatabase db, Account account, String key, String value) {
        HashMap<String, String> authTokensForAccount = (HashMap) accounts.authTokenCache.get(account);
        if (authTokensForAccount == null) {
            authTokensForAccount = readAuthTokensForAccountFromDatabaseLocked(db, account);
            accounts.authTokenCache.put(account, authTokensForAccount);
        }
        if (value == null) {
            authTokensForAccount.remove(key);
        } else {
            authTokensForAccount.put(key, value);
        }
    }

    protected String readAuthTokenInternal(UserAccounts accounts, Account account, String authTokenType) {
        String str;
        synchronized (accounts.cacheLock) {
            HashMap<String, String> authTokensForAccount = (HashMap) accounts.authTokenCache.get(account);
            if (authTokensForAccount == null) {
                authTokensForAccount = readAuthTokensForAccountFromDatabaseLocked(accounts.openHelper.getReadableDatabase(), account);
                accounts.authTokenCache.put(account, authTokensForAccount);
            }
            str = (String) authTokensForAccount.get(authTokenType);
        }
        return str;
    }

    protected String readUserDataInternal(UserAccounts accounts, Account account, String key) {
        String str;
        synchronized (accounts.cacheLock) {
            HashMap<String, String> userDataForAccount = (HashMap) accounts.userDataCache.get(account);
            if (userDataForAccount == null) {
                userDataForAccount = readUserDataForAccountFromDatabaseLocked(accounts.openHelper.getReadableDatabase(), account);
                accounts.userDataCache.put(account, userDataForAccount);
            }
            str = (String) userDataForAccount.get(key);
        }
        return str;
    }

    protected HashMap<String, String> readUserDataForAccountFromDatabaseLocked(SQLiteDatabase db, Account account) {
        HashMap<String, String> userDataForAccount = new HashMap();
        Cursor cursor = db.query(TABLE_EXTRAS, COLUMNS_EXTRAS_KEY_AND_VALUE, SELECTION_USERDATA_BY_ACCOUNT, new String[]{account.name, account.type}, null, null, null);
        while (cursor.moveToNext()) {
            try {
                userDataForAccount.put(cursor.getString(0), cursor.getString(1));
            } finally {
                cursor.close();
            }
        }
        return userDataForAccount;
    }

    protected HashMap<String, String> readAuthTokensForAccountFromDatabaseLocked(SQLiteDatabase db, Account account) {
        HashMap<String, String> authTokensForAccount = new HashMap();
        Cursor cursor = db.query(TABLE_AUTHTOKENS, COLUMNS_AUTHTOKENS_TYPE_AND_AUTHTOKEN, SELECTION_USERDATA_BY_ACCOUNT, new String[]{account.name, account.type}, null, null, null);
        while (cursor.moveToNext()) {
            try {
                authTokensForAccount.put(cursor.getString(0), cursor.getString(1));
            } finally {
                cursor.close();
            }
        }
        return authTokensForAccount;
    }

    private int getCallingUid() {
        return this.mContext.getApplicationInfo().uid;
    }

    private long clearCallingIdentity() {
        return 0;
    }

    private void restoreCallingIdentity(long identity) {
    }
}
