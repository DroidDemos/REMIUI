package com.alipay.sdk.data;

import com.alipay.sdk.util.LogUtils;

public class Envelope {
    private String a;
    private String b;
    private String c;
    private String d;
    private String e;
    private String f;
    private String g;

    public Envelope() {
        this.g = "com.alipay.mcpay";
    }

    public String a() {
        return this.g;
    }

    public void a(String str) {
        this.g = str;
    }

    public String b() {
        return this.a;
    }

    public void b(String str) {
        this.a = h(str);
    }

    public String c() {
        return this.b;
    }

    public void c(String str) {
        this.b = str;
    }

    public String d() {
        return this.c;
    }

    public void d(String str) {
        this.c = str;
    }

    public String e() {
        return this.d;
    }

    public void e(String str) {
        this.d = str;
    }

    public String toString() {
        return "requestUrl = " + this.a + ", namespace = " + this.b + ", apiName = " + this.c + ", apiVersion = " + this.d;
    }

    private String h(String str) {
        LogUtils.b("filterHost, GlobalConstant.PRE = " + false);
        return str;
    }

    public String f() {
        return this.e;
    }

    public void f(String str) {
        this.e = str;
    }

    public String g() {
        return this.f;
    }

    public void g(String str) {
        this.f = str;
    }
}
