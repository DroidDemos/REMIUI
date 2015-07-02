package com.xiaomi.push.service;

import android.text.TextUtils;
import com.xiaomi.push.service.q.c;

class e extends b {
    private final m co;

    public e(m mVar) {
        super(12);
        this.co = mVar;
    }

    public void a() {
        this.co.a(c.unbind, 1, 21, null, null);
    }

    public String b() {
        return "bind time out. chid=" + this.co.h;
    }

    public boolean equals(Object obj) {
        return !(obj instanceof e) ? false : TextUtils.equals(((e) obj).co.h, this.co.h);
    }

    public int hashCode() {
        return this.co.h.hashCode();
    }
}
