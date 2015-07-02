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
import org.apache.thrift.protocol.c;
import org.apache.thrift.protocol.e;
import org.apache.thrift.protocol.f;

public class b implements Serializable, Cloneable, TBase {
    private static final e fT;
    private static final e fU;
    private static final e fV;
    public static final Map fe;
    private static final f ff;
    private static final e fg;
    private static final e fh;
    private static final e fi;
    private static final e fp;
    private static final e fq;
    private static final e fr;
    private static final e fs;
    private String m;
    private String n;
    private String o;
    private String p;
    private String q;
    private e r;
    private Set s;
    private String t;
    private String u;
    private String v;

    public enum a {
        CATEGORY((short) 1, "category"),
        UUID((short) 2, "uuid"),
        VERSION((short) 3, "version"),
        NETWORK((short) 4, "network"),
        CLIENT_IP((short) 5, "client_ip"),
        LOCATION((short) 6, "location"),
        HOST_INFO((short) 7, "host_info"),
        VERSION_TYPE((short) 8, "version_type"),
        APP_NAME((short) 9, "app_name"),
        APP_VERSION((short) 10, "app_version");
        
        private static final Map ek;
        private final short l;
        private final String m;

        static {
            ek = new HashMap();
            Iterator it = EnumSet.allOf(a.class).iterator();
            while (it.hasNext()) {
                a aVar = (a) it.next();
                ek.put(aVar.a(), aVar);
            }
        }

        private a(short s, String str) {
            this.l = s;
            this.m = str;
        }

        public String a() {
            return this.m;
        }
    }

    static {
        ff = new f("HttpApi");
        fg = new e("category", (byte) 11, (short) 1);
        fh = new e("uuid", (byte) 11, (short) 2);
        fi = new e("version", (byte) 11, (short) 3);
        fp = new e("network", (byte) 11, (short) 4);
        fq = new e("client_ip", (byte) 11, (short) 5);
        fr = new e("location", (byte) 12, (short) 6);
        fs = new e("host_info", (byte) 14, (short) 7);
        fT = new e("version_type", (byte) 11, (short) 8);
        fU = new e("app_name", (byte) 11, (short) 9);
        fV = new e("app_version", (byte) 11, (short) 10);
        Map enumMap = new EnumMap(a.class);
        enumMap.put(a.CATEGORY, new FieldMetaData("category", (byte) 1, new FieldValueMetaData((byte) 11)));
        enumMap.put(a.UUID, new FieldMetaData("uuid", (byte) 1, new FieldValueMetaData((byte) 11)));
        enumMap.put(a.VERSION, new FieldMetaData("version", (byte) 1, new FieldValueMetaData((byte) 11)));
        enumMap.put(a.NETWORK, new FieldMetaData("network", (byte) 1, new FieldValueMetaData((byte) 11)));
        enumMap.put(a.CLIENT_IP, new FieldMetaData("client_ip", (byte) 2, new FieldValueMetaData((byte) 11)));
        enumMap.put(a.LOCATION, new FieldMetaData("location", (byte) 2, new StructMetaData((byte) 12, e.class)));
        enumMap.put(a.HOST_INFO, new FieldMetaData("host_info", (byte) 2, new SetMetaData((byte) 14, new StructMetaData((byte) 12, a.class))));
        enumMap.put(a.VERSION_TYPE, new FieldMetaData("version_type", (byte) 2, new FieldValueMetaData((byte) 11)));
        enumMap.put(a.APP_NAME, new FieldMetaData("app_name", (byte) 2, new FieldValueMetaData((byte) 11)));
        enumMap.put(a.APP_VERSION, new FieldMetaData("app_version", (byte) 2, new FieldValueMetaData((byte) 11)));
        fe = Collections.unmodifiableMap(enumMap);
        FieldMetaData.a(b.class, fe);
    }

    public b() {
        this.m = ConfigConstant.WIRELESS_FILENAME;
        this.t = ConfigConstant.WIRELESS_FILENAME;
        this.u = ConfigConstant.WIRELESS_FILENAME;
        this.v = ConfigConstant.WIRELESS_FILENAME;
    }

    public b A(String str) {
        this.u = str;
        return this;
    }

    public b B(String str) {
        this.v = str;
        return this;
    }

    public void a(org.apache.thrift.protocol.b bVar) {
        bVar.bq();
        while (true) {
            e br = bVar.br();
            if (br.b == (byte) 0) {
                bVar.am();
                ao();
                return;
            }
            switch (br.An) {
                case TransactionXMLFile.MODE_WORLD_READABLE /*1*/:
                    if (br.b != (byte) 11) {
                        c.a(bVar, br.b);
                        break;
                    } else {
                        this.m = bVar.bD();
                        break;
                    }
                case TransactionXMLFile.MODE_WORLD_WRITEABLE /*2*/:
                    if (br.b != (byte) 11) {
                        c.a(bVar, br.b);
                        break;
                    } else {
                        this.n = bVar.bD();
                        break;
                    }
                case WindowData.d /*3*/:
                    if (br.b != (byte) 11) {
                        c.a(bVar, br.b);
                        break;
                    } else {
                        this.o = bVar.bD();
                        break;
                    }
                case Base64.CRLF /*4*/:
                    if (br.b != (byte) 11) {
                        c.a(bVar, br.b);
                        break;
                    } else {
                        this.p = bVar.bD();
                        break;
                    }
                case WindowData.f /*5*/:
                    if (br.b != (byte) 11) {
                        c.a(bVar, br.b);
                        break;
                    } else {
                        this.q = bVar.bD();
                        break;
                    }
                case WindowData.g /*6*/:
                    if (br.b != (byte) 12) {
                        c.a(bVar, br.b);
                        break;
                    }
                    this.r = new e();
                    this.r.a(bVar);
                    break;
                case WindowData.h /*7*/:
                    if (br.b != (byte) 14) {
                        c.a(bVar, br.b);
                        break;
                    }
                    org.apache.thrift.protocol.a bv = bVar.bv();
                    this.s = new HashSet(bv.b * 2);
                    for (int i = 0; i < bv.b; i++) {
                        a aVar = new a();
                        aVar.a(bVar);
                        this.s.add(aVar);
                    }
                    bVar.bw();
                    break;
                case Base64.URL_SAFE /*8*/:
                    if (br.b != (byte) 11) {
                        c.a(bVar, br.b);
                        break;
                    } else {
                        this.t = bVar.bD();
                        break;
                    }
                case WindowData.j /*9*/:
                    if (br.b != (byte) 11) {
                        c.a(bVar, br.b);
                        break;
                    } else {
                        this.u = bVar.bD();
                        break;
                    }
                case WindowData.k /*10*/:
                    if (br.b != (byte) 11) {
                        c.a(bVar, br.b);
                        break;
                    } else {
                        this.v = bVar.bD();
                        break;
                    }
                default:
                    c.a(bVar, br.b);
                    break;
            }
            bVar.j();
        }
    }

    public boolean a() {
        return this.m != null;
    }

    public boolean an() {
        return this.s != null;
    }

    public void ao() {
        if (this.m == null) {
            throw new TProtocolException("Required field 'category' was not present! Struct: " + toString());
        } else if (this.n == null) {
            throw new TProtocolException("Required field 'uuid' was not present! Struct: " + toString());
        } else if (this.o == null) {
            throw new TProtocolException("Required field 'version' was not present! Struct: " + toString());
        } else if (this.p == null) {
            throw new TProtocolException("Required field 'network' was not present! Struct: " + toString());
        }
    }

    public void b(org.apache.thrift.protocol.b bVar) {
        ao();
        bVar.a(ff);
        if (this.m != null) {
            bVar.a(fg);
            bVar.a(this.m);
            bVar.b();
        }
        if (this.n != null) {
            bVar.a(fh);
            bVar.a(this.n);
            bVar.b();
        }
        if (this.o != null) {
            bVar.a(fi);
            bVar.a(this.o);
            bVar.b();
        }
        if (this.p != null) {
            bVar.a(fp);
            bVar.a(this.p);
            bVar.b();
        }
        if (this.q != null && e()) {
            bVar.a(fq);
            bVar.a(this.q);
            bVar.b();
        }
        if (this.r != null && f()) {
            bVar.a(fr);
            this.r.b(bVar);
            bVar.b();
        }
        if (this.s != null && an()) {
            bVar.a(fs);
            bVar.a(new org.apache.thrift.protocol.a((byte) 12, this.s.size()));
            for (a b : this.s) {
                b.b(bVar);
            }
            bVar.f();
            bVar.b();
        }
        if (this.t != null && i()) {
            bVar.a(fT);
            bVar.a(this.t);
            bVar.b();
        }
        if (this.u != null && j()) {
            bVar.a(fU);
            bVar.a(this.u);
            bVar.b();
        }
        if (this.v != null && k()) {
            bVar.a(fV);
            bVar.a(this.v);
            bVar.b();
        }
        bVar.c();
        bVar.a();
    }

    public boolean b() {
        return this.n != null;
    }

    public boolean b(b bVar) {
        if (bVar != null) {
            boolean a = a();
            boolean a2 = bVar.a();
            if (!(a || a2) || (a && a2 && this.m.equals(bVar.m))) {
                a = b();
                a2 = bVar.b();
                if (!(a || a2) || (a && a2 && this.n.equals(bVar.n))) {
                    a = c();
                    a2 = bVar.c();
                    if (!(a || a2) || (a && a2 && this.o.equals(bVar.o))) {
                        a = d();
                        a2 = bVar.d();
                        if (!(a || a2) || (a && a2 && this.p.equals(bVar.p))) {
                            a = e();
                            a2 = bVar.e();
                            if (!(a || a2) || (a && a2 && this.q.equals(bVar.q))) {
                                a = f();
                                a2 = bVar.f();
                                if (!(a || a2) || (a && a2 && this.r.a(bVar.r))) {
                                    a = an();
                                    a2 = bVar.an();
                                    if (!(a || a2) || (a && a2 && this.s.equals(bVar.s))) {
                                        a = i();
                                        a2 = bVar.i();
                                        if (!(a || a2) || (a && a2 && this.t.equals(bVar.t))) {
                                            a = j();
                                            a2 = bVar.j();
                                            if (!(a || a2) || (a && a2 && this.u.equals(bVar.u))) {
                                                a = k();
                                                a2 = bVar.k();
                                                if (!(a || a2) || (a && a2 && this.v.equals(bVar.v))) {
                                                    return true;
                                                }
                                            }
                                        }
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

    public int c(b bVar) {
        if (!getClass().equals(bVar.getClass())) {
            return getClass().getName().compareTo(bVar.getClass().getName());
        }
        int compareTo = Boolean.valueOf(a()).compareTo(Boolean.valueOf(bVar.a()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (a()) {
            compareTo = org.apache.thrift.b.f(this.m, bVar.m);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(b()).compareTo(Boolean.valueOf(bVar.b()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (b()) {
            compareTo = org.apache.thrift.b.f(this.n, bVar.n);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(c()).compareTo(Boolean.valueOf(bVar.c()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (c()) {
            compareTo = org.apache.thrift.b.f(this.o, bVar.o);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(d()).compareTo(Boolean.valueOf(bVar.d()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (d()) {
            compareTo = org.apache.thrift.b.f(this.p, bVar.p);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(e()).compareTo(Boolean.valueOf(bVar.e()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (e()) {
            compareTo = org.apache.thrift.b.f(this.q, bVar.q);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(f()).compareTo(Boolean.valueOf(bVar.f()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (f()) {
            compareTo = org.apache.thrift.b.a(this.r, bVar.r);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(an()).compareTo(Boolean.valueOf(bVar.an()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (an()) {
            compareTo = org.apache.thrift.b.a(this.s, bVar.s);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(i()).compareTo(Boolean.valueOf(bVar.i()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (i()) {
            compareTo = org.apache.thrift.b.f(this.t, bVar.t);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(j()).compareTo(Boolean.valueOf(bVar.j()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (j()) {
            compareTo = org.apache.thrift.b.f(this.u, bVar.u);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(k()).compareTo(Boolean.valueOf(bVar.k()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (k()) {
            compareTo = org.apache.thrift.b.f(this.v, bVar.v);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        return 0;
    }

    public b c(e eVar) {
        this.r = eVar;
        return this;
    }

    public void c(a aVar) {
        if (this.s == null) {
            this.s = new HashSet();
        }
        this.s.add(aVar);
    }

    public boolean c() {
        return this.o != null;
    }

    public /* synthetic */ int compareTo(Object obj) {
        return c((b) obj);
    }

    public boolean d() {
        return this.p != null;
    }

    public boolean e() {
        return this.q != null;
    }

    public boolean equals(Object obj) {
        return (obj != null && (obj instanceof b)) ? b((b) obj) : false;
    }

    public boolean f() {
        return this.r != null;
    }

    public int g() {
        return this.s == null ? 0 : this.s.size();
    }

    public int hashCode() {
        return 0;
    }

    public boolean i() {
        return this.t != null;
    }

    public boolean j() {
        return this.u != null;
    }

    public boolean k() {
        return this.v != null;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("HttpApi(");
        stringBuilder.append("category:");
        if (this.m == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.m);
        }
        stringBuilder.append(", ");
        stringBuilder.append("uuid:");
        if (this.n == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.n);
        }
        stringBuilder.append(", ");
        stringBuilder.append("version:");
        if (this.o == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.o);
        }
        stringBuilder.append(", ");
        stringBuilder.append("network:");
        if (this.p == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.p);
        }
        if (e()) {
            stringBuilder.append(", ");
            stringBuilder.append("client_ip:");
            if (this.q == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.q);
            }
        }
        if (f()) {
            stringBuilder.append(", ");
            stringBuilder.append("location:");
            if (this.r == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.r);
            }
        }
        if (an()) {
            stringBuilder.append(", ");
            stringBuilder.append("host_info:");
            if (this.s == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.s);
            }
        }
        if (i()) {
            stringBuilder.append(", ");
            stringBuilder.append("version_type:");
            if (this.t == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.t);
            }
        }
        if (j()) {
            stringBuilder.append(", ");
            stringBuilder.append("app_name:");
            if (this.u == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.u);
            }
        }
        if (k()) {
            stringBuilder.append(", ");
            stringBuilder.append("app_version:");
            if (this.v == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.v);
            }
        }
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    public b u(String str) {
        this.m = str;
        return this;
    }

    public b v(String str) {
        this.n = str;
        return this;
    }

    public b w(String str) {
        this.o = str;
        return this;
    }

    public b x(String str) {
        this.p = str;
        return this;
    }

    public b y(String str) {
        this.q = str;
        return this;
    }

    public b z(String str) {
        this.t = str;
        return this;
    }
}
