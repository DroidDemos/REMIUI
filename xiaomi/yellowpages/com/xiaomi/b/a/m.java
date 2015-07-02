package com.xiaomi.b.a;

import java.util.Collections;
import java.util.Map;

final class m extends z {
    private static final l oj;
    private final String c;
    private final Map iJ;

    static {
        oj = new h();
    }

    private m(Map map, String str) {
        this.iJ = map;
        this.c = str;
    }

    public static m Z(String str) {
        return new m(oj.V(str).bY(), str);
    }

    public String b() {
        return this.c;
    }

    public Map bY() {
        return Collections.unmodifiableMap(this.iJ);
    }
}
