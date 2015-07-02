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
import org.apache.thrift.protocol.e;
import org.apache.thrift.protocol.f;

public class k implements Serializable, Cloneable, TBase {
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
    public d b;
    public String c;
    public String d;
    public j e;
    public long f;
    public String g;
    public String h;
    public String i;
    public String j;
    private BitSet w;

    public enum a {
        DEBUG((short) 1, "debug"),
        TARGET((short) 2, "target"),
        ID((short) 3, "id"),
        APP_ID((short) 4, DeviceIdModel.mAppId),
        REQUEST((short) 5, "request"),
        ERROR_CODE((short) 6, "errorCode"),
        REASON((short) 7, "reason"),
        REG_ID((short) 8, "regId"),
        REG_SECRET((short) 9, "regSecret"),
        PACKAGE_NAME((short) 10, "packageName");
        
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
        xZ = new f("XmPushActionRegistrationResult");
        ya = new e("debug", (byte) 11, (short) 1);
        yb = new e("target", (byte) 12, (short) 2);
        yc = new e("id", (byte) 11, (short) 3);
        yd = new e(DeviceIdModel.mAppId, (byte) 11, (short) 4);
        ye = new e("request", (byte) 12, (short) 5);
        yf = new e("errorCode", (byte) 10, (short) 6);
        yg = new e("reason", (byte) 11, (short) 7);
        yh = new e("regId", (byte) 11, (short) 8);
        yi = new e("regSecret", (byte) 11, (short) 9);
        yj = new e("packageName", (byte) 11, (short) 10);
        Map enumMap = new EnumMap(a.class);
        enumMap.put(a.DEBUG, new FieldMetaData("debug", (byte) 2, new FieldValueMetaData((byte) 11)));
        enumMap.put(a.TARGET, new FieldMetaData("target", (byte) 2, new StructMetaData((byte) 12, d.class)));
        enumMap.put(a.ID, new FieldMetaData("id", (byte) 1, new FieldValueMetaData((byte) 11)));
        enumMap.put(a.APP_ID, new FieldMetaData(DeviceIdModel.mAppId, (byte) 1, new FieldValueMetaData((byte) 11)));
        enumMap.put(a.REQUEST, new FieldMetaData("request", (byte) 2, new StructMetaData((byte) 12, j.class)));
        enumMap.put(a.ERROR_CODE, new FieldMetaData("errorCode", (byte) 1, new FieldValueMetaData((byte) 10)));
        enumMap.put(a.REASON, new FieldMetaData("reason", (byte) 2, new FieldValueMetaData((byte) 11)));
        enumMap.put(a.REG_ID, new FieldMetaData("regId", (byte) 2, new FieldValueMetaData((byte) 11)));
        enumMap.put(a.REG_SECRET, new FieldMetaData("regSecret", (byte) 2, new FieldValueMetaData((byte) 11)));
        enumMap.put(a.PACKAGE_NAME, new FieldMetaData("packageName", (byte) 2, new FieldValueMetaData((byte) 11)));
        ek = Collections.unmodifiableMap(enumMap);
        FieldMetaData.a(k.class, ek);
    }

    public k() {
        this.w = new BitSet(1);
    }

    public void a(b bVar) {
        bVar.bq();
        while (true) {
            e br = bVar.br();
            if (br.b == (byte) 0) {
                bVar.am();
                if (f()) {
                    cF();
                    return;
                }
                throw new TProtocolException("Required field 'errorCode' was not found in serialized data! Struct: " + toString());
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
                    if (br.b != (byte) 12) {
                        c.a(bVar, br.b);
                        break;
                    }
                    this.e = new j();
                    this.e.a(bVar);
                    break;
                case WindowData.g /*6*/:
                    if (br.b != (byte) 10) {
                        c.a(bVar, br.b);
                        break;
                    }
                    this.f = bVar.bB();
                    a(true);
                    break;
                case WindowData.h /*7*/:
                    if (br.b != (byte) 11) {
                        c.a(bVar, br.b);
                        break;
                    } else {
                        this.g = bVar.bD();
                        break;
                    }
                case Base64.URL_SAFE /*8*/:
                    if (br.b != (byte) 11) {
                        c.a(bVar, br.b);
                        break;
                    } else {
                        this.h = bVar.bD();
                        break;
                    }
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

    public boolean a(k kVar) {
        if (kVar != null) {
            boolean a = a();
            boolean a2 = kVar.a();
            if (!(a || a2) || (a && a2 && this.a.equals(kVar.a))) {
                a = b();
                a2 = kVar.b();
                if (!(a || a2) || (a && a2 && this.b.b(kVar.b))) {
                    a = c();
                    a2 = kVar.c();
                    if (!(a || a2) || (a && a2 && this.c.equals(kVar.c))) {
                        a = d();
                        a2 = kVar.d();
                        if (!(a || a2) || (a && a2 && this.d.equals(kVar.d))) {
                            a = e();
                            a2 = kVar.e();
                            if ((!(a || a2) || (a && a2 && this.e.b(kVar.e))) && this.f == kVar.f) {
                                a = g();
                                a2 = kVar.g();
                                if (!(a || a2) || (a && a2 && this.g.equals(kVar.g))) {
                                    a = an();
                                    a2 = kVar.an();
                                    if (!(a || a2) || (a && a2 && this.h.equals(kVar.h))) {
                                        a = i();
                                        a2 = kVar.i();
                                        if (!(a || a2) || (a && a2 && this.i.equals(kVar.i))) {
                                            a = j();
                                            a2 = kVar.j();
                                            if (!(a || a2) || (a && a2 && this.j.equals(kVar.j))) {
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

    public int b(k kVar) {
        if (!getClass().equals(kVar.getClass())) {
            return getClass().getName().compareTo(kVar.getClass().getName());
        }
        int compareTo = Boolean.valueOf(a()).compareTo(Boolean.valueOf(kVar.a()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (a()) {
            compareTo = org.apache.thrift.b.f(this.a, kVar.a);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(b()).compareTo(Boolean.valueOf(kVar.b()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (b()) {
            compareTo = org.apache.thrift.b.a(this.b, kVar.b);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(c()).compareTo(Boolean.valueOf(kVar.c()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (c()) {
            compareTo = org.apache.thrift.b.f(this.c, kVar.c);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(d()).compareTo(Boolean.valueOf(kVar.d()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (d()) {
            compareTo = org.apache.thrift.b.f(this.d, kVar.d);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(e()).compareTo(Boolean.valueOf(kVar.e()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (e()) {
            compareTo = org.apache.thrift.b.a(this.e, kVar.e);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(f()).compareTo(Boolean.valueOf(kVar.f()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (f()) {
            compareTo = org.apache.thrift.b.a(this.f, kVar.f);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(g()).compareTo(Boolean.valueOf(kVar.g()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (g()) {
            compareTo = org.apache.thrift.b.f(this.g, kVar.g);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(an()).compareTo(Boolean.valueOf(kVar.an()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (an()) {
            compareTo = org.apache.thrift.b.f(this.h, kVar.h);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(i()).compareTo(Boolean.valueOf(kVar.i()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (i()) {
            compareTo = org.apache.thrift.b.f(this.i, kVar.i);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(j()).compareTo(Boolean.valueOf(kVar.j()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (j()) {
            compareTo = org.apache.thrift.b.f(this.j, kVar.j);
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
        if (this.e != null && e()) {
            bVar.a(ye);
            this.e.b(bVar);
            bVar.b();
        }
        bVar.a(yf);
        bVar.a(this.f);
        bVar.b();
        if (this.g != null && g()) {
            bVar.a(yg);
            bVar.a(this.g);
            bVar.b();
        }
        if (this.h != null && an()) {
            bVar.a(yh);
            bVar.a(this.h);
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
        return b((k) obj);
    }

    public boolean d() {
        return this.d != null;
    }

    public boolean e() {
        return this.e != null;
    }

    public boolean equals(Object obj) {
        return (obj != null && (obj instanceof k)) ? a((k) obj) : false;
    }

    public boolean f() {
        return this.w.get(0);
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

    public String toString() {
        Object obj = null;
        StringBuilder stringBuilder = new StringBuilder("XmPushActionRegistrationResult(");
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
        if (e()) {
            stringBuilder.append(", ");
            stringBuilder.append("request:");
            if (this.e == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.e);
            }
        }
        stringBuilder.append(", ");
        stringBuilder.append("errorCode:");
        stringBuilder.append(this.f);
        if (g()) {
            stringBuilder.append(", ");
            stringBuilder.append("reason:");
            if (this.g == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.g);
            }
        }
        if (an()) {
            stringBuilder.append(", ");
            stringBuilder.append("regId:");
            if (this.h == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.h);
            }
        }
        if (i()) {
            stringBuilder.append(", ");
            stringBuilder.append("regSecret:");
            if (this.i == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.i);
            }
        }
        if (j()) {
            stringBuilder.append(", ");
            stringBuilder.append("packageName:");
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
