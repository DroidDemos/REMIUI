package com.google.android.gms.internal;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public abstract class at {
    private static MessageDigest nU;
    protected Object mK;

    static {
        nU = null;
    }

    public at() {
        this.mK = new Object();
    }

    protected MessageDigest bf() {
        MessageDigest messageDigest;
        synchronized (this.mK) {
            if (nU != null) {
                messageDigest = nU;
            } else {
                for (int i = 0; i < 2; i++) {
                    try {
                        nU = MessageDigest.getInstance("MD5");
                    } catch (NoSuchAlgorithmException e) {
                    }
                }
                messageDigest = nU;
            }
        }
        return messageDigest;
    }

    abstract byte[] o(String str);
}
