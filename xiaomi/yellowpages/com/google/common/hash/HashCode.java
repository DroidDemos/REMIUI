package com.google.common.hash;

import com.google.common.b.a;
import com.google.common.primitives.Ints;
import java.io.Serializable;
import java.security.MessageDigest;

public abstract class HashCode {
    private static final char[] ce;

    final class BytesHashCode extends HashCode implements Serializable {
        private static final long serialVersionUID = 0;
        final byte[] bytes;

        BytesHashCode(byte[] bArr) {
            this.bytes = (byte[]) a.checkNotNull(bArr);
        }

        public int w() {
            return this.bytes.length * 8;
        }

        public byte[] z() {
            return (byte[]) this.bytes.clone();
        }

        public int x() {
            boolean z;
            if (this.bytes.length >= 4) {
                z = true;
            } else {
                z = false;
            }
            a.b(z, "HashCode#asInt() requires >= 4 bytes (it only has %s bytes).", Integer.valueOf(this.bytes.length));
            return (((this.bytes[0] & 255) | ((this.bytes[1] & 255) << 8)) | ((this.bytes[2] & 255) << 16)) | ((this.bytes[3] & 255) << 24);
        }

        public long y() {
            boolean z;
            if (this.bytes.length >= 8) {
                z = true;
            } else {
                z = false;
            }
            a.b(z, "HashCode#asLong() requires >= 8 bytes (it only has %s bytes).", Integer.valueOf(this.bytes.length));
            return gK();
        }

        public long gK() {
            long j = (long) (this.bytes[0] & 255);
            for (int i = 1; i < Math.min(this.bytes.length, 8); i++) {
                j |= (((long) this.bytes[i]) & 255) << (i * 8);
            }
            return j;
        }

        void c(byte[] bArr, int i, int i2) {
            System.arraycopy(this.bytes, 0, bArr, i, i2);
        }

        byte[] A() {
            return this.bytes;
        }

        boolean a(HashCode hashCode) {
            return MessageDigest.isEqual(this.bytes, hashCode.A());
        }
    }

    abstract boolean a(HashCode hashCode);

    abstract void c(byte[] bArr, int i, int i2);

    public abstract int w();

    public abstract int x();

    public abstract long y();

    public abstract byte[] z();

    HashCode() {
    }

    public int b(byte[] bArr, int i, int i2) {
        int min = Ints.min(i2, w() / 8);
        a.a(i, i + min, bArr.length);
        c(bArr, i, min);
        return min;
    }

    byte[] A() {
        return z();
    }

    static HashCode c(byte[] bArr) {
        return new BytesHashCode(bArr);
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof HashCode)) {
            return false;
        }
        HashCode hashCode = (HashCode) obj;
        if (w() == hashCode.w() && a(hashCode)) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        if (w() >= 32) {
            return x();
        }
        byte[] z = z();
        int i = z[0] & 255;
        for (int i2 = 1; i2 < z.length; i2++) {
            i |= (z[i2] & 255) << (i2 * 8);
        }
        return i;
    }

    public final String toString() {
        byte[] z = z();
        StringBuilder stringBuilder = new StringBuilder(z.length * 2);
        for (byte b : z) {
            stringBuilder.append(ce[(b >> 4) & 15]).append(ce[b & 15]);
        }
        return stringBuilder.toString();
    }

    static {
        ce = "0123456789abcdef".toCharArray();
    }
}
