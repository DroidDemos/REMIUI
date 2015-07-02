package com.google.android.finsky.utils;

public class OrderHistoryHelper {
    private static long sLastMutationTimeMs;

    public static boolean hasMutationOccurredSince(long timestampMs) {
        return timestampMs < sLastMutationTimeMs;
    }

    public static void onMutationOccurred() {
        sLastMutationTimeMs = System.currentTimeMillis();
    }
}
