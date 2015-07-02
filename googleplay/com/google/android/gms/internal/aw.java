package com.google.android.gms.internal;

import java.security.MessageDigest;

public class aw extends at {
    private MessageDigest ob;

    byte[] a(String[] strArr) {
        byte[] bArr = new byte[strArr.length];
        for (int i = 0; i < strArr.length; i++) {
            bArr[i] = e(av.r(strArr[i]));
        }
        return bArr;
    }

    byte e(int i) {
        return (byte) ((((i & 255) ^ ((65280 & i) >> 8)) ^ ((16711680 & i) >> 16)) ^ ((-16777216 & i) >> 24));
    }

    public byte[] o(String str) {
        byte[] a = a(str.split(" "));
        this.ob = bf();
        synchronized (this.mK) {
            if (this.ob == null) {
                a = new byte[0];
            } else {
                this.ob.reset();
                this.ob.update(a);
                Object digest = this.ob.digest();
                int i = 4;
                if (digest.length <= 4) {
                    i = digest.length;
                }
                a = new byte[i];
                System.arraycopy(digest, 0, a, 0, a.length);
            }
        }
        return a;
    }
}
