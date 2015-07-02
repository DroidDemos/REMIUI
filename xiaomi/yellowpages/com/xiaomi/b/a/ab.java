package com.xiaomi.b.a;

import java.util.EventObject;

public final class ab extends EventObject {
    private final z a;

    private ab(Object obj, z zVar) {
        super(obj);
        if (zVar == null) {
            throw new IllegalArgumentException("message body may not be null");
        }
        this.a = zVar;
    }

    static ab a(H h, z zVar) {
        return new ab(h, zVar);
    }

    static ab b(H h, z zVar) {
        return new ab(h, zVar);
    }

    public z cf() {
        return this.a;
    }
}
