package android.support.v4.a;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

/* compiled from: MapCollections */
final class d implements Set {
    final /* synthetic */ c fd;

    d(c cVar) {
        this.fd = cVar;
    }

    public boolean add(Object obj) {
        throw new UnsupportedOperationException();
    }

    public boolean addAll(Collection collection) {
        throw new UnsupportedOperationException();
    }

    public void clear() {
        this.fd.colClear();
    }

    public boolean contains(Object obj) {
        return this.fd.colIndexOfKey(obj) >= 0;
    }

    public boolean containsAll(Collection collection) {
        return c.containsAllHelper(this.fd.colGetMap(), collection);
    }

    public boolean isEmpty() {
        return this.fd.colGetSize() == 0;
    }

    public Iterator iterator() {
        return new j(this.fd, 0);
    }

    public boolean remove(Object obj) {
        int colIndexOfKey = this.fd.colIndexOfKey(obj);
        if (colIndexOfKey < 0) {
            return false;
        }
        this.fd.colRemoveAt(colIndexOfKey);
        return true;
    }

    public boolean removeAll(Collection collection) {
        return c.removeAllHelper(this.fd.colGetMap(), collection);
    }

    public boolean retainAll(Collection collection) {
        return c.retainAllHelper(this.fd.colGetMap(), collection);
    }

    public int size() {
        return this.fd.colGetSize();
    }

    public Object[] toArray() {
        return this.fd.toArrayHelper(0);
    }

    public Object[] toArray(Object[] objArr) {
        return this.fd.toArrayHelper(objArr, 0);
    }

    public boolean equals(Object obj) {
        return c.equalsSetHelper(this, obj);
    }

    public int hashCode() {
        int i = 0;
        for (int colGetSize = this.fd.colGetSize() - 1; colGetSize >= 0; colGetSize--) {
            Object colGetEntry = this.fd.colGetEntry(colGetSize, 0);
            i += colGetEntry == null ? 0 : colGetEntry.hashCode();
        }
        return i;
    }
}
