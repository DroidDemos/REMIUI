package com.xiaomi.d;

import com.xiaomi.f.a.b.a;
import java.util.Map;

public class r implements Cloneable {
    private t FY;
    private String a;
    private String b;
    private int c;
    private boolean d;
    private boolean e;
    private String f;

    public r(Map map, int i, String str, t tVar) {
        this.d = s.c;
        this.e = true;
        a(map, i, str, tVar);
    }

    private void a(Map map, int i, String str, t tVar) {
        this.b = d();
        this.c = i;
        this.a = str;
        this.FY = tVar;
    }

    public static final String d() {
        return a.e ? "10.237.12.2" : a.a() ? "sandbox.xmpush.xiaomi.com" : a.b() ? "10.237.12.17" : a.b ? "58.68.235.106" : "app.chat.xiaomi.net";
    }

    public void a(String str) {
        this.a = str;
    }

    public void a(boolean z) {
        this.d = z;
    }

    public void b(String str) {
        this.f = str;
    }

    public String e() {
        return this.a;
    }

    public String f() {
        return this.f;
    }

    public int g() {
        return this.c;
    }

    public String h() {
        return this.b;
    }

    public boolean i() {
        return this.d;
    }
}
