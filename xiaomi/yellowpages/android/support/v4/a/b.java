package android.support.v4.a;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

/* compiled from: MapCollections */
final class b implements Set {
    final /* synthetic */ c fd;

    b(c cVar) {
        this.fd = cVar;
    }

    public boolean add(Entry entry) {
        throw new UnsupportedOperationException();
    }

    public boolean addAll(Collection collection) {
        int colGetSize = this.fd.colGetSize();
        for (Entry entry : collection) {
            this.fd.colPut(entry.getKey(), entry.getValue());
        }
        return colGetSize != this.fd.colGetSize();
    }

    public void clear() {
        this.fd.colClear();
    }

    public boolean contains(Object obj) {
        if (!(obj instanceof Entry)) {
            return false;
        }
        Entry entry = (Entry) obj;
        int colIndexOfKey = this.fd.colIndexOfKey(entry.getKey());
        if (colIndexOfKey >= 0) {
            return h.equal(this.fd.colGetEntry(colIndexOfKey, 1), entry.getValue());
        }
        return false;
    }

    public boolean containsAll(Collection collection) {
        for (Object contains : collection) {
            if (!contains(contains)) {
                return false;
            }
        }
        return true;
    }

    public boolean isEmpty() {
        return this.fd.colGetSize() == 0;
    }

    public Iterator iterator() {
        return new g(this.fd);
    }

    public boolean remove(Object obj) {
        throw new UnsupportedOperationException();
    }

    public boolean removeAll(Collection collection) {
        throw new UnsupportedOperationException();
    }

    public boolean retainAll(Collection collection) {
        throw new UnsupportedOperationException();
    }

    public int size() {
        return this.fd.colGetSize();
    }

    public Object[] toArray() {
        throw new UnsupportedOperationException();
    }

    public Object[] toArray(Object[] objArr) {
        throw new UnsupportedOperationException();
    }

    public boolean equals(Object obj) {
        return c.equalsSetHelper(this, obj);
    }

    public int hashCode() {
        int colGetSize = this.fd.colGetSize() - 1;
        int i = 0;
        while (colGetSize >= 0) {
            Object colGetEntry = this.fd.colGetEntry(colGetSize, 0);
            Object colGetEntry2 = this.fd.colGetEntry(colGetSize, 1);
            colGetSize--;
            i += (colGetEntry2 == null ? 0 : colGetEntry2.hashCode()) ^ (colGetEntry == null ? 0 : colGetEntry.hashCode());
        }
        return i;
    }
}
