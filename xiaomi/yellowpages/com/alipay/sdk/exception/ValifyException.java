package com.alipay.sdk.exception;

import android.text.TextUtils;
import android.util.Log;

public final class ValifyException extends Exception {
    private static final long serialVersionUID = 7405333891987087563L;

    public ValifyException() {
        this(null, null);
    }

    public ValifyException(String str) {
        this(str, null);
    }

    public ValifyException(Throwable th) {
        this(null, th);
    }

    public ValifyException(String str, Throwable th) {
        super(str, th);
        printException(str, th);
    }

    public static void printException(String str, Throwable th) {
        if (!TextUtils.isEmpty(str)) {
            Log.e("Validation", "Validation--" + str);
        }
        if (th != null) {
            try {
                Log.e("Validation", "Validation--" + th.getMessage());
                th.printStackTrace();
            } catch (Exception e) {
            }
        }
    }
}
