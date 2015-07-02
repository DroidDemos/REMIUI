package com.xiaomi.d.c;

import android.os.Bundle;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class c {
    private int a;
    private String b;
    private String c;
    private String d;
    private String e;
    private List f;

    public c(int i, String str, String str2, String str3, String str4, List list) {
        this.f = null;
        this.a = i;
        this.b = str;
        this.d = str2;
        this.c = str3;
        this.e = str4;
        this.f = list;
    }

    public c(Bundle bundle) {
        this.f = null;
        this.a = bundle.getInt("ext_err_code");
        if (bundle.containsKey("ext_err_type")) {
            this.b = bundle.getString("ext_err_type");
        }
        this.c = bundle.getString("ext_err_cond");
        this.d = bundle.getString("ext_err_reason");
        this.e = bundle.getString("ext_err_msg");
        Parcelable[] parcelableArray = bundle.getParcelableArray("ext_exts");
        if (parcelableArray != null) {
            this.f = new ArrayList(parcelableArray.length);
            for (Parcelable parcelable : parcelableArray) {
                j k = j.k((Bundle) parcelable);
                if (k != null) {
                    this.f.add(k);
                }
            }
        }
    }

    public c(b bVar) {
        this.f = null;
        b(bVar);
        this.e = null;
    }

    public c(b bVar, String str) {
        this.f = null;
        b(bVar);
        this.e = str;
    }

    private void b(b bVar) {
        this.c = bVar.y;
    }

    public String a() {
        return this.d;
    }

    public String b() {
        return this.b;
    }

    public String d() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<error code=\"").append(this.a).append("\"");
        if (this.b != null) {
            stringBuilder.append(" type=\"");
            stringBuilder.append(this.b);
            stringBuilder.append("\"");
        }
        if (this.d != null) {
            stringBuilder.append(" reason=\"");
            stringBuilder.append(this.d);
            stringBuilder.append("\"");
        }
        stringBuilder.append(">");
        if (this.c != null) {
            stringBuilder.append("<").append(this.c);
            stringBuilder.append(" xmlns=\"urn:ietf:params:xml:ns:xmpp-stanzas\"/>");
        }
        if (this.e != null) {
            stringBuilder.append("<text xml:lang=\"en\" xmlns=\"urn:ietf:params:xml:ns:xmpp-stanzas\">");
            stringBuilder.append(this.e);
            stringBuilder.append("</text>");
        }
        for (j d : iL()) {
            stringBuilder.append(d.d());
        }
        stringBuilder.append("</error>");
        return stringBuilder.toString();
    }

    public Bundle iK() {
        Bundle bundle = new Bundle();
        if (this.b != null) {
            bundle.putString("ext_err_type", this.b);
        }
        bundle.putInt("ext_err_code", this.a);
        if (this.d != null) {
            bundle.putString("ext_err_reason", this.d);
        }
        if (this.c != null) {
            bundle.putString("ext_err_cond", this.c);
        }
        if (this.e != null) {
            bundle.putString("ext_err_msg", this.e);
        }
        if (this.f != null) {
            Parcelable[] parcelableArr = new Bundle[this.f.size()];
            int i = 0;
            for (j jh : this.f) {
                int i2;
                Bundle jh2 = jh.jh();
                if (jh2 != null) {
                    i2 = i + 1;
                    parcelableArr[i] = jh2;
                } else {
                    i2 = i;
                }
                i = i2;
            }
            bundle.putParcelableArray("ext_exts", parcelableArr);
        }
        return bundle;
    }

    public List iL() {
        List emptyList;
        synchronized (this) {
            emptyList = this.f == null ? Collections.emptyList() : Collections.unmodifiableList(this.f);
        }
        return emptyList;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        if (this.c != null) {
            stringBuilder.append(this.c);
        }
        stringBuilder.append("(").append(this.a).append(")");
        if (this.e != null) {
            stringBuilder.append(" ").append(this.e);
        }
        return stringBuilder.toString();
    }
}
