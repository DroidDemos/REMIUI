package com.google.common.primitives;

import java.math.BigInteger;

/* compiled from: UnsignedLongs */
public final class b {
    private static final long[] rc;
    private static final int[] rd;
    private static final int[] re;

    private b() {
    }

    private static long l(long j) {
        return Long.MIN_VALUE ^ j;
    }

    public static int compare(long j, long j2) {
        return a.compare(l(j), l(j2));
    }

    public static long c(long j, long j2) {
        int i = 1;
        if (j2 < 0) {
            if (compare(j, j2) < 0) {
                return 0;
            }
            return 1;
        } else if (j >= 0) {
            return j / j2;
        } else {
            long j3 = ((j >>> 1) / j2) << 1;
            if (compare(j - (j3 * j2), j2) < 0) {
                i = 0;
            }
            return j3 + ((long) i);
        }
    }

    public static long d(long j, long j2) {
        if (j2 < 0) {
            if (compare(j, j2) < 0) {
                return j;
            }
            return j - j2;
        } else if (j >= 0) {
            return j % j2;
        } else {
            long j3 = j - ((((j >>> 1) / j2) << 1) * j2);
            if (compare(j3, j2) < 0) {
                j2 = 0;
            }
            return j3 - j2;
        }
    }

    static {
        rc = new long[37];
        rd = new int[37];
        re = new int[37];
        BigInteger bigInteger = new BigInteger("10000000000000000", 16);
        for (int i = 2; i <= 36; i++) {
            rc[i] = c(-1, (long) i);
            rd[i] = (int) d(-1, (long) i);
            re[i] = bigInteger.toString(i).length() - 1;
        }
    }
}
