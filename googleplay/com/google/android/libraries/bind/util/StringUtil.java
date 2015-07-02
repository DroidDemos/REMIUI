package com.google.android.libraries.bind.util;

public class StringUtil {
    public static long getLongHash(String str) {
        long hash = 0;
        for (int i = 0; i < str.length(); i++) {
            hash = (63 * hash) + ((long) str.charAt(i));
        }
        return hash;
    }
}
