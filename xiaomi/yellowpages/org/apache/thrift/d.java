package org.apache.thrift;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;

class d implements Comparator {
    private d() {
    }

    public int compare(Object obj, Object obj2) {
        return (obj == null && obj2 == null) ? 0 : obj == null ? -1 : obj2 == null ? 1 : obj instanceof List ? b.a((List) obj, (List) obj2) : obj instanceof Set ? b.a((Set) obj, (Set) obj2) : obj instanceof Map ? b.a((Map) obj, (Map) obj2) : obj instanceof byte[] ? b.b((byte[]) obj, (byte[]) obj2) : b.a((Comparable) obj, (Comparable) obj2);
    }
}
