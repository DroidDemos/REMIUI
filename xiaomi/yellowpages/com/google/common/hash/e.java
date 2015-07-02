package com.google.common.hash;

/* compiled from: Hashing */
class e {
    static final g lM;
    static final g lN;

    private e() {
    }

    static {
        lM = new Murmur3_128HashFunction(0);
        lN = Hashing.s(Hashing.ly);
    }
}
