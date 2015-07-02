package com.xiaomi.b.a;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EventObject;
import java.util.List;

public final class w extends EventObject {
    private final boolean a;
    private final List b;
    private final Throwable c;

    private w(H h, boolean z, List list, Throwable th) {
        super(h);
        this.a = z;
        this.c = th;
        if (this.a) {
            if (th != null) {
                throw new IllegalStateException("Cannot be connected and have a cause");
            } else if (list != null && list.size() > 0) {
                throw new IllegalStateException("Cannot be connected and have outstanding requests");
            }
        }
        if (list == null) {
            this.b = Collections.emptyList();
        } else {
            this.b = Collections.unmodifiableList(new ArrayList(list));
        }
    }

    static w a(H h, List list, Throwable th) {
        return new w(h, false, list, th);
    }

    static w e(H h) {
        return new w(h, true, null, null);
    }

    static w f(H h) {
        return new w(h, false, null, null);
    }

    public boolean a() {
        return this.a;
    }

    public Throwable cK() {
        return this.c;
    }
}
