package com.xiaomi.e.a;

import com.alipay.mobilesecuritysdk.deviceID.DeviceIdModel;
import com.alipay.sdk.protocol.WindowData;
import com.ta.utdid2.android.utils.Base64;
import com.ta.utdid2.core.persistent.TransactionXMLFile;
import java.io.Serializable;
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
import org.apache.thrift.meta_data.StructMetaData;
import org.apache.thrift.protocol.TProtocolException;
import org.apache.thrift.protocol.b;
import org.apache.thrift.protocol.c;
import org.apache.thrift.protocol.d;
import org.apache.thrift.protocol.e;
import org.apache.thrift.protocol.f;

public class l implements Serializable, Cloneable, TBase {
    private static final e fT;
    private static final e fU;
    private static final e fV;
    private static final e fs;
    private static final e ya;
    private static final e yb;
    public static final Map yk;
    private static final f yl;
    public String a;
    public d b;
    public String c;
    public String d;
    public Map e;
    public String f;

    public enum a {
        DEBUG((short) 1, "debug"),
        TARGET((short) 2, "target"),
        ID((short) 3, "id"),
        APP_ID((short) 4, DeviceIdModel.mAppId),
        FEEDBACKS((short) 5, "feedbacks"),
        CATEGORY((short) 6, "category");
        
        private static final Map yk;
        private final short h;
        private final String i;

        static {
            yk = new HashMap();
            Iterator it = EnumSet.allOf(a.class).iterator();
            while (it.hasNext()) {
                a aVar = (a) it.next();
                yk.put(aVar.a(), aVar);
            }
        }

        private a(short s, String str) {
            this.h = s;
            this.i = str;
        }

        public String a() {
            return this.i;
        }
    }

    static {
        yl = new f("XmPushActionSendFeedback");
        fs = new e("debug", (byte) 11, (short) 1);
        fT = new e("target", (byte) 12, (short) 2);
        fU = new e("id", (byte) 11, (short) 3);
        fV = new e(DeviceIdModel.mAppId, (byte) 11, (short) 4);
        ya = new e("feedbacks", (byte) 13, (short) 5);
        yb = new e("category", (byte) 11, (short) 6);
        Map enumMap = new EnumMap(a.class);
        enumMap.put(a.DEBUG, new FieldMetaData("debug", (byte) 2, new FieldValueMetaData((byte) 11)));
        enumMap.put(a.TARGET, new FieldMetaData("target", (byte) 2, new StructMetaData((byte) 12, d.class)));
        enumMap.put(a.ID, new FieldMetaData("id", (byte) 1, new FieldValueMetaData((byte) 11)));
        enumMap.put(a.APP_ID, new FieldMetaData(DeviceIdModel.mAppId, (byte) 1, new FieldValueMetaData((byte) 11)));
        enumMap.put(a.FEEDBACKS, new FieldMetaData("feedbacks", (byte) 2, new MapMetaData((byte) 13, new FieldValueMetaData((byte) 11), new FieldValueMetaData((byte) 11))));
        enumMap.put(a.CATEGORY, new FieldMetaData("category", (byte) 2, new FieldValueMetaData((byte) 11)));
        yk = Collections.unmodifiableMap(enumMap);
        FieldMetaData.a(l.class, yk);
    }

    public void a(b bVar) {
        bVar.bq();
        while (true) {
            e br = bVar.br();
            if (br.b == (byte) 0) {
                bVar.am();
                g();
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
                    if (br.b != (byte) 13) {
                        c.a(bVar, br.b);
                        break;
                    }
                    d bs = bVar.bs();
                    this.e = new HashMap(bs.c * 2);
                    for (int i = 0; i < bs.c; i++) {
                        this.e.put(bVar.bD(), bVar.bD());
                    }
                    bVar.ao();
                    break;
                case WindowData.g /*6*/:
                    if (br.b != (byte) 11) {
                        c.a(bVar, br.b);
                        break;
                    } else {
                        this.f = bVar.bD();
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

    public boolean a(l lVar) {
        if (lVar != null) {
            boolean a = a();
            boolean a2 = lVar.a();
            if (!(a || a2) || (a && a2 && this.a.equals(lVar.a))) {
                a = b();
                a2 = lVar.b();
                if (!(a || a2) || (a && a2 && this.b.b(lVar.b))) {
                    a = c();
                    a2 = lVar.c();
                    if (!(a || a2) || (a && a2 && this.c.equals(lVar.c))) {
                        a = d();
                        a2 = lVar.d();
                        if (!(a || a2) || (a && a2 && this.d.equals(lVar.d))) {
                            a = e();
                            a2 = lVar.e();
                            if (!(a || a2) || (a && a2 && this.e.equals(lVar.e))) {
                                a = f();
                                a2 = lVar.f();
                                if (!(a || a2) || (a && a2 && this.f.equals(lVar.f))) {
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

    public int b(l lVar) {
        if (!getClass().equals(lVar.getClass())) {
            return getClass().getName().compareTo(lVar.getClass().getName());
        }
        int compareTo = Boolean.valueOf(a()).compareTo(Boolean.valueOf(lVar.a()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (a()) {
            compareTo = org.apache.thrift.b.f(this.a, lVar.a);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(b()).compareTo(Boolean.valueOf(lVar.b()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (b()) {
            compareTo = org.apache.thrift.b.a(this.b, lVar.b);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(c()).compareTo(Boolean.valueOf(lVar.c()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (c()) {
            compareTo = org.apache.thrift.b.f(this.c, lVar.c);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(d()).compareTo(Boolean.valueOf(lVar.d()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (d()) {
            compareTo = org.apache.thrift.b.f(this.d, lVar.d);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(e()).compareTo(Boolean.valueOf(lVar.e()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (e()) {
            compareTo = org.apache.thrift.b.a(this.e, lVar.e);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(f()).compareTo(Boolean.valueOf(lVar.f()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (f()) {
            compareTo = org.apache.thrift.b.f(this.f, lVar.f);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        return 0;
    }

    public void b(b bVar) {
        g();
        bVar.a(yl);
        if (this.a != null && a()) {
            bVar.a(fs);
            bVar.a(this.a);
            bVar.b();
        }
        if (this.b != null && b()) {
            bVar.a(fT);
            this.b.b(bVar);
            bVar.b();
        }
        if (this.c != null) {
            bVar.a(fU);
            bVar.a(this.c);
            bVar.b();
        }
        if (this.d != null) {
            bVar.a(fV);
            bVar.a(this.d);
            bVar.b();
        }
        if (this.e != null && e()) {
            bVar.a(ya);
            bVar.a(new d((byte) 11, (byte) 11, this.e.size()));
            for (Entry entry : this.e.entrySet()) {
                bVar.a((String) entry.getKey());
                bVar.a((String) entry.getValue());
            }
            bVar.d();
            bVar.b();
        }
        if (this.f != null && f()) {
            bVar.a(yb);
            bVar.a(this.f);
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
        return b((l) obj);
    }

    public boolean d() {
        return this.d != null;
    }

    public boolean e() {
        return this.e != null;
    }

    public boolean equals(Object obj) {
        return (obj != null && (obj instanceof l)) ? a((l) obj) : false;
    }

    public boolean f() {
        return this.f != null;
    }

    public void g() {
        if (this.c == null) {
            throw new TProtocolException("Required field 'id' was not present! Struct: " + toString());
        } else if (this.d == null) {
            throw new TProtocolException("Required field 'appId' was not present! Struct: " + toString());
        }
    }

    public int hashCode() {
        return 0;
    }

    public String toString() {
        Object obj = null;
        StringBuilder stringBuilder = new StringBuilder("XmPushActionSendFeedback(");
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
            stringBuilder.append("feedbacks:");
            if (this.e == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.e);
            }
        }
        if (f()) {
            stringBuilder.append(", ");
            stringBuilder.append("category:");
            if (this.f == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.f);
            }
        }
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
