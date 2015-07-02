package com.xiaomi.b.a;

final class B extends A {
    private final String[] oN;

    private B(String str) {
        super(str);
        this.oN = str.split("\\ +");
    }

    static B ao(String str) {
        return str == null ? null : new B(str);
    }
}
