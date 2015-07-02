package com.google.android.finsky.library;

import android.accounts.Account;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.Lists;
import com.google.android.finsky.utils.Maps;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

public class Libraries implements Library {
    private final Accounts mAccounts;
    private final Handler mBackgroundHandler;
    private List<Account> mCurrentAccounts;
    private final Map<Account, AccountLibrary> mLibraries;
    private List<AccountLibrary> mLibraryList;
    private final List<Listener> mListeners;
    private boolean mLoadHasBeenCalled;
    private int mLoadedAccountHash;
    private final List<Runnable> mLoadingCallbacks;
    private final Handler mNotificationHandler;
    private final SQLiteLibrary mSQLiteLibrary;

    public interface Listener {
        void onAllLibrariesLoaded();

        void onLibraryContentsChanged(AccountLibrary accountLibrary);
    }

    public Libraries(Accounts accounts, SQLiteLibrary sqLiteLibrary, Handler notificationHandler, Handler backgroundHandler) {
        this.mLibraries = Maps.newHashMap();
        this.mCurrentAccounts = null;
        this.mLibraryList = Collections.unmodifiableList(Lists.newArrayList());
        this.mLoadingCallbacks = Lists.newArrayList();
        this.mListeners = Lists.newArrayList();
        this.mLoadHasBeenCalled = false;
        this.mAccounts = accounts;
        this.mSQLiteLibrary = sqLiteLibrary;
        this.mBackgroundHandler = backgroundHandler;
        this.mNotificationHandler = notificationHandler;
    }

    public synchronized void addListener(Listener listener) {
        this.mListeners.add(listener);
    }

    public synchronized void removeListener(Listener listener) {
        this.mListeners.remove(listener);
    }

    public synchronized boolean isLoaded() {
        return this.mLoadHasBeenCalled;
    }

    public synchronized int getLoadedAccountHash() {
        return this.mLoadedAccountHash;
    }

    public void blockingLoad() {
        if (Looper.myLooper() == this.mNotificationHandler.getLooper() || Looper.myLooper() == this.mBackgroundHandler.getLooper()) {
            throw new IllegalStateException();
        }
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        load(new Runnable() {
            public void run() {
                countDownLatch.countDown();
            }
        });
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public synchronized void load(Runnable callback) {
        if (!this.mLoadHasBeenCalled || this.mLoadedAccountHash != computeAccountHash(this.mAccounts.getAccounts())) {
            this.mLoadingCallbacks.add(callback);
            if (this.mLoadingCallbacks.size() <= 1) {
                this.mCurrentAccounts = this.mAccounts.getAccounts();
                final int accountsHash = computeAccountHash(this.mCurrentAccounts);
                List<Account> librariesToUnload = null;
                for (Account account : this.mLibraries.keySet()) {
                    if (librariesToUnload == null) {
                        librariesToUnload = Lists.newArrayList();
                    }
                    if (!this.mCurrentAccounts.contains(account)) {
                        librariesToUnload.add(account);
                    }
                }
                if (librariesToUnload != null) {
                    for (Account account2 : librariesToUnload) {
                        FinskyLog.d("Unloading AccountLibrary for account: %s", FinskyLog.scrubPii(account2.name));
                        this.mLibraries.remove(account2);
                    }
                }
                final List<Account> librariesToLoad = Lists.newArrayList();
                for (Account account22 : this.mCurrentAccounts) {
                    if (!this.mLibraries.containsKey(account22)) {
                        librariesToLoad.add(account22);
                        this.mLibraries.put(account22, setupAccountLibrary(account22));
                    }
                }
                this.mLibraryList = Collections.unmodifiableList(Lists.newArrayList(this.mLibraries.values()));
                if (librariesToLoad.size() == 0) {
                    fireAllLibrariesLoaded();
                    runAndClearLoadingCallbacks();
                    this.mLoadedAccountHash = accountsHash;
                    this.mLoadHasBeenCalled = true;
                } else {
                    final int[] loadedLibrariesCount = new int[]{0};
                    for (final Account account222 : librariesToLoad) {
                        new LibraryLoader(this.mSQLiteLibrary, (AccountLibrary) this.mLibraries.get(account222), this.mNotificationHandler, this.mBackgroundHandler).load(new Runnable() {
                            public void run() {
                                FinskyLog.d("Loaded library for account: %s", FinskyLog.scrubPii(account222.name));
                                int[] iArr = loadedLibrariesCount;
                                iArr[0] = iArr[0] + 1;
                                if (loadedLibrariesCount[0] == librariesToLoad.size()) {
                                    FinskyLog.d("Finished loading %d libraries.", Integer.valueOf(librariesToLoad.size()));
                                    Libraries.this.fireAllLibrariesLoaded();
                                    Libraries.this.runAndClearLoadingCallbacks();
                                    Libraries.this.mLoadedAccountHash = accountsHash;
                                }
                            }
                        });
                    }
                    this.mLoadHasBeenCalled = true;
                }
            }
        } else if (callback != null) {
            this.mNotificationHandler.post(callback);
        }
    }

    public synchronized void cleanup() {
        this.mBackgroundHandler.post(new Runnable() {
            public void run() {
                Libraries.this.mSQLiteLibrary.reopen();
                Libraries.this.mSQLiteLibrary.resetKeepOnlyAccounts(Libraries.this.mAccounts.getAccounts());
                Libraries.this.mSQLiteLibrary.close();
            }
        });
    }

    private int computeAccountHash(List<Account> accounts) {
        int result = 0;
        for (Account account : accounts) {
            result += account.hashCode();
        }
        return result;
    }

    private AccountLibrary setupAccountLibrary(Account account) {
        final AccountLibrary accountLibrary = new AccountLibrary(account, this.mNotificationHandler);
        accountLibrary.addListener(new com.google.android.finsky.library.AccountLibrary.Listener() {
            public void onLibraryChange() {
                Libraries.this.notifyLibraryChanged(accountLibrary);
            }
        });
        return accountLibrary;
    }

    private void notifyLibraryChanged(final AccountLibrary library) {
        this.mNotificationHandler.post(new Runnable() {
            public void run() {
                synchronized (Libraries.this.mListeners) {
                    for (Listener listener : Libraries.this.mListeners) {
                        listener.onLibraryContentsChanged(library);
                    }
                }
            }
        });
    }

    private void fireAllLibrariesLoaded() {
        this.mNotificationHandler.post(new Runnable() {
            public void run() {
                synchronized (Libraries.this.mListeners) {
                    for (Listener listener : Libraries.this.mListeners) {
                        listener.onAllLibrariesLoaded();
                    }
                }
            }
        });
    }

    private synchronized void runAndClearLoadingCallbacks() {
        for (Runnable loadingCallback : this.mLoadingCallbacks) {
            if (loadingCallback != null) {
                this.mNotificationHandler.post(loadingCallback);
            }
        }
        this.mLoadingCallbacks.clear();
    }

    public synchronized List<AccountLibrary> getAccountLibraries() {
        return this.mLibraryList;
    }

    public synchronized AccountLibrary getAccountLibrary(Account account) {
        return (AccountLibrary) this.mLibraries.get(account);
    }

    public synchronized boolean contains(LibraryEntry key) {
        boolean z;
        int libraryCount = this.mLibraryList.size();
        for (int i = 0; i < libraryCount; i++) {
            if (((AccountLibrary) this.mLibraryList.get(i)).contains(key)) {
                z = true;
                break;
            }
        }
        z = false;
        return z;
    }

    public synchronized LibraryEntry get(LibraryEntry key) {
        LibraryEntry entry;
        int libraryCount = this.mLibraryList.size();
        for (int i = 0; i < libraryCount; i++) {
            entry = ((AccountLibrary) this.mLibraryList.get(i)).get(key);
            if (entry != null) {
                break;
            }
        }
        entry = null;
        return entry;
    }

    public synchronized List<Account> getAppOwners(String docId, String[] certificateHashes) {
        List<Account> result;
        result = null;
        int accountCount = this.mCurrentAccounts.size();
        for (int i = 0; i < accountCount; i++) {
            Account account = (Account) this.mCurrentAccounts.get(i);
            LibraryAppEntry appEntry = ((AccountLibrary) this.mLibraries.get(account)).getAppEntry(docId);
            if (appEntry != null && appEntry.hasMatchingCertificateHash(certificateHashes)) {
                if (result == null) {
                    result = Lists.newArrayList();
                }
                result.add(account);
            }
        }
        if (result == null) {
            result = Collections.emptyList();
        }
        return result;
    }

    public synchronized List<Account> getAppOwners(String docId) {
        return getAppOwners(docId, LibraryAppEntry.ANY_CERTIFICATE_HASHES);
    }

    public synchronized List<LibraryAppEntry> getAppEntries(String docId, String[] certificateHashes) {
        List<LibraryAppEntry> result;
        result = Lists.newArrayList();
        int libraryCount = this.mLibraryList.size();
        for (int i = 0; i < libraryCount; i++) {
            LibraryAppEntry appEntry = ((AccountLibrary) this.mLibraryList.get(i)).getAppEntry(docId);
            if (appEntry != null && appEntry.hasMatchingCertificateHash(certificateHashes)) {
                result.add(appEntry);
            }
        }
        return result;
    }

    public synchronized boolean hasSubscriptions() {
        boolean z;
        int libraryCount = this.mLibraryList.size();
        for (int i = 0; i < libraryCount; i++) {
            if (!((AccountLibrary) this.mLibraryList.get(i)).getInAppSubscriptionsList().isEmpty()) {
                z = true;
                break;
            }
        }
        z = false;
        return z;
    }

    public void remove(LibraryEntry key) {
        throw new UnsupportedOperationException();
    }

    public synchronized int size() {
        int size;
        size = 0;
        for (int i = 0; i < this.mLibraryList.size(); i++) {
            size += ((AccountLibrary) this.mLibraryList.get(i)).size();
        }
        return size;
    }

    public Iterator<LibraryEntry> iterator() {
        throw new UnsupportedOperationException();
    }

    public void dumpState() {
        Log.d("FinskyLibrary", "| Libraries {");
        for (AccountLibrary accountLibrary : this.mLibraries.values()) {
            accountLibrary.dumpState("|   ");
        }
        Log.d("FinskyLibrary", "| }");
    }
}
