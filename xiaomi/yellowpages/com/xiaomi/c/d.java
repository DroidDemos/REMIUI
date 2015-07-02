package com.xiaomi.c;

import java.io.FilterInputStream;
import java.io.InputStream;

public final class d extends FilterInputStream {
    private boolean a;

    public d(InputStream inputStream) {
        super(inputStream);
    }

    public int read(byte[] bArr, int i, int i2) {
        if (!this.a) {
            int read = super.read(bArr, i, i2);
            if (read != -1) {
                return read;
            }
        }
        this.a = true;
        return -1;
    }
}
