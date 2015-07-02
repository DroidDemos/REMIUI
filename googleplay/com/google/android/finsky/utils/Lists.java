package com.google.android.finsky.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

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

    public static <T> ArrayList<T> newArrayList(Collection<T> items) {
        return new ArrayList(items);
    }

    public static <T> LinkedList<T> newLinkedList() {
        return new LinkedList();
    }
}
