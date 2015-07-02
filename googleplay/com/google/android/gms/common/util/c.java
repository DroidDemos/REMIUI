package com.google.android.gms.common.util;

import android.content.Context;
import java.util.regex.Pattern;

public final class c {
    private static Pattern XH;

    static {
        XH = null;
    }

    public static boolean M(Context context) {
        return context.getPackageManager().hasSystemFeature("android.hardware.type.watch");
    }

    public static int dA(int i) {
        return (i % 1000) / 100;
    }

    public static boolean dB(int i) {
        return dA(i) == 3;
    }

    public static int dz(int i) {
        return i / 1000;
    }
}
