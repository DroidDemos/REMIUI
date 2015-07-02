package com.xiaomi.a.a.a.a;

import com.alipay.mobilesecuritysdk.constant.ConfigConstant;
import com.alipay.sdk.protocol.WindowData;
import com.ta.utdid2.android.utils.Base64;
import com.ta.utdid2.core.persistent.TransactionXMLFile;
import java.io.Serializable;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.apache.thrift.TBase;
import org.apache.thrift.meta_data.FieldMetaData;
import org.apache.thrift.meta_data.FieldValueMetaData;
import org.apache.thrift.meta_data.SetMetaData;
import org.apache.thrift.meta_data.StructMetaData;
import org.apache.thrift.protocol.TProtocolException;
import org.apache.thrift.protocol.b;
import org.apache.thrift.protocol.c;
import org.apache.thrift.protocol.e;

public class f implements Serializable, Cloneable, TBase {
    public static final Map fe;
    private static final org.apache.thrift.protocol.f ff;
    private static final e fg;
    private static final e fh;
    private static final e fi;
    private static final e fp;
    private static final e fq;
    private static final e fr;
    private static final e fs;
    private String j;
    private String k;
    private String l;
    private String m;
    private String n;
    private e o;
    private Set p;

    public enum a {
        CATEGORY((short) 1, "category"),
        UUID((short) 2, "uuid"),
        VERSION((short) 3, "version"),
        NETWORK((short) 4, "network"),
        RID((short) 5, "rid"),
        LOCATION((short) 6, "location"),
        HOST_INFO((short) 7, "host_info");
        
        private static final Map h;
        private final short i;
        private final String j;

        static {
            h = new HashMap();
            Iterator it = EnumSet.allOf(a.class).iterator();
            while (it.hasNext()) {
                a aVar = (a) it.next();
                h.put(aVar.a(), aVar);
            }
        }

        private a(short s, String str) {
            this.i = s;
            this.j = str;
        }

        public String a() {
            return this.j;
        }
    }

    static {
        ff = new org.apache.thrift.protocol.f("Passport");
        fg = new e("category", (byte) 11, (short) 1);
        fh = new e("uuid", (byte) 11, (short) 2);
        fi = new e("version", (byte) 11, (short) 3);
        fp = new e("network", (byte) 11, (short) 4);
        fq = new e("rid", (byte) 11, (short) 5);
        fr = new e("location", (byte) 12, (short) 6);
        fs = new e("host_info", (byte) 14, (short) 7);
        Map enumMap = new EnumMap(a.class);
        enumMap.put(a.CATEGORY, new FieldMetaData("category", (byte) 1, new FieldValueMetaData((byte) 11)));
        enumMap.put(a.UUID, new FieldMetaData("uuid", (byte) 1, new FieldValueMetaData((byte) 11)));
        enumMap.put(a.VERSION, new FieldMetaData("version", (byte) 1, new FieldValueMetaData((byte) 11)));
        enumMap.put(a.NETWORK, new FieldMetaData("network", (byte) 1, new FieldValueMetaData((byte) 11)));
        enumMap.put(a.RID, new FieldMetaData("rid", (byte) 1, new FieldValueMetaData((byte) 11)));
        enumMap.put(a.LOCATION, new FieldMetaData("location", (byte) 2, new StructMetaData((byte) 12, e.class)));
        enumMap.put(a.HOST_INFO, new FieldMetaData("host_info", (byte) 2, new SetMetaData((byte) 14, new StructMetaData((byte) 12, g.class))));
        fe = Collections.unmodifiableMap(enumMap);
        FieldMetaData.a(f.class, fe);
    }

    public f() {
        this.j = ConfigConstant.WIRELESS_FILENAME;
    }

    public void a(b bVar) {
        bVar.bq();
        while (true) {
            e br = bVar.br();
            if (br.b == (byte) 0) {
                bVar.am();
                am();
                return;
            }
            switch (br.An) {
                case TransactionXMLFile.MODE_WORLD_READABLE /*1*/:
                    if (br.b != (byte) 11) {
                        c.a(bVar, br.b);
                        break;
                    } else {
                        this.j = bVar.bD();
                        break;
                    }
                case TransactionXMLFile.MODE_WORLD_WRITEABLE /*2*/:
                    if (br.b != (byte) 11) {
                        c.a(bVar, br.b);
                        break;
                    } else {
                        this.k = bVar.bD();
                        break;
                    }
                case WindowData.d /*3*/:
                    if (br.b != (byte) 11) {
                        c.a(bVar, br.b);
                        break;
                    } else {
                        this.l = bVar.bD();
                        break;
                    }
                case Base64.CRLF /*4*/:
                    if (br.b != (byte) 11) {
                        c.a(bVar, br.b);
                        break;
                    } else {
                        this.m = bVar.bD();
                        break;
                    }
                case WindowData.f /*5*/:
                    if (br.b != (byte) 11) {
                        c.a(bVar, br.b);
                        break;
                    } else {
                        this.n = bVar.bD();
                        break;
                    }
                case WindowData.g /*6*/:
                    if (br.b != (byte) 12) {
                        c.a(bVar, br.b);
                        break;
                    }
                    this.o = new e();
                    this.o.a(bVar);
                    break;
                case WindowData.h /*7*/:
                    if (br.b != (byte) 14) {
                        c.a(bVar, br.b);
                        break;
                    }
                    org.apache.thrift.protocol.a bv = bVar.bv();
                    this.p = new HashSet(bv.b * 2);
                    for (int i = 0; i < bv.b; i++) {
                        g gVar = new g();
                        gVar.a(bVar);
                        this.p.add(gVar);
                    }
                    bVar.bw();
                    break;
                default:
                    c.a(bVar, br.b);
                    break;
            }
            bVar.j();
        }
    }

    public boolean a() {
        return this.j != null;
    }

    public boolean a(f fVar) {
        if (fVar != null) {
            boolean a = a();
            boolean a2 = fVar.a();
            if (!(a || a2) || (a && a2 && this.j.equals(fVar.j))) {
                a = b();
                a2 = fVar.b();
                if (!(a || a2) || (a && a2 && this.k.equals(fVar.k))) {
                    a = c();
                    a2 = fVar.c();
                    if (!(a || a2) || (a && a2 && this.l.equals(fVar.l))) {
                        a = d();
                        a2 = fVar.d();
                        if (!(a || a2) || (a && a2 && this.m.equals(fVar.m))) {
                            a = e();
                            a2 = fVar.e();
                            if (!(a || a2) || (a && a2 && this.n.equals(fVar.n))) {
                                a = f();
                                a2 = fVar.f();
                                if (!(a || a2) || (a && a2 && this.o.a(fVar.o))) {
                                    a = g();
                                    a2 = fVar.g();
                                    if (!(a || a2) || (a && a2 && this.p.equals(fVar.p))) {
                                        return true;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    public void am() {
        if (this.j == null) {
            throw new TProtocolException("Required field 'category' was not present! Struct: " + toString());
        } else if (this.k == null) {
            throw new TProtocolException("Required field 'uuid' was not present! Struct: " + toString());
        } else if (this.l == null) {
            throw new TProtocolException("Required field 'version' was not present! Struct: " + toString());
        } else if (this.m == null) {
            throw new TProtocolException("Required field 'network' was not present! Struct: " + toString());
        } else if (this.n == null) {
            throw new TProtocolException("Required field 'rid' was not present! Struct: " + toString());
        }
    }

    public int b(f fVar) {
        if (!getClass().equals(fVar.getClass())) {
            return getClass().getName().compareTo(fVar.getClass().getName());
        }
        int compareTo = Boolean.valueOf(a()).compareTo(Boolean.valueOf(fVar.a()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (a()) {
            compareTo = org.apache.thrift.b.f(this.j, fVar.j);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(b()).compareTo(Boolean.valueOf(fVar.b()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (b()) {
            compareTo = org.apache.thrift.b.f(this.k, fVar.k);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(c()).compareTo(Boolean.valueOf(fVar.c()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (c()) {
            compareTo = org.apache.thrift.b.f(this.l, fVar.l);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(d()).compareTo(Boolean.valueOf(fVar.d()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (d()) {
            compareTo = org.apache.thrift.b.f(this.m, fVar.m);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(e()).compareTo(Boolean.valueOf(fVar.e()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (e()) {
            compareTo = org.apache.thrift.b.f(this.n, fVar.n);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(f()).compareTo(Boolean.valueOf(fVar.f()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (f()) {
            compareTo = org.apache.thrift.b.a(this.o, fVar.o);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(g()).compareTo(Boolean.valueOf(fVar.g()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (g()) {
            compareTo = org.apache.thrift.b.a(this.p, fVar.p);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        return 0;
    }

    public void b(b bVar) {
        am();
        bVar.a(ff);
        if (this.j != null) {
            bVar.a(fg);
            bVar.a(this.j);
            bVar.b();
        }
        if (this.k != null) {
            bVar.a(fh);
            bVar.a(this.k);
            bVar.b();
        }
        if (this.l != null) {
            bVar.a(fi);
            bVar.a(this.l);
            bVar.b();
        }
        if (this.m != null) {
            bVar.a(fp);
            bVar.a(this.m);
            bVar.b();
        }
        if (this.n != null) {
            bVar.a(fq);
            bVar.a(this.n);
            bVar.b();
        }
        if (this.o != null && f()) {
            bVar.a(fr);
            this.o.b(bVar);
            bVar.b();
        }
        if (this.p != null && g()) {
            bVar.a(fs);
            bVar.a(new org.apache.thrift.protocol.a((byte) 12, this.p.size()));
            for (g b : this.p) {
                b.b(bVar);
            }
            bVar.f();
            bVar.b();
        }
        bVar.c();
        bVar.a();
    }

    public boolean b() {
        return this.k != null;
    }

    public boolean c() {
        return this.l != null;
    }

    public /* synthetic */ int compareTo(Object obj) {
        return b((f) obj);
    }

    public boolean d() {
        return this.m != null;
    }

    public boolean e() {
        return this.n != null;
    }

    public boolean equals(Object obj) {
        return (obj != null && (obj instanceof f)) ? a((f) obj) : false;
    }

    public boolean f() {
        return this.o != null;
    }

    public boolean g() {
        return this.p != null;
    }

    public int hashCode() {
        return 0;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("Passport(");
        stringBuilder.append("category:");
        if (this.j == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.j);
        }
        stringBuilder.append(", ");
        stringBuilder.append("uuid:");
        if (this.k == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.k);
        }
        stringBuilder.append(", ");
        stringBuilder.append("version:");
        if (this.l == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.l);
        }
        stringBuilder.append(", ");
        stringBuilder.append("network:");
        if (this.m == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.m);
        }
        stringBuilder.append(", ");
        stringBuilder.append("rid:");
        if (this.n == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.n);
        }
        if (f()) {
            stringBuilder.append(", ");
            stringBuilder.append("location:");
            if (this.o == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.o);
            }
        }
        if (g()) {
            stringBuilder.append(", ");
            stringBuilder.append("host_info:");
            if (this.p == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.p);
            }
        }
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
