package com.xiaomi.kenai.jbosh;

abstract class AbstractAttr<T extends Comparable> implements Comparable {
    private final T value;

    protected AbstractAttr(T aValue) {
        this.value = aValue;
    }

    public final T getValue() {
        return this.value;
    }

    public boolean equals(Object otherObj) {
        if (otherObj == null || !(otherObj instanceof AbstractAttr)) {
            return false;
        }
        return this.value.equals(((AbstractAttr) otherObj).value);
    }

    public int hashCode() {
        return this.value.hashCode();
    }

    public String toString() {
        return this.value.toString();
    }

    public int compareTo(Object otherObj) {
        if (otherObj == null) {
            return 1;
        }
        return this.value.compareTo(otherObj);
    }
}
