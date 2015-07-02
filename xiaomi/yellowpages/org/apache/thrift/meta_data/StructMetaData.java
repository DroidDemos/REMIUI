package org.apache.thrift.meta_data;

public class StructMetaData extends FieldValueMetaData {
    public final Class a;

    public StructMetaData(byte b, Class cls) {
        super(b);
        this.a = cls;
    }
}
