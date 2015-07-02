package com.alipay.sdk.exception;

import android.text.TextUtils;
import android.util.Log;

public final class FailOperatingException extends Exception {
    private static final long serialVersionUID = 5908800852030168641L;

    public FailOperatingException() {
        this(null, null);
    }

    public FailOperatingException(String str) {
        this(str, null);
    }

    public FailOperatingException(Throwable th) {
        this(null, th);
    }

    public FailOperatingException(String str, Throwable th) {
        super(str, th);
        printException(str, th);
    }

    public static void printException(String str, Throwable th) {
        if (!TextUtils.isEmpty(str)) {
            Log.i("FailOperating", "FailOperating--" + str);
        }
        if (th != null) {
            try {
                Log.i("FailOperating", "FailOperating--" + th.getMessage());
                th.printStackTrace();
            } catch (Exception e) {
            }
        }
    }
}
