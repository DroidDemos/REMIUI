package com.google.android.gms.internal;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public final class xx {
    private final int aLZ;
    private final byte[] buffer;
    private int position;

    public static class a extends IOException {
        a(int i, int i2) {
            super("CodedOutputStream was writing to a flat byte array and ran out of space (pos " + i + " limit " + i2 + ").");
        }
    }

    private xx(byte[] bArr, int i, int i2) {
        this.buffer = bArr;
        this.position = i;
        this.aLZ = i + i2;
    }

    public static int E(int i, int i2) {
        return ml(i) + mi(i2);
    }

    public static int H(long j) {
        return K(j);
    }

    public static int I(long j) {
        return K(M(j));
    }

    public static int K(long j) {
        return (-128 & j) == 0 ? 1 : (-16384 & j) == 0 ? 2 : (-2097152 & j) == 0 ? 3 : (-268435456 & j) == 0 ? 4 : (-34359738368L & j) == 0 ? 5 : (-4398046511104L & j) == 0 ? 6 : (-562949953421312L & j) == 0 ? 7 : (-72057594037927936L & j) == 0 ? 8 : (Long.MIN_VALUE & j) == 0 ? 9 : 10;
    }

    public static long M(long j) {
        return (j << 1) ^ (j >> 63);
    }

    public static int ac(boolean z) {
        return 1;
    }

    public static int b(int i, ye yeVar) {
        return (ml(i) * 2) + d(yeVar);
    }

    public static int b(int i, byte[] bArr) {
        return ml(i) + w(bArr);
    }

    public static xx b(byte[] bArr, int i, int i2) {
        return new xx(bArr, i, i2);
    }

    public static int c(int i, ye yeVar) {
        return ml(i) + e(yeVar);
    }

    public static int c(int i, boolean z) {
        return ml(i) + ac(z);
    }

    public static int d(int i, long j) {
        return ml(i) + H(j);
    }

    public static int d(ye yeVar) {
        return yeVar.getSerializedSize();
    }

    public static int e(int i, long j) {
        return ml(i) + I(j);
    }

    public static int e(ye yeVar) {
        int serializedSize = yeVar.getSerializedSize();
        return serializedSize + mn(serializedSize);
    }

    public static int ex(String str) {
        try {
            byte[] bytes = str.getBytes("UTF-8");
            return bytes.length + mn(bytes.length);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("UTF-8 not supported.");
        }
    }

    public static int j(int i, String str) {
        return ml(i) + ex(str);
    }

    public static int mi(int i) {
        return i >= 0 ? mn(i) : 10;
    }

    public static int ml(int i) {
        return mn(yh.H(i, 0));
    }

    public static int mn(int i) {
        return (i & -128) == 0 ? 1 : (i & -16384) == 0 ? 2 : (-2097152 & i) == 0 ? 3 : (-268435456 & i) == 0 ? 4 : 5;
    }

    public static xx u(byte[] bArr) {
        return b(bArr, 0, bArr.length);
    }

    public static int w(byte[] bArr) {
        return mn(bArr.length) + bArr.length;
    }

    public void C(int i, int i2) throws IOException {
        G(i, 0);
        mg(i2);
    }

    public void F(long j) throws IOException {
        J(j);
    }

    public void G(int i, int i2) throws IOException {
        mm(yh.H(i, i2));
    }

    public void G(long j) throws IOException {
        J(M(j));
    }

    public void J(long j) throws IOException {
        while ((-128 & j) != 0) {
            mk((((int) j) & 127) | 128);
            j >>>= 7;
        }
        mk((int) j);
    }

    public void a(int i, ye yeVar) throws IOException {
        G(i, 2);
        c(yeVar);
    }

    public void a(int i, byte[] bArr) throws IOException {
        G(i, 2);
        v(bArr);
    }

    public void ab(boolean z) throws IOException {
        mk(z ? 1 : 0);
    }

    public void b(byte b) throws IOException {
        if (this.position == this.aLZ) {
            throw new a(this.position, this.aLZ);
        }
        byte[] bArr = this.buffer;
        int i = this.position;
        this.position = i + 1;
        bArr[i] = b;
    }

    public void b(int i, long j) throws IOException {
        G(i, 0);
        F(j);
    }

    public void b(int i, String str) throws IOException {
        G(i, 2);
        ew(str);
    }

    public void b(int i, boolean z) throws IOException {
        G(i, 0);
        ab(z);
    }

    public void b(ye yeVar) throws IOException {
        yeVar.writeTo(this);
    }

    public void c(int i, long j) throws IOException {
        G(i, 0);
        G(j);
    }

    public void c(ye yeVar) throws IOException {
        mm(yeVar.getCachedSize());
        yeVar.writeTo(this);
    }

    public void c(byte[] bArr, int i, int i2) throws IOException {
        if (this.aLZ - this.position >= i2) {
            System.arraycopy(bArr, i, this.buffer, this.position, i2);
            this.position += i2;
            return;
        }
        throw new a(this.position, this.aLZ);
    }

    public void ew(String str) throws IOException {
        byte[] bytes = str.getBytes("UTF-8");
        mm(bytes.length);
        x(bytes);
    }

    public void mg(int i) throws IOException {
        if (i >= 0) {
            mm(i);
        } else {
            J((long) i);
        }
    }

    public void mk(int i) throws IOException {
        b((byte) i);
    }

    public void mm(int i) throws IOException {
        while ((i & -128) != 0) {
            mk((i & 127) | 128);
            i >>>= 7;
        }
        mk(i);
    }

    public void v(byte[] bArr) throws IOException {
        mm(bArr.length);
        x(bArr);
    }

    public int vJ() {
        return this.aLZ - this.position;
    }

    public void vK() {
        if (vJ() != 0) {
            throw new IllegalStateException("Did not write as much data as expected.");
        }
    }

    public void x(byte[] bArr) throws IOException {
        c(bArr, 0, bArr.length);
    }
}
