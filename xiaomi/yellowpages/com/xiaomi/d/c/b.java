package com.xiaomi.d.c;

public class b {
    public static final b FA;
    public static final b FB;
    public static final b FC;
    public static final b FD;
    public static final b FE;
    public static final b FF;
    public static final b Fi;
    public static final b Fj;
    public static final b Fk;
    public static final b Fl;
    public static final b Fm;
    public static final b Fn;
    public static final b Fo;
    public static final b Fp;
    public static final b Fq;
    public static final b Fr;
    public static final b Fs;
    public static final b Ft;
    public static final b Fu;
    public static final b Fv;
    public static final b Fw;
    public static final b Fx;
    public static final b Fy;
    public static final b Fz;
    private String y;

    static {
        Fi = new b("internal-server-error");
        Fj = new b("forbidden");
        Fk = new b("bad-request");
        Fl = new b("conflict");
        Fm = new b("feature-not-implemented");
        Fn = new b("gone");
        Fo = new b("item-not-found");
        Fp = new b("jid-malformed");
        Fq = new b("not-acceptable");
        Fr = new b("not-allowed");
        Fs = new b("not-authorized");
        Ft = new b("payment-required");
        Fu = new b("recipient-unavailable");
        Fv = new b("redirect");
        Fw = new b("registration-required");
        Fx = new b("remote-server-error");
        Fy = new b("remote-server-not-found");
        Fz = new b("remote-server-timeout");
        FA = new b("resource-constraint");
        FB = new b("service-unavailable");
        FC = new b("subscription-required");
        FD = new b("undefined-condition");
        FE = new b("unexpected-request");
        FF = new b("request-timeout");
    }

    public b(String str) {
        this.y = str;
    }

    public String toString() {
        return this.y;
    }
}
