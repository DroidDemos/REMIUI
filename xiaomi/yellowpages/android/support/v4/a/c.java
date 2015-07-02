package android.support.v4.a;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* compiled from: MapCollections */
abstract class c {
    b iw;
    d ix;
    l iy;

    protected abstract void colClear();

    protected abstract Object colGetEntry(int i, int i2);

    protected abstract Map colGetMap();

    protected abstract int colGetSize();

    protected abstract int colIndexOfKey(Object obj);

    protected abstract int colIndexOfValue(Object obj);

    protected abstract void colPut(Object obj, Object obj2);

    protected abstract void colRemoveAt(int i);

    protected abstract Object colSetValue(int i, Object obj);

    c() {
    }

    public static boolean containsAllHelper(Map map, Collection collection) {
        for (Object containsKey : collection) {
            if (!map.containsKey(containsKey)) {
                return false;
            }
        }
        return true;
    }

    public static boolean removeAllHelper(Map map, Collection collection) {
        int size = map.size();
        for (Object remove : collection) {
            map.remove(remove);
        }
        return size != map.size();
    }

    public static boolean retainAllHelper(Map map, Collection collection) {
        int size = map.size();
        Iterator it = map.keySet().iterator();
        while (it.hasNext()) {
            if (!collection.contains(it.next())) {
                it.remove();
            }
        }
        return size != map.size();
    }

    public Object[] toArrayHelper(int i) {
        int colGetSize = colGetSize();
        Object[] objArr = new Object[colGetSize];
        for (int i2 = 0; i2 < colGetSize; i2++) {
            objArr[i2] = colGetEntry(i2, i);
        }
        return objArr;
    }

    public Object[] toArrayHelper(Object[] objArr, int i) {
        Object[] objArr2;
        int colGetSize = colGetSize();
        if (objArr.length < colGetSize) {
            objArr2 = (Object[]) Array.newInstance(objArr.getClass().getComponentType(), colGetSize);
        } else {
            objArr2 = objArr;
        }
        for (int i2 = 0; i2 < colGetSize; i2++) {
            objArr2[i2] = colGetEntry(i2, i);
        }
        if (objArr2.length > colGetSize) {
            objArr2[colGetSize] = null;
        }
        return objArr2;
    }

    public static boolean equalsSetHelper(Set set, Object obj) {
        boolean z = true;
        if (set == obj) {
            return true;
        }
        if (!(obj instanceof Set)) {
            return false;
        }
        Set set2 = (Set) obj;
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

    public Set getEntrySet() {
        if (this.iw == null) {
            this.iw = new b(this);
        }
        return this.iw;
    }

    public Set getKeySet() {
        if (this.ix == null) {
            this.ix = new d(this);
        }
        return this.ix;
    }

    public Collection getValues() {
        if (this.iy == null) {
            this.iy = new l(this);
        }
        return this.iy;
    }
}
