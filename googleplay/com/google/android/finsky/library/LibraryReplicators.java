package com.google.android.finsky.library;

import android.accounts.Account;
import com.google.android.finsky.protos.LibraryUpdateProto.LibraryUpdate;

public interface LibraryReplicators {

    public interface Listener {
        void onMutationsApplied(AccountLibrary accountLibrary, String str);
    }

    void addListener(Listener listener);

    void applyLibraryUpdates(Account account, String str, Runnable runnable, LibraryUpdate... libraryUpdateArr);

    void dumpState();

    void reinitialize();

    void replicateAccount(Account account, String[] strArr, Runnable runnable, String str);

    void replicateAllAccounts(Runnable runnable, String str);
}
