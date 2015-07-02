package com.google.android.gms.internal;

import android.os.Bundle;
import java.util.regex.Pattern;

public abstract class sb<T> {
    public static b aEy;
    public static a aEz;
    private final char aEt;
    private final char aEu;
    private final String aEv;
    private final String aEw;
    private final Bundle aEx;

    public static class a extends sb<Object> {
        public a(Bundle bundle) {
            super(bundle, '\u0001', '\u0002');
        }
    }

    public static class b extends sb<Object> {
        public b(Bundle bundle) {
            super(bundle, '\u0001', '\u0002');
        }
    }

    static {
        aEy = new b(Bundle.EMPTY);
        aEz = new a(Bundle.EMPTY);
    }

    sb(Bundle bundle, char c, char c2) {
        this.aEx = bundle;
        this.aEt = c;
        this.aEu = c2;
        this.aEv = Pattern.quote(String.valueOf(this.aEt));
        this.aEw = Pattern.quote(String.valueOf(this.aEu));
    }
}
