package com.google.android.finsky.utils;

import android.os.Bundle;

public class ExpandableUtils {
    public static void saveExpansionState(Bundle bundle, String keySuffix, int expansionState) {
        bundle.putInt("expansion_state:" + keySuffix, expansionState);
    }

    public static int getSavedExpansionState(Bundle bundle, String keySuffix, int defaultValue) {
        if (bundle == null) {
            return defaultValue;
        }
        String key = "expansion_state:" + keySuffix;
        if (bundle.containsKey(key)) {
            return bundle.getInt(key);
        }
        return defaultValue;
    }
}
