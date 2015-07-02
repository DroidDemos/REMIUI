package com.xiaomi.b.a;

final class D extends A {
    private final String[] oN;

    private D(String str) {
        super(str);
        this.oN = str.split("[\\s,]+");
    }

    static D ar(String str) {
        return str == null ? null : new D(str);
    }
}
