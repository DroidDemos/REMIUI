package com.xiaomi.e.a;

import com.alipay.sdk.cons.MiniDefine;
import com.alipay.sdk.protocol.WindowData;
import com.ta.utdid2.android.utils.Base64;
import com.ta.utdid2.core.persistent.TransactionXMLFile;
import java.io.Serializable;
import java.util.BitSet;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.thrift.TBase;
import org.apache.thrift.meta_data.FieldMetaData;
import org.apache.thrift.meta_data.FieldValueMetaData;
import org.apache.thrift.meta_data.MapMetaData;
import org.apache.thrift.protocol.TProtocolException;
import org.apache.thrift.protocol.b;
import org.apache.thrift.protocol.d;
import org.apache.thrift.protocol.e;
import org.apache.thrift.protocol.f;

public class c implements Serializable, Cloneable, TBase {
    public static final Map ek;
    private static final f xZ;
    private static final e ya;
    private static final e yb;
    private static final e yc;
    private static final e yd;
    private static final e ye;
    private static final e yf;
    private static final e yg;
    private static final e yh;
    private static final e yi;
    private static final e yj;
    public String a;
    public long b;
    public String c;
    public String d;
    public String e;
    public int f;
    public String g;
    public int h;
    public int i;
    public Map j;
    private BitSet w;

    public enum a {
        ID((short) 1, "id"),
        MESSAGE_TS((short) 2, "messageTs"),
        TOPIC((short) 3, "topic"),
        TITLE((short) 4, MiniDefine.au),
        DESCRIPTION((short) 5, "description"),
        NOTIFY_TYPE((short) 6, "notifyType"),
        URL((short) 7, "url"),
        PASS_THROUGH((short) 8, "passThrough"),
        NOTIFY_ID((short) 9, "notifyId"),
        EXTRA((short) 10, "extra");
        
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
        xZ = new f("PushMetaInfo");
        ya = new e("id", (byte) 11, (short) 1);
        yb = new e("messageTs", (byte) 10, (short) 2);
        yc = new e("topic", (byte) 11, (short) 3);
        yd = new e(MiniDefine.au, (byte) 11, (short) 4);
        ye = new e("description", (byte) 11, (short) 5);
        yf = new e("notifyType", (byte) 8, (short) 6);
        yg = new e("url", (byte) 11, (short) 7);
        yh = new e("passThrough", (byte) 8, (short) 8);
        yi = new e("notifyId", (byte) 8, (short) 9);
        yj = new e("extra", (byte) 13, (short) 10);
        Map enumMap = new EnumMap(a.class);
        enumMap.put(a.ID, new FieldMetaData("id", (byte) 1, new FieldValueMetaData((byte) 11)));
        enumMap.put(a.MESSAGE_TS, new FieldMetaData("messageTs", (byte) 1, new FieldValueMetaData((byte) 10)));
        enumMap.put(a.TOPIC, new FieldMetaData("topic", (byte) 2, new FieldValueMetaData((byte) 11)));
        enumMap.put(a.TITLE, new FieldMetaData(MiniDefine.au, (byte) 2, new FieldValueMetaData((byte) 11)));
        enumMap.put(a.DESCRIPTION, new FieldMetaData("description", (byte) 2, new FieldValueMetaData((byte) 11)));
        enumMap.put(a.NOTIFY_TYPE, new FieldMetaData("notifyType", (byte) 2, new FieldValueMetaData((byte) 8)));
        enumMap.put(a.URL, new FieldMetaData("url", (byte) 2, new FieldValueMetaData((byte) 11)));
        enumMap.put(a.PASS_THROUGH, new FieldMetaData("passThrough", (byte) 2, new FieldValueMetaData((byte) 8)));
        enumMap.put(a.NOTIFY_ID, new FieldMetaData("notifyId", (byte) 2, new FieldValueMetaData((byte) 8)));
        enumMap.put(a.EXTRA, new FieldMetaData("extra", (byte) 2, new MapMetaData((byte) 13, new FieldValueMetaData((byte) 11), new FieldValueMetaData((byte) 11))));
        ek = Collections.unmodifiableMap(enumMap);
        FieldMetaData.a(c.class, ek);
    }

    public c() {
        this.w = new BitSet(4);
    }

    public String a() {
        return this.a;
    }

    public void a(b bVar) {
        bVar.bq();
        while (true) {
            e br = bVar.br();
            if (br.b == (byte) 0) {
                bVar.am();
                if (d()) {
                    gv();
                    return;
                }
                throw new TProtocolException("Required field 'messageTs' was not found in serialized data! Struct: " + toString());
            }
            switch (br.An) {
                case TransactionXMLFile.MODE_WORLD_READABLE /*1*/:
                    if (br.b != (byte) 11) {
                        org.apache.thrift.protocol.c.a(bVar, br.b);
                        break;
                    } else {
                        this.a = bVar.bD();
                        break;
                    }
                case TransactionXMLFile.MODE_WORLD_WRITEABLE /*2*/:
                    if (br.b != (byte) 10) {
                        org.apache.thrift.protocol.c.a(bVar, br.b);
                        break;
                    }
                    this.b = bVar.bB();
                    a(true);
                    break;
                case WindowData.d /*3*/:
                    if (br.b != (byte) 11) {
                        org.apache.thrift.protocol.c.a(bVar, br.b);
                        break;
                    } else {
                        this.c = bVar.bD();
                        break;
                    }
                case Base64.CRLF /*4*/:
                    if (br.b != (byte) 11) {
                        org.apache.thrift.protocol.c.a(bVar, br.b);
                        break;
                    } else {
                        this.d = bVar.bD();
                        break;
                    }
                case WindowData.f /*5*/:
                    if (br.b != (byte) 11) {
                        org.apache.thrift.protocol.c.a(bVar, br.b);
                        break;
                    } else {
                        this.e = bVar.bD();
                        break;
                    }
                case WindowData.g /*6*/:
                    if (br.b != (byte) 8) {
                        org.apache.thrift.protocol.c.a(bVar, br.b);
                        break;
                    }
                    this.f = bVar.bA();
                    b(true);
                    break;
                case WindowData.h /*7*/:
                    if (br.b != (byte) 11) {
                        org.apache.thrift.protocol.c.a(bVar, br.b);
                        break;
                    } else {
                        this.g = bVar.bD();
                        break;
                    }
                case Base64.URL_SAFE /*8*/:
                    if (br.b != (byte) 8) {
                        org.apache.thrift.protocol.c.a(bVar, br.b);
                        break;
                    }
                    this.h = bVar.bA();
                    e(true);
                    break;
                case WindowData.j /*9*/:
                    if (br.b != (byte) 8) {
                        org.apache.thrift.protocol.c.a(bVar, br.b);
                        break;
                    }
                    this.i = bVar.bA();
                    f(true);
                    break;
                case WindowData.k /*10*/:
                    if (br.b != (byte) 13) {
                        org.apache.thrift.protocol.c.a(bVar, br.b);
                        break;
                    }
                    d bs = bVar.bs();
                    this.j = new HashMap(bs.c * 2);
                    for (int i = 0; i < bs.c; i++) {
                        this.j.put(bVar.bD(), bVar.bD());
                    }
                    bVar.ao();
                    break;
                default:
                    org.apache.thrift.protocol.c.a(bVar, br.b);
                    break;
            }
            bVar.j();
        }
    }

    public void a(boolean z) {
        this.w.set(0, z);
    }

    public boolean a(c cVar) {
        if (cVar != null) {
            boolean b = b();
            boolean b2 = cVar.b();
            if ((!(b || b2) || (b && b2 && this.a.equals(cVar.a))) && this.b == cVar.b) {
                b = f();
                b2 = cVar.f();
                if (!(b || b2) || (b && b2 && this.c.equals(cVar.c))) {
                    b = an();
                    b2 = cVar.an();
                    if (!(b || b2) || (b && b2 && this.d.equals(cVar.d))) {
                        b = j();
                        b2 = cVar.j();
                        if (!(b || b2) || (b && b2 && this.e.equals(cVar.e))) {
                            b = l();
                            b2 = cVar.l();
                            if (!(b || b2) || (b && b2 && this.f == cVar.f)) {
                                b = m();
                                b2 = cVar.m();
                                if (!(b || b2) || (b && b2 && this.g.equals(cVar.g))) {
                                    b = gs();
                                    b2 = cVar.gs();
                                    if (!(b || b2) || (b && b2 && this.h == cVar.h)) {
                                        b = bx();
                                        b2 = cVar.bx();
                                        if (!(b || b2) || (b && b2 && this.i == cVar.i)) {
                                            b = gu();
                                            b2 = cVar.gu();
                                            if (!(b || b2) || (b && b2 && this.j.equals(cVar.j))) {
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
        return false;
    }

    public boolean an() {
        return this.d != null;
    }

    public int b(c cVar) {
        if (!getClass().equals(cVar.getClass())) {
            return getClass().getName().compareTo(cVar.getClass().getName());
        }
        int compareTo = Boolean.valueOf(b()).compareTo(Boolean.valueOf(cVar.b()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (b()) {
            compareTo = org.apache.thrift.b.f(this.a, cVar.a);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(d()).compareTo(Boolean.valueOf(cVar.d()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (d()) {
            compareTo = org.apache.thrift.b.a(this.b, cVar.b);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(f()).compareTo(Boolean.valueOf(cVar.f()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (f()) {
            compareTo = org.apache.thrift.b.f(this.c, cVar.c);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(an()).compareTo(Boolean.valueOf(cVar.an()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (an()) {
            compareTo = org.apache.thrift.b.f(this.d, cVar.d);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(j()).compareTo(Boolean.valueOf(cVar.j()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (j()) {
            compareTo = org.apache.thrift.b.f(this.e, cVar.e);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(l()).compareTo(Boolean.valueOf(cVar.l()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (l()) {
            compareTo = org.apache.thrift.b.a(this.f, cVar.f);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m()).compareTo(Boolean.valueOf(cVar.m()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m()) {
            compareTo = org.apache.thrift.b.f(this.g, cVar.g);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(gs()).compareTo(Boolean.valueOf(cVar.gs()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (gs()) {
            compareTo = org.apache.thrift.b.a(this.h, cVar.h);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(bx()).compareTo(Boolean.valueOf(cVar.bx()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (bx()) {
            compareTo = org.apache.thrift.b.a(this.i, cVar.i);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(gu()).compareTo(Boolean.valueOf(cVar.gu()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (gu()) {
            compareTo = org.apache.thrift.b.a(this.j, cVar.j);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        return 0;
    }

    public void b(b bVar) {
        gv();
        bVar.a(xZ);
        if (this.a != null) {
            bVar.a(ya);
            bVar.a(this.a);
            bVar.b();
        }
        bVar.a(yb);
        bVar.a(this.b);
        bVar.b();
        if (this.c != null && f()) {
            bVar.a(yc);
            bVar.a(this.c);
            bVar.b();
        }
        if (this.d != null && an()) {
            bVar.a(yd);
            bVar.a(this.d);
            bVar.b();
        }
        if (this.e != null && j()) {
            bVar.a(ye);
            bVar.a(this.e);
            bVar.b();
        }
        if (l()) {
            bVar.a(yf);
            bVar.a(this.f);
            bVar.b();
        }
        if (this.g != null && m()) {
            bVar.a(yg);
            bVar.a(this.g);
            bVar.b();
        }
        if (gs()) {
            bVar.a(yh);
            bVar.a(this.h);
            bVar.b();
        }
        if (bx()) {
            bVar.a(yi);
            bVar.a(this.i);
            bVar.b();
        }
        if (this.j != null && gu()) {
            bVar.a(yj);
            bVar.a(new d((byte) 11, (byte) 11, this.j.size()));
            for (Entry entry : this.j.entrySet()) {
                bVar.a((String) entry.getKey());
                bVar.a((String) entry.getValue());
            }
            bVar.d();
            bVar.b();
        }
        bVar.c();
        bVar.a();
    }

    public void b(boolean z) {
        this.w.set(1, z);
    }

    public boolean b() {
        return this.a != null;
    }

    public boolean bx() {
        return this.w.get(3);
    }

    public long c() {
        return this.b;
    }

    public /* synthetic */ int compareTo(Object obj) {
        return b((c) obj);
    }

    public boolean d() {
        return this.w.get(0);
    }

    public String e() {
        return this.c;
    }

    public void e(boolean z) {
        this.w.set(2, z);
    }

    public boolean equals(Object obj) {
        return (obj != null && (obj instanceof c)) ? a((c) obj) : false;
    }

    public void f(boolean z) {
        this.w.set(3, z);
    }

    public boolean f() {
        return this.c != null;
    }

    public String g() {
        return this.d;
    }

    public int gr() {
        return this.h;
    }

    public boolean gs() {
        return this.w.get(2);
    }

    public int gt() {
        return this.i;
    }

    public boolean gu() {
        return this.j != null;
    }

    public void gv() {
        if (this.a == null) {
            throw new TProtocolException("Required field 'id' was not present! Struct: " + toString());
        }
    }

    public int hashCode() {
        return 0;
    }

    public String i() {
        return this.e;
    }

    public boolean j() {
        return this.e != null;
    }

    public int k() {
        return this.f;
    }

    public boolean l() {
        return this.w.get(1);
    }

    public boolean m() {
        return this.g != null;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("PushMetaInfo(");
        stringBuilder.append("id:");
        if (this.a == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.a);
        }
        stringBuilder.append(", ");
        stringBuilder.append("messageTs:");
        stringBuilder.append(this.b);
        if (f()) {
            stringBuilder.append(", ");
            stringBuilder.append("topic:");
            if (this.c == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.c);
            }
        }
        if (an()) {
            stringBuilder.append(", ");
            stringBuilder.append("title:");
            if (this.d == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.d);
            }
        }
        if (j()) {
            stringBuilder.append(", ");
            stringBuilder.append("description:");
            if (this.e == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.e);
            }
        }
        if (l()) {
            stringBuilder.append(", ");
            stringBuilder.append("notifyType:");
            stringBuilder.append(this.f);
        }
        if (m()) {
            stringBuilder.append(", ");
            stringBuilder.append("url:");
            if (this.g == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.g);
            }
        }
        if (gs()) {
            stringBuilder.append(", ");
            stringBuilder.append("passThrough:");
            stringBuilder.append(this.h);
        }
        if (bx()) {
            stringBuilder.append(", ");
            stringBuilder.append("notifyId:");
            stringBuilder.append(this.i);
        }
        if (gu()) {
            stringBuilder.append(", ");
            stringBuilder.append("extra:");
            if (this.j == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.j);
            }
        }
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
