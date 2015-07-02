package com.google.android.gms.car.support;

import android.util.Log;
import java.io.Writer;

public class i extends Writer {
    private StringBuilder Nv;
    private final String mTag;

    public i(String str) {
        this.Nv = new StringBuilder(128);
        this.mTag = str;
    }

    private void hk() {
        if (this.Nv.length() > 0) {
            Log.d(this.mTag, this.Nv.toString());
            this.Nv.delete(0, this.Nv.length());
        }
    }

    public void close() {
        hk();
    }

    public void flush() {
        hk();
    }

    public void write(char[] buf, int offset, int count) {
        for (int i = 0; i < count; i++) {
            char c = buf[offset + i];
            if (c == '\n') {
                hk();
            } else {
                this.Nv.append(c);
            }
        }
    }
}
