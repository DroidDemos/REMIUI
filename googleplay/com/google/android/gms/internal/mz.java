package com.google.android.gms.internal;

import com.google.android.gms.common.util.k;
import com.google.android.gms.fitness.data.DataSource;

public class mz {
    private static final ThreadLocal<String> ahc;

    static {
        ahc = new ThreadLocal();
    }

    private static String B(String str, String str2) {
        if (str == null || str2 == null) {
            return str;
        }
        Object obj = new byte[(str.length() + str2.length())];
        System.arraycopy(str.getBytes(), 0, obj, 0, str.length());
        System.arraycopy(str2.getBytes(), 0, obj, str.length(), str2.length());
        return Integer.toHexString(k.a(obj, 0, obj.length, 0));
    }

    public static DataSource b(DataSource dataSource) {
        if (!dataSource.lv()) {
            return dataSource;
        }
        return (lG() || ((String) ahc.get()).equals(dataSource.getAppPackageName())) ? dataSource : dataSource.lw();
    }

    public static String bP(String str) {
        return B(str, (String) ahc.get());
    }

    public static boolean lG() {
        String str = (String) ahc.get();
        return str == null || str.startsWith("com.google");
    }
}
