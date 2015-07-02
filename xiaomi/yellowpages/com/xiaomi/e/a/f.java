package com.xiaomi.e.a;

import com.alipay.mobilesecuritysdk.deviceID.DeviceIdModel;
import com.alipay.sdk.protocol.WindowData;
import com.ta.utdid2.android.utils.Base64;
import com.ta.utdid2.core.persistent.TransactionXMLFile;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.thrift.TBase;
import org.apache.thrift.meta_data.FieldMetaData;
import org.apache.thrift.meta_data.FieldValueMetaData;
import org.apache.thrift.meta_data.ListMetaData;
import org.apache.thrift.meta_data.StructMetaData;
import org.apache.thrift.protocol.TProtocolException;
import org.apache.thrift.protocol.b;
import org.apache.thrift.protocol.c;
import org.apache.thrift.protocol.e;
import org.apache.thrift.protocol.g;

public class f implements Serializable, Cloneable, TBase {
    public static final Map dS;
    private static final e fU;
    private static final e fV;
    private static final e ya;
    private static final e yb;
    private static final e yc;
    private static final e yd;
    private static final e ye;
    private static final e yf;
    private static final org.apache.thrift.protocol.f yo;
    public String a;
    public d b;
    public String c;
    public String d;
    public String e;
    public List f;
    public String g;
    public String h;

    public enum a {
        DEBUG((short) 1, "debug"),
        TARGET((short) 2, "target"),
        ID((short) 3, "id"),
        APP_ID((short) 4, DeviceIdModel.mAppId),
        CMD_NAME((short) 5, "cmdName"),
        CMD_ARGS((short) 6, "cmdArgs"),
        PACKAGE_NAME((short) 7, "packageName"),
        CATEGORY((short) 9, "category");
        
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
        yo = new org.apache.thrift.protocol.f("XmPushActionCommand");
        fU = new e("debug", (byte) 11, (short) 1);
        fV = new e("target", (byte) 12, (short) 2);
        ya = new e("id", (byte) 11, (short) 3);
        yb = new e(DeviceIdModel.mAppId, (byte) 11, (short) 4);
        yc = new e("cmdName", (byte) 11, (short) 5);
        yd = new e("cmdArgs", (byte) 15, (short) 6);
        ye = new e("packageName", (byte) 11, (short) 7);
        yf = new e("category", (byte) 11, (short) 9);
        Map enumMap = new EnumMap(a.class);
        enumMap.put(a.DEBUG, new FieldMetaData("debug", (byte) 2, new FieldValueMetaData((byte) 11)));
        enumMap.put(a.TARGET, new FieldMetaData("target", (byte) 2, new StructMetaData((byte) 12, d.class)));
        enumMap.put(a.ID, new FieldMetaData("id", (byte) 1, new FieldValueMetaData((byte) 11)));
        enumMap.put(a.APP_ID, new FieldMetaData(DeviceIdModel.mAppId, (byte) 1, new FieldValueMetaData((byte) 11)));
        enumMap.put(a.CMD_NAME, new FieldMetaData("cmdName", (byte) 1, new FieldValueMetaData((byte) 11)));
        enumMap.put(a.CMD_ARGS, new FieldMetaData("cmdArgs", (byte) 2, new ListMetaData((byte) 15, new FieldValueMetaData((byte) 11))));
        enumMap.put(a.PACKAGE_NAME, new FieldMetaData("packageName", (byte) 2, new FieldValueMetaData((byte) 11)));
        enumMap.put(a.CATEGORY, new FieldMetaData("category", (byte) 2, new FieldValueMetaData((byte) 11)));
        dS = Collections.unmodifiableMap(enumMap);
        FieldMetaData.a(f.class, dS);
    }

    public void a(b bVar) {
        bVar.bq();
        while (true) {
            e br = bVar.br();
            if (br.b == (byte) 0) {
                bVar.am();
                fa();
                return;
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
                    if (br.b != (byte) 11) {
                        c.a(bVar, br.b);
                        break;
                    } else {
                        this.e = bVar.bD();
                        break;
                    }
                case WindowData.g /*6*/:
                    if (br.b != (byte) 15) {
                        c.a(bVar, br.b);
                        break;
                    }
                    g bt = bVar.bt();
                    this.f = new ArrayList(bt.b);
                    for (int i = 0; i < bt.b; i++) {
                        this.f.add(bVar.bD());
                    }
                    bVar.bu();
                    break;
                case WindowData.h /*7*/:
                    if (br.b != (byte) 11) {
                        c.a(bVar, br.b);
                        break;
                    } else {
                        this.g = bVar.bD();
                        break;
                    }
                case WindowData.j /*9*/:
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

    public boolean a() {
        return this.a != null;
    }

    public boolean a(f fVar) {
        if (fVar != null) {
            boolean a = a();
            boolean a2 = fVar.a();
            if (!(a || a2) || (a && a2 && this.a.equals(fVar.a))) {
                a = b();
                a2 = fVar.b();
                if (!(a || a2) || (a && a2 && this.b.b(fVar.b))) {
                    a = c();
                    a2 = fVar.c();
                    if (!(a || a2) || (a && a2 && this.c.equals(fVar.c))) {
                        a = d();
                        a2 = fVar.d();
                        if (!(a || a2) || (a && a2 && this.d.equals(fVar.d))) {
                            a = e();
                            a2 = fVar.e();
                            if (!(a || a2) || (a && a2 && this.e.equals(fVar.e))) {
                                a = f();
                                a2 = fVar.f();
                                if (!(a || a2) || (a && a2 && this.f.equals(fVar.f))) {
                                    a = g();
                                    a2 = fVar.g();
                                    if (!(a || a2) || (a && a2 && this.g.equals(fVar.g))) {
                                        a = an();
                                        a2 = fVar.an();
                                        if (!(a || a2) || (a && a2 && this.h.equals(fVar.h))) {
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
        return this.h != null;
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
            compareTo = org.apache.thrift.b.f(this.a, fVar.a);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(b()).compareTo(Boolean.valueOf(fVar.b()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (b()) {
            compareTo = org.apache.thrift.b.a(this.b, fVar.b);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(c()).compareTo(Boolean.valueOf(fVar.c()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (c()) {
            compareTo = org.apache.thrift.b.f(this.c, fVar.c);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(d()).compareTo(Boolean.valueOf(fVar.d()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (d()) {
            compareTo = org.apache.thrift.b.f(this.d, fVar.d);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(e()).compareTo(Boolean.valueOf(fVar.e()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (e()) {
            compareTo = org.apache.thrift.b.f(this.e, fVar.e);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(f()).compareTo(Boolean.valueOf(fVar.f()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (f()) {
            compareTo = org.apache.thrift.b.a(this.f, fVar.f);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(g()).compareTo(Boolean.valueOf(fVar.g()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (g()) {
            compareTo = org.apache.thrift.b.f(this.g, fVar.g);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(an()).compareTo(Boolean.valueOf(fVar.an()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (an()) {
            compareTo = org.apache.thrift.b.f(this.h, fVar.h);
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
        if (this.e != null) {
            bVar.a(yc);
            bVar.a(this.e);
            bVar.b();
        }
        if (this.f != null && f()) {
            bVar.a(yd);
            bVar.a(new g((byte) 11, this.f.size()));
            for (String a : this.f) {
                bVar.a(a);
            }
            bVar.e();
            bVar.b();
        }
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

    public f bA(String str) {
        this.g = str;
        return this;
    }

    public f bB(String str) {
        this.h = str;
        return this;
    }

    public f bx(String str) {
        this.c = str;
        return this;
    }

    public f by(String str) {
        this.d = str;
        return this;
    }

    public f bz(String str) {
        this.e = str;
        return this;
    }

    public boolean c() {
        return this.c != null;
    }

    public /* synthetic */ int compareTo(Object obj) {
        return b((f) obj);
    }

    public void d(String str) {
        if (this.f == null) {
            this.f = new ArrayList();
        }
        this.f.add(str);
    }

    public boolean d() {
        return this.d != null;
    }

    public boolean e() {
        return this.e != null;
    }

    public boolean equals(Object obj) {
        return (obj != null && (obj instanceof f)) ? a((f) obj) : false;
    }

    public boolean f() {
        return this.f != null;
    }

    public void fa() {
        if (this.c == null) {
            throw new TProtocolException("Required field 'id' was not present! Struct: " + toString());
        } else if (this.d == null) {
            throw new TProtocolException("Required field 'appId' was not present! Struct: " + toString());
        } else if (this.e == null) {
            throw new TProtocolException("Required field 'cmdName' was not present! Struct: " + toString());
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
        StringBuilder stringBuilder = new StringBuilder("XmPushActionCommand(");
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
        stringBuilder.append("cmdName:");
        if (this.e == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.e);
        }
        if (f()) {
            stringBuilder.append(", ");
            stringBuilder.append("cmdArgs:");
            if (this.f == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.f);
            }
        }
        if (g()) {
            stringBuilder.append(", ");
            stringBuilder.append("packageName:");
            if (this.g == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.g);
            }
        }
        if (an()) {
            stringBuilder.append(", ");
            stringBuilder.append("category:");
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
