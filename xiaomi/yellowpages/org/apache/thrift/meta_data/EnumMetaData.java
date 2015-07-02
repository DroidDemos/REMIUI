package org.apache.thrift.meta_data;

public class EnumMetaData extends FieldValueMetaData {
    public final Class a;

    public EnumMetaData(byte b, Class cls) {
        super(b);
        this.a = cls;
    }
}
