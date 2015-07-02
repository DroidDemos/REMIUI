package com.google.android.finsky.auth;

public interface AuthResponseListener {
    void onAuthFailure(int i);

    void onAuthSuccess();
}
