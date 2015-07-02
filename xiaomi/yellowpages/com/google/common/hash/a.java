package com.google.common.hash;

import com.google.common.a.b;
import com.google.common.primitives.Ints;
import java.math.RoundingMode;
import java.util.Arrays;

/* compiled from: BloomFilterStrategies */
final class a {
    long bW;
    final long[] data;

    a(long j) {
        this(new long[Ints.v(b.a(j, 64, RoundingMode.CEILING))]);
    }

    a(long[] jArr) {
        boolean z;
        int i = 0;
        if (jArr.length > 0) {
            z = true;
        } else {
            z = false;
        }
        com.google.common.b.a.a(z, "data length is zero!");
        this.data = jArr;
        long j = 0;
        while (i < jArr.length) {
            j += (long) Long.bitCount(jArr[i]);
            i++;
        }
        this.bW = j;
    }

    boolean c(long j) {
        if (d(j)) {
            return false;
        }
        long[] jArr = this.data;
        int i = (int) (j >>> 6);
        jArr[i] = jArr[i] | (1 << ((int) j));
        this.bW++;
        return true;
    }

    boolean d(long j) {
        return (this.data[(int) (j >>> 6)] & (1 << ((int) j))) != 0;
    }

    long t() {
        return ((long) this.data.length) * 64;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof a)) {
            return false;
        }
        return Arrays.equals(this.data, ((a) obj).data);
    }

    public int hashCode() {
        return Arrays.hashCode(this.data);
    }
}
