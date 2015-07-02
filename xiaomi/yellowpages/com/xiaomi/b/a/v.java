package com.xiaomi.b.a;

import java.util.concurrent.TimeUnit;

final class v extends y {
    private v(String str) {
        super(str);
        a(0);
    }

    static v am(String str) {
        return str == null ? null : new v(str);
    }

    public int c() {
        return (int) TimeUnit.MILLISECONDS.convert((long) ah(), TimeUnit.SECONDS);
    }
}
