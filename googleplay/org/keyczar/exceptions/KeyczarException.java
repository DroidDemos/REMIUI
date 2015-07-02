package org.keyczar.exceptions;

public class KeyczarException extends Exception {
    public KeyczarException(String message) {
        super(message);
    }

    public KeyczarException(String message, Throwable cause) {
        super(message, cause);
    }

    public KeyczarException(Throwable cause) {
        super(cause);
    }
}
