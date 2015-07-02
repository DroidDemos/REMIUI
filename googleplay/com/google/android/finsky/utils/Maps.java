package com.google.android.finsky.utils;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class Maps {
    public static <K, V> HashMap<K, V> newHashMap() {
        return new HashMap();
    }

    public static <K, V> LinkedHashMap<K, V> newLinkedHashMap() {
        return new LinkedHashMap();
    }
}
