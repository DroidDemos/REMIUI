package com.xiaomi.d.e;

import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class a extends Reader {
    Reader Jq;
    List b;

    public a(Reader reader) {
        this.Jq = null;
        this.b = new ArrayList();
        this.Jq = reader;
    }

    public void a(f fVar) {
        if (fVar != null) {
            synchronized (this.b) {
                if (!this.b.contains(fVar)) {
                    this.b.add(fVar);
                }
            }
        }
    }

    public void b(f fVar) {
        synchronized (this.b) {
            this.b.remove(fVar);
        }
    }

    public void close() {
        this.Jq.close();
    }

    public void mark(int i) {
        this.Jq.mark(i);
    }

    public boolean markSupported() {
        return this.Jq.markSupported();
    }

    public int read() {
        return this.Jq.read();
    }

    public int read(char[] cArr) {
        return this.Jq.read(cArr);
    }

    public int read(char[] cArr, int i, int i2) {
        int read = this.Jq.read(cArr, i, i2);
        if (read > 0) {
            f[] fVarArr;
            String str = new String(cArr, i, read);
            synchronized (this.b) {
                fVarArr = new f[this.b.size()];
                this.b.toArray(fVarArr);
            }
            for (f a : fVarArr) {
                a.a(str);
            }
        }
        return read;
    }

    public boolean ready() {
        return this.Jq.ready();
    }

    public void reset() {
        this.Jq.reset();
    }

    public long skip(long j) {
        return this.Jq.skip(j);
    }
}
