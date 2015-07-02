package com.xiaomi.b.a;

import com.alipay.mobilesecuritysdk.constant.ConfigConstant;
import java.util.HashMap;
import java.util.Map;

public final class P {
    private boolean b;
    private String c;
    private Map fe;

    private P() {
    }

    private static P e(d dVar) {
        P p = new P();
        p.fe = dVar.bY();
        p.b = true;
        p.c = dVar.c;
        return p;
    }

    public P b(j jVar, String str) {
        if (this.fe == null) {
            this.fe = new HashMap();
        } else if (this.b) {
            this.fe = new HashMap(this.fe);
            this.b = false;
        }
        if (str == null) {
            this.fe.remove(jVar);
        } else {
            this.fe.put(jVar, str);
        }
        return this;
    }

    public P cl(String str) {
        if (str == null) {
            throw new IllegalArgumentException("payload XML argument cannot be null");
        }
        this.c = str;
        return this;
    }

    public d ij() {
        if (this.fe == null) {
            this.fe = new HashMap();
        }
        if (this.c == null) {
            this.c = ConfigConstant.WIRELESS_FILENAME;
        }
        return new d(this.fe, this.c);
    }

    public P w(String str, String str2) {
        return b(j.b("http://www.w3.org/XML/1998/namespace", str, "xmlns"), str2);
    }
}
