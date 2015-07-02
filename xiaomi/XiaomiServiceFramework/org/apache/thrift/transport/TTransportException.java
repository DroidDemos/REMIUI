package org.apache.thrift.transport;

import org.apache.thrift.TException;

public class TTransportException extends TException {
    public static final int ALREADY_OPEN = 2;
    public static final int END_OF_FILE = 4;
    public static final int NOT_OPEN = 1;
    public static final int TIMED_OUT = 3;
    public static final int UNKNOWN = 0;
    private static final long serialVersionUID = 1;
    protected int type_;

    public TTransportException() {
        this.type_ = UNKNOWN;
    }

    public TTransportException(int type) {
        this.type_ = UNKNOWN;
        this.type_ = type;
    }

    public TTransportException(int type, String message) {
        super(message);
        this.type_ = UNKNOWN;
        this.type_ = type;
    }

    public TTransportException(String message) {
        super(message);
        this.type_ = UNKNOWN;
    }

    public TTransportException(int type, Throwable cause) {
        super(cause);
        this.type_ = UNKNOWN;
        this.type_ = type;
    }

    public TTransportException(Throwable cause) {
        super(cause);
        this.type_ = UNKNOWN;
    }

    public TTransportException(String message, Throwable cause) {
        super(message, cause);
        this.type_ = UNKNOWN;
    }

    public TTransportException(int type, String message, Throwable cause) {
        super(message, cause);
        this.type_ = UNKNOWN;
        this.type_ = type;
    }

    public int getType() {
        return this.type_;
    }
}
