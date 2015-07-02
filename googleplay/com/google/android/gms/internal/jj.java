package com.google.android.gms.internal;

public abstract class jj<T> {
    private static a TM;
    private static final Object mK;
    protected final String TN;
    protected final T TO;
    private T TP;

    private interface a {
    }

    static {
        mK = new Object();
        TM = null;
    }

    protected jj(String str, T t) {
        this.TP = null;
        this.TN = str;
        this.TO = t;
    }

    public static jj<Integer> a(String str, Integer num) {
        return new jj<Integer>(str, num) {
        };
    }

    public static jj<Boolean> j(String str, boolean z) {
        return new jj<Boolean>(str, Boolean.valueOf(z)) {
        };
    }

    public static jj<String> s(String str, String str2) {
        return new jj<String>(str, str2) {
        };
    }
}
