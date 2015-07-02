package com.xiaomi.b.a;

final class J extends y {
    private J(String str) {
        super(str);
        a(1);
    }

    static J as(String str) {
        return str == null ? null : new J(str);
    }
}
