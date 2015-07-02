package com.xiaomi.b.a;

import com.alipay.sdk.cons.MiniDefine;
import java.net.URI;
import javax.net.ssl.SSLContext;

public final class R {
    private Boolean KM;
    private final String b;
    private String c;
    private String d;
    private String e;
    private String f;
    private int g;
    private final URI qc;
    private SSLContext qd;

    private R(URI uri, String str) {
        this.qc = uri;
        this.b = str;
    }

    public static R a(URI uri, String str) {
        if (uri == null) {
            throw new IllegalArgumentException("Connection manager URI must not be null");
        } else if (str == null) {
            throw new IllegalArgumentException("Target domain must not be null");
        } else {
            String scheme = uri.getScheme();
            if ("http".equals(scheme) || MiniDefine.aQ.equals(scheme)) {
                return new R(uri, str);
            }
            throw new IllegalArgumentException("Only 'http' and 'https' URI are allowed");
        }
    }

    public N il() {
        return new N(this.qc, this.b, this.c, this.d == null ? MiniDefine.ao : this.d, this.e, this.f, this.f == null ? 0 : this.g, this.qd, this.KM == null ? false : this.KM.booleanValue());
    }
}
