package com.google.android.finsky.library;

public class MagazinesLibrary extends HashMapLibrary {
    public MagazinesLibrary(LibraryHasher hasher) {
        super(6, hasher);
    }

    public LibrarySubscriptionEntry getSubscriptionEntry(String docId) {
        return (LibrarySubscriptionEntry) get(new LibraryEntry(null, AccountLibrary.LIBRARY_ID_MAGAZINE, 6, docId, 15, 1));
    }
}
