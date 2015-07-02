package com.xiaomi.push.service;

import com.xiaomi.f.a.a.a;

public class G {
    private static int a;
    private int c;
    private int d;
    private int e;
    private byte[] vx;

    static {
        a = 8;
    }

    public G() {
        this.e = -666;
        this.vx = new byte[256];
        this.d = 0;
        this.c = 0;
    }

    public static String a(byte[] bArr, String str) {
        return String.valueOf(a.d(c(bArr, str.getBytes())));
    }

    private void a(int i, byte[] bArr, boolean z) {
        int i2 = 0;
        int length = bArr.length;
        for (int i3 = 0; i3 < 256; i3++) {
            this.vx[i3] = (byte) i3;
        }
        this.d = 0;
        this.c = 0;
        while (this.c < i) {
            this.d = ((this.d + b(this.vx[this.c])) + b(bArr[this.c % length])) % 256;
            g(this.vx, this.c, this.d);
            this.c++;
        }
        if (i != 256) {
            this.e = ((this.d + b(this.vx[i])) + b(bArr[i % length])) % 256;
        }
        if (z) {
            System.out.print("S_" + (i - 1) + ":");
            while (i2 <= i) {
                System.out.print(" " + b(this.vx[i2]));
                i2++;
            }
            System.out.print("   j_" + (i - 1) + "=" + this.d);
            System.out.print("   j_" + i + "=" + this.e);
            System.out.print("   S_" + (i - 1) + "[j_" + (i - 1) + "]=" + b(this.vx[this.d]));
            System.out.print("   S_" + (i - 1) + "[j_" + i + "]=" + b(this.vx[this.e]));
            if (this.vx[1] != (byte) 0) {
                System.out.print("   S[1]!=0");
            }
            System.out.println();
        }
    }

    public static int b(byte b) {
        return b >= (byte) 0 ? b : b + 256;
    }

    private void b() {
        this.d = 0;
        this.c = 0;
    }

    public static byte[] b(byte[] bArr, String str) {
        return c(bArr, a.a(str));
    }

    public static byte[] c(byte[] bArr, byte[] bArr2) {
        byte[] bArr3 = new byte[bArr2.length];
        G g = new G();
        g.f(bArr);
        g.b();
        for (int i = 0; i < bArr2.length; i++) {
            bArr3[i] = (byte) (bArr2[i] ^ g.fq());
        }
        return bArr3;
    }

    private void f(byte[] bArr) {
        a(256, bArr, false);
    }

    private static void g(byte[] bArr, int i, int i2) {
        byte b = bArr[i];
        bArr[i] = bArr[i2];
        bArr[i2] = b;
    }

    public static byte[] s(String str, String str2) {
        int i = 0;
        byte[] a = a.a(str);
        byte[] bytes = str2.getBytes();
        byte[] bArr = new byte[((a.length + 1) + bytes.length)];
        for (int i2 = 0; i2 < a.length; i2++) {
            bArr[i2] = a[i2];
        }
        bArr[a.length] = (byte) 95;
        while (i < bytes.length) {
            bArr[(a.length + 1) + i] = bytes[i];
            i++;
        }
        return bArr;
    }

    byte fq() {
        this.c = (this.c + 1) % 256;
        this.d = (this.d + b(this.vx[this.c])) % 256;
        g(this.vx, this.c, this.d);
        return this.vx[(b(this.vx[this.c]) + b(this.vx[this.d])) % 256];
    }
}
