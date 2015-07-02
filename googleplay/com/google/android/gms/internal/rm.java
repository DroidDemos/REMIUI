package com.google.android.gms.internal;

import android.os.Bundle;
import java.util.regex.Pattern;

public class rm {
    public static final rm aCt;
    private Pattern[] aCu;
    private String[] aCv;

    static {
        aCt = new rm();
    }

    private rm() {
        this.aCu = new Pattern[0];
        this.aCv = new String[0];
    }

    public synchronized void a(String[] strArr, String[] strArr2) {
        int i = 0;
        synchronized (this) {
            kn.L(strArr.length == strArr2.length);
            this.aCu = new Pattern[strArr.length];
            this.aCv = strArr2;
            while (i < strArr.length) {
                this.aCu[i] = Pattern.compile(strArr[i]);
                i++;
            }
        }
    }

    public void t(Bundle bundle) {
        a(bundle.getStringArray("config.url_uncompress.patterns"), bundle.getStringArray("config.url_uncompress.replacements"));
    }
}
