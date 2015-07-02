package org.apache.thrift;

import java.io.Serializable;
import org.apache.thrift.protocol.TProtocol;

public interface TBase<T extends TBase, F> extends Comparable<T>, Serializable {
    void clear();

    TBase<T, F> deepCopy();

    F fieldForId(int i);

    Object getFieldValue(F f);

    boolean isSet(F f);

    void read(TProtocol tProtocol) throws TException;

    void setFieldValue(F f, Object obj);

    void write(TProtocol tProtocol) throws TException;
}
