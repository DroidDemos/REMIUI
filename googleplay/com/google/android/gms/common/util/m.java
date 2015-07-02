package com.google.android.gms.common.util;

import android.os.Build.VERSION;

public final class m {
    private static boolean dD(int i) {
        return VERSION.SDK_INT >= i;
    }

    public static boolean jk() {
        return dD(11);
    }

    public static boolean jm() {
        return dD(13);
    }

    public static boolean jn() {
        return dD(14);
    }

    public static boolean jr() {
        return dD(19);
    }

    @Deprecated
    public static boolean js() {
        return jt();
    }

    public static boolean jt() {
        return dD(21);
    }
}
