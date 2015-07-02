package com.b.a.a.a;

import android.util.Log;

/* compiled from: Log */
public class a {
    private static boolean isDebug;

    static {
        isDebug = false;
    }

    public static void i(String str, String str2) {
        if (isDebug) {
            Log.i("weibosdk", new StringBuilder(String.valueOf(str)).append("  ").append(str2).toString());
        }
    }
}
