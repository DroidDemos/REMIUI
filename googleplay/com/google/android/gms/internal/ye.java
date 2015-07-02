package com.google.android.gms.internal;

import java.io.IOException;

public abstract class ye {
    protected volatile int aYu;

    public ye() {
        this.aYu = -1;
    }

    public static final void toByteArray(ye msg, byte[] data, int offset, int length) {
        try {
            xx b = xx.b(data, offset, length);
            msg.writeTo(b);
            b.vK();
        } catch (Throwable e) {
            throw new RuntimeException("Serializing to a byte array threw an IOException (should never happen).", e);
        }
    }

    public static final byte[] toByteArray(ye msg) {
        byte[] bArr = new byte[msg.getSerializedSize()];
        toByteArray(msg, bArr, 0, bArr.length);
        return bArr;
    }

    protected int c() {
        return 0;
    }

    public int getCachedSize() {
        if (this.aYu < 0) {
            getSerializedSize();
        }
        return this.aYu;
    }

    public int getSerializedSize() {
        int c = c();
        this.aYu = c;
        return c;
    }

    public String toString() {
        return yf.f(this);
    }

    public void writeTo(xx output) throws IOException {
    }
}
