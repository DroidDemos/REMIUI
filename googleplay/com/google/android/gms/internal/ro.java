package com.google.android.gms.internal;

import android.util.Log;

public final class ro {
    private static volatile boolean aCW;

    public static void b(String str, String str2, Throwable th) {
        if (di(5)) {
            Log.w(str, str2, th);
        }
    }

    public static boolean di(int i) {
        return aCW || Log.isLoggable("PeopleService", i);
    }

    public static boolean qQ() {
        return di(3);
    }

    public static void t(String str, String str2) {
        if (di(3)) {
            Log.d(str, str2);
        }
    }

    public static void w(String str, String str2) {
        if (di(5)) {
            Log.w(str, str2);
        }
    }
}
