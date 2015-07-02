package android.support.v4.a;

import java.util.Collection;
import java.util.Iterator;

/* compiled from: MapCollections */
final class l implements Collection {
    final /* synthetic */ c fd;

    l(c cVar) {
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
        return this.fd.colIndexOfValue(obj) >= 0;
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
        return new j(this.fd, 1);
    }

    public boolean remove(Object obj) {
        int colIndexOfValue = this.fd.colIndexOfValue(obj);
        if (colIndexOfValue < 0) {
            return false;
        }
        this.fd.colRemoveAt(colIndexOfValue);
        return true;
    }

    public boolean removeAll(Collection collection) {
        int i = 0;
        int colGetSize = this.fd.colGetSize();
        boolean z = false;
        while (i < colGetSize) {
            if (collection.contains(this.fd.colGetEntry(i, 1))) {
                this.fd.colRemoveAt(i);
                i--;
                colGetSize--;
                z = true;
            }
            i++;
        }
        return z;
    }

    public boolean retainAll(Collection collection) {
        int i = 0;
        int colGetSize = this.fd.colGetSize();
        boolean z = false;
        while (i < colGetSize) {
            if (!collection.contains(this.fd.colGetEntry(i, 1))) {
                this.fd.colRemoveAt(i);
                i--;
                colGetSize--;
                z = true;
            }
            i++;
        }
        return z;
    }

    public int size() {
        return this.fd.colGetSize();
    }

    public Object[] toArray() {
        return this.fd.toArrayHelper(1);
    }

    public Object[] toArray(Object[] objArr) {
        return this.fd.toArrayHelper(objArr, 1);
    }
}
