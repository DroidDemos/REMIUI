package com.xiaomi.d.e;

import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class b extends Writer {
    Writer FW;
    List b;

    public b(Writer writer) {
        this.FW = null;
        this.b = new ArrayList();
        this.FW = writer;
    }

    private void a(String str) {
        synchronized (this.b) {
            h[] hVarArr = new h[this.b.size()];
            this.b.toArray(hVarArr);
        }
        for (h a : hVarArr) {
            a.a(str);
        }
    }

    public void a(h hVar) {
        if (hVar != null) {
            synchronized (this.b) {
                if (!this.b.contains(hVar)) {
                    this.b.add(hVar);
                }
            }
        }
    }

    public void b(h hVar) {
        synchronized (this.b) {
            this.b.remove(hVar);
        }
    }

    public void close() {
        this.FW.close();
    }

    public void flush() {
        this.FW.flush();
    }

    public void write(int i) {
        this.FW.write(i);
    }

    public void write(String str) {
        this.FW.write(str);
        a(str);
    }

    public void write(String str, int i, int i2) {
        this.FW.write(str, i, i2);
        a(str.substring(i, i + i2));
    }

    public void write(char[] cArr) {
        this.FW.write(cArr);
        a(new String(cArr));
    }

    public void write(char[] cArr, int i, int i2) {
        this.FW.write(cArr, i, i2);
        a(new String(cArr, i, i2));
    }
}
