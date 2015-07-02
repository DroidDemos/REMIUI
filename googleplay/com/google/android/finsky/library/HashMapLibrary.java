package com.google.android.finsky.library;

import android.util.Log;
import com.google.android.finsky.utils.Maps;
import java.util.Iterator;
import java.util.Map;

public class HashMapLibrary extends HashingLibrary {
    private final int mBackendId;
    private final Map<LibraryEntry, LibraryEntry> mEntries;

    public HashMapLibrary(int backendId, LibraryHasher hasher) {
        super(hasher);
        this.mEntries = Maps.newHashMap();
        this.mBackendId = backendId;
    }

    public synchronized boolean contains(LibraryEntry entry) {
        return this.mEntries.containsKey(entry);
    }

    public synchronized void add(LibraryEntry entry) {
        super.add(entry);
        this.mEntries.put(entry, entry);
    }

    public LibraryEntry get(LibraryEntry entry) {
        return (LibraryEntry) this.mEntries.get(entry);
    }

    public synchronized void remove(LibraryEntry entry) {
        super.remove(entry);
        this.mEntries.remove(entry);
    }

    public synchronized int size() {
        return this.mEntries.size();
    }

    public synchronized Iterator<LibraryEntry> iterator() {
        return this.mEntries.values().iterator();
    }

    public synchronized void reset() {
        super.reset();
        this.mEntries.clear();
    }

    public String toString() {
        return String.format("{backend=%d, num entries=%d}", new Object[]{Integer.valueOf(this.mBackendId), Integer.valueOf(size())});
    }

    public void dumpState(String label, String indent) {
        Log.d("FinskyLibrary", indent + "Library (" + label + ") {");
        Log.d("FinskyLibrary", indent + "  backend=" + this.mBackendId);
        Log.d("FinskyLibrary", indent + "  entryCount=" + this.mEntries.size());
        Log.d("FinskyLibrary", indent + "}");
    }
}
