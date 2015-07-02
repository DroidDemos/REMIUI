package com.google.android.finsky.library;

import android.accounts.Account;
import android.os.Handler;
import android.util.Log;
import com.google.android.finsky.api.DfeApiProvider;
import com.google.android.finsky.library.LibraryReplicators.Listener;
import com.google.android.finsky.protos.LibraryUpdateProto.LibraryUpdate;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.Lists;
import com.google.android.finsky.utils.Maps;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class LibraryReplicatorsImpl implements LibraryReplicators {
    private final Handler mBackgroundHandler;
    private final DfeApiProvider mDfeApiProvider;
    private final boolean mEnableDebugging;
    private final Libraries mLibraries;
    private final List<Listener> mListeners;
    private final Handler mNotificationHandler;
    private final Map<Account, LibraryReplicator> mReplicators;
    private final SQLiteLibrary mSQLiteLibrary;

    public LibraryReplicatorsImpl(DfeApiProvider dfeApiProvider, SQLiteLibrary sqLiteLibrary, Libraries libraries, Handler notificationHandler, Handler backgroundHandler, boolean enableDebugging) {
        this.mReplicators = Maps.newHashMap();
        this.mListeners = Lists.newArrayList();
        this.mDfeApiProvider = dfeApiProvider;
        this.mSQLiteLibrary = sqLiteLibrary;
        this.mLibraries = libraries;
        this.mNotificationHandler = notificationHandler;
        this.mBackgroundHandler = backgroundHandler;
        this.mEnableDebugging = enableDebugging;
        reinitialize();
    }

    public synchronized void addListener(Listener listener) {
        this.mListeners.add(listener);
    }

    public synchronized void reinitialize() {
        this.mReplicators.clear();
        for (final AccountLibrary accountLibrary : this.mLibraries.getAccountLibraries()) {
            Account account = accountLibrary.getAccount();
            LibraryReplicator replicator = new LibraryReplicator(this.mDfeApiProvider.getDfeApi(account.name), this.mSQLiteLibrary, accountLibrary, this.mNotificationHandler, this.mBackgroundHandler, this.mEnableDebugging);
            replicator.addListener(new LibraryReplicator.Listener() {
                public void onMutationsApplied(String libraryId) {
                    LibraryReplicatorsImpl.this.notifyListeners(accountLibrary, libraryId);
                }
            });
            this.mReplicators.put(account, replicator);
        }
    }

    private synchronized void notifyListeners(AccountLibrary accountLibrary, String libraryId) {
        for (Listener listener : this.mListeners) {
            listener.onMutationsApplied(accountLibrary, libraryId);
        }
    }

    public void replicateAllAccounts(final Runnable successRunnable, final String debugTag) {
        this.mLibraries.load(new Runnable() {
            public void run() {
                synchronized (LibraryReplicatorsImpl.this) {
                    final Collection<LibraryReplicator> replicators = LibraryReplicatorsImpl.this.mReplicators.values();
                    if (replicators.isEmpty()) {
                        if (successRunnable != null) {
                            LibraryReplicatorsImpl.this.mNotificationHandler.post(successRunnable);
                        }
                        return;
                    }
                    Runnable callSuccessRunnableWhenDone = new Runnable() {
                        private int mFinished;

                        public void run() {
                            this.mFinished++;
                            if (this.mFinished == replicators.size() && successRunnable != null) {
                                LibraryReplicatorsImpl.this.mNotificationHandler.post(successRunnable);
                            }
                        }
                    };
                    for (LibraryReplicator replicator : replicators) {
                        replicator.replicate(AccountLibrary.LIBRARY_IDS, callSuccessRunnableWhenDone, debugTag);
                    }
                }
            }
        });
    }

    public void replicateAccount(Account account, String[] libraryIds, Runnable successRunnable, String debugTag) {
        final Account account2 = account;
        final String[] strArr = libraryIds;
        final Runnable runnable = successRunnable;
        final String str = debugTag;
        this.mLibraries.load(new Runnable() {
            public void run() {
                synchronized (LibraryReplicatorsImpl.this) {
                    ((LibraryReplicator) LibraryReplicatorsImpl.this.mReplicators.get(account2)).replicate(strArr, runnable, str);
                }
            }
        });
    }

    public void applyLibraryUpdates(Account account, String debugTag, Runnable postLibraryUpdateCallback, LibraryUpdate... libraryUpdates) {
        final Account account2 = account;
        final String str = debugTag;
        final LibraryUpdate[] libraryUpdateArr = libraryUpdates;
        final Runnable runnable = postLibraryUpdateCallback;
        this.mLibraries.load(new Runnable() {
            public void run() {
                synchronized (LibraryReplicatorsImpl.this) {
                    if (LibraryReplicatorsImpl.this.mReplicators.containsKey(account2)) {
                        FinskyLog.d("Applying library update: account=%s", FinskyLog.scrubPii(account2.name));
                        ((LibraryReplicator) LibraryReplicatorsImpl.this.mReplicators.get(account2)).applyLibraryUpdates(str, libraryUpdateArr);
                    } else {
                        FinskyLog.e("LibraryUpdate for unknown account %s could not be applied", FinskyLog.scrubPii(account2.name));
                    }
                    if (runnable != null) {
                        LibraryReplicatorsImpl.this.mBackgroundHandler.post(new Runnable() {
                            public void run() {
                                LibraryReplicatorsImpl.this.mNotificationHandler.post(runnable);
                            }
                        });
                    }
                }
            }
        });
    }

    public void dumpState() {
        Log.d("FinskyLibrary", "| LibraryReplicators {");
        for (LibraryReplicator replicator : this.mReplicators.values()) {
            replicator.dumpState("|   ");
        }
        Log.d("FinskyLibrary", "| }");
    }
}
