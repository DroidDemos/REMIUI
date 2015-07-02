package com.google.common.primitives;

/* compiled from: Longs */
public final class a {
    private a() {
    }

    public static int compare(long j, long j2) {
        if (j < j2) {
            return -1;
        }
        return j > j2 ? 1 : 0;
    }

    public static long a(byte b, byte b2, byte b3, byte b4, byte b5, byte b6, byte b7, byte b8) {
        return ((((((((((long) b) & 255) << 56) | ((((long) b2) & 255) << 48)) | ((((long) b3) & 255) << 40)) | ((((long) b4) & 255) << 32)) | ((((long) b5) & 255) << 24)) | ((((long) b6) & 255) << 16)) | ((((long) b7) & 255) << 8)) | (((long) b8) & 255);
    }
}
