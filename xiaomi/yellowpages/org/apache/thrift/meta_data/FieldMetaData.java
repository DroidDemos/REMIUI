package org.apache.thrift.meta_data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class FieldMetaData implements Serializable {
    private static Map cm;
    public final String a;
    public final byte b;
    public final FieldValueMetaData c;

    static {
        cm = new HashMap();
    }

    public FieldMetaData(String str, byte b, FieldValueMetaData fieldValueMetaData) {
        this.a = str;
        this.b = b;
        this.c = fieldValueMetaData;
    }

    public static void a(Class cls, Map map) {
        cm.put(cls, map);
    }
}
