package com.google.common.primitives;

import java.lang.reflect.Field;
import java.security.PrivilegedExceptionAction;
import sun.misc.Unsafe;

/* compiled from: UnsignedBytes */
final class c implements PrivilegedExceptionAction {
    c() {
    }

    public /* bridge */ /* synthetic */ Object run() {
        return ev();
    }

    public Unsafe ev() {
        Class cls = Unsafe.class;
        for (Field field : cls.getDeclaredFields()) {
            field.setAccessible(true);
            Object obj = field.get(null);
            if (cls.isInstance(obj)) {
                return (Unsafe) cls.cast(obj);
            }
        }
        throw new NoSuchFieldError("the Unsafe");
    }
}
