package com.xiaomi.b.a;

import java.util.Collections;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Pattern;

public final class d extends z {
    private static final Pattern gf;
    private final String c;
    private final Map iJ;
    private final AtomicReference nE;

    static {
        gf = Pattern.compile("<body(?:[\t\n\r ][^>]*?)?(/>|>)", 64);
    }

    private d(Map map, String str) {
        this.nE = new AtomicReference();
        this.iJ = map;
        this.c = str;
    }

    private String a(String str) {
        return str.replace("'", "&apos;");
    }

    public static P bX() {
        return new P();
    }

    private String g() {
        j cv = z.cv();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<");
        stringBuilder.append(cv.b());
        for (Entry entry : this.iJ.entrySet()) {
            stringBuilder.append(" ");
            j jVar = (j) entry.getKey();
            String c = jVar.c();
            if (c != null && c.length() > 0) {
                stringBuilder.append(c);
                stringBuilder.append(":");
            }
            stringBuilder.append(jVar.b());
            stringBuilder.append("='");
            stringBuilder.append(a((String) entry.getValue()));
            stringBuilder.append("'");
        }
        stringBuilder.append(" ");
        stringBuilder.append("xmlns");
        stringBuilder.append("='");
        stringBuilder.append(cv.a());
        stringBuilder.append("'>");
        if (this.c != null) {
            stringBuilder.append(this.c);
        }
        stringBuilder.append("</body>");
        return stringBuilder.toString();
    }

    public String b() {
        String str = (String) this.nE.get();
        if (str != null) {
            return str;
        }
        str = g();
        this.nE.set(str);
        return str;
    }

    public Map bY() {
        return Collections.unmodifiableMap(this.iJ);
    }

    public P bZ() {
        return P.e(this);
    }

    public String f() {
        return this.c;
    }
}
