package com.google.android.wallet.instrumentmanager.common.util;

public final class Objects {
    public static boolean equals(Object a, Object b) {
        if (a == null) {
            return b == null;
        } else {
            return a.equals(b);
        }
    }
}
