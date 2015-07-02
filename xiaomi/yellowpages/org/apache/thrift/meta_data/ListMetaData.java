package org.apache.thrift.meta_data;

public class ListMetaData extends FieldValueMetaData {
    public final FieldValueMetaData a;

    public ListMetaData(byte b, FieldValueMetaData fieldValueMetaData) {
        super(b);
        this.a = fieldValueMetaData;
    }
}
