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
import org.apache.thrift.protocol.c;
import org.apache.thrift.protocol.e;
import org.apache.thrift.protocol.f;

public class b implements Serializable, Cloneable, TBase {
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
    public d a;
    public String b;
    public String c;
    public String d;
    public long e;
    public long f;
    public String g;
    public String h;
    private BitSet s;

    public enum a {
        TO((short) 1, "to"),
        ID((short) 2, "id"),
        APP_ID((short) 3, DeviceIdModel.mAppId),
        PAYLOAD((short) 4, "payload"),
        CREATE_AT((short) 5, "createAt"),
        TTL((short) 6, "ttl"),
        COLLAPSE_KEY((short) 7, "collapseKey"),
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
        yo = new f("PushMessage");
        fU = new e("to", (byte) 12, (short) 1);
        fV = new e("id", (byte) 11, (short) 2);
        ya = new e(DeviceIdModel.mAppId, (byte) 11, (short) 3);
        yb = new e("payload", (byte) 11, (short) 4);
        yc = new e("createAt", (byte) 10, (short) 5);
        yd = new e("ttl", (byte) 10, (short) 6);
        ye = new e("collapseKey", (byte) 11, (short) 7);
        yf = new e("packageName", (byte) 11, (short) 8);
        Map enumMap = new EnumMap(a.class);
        enumMap.put(a.TO, new FieldMetaData("to", (byte) 2, new StructMetaData((byte) 12, d.class)));
        enumMap.put(a.ID, new FieldMetaData("id", (byte) 1, new FieldValueMetaData((byte) 11)));
        enumMap.put(a.APP_ID, new FieldMetaData(DeviceIdModel.mAppId, (byte) 1, new FieldValueMetaData((byte) 11)));
        enumMap.put(a.PAYLOAD, new FieldMetaData("payload", (byte) 1, new FieldValueMetaData((byte) 11)));
        enumMap.put(a.CREATE_AT, new FieldMetaData("createAt", (byte) 2, new FieldValueMetaData((byte) 10)));
        enumMap.put(a.TTL, new FieldMetaData("ttl", (byte) 2, new FieldValueMetaData((byte) 10)));
        enumMap.put(a.COLLAPSE_KEY, new FieldMetaData("collapseKey", (byte) 2, new FieldValueMetaData((byte) 11)));
        enumMap.put(a.PACKAGE_NAME, new FieldMetaData("packageName", (byte) 2, new FieldValueMetaData((byte) 11)));
        dS = Collections.unmodifiableMap(enumMap);
        FieldMetaData.a(b.class, dS);
    }

    public b() {
        this.s = new BitSet(2);
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
                    if (br.b != (byte) 12) {
                        c.a(bVar, br.b);
                        break;
                    }
                    this.a = new d();
                    this.a.a(bVar);
                    break;
                case TransactionXMLFile.MODE_WORLD_WRITEABLE /*2*/:
                    if (br.b != (byte) 11) {
                        c.a(bVar, br.b);
                        break;
                    } else {
                        this.b = bVar.bD();
                        break;
                    }
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
                    if (br.b != (byte) 10) {
                        c.a(bVar, br.b);
                        break;
                    }
                    this.f = bVar.bB();
                    b(true);
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

    public boolean a(b bVar) {
        if (bVar != null) {
            boolean a = a();
            boolean a2 = bVar.a();
            if (!(a || a2) || (a && a2 && this.a.b(bVar.a))) {
                a = c();
                a2 = bVar.c();
                if (!(a || a2) || (a && a2 && this.b.equals(bVar.b))) {
                    a = d();
                    a2 = bVar.d();
                    if (!(a || a2) || (a && a2 && this.c.equals(bVar.c))) {
                        a = f();
                        a2 = bVar.f();
                        if (!(a || a2) || (a && a2 && this.d.equals(bVar.d))) {
                            a = an();
                            a2 = bVar.an();
                            if (!(a || a2) || (a && a2 && this.e == bVar.e)) {
                                a = i();
                                a2 = bVar.i();
                                if (!(a || a2) || (a && a2 && this.f == bVar.f)) {
                                    a = j();
                                    a2 = bVar.j();
                                    if (!(a || a2) || (a && a2 && this.g.equals(bVar.g))) {
                                        a = k();
                                        a2 = bVar.k();
                                        if (!(a || a2) || (a && a2 && this.h.equals(bVar.h))) {
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
        return false;
    }

    public boolean an() {
        return this.s.get(0);
    }

    public void ao() {
        if (this.b == null) {
            throw new TProtocolException("Required field 'id' was not present! Struct: " + toString());
        } else if (this.c == null) {
            throw new TProtocolException("Required field 'appId' was not present! Struct: " + toString());
        } else if (this.d == null) {
            throw new TProtocolException("Required field 'payload' was not present! Struct: " + toString());
        }
    }

    public int b(b bVar) {
        if (!getClass().equals(bVar.getClass())) {
            return getClass().getName().compareTo(bVar.getClass().getName());
        }
        int compareTo = Boolean.valueOf(a()).compareTo(Boolean.valueOf(bVar.a()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (a()) {
            compareTo = org.apache.thrift.b.a(this.a, bVar.a);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(c()).compareTo(Boolean.valueOf(bVar.c()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (c()) {
            compareTo = org.apache.thrift.b.f(this.b, bVar.b);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(d()).compareTo(Boolean.valueOf(bVar.d()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (d()) {
            compareTo = org.apache.thrift.b.f(this.c, bVar.c);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(f()).compareTo(Boolean.valueOf(bVar.f()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (f()) {
            compareTo = org.apache.thrift.b.f(this.d, bVar.d);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(an()).compareTo(Boolean.valueOf(bVar.an()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (an()) {
            compareTo = org.apache.thrift.b.a(this.e, bVar.e);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(i()).compareTo(Boolean.valueOf(bVar.i()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (i()) {
            compareTo = org.apache.thrift.b.a(this.f, bVar.f);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(j()).compareTo(Boolean.valueOf(bVar.j()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (j()) {
            compareTo = org.apache.thrift.b.f(this.g, bVar.g);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(k()).compareTo(Boolean.valueOf(bVar.k()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (k()) {
            compareTo = org.apache.thrift.b.f(this.h, bVar.h);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        return 0;
    }

    public String b() {
        return this.b;
    }

    public void b(org.apache.thrift.protocol.b bVar) {
        ao();
        bVar.a(yo);
        if (this.a != null && a()) {
            bVar.a(fU);
            this.a.b(bVar);
            bVar.b();
        }
        if (this.b != null) {
            bVar.a(fV);
            bVar.a(this.b);
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
        if (an()) {
            bVar.a(yc);
            bVar.a(this.e);
            bVar.b();
        }
        if (i()) {
            bVar.a(yd);
            bVar.a(this.f);
            bVar.b();
        }
        if (this.g != null && j()) {
            bVar.a(ye);
            bVar.a(this.g);
            bVar.b();
        }
        if (this.h != null && k()) {
            bVar.a(yf);
            bVar.a(this.h);
            bVar.b();
        }
        bVar.c();
        bVar.a();
    }

    public void b(boolean z) {
        this.s.set(1, z);
    }

    public boolean c() {
        return this.b != null;
    }

    public /* synthetic */ int compareTo(Object obj) {
        return b((b) obj);
    }

    public boolean d() {
        return this.c != null;
    }

    public String e() {
        return this.d;
    }

    public boolean equals(Object obj) {
        return (obj != null && (obj instanceof b)) ? a((b) obj) : false;
    }

    public boolean f() {
        return this.d != null;
    }

    public long gq() {
        return this.e;
    }

    public int hashCode() {
        return 0;
    }

    public boolean i() {
        return this.s.get(1);
    }

    public boolean j() {
        return this.g != null;
    }

    public boolean k() {
        return this.h != null;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("PushMessage(");
        Object obj = 1;
        if (a()) {
            stringBuilder.append("to:");
            if (this.a == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.a);
            }
            obj = null;
        }
        if (obj == null) {
            stringBuilder.append(", ");
        }
        stringBuilder.append("id:");
        if (this.b == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.b);
        }
        stringBuilder.append(", ");
        stringBuilder.append("appId:");
        if (this.c == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.c);
        }
        stringBuilder.append(", ");
        stringBuilder.append("payload:");
        if (this.d == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.d);
        }
        if (an()) {
            stringBuilder.append(", ");
            stringBuilder.append("createAt:");
            stringBuilder.append(this.e);
        }
        if (i()) {
            stringBuilder.append(", ");
            stringBuilder.append("ttl:");
            stringBuilder.append(this.f);
        }
        if (j()) {
            stringBuilder.append(", ");
            stringBuilder.append("collapseKey:");
            if (this.g == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.g);
            }
        }
        if (k()) {
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
