package com.google.android.finsky.library;

public abstract class HashingLibrary implements Library {
    final LibraryHasher mHasher;

    public abstract void dumpState(String str, String str2);

    public HashingLibrary(LibraryHasher hasher) {
        this.mHasher = hasher;
    }

    public void add(LibraryEntry entry) {
        if (!contains(entry)) {
            this.mHasher.add(entry.getDocumentHash());
        }
    }

    public void remove(LibraryEntry entry) {
        LibraryEntry existingEntry = get(entry);
        if (existingEntry != null) {
            this.mHasher.remove(existingEntry.getDocumentHash());
        }
    }

    public long getHash() {
        return this.mHasher.compute();
    }

    public void reset() {
        this.mHasher.reset();
    }
}
