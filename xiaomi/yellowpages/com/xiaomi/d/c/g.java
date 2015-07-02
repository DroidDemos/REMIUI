package com.xiaomi.d.c;

import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.CopyOnWriteArrayList;

public abstract class g {
    public static final DateFormat MA;
    private static String a;
    protected static final String b;
    private static String d;
    private static long e;
    private List MB;
    private c MC;
    private final Map eI;
    private String f;
    private String g;
    private String h;
    private String i;
    private String j;

    static {
        b = Locale.getDefault().getLanguage().toLowerCase();
        a = null;
        MA = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        MA.setTimeZone(TimeZone.getTimeZone("UTC"));
        d = com.xiaomi.d.e.g.a(5) + "-";
        e = 0;
    }

    public g() {
        this.f = a;
        this.g = null;
        this.h = null;
        this.i = null;
        this.j = null;
        this.MB = new CopyOnWriteArrayList();
        this.eI = new HashMap();
        this.MC = null;
    }

    public g(Bundle bundle) {
        this.f = a;
        this.g = null;
        this.h = null;
        this.i = null;
        this.j = null;
        this.MB = new CopyOnWriteArrayList();
        this.eI = new HashMap();
        this.MC = null;
        this.h = bundle.getString("ext_to");
        this.i = bundle.getString("ext_from");
        this.j = bundle.getString("ext_chid");
        this.g = bundle.getString("ext_pkt_id");
        Parcelable[] parcelableArray = bundle.getParcelableArray("ext_exts");
        if (parcelableArray != null) {
            this.MB = new ArrayList(parcelableArray.length);
            for (Parcelable parcelable : parcelableArray) {
                j k = j.k((Bundle) parcelable);
                if (k != null) {
                    this.MB.add(k);
                }
            }
        }
        Bundle bundle2 = bundle.getBundle("ext_ERROR");
        if (bundle2 != null) {
            this.MC = new c(bundle2);
        }
    }

    public static String iV() {
        return b;
    }

    public static String j() {
        String str;
        synchronized (g.class) {
            try {
            } finally {
                str = g.class;
            }
        }
        StringBuilder append = new StringBuilder().append(d);
        long j = e;
        e = 1 + j;
        str = Long.toString(j);
        String stringBuilder = append.append(str).toString();
        return stringBuilder;
    }

    public abstract String a();

    public void a(c cVar) {
        this.MC = cVar;
    }

    public void a(j jVar) {
        this.MB.add(jVar);
    }

    public void a(String str, Object obj) {
        synchronized (this) {
            if (obj instanceof Serializable) {
                this.eI.put(str, obj);
            } else {
                throw new IllegalArgumentException("Value must be serialiazble");
            }
        }
    }

    public j cA(String str) {
        return x(str, null);
    }

    public Object cB(String str) {
        Object obj;
        synchronized (this) {
            obj = this.eI == null ? null : this.eI.get(str);
        }
        return obj;
    }

    public void cw(String str) {
        this.g = str;
    }

    public void cx(String str) {
        this.j = str;
    }

    public void cy(String str) {
        this.h = str;
    }

    public void cz(String str) {
        this.i = str;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(java.lang.Object r5) {
        /*
        r4 = this;
        r1 = 0;
        r0 = 1;
        if (r4 != r5) goto L_0x0006;
    L_0x0004:
        r1 = r0;
    L_0x0005:
        return r1;
    L_0x0006:
        if (r5 == 0) goto L_0x00ac;
    L_0x0008:
        r2 = r4.getClass();
        r3 = r5.getClass();
        if (r2 != r3) goto L_0x00ac;
    L_0x0012:
        r5 = (com.xiaomi.d.c.g) r5;
        r2 = r4.MC;
        if (r2 == 0) goto L_0x0083;
    L_0x0018:
        r2 = r4.MC;
        r3 = r5.MC;
        r2 = r2.equals(r3);
        if (r2 == 0) goto L_0x00ac;
    L_0x0022:
        r2 = r4.i;
        if (r2 == 0) goto L_0x0089;
    L_0x0026:
        r2 = r4.i;
        r3 = r5.i;
        r2 = r2.equals(r3);
        if (r2 == 0) goto L_0x00ac;
    L_0x0030:
        r2 = r4.MB;
        r3 = r5.MB;
        r2 = r2.equals(r3);
        if (r2 == 0) goto L_0x00ac;
    L_0x003a:
        r2 = r4.g;
        if (r2 == 0) goto L_0x008f;
    L_0x003e:
        r2 = r4.g;
        r3 = r5.g;
        r2 = r2.equals(r3);
        if (r2 == 0) goto L_0x00ac;
    L_0x0048:
        r2 = r4.j;
        if (r2 == 0) goto L_0x0095;
    L_0x004c:
        r2 = r4.j;
        r3 = r5.j;
        r2 = r2.equals(r3);
        if (r2 == 0) goto L_0x00ac;
    L_0x0056:
        r2 = r4.eI;
        if (r2 == 0) goto L_0x009b;
    L_0x005a:
        r2 = r4.eI;
        r3 = r5.eI;
        r2 = r2.equals(r3);
        if (r2 == 0) goto L_0x00ac;
    L_0x0064:
        r2 = r4.h;
        if (r2 == 0) goto L_0x00a1;
    L_0x0068:
        r2 = r4.h;
        r3 = r5.h;
        r2 = r2.equals(r3);
        if (r2 == 0) goto L_0x00ac;
    L_0x0072:
        r2 = r4.f;
        if (r2 == 0) goto L_0x00a7;
    L_0x0076:
        r2 = r4.f;
        r3 = r5.f;
        r2 = r2.equals(r3);
        if (r2 != 0) goto L_0x0081;
    L_0x0080:
        r0 = r1;
    L_0x0081:
        r1 = r0;
        goto L_0x0005;
    L_0x0083:
        r2 = r5.MC;
        if (r2 == 0) goto L_0x0022;
    L_0x0087:
        goto L_0x0005;
    L_0x0089:
        r2 = r5.i;
        if (r2 == 0) goto L_0x0030;
    L_0x008d:
        goto L_0x0005;
    L_0x008f:
        r2 = r5.g;
        if (r2 == 0) goto L_0x0048;
    L_0x0093:
        goto L_0x0005;
    L_0x0095:
        r2 = r5.j;
        if (r2 == 0) goto L_0x0056;
    L_0x0099:
        goto L_0x0005;
    L_0x009b:
        r2 = r5.eI;
        if (r2 == 0) goto L_0x0064;
    L_0x009f:
        goto L_0x0005;
    L_0x00a1:
        r2 = r5.h;
        if (r2 == 0) goto L_0x0072;
    L_0x00a5:
        goto L_0x0005;
    L_0x00a7:
        r2 = r5.f;
        if (r2 != 0) goto L_0x0080;
    L_0x00ab:
        goto L_0x0081;
    L_0x00ac:
        r0 = r1;
        goto L_0x0004;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.d.c.g.equals(java.lang.Object):boolean");
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((((this.j != null ? this.j.hashCode() : 0) + (((this.i != null ? this.i.hashCode() : 0) + (((this.h != null ? this.h.hashCode() : 0) + (((this.g != null ? this.g.hashCode() : 0) + ((this.f != null ? this.f.hashCode() : 0) * 31)) * 31)) * 31)) * 31)) * 31) + this.MB.hashCode()) * 31) + this.eI.hashCode()) * 31;
        if (this.MC != null) {
            i = this.MC.hashCode();
        }
        return hashCode + i;
    }

    public Bundle iK() {
        Bundle bundle = new Bundle();
        if (!TextUtils.isEmpty(this.f)) {
            bundle.putString("ext_ns", this.f);
        }
        if (!TextUtils.isEmpty(this.i)) {
            bundle.putString("ext_from", this.i);
        }
        if (!TextUtils.isEmpty(this.h)) {
            bundle.putString("ext_to", this.h);
        }
        if (!TextUtils.isEmpty(this.g)) {
            bundle.putString("ext_pkt_id", this.g);
        }
        if (!TextUtils.isEmpty(this.j)) {
            bundle.putString("ext_chid", this.j);
        }
        if (this.MC != null) {
            bundle.putBundle("ext_ERROR", this.MC.iK());
        }
        if (this.MB != null) {
            Parcelable[] parcelableArr = new Bundle[this.MB.size()];
            int i = 0;
            for (j jh : this.MB) {
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

    public String iW() {
        return this.i;
    }

    public c iX() {
        return this.MC;
    }

    public Collection iY() {
        Collection emptyList;
        synchronized (this) {
            emptyList = this.MB == null ? Collections.emptyList() : Collections.unmodifiableList(new ArrayList(this.MB));
        }
        return emptyList;
    }

    public Collection iZ() {
        Collection emptySet;
        synchronized (this) {
            emptySet = this.eI == null ? Collections.emptySet() : Collections.unmodifiableSet(new HashSet(this.eI.keySet()));
        }
        return emptySet;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected java.lang.String ja() {
        /*
        r9 = this;
        r4 = 0;
        monitor-enter(r9);
        r6 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0023 }
        r6.<init>();	 Catch:{ all -> 0x0023 }
        r1 = r9.iY();	 Catch:{ all -> 0x0023 }
        r2 = r1.iterator();	 Catch:{ all -> 0x0023 }
    L_0x000f:
        r1 = r2.hasNext();	 Catch:{ all -> 0x0023 }
        if (r1 == 0) goto L_0x0026;
    L_0x0015:
        r1 = r2.next();	 Catch:{ all -> 0x0023 }
        r1 = (com.xiaomi.d.c.j) r1;	 Catch:{ all -> 0x0023 }
        r1 = r1.d();	 Catch:{ all -> 0x0023 }
        r6.append(r1);	 Catch:{ all -> 0x0023 }
        goto L_0x000f;
    L_0x0023:
        r1 = move-exception;
        monitor-exit(r9);
        throw r1;
    L_0x0026:
        r1 = r9.eI;	 Catch:{ all -> 0x0023 }
        if (r1 == 0) goto L_0x0149;
    L_0x002a:
        r1 = r9.eI;	 Catch:{ all -> 0x0023 }
        r1 = r1.isEmpty();	 Catch:{ all -> 0x0023 }
        if (r1 != 0) goto L_0x0149;
    L_0x0032:
        r1 = "<properties xmlns=\"http://www.jivesoftware.com/xmlns/xmpp/properties\">";
        r6.append(r1);	 Catch:{ all -> 0x0023 }
        r1 = r9.iZ();	 Catch:{ all -> 0x0023 }
        r7 = r1.iterator();	 Catch:{ all -> 0x0023 }
    L_0x003f:
        r1 = r7.hasNext();	 Catch:{ all -> 0x0023 }
        if (r1 == 0) goto L_0x0144;
    L_0x0045:
        r1 = r7.next();	 Catch:{ all -> 0x0023 }
        r1 = (java.lang.String) r1;	 Catch:{ all -> 0x0023 }
        r2 = r9.cB(r1);	 Catch:{ all -> 0x0023 }
        r3 = "<property>";
        r6.append(r3);	 Catch:{ all -> 0x0023 }
        r3 = "<name>";
        r3 = r6.append(r3);	 Catch:{ all -> 0x0023 }
        r1 = com.xiaomi.d.e.g.a(r1);	 Catch:{ all -> 0x0023 }
        r1 = r3.append(r1);	 Catch:{ all -> 0x0023 }
        r3 = "</name>";
        r1.append(r3);	 Catch:{ all -> 0x0023 }
        r1 = "<value type=\"";
        r6.append(r1);	 Catch:{ all -> 0x0023 }
        r1 = r2 instanceof java.lang.Integer;	 Catch:{ all -> 0x0023 }
        if (r1 == 0) goto L_0x0085;
    L_0x0070:
        r1 = "integer\">";
        r1 = r6.append(r1);	 Catch:{ all -> 0x0023 }
        r1 = r1.append(r2);	 Catch:{ all -> 0x0023 }
        r2 = "</value>";
        r1.append(r2);	 Catch:{ all -> 0x0023 }
    L_0x007f:
        r1 = "</property>";
        r6.append(r1);	 Catch:{ all -> 0x0023 }
        goto L_0x003f;
    L_0x0085:
        r1 = r2 instanceof java.lang.Long;	 Catch:{ all -> 0x0023 }
        if (r1 == 0) goto L_0x0099;
    L_0x0089:
        r1 = "long\">";
        r1 = r6.append(r1);	 Catch:{ all -> 0x0023 }
        r1 = r1.append(r2);	 Catch:{ all -> 0x0023 }
        r2 = "</value>";
        r1.append(r2);	 Catch:{ all -> 0x0023 }
        goto L_0x007f;
    L_0x0099:
        r1 = r2 instanceof java.lang.Float;	 Catch:{ all -> 0x0023 }
        if (r1 == 0) goto L_0x00ad;
    L_0x009d:
        r1 = "float\">";
        r1 = r6.append(r1);	 Catch:{ all -> 0x0023 }
        r1 = r1.append(r2);	 Catch:{ all -> 0x0023 }
        r2 = "</value>";
        r1.append(r2);	 Catch:{ all -> 0x0023 }
        goto L_0x007f;
    L_0x00ad:
        r1 = r2 instanceof java.lang.Double;	 Catch:{ all -> 0x0023 }
        if (r1 == 0) goto L_0x00c1;
    L_0x00b1:
        r1 = "double\">";
        r1 = r6.append(r1);	 Catch:{ all -> 0x0023 }
        r1 = r1.append(r2);	 Catch:{ all -> 0x0023 }
        r2 = "</value>";
        r1.append(r2);	 Catch:{ all -> 0x0023 }
        goto L_0x007f;
    L_0x00c1:
        r1 = r2 instanceof java.lang.Boolean;	 Catch:{ all -> 0x0023 }
        if (r1 == 0) goto L_0x00d5;
    L_0x00c5:
        r1 = "boolean\">";
        r1 = r6.append(r1);	 Catch:{ all -> 0x0023 }
        r1 = r1.append(r2);	 Catch:{ all -> 0x0023 }
        r2 = "</value>";
        r1.append(r2);	 Catch:{ all -> 0x0023 }
        goto L_0x007f;
    L_0x00d5:
        r1 = r2 instanceof java.lang.String;	 Catch:{ all -> 0x0023 }
        if (r1 == 0) goto L_0x00ef;
    L_0x00d9:
        r1 = "string\">";
        r6.append(r1);	 Catch:{ all -> 0x0023 }
        r0 = r2;
        r0 = (java.lang.String) r0;	 Catch:{ all -> 0x0023 }
        r1 = r0;
        r1 = com.xiaomi.d.e.g.a(r1);	 Catch:{ all -> 0x0023 }
        r6.append(r1);	 Catch:{ all -> 0x0023 }
        r1 = "</value>";
        r6.append(r1);	 Catch:{ all -> 0x0023 }
        goto L_0x007f;
    L_0x00ef:
        r3 = new java.io.ByteArrayOutputStream;	 Catch:{ Exception -> 0x0121, all -> 0x0137 }
        r3.<init>();	 Catch:{ Exception -> 0x0121, all -> 0x0137 }
        r5 = new java.io.ObjectOutputStream;	 Catch:{ Exception -> 0x0158, all -> 0x016c }
        r5.<init>(r3);	 Catch:{ Exception -> 0x0158, all -> 0x016c }
        r5.writeObject(r2);	 Catch:{ Exception -> 0x015e, all -> 0x014f }
        r1 = "java-object\">";
        r6.append(r1);	 Catch:{ Exception -> 0x015e, all -> 0x014f }
        r1 = r3.toByteArray();	 Catch:{ Exception -> 0x015e, all -> 0x014f }
        r1 = com.xiaomi.d.e.g.a(r1);	 Catch:{ Exception -> 0x015e, all -> 0x014f }
        r1 = r6.append(r1);	 Catch:{ Exception -> 0x015e, all -> 0x014f }
        r2 = "</value>";
        r1.append(r2);	 Catch:{ Exception -> 0x015e, all -> 0x014f }
        if (r5 == 0) goto L_0x0117;
    L_0x0114:
        r5.close();	 Catch:{ Exception -> 0x0164 }
    L_0x0117:
        if (r3 == 0) goto L_0x007f;
    L_0x0119:
        r3.close();	 Catch:{ Exception -> 0x011e }
        goto L_0x007f;
    L_0x011e:
        r1 = move-exception;
        goto L_0x007f;
    L_0x0121:
        r1 = move-exception;
        r2 = r4;
        r3 = r1;
        r1 = r4;
    L_0x0125:
        r3.printStackTrace();	 Catch:{ all -> 0x0152 }
        if (r2 == 0) goto L_0x012d;
    L_0x012a:
        r2.close();	 Catch:{ Exception -> 0x0166 }
    L_0x012d:
        if (r1 == 0) goto L_0x007f;
    L_0x012f:
        r1.close();	 Catch:{ Exception -> 0x0134 }
        goto L_0x007f;
    L_0x0134:
        r1 = move-exception;
        goto L_0x007f;
    L_0x0137:
        r1 = move-exception;
        r3 = r4;
    L_0x0139:
        if (r4 == 0) goto L_0x013e;
    L_0x013b:
        r4.close();	 Catch:{ Exception -> 0x0168 }
    L_0x013e:
        if (r3 == 0) goto L_0x0143;
    L_0x0140:
        r3.close();	 Catch:{ Exception -> 0x016a }
    L_0x0143:
        throw r1;	 Catch:{ all -> 0x0023 }
    L_0x0144:
        r1 = "</properties>";
        r6.append(r1);	 Catch:{ all -> 0x0023 }
    L_0x0149:
        r1 = r6.toString();	 Catch:{ all -> 0x0023 }
        monitor-exit(r9);
        return r1;
    L_0x014f:
        r1 = move-exception;
        r4 = r5;
        goto L_0x0139;
    L_0x0152:
        r3 = move-exception;
        r4 = r2;
        r8 = r3;
        r3 = r1;
        r1 = r8;
        goto L_0x0139;
    L_0x0158:
        r1 = move-exception;
        r2 = r4;
        r8 = r3;
        r3 = r1;
        r1 = r8;
        goto L_0x0125;
    L_0x015e:
        r1 = move-exception;
        r2 = r5;
        r8 = r3;
        r3 = r1;
        r1 = r8;
        goto L_0x0125;
    L_0x0164:
        r1 = move-exception;
        goto L_0x0117;
    L_0x0166:
        r2 = move-exception;
        goto L_0x012d;
    L_0x0168:
        r2 = move-exception;
        goto L_0x013e;
    L_0x016a:
        r2 = move-exception;
        goto L_0x0143;
    L_0x016c:
        r1 = move-exception;
        goto L_0x0139;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.d.c.g.ja():java.lang.String");
    }

    public String jb() {
        return this.f;
    }

    public String k() {
        if ("ID_NOT_AVAILABLE".equals(this.g)) {
            return null;
        }
        if (this.g == null) {
            this.g = j();
        }
        return this.g;
    }

    public String l() {
        return this.j;
    }

    public String m() {
        return this.h;
    }

    public j x(String str, String str2) {
        for (j jVar : this.MB) {
            if ((str2 == null || str2.equals(jVar.b())) && str.equals(jVar.a())) {
                return jVar;
            }
        }
        return null;
    }
}
