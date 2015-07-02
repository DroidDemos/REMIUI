package com.xiaomi.d;

import com.xiaomi.d.c.c;
import com.xiaomi.d.c.g;
import com.xiaomi.d.c.j;

public class b extends g {
    private B vd;

    public String a() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<bind ");
        if (k() != null) {
            stringBuilder.append("id=\"" + k() + "\" ");
        }
        if (m() != null) {
            stringBuilder.append("to=\"").append(com.xiaomi.d.e.g.a(m())).append("\" ");
        }
        if (iW() != null) {
            stringBuilder.append("from=\"").append(com.xiaomi.d.e.g.a(iW())).append("\" ");
        }
        if (l() != null) {
            stringBuilder.append(" chid=\"").append(com.xiaomi.d.e.g.a(l())).append("\" ");
        }
        if (this.vd == null) {
            stringBuilder.append("type=\"result\">");
        } else {
            stringBuilder.append("type=\"").append(eX()).append("\">");
        }
        if (iY() != null) {
            for (j d : iY()) {
                stringBuilder.append(d.d());
            }
        }
        c iX = iX();
        if (iX != null) {
            stringBuilder.append(iX.d());
        }
        stringBuilder.append("</bind>");
        return stringBuilder.toString();
    }

    public void a(B b) {
        if (b == null) {
            this.vd = B.vd;
        } else {
            this.vd = b;
        }
    }

    public B eX() {
        return this.vd;
    }
}
