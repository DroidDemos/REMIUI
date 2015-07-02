package com.google.android.finsky.utils;

public interface PlayCardImageTypeSequence {
    public static final int[] CORE_IMAGE_TYPES;
    public static final int[] PROMO_IMAGE_TYPES;

    int[] getImageTypePreference();

    static {
        CORE_IMAGE_TYPES = new int[]{4, 0};
        PROMO_IMAGE_TYPES = new int[]{2, 4, 0};
    }
}
