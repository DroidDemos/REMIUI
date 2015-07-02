package android.support.v4.a;

import java.util.Iterator;
import java.util.Map.Entry;

/* compiled from: MapCollections */
final class g implements Iterator, Entry {
    final /* synthetic */ c fd;
    int mEnd;
    boolean mEntryValid;
    int mIndex;

    g(c cVar) {
        this.fd = cVar;
        this.mEntryValid = false;
        this.mEnd = cVar.colGetSize() - 1;
        this.mIndex = -1;
    }

    public boolean hasNext() {
        return this.mIndex < this.mEnd;
    }

    public Entry next() {
        this.mIndex++;
        this.mEntryValid = true;
        return this;
    }

    public void remove() {
        if (this.mEntryValid) {
            this.fd.colRemoveAt(this.mIndex);
            this.mIndex--;
            this.mEnd--;
            this.mEntryValid = false;
            return;
        }
        throw new IllegalStateException();
    }

    public Object getKey() {
        if (this.mEntryValid) {
            return this.fd.colGetEntry(this.mIndex, 0);
        }
        throw new IllegalStateException("This container does not support retaining Map.Entry objects");
    }

    public Object getValue() {
        if (this.mEntryValid) {
            return this.fd.colGetEntry(this.mIndex, 1);
        }
        throw new IllegalStateException("This container does not support retaining Map.Entry objects");
    }

    public Object setValue(Object obj) {
        if (this.mEntryValid) {
            return this.fd.colSetValue(this.mIndex, obj);
        }
        throw new IllegalStateException("This container does not support retaining Map.Entry objects");
    }

    public final boolean equals(Object obj) {
        boolean z = true;
        if (!this.mEntryValid) {
            throw new IllegalStateException("This container does not support retaining Map.Entry objects");
        } else if (!(obj instanceof Entry)) {
            return false;
        } else {
            Entry entry = (Entry) obj;
            if (!(h.equal(entry.getKey(), this.fd.colGetEntry(this.mIndex, 0)) && h.equal(entry.getValue(), this.fd.colGetEntry(this.mIndex, 1)))) {
                z = false;
            }
            return z;
        }
    }

    public final int hashCode() {
        int i = 0;
        if (this.mEntryValid) {
            Object colGetEntry = this.fd.colGetEntry(this.mIndex, 0);
            Object colGetEntry2 = this.fd.colGetEntry(this.mIndex, 1);
            int hashCode = colGetEntry == null ? 0 : colGetEntry.hashCode();
            if (colGetEntry2 != null) {
                i = colGetEntry2.hashCode();
            }
            return i ^ hashCode;
        }
        throw new IllegalStateException("This container does not support retaining Map.Entry objects");
    }

    public final String toString() {
        return getKey() + "=" + getValue();
    }
}
