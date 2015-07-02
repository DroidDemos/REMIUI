package com.google.android.finsky.utils;

public class IntMath {
    public static int ceil(int numerator, int denominator) {
        return ((numerator + denominator) - 1) / denominator;
    }

    public static int floor(int numerator, int denominator) {
        return numerator / denominator;
    }
}
