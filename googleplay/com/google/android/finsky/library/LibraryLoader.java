package com.google.android.finsky.library;

import android.os.Handler;
import android.util.Base64;
import com.google.android.finsky.utils.FinskyPreferences;
import com.google.android.finsky.utils.Lists;
import com.google.android.finsky.utils.Utils;
import java.util.Iterator;
import java.util.List;

public class LibraryLoader {
    private final AccountLibrary mAccountLibrary;
    private final Handler mBackgroundHandler;
    private final List<Runnable> mLoadingCallbacks;
    private final Handler mNotificationHandler;
    private final SQLiteLibrary mSQLiteLibrary;
    private State mState;

    private enum State {
        UNINITIALIZED,
        LOADING,
        LOADED
    }

    public LibraryLoader(SQLiteLibrary sqLiteLibrary, AccountLibrary accountLibrary, Handler notificationHandler, Handler backgroundHandler) {
        this.mLoadingCallbacks = Lists.newArrayList();
        this.mState = State.UNINITIALIZED;
        this.mSQLiteLibrary = sqLiteLibrary;
        this.mAccountLibrary = accountLibrary;
        this.mBackgroundHandler = backgroundHandler;
        this.mNotificationHandler = notificationHandler;
    }

    public synchronized void load(Runnable callback) {
        this.mLoadingCallbacks.add(callback);
        this.mBackgroundHandler.post(new Runnable() {
            public void run() {
                LibraryLoader.this.loadBlocking();
            }
        });
    }

    void loadBlocking() {
        Iterator i$;
        if (this.mState == State.UNINITIALIZED) {
            this.mAccountLibrary.disableListeners();
            this.mSQLiteLibrary.reopen();
            String accountName = this.mAccountLibrary.getAccount().name;
            i$ = this.mSQLiteLibrary.iterator();
            while (i$.hasNext()) {
                LibraryEntry libraryEntry = (LibraryEntry) i$.next();
                if (accountName.equals(libraryEntry.getAccountName())) {
                    this.mAccountLibrary.add(libraryEntry);
                }
            }
            for (String libraryId : AccountLibrary.LIBRARY_IDS) {
                String encodedToken = (String) FinskyPreferences.getLibraryServerToken(libraryId).get(accountName).get();
                this.mAccountLibrary.setServerToken(libraryId, encodedToken != null ? Base64.decode(encodedToken, 0) : null);
            }
            this.mAccountLibrary.setAutoAcquireTags(Utils.commaUnpackStrings((String) FinskyPreferences.autoAcquireTags.get(accountName).get()));
            this.mState = State.LOADED;
            this.mSQLiteLibrary.close();
            this.mAccountLibrary.enableListeners();
        }
        synchronized (this) {
            for (Runnable loadingCallback : this.mLoadingCallbacks) {
                this.mNotificationHandler.post(loadingCallback);
            }
            this.mLoadingCallbacks.clear();
        }
    }
}
