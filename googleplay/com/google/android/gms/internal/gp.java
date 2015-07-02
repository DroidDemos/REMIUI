package com.google.android.gms.internal;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

@fd
public class gp<T> implements Future<T> {
    private final T wE;

    public gp(T t) {
        this.wE = t;
    }

    public boolean cancel(boolean mayInterruptIfRunning) {
        return false;
    }

    public T get() {
        return this.wE;
    }

    public T get(long timeout, TimeUnit unit) {
        return this.wE;
    }

    public boolean isCancelled() {
        return false;
    }

    public boolean isDone() {
        return true;
    }
}
