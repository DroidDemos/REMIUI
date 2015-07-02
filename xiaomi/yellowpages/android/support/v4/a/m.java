package android.support.v4.a;

import java.util.Map;

/* compiled from: ArrayMap */
class m extends c {
    final /* synthetic */ e Md;

    m(e eVar) {
        this.Md = eVar;
    }

    protected int colGetSize() {
        return this.Md.mSize;
    }

    protected Object colGetEntry(int i, int i2) {
        return this.Md.mArray[(i << 1) + i2];
    }

    protected int colIndexOfKey(Object obj) {
        return obj == null ? this.Md.indexOfNull() : this.Md.indexOf(obj, obj.hashCode());
    }

    protected int colIndexOfValue(Object obj) {
        return this.Md.indexOfValue(obj);
    }

    protected Map colGetMap() {
        return this.Md;
    }

    protected void colPut(Object obj, Object obj2) {
        this.Md.put(obj, obj2);
    }

    protected Object colSetValue(int i, Object obj) {
        return this.Md.setValueAt(i, obj);
    }

    protected void colRemoveAt(int i) {
        this.Md.removeAt(i);
    }

    protected void colClear() {
        this.Md.clear();
    }
}
