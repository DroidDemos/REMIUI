package com.google.android.finsky.library;

import android.accounts.Account;
import com.android.volley.Response.Listener;
import com.google.android.finsky.protos.RevokeResponse;

public class RevokeListenerWrapper implements Listener<RevokeResponse> {
    private final Account mCurrentAccount;
    private final Listener<RevokeResponse> mListener;
    private final LibraryReplicators mReplicators;

    public RevokeListenerWrapper(LibraryReplicators replicators, Account requestingAccount, Listener<RevokeResponse> listenerToWrap) {
        this.mReplicators = replicators;
        this.mCurrentAccount = requestingAccount;
        this.mListener = listenerToWrap;
    }

    public void onResponse(final RevokeResponse response) {
        this.mReplicators.applyLibraryUpdates(this.mCurrentAccount, "revoke", new Runnable() {
            public void run() {
                RevokeListenerWrapper.this.mListener.onResponse(response);
            }
        }, response.libraryUpdate);
    }
}
