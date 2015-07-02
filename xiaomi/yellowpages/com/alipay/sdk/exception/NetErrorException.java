package com.alipay.sdk.exception;

import android.text.TextUtils;
import android.util.Log;

public final class NetErrorException extends Exception {
    public static final int NET_CONNECTION_ERROR = 0;
    public static final int SERVER_ERROR = 1;
    public static final int SSL_ERROR = 2;
    private static final long serialVersionUID = 8374198311711795611L;
    private int errorCode;

    public NetErrorException() {
        this(null, null);
    }

    public NetErrorException(String str) {
        this(str, null);
    }

    public NetErrorException(Throwable th) {
        this(null, th);
    }

    public NetErrorException(String str, Throwable th) {
        super(str, th);
        this.errorCode = NET_CONNECTION_ERROR;
        printException(str, th);
    }

    public final int getErrorCode() {
        return this.errorCode;
    }

    public final void setErrorCode(int i) {
        this.errorCode = i;
    }

    public static void printException(String str, Throwable th) {
        if (!TextUtils.isEmpty(str)) {
            Log.w("NetError", "NetError--" + str);
        }
        if (th != null) {
            try {
                Log.w("NetError", "NetError--" + th.getMessage());
            } catch (Exception e) {
            }
        }
    }
}
