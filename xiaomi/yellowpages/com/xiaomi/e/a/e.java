package com.xiaomi.e.a;

import com.alipay.mobilesecuritysdk.deviceID.DeviceIdModel;
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
import org.apache.thrift.TBase;
import org.apache.thrift.meta_data.FieldMetaData;
import org.apache.thrift.meta_data.FieldValueMetaData;
import org.apache.thrift.meta_data.StructMetaData;
import org.apache.thrift.protocol.TProtocolException;
import org.apache.thrift.protocol.b;
import org.apache.thrift.protocol.c;
import org.apache.thrift.protocol.f;

public class e implements Serializable, Cloneable, TBase {
    public static final Map ek;
    private static final f xZ;
    private static final org.apache.thrift.protocol.e ya;
    private static final org.apache.thrift.protocol.e yb;
    private static final org.apache.thrift.protocol.e yc;
    private static final org.apache.thrift.protocol.e yd;
    private static final org.apache.thrift.protocol.e ye;
    private static final org.apache.thrift.protocol.e yf;
    private static final org.apache.thrift.protocol.e yg;
    private static final org.apache.thrift.protocol.e yh;
    private static final org.apache.thrift.protocol.e yi;
    private static final org.apache.thrift.protocol.e yj;
    public String a;
    public d b;
    public String c;
    public String d;
    public long e;
    public String f;
    public String g;
    public n h;
    public String i;
    public String j;
    private BitSet w;

    public enum a {
        DEBUG((short) 1, "debug"),
        TARGET((short) 2, "target"),
        ID((short) 3, "id"),
        APP_ID((short) 4, DeviceIdModel.mAppId),
        MESSAGE_TS((short) 5, "messageTs"),
        TOPIC((short) 6, "topic"),
        ALIAS_NAME((short) 7, "aliasName"),
        REQUEST((short) 8, "request"),
        PACKAGE_NAME((short) 9, "packageName"),
        CATEGORY((short) 10, "category");
        
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
        xZ = new f("XmPushActionAckMessage");
        ya = new org.apache.thrift.protocol.e("debug", (byte) 11, (short) 1);
        yb = new org.apache.thrift.protocol.e("target", (byte) 12, (short) 2);
        yc = new org.apache.thrift.protocol.e("id", (byte) 11, (short) 3);
        yd = new org.apache.thrift.protocol.e(DeviceIdModel.mAppId, (byte) 11, (short) 4);
        ye = new org.apache.thrift.protocol.e("messageTs", (byte) 10, (short) 5);
        yf = new org.apache.thrift.protocol.e("topic", (byte) 11, (short) 6);
        yg = new org.apache.thrift.protocol.e("aliasName", (byte) 11, (short) 7);
        yh = new org.apache.thrift.protocol.e("request", (byte) 12, (short) 8);
        yi = new org.apache.thrift.protocol.e("packageName", (byte) 11, (short) 9);
        yj = new org.apache.thrift.protocol.e("category", (byte) 11, (short) 10);
        Map enumMap = new EnumMap(a.class);
        enumMap.put(a.DEBUG, new FieldMetaData("debug", (byte) 2, new FieldValueMetaData((byte) 11)));
        enumMap.put(a.TARGET, new FieldMetaData("target", (byte) 2, new StructMetaData((byte) 12, d.class)));
        enumMap.put(a.ID, new FieldMetaData("id", (byte) 1, new FieldValueMetaData((byte) 11)));
        enumMap.put(a.APP_ID, new FieldMetaData(DeviceIdModel.mAppId, (byte) 1, new FieldValueMetaData((byte) 11)));
        enumMap.put(a.MESSAGE_TS, new FieldMetaData("messageTs", (byte) 1, new FieldValueMetaData((byte) 10)));
        enumMap.put(a.TOPIC, new FieldMetaData("topic", (byte) 2, new FieldValueMetaData((byte) 11)));
        enumMap.put(a.ALIAS_NAME, new FieldMetaData("aliasName", (byte) 2, new FieldValueMetaData((byte) 11)));
        enumMap.put(a.REQUEST, new FieldMetaData("request", (byte) 2, new StructMetaData((byte) 12, n.class)));
        enumMap.put(a.PACKAGE_NAME, new FieldMetaData("packageName", (byte) 2, new FieldValueMetaData((byte) 11)));
        enumMap.put(a.CATEGORY, new FieldMetaData("category", (byte) 2, new FieldValueMetaData((byte) 11)));
        ek = Collections.unmodifiableMap(enumMap);
        FieldMetaData.a(e.class, ek);
    }

    public e() {
        this.w = new BitSet(1);
    }

    public void a(b bVar) {
        bVar.bq();
        while (true) {
            org.apache.thrift.protocol.e br = bVar.br();
            if (br.b == (byte) 0) {
                bVar.am();
                if (e()) {
                    cF();
                    return;
                }
                throw new TProtocolException("Required field 'messageTs' was not found in serialized data! Struct: " + toString());
            }
            switch (br.An) {
                case TransactionXMLFile.MODE_WORLD_READABLE /*1*/:
                    if (br.b != (byte) 11) {
                        c.a(bVar, br.b);
                        break;
                    } else {
                        this.a = bVar.bD();
                        break;
                    }
                case TransactionXMLFile.MODE_WORLD_WRITEABLE /*2*/:
                    if (br.b != (byte) 12) {
                        c.a(bVar, br.b);
                        break;
                    }
                    this.b = new d();
                    this.b.a(bVar);
                    break;
                case WindowData.d /*3*/:
                    if (br.b != (byte) 11) {
                        c.a(bVar, br.b);
                        break;
                    } else {
                        this.c = bVar.bD();
                        break;
                    }
                case Base64.CRLF /*4*/:
                    if (br.b != (byte) 11) {
                        c.a(bVar, br.b);
                        break;
                    } else {
                        this.d = bVar.bD();
                        break;
                    }
                case WindowData.f /*5*/:
                    if (br.b != (byte) 10) {
                        c.a(bVar, br.b);
                        break;
                    }
                    this.e = bVar.bB();
                    a(true);
                    break;
                case WindowData.g /*6*/:
                    if (br.b != (byte) 11) {
                        c.a(bVar, br.b);
                        break;
                    } else {
                        this.f = bVar.bD();
                        break;
                    }
                case WindowData.h /*7*/:
                    if (br.b != (byte) 11) {
                        c.a(bVar, br.b);
                        break;
                    } else {
                        this.g = bVar.bD();
                        break;
                    }
                case Base64.URL_SAFE /*8*/:
                    if (br.b != (byte) 12) {
                        c.a(bVar, br.b);
                        break;
                    }
                    this.h = new n();
                    this.h.a(bVar);
                    break;
                case WindowData.j /*9*/:
                    if (br.b != (byte) 11) {
                        c.a(bVar, br.b);
                        break;
                    } else {
                        this.i = bVar.bD();
                        break;
                    }
                case WindowData.k /*10*/:
                    if (br.b != (byte) 11) {
                        c.a(bVar, br.b);
                        break;
                    } else {
                        this.j = bVar.bD();
                        break;
                    }
                default:
                    c.a(bVar, br.b);
                    break;
            }
            bVar.j();
        }
    }

    public void a(boolean z) {
        this.w.set(0, z);
    }

    public boolean a() {
        return this.a != null;
    }

    public boolean a(e eVar) {
        if (eVar != null) {
            boolean a = a();
            boolean a2 = eVar.a();
            if (!(a || a2) || (a && a2 && this.a.equals(eVar.a))) {
                a = b();
                a2 = eVar.b();
                if (!(a || a2) || (a && a2 && this.b.b(eVar.b))) {
                    a = c();
                    a2 = eVar.c();
                    if (!(a || a2) || (a && a2 && this.c.equals(eVar.c))) {
                        a = d();
                        a2 = eVar.d();
                        if ((!(a || a2) || (a && a2 && this.d.equals(eVar.d))) && this.e == eVar.e) {
                            a = f();
                            a2 = eVar.f();
                            if (!(a || a2) || (a && a2 && this.f.equals(eVar.f))) {
                                a = g();
                                a2 = eVar.g();
                                if (!(a || a2) || (a && a2 && this.g.equals(eVar.g))) {
                                    a = an();
                                    a2 = eVar.an();
                                    if (!(a || a2) || (a && a2 && this.h.b(eVar.h))) {
                                        a = i();
                                        a2 = eVar.i();
                                        if (!(a || a2) || (a && a2 && this.i.equals(eVar.i))) {
                                            a = j();
                                            a2 = eVar.j();
                                            if (!(a || a2) || (a && a2 && this.j.equals(eVar.j))) {
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
        return this.h != null;
    }

    public int b(e eVar) {
        if (!getClass().equals(eVar.getClass())) {
            return getClass().getName().compareTo(eVar.getClass().getName());
        }
        int compareTo = Boolean.valueOf(a()).compareTo(Boolean.valueOf(eVar.a()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (a()) {
            compareTo = org.apache.thrift.b.f(this.a, eVar.a);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(b()).compareTo(Boolean.valueOf(eVar.b()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (b()) {
            compareTo = org.apache.thrift.b.a(this.b, eVar.b);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(c()).compareTo(Boolean.valueOf(eVar.c()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (c()) {
            compareTo = org.apache.thrift.b.f(this.c, eVar.c);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(d()).compareTo(Boolean.valueOf(eVar.d()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (d()) {
            compareTo = org.apache.thrift.b.f(this.d, eVar.d);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(e()).compareTo(Boolean.valueOf(eVar.e()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (e()) {
            compareTo = org.apache.thrift.b.a(this.e, eVar.e);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(f()).compareTo(Boolean.valueOf(eVar.f()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (f()) {
            compareTo = org.apache.thrift.b.f(this.f, eVar.f);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(g()).compareTo(Boolean.valueOf(eVar.g()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (g()) {
            compareTo = org.apache.thrift.b.f(this.g, eVar.g);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(an()).compareTo(Boolean.valueOf(eVar.an()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (an()) {
            compareTo = org.apache.thrift.b.a(this.h, eVar.h);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(i()).compareTo(Boolean.valueOf(eVar.i()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (i()) {
            compareTo = org.apache.thrift.b.f(this.i, eVar.i);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(j()).compareTo(Boolean.valueOf(eVar.j()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (j()) {
            compareTo = org.apache.thrift.b.f(this.j, eVar.j);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        return 0;
    }

    public void b(b bVar) {
        cF();
        bVar.a(xZ);
        if (this.a != null && a()) {
            bVar.a(ya);
            bVar.a(this.a);
            bVar.b();
        }
        if (this.b != null && b()) {
            bVar.a(yb);
            this.b.b(bVar);
            bVar.b();
        }
        if (this.c != null) {
            bVar.a(yc);
            bVar.a(this.c);
            bVar.b();
        }
        if (this.d != null) {
            bVar.a(yd);
            bVar.a(this.d);
            bVar.b();
        }
        bVar.a(ye);
        bVar.a(this.e);
        bVar.b();
        if (this.f != null && f()) {
            bVar.a(yf);
            bVar.a(this.f);
            bVar.b();
        }
        if (this.g != null && g()) {
            bVar.a(yg);
            bVar.a(this.g);
            bVar.b();
        }
        if (this.h != null && an()) {
            bVar.a(yh);
            this.h.b(bVar);
            bVar.b();
        }
        if (this.i != null && i()) {
            bVar.a(yi);
            bVar.a(this.i);
            bVar.b();
        }
        if (this.j != null && j()) {
            bVar.a(yj);
            bVar.a(this.j);
            bVar.b();
        }
        bVar.c();
        bVar.a();
    }

    public boolean b() {
        return this.b != null;
    }

    public e bJ(String str) {
        this.c = str;
        return this;
    }

    public e bK(String str) {
        this.d = str;
        return this;
    }

    public e bL(String str) {
        this.f = str;
        return this;
    }

    public e bM(String str) {
        this.g = str;
        return this;
    }

    public boolean c() {
        return this.c != null;
    }

    public void cF() {
        if (this.c == null) {
            throw new TProtocolException("Required field 'id' was not present! Struct: " + toString());
        } else if (this.d == null) {
            throw new TProtocolException("Required field 'appId' was not present! Struct: " + toString());
        }
    }

    public /* synthetic */ int compareTo(Object obj) {
        return b((e) obj);
    }

    public boolean d() {
        return this.d != null;
    }

    public boolean e() {
        return this.w.get(0);
    }

    public boolean equals(Object obj) {
        return (obj != null && (obj instanceof e)) ? a((e) obj) : false;
    }

    public boolean f() {
        return this.f != null;
    }

    public boolean g() {
        return this.g != null;
    }

    public int hashCode() {
        return 0;
    }

    public boolean i() {
        return this.i != null;
    }

    public boolean j() {
        return this.j != null;
    }

    public e m(long j) {
        this.e = j;
        a(true);
        return this;
    }

    public String toString() {
        Object obj = null;
        StringBuilder stringBuilder = new StringBuilder("XmPushActionAckMessage(");
        Object obj2 = 1;
        if (a()) {
            stringBuilder.append("debug:");
            if (this.a == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.a);
            }
            obj2 = null;
        }
        if (b()) {
            if (obj2 == null) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("target:");
            if (this.b == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.b);
            }
        } else {
            obj = obj2;
        }
        if (obj == null) {
            stringBuilder.append(", ");
        }
        stringBuilder.append("id:");
        if (this.c == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.c);
        }
        stringBuilder.append(", ");
        stringBuilder.append("appId:");
        if (this.d == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.d);
        }
        stringBuilder.append(", ");
        stringBuilder.append("messageTs:");
        stringBuilder.append(this.e);
        if (f()) {
            stringBuilder.append(", ");
            stringBuilder.append("topic:");
            if (this.f == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.f);
            }
        }
        if (g()) {
            stringBuilder.append(", ");
            stringBuilder.append("aliasName:");
            if (this.g == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.g);
            }
        }
        if (an()) {
            stringBuilder.append(", ");
            stringBuilder.append("request:");
            if (this.h == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.h);
            }
        }
        if (i()) {
            stringBuilder.append(", ");
            stringBuilder.append("packageName:");
            if (this.i == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.i);
            }
        }
        if (j()) {
            stringBuilder.append(", ");
            stringBuilder.append("category:");
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
