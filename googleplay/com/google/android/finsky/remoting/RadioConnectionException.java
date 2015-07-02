package com.google.android.finsky.remoting;

public class RadioConnectionException extends Exception {
    public RadioConnectionException(String message) {
        super(message);
    }

    public RadioConnectionException(Throwable t) {
        super(t);
    }
}
