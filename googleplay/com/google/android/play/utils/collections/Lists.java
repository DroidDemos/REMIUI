package com.google.android.play.utils.collections;

import java.util.ArrayList;

public class Lists {
    public static <T> ArrayList<T> newArrayList() {
        return new ArrayList();
    }

    public static <T> ArrayList<T> newArrayList(int capacity) {
        return new ArrayList(capacity);
    }

    public static <T> ArrayList<T> newArrayList(T... items) {
        ArrayList<T> list = new ArrayList(items.length);
        for (T item : items) {
            list.add(item);
        }
        return list;
    }
}
