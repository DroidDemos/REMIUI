package com.google.android.gms.internal;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.DisplayMetrics;
import android.util.TypedValue;

@fd
public final class gv {
    public static final Handler wQ;

    static {
        wQ = new Handler(Looper.getMainLooper());
    }

    public static int a(Context context, int i) {
        return a(context.getResources().getDisplayMetrics(), i);
    }

    public static int a(DisplayMetrics displayMetrics, int i) {
        return (int) TypedValue.applyDimension(1, (float) i, displayMetrics);
    }

    public static boolean dB() {
        return Looper.myLooper() == Looper.getMainLooper();
    }
}
