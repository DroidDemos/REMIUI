package com.google.android.finsky.library;

public interface LibraryHasher {
    void add(long j);

    long compute();

    void remove(long j);

    void reset();
}
