package com.xiaomi.b.a;

abstract class A implements Comparable {
    private final Comparable oM;

    protected A(Comparable comparable) {
        this.oM = comparable;
    }

    public int compareTo(Object obj) {
        return obj == null ? 1 : this.oM.compareTo(obj);
    }

    public final Comparable cw() {
        return this.oM;
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof A)) {
            return false;
        }
        return this.oM.equals(((A) obj).oM);
    }

    public int hashCode() {
        return this.oM.hashCode();
    }

    public String toString() {
        return this.oM.toString();
    }
}
