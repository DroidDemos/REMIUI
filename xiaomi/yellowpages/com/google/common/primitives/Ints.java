package com.google.common.primitives;

import com.google.common.b.a;
import java.util.Arrays;

public final class Ints {
    private static final byte[] Ma;

    private Ints() {
    }

    public static int v(long j) {
        int i = (int) j;
        if (((long) i) == j) {
            return i;
        }
        throw new IllegalArgumentException("Out of range: " + j);
    }

    public static int min(int... iArr) {
        boolean z;
        int i = 1;
        if (iArr.length > 0) {
            z = true;
        } else {
            z = false;
        }
        a.checkArgument(z);
        int i2 = iArr[0];
        while (i < iArr.length) {
            if (iArr[i] < i2) {
                i2 = iArr[i];
            }
            i++;
        }
        return i2;
    }

    static {
        int i = 0;
        Ma = new byte[128];
        Arrays.fill(Ma, (byte) -1);
        for (int i2 = 0; i2 <= 9; i2++) {
            Ma[i2 + 48] = (byte) i2;
        }
        while (i <= 26) {
            Ma[i + 65] = (byte) (i + 10);
            Ma[i + 97] = (byte) (i + 10);
            i++;
        }
    }
}
