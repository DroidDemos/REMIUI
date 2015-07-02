package org.apache.thrift.transport;

public abstract class c {
    public abstract int a(byte[] bArr, int i, int i2);

    public void a(int i) {
    }

    public byte[] ag() {
        return null;
    }

    public int ah() {
        return 0;
    }

    public int c() {
        return -1;
    }

    public abstract void e(byte[] bArr, int i, int i2);

    public int j(byte[] bArr, int i, int i2) {
        int i3 = 0;
        while (i3 < i2) {
            int a = a(bArr, i + i3, i2 - i3);
            if (a <= 0) {
                throw new TTransportException("Cannot read. Remote side has closed. Tried to read " + i2 + " bytes, but only got " + i3 + " bytes.");
            }
            i3 += a;
        }
        return i3;
    }

    public void l(byte[] bArr) {
        e(bArr, 0, bArr.length);
    }
}
