package com.alipay.sdk.exception;

import android.text.TextUtils;
import android.util.Log;

public final class AppErrorException extends Exception {
    private static final long serialVersionUID = 4918321648798599467L;

    public AppErrorException(Class cls) {
        this(cls, null, null);
    }

    public AppErrorException(Class cls, String str) {
        this(cls, str, null);
    }

    public AppErrorException(Class cls, Throwable th) {
        this(cls, null, th);
    }

    public AppErrorException(Class cls, String str, Throwable th) {
        super(str, th);
        printException(cls, str, th);
    }

    public static void printException(Class cls, String str, Throwable th) {
        if (cls != null) {
            Log.e("AppError", "AppError--" + cls.getCanonicalName());
        }
        if (!TextUtils.isEmpty(str)) {
            Log.e("AppError", "AppError--" + str);
        }
        if (th != null) {
            try {
                Log.e("AppError", "AppError--" + th.getMessage());
                th.printStackTrace();
            } catch (Exception e) {
            }
        }
    }
}
