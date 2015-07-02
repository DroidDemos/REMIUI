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

public class r implements Serializable, Cloneable, TBase {
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
    public String a;
    public d b;
    public String c;
    public String d;
    public q e;
    public long f;
    public String g;
    public String h;
    private BitSet s;

    public enum a {
        DEBUG((short) 1, "debug"),
        TARGET((short) 2, "target"),
        ID((short) 3, "id"),
        APP_ID((short) 4, DeviceIdModel.mAppId),
        REQUEST((short) 5, "request"),
        ERROR_CODE((short) 6, "errorCode"),
        REASON((short) 7, "reason"),
        PACKAGE_NAME((short) 8, "packageName");
        
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
        yo = new f("XmPushActionUnRegistrationResult");
        fU = new e("debug", (byte) 11, (short) 1);
        fV = new e("target", (byte) 12, (short) 2);
        ya = new e("id", (byte) 11, (short) 3);
        yb = new e(DeviceIdModel.mAppId, (byte) 11, (short) 4);
        yc = new e("request", (byte) 12, (short) 5);
        yd = new e("errorCode", (byte) 10, (short) 6);
        ye = new e("reason", (byte) 11, (short) 7);
        yf = new e("packageName", (byte) 11, (short) 8);
        Map enumMap = new EnumMap(a.class);
        enumMap.put(a.DEBUG, new FieldMetaData("debug", (byte) 2, new FieldValueMetaData((byte) 11)));
        enumMap.put(a.TARGET, new FieldMetaData("target", (byte) 2, new StructMetaData((byte) 12, d.class)));
        enumMap.put(a.ID, new FieldMetaData("id", (byte) 1, new FieldValueMetaData((byte) 11)));
        enumMap.put(a.APP_ID, new FieldMetaData(DeviceIdModel.mAppId, (byte) 1, new FieldValueMetaData((byte) 11)));
        enumMap.put(a.REQUEST, new FieldMetaData("request", (byte) 2, new StructMetaData((byte) 12, q.class)));
        enumMap.put(a.ERROR_CODE, new FieldMetaData("errorCode", (byte) 1, new FieldValueMetaData((byte) 10)));
        enumMap.put(a.REASON, new FieldMetaData("reason", (byte) 2, new FieldValueMetaData((byte) 11)));
        enumMap.put(a.PACKAGE_NAME, new FieldMetaData("packageName", (byte) 2, new FieldValueMetaData((byte) 11)));
        dS = Collections.unmodifiableMap(enumMap);
        FieldMetaData.a(r.class, dS);
    }

    public r() {
        this.s = new BitSet(1);
    }

    public void a(b bVar) {
        bVar.bq();
        while (true) {
            e br = bVar.br();
            if (br.b == (byte) 0) {
                bVar.am();
                if (f()) {
                    fa();
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
                    this.e = new q();
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
                default:
                    c.a(bVar, br.b);
                    break;
            }
            bVar.j();
        }
    }

    public void a(boolean z) {
        this.s.set(0, z);
    }

    public boolean a() {
        return this.a != null;
    }

    public boolean a(r rVar) {
        if (rVar != null) {
            boolean a = a();
            boolean a2 = rVar.a();
            if (!(a || a2) || (a && a2 && this.a.equals(rVar.a))) {
                a = b();
                a2 = rVar.b();
                if (!(a || a2) || (a && a2 && this.b.b(rVar.b))) {
                    a = c();
                    a2 = rVar.c();
                    if (!(a || a2) || (a && a2 && this.c.equals(rVar.c))) {
                        a = d();
                        a2 = rVar.d();
                        if (!(a || a2) || (a && a2 && this.d.equals(rVar.d))) {
                            a = e();
                            a2 = rVar.e();
                            if ((!(a || a2) || (a && a2 && this.e.b(rVar.e))) && this.f == rVar.f) {
                                a = g();
                                a2 = rVar.g();
                                if (!(a || a2) || (a && a2 && this.g.equals(rVar.g))) {
                                    a = an();
                                    a2 = rVar.an();
                                    if (!(a || a2) || (a && a2 && this.h.equals(rVar.h))) {
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

    public boolean an() {
        return this.h != null;
    }

    public int b(r rVar) {
        if (!getClass().equals(rVar.getClass())) {
            return getClass().getName().compareTo(rVar.getClass().getName());
        }
        int compareTo = Boolean.valueOf(a()).compareTo(Boolean.valueOf(rVar.a()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (a()) {
            compareTo = org.apache.thrift.b.f(this.a, rVar.a);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(b()).compareTo(Boolean.valueOf(rVar.b()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (b()) {
            compareTo = org.apache.thrift.b.a(this.b, rVar.b);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(c()).compareTo(Boolean.valueOf(rVar.c()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (c()) {
            compareTo = org.apache.thrift.b.f(this.c, rVar.c);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(d()).compareTo(Boolean.valueOf(rVar.d()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (d()) {
            compareTo = org.apache.thrift.b.f(this.d, rVar.d);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(e()).compareTo(Boolean.valueOf(rVar.e()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (e()) {
            compareTo = org.apache.thrift.b.a(this.e, rVar.e);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(f()).compareTo(Boolean.valueOf(rVar.f()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (f()) {
            compareTo = org.apache.thrift.b.a(this.f, rVar.f);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(g()).compareTo(Boolean.valueOf(rVar.g()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (g()) {
            compareTo = org.apache.thrift.b.f(this.g, rVar.g);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(an()).compareTo(Boolean.valueOf(rVar.an()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (an()) {
            compareTo = org.apache.thrift.b.f(this.h, rVar.h);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        return 0;
    }

    public void b(b bVar) {
        fa();
        bVar.a(yo);
        if (this.a != null && a()) {
            bVar.a(fU);
            bVar.a(this.a);
            bVar.b();
        }
        if (this.b != null && b()) {
            bVar.a(fV);
            this.b.b(bVar);
            bVar.b();
        }
        if (this.c != null) {
            bVar.a(ya);
            bVar.a(this.c);
            bVar.b();
        }
        if (this.d != null) {
            bVar.a(yb);
            bVar.a(this.d);
            bVar.b();
        }
        if (this.e != null && e()) {
            bVar.a(yc);
            this.e.b(bVar);
            bVar.b();
        }
        bVar.a(yd);
        bVar.a(this.f);
        bVar.b();
        if (this.g != null && g()) {
            bVar.a(ye);
            bVar.a(this.g);
            bVar.b();
        }
        if (this.h != null && an()) {
            bVar.a(yf);
            bVar.a(this.h);
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

    public /* synthetic */ int compareTo(Object obj) {
        return b((r) obj);
    }

    public boolean d() {
        return this.d != null;
    }

    public boolean e() {
        return this.e != null;
    }

    public boolean equals(Object obj) {
        return (obj != null && (obj instanceof r)) ? a((r) obj) : false;
    }

    public boolean f() {
        return this.s.get(0);
    }

    public void fa() {
        if (this.c == null) {
            throw new TProtocolException("Required field 'id' was not present! Struct: " + toString());
        } else if (this.d == null) {
            throw new TProtocolException("Required field 'appId' was not present! Struct: " + toString());
        }
    }

    public boolean g() {
        return this.g != null;
    }

    public int hashCode() {
        return 0;
    }

    public String toString() {
        Object obj = null;
        StringBuilder stringBuilder = new StringBuilder("XmPushActionUnRegistrationResult(");
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
            stringBuilder.append("packageName:");
            if (this.h == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.h);
            }
        }
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
