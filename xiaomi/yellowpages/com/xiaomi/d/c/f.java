package com.xiaomi.d.c;

import android.os.Bundle;
import com.xiaomi.d.e.g;

public class f extends g {
    private b EE;
    private a Mt;
    private String d;
    private int e;

    public enum a {
        chat,
        available,
        away,
        xa,
        dnd
    }

    public enum b {
        available,
        unavailable,
        subscribe,
        subscribed,
        unsubscribe,
        unsubscribed,
        error,
        probe
    }

    public f(Bundle bundle) {
        super(bundle);
        this.EE = b.available;
        this.d = null;
        this.e = Integer.MIN_VALUE;
        this.Mt = null;
        if (bundle.containsKey("ext_pres_type")) {
            this.EE = b.valueOf(bundle.getString("ext_pres_type"));
        }
        if (bundle.containsKey("ext_pres_status")) {
            this.d = bundle.getString("ext_pres_status");
        }
        if (bundle.containsKey("ext_pres_prio")) {
            this.e = bundle.getInt("ext_pres_prio");
        }
        if (bundle.containsKey("ext_pres_mode")) {
            this.Mt = a.valueOf(bundle.getString("ext_pres_mode"));
        }
    }

    public f(b bVar) {
        this.EE = b.available;
        this.d = null;
        this.e = Integer.MIN_VALUE;
        this.Mt = null;
        a(bVar);
    }

    public String a() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<presence");
        if (jb() != null) {
            stringBuilder.append(" xmlns=\"").append(jb()).append("\"");
        }
        if (k() != null) {
            stringBuilder.append(" id=\"").append(k()).append("\"");
        }
        if (m() != null) {
            stringBuilder.append(" to=\"").append(g.a(m())).append("\"");
        }
        if (iW() != null) {
            stringBuilder.append(" from=\"").append(g.a(iW())).append("\"");
        }
        if (l() != null) {
            stringBuilder.append(" chid=\"").append(g.a(l())).append("\"");
        }
        if (this.EE != null) {
            stringBuilder.append(" type=\"").append(this.EE).append("\"");
        }
        stringBuilder.append(">");
        if (this.d != null) {
            stringBuilder.append("<status>").append(g.a(this.d)).append("</status>");
        }
        if (this.e != Integer.MIN_VALUE) {
            stringBuilder.append("<priority>").append(this.e).append("</priority>");
        }
        if (!(this.Mt == null || this.Mt == a.available)) {
            stringBuilder.append("<show>").append(this.Mt).append("</show>");
        }
        stringBuilder.append(ja());
        c iX = iX();
        if (iX != null) {
            stringBuilder.append(iX.d());
        }
        stringBuilder.append("</presence>");
        return stringBuilder.toString();
    }

    public void a(int i) {
        if (i < -128 || i > 128) {
            throw new IllegalArgumentException("Priority value " + i + " is not valid. Valid range is -128 through 128.");
        }
        this.e = i;
    }

    public void a(a aVar) {
        this.Mt = aVar;
    }

    public void a(b bVar) {
        if (bVar == null) {
            throw new NullPointerException("Type cannot be null");
        }
        this.EE = bVar;
    }

    public void a(String str) {
        this.d = str;
    }

    public Bundle iK() {
        Bundle iK = super.iK();
        if (this.EE != null) {
            iK.putString("ext_pres_type", this.EE.toString());
        }
        if (this.d != null) {
            iK.putString("ext_pres_status", this.d);
        }
        if (this.e != Integer.MIN_VALUE) {
            iK.putInt("ext_pres_prio", this.e);
        }
        if (!(this.Mt == null || this.Mt == a.available)) {
            iK.putString("ext_pres_mode", this.Mt.toString());
        }
        return iK;
    }
}
