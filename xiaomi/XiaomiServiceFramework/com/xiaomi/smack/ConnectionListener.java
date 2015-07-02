package com.xiaomi.smack;

public interface ConnectionListener {
    void connectionClosed(int i, Exception exception);

    void connectionStarted();

    void reconnectionFailed(Exception exception);

    void reconnectionSuccessful();
}
