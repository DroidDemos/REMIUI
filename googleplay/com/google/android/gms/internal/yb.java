package com.google.android.gms.internal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class yb {
    private xz<?, ?> aYq;
    private Object aYr;
    private List<yg> aYs;

    yb() {
        this.aYs = new ArrayList();
    }

    private byte[] toByteArray() throws IOException {
        byte[] bArr = new byte[c()];
        writeTo(xx.u(bArr));
        return bArr;
    }

    int c() {
        if (this.aYr != null) {
            return this.aYq.I(this.aYr);
        }
        int i = 0;
        for (yg c : this.aYs) {
            i = c.c() + i;
        }
        return i;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof yb)) {
            return false;
        }
        yb ybVar = (yb) o;
        if (this.aYr == null || ybVar.aYr == null) {
            if (this.aYs != null && ybVar.aYs != null) {
                return this.aYs.equals(ybVar.aYs);
            }
            try {
                return Arrays.equals(toByteArray(), ybVar.toByteArray());
            } catch (Throwable e) {
                throw new IllegalStateException(e);
            }
        } else if (this.aYq != ybVar.aYq) {
            return false;
        } else {
            if (!this.aYq.aYk.isArray()) {
                return this.aYr.equals(ybVar.aYr);
            }
            if (this.aYr instanceof byte[]) {
                return Arrays.equals((byte[]) this.aYr, (byte[]) ybVar.aYr);
            }
            if (this.aYr instanceof int[]) {
                return Arrays.equals((int[]) this.aYr, (int[]) ybVar.aYr);
            }
            if (this.aYr instanceof long[]) {
                return Arrays.equals((long[]) this.aYr, (long[]) ybVar.aYr);
            }
            if (this.aYr instanceof float[]) {
                return Arrays.equals((float[]) this.aYr, (float[]) ybVar.aYr);
            }
            if (this.aYr instanceof double[]) {
                return Arrays.equals((double[]) this.aYr, (double[]) ybVar.aYr);
            }
            return this.aYr instanceof boolean[] ? Arrays.equals((boolean[]) this.aYr, (boolean[]) ybVar.aYr) : Arrays.deepEquals((Object[]) this.aYr, (Object[]) ybVar.aYr);
        }
    }

    public int hashCode() {
        try {
            return Arrays.hashCode(toByteArray()) + 527;
        } catch (Throwable e) {
            throw new IllegalStateException(e);
        }
    }

    void writeTo(xx output) throws IOException {
        if (this.aYr != null) {
            this.aYq.a(this.aYr, output);
            return;
        }
        for (yg writeTo : this.aYs) {
            writeTo.writeTo(output);
        }
    }
}
