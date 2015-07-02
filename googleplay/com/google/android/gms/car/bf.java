package com.google.android.gms.car;

import android.os.Handler;
import android.os.Looper;

public class bf {
    public static void a(Looper looper, Runnable runnable) {
        new Handler(looper).post(runnable);
    }

    public static void c(Runnable runnable) {
        a(Looper.getMainLooper(), runnable);
    }
}
