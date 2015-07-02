package com.xiaomi.e.a;

import com.alipay.sdk.cons.MiniDefine;
import com.alipay.sdk.protocol.WindowData;
import com.ta.utdid2.android.utils.Base64;
import com.ta.utdid2.core.persistent.TransactionXMLFile;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.util.BitSet;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.apache.thrift.TBase;
import org.apache.thrift.meta_data.EnumMetaData;
import org.apache.thrift.meta_data.FieldMetaData;
import org.apache.thrift.meta_data.FieldValueMetaData;
import org.apache.thrift.meta_data.StructMetaData;
import org.apache.thrift.protocol.TProtocolException;
import org.apache.thrift.protocol.b;
import org.apache.thrift.protocol.c;
import org.apache.thrift.protocol.e;
import org.apache.thrift.protocol.f;

public class h implements Serializable, Cloneable, TBase {
    public static final Map dS;
    private static final e fU;
    private static final e fV;
    private static final e ya;
    private static final e yb;
    private static final e yc;
    private static final e yd;
    private static final e ye;
    private static final e yf;
    private static final f yo;
    public a a;
    public boolean b;
    public boolean c;
    public ByteBuffer d;
    public String e;
    public String f;
    public d g;
    public c h;
    private BitSet s;

    public enum a {
        ACTION((short) 1, MiniDefine.i),
        ENCRYPT_ACTION((short) 2, "encryptAction"),
        IS_REQUEST((short) 3, "isRequest"),
        PUSH_ACTION((short) 4, "pushAction"),
        APPID((short) 5, "appid"),
        PACKAGE_NAME((short) 6, "packageName"),
        TARGET((short) 7, "target"),
        META_INFO((short) 8, "metaInfo");
        
        private static final Map dS;
        private final short j;
        private final String k;

        static {
            dS = new HashMap();
            Iterator it = EnumSet.allOf(a.class).iterator();
            while (it.hasNext()) {
                a aVar = (a) it.next();
                dS.put(aVar.a(), aVar);
            }
        }

        private a(short s, String str) {
            this.j = s;
            this.k = str;
        }

        public String a() {
            return this.k;
        }
    }

    static {
        yo = new f("XmPushActionContainer");
        fU = new e(MiniDefine.i, (byte) 8, (short) 1);
        fV = new e("encryptAction", (byte) 2, (short) 2);
        ya = new e("isRequest", (byte) 2, (short) 3);
        yb = new e("pushAction", (byte) 11, (short) 4);
        yc = new e("appid", (byte) 11, (short) 5);
        yd = new e("packageName", (byte) 11, (short) 6);
        ye = new e("target", (byte) 12, (short) 7);
        yf = new e("metaInfo", (byte) 12, (short) 8);
        Map enumMap = new EnumMap(a.class);
        enumMap.put(a.ACTION, new FieldMetaData(MiniDefine.i, (byte) 1, new EnumMetaData((byte) 16, a.class)));
        enumMap.put(a.ENCRYPT_ACTION, new FieldMetaData("encryptAction", (byte) 1, new FieldValueMetaData((byte) 2)));
        enumMap.put(a.IS_REQUEST, new FieldMetaData("isRequest", (byte) 1, new FieldValueMetaData((byte) 2)));
        enumMap.put(a.PUSH_ACTION, new FieldMetaData("pushAction", (byte) 1, new FieldValueMetaData((byte) 11)));
        enumMap.put(a.APPID, new FieldMetaData("appid", (byte) 2, new FieldValueMetaData((byte) 11)));
        enumMap.put(a.PACKAGE_NAME, new FieldMetaData("packageName", (byte) 2, new FieldValueMetaData((byte) 11)));
        enumMap.put(a.TARGET, new FieldMetaData("target", (byte) 1, new StructMetaData((byte) 12, d.class)));
        enumMap.put(a.META_INFO, new FieldMetaData("metaInfo", (byte) 2, new StructMetaData((byte) 12, c.class)));
        dS = Collections.unmodifiableMap(enumMap);
        FieldMetaData.a(h.class, dS);
    }

    public h() {
        this.s = new BitSet(2);
        this.b = true;
        this.c = true;
    }

    public h A(boolean z) {
        this.c = z;
        f(true);
        return this;
    }

    public h a(d dVar) {
        this.g = dVar;
        return this;
    }

    public void a(b bVar) {
        bVar.bq();
        while (true) {
            e br = bVar.br();
            if (br.b == (byte) 0) {
                bVar.am();
                if (!d()) {
                    throw new TProtocolException("Required field 'encryptAction' was not found in serialized data! Struct: " + toString());
                } else if (e()) {
                    gm();
                    return;
                } else {
                    throw new TProtocolException("Required field 'isRequest' was not found in serialized data! Struct: " + toString());
                }
            }
            switch (br.An) {
                case TransactionXMLFile.MODE_WORLD_READABLE /*1*/:
                    if (br.b != (byte) 8) {
                        c.a(bVar, br.b);
                        break;
                    } else {
                        this.a = a.O(bVar.bA());
                        break;
                    }
                case TransactionXMLFile.MODE_WORLD_WRITEABLE /*2*/:
                    if (br.b != (byte) 2) {
                        c.a(bVar, br.b);
                        break;
                    }
                    this.b = bVar.bx();
                    b(true);
                    break;
                case WindowData.d /*3*/:
                    if (br.b != (byte) 2) {
                        c.a(bVar, br.b);
                        break;
                    }
                    this.c = bVar.bx();
                    f(true);
                    break;
                case Base64.CRLF /*4*/:
                    if (br.b != (byte) 11) {
                        c.a(bVar, br.b);
                        break;
                    } else {
                        this.d = bVar.bE();
                        break;
                    }
                case WindowData.f /*5*/:
                    if (br.b != (byte) 11) {
                        c.a(bVar, br.b);
                        break;
                    } else {
                        this.e = bVar.bD();
                        break;
                    }
                case WindowData.g /*6*/:
                    if (br.b != (byte) 11) {
                        c.a(bVar, br.b);
                        break;
                    } else {
                        this.f = bVar.bD();
                        break;
                    }
                case WindowData.h /*7*/:
                    if (br.b != (byte) 12) {
                        c.a(bVar, br.b);
                        break;
                    }
                    this.g = new d();
                    this.g.a(bVar);
                    break;
                case Base64.URL_SAFE /*8*/:
                    if (br.b != (byte) 12) {
                        c.a(bVar, br.b);
                        break;
                    }
                    this.h = new c();
                    this.h.a(bVar);
                    break;
                default:
                    c.a(bVar, br.b);
                    break;
            }
            bVar.j();
        }
    }

    public h b(a aVar) {
        this.a = aVar;
        return this;
    }

    public void b(b bVar) {
        gm();
        bVar.a(yo);
        if (this.a != null) {
            bVar.a(fU);
            bVar.a(this.a.a());
            bVar.b();
        }
        bVar.a(fV);
        bVar.a(this.b);
        bVar.b();
        bVar.a(ya);
        bVar.a(this.c);
        bVar.b();
        if (this.d != null) {
            bVar.a(yb);
            bVar.f(this.d);
            bVar.b();
        }
        if (this.e != null && i()) {
            bVar.a(yc);
            bVar.a(this.e);
            bVar.b();
        }
        if (this.f != null && k()) {
            bVar.a(yd);
            bVar.a(this.f);
            bVar.b();
        }
        if (this.g != null) {
            bVar.a(ye);
            this.g.b(bVar);
            bVar.b();
        }
        if (this.h != null && gl()) {
            bVar.a(yf);
            this.h.b(bVar);
            bVar.b();
        }
        bVar.c();
        bVar.a();
    }

    public void b(boolean z) {
        this.s.set(0, z);
    }

    public boolean b() {
        return this.a != null;
    }

    public h bE(String str) {
        this.e = str;
        return this;
    }

    public h bF(String str) {
        this.f = str;
        return this;
    }

    public boolean c() {
        return this.b;
    }

    public boolean c(h hVar) {
        if (hVar != null) {
            boolean b = b();
            boolean b2 = hVar.b();
            if ((!(b || b2) || (b && b2 && this.a.equals(hVar.a))) && this.b == hVar.b && this.c == hVar.c) {
                b = g();
                b2 = hVar.g();
                if (!(b || b2) || (b && b2 && this.d.equals(hVar.d))) {
                    b = i();
                    b2 = hVar.i();
                    if (!(b || b2) || (b && b2 && this.e.equals(hVar.e))) {
                        b = k();
                        b2 = hVar.k();
                        if (!(b || b2) || (b && b2 && this.f.equals(hVar.f))) {
                            b = l();
                            b2 = hVar.l();
                            if (!(b || b2) || (b && b2 && this.g.b(hVar.g))) {
                                b = gl();
                                b2 = hVar.gl();
                                if (!(b || b2) || (b && b2 && this.h.a(hVar.h))) {
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    public /* synthetic */ int compareTo(Object obj) {
        return d((h) obj);
    }

    public int d(h hVar) {
        if (!getClass().equals(hVar.getClass())) {
            return getClass().getName().compareTo(hVar.getClass().getName());
        }
        int compareTo = Boolean.valueOf(b()).compareTo(Boolean.valueOf(hVar.b()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (b()) {
            compareTo = org.apache.thrift.b.a(this.a, hVar.a);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(d()).compareTo(Boolean.valueOf(hVar.d()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (d()) {
            compareTo = org.apache.thrift.b.a(this.b, hVar.b);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(e()).compareTo(Boolean.valueOf(hVar.e()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (e()) {
            compareTo = org.apache.thrift.b.a(this.c, hVar.c);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(g()).compareTo(Boolean.valueOf(hVar.g()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (g()) {
            compareTo = org.apache.thrift.b.a(this.d, hVar.d);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(i()).compareTo(Boolean.valueOf(hVar.i()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (i()) {
            compareTo = org.apache.thrift.b.f(this.e, hVar.e);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(k()).compareTo(Boolean.valueOf(hVar.k()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (k()) {
            compareTo = org.apache.thrift.b.f(this.f, hVar.f);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(l()).compareTo(Boolean.valueOf(hVar.l()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (l()) {
            compareTo = org.apache.thrift.b.a(this.g, hVar.g);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(gl()).compareTo(Boolean.valueOf(hVar.gl()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (gl()) {
            compareTo = org.apache.thrift.b.a(this.h, hVar.h);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        return 0;
    }

    public boolean d() {
        return this.s.get(0);
    }

    public boolean e() {
        return this.s.get(1);
    }

    public boolean equals(Object obj) {
        return (obj != null && (obj instanceof h)) ? c((h) obj) : false;
    }

    public void f(boolean z) {
        this.s.set(1, z);
    }

    public h g(ByteBuffer byteBuffer) {
        this.d = byteBuffer;
        return this;
    }

    public boolean g() {
        return this.d != null;
    }

    public boolean gl() {
        return this.h != null;
    }

    public void gm() {
        if (this.a == null) {
            throw new TProtocolException("Required field 'action' was not present! Struct: " + toString());
        } else if (this.d == null) {
            throw new TProtocolException("Required field 'pushAction' was not present! Struct: " + toString());
        } else if (this.g == null) {
            throw new TProtocolException("Required field 'target' was not present! Struct: " + toString());
        }
    }

    public a gn() {
        return this.a;
    }

    public byte[] go() {
        g(org.apache.thrift.b.c(this.d));
        return this.d.array();
    }

    public c gp() {
        return this.h;
    }

    public String h() {
        return this.e;
    }

    public int hashCode() {
        return 0;
    }

    public boolean i() {
        return this.e != null;
    }

    public String j() {
        return this.f;
    }

    public boolean k() {
        return this.f != null;
    }

    public boolean l() {
        return this.g != null;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("XmPushActionContainer(");
        stringBuilder.append("action:");
        if (this.a == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.a);
        }
        stringBuilder.append(", ");
        stringBuilder.append("encryptAction:");
        stringBuilder.append(this.b);
        stringBuilder.append(", ");
        stringBuilder.append("isRequest:");
        stringBuilder.append(this.c);
        stringBuilder.append(", ");
        stringBuilder.append("pushAction:");
        if (this.d == null) {
            stringBuilder.append("null");
        } else {
            org.apache.thrift.b.a(this.d, stringBuilder);
        }
        if (i()) {
            stringBuilder.append(", ");
            stringBuilder.append("appid:");
            if (this.e == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.e);
            }
        }
        if (k()) {
            stringBuilder.append(", ");
            stringBuilder.append("packageName:");
            if (this.f == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.f);
            }
        }
        stringBuilder.append(", ");
        stringBuilder.append("target:");
        if (this.g == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.g);
        }
        if (gl()) {
            stringBuilder.append(", ");
            stringBuilder.append("metaInfo:");
            if (this.h == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.h);
            }
        }
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    public h z(boolean z) {
        this.b = z;
        b(true);
        return this;
    }
}
