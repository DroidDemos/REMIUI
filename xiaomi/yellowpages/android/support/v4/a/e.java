package android.support.v4.a;

import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/* compiled from: ArrayMap */
public class e extends a implements Map {
    c qe;

    private c cM() {
        if (this.qe == null) {
            this.qe = new m(this);
        }
        return this.qe;
    }

    public void putAll(Map map) {
        ensureCapacity(this.mSize + map.size());
        for (Entry entry : map.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    public Set entrySet() {
        return cM().getEntrySet();
    }

    public Set keySet() {
        return cM().getKeySet();
    }

    public Collection values() {
        return cM().getValues();
    }
}
