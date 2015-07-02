package com.google.common.hash;

import com.google.common.b.a;
import com.google.common.b.b;
import java.io.Serializable;

public final class BloomFilter implements Serializable {
    private static final Strategy GK;
    private final a bits;
    private final Funnel funnel;
    private final int numHashFunctions;
    private final Strategy strategy;

    class SerialForm implements Serializable {
        private static final long serialVersionUID = 1;
        final long[] data;
        final Funnel funnel;
        final int numHashFunctions;
        final Strategy strategy;

        SerialForm(BloomFilter bloomFilter) {
            this.data = bloomFilter.bits.data;
            this.numHashFunctions = bloomFilter.numHashFunctions;
            this.funnel = bloomFilter.funnel;
            this.strategy = bloomFilter.strategy;
        }

        Object readResolve() {
            return new BloomFilter(new a(this.data), this.numHashFunctions, this.funnel, this.strategy);
        }
    }

    interface Strategy extends Serializable {
        boolean a(Object obj, Funnel funnel, int i, a aVar);

        boolean b(Object obj, Funnel funnel, int i, a aVar);
    }

    private BloomFilter(a aVar, int i, Funnel funnel, Strategy strategy) {
        boolean z;
        a.a(i > 0, "numHashFunctions (%s) must be > 0", Integer.valueOf(i));
        if (i <= 255) {
            z = true;
        } else {
            z = false;
        }
        a.a(z, "numHashFunctions (%s) must be <= 255", Integer.valueOf(i));
        this.bits = (a) a.checkNotNull(aVar);
        this.numHashFunctions = i;
        this.funnel = (Funnel) a.checkNotNull(funnel);
        this.strategy = (Strategy) a.checkNotNull(strategy);
    }

    public boolean w(Object obj) {
        return this.strategy.b(obj, this.funnel, this.numHashFunctions, this.bits);
    }

    public boolean put(Object obj) {
        return this.strategy.a(obj, this.funnel, this.numHashFunctions, this.bits);
    }

    long t() {
        return this.bits.t();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof BloomFilter)) {
            return false;
        }
        BloomFilter bloomFilter = (BloomFilter) obj;
        if (this.numHashFunctions == bloomFilter.numHashFunctions && this.funnel.equals(bloomFilter.funnel) && this.bits.equals(bloomFilter.bits) && this.strategy.equals(bloomFilter.strategy)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return b.hashCode(Integer.valueOf(this.numHashFunctions), this.funnel, this.strategy, this.bits);
    }

    static {
        GK = BloomFilterStrategies.MURMUR128_MITZ_64;
    }

    public static BloomFilter a(Funnel funnel, int i, double d) {
        return a(funnel, i, d, GK);
    }

    static BloomFilter a(Funnel funnel, int i, double d, Strategy strategy) {
        boolean z;
        int i2 = 1;
        a.checkNotNull(funnel);
        a.a(i >= 0, "Expected insertions (%s) must be >= 0", Integer.valueOf(i));
        if (d > 0.0d) {
            z = true;
        } else {
            z = false;
        }
        a.a(z, "False positive probability (%s) must be > 0.0", Double.valueOf(d));
        if (d < 1.0d) {
            z = true;
        } else {
            z = false;
        }
        a.a(z, "False positive probability (%s) must be < 1.0", Double.valueOf(d));
        a.checkNotNull(strategy);
        if (i != 0) {
            i2 = i;
        }
        long a = a((long) i2, d);
        try {
            return new BloomFilter(new a(a), e((long) i2, a), funnel, strategy);
        } catch (Throwable e) {
            throw new IllegalArgumentException("Could not create BloomFilter of " + a + " bits", e);
        }
    }

    static int e(long j, long j2) {
        return Math.max(1, (int) Math.round((((double) j2) / ((double) j)) * Math.log(2.0d)));
    }

    static long a(long j, double d) {
        if (d == 0.0d) {
            d = Double.MIN_VALUE;
        }
        return (long) ((((double) (-j)) * Math.log(d)) / (Math.log(2.0d) * Math.log(2.0d)));
    }

    private Object writeReplace() {
        return new SerialForm(this);
    }
}
