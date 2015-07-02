package com.xiaomi.d.c;

import android.os.Bundle;
import com.xiaomi.d.e.g;

public class i extends g {
    private a gU;

    public i() {
        this.gU = a.gU;
    }

    public i(Bundle bundle) {
        super(bundle);
        this.gU = a.gU;
        if (bundle.containsKey("ext_iq_type")) {
            this.gU = a.E(bundle.getString("ext_iq_type"));
        }
    }

    public String a() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<iq ");
        if (k() != null) {
            stringBuilder.append("id=\"" + k() + "\" ");
        }
        if (m() != null) {
            stringBuilder.append("to=\"").append(g.a(m())).append("\" ");
        }
        if (iW() != null) {
            stringBuilder.append("from=\"").append(g.a(iW())).append("\" ");
        }
        if (l() != null) {
            stringBuilder.append("chid=\"").append(g.a(l())).append("\" ");
        }
        if (this.gU == null) {
            stringBuilder.append("type=\"get\">");
        } else {
            stringBuilder.append("type=\"").append(jg()).append("\">");
        }
        String d = d();
        if (d != null) {
            stringBuilder.append(d);
        }
        stringBuilder.append(ja());
        c iX = iX();
        if (iX != null) {
            stringBuilder.append(iX.d());
        }
        stringBuilder.append("</iq>");
        return stringBuilder.toString();
    }

    public void a(a aVar) {
        if (aVar == null) {
            this.gU = a.gU;
        } else {
            this.gU = aVar;
        }
    }

    public String d() {
        return null;
    }

    public Bundle iK() {
        Bundle iK = super.iK();
        if (this.gU != null) {
            iK.putString("ext_iq_type", this.gU.toString());
        }
        return iK;
    }

    public a jg() {
        return this.gU;
    }
}
