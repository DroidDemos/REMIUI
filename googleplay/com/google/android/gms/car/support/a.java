package com.google.android.gms.car.support;

import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class a<K, V> extends l<K, V> implements Map<K, V> {
    j<K, V> MW;

    private j<K, V> hi() {
        if (this.MW == null) {
            this.MW = new j<K, V>(this) {
                final /* synthetic */ a MX;

                {
                    this.MX = r1;
                }

                protected void colClear() {
                    this.MX.clear();
                }

                protected Object colGetEntry(int index, int offset) {
                    return this.MX.mArray[(index << 1) + offset];
                }

                protected Map<K, V> colGetMap() {
                    return this.MX;
                }

                protected int colGetSize() {
                    return this.MX.mSize;
                }

                protected int colIndexOfKey(Object key) {
                    return key == null ? this.MX.indexOfNull() : this.MX.indexOf(key, key.hashCode());
                }

                protected int colIndexOfValue(Object value) {
                    return this.MX.indexOfValue(value);
                }

                protected void colPut(K key, V value) {
                    this.MX.put(key, value);
                }

                protected void colRemoveAt(int index) {
                    this.MX.removeAt(index);
                }

                protected V colSetValue(int index, V value) {
                    return this.MX.setValueAt(index, value);
                }
            };
        }
        return this.MW;
    }

    public Set<Entry<K, V>> entrySet() {
        return hi().getEntrySet();
    }

    public Set<K> keySet() {
        return hi().getKeySet();
    }

    public void putAll(Map<? extends K, ? extends V> map) {
        ensureCapacity(this.mSize + map.size());
        for (Entry entry : map.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    public Collection<V> values() {
        return hi().getValues();
    }
}
