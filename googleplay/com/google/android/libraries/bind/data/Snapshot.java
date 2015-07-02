package com.google.android.libraries.bind.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class Snapshot {
    private static final List<Data> INVALID_LIST;
    private static final Map<Object, Integer> INVALID_MAP;
    private final DataException exception;
    public final List<Data> list;
    public final int primaryKey;
    private final Map<Object, Integer> primaryKeyIndex;

    static {
        INVALID_LIST = Collections.unmodifiableList(Collections.emptyList());
        INVALID_MAP = Collections.emptyMap();
    }

    public Snapshot(int primaryKey, List<Data> list) {
        this(primaryKey, list, indexByPrimaryKey(list, primaryKey));
    }

    public Snapshot(int primaryKey, List<Data> list, Map<Object, Integer> primaryKeyIndex) {
        this.primaryKey = primaryKey;
        this.list = Collections.unmodifiableList(list);
        this.primaryKeyIndex = primaryKeyIndex;
        this.exception = null;
        for (Data data : list) {
            data.freeze();
        }
    }

    public Snapshot(int primaryKey, DataException exception) {
        this.primaryKey = primaryKey;
        this.exception = exception;
        this.list = INVALID_LIST;
        this.primaryKeyIndex = INVALID_MAP;
    }

    Snapshot(int primaryKey) {
        this.primaryKey = primaryKey;
        this.list = INVALID_LIST;
        this.primaryKeyIndex = INVALID_MAP;
        this.exception = null;
    }

    public static Map<Object, Integer> indexByPrimaryKey(List<Data> list, int primaryKey) {
        return indexByKey(list, primaryKey);
    }

    public static Map<Object, Integer> indexByKey(List<Data> list, int primaryKey) {
        if (list == null) {
            return INVALID_MAP;
        }
        if (list.size() == 0) {
            return Collections.emptyMap();
        }
        Map<Object, Integer> map = new HashMap();
        for (int i = 0; i < list.size(); i++) {
            Data data = (Data) list.get(i);
            if (data == null) {
                throw new IllegalStateException(String.format("Entry %d has no data", new Object[]{Integer.valueOf(i)}));
            }
            Object primaryValue = data.get(primaryKey);
            if (primaryValue == null) {
                throw new IllegalStateException(String.format("Entry %d has an empty primary key %s - %s", new Object[]{Integer.valueOf(i), Data.keyName(primaryKey), ((Data) list.get(i)).toString()}));
            }
            if (map.put(primaryValue, Integer.valueOf(i)) != null) {
                throw new IllegalStateException(String.format("Duplicate entries for primary key %s, value %s (class %s), positions %s and %s", new Object[]{Data.keyName(primaryKey), primaryValue, primaryValue.getClass().getSimpleName(), map.put(primaryValue, Integer.valueOf(i)), Integer.valueOf(i)}));
            }
        }
        return map;
    }

    public String toString() {
        Locale locale = Locale.US;
        String str = "%s - primaryKey: %s, size: %d, exception: %s";
        Object[] objArr = new Object[4];
        objArr[0] = getClass().getSimpleName();
        objArr[1] = Data.keyName(this.primaryKey);
        objArr[2] = Integer.valueOf(getCount());
        objArr[3] = hasException() ? getException().getMessage() : "no";
        return String.format(locale, str, objArr);
    }

    public int getCount() {
        return this.list.size();
    }

    public boolean hasException() {
        return this.exception != null;
    }

    public DataException getException() {
        return this.exception;
    }

    public boolean isInvalidPosition(int position) {
        return position < 0 || position >= this.list.size();
    }

    public Data getData(int position) {
        if (isInvalidPosition(position)) {
            return null;
        }
        return (Data) this.list.get(position);
    }

    public Object getItemId(int position) {
        if (isInvalidPosition(position)) {
            return null;
        }
        return ((Data) this.list.get(position)).get(this.primaryKey);
    }

    public boolean isEmpty() {
        return this.list.isEmpty();
    }

    public int findPositionForPrimaryValue(Object id) {
        Integer position = (Integer) this.primaryKeyIndex.get(id);
        return position == null ? -1 : position.intValue();
    }

    public boolean isInvalid() {
        return this.list == INVALID_LIST;
    }

    public List<Data> cloneList() {
        List<Data> clonedList = new ArrayList(this.list.size());
        for (Data data : this.list) {
            clonedList.add(data.copy());
        }
        return clonedList;
    }
}
