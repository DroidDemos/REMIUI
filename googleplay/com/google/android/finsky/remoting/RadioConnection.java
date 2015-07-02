package com.google.android.finsky.remoting;

public interface RadioConnection {
    void ensureRouteToHost(String str) throws RadioConnectionException;

    void start() throws RadioConnectionException;

    void stop() throws RadioConnectionException;
}
