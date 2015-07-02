package com.google.protobuf.nano;

public final class InternalNano {
    public static final Object LAZY_INIT_LOCK;

    static {
        LAZY_INIT_LOCK = new Object();
    }
}
