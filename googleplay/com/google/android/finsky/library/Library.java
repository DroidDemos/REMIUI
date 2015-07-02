package com.google.android.finsky.library;

public interface Library extends Iterable<LibraryEntry> {
    boolean contains(LibraryEntry libraryEntry);

    LibraryEntry get(LibraryEntry libraryEntry);

    void remove(LibraryEntry libraryEntry);

    int size();
}
