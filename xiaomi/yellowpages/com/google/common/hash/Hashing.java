package com.google.common.hash;

public final class Hashing {
    private static final int ly;

    static {
        ly = (int) System.currentTimeMillis();
    }

    public static g s(int i) {
        return new Murmur3_128HashFunction(i);
    }

    public static g bG() {
        return e.lM;
    }

    private Hashing() {
    }
}
