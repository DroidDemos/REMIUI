package com.google.android.gms.internal;

import android.util.Log;

@fd
public final class gw {
    public static void d(String msg) {
        if (v(3)) {
            Log.d("Ads", msg);
        }
    }

    public static void d(String msg, Throwable tr) {
        if (v(3)) {
            Log.d("Ads", msg, tr);
        }
    }

    public static void e(String msg) {
        if (v(6)) {
            Log.e("Ads", msg);
        }
    }

    public static void e(String msg, Throwable tr) {
        if (v(6)) {
            Log.e("Ads", msg, tr);
        }
    }

    public static void i(String msg) {
        if (v(4)) {
            Log.i("Ads", msg);
        }
    }

    public static void v(String msg) {
        if (v(2)) {
            Log.v("Ads", msg);
        }
    }

    public static boolean v(int i) {
        return (i >= 5 || Log.isLoggable("Ads", i)) && i != 2;
    }

    public static void w(String msg) {
        if (v(5)) {
            Log.w("Ads", msg);
        }
    }

    public static void w(String msg, Throwable tr) {
        if (v(5)) {
            Log.w("Ads", msg, tr);
        }
    }
}
