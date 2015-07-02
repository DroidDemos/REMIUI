package org.apache.thrift.meta_data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import org.apache.thrift.TBase;

public class FieldMetaData implements Serializable {
    private static Map<Class<? extends TBase>, Map<?, FieldMetaData>> structMap;
    public final String fieldName;
    public final byte requirementType;
    public final FieldValueMetaData valueMetaData;

    static {
        structMap = new HashMap();
    }

    public FieldMetaData(String name, byte req, FieldValueMetaData vMetaData) {
        this.fieldName = name;
        this.requirementType = req;
        this.valueMetaData = vMetaData;
    }

    public static void addStructMetaDataMap(Class<? extends TBase> sClass, Map<?, FieldMetaData> map) {
        structMap.put(sClass, map);
    }

    public static Map<?, FieldMetaData> getStructMetaDataMap(Class<? extends TBase> sClass) {
        if (!structMap.containsKey(sClass)) {
            try {
                sClass.newInstance();
            } catch (InstantiationException e) {
                throw new RuntimeException("InstantiationException for TBase class: " + sClass.getName() + ", message: " + e.getMessage());
            } catch (IllegalAccessException e2) {
                throw new RuntimeException("IllegalAccessException for TBase class: " + sClass.getName() + ", message: " + e2.getMessage());
            }
        }
        return (Map) structMap.get(sClass);
    }
}
