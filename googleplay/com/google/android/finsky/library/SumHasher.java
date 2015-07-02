package com.google.android.finsky.library;

public class SumHasher implements LibraryHasher {
    private long mHash;

    public void add(long documentHash) {
        this.mHash += documentHash;
    }

    public void remove(long documentHash) {
        this.mHash -= documentHash;
    }

    public long compute() {
        return this.mHash;
    }

    public void reset() {
        this.mHash = 0;
    }
}
