package com.google.android.wallet.instrumentmanager.common.util;

import java.lang.reflect.Array;
import java.util.Arrays;

public final class ArrayUtils {
    public static <T> int indexOf(T[] array, T searchValue) {
        int arrayLength = array != null ? array.length : 0;
        for (int i = 0; i < arrayLength; i++) {
            if (Objects.equals(array[i], searchValue)) {
                return i;
            }
        }
        return -1;
    }

    public static <T> boolean contains(T[] array, T searchValue) {
        return indexOf(array, searchValue) >= 0;
    }

    public static boolean contains(int[] array, int value) {
        if (array == null) {
            return false;
        }
        for (int i : array) {
            if (i == value) {
                return true;
            }
        }
        return false;
    }

    public static boolean contains(char[] array, char value) {
        if (array == null) {
            return false;
        }
        for (char c : array) {
            if (c == value) {
                return true;
            }
        }
        return false;
    }

    public static <T> T[] appendToArray(T[] from, T itemToAppend) {
        if (from == null && itemToAppend == null) {
            throw new IllegalArgumentException("Cannot generate array of generic type w/o class info");
        }
        T[] result;
        if (from == null) {
            result = (Object[]) Array.newInstance(itemToAppend.getClass(), 1);
        } else {
            result = Arrays.copyOf(from, from.length + 1);
        }
        result[result.length - 1] = itemToAppend;
        return result;
    }

    public static Integer[] toWrapperArray(int[] array) {
        if (array == null) {
            return null;
        }
        int length = array.length;
        Integer[] newArray = new Integer[length];
        for (int i = 0; i < length; i++) {
            newArray[i] = Integer.valueOf(array[i]);
        }
        return newArray;
    }
}
