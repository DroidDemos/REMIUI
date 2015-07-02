package com.xiaomi.g.a;

import com.xiaomi.d.d.a;
import com.xiaomi.d.e.b;
import com.xiaomi.d.e.f;
import com.xiaomi.d.e.h;
import com.xiaomi.d.o;
import com.xiaomi.d.s;
import com.xiaomi.d.u;
import java.io.Reader;
import java.io.Writer;
import java.text.SimpleDateFormat;

public class e implements a {
    public static boolean a;
    private SimpleDateFormat ul;
    private s um;
    private o un;
    private u uo;
    private Writer up;
    private Reader uq;
    private f ur;
    private h us;

    static {
        a = false;
    }

    public e(s sVar, Writer writer, Reader reader) {
        this.ul = new SimpleDateFormat("hh:mm:ss aaa");
        this.um = null;
        this.un = null;
        this.uo = null;
        this.um = sVar;
        this.up = writer;
        this.uq = reader;
        e();
    }

    private void e() {
        Reader aVar = new com.xiaomi.d.e.a(this.uq);
        this.ur = new c(this);
        aVar.a(this.ur);
        Writer bVar = new b(this.up);
        this.us = new d(this);
        bVar.a(this.us);
        this.uq = aVar;
        this.up = bVar;
        this.un = new a(this);
        this.uo = new b(this);
    }

    public Reader a(Reader reader) {
        ((com.xiaomi.d.e.a) this.uq).b(this.ur);
        Reader aVar = new com.xiaomi.d.e.a(reader);
        aVar.a(this.ur);
        this.uq = aVar;
        return this.uq;
    }

    public Writer a(Writer writer) {
        ((b) this.up).b(this.us);
        Writer bVar = new b(writer);
        bVar.a(this.us);
        this.up = bVar;
        return this.up;
    }

    public Reader eA() {
        return this.uq;
    }

    public Writer eB() {
        return this.up;
    }

    public o eC() {
        return this.un;
    }

    public o eD() {
        return null;
    }
}
