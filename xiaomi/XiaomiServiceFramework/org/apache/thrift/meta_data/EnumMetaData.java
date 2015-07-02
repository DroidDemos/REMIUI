package org.apache.thrift.meta_data;

public class EnumMetaData extends FieldValueMetaData {
    public final Class enumClass;

    public EnumMetaData(byte type, Class sClass) {
        super(type);
        this.enumClass = sClass;
    }
}
