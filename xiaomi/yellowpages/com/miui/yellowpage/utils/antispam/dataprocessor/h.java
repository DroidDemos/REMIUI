package com.miui.yellowpage.utils.antispam.dataprocessor;

/* compiled from: ConvertUtils */
public class h {
    public static int c(byte b) {
        return b & 255;
    }

    public static int byteArrayToInt(byte[] bArr) {
        return (((c(bArr[0]) << 24) | (c(bArr[1]) << 16)) | (c(bArr[2]) << 8)) | c(bArr[3]);
    }

    public static byte[] h(byte[] bArr, int i, int i2) {
        byte[] bArr2 = new byte[i2];
        for (int i3 = 0; i3 < i2; i3++) {
            bArr2[i3] = bArr[i + i3];
        }
        return bArr2;
    }
}
