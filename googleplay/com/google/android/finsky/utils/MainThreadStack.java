package com.google.android.finsky.utils;

import java.util.Stack;

public class MainThreadStack<T> extends Stack<T> {
    public T pop() {
        Utils.ensureOnMainThread();
        return super.pop();
    }

    public T push(T data) {
        Utils.ensureOnMainThread();
        return super.push(data);
    }

    public T peek() {
        Utils.ensureOnMainThread();
        return super.peek();
    }

    public boolean isEmpty() {
        Utils.ensureOnMainThread();
        return super.isEmpty();
    }
}
