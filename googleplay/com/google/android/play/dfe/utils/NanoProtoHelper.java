package com.google.android.play.dfe.utils;

import com.google.protobuf.nano.MessageNano;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

public class NanoProtoHelper {
    private static final Map<Class<?>, Field> sFieldCache;

    static {
        sFieldCache = new HashMap();
    }

    public static <X extends MessageNano, Y extends MessageNano> void setRequestInWrapper(X wrapper, Class<X> wrapperClass, Y request, Class<Y> requestClass) {
        try {
            findField(wrapperClass, requestClass).set(wrapper, request);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static <X extends MessageNano, Y extends MessageNano> Y getParsedResponseFromWrapper(X wrapper, Class<X> wrapperClass, Class<Y> responseClass) {
        try {
            return (MessageNano) findField(wrapperClass, responseClass).get(wrapper);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static Field findField(Class<?> outerClass, Class<?> innerClass) {
        Field field = (Field) sFieldCache.get(innerClass);
        if (field != null) {
            return field;
        }
        for (Field f : outerClass.getFields()) {
            if (f.getType().equals(innerClass) && Modifier.isPublic(f.getModifiers())) {
                sFieldCache.put(innerClass, f);
                return f;
            }
        }
        throw new IllegalArgumentException("No field for " + innerClass + " in " + outerClass);
    }
}
