package org.apache.thrift.protocol;

import org.apache.thrift.TException;

public class TProtocolException extends TException {
    public static final int BAD_VERSION = 4;
    public static final int INVALID_DATA = 1;
    public static final int NEGATIVE_SIZE = 2;
    public static final int NOT_IMPLEMENTED = 5;
    public static final int SIZE_LIMIT = 3;
    public static final int UNKNOWN = 0;
    private static final long serialVersionUID = 1;
    protected int type_;

    public TProtocolException() {
        this.type_ = UNKNOWN;
    }

    public TProtocolException(int type) {
        this.type_ = UNKNOWN;
        this.type_ = type;
    }

    public TProtocolException(int type, String message) {
        super(message);
        this.type_ = UNKNOWN;
        this.type_ = type;
    }

    public TProtocolException(String message) {
        super(message);
        this.type_ = UNKNOWN;
    }

    public TProtocolException(int type, Throwable cause) {
        super(cause);
        this.type_ = UNKNOWN;
        this.type_ = type;
    }

    public TProtocolException(Throwable cause) {
        super(cause);
        this.type_ = UNKNOWN;
    }

    public TProtocolException(String message, Throwable cause) {
        super(message, cause);
        this.type_ = UNKNOWN;
    }

    public TProtocolException(int type, String message, Throwable cause) {
        super(message, cause);
        this.type_ = UNKNOWN;
        this.type_ = type;
    }

    public int getType() {
        return this.type_;
    }
}
