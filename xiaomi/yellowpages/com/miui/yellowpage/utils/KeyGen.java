package com.miui.yellowpage.utils;

import android.content.Context;

public class KeyGen {
    public static native String getUUID(Context context);

    static {
        try {
            System.loadLibrary("keygen_jni");
        } catch (UnsatisfiedLinkError e) {
            e.printStackTrace();
        }
    }
}
