package com.xiaomi.b.a;

import java.lang.ref.SoftReference;

final class k extends ThreadLocal {
    k() {
    }

    protected SoftReference cj() {
        return new SoftReference(null);
    }

    protected /* synthetic */ Object initialValue() {
        return cj();
    }
}
