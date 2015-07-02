package com.google.android.gms.car.support;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

abstract class j<K, V> {
    com.google.android.gms.car.support.j$com.google.android.gms.car.support.j.b Nw;
    com.google.android.gms.car.support.j$com.google.android.gms.car.support.j.c Nx;
    com.google.android.gms.car.support.j$com.google.android.gms.car.support.j.e Ny;

    final class a<T> implements Iterator<T> {
        final /* synthetic */ j Nz;
        boolean mCanRemove;
        int mIndex;
        final int mOffset;
        int mSize;

        a(j jVar, int i) {
            this.Nz = jVar;
            this.mCanRemove = false;
            this.mOffset = i;
            this.mSize = jVar.colGetSize();
        }

        public boolean hasNext() {
            return this.mIndex < this.mSize;
        }

        public T next() {
            T colGetEntry = this.Nz.colGetEntry(this.mIndex, this.mOffset);
            this.mIndex++;
            this.mCanRemove = true;
            return colGetEntry;
        }

        public void remove() {
            if (this.mCanRemove) {
                this.mIndex--;
                this.mSize--;
                this.mCanRemove = false;
                this.Nz.colRemoveAt(this.mIndex);
                return;
            }
            throw new IllegalStateException();
        }
    }

    final class b implements Set<Entry<K, V>> {
        final /* synthetic */ j Nz;

        b(j jVar) {
            this.Nz = jVar;
        }

        public boolean add(Entry<K, V> entry) {
            throw new UnsupportedOperationException();
        }

        public boolean addAll(Collection<? extends Entry<K, V>> collection) {
            int colGetSize = this.Nz.colGetSize();
            for (Entry entry : collection) {
                this.Nz.colPut(entry.getKey(), entry.getValue());
            }
            return colGetSize != this.Nz.colGetSize();
        }

        public void clear() {
            this.Nz.colClear();
        }

        public boolean contains(Object o) {
            if (!(o instanceof Entry)) {
                return false;
            }
            Entry entry = (Entry) o;
            int colIndexOfKey = this.Nz.colIndexOfKey(entry.getKey());
            return colIndexOfKey >= 0 ? d.equal(this.Nz.colGetEntry(colIndexOfKey, 1), entry.getValue()) : false;
        }

        public boolean containsAll(Collection<?> collection) {
            for (Object contains : collection) {
                if (!contains(contains)) {
                    return false;
                }
            }
            return true;
        }

        public boolean equals(Object object) {
            return j.equalsSetHelper(this, object);
        }

        public int hashCode() {
            int colGetSize = this.Nz.colGetSize() - 1;
            int i = 0;
            while (colGetSize >= 0) {
                Object colGetEntry = this.Nz.colGetEntry(colGetSize, 0);
                Object colGetEntry2 = this.Nz.colGetEntry(colGetSize, 1);
                colGetSize--;
                i += (colGetEntry2 == null ? 0 : colGetEntry2.hashCode()) ^ (colGetEntry == null ? 0 : colGetEntry.hashCode());
            }
            return i;
        }

        public boolean isEmpty() {
            return this.Nz.colGetSize() == 0;
        }

        public Iterator<Entry<K, V>> iterator() {
            return new d(this.Nz);
        }

        public boolean remove(Object object) {
            throw new UnsupportedOperationException();
        }

        public boolean removeAll(Collection<?> collection) {
            throw new UnsupportedOperationException();
        }

        public boolean retainAll(Collection<?> collection) {
            throw new UnsupportedOperationException();
        }

        public int size() {
            return this.Nz.colGetSize();
        }

        public Object[] toArray() {
            throw new UnsupportedOperationException();
        }

        public <T> T[] toArray(T[] tArr) {
            throw new UnsupportedOperationException();
        }
    }

    final class c implements Set<K> {
        final /* synthetic */ j Nz;

        c(j jVar) {
            this.Nz = jVar;
        }

        public boolean add(K k) {
            throw new UnsupportedOperationException();
        }

        public boolean addAll(Collection<? extends K> collection) {
            throw new UnsupportedOperationException();
        }

        public void clear() {
            this.Nz.colClear();
        }

        public boolean contains(Object object) {
            return this.Nz.colIndexOfKey(object) >= 0;
        }

        public boolean containsAll(Collection<?> collection) {
            return j.containsAllHelper(this.Nz.colGetMap(), collection);
        }

        public boolean equals(Object object) {
            return j.equalsSetHelper(this, object);
        }

        public int hashCode() {
            int i = 0;
            for (int colGetSize = this.Nz.colGetSize() - 1; colGetSize >= 0; colGetSize--) {
                Object colGetEntry = this.Nz.colGetEntry(colGetSize, 0);
                i += colGetEntry == null ? 0 : colGetEntry.hashCode();
            }
            return i;
        }

        public boolean isEmpty() {
            return this.Nz.colGetSize() == 0;
        }

        public Iterator<K> iterator() {
            return new a(this.Nz, 0);
        }

        public boolean remove(Object object) {
            int colIndexOfKey = this.Nz.colIndexOfKey(object);
            if (colIndexOfKey < 0) {
                return false;
            }
            this.Nz.colRemoveAt(colIndexOfKey);
            return true;
        }

        public boolean removeAll(Collection<?> collection) {
            return j.removeAllHelper(this.Nz.colGetMap(), collection);
        }

        public boolean retainAll(Collection<?> collection) {
            return j.retainAllHelper(this.Nz.colGetMap(), collection);
        }

        public int size() {
            return this.Nz.colGetSize();
        }

        public Object[] toArray() {
            return this.Nz.toArrayHelper(0);
        }

        public <T> T[] toArray(T[] array) {
            return this.Nz.toArrayHelper(array, 0);
        }
    }

    final class d implements Iterator<Entry<K, V>>, Entry<K, V> {
        final /* synthetic */ j Nz;
        int mEnd;
        boolean mEntryValid;
        int mIndex;

        d(j jVar) {
            this.Nz = jVar;
            this.mEntryValid = false;
            this.mEnd = jVar.colGetSize() - 1;
            this.mIndex = -1;
        }

        public final boolean equals(Object o) {
            boolean z = true;
            if (!this.mEntryValid) {
                throw new IllegalStateException("This container does not support retaining Map.Entry objects");
            } else if (!(o instanceof Entry)) {
                return false;
            } else {
                Entry entry = (Entry) o;
                if (!(d.equal(entry.getKey(), this.Nz.colGetEntry(this.mIndex, 0)) && d.equal(entry.getValue(), this.Nz.colGetEntry(this.mIndex, 1)))) {
                    z = false;
                }
                return z;
            }
        }

        public K getKey() {
            if (this.mEntryValid) {
                return this.Nz.colGetEntry(this.mIndex, 0);
            }
            throw new IllegalStateException("This container does not support retaining Map.Entry objects");
        }

        public V getValue() {
            if (this.mEntryValid) {
                return this.Nz.colGetEntry(this.mIndex, 1);
            }
            throw new IllegalStateException("This container does not support retaining Map.Entry objects");
        }

        public boolean hasNext() {
            return this.mIndex < this.mEnd;
        }

        public final int hashCode() {
            int i = 0;
            if (this.mEntryValid) {
                Object colGetEntry = this.Nz.colGetEntry(this.mIndex, 0);
                Object colGetEntry2 = this.Nz.colGetEntry(this.mIndex, 1);
                int hashCode = colGetEntry == null ? 0 : colGetEntry.hashCode();
                if (colGetEntry2 != null) {
                    i = colGetEntry2.hashCode();
                }
                return i ^ hashCode;
            }
            throw new IllegalStateException("This container does not support retaining Map.Entry objects");
        }

        public Entry<K, V> next() {
            this.mIndex++;
            this.mEntryValid = true;
            return this;
        }

        public void remove() {
            if (this.mEntryValid) {
                this.Nz.colRemoveAt(this.mIndex);
                this.mIndex--;
                this.mEnd--;
                this.mEntryValid = false;
                return;
            }
            throw new IllegalStateException();
        }

        public V setValue(V object) {
            if (this.mEntryValid) {
                return this.Nz.colSetValue(this.mIndex, object);
            }
            throw new IllegalStateException("This container does not support retaining Map.Entry objects");
        }

        public final String toString() {
            return getKey() + "=" + getValue();
        }
    }

    final class e implements Collection<V> {
        final /* synthetic */ j Nz;

        e(j jVar) {
            this.Nz = jVar;
        }

        public boolean add(V v) {
            throw new UnsupportedOperationException();
        }

        public boolean addAll(Collection<? extends V> collection) {
            throw new UnsupportedOperationException();
        }

        public void clear() {
            this.Nz.colClear();
        }

        public boolean contains(Object object) {
            return this.Nz.colIndexOfValue(object) >= 0;
        }

        public boolean containsAll(Collection<?> collection) {
            for (Object contains : collection) {
                if (!contains(contains)) {
                    return false;
                }
            }
            return true;
        }

        public boolean isEmpty() {
            return this.Nz.colGetSize() == 0;
        }

        public Iterator<V> iterator() {
            return new a(this.Nz, 1);
        }

        public boolean remove(Object object) {
            int colIndexOfValue = this.Nz.colIndexOfValue(object);
            if (colIndexOfValue < 0) {
                return false;
            }
            this.Nz.colRemoveAt(colIndexOfValue);
            return true;
        }

        public boolean removeAll(Collection<?> collection) {
            int i = 0;
            int colGetSize = this.Nz.colGetSize();
            boolean z = false;
            while (i < colGetSize) {
                if (collection.contains(this.Nz.colGetEntry(i, 1))) {
                    this.Nz.colRemoveAt(i);
                    i--;
                    colGetSize--;
                    z = true;
                }
                i++;
            }
            return z;
        }

        public boolean retainAll(Collection<?> collection) {
            int i = 0;
            int colGetSize = this.Nz.colGetSize();
            boolean z = false;
            while (i < colGetSize) {
                if (!collection.contains(this.Nz.colGetEntry(i, 1))) {
                    this.Nz.colRemoveAt(i);
                    i--;
                    colGetSize--;
                    z = true;
                }
                i++;
            }
            return z;
        }

        public int size() {
            return this.Nz.colGetSize();
        }

        public Object[] toArray() {
            return this.Nz.toArrayHelper(1);
        }

        public <T> T[] toArray(T[] array) {
            return this.Nz.toArrayHelper(array, 1);
        }
    }

    j() {
    }

    public static <K, V> boolean containsAllHelper(Map<K, V> map, Collection<?> collection) {
        for (Object containsKey : collection) {
            if (!map.containsKey(containsKey)) {
                return false;
            }
        }
        return true;
    }

    public static <T> boolean equalsSetHelper(Set<T> set, Object object) {
        boolean z = true;
        if (set == object) {
            return true;
        }
        if (!(object instanceof Set)) {
            return false;
        }
        Set set2 = (Set) object;
        try {
            if (!(set.size() == set2.size() && set.containsAll(set2))) {
                z = false;
            }
            return z;
        } catch (NullPointerException e) {
            return false;
        } catch (ClassCastException e2) {
            return false;
        }
    }

    public static <K, V> boolean removeAllHelper(Map<K, V> map, Collection<?> collection) {
        int size = map.size();
        for (Object remove : collection) {
            map.remove(remove);
        }
        return size != map.size();
    }

    public static <K, V> boolean retainAllHelper(Map<K, V> map, Collection<?> collection) {
        int size = map.size();
        Iterator it = map.keySet().iterator();
        while (it.hasNext()) {
            if (!collection.contains(it.next())) {
                it.remove();
            }
        }
        return size != map.size();
    }

    protected abstract void colClear();

    protected abstract Object colGetEntry(int i, int i2);

    protected abstract Map<K, V> colGetMap();

    protected abstract int colGetSize();

    protected abstract int colIndexOfKey(Object obj);

    protected abstract int colIndexOfValue(Object obj);

    protected abstract void colPut(K k, V v);

    protected abstract void colRemoveAt(int i);

    protected abstract V colSetValue(int i, V v);

    public Set<Entry<K, V>> getEntrySet() {
        if (this.Nw == null) {
            this.Nw = new b(this);
        }
        return this.Nw;
    }

    public Set<K> getKeySet() {
        if (this.Nx == null) {
            this.Nx = new c(this);
        }
        return this.Nx;
    }

    public Collection<V> getValues() {
        if (this.Ny == null) {
            this.Ny = new e(this);
        }
        return this.Ny;
    }

    public Object[] toArrayHelper(int offset) {
        int colGetSize = colGetSize();
        Object[] objArr = new Object[colGetSize];
        for (int i = 0; i < colGetSize; i++) {
            objArr[i] = colGetEntry(i, offset);
        }
        return objArr;
    }

    public <T> T[] toArrayHelper(T[] array, int offset) {
        int colGetSize = colGetSize();
        if (array.length < colGetSize) {
            array = (Object[]) ((Object[]) Array.newInstance(array.getClass().getComponentType(), colGetSize));
        }
        for (int i = 0; i < colGetSize; i++) {
            array[i] = colGetEntry(i, offset);
        }
        if (array.length > colGetSize) {
            array[colGetSize] = null;
        }
        return array;
    }
}
