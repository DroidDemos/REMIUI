package com.google.android.gms.car.support;

class d {
    static final int[] EMPTY_INTS;
    static final long[] EMPTY_LONGS;
    static final Object[] EMPTY_OBJECTS;

    static {
        EMPTY_INTS = new int[0];
        EMPTY_LONGS = new long[0];
        EMPTY_OBJECTS = new Object[0];
    }

    static int binarySearch(int[] array, int size, int value) {
        int i = 0;
        int i2 = size - 1;
        while (i <= i2) {
            int i3 = (i + i2) >>> 1;
            int i4 = array[i3];
            if (i4 < value) {
                i = i3 + 1;
            } else if (i4 <= value) {
                return i3;
            } else {
                i2 = i3 - 1;
            }
        }
        return i ^ -1;
    }

    public static boolean equal(Object a, Object b) {
        return a == b || (a != null && a.equals(b));
    }
}
