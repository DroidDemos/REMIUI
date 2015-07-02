package com.xiaomi.b.a;

final class p extends y {
    private p(String str) {
        super(str);
        a(1);
    }

    static p af(String str) {
        return str == null ? null : new p(str);
    }
}
