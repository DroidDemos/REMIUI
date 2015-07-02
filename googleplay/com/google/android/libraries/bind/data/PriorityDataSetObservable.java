package com.google.android.libraries.bind.data;

import android.database.DataSetObserver;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class PriorityDataSetObservable {
    private final List<ObserverEntry> observerEntries;

    private static class ObserverEntry implements Comparable<ObserverEntry> {
        public final DataSetObserver observer;
        public final int priority;

        public ObserverEntry(DataSetObserver observer, int priority) {
            this.observer = observer;
            this.priority = priority;
        }

        public int compareTo(ObserverEntry other) {
            if (this.priority > other.priority) {
                return 1;
            }
            return this.priority < other.priority ? -1 : 0;
        }
    }

    public boolean add(DataSetObserver observer, int priority) {
        if (observer == null) {
            throw new IllegalArgumentException("Observer is null");
        }
        boolean wasEmpty = this.observerEntries.isEmpty();
        this.observerEntries.add(new ObserverEntry(observer, priority));
        Collections.sort(this.observerEntries);
        return wasEmpty;
    }

    public boolean remove(DataSetObserver observer) {
        if (observer == null) {
            throw new IllegalArgumentException("Observer is null");
        } else if (this.observerEntries.isEmpty()) {
            return false;
        } else {
            for (int i = 0; i < this.observerEntries.size(); i++) {
                if (((ObserverEntry) this.observerEntries.get(i)).observer == observer) {
                    this.observerEntries.remove(i);
                    break;
                }
            }
            return this.observerEntries.isEmpty();
        }
    }

    public int size() {
        return this.observerEntries.size();
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format(Locale.US, "  observers count: %d\n", new Object[]{Integer.valueOf(size())}));
        for (ObserverEntry entry : this.observerEntries) {
            builder.append("    ");
            builder.append(entry.toString());
            builder.append("\n");
        }
        return builder.toString();
    }
}
