package org.apache.thrift;

public class TException extends Exception {
    public TException(String str) {
        super(str);
    }

    public TException(String str, Throwable th) {
        super(str, th);
    }
}
