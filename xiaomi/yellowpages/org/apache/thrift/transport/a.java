package org.apache.thrift.transport;

public final class a extends c {
    private int b;
    private int c;
    private byte[] mP;

    public int a(byte[] bArr, int i, int i2) {
        int c = c();
        if (i2 > c) {
            i2 = c;
        }
        if (i2 > 0) {
            System.arraycopy(this.mP, this.b, bArr, i, i2);
            a(i2);
        }
        return i2;
    }

    public void a(int i) {
        this.b += i;
    }

    public byte[] ag() {
        return this.mP;
    }

    public int ah() {
        return this.b;
    }

    public int c() {
        return this.c - this.b;
    }

    public void e(byte[] bArr, int i, int i2) {
        throw new UnsupportedOperationException("No writing allowed!");
    }

    public void f(byte[] bArr) {
        f(bArr, 0, bArr.length);
    }

    public void f(byte[] bArr, int i, int i2) {
        this.mP = bArr;
        this.b = i;
        this.c = i + i2;
    }
}
