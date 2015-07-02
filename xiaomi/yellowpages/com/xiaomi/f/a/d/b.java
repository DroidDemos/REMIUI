package com.xiaomi.f.a.d;

import java.io.FilterInputStream;
import java.io.InputStream;

public final class b extends FilterInputStream {
    private boolean a;

    public b(InputStream inputStream) {
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
