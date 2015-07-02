package org.apache.thrift.transport;

import org.apache.thrift.TException;

public class TTransportException extends TException {
    protected int a;

    public TTransportException() {
        this.a = 0;
    }

    public TTransportException(String str) {
        super(str);
        this.a = 0;
    }
}
