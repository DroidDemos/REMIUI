package org.apache.thrift.protocol;

import org.apache.thrift.TException;

public class TProtocolException extends TException {
    protected int a;

    public TProtocolException() {
        this.a = 0;
    }

    public TProtocolException(String str) {
        super(str);
        this.a = 0;
    }
}
