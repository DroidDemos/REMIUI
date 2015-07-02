package com.xiaomi.kenai.jbosh;

public class BOSHException extends Exception {
    private static final long serialVersionUID = 1;

    public BOSHException(String msg) {
        super(msg);
    }

    public BOSHException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
