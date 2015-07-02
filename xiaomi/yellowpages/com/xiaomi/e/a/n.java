package com.xiaomi.e.a;

import com.alipay.mobilesecuritysdk.deviceID.DeviceIdModel;
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
import org.apache.thrift.meta_data.StructMetaData;
import org.apache.thrift.protocol.TProtocolException;
import org.apache.thrift.protocol.b;
import org.apache.thrift.protocol.c;
import org.apache.thrift.protocol.d;
import org.apache.thrift.protocol.e;
import org.apache.thrift.protocol.f;

public class n implements Serializable, Cloneable, TBase {
    public static final Map eI;
    private static final f yO;
    private static final e yP;
    private static final e yQ;
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
    public String e;
    public String f;
    public String g;
    public b h;
    public boolean i;
    public Map j;
    public String k;
    private BitSet y;

    public enum a {
        DEBUG((short) 1, "debug"),
        TARGET((short) 2, "target"),
        ID((short) 3, "id"),
        APP_ID((short) 4, DeviceIdModel.mAppId),
        PACKAGE_NAME((short) 5, "packageName"),
        TOPIC((short) 6, "topic"),
        ALIAS_NAME((short) 7, "aliasName"),
        MESSAGE((short) 8, "message"),
        NEED_ACK((short) 9, "needAck"),
        PARAMS((short) 10, MiniDefine.aM),
        CATEGORY((short) 11, "category");
        
        private static final Map eI;
        private final short m;
        private final String n;

        static {
            eI = new HashMap();
            Iterator it = EnumSet.allOf(a.class).iterator();
            while (it.hasNext()) {
                a aVar = (a) it.next();
                eI.put(aVar.a(), aVar);
            }
        }

        private a(short s, String str) {
            this.m = s;
            this.n = str;
        }

        public String a() {
            return this.n;
        }
    }

    static {
        yO = new f("XmPushActionSendMessage");
        yb = new e("debug", (byte) 11, (short) 1);
        yc = new e("target", (byte) 12, (short) 2);
        yd = new e("id", (byte) 11, (short) 3);
        ye = new e(DeviceIdModel.mAppId, (byte) 11, (short) 4);
        yf = new e("packageName", (byte) 11, (short) 5);
        yg = new e("topic", (byte) 11, (short) 6);
        yh = new e("aliasName", (byte) 11, (short) 7);
        yi = new e("message", (byte) 12, (short) 8);
        yj = new e("needAck", (byte) 2, (short) 9);
        yP = new e(MiniDefine.aM, (byte) 13, (short) 10);
        yQ = new e("category", (byte) 11, (short) 11);
        Map enumMap = new EnumMap(a.class);
        enumMap.put(a.DEBUG, new FieldMetaData("debug", (byte) 2, new FieldValueMetaData((byte) 11)));
        enumMap.put(a.TARGET, new FieldMetaData("target", (byte) 2, new StructMetaData((byte) 12, d.class)));
        enumMap.put(a.ID, new FieldMetaData("id", (byte) 1, new FieldValueMetaData((byte) 11)));
        enumMap.put(a.APP_ID, new FieldMetaData(DeviceIdModel.mAppId, (byte) 1, new FieldValueMetaData((byte) 11)));
        enumMap.put(a.PACKAGE_NAME, new FieldMetaData("packageName", (byte) 2, new FieldValueMetaData((byte) 11)));
        enumMap.put(a.TOPIC, new FieldMetaData("topic", (byte) 2, new FieldValueMetaData((byte) 11)));
        enumMap.put(a.ALIAS_NAME, new FieldMetaData("aliasName", (byte) 2, new FieldValueMetaData((byte) 11)));
        enumMap.put(a.MESSAGE, new FieldMetaData("message", (byte) 2, new StructMetaData((byte) 12, b.class)));
        enumMap.put(a.NEED_ACK, new FieldMetaData("needAck", (byte) 2, new FieldValueMetaData((byte) 2)));
        enumMap.put(a.PARAMS, new FieldMetaData(MiniDefine.aM, (byte) 2, new MapMetaData((byte) 13, new FieldValueMetaData((byte) 11), new FieldValueMetaData((byte) 11))));
        enumMap.put(a.CATEGORY, new FieldMetaData("category", (byte) 2, new FieldValueMetaData((byte) 11)));
        eI = Collections.unmodifiableMap(enumMap);
        FieldMetaData.a(n.class, eI);
    }

    public n() {
        this.y = new BitSet(1);
        this.i = true;
    }

    public void a(b bVar) {
        bVar.bq();
        while (true) {
            e br = bVar.br();
            if (br.b == (byte) 0) {
                bVar.am();
                gA();
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
                    this.h = new b();
                    this.h.a(bVar);
                    break;
                case WindowData.j /*9*/:
                    if (br.b != (byte) 2) {
                        c.a(bVar, br.b);
                        break;
                    }
                    this.i = bVar.bx();
                    a(true);
                    break;
                case WindowData.k /*10*/:
                    if (br.b != (byte) 13) {
                        c.a(bVar, br.b);
                        break;
                    }
                    d bs = bVar.bs();
                    this.j = new HashMap(bs.c * 2);
                    for (int i = 0; i < bs.c; i++) {
                        this.j.put(bVar.bD(), bVar.bD());
                    }
                    bVar.ao();
                    break;
                case (short) 11:
                    if (br.b != (byte) 11) {
                        c.a(bVar, br.b);
                        break;
                    } else {
                        this.k = bVar.bD();
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
        this.y.set(0, z);
    }

    public boolean a() {
        return this.a != null;
    }

    public void b(b bVar) {
        gA();
        bVar.a(yO);
        if (this.a != null && a()) {
            bVar.a(yb);
            bVar.a(this.a);
            bVar.b();
        }
        if (this.b != null && b()) {
            bVar.a(yc);
            this.b.b(bVar);
            bVar.b();
        }
        if (this.c != null) {
            bVar.a(yd);
            bVar.a(this.c);
            bVar.b();
        }
        if (this.d != null) {
            bVar.a(ye);
            bVar.a(this.d);
            bVar.b();
        }
        if (this.e != null && g()) {
            bVar.a(yf);
            bVar.a(this.e);
            bVar.b();
        }
        if (this.f != null && i()) {
            bVar.a(yg);
            bVar.a(this.f);
            bVar.b();
        }
        if (this.g != null && k()) {
            bVar.a(yh);
            bVar.a(this.g);
            bVar.b();
        }
        if (this.h != null && m()) {
            bVar.a(yi);
            this.h.b(bVar);
            bVar.b();
        }
        if (gl()) {
            bVar.a(yj);
            bVar.a(this.i);
            bVar.b();
        }
        if (this.j != null && gs()) {
            bVar.a(yP);
            bVar.a(new d((byte) 11, (byte) 11, this.j.size()));
            for (Entry entry : this.j.entrySet()) {
                bVar.a((String) entry.getKey());
                bVar.a((String) entry.getValue());
            }
            bVar.d();
            bVar.b();
        }
        if (this.k != null && bx()) {
            bVar.a(yQ);
            bVar.a(this.k);
            bVar.b();
        }
        bVar.c();
        bVar.a();
    }

    public boolean b() {
        return this.b != null;
    }

    public boolean b(n nVar) {
        if (nVar != null) {
            boolean a = a();
            boolean a2 = nVar.a();
            if (!(a || a2) || (a && a2 && this.a.equals(nVar.a))) {
                a = b();
                a2 = nVar.b();
                if (!(a || a2) || (a && a2 && this.b.b(nVar.b))) {
                    a = d();
                    a2 = nVar.d();
                    if (!(a || a2) || (a && a2 && this.c.equals(nVar.c))) {
                        a = f();
                        a2 = nVar.f();
                        if (!(a || a2) || (a && a2 && this.d.equals(nVar.d))) {
                            a = g();
                            a2 = nVar.g();
                            if (!(a || a2) || (a && a2 && this.e.equals(nVar.e))) {
                                a = i();
                                a2 = nVar.i();
                                if (!(a || a2) || (a && a2 && this.f.equals(nVar.f))) {
                                    a = k();
                                    a2 = nVar.k();
                                    if (!(a || a2) || (a && a2 && this.g.equals(nVar.g))) {
                                        a = m();
                                        a2 = nVar.m();
                                        if (!(a || a2) || (a && a2 && this.h.a(nVar.h))) {
                                            a = gl();
                                            a2 = nVar.gl();
                                            if (!(a || a2) || (a && a2 && this.i == nVar.i)) {
                                                a = gs();
                                                a2 = nVar.gs();
                                                if (!(a || a2) || (a && a2 && this.j.equals(nVar.j))) {
                                                    a = bx();
                                                    a2 = nVar.bx();
                                                    if (!(a || a2) || (a && a2 && this.k.equals(nVar.k))) {
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
        }
        return false;
    }

    public boolean bx() {
        return this.k != null;
    }

    public int c(n nVar) {
        if (!getClass().equals(nVar.getClass())) {
            return getClass().getName().compareTo(nVar.getClass().getName());
        }
        int compareTo = Boolean.valueOf(a()).compareTo(Boolean.valueOf(nVar.a()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (a()) {
            compareTo = org.apache.thrift.b.f(this.a, nVar.a);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(b()).compareTo(Boolean.valueOf(nVar.b()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (b()) {
            compareTo = org.apache.thrift.b.a(this.b, nVar.b);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(d()).compareTo(Boolean.valueOf(nVar.d()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (d()) {
            compareTo = org.apache.thrift.b.f(this.c, nVar.c);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(f()).compareTo(Boolean.valueOf(nVar.f()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (f()) {
            compareTo = org.apache.thrift.b.f(this.d, nVar.d);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(g()).compareTo(Boolean.valueOf(nVar.g()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (g()) {
            compareTo = org.apache.thrift.b.f(this.e, nVar.e);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(i()).compareTo(Boolean.valueOf(nVar.i()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (i()) {
            compareTo = org.apache.thrift.b.f(this.f, nVar.f);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(k()).compareTo(Boolean.valueOf(nVar.k()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (k()) {
            compareTo = org.apache.thrift.b.f(this.g, nVar.g);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m()).compareTo(Boolean.valueOf(nVar.m()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m()) {
            compareTo = org.apache.thrift.b.a(this.h, nVar.h);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(gl()).compareTo(Boolean.valueOf(nVar.gl()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (gl()) {
            compareTo = org.apache.thrift.b.a(this.i, nVar.i);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(gs()).compareTo(Boolean.valueOf(nVar.gs()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (gs()) {
            compareTo = org.apache.thrift.b.a(this.j, nVar.j);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(bx()).compareTo(Boolean.valueOf(nVar.bx()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (bx()) {
            compareTo = org.apache.thrift.b.f(this.k, nVar.k);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        return 0;
    }

    public String c() {
        return this.c;
    }

    public /* synthetic */ int compareTo(Object obj) {
        return c((n) obj);
    }

    public boolean d() {
        return this.c != null;
    }

    public String e() {
        return this.d;
    }

    public boolean equals(Object obj) {
        return (obj != null && (obj instanceof n)) ? b((n) obj) : false;
    }

    public boolean f() {
        return this.d != null;
    }

    public boolean g() {
        return this.e != null;
    }

    public void gA() {
        if (this.c == null) {
            throw new TProtocolException("Required field 'id' was not present! Struct: " + toString());
        } else if (this.d == null) {
            throw new TProtocolException("Required field 'appId' was not present! Struct: " + toString());
        }
    }

    public boolean gl() {
        return this.y.get(0);
    }

    public boolean gs() {
        return this.j != null;
    }

    public b gy() {
        return this.h;
    }

    public String gz() {
        return this.k;
    }

    public String h() {
        return this.f;
    }

    public int hashCode() {
        return 0;
    }

    public boolean i() {
        return this.f != null;
    }

    public String j() {
        return this.g;
    }

    public boolean k() {
        return this.g != null;
    }

    public boolean m() {
        return this.h != null;
    }

    public String toString() {
        Object obj = null;
        StringBuilder stringBuilder = new StringBuilder("XmPushActionSendMessage(");
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
        if (g()) {
            stringBuilder.append(", ");
            stringBuilder.append("packageName:");
            if (this.e == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.e);
            }
        }
        if (i()) {
            stringBuilder.append(", ");
            stringBuilder.append("topic:");
            if (this.f == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.f);
            }
        }
        if (k()) {
            stringBuilder.append(", ");
            stringBuilder.append("aliasName:");
            if (this.g == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.g);
            }
        }
        if (m()) {
            stringBuilder.append(", ");
            stringBuilder.append("message:");
            if (this.h == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.h);
            }
        }
        if (gl()) {
            stringBuilder.append(", ");
            stringBuilder.append("needAck:");
            stringBuilder.append(this.i);
        }
        if (gs()) {
            stringBuilder.append(", ");
            stringBuilder.append("params:");
            if (this.j == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.j);
            }
        }
        if (bx()) {
            stringBuilder.append(", ");
            stringBuilder.append("category:");
            if (this.k == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.k);
            }
        }
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
