package com.xiaomi.b.a;

import java.util.concurrent.TimeUnit;

final class o extends y {
    private o(String str) {
        super(str);
        a(1);
    }

    static o ae(String str) {
        return str == null ? null : new o(str);
    }

    public int c() {
        return (int) TimeUnit.MILLISECONDS.convert((long) ah(), TimeUnit.SECONDS);
    }
}
