package com.google.android.play.utils;

public class Assertions {
    public static void checkState(boolean state, String message) throws IllegalStateException {
        if (!state) {
            throw new IllegalStateException(message);
        }
    }

    public static void checkArgument(boolean state, String message) throws IllegalArgumentException {
        if (!state) {
            throw new IllegalArgumentException(message);
        }
    }
}
