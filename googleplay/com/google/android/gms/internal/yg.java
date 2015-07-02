package com.google.android.gms.internal;

import java.io.IOException;
import java.util.Arrays;

final class yg {
    final byte[] bytes;
    final int tag;

    int c() {
        return (0 + xx.mn(this.tag)) + this.bytes.length;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof yg)) {
            return false;
        }
        yg ygVar = (yg) o;
        return this.tag == ygVar.tag && Arrays.equals(this.bytes, ygVar.bytes);
    }

    public int hashCode() {
        return ((this.tag + 527) * 31) + Arrays.hashCode(this.bytes);
    }

    void writeTo(xx output) throws IOException {
        output.mm(this.tag);
        output.x(this.bytes);
    }
}
