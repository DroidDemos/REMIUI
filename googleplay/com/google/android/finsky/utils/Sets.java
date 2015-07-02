package com.google.android.finsky.utils;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Sets {
    public static <T> HashSet<T> newHashSet() {
        return new HashSet();
    }

    public static <T> Set<T> newHashSet(T... items) {
        HashSet<T> list = new HashSet(items.length);
        for (T item : items) {
            list.add(item);
        }
        return list;
    }

    public static <T> HashSet<T> newHashSet(Collection<T> items) {
        return new HashSet(items);
    }
}
