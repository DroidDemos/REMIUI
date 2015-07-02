package com.google.android.gms.car;

import android.util.Log;

public class CarLog {
    public static boolean sForceLogs;

    public static final boolean isLoggable(String tag, int level) {
        return (sForceLogs && level >= 3) || Log.isLoggable(tag, level);
    }
}
