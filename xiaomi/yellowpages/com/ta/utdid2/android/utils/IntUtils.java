package com.ta.utdid2.android.utils;

public class IntUtils {
    public static byte[] getBytes(int i) {
        byte[] bArr = new byte[4];
        bArr[3] = (byte) (i % 256);
        int i2 = i >> 8;
        bArr[2] = (byte) (i2 % 256);
        i2 >>= 8;
        bArr[1] = (byte) (i2 % 256);
        bArr[0] = (byte) ((i2 >> 8) % 256);
        return bArr;
    }

    public static byte[] getBytes(byte[] bArr, int i) {
        if (bArr.length != 4) {
            return null;
        }
        bArr[3] = (byte) (i % 256);
        int i2 = i >> 8;
        bArr[2] = (byte) (i2 % 256);
        i2 >>= 8;
        bArr[1] = (byte) (i2 % 256);
        bArr[0] = (byte) ((i2 >> 8) % 256);
        return bArr;
    }
}
