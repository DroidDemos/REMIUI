package com.xiaomi.b.a;

final class C extends A {
    private C(String str) {
        super(str);
    }

    static C aq(String str) {
        return str == null ? null : new C(str);
    }
}
