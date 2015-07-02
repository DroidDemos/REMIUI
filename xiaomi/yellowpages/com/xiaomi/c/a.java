package com.xiaomi.c;

public class a {
    private static final String a;
    private static char[] cp;
    private static byte[] cq;

    static {
        int i;
        int i2 = 0;
        a = System.getProperty("line.separator");
        cp = new char[64];
        int i3 = 0;
        char c = 'A';
        while (c <= 'Z') {
            int i4 = i3 + 1;
            cp[i3] = c;
            c = (char) (c + 1);
            i3 = i4;
        }
        char c2 = 'a';
        while (c2 <= 'z') {
            i = i3 + 1;
            cp[i3] = c2;
            c2 = (char) (c2 + 1);
            i3 = i;
        }
        c2 = '0';
        while (c2 <= '9') {
            i = i3 + 1;
            cp[i3] = c2;
            c2 = (char) (c2 + 1);
            i3 = i;
        }
        i = i3 + 1;
        cp[i3] = '+';
        i3 = i + 1;
        cp[i] = '/';
        cq = new byte[128];
        for (i4 = 0; i4 < cq.length; i4++) {
            cq[i4] = (byte) -1;
        }
        while (i2 < 64) {
            cq[cp[i2]] = (byte) i2;
            i2++;
        }
    }

    private a() {
    }

    public static char[] d(byte[] bArr) {
        return d(bArr, 0, bArr.length);
    }

    public static char[] d(byte[] bArr, int i, int i2) {
        int i3 = ((i2 * 4) + 2) / 3;
        char[] cArr = new char[(((i2 + 2) / 3) * 4)];
        int i4 = i + i2;
        int i5 = 0;
        while (i < i4) {
            int i6;
            int i7;
            int i8 = i + 1;
            int i9 = bArr[i] & 255;
            if (i8 < i4) {
                i6 = bArr[i8] & 255;
                i8++;
            } else {
                i6 = 0;
            }
            if (i8 < i4) {
                i7 = i8 + 1;
                i8 = bArr[i8] & 255;
            } else {
                i7 = i8;
                i8 = 0;
            }
            int i10 = i9 >>> 2;
            i9 = ((i9 & 3) << 4) | (i6 >>> 4);
            i6 = ((i6 & 15) << 2) | (i8 >>> 6);
            int i11 = i8 & 63;
            i8 = i5 + 1;
            cArr[i5] = cp[i10];
            i5 = i8 + 1;
            cArr[i8] = cp[i9];
            cArr[i5] = i5 < i3 ? cp[i6] : '=';
            i6 = i5 + 1;
            cArr[i6] = i6 < i3 ? cp[i11] : '=';
            i5 = i6 + 1;
            i = i7;
        }
        return cArr;
    }
}
