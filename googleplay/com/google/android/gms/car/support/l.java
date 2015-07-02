package com.google.android.gms.car.support;

import java.util.Map;

public class l<K, V> {
    static Object[] mBaseCache;
    static int mBaseCacheSize;
    static Object[] mTwiceBaseCache;
    static int mTwiceBaseCacheSize;
    Object[] mArray;
    int[] mHashes;
    int mSize;

    public l() {
        this.mHashes = d.EMPTY_INTS;
        this.mArray = d.EMPTY_OBJECTS;
        this.mSize = 0;
    }

    private static void a(int[] iArr, Object[] objArr, int i) {
        int i2;
        if (iArr.length == 8) {
            synchronized (a.class) {
                if (mTwiceBaseCacheSize < 10) {
                    objArr[0] = mTwiceBaseCache;
                    objArr[1] = iArr;
                    for (i2 = (i << 1) - 1; i2 >= 2; i2--) {
                        objArr[i2] = null;
                    }
                    mTwiceBaseCache = objArr;
                    mTwiceBaseCacheSize++;
                }
            }
        } else if (iArr.length == 4) {
            synchronized (a.class) {
                if (mBaseCacheSize < 10) {
                    objArr[0] = mBaseCache;
                    objArr[1] = iArr;
                    for (i2 = (i << 1) - 1; i2 >= 2; i2--) {
                        objArr[i2] = null;
                    }
                    mBaseCache = objArr;
                    mBaseCacheSize++;
                }
            }
        }
    }

    private void cy(int i) {
        Object[] objArr;
        if (i == 8) {
            synchronized (a.class) {
                if (mTwiceBaseCache != null) {
                    objArr = mTwiceBaseCache;
                    this.mArray = objArr;
                    mTwiceBaseCache = (Object[]) objArr[0];
                    this.mHashes = (int[]) objArr[1];
                    objArr[1] = null;
                    objArr[0] = null;
                    mTwiceBaseCacheSize--;
                    return;
                }
            }
        } else if (i == 4) {
            synchronized (a.class) {
                if (mBaseCache != null) {
                    objArr = mBaseCache;
                    this.mArray = objArr;
                    mBaseCache = (Object[]) objArr[0];
                    this.mHashes = (int[]) objArr[1];
                    objArr[1] = null;
                    objArr[0] = null;
                    mBaseCacheSize--;
                    return;
                }
            }
        }
        this.mHashes = new int[i];
        this.mArray = new Object[(i << 1)];
    }

    public void clear() {
        if (this.mSize != 0) {
            a(this.mHashes, this.mArray, this.mSize);
            this.mHashes = d.EMPTY_INTS;
            this.mArray = d.EMPTY_OBJECTS;
            this.mSize = 0;
        }
    }

    public boolean containsKey(Object key) {
        return key == null ? indexOfNull() >= 0 : indexOf(key, key.hashCode()) >= 0;
    }

    public boolean containsValue(Object value) {
        return indexOfValue(value) >= 0;
    }

    public void ensureCapacity(int minimumCapacity) {
        if (this.mHashes.length < minimumCapacity) {
            Object obj = this.mHashes;
            Object obj2 = this.mArray;
            cy(minimumCapacity);
            if (this.mSize > 0) {
                System.arraycopy(obj, 0, this.mHashes, 0, this.mSize);
                System.arraycopy(obj2, 0, this.mArray, 0, this.mSize << 1);
            }
            a(obj, obj2, this.mSize);
        }
    }

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof Map)) {
            return false;
        }
        Map map = (Map) object;
        if (size() != map.size()) {
            return false;
        }
        int i = 0;
        while (i < this.mSize) {
            try {
                Object keyAt = keyAt(i);
                Object valueAt = valueAt(i);
                Object obj = map.get(keyAt);
                if (valueAt == null) {
                    if (obj != null || !map.containsKey(keyAt)) {
                        return false;
                    }
                } else if (!valueAt.equals(obj)) {
                    return false;
                }
                i++;
            } catch (NullPointerException e) {
                return false;
            } catch (ClassCastException e2) {
                return false;
            }
        }
        return true;
    }

    public V get(Object key) {
        int indexOfNull = key == null ? indexOfNull() : indexOf(key, key.hashCode());
        return indexOfNull >= 0 ? this.mArray[(indexOfNull << 1) + 1] : null;
    }

    public int hashCode() {
        int[] iArr = this.mHashes;
        Object[] objArr = this.mArray;
        int i = this.mSize;
        int i2 = 1;
        int i3 = 0;
        int i4 = 0;
        while (i3 < i) {
            Object obj = objArr[i2];
            i4 += (obj == null ? 0 : obj.hashCode()) ^ iArr[i3];
            i3++;
            i2 += 2;
        }
        return i4;
    }

    int indexOf(Object key, int hash) {
        int i = this.mSize;
        if (i == 0) {
            return -1;
        }
        int binarySearch = d.binarySearch(this.mHashes, i, hash);
        if (binarySearch < 0 || key.equals(this.mArray[binarySearch << 1])) {
            return binarySearch;
        }
        int i2 = binarySearch + 1;
        while (i2 < i && this.mHashes[i2] == hash) {
            if (key.equals(this.mArray[i2 << 1])) {
                return i2;
            }
            i2++;
        }
        binarySearch--;
        while (binarySearch >= 0 && this.mHashes[binarySearch] == hash) {
            if (key.equals(this.mArray[binarySearch << 1])) {
                return binarySearch;
            }
            binarySearch--;
        }
        return i2 ^ -1;
    }

    int indexOfNull() {
        int i = this.mSize;
        if (i == 0) {
            return -1;
        }
        int binarySearch = d.binarySearch(this.mHashes, i, 0);
        if (binarySearch < 0 || this.mArray[binarySearch << 1] == null) {
            return binarySearch;
        }
        int i2 = binarySearch + 1;
        while (i2 < i && this.mHashes[i2] == 0) {
            if (this.mArray[i2 << 1] == null) {
                return i2;
            }
            i2++;
        }
        binarySearch--;
        while (binarySearch >= 0 && this.mHashes[binarySearch] == 0) {
            if (this.mArray[binarySearch << 1] == null) {
                return binarySearch;
            }
            binarySearch--;
        }
        return i2 ^ -1;
    }

    int indexOfValue(Object value) {
        int i = 1;
        int i2 = this.mSize * 2;
        Object[] objArr = this.mArray;
        if (value == null) {
            while (i < i2) {
                if (objArr[i] == null) {
                    return i >> 1;
                }
                i += 2;
            }
        } else {
            while (i < i2) {
                if (value.equals(objArr[i])) {
                    return i >> 1;
                }
                i += 2;
            }
        }
        return -1;
    }

    public boolean isEmpty() {
        return this.mSize <= 0;
    }

    public K keyAt(int index) {
        return this.mArray[index << 1];
    }

    public V put(K key, V value) {
        int indexOfNull;
        int i;
        int i2 = 8;
        if (key == null) {
            indexOfNull = indexOfNull();
            i = 0;
        } else {
            i = key.hashCode();
            indexOfNull = indexOf(key, i);
        }
        if (indexOfNull >= 0) {
            int i3 = (indexOfNull << 1) + 1;
            V v = this.mArray[i3];
            this.mArray[i3] = value;
            return v;
        }
        indexOfNull ^= -1;
        if (this.mSize >= this.mHashes.length) {
            if (this.mSize >= 8) {
                i2 = this.mSize + (this.mSize >> 1);
            } else if (this.mSize < 4) {
                i2 = 4;
            }
            Object obj = this.mHashes;
            Object obj2 = this.mArray;
            cy(i2);
            if (this.mHashes.length > 0) {
                System.arraycopy(obj, 0, this.mHashes, 0, obj.length);
                System.arraycopy(obj2, 0, this.mArray, 0, obj2.length);
            }
            a(obj, obj2, this.mSize);
        }
        if (indexOfNull < this.mSize) {
            System.arraycopy(this.mHashes, indexOfNull, this.mHashes, indexOfNull + 1, this.mSize - indexOfNull);
            System.arraycopy(this.mArray, indexOfNull << 1, this.mArray, (indexOfNull + 1) << 1, (this.mSize - indexOfNull) << 1);
        }
        this.mHashes[indexOfNull] = i;
        this.mArray[indexOfNull << 1] = key;
        this.mArray[(indexOfNull << 1) + 1] = value;
        this.mSize++;
        return null;
    }

    public V remove(Object key) {
        int indexOfNull = key == null ? indexOfNull() : indexOf(key, key.hashCode());
        return indexOfNull >= 0 ? removeAt(indexOfNull) : null;
    }

    public V removeAt(int index) {
        int i = 8;
        V v = this.mArray[(index << 1) + 1];
        if (this.mSize <= 1) {
            a(this.mHashes, this.mArray, this.mSize);
            this.mHashes = d.EMPTY_INTS;
            this.mArray = d.EMPTY_OBJECTS;
            this.mSize = 0;
        } else if (this.mHashes.length <= 8 || this.mSize >= this.mHashes.length / 3) {
            this.mSize--;
            if (index < this.mSize) {
                System.arraycopy(this.mHashes, index + 1, this.mHashes, index, this.mSize - index);
                System.arraycopy(this.mArray, (index + 1) << 1, this.mArray, index << 1, (this.mSize - index) << 1);
            }
            this.mArray[this.mSize << 1] = null;
            this.mArray[(this.mSize << 1) + 1] = null;
        } else {
            if (this.mSize > 8) {
                i = this.mSize + (this.mSize >> 1);
            }
            Object obj = this.mHashes;
            Object obj2 = this.mArray;
            cy(i);
            this.mSize--;
            if (index > 0) {
                System.arraycopy(obj, 0, this.mHashes, 0, index);
                System.arraycopy(obj2, 0, this.mArray, 0, index << 1);
            }
            if (index < this.mSize) {
                System.arraycopy(obj, index + 1, this.mHashes, index, this.mSize - index);
                System.arraycopy(obj2, (index + 1) << 1, this.mArray, index << 1, (this.mSize - index) << 1);
            }
        }
        return v;
    }

    public V setValueAt(int index, V value) {
        index = (index << 1) + 1;
        V v = this.mArray[index];
        this.mArray[index] = value;
        return v;
    }

    public int size() {
        return this.mSize;
    }

    public String toString() {
        if (isEmpty()) {
            return "{}";
        }
        StringBuilder stringBuilder = new StringBuilder(this.mSize * 28);
        stringBuilder.append('{');
        for (int i = 0; i < this.mSize; i++) {
            if (i > 0) {
                stringBuilder.append(", ");
            }
            l keyAt = keyAt(i);
            if (keyAt != this) {
                stringBuilder.append(keyAt);
            } else {
                stringBuilder.append("(this Map)");
            }
            stringBuilder.append('=');
            keyAt = valueAt(i);
            if (keyAt != this) {
                stringBuilder.append(keyAt);
            } else {
                stringBuilder.append("(this Map)");
            }
        }
        stringBuilder.append('}');
        return stringBuilder.toString();
    }

    public V valueAt(int index) {
        return this.mArray[(index << 1) + 1];
    }
}
