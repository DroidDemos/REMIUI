package com.google.android.gms.internal;

import android.util.Log;

public final class kf {
    private final String Wt;

    public kf(String str) {
        this.Wt = (String) kn.k(str);
    }

    public boolean di(int i) {
        return Log.isLoggable(this.Wt, i);
    }

    public void w(String str, String str2) {
        if (di(5)) {
            Log.w(str, str2);
        }
    }
}
