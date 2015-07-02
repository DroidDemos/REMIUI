package com.google.common.hash;

import java.io.Serializable;

final class Murmur3_128HashFunction extends i implements Serializable {
    private static final long serialVersionUID = 0;
    private final int seed;

    Murmur3_128HashFunction(int i) {
        this.seed = i;
    }

    public m av() {
        return new d(this.seed);
    }

    public String toString() {
        return "Hashing.murmur3_128(" + this.seed + ")";
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Murmur3_128HashFunction)) {
            return false;
        }
        if (this.seed == ((Murmur3_128HashFunction) obj).seed) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return getClass().hashCode() ^ this.seed;
    }
}
