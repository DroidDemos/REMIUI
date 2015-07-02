package com.xiaomi.d;

import android.os.Build;
import android.os.Build.VERSION;
import com.xiaomi.d.c.g;
import java.io.Writer;

class p {
    private x FU;
    private Writer FW;

    protected p(x xVar) {
        this.FU = xVar;
        this.FW = xVar.Gd;
    }

    private void c(g gVar) {
        synchronized (this.FW) {
            try {
                this.FW.write(gVar.a() + "\r\n");
                this.FW.flush();
            } catch (Throwable e) {
                throw new v(e);
            }
        }
    }

    void a() {
        this.FU.yk.clear();
    }

    public void a(g gVar) {
        c(gVar);
        this.FU.d(gVar);
    }

    public void b() {
        synchronized (this.FW) {
            this.FW.write("</stream:stream>");
            this.FW.flush();
        }
    }

    void c() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<stream:stream");
        stringBuilder.append(" xmlns=\"xm\"");
        stringBuilder.append(" xmlns:stream=\"xm\"");
        stringBuilder.append(" to=\"").append(this.FU.d()).append("\"");
        stringBuilder.append(" version=\"105\"");
        stringBuilder.append(" model=\"").append(com.xiaomi.d.e.g.a(Build.MODEL)).append("\"");
        stringBuilder.append(" os=\"").append(com.xiaomi.d.e.g.a(VERSION.INCREMENTAL)).append("\"");
        stringBuilder.append(" connpt=\"").append(com.xiaomi.d.e.g.a(this.FU.f())).append("\"");
        stringBuilder.append(" host=\"").append(this.FU.e()).append("\"");
        stringBuilder.append(">");
        this.FW.write(stringBuilder.toString());
        this.FW.flush();
    }

    public void d() {
        synchronized (this.FW) {
            try {
                this.FW.write(this.FU.ib() + "\r\n");
                this.FW.flush();
                this.FU.id();
            } catch (Throwable e) {
                throw new v(e);
            }
        }
    }
}
