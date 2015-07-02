package com.google.android.finsky.utils;

import java.lang.reflect.Array;
import java.util.AbstractList;
import java.util.Collections;
import java.util.List;

public class ArrayUtils {

    private static class ArrayAsList<T> extends AbstractList<T> {
        private final T[] mArray;

        public ArrayAsList(T[] array) {
            this.mArray = array;
        }

        public T get(int location) {
            return this.mArray[location];
        }

        public int size() {
            return this.mArray.length;
        }
    }

    public static <T> List<T> asList(T[] array) {
        if (array == null || array.length == 0) {
            return Collections.emptyList();
        }
        return new ArrayAsList(array);
    }

    public static <T> T[] concatenate(T[] a, T[] b) {
        if (a == null || b == null) {
            throw new IllegalArgumentException();
        } else if (a.length == 0) {
            return b;
        } else {
            if (b.length == 0) {
                return a;
            }
            T[] result = (Object[]) ((Object[]) Array.newInstance(a.getClass().getComponentType(), a.length + b.length));
            System.arraycopy(a, 0, result, 0, a.length);
            System.arraycopy(b, 0, result, a.length, b.length);
            return result;
        }
    }

    public static <T> T[] remove(T[] array, int index) {
        if (index < 0 || index >= array.length) {
            throw new ArrayIndexOutOfBoundsException(index);
        }
        int resultLength = array.length - 1;
        Object[] result = (Object[]) ((Object[]) Array.newInstance(array.getClass().getComponentType(), resultLength));
        if (index != 0) {
            System.arraycopy(array, 0, result, 0, index);
        }
        int remainder = resultLength - index;
        if (remainder > 0) {
            System.arraycopy(array, index + 1, result, index, remainder);
        }
        return result;
    }
}
