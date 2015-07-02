package com.xiaomi.e.a;

import com.alipay.mobilesecuritysdk.deviceID.DeviceIdModel;
import com.alipay.sdk.protocol.WindowData;
import com.ta.utdid2.android.utils.Base64;
import com.ta.utdid2.core.persistent.TransactionXMLFile;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.BitSet;
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
import org.apache.thrift.protocol.f;

public class g implements Serializable, Cloneable, TBase {
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
    public f f;
    public long g;
    public String h;
    public String i;
    public List j;
    public String k;
    private BitSet y;

    public enum a {
        DEBUG((short) 1, "debug"),
        TARGET((short) 2, "target"),
        ID((short) 3, "id"),
        APP_ID((short) 4, DeviceIdModel.mAppId),
        CMD_NAME((short) 5, "cmdName"),
        REQUEST((short) 6, "request"),
        ERROR_CODE((short) 7, "errorCode"),
        REASON((short) 8, "reason"),
        PACKAGE_NAME((short) 9, "packageName"),
        CMD_ARGS((short) 10, "cmdArgs"),
        CATEGORY((short) 12, "category");
        
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
        yO = new f("XmPushActionCommandResult");
        yb = new e("debug", (byte) 11, (short) 1);
        yc = new e("target", (byte) 12, (short) 2);
        yd = new e("id", (byte) 11, (short) 3);
        ye = new e(DeviceIdModel.mAppId, (byte) 11, (short) 4);
        yf = new e("cmdName", (byte) 11, (short) 5);
        yg = new e("request", (byte) 12, (short) 6);
        yh = new e("errorCode", (byte) 10, (short) 7);
        yi = new e("reason", (byte) 11, (short) 8);
        yj = new e("packageName", (byte) 11, (short) 9);
        yP = new e("cmdArgs", (byte) 15, (short) 10);
        yQ = new e("category", (byte) 11, (short) 12);
        Map enumMap = new EnumMap(a.class);
        enumMap.put(a.DEBUG, new FieldMetaData("debug", (byte) 2, new FieldValueMetaData((byte) 11)));
        enumMap.put(a.TARGET, new FieldMetaData("target", (byte) 2, new StructMetaData((byte) 12, d.class)));
        enumMap.put(a.ID, new FieldMetaData("id", (byte) 1, new FieldValueMetaData((byte) 11)));
        enumMap.put(a.APP_ID, new FieldMetaData(DeviceIdModel.mAppId, (byte) 1, new FieldValueMetaData((byte) 11)));
        enumMap.put(a.CMD_NAME, new FieldMetaData("cmdName", (byte) 1, new FieldValueMetaData((byte) 11)));
        enumMap.put(a.REQUEST, new FieldMetaData("request", (byte) 2, new StructMetaData((byte) 12, f.class)));
        enumMap.put(a.ERROR_CODE, new FieldMetaData("errorCode", (byte) 1, new FieldValueMetaData((byte) 10)));
        enumMap.put(a.REASON, new FieldMetaData("reason", (byte) 2, new FieldValueMetaData((byte) 11)));
        enumMap.put(a.PACKAGE_NAME, new FieldMetaData("packageName", (byte) 2, new FieldValueMetaData((byte) 11)));
        enumMap.put(a.CMD_ARGS, new FieldMetaData("cmdArgs", (byte) 2, new ListMetaData((byte) 15, new FieldValueMetaData((byte) 11))));
        enumMap.put(a.CATEGORY, new FieldMetaData("category", (byte) 2, new FieldValueMetaData((byte) 11)));
        eI = Collections.unmodifiableMap(enumMap);
        FieldMetaData.a(g.class, eI);
    }

    public g() {
        this.y = new BitSet(1);
    }

    public void a(b bVar) {
        bVar.bq();
        while (true) {
            e br = bVar.br();
            if (br.b == (byte) 0) {
                bVar.am();
                if (an()) {
                    gm();
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
                    if (br.b != (byte) 11) {
                        c.a(bVar, br.b);
                        break;
                    } else {
                        this.e = bVar.bD();
                        break;
                    }
                case WindowData.g /*6*/:
                    if (br.b != (byte) 12) {
                        c.a(bVar, br.b);
                        break;
                    }
                    this.f = new f();
                    this.f.a(bVar);
                    break;
                case WindowData.h /*7*/:
                    if (br.b != (byte) 10) {
                        c.a(bVar, br.b);
                        break;
                    }
                    this.g = bVar.bB();
                    a(true);
                    break;
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
                    if (br.b != (byte) 15) {
                        c.a(bVar, br.b);
                        break;
                    }
                    org.apache.thrift.protocol.g bt = bVar.bt();
                    this.j = new ArrayList(bt.b);
                    for (int i = 0; i < bt.b; i++) {
                        this.j.add(bVar.bD());
                    }
                    bVar.bu();
                    break;
                case (short) 12:
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

    public boolean a(g gVar) {
        if (gVar != null) {
            boolean a = a();
            boolean a2 = gVar.a();
            if (!(a || a2) || (a && a2 && this.a.equals(gVar.a))) {
                a = b();
                a2 = gVar.b();
                if (!(a || a2) || (a && a2 && this.b.b(gVar.b))) {
                    a = c();
                    a2 = gVar.c();
                    if (!(a || a2) || (a && a2 && this.c.equals(gVar.c))) {
                        a = d();
                        a2 = gVar.d();
                        if (!(a || a2) || (a && a2 && this.d.equals(gVar.d))) {
                            a = f();
                            a2 = gVar.f();
                            if (!(a || a2) || (a && a2 && this.e.equals(gVar.e))) {
                                a = g();
                                a2 = gVar.g();
                                if ((!(a || a2) || (a && a2 && this.f.a(gVar.f))) && this.g == gVar.g) {
                                    a = i();
                                    a2 = gVar.i();
                                    if (!(a || a2) || (a && a2 && this.h.equals(gVar.h))) {
                                        a = j();
                                        a2 = gVar.j();
                                        if (!(a || a2) || (a && a2 && this.i.equals(gVar.i))) {
                                            a = l();
                                            a2 = gVar.l();
                                            if (!(a || a2) || (a && a2 && this.j.equals(gVar.j))) {
                                                a = gl();
                                                a2 = gVar.gl();
                                                if (!(a || a2) || (a && a2 && this.k.equals(gVar.k))) {
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

    public boolean an() {
        return this.y.get(0);
    }

    public int b(g gVar) {
        if (!getClass().equals(gVar.getClass())) {
            return getClass().getName().compareTo(gVar.getClass().getName());
        }
        int compareTo = Boolean.valueOf(a()).compareTo(Boolean.valueOf(gVar.a()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (a()) {
            compareTo = org.apache.thrift.b.f(this.a, gVar.a);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(b()).compareTo(Boolean.valueOf(gVar.b()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (b()) {
            compareTo = org.apache.thrift.b.a(this.b, gVar.b);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(c()).compareTo(Boolean.valueOf(gVar.c()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (c()) {
            compareTo = org.apache.thrift.b.f(this.c, gVar.c);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(d()).compareTo(Boolean.valueOf(gVar.d()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (d()) {
            compareTo = org.apache.thrift.b.f(this.d, gVar.d);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(f()).compareTo(Boolean.valueOf(gVar.f()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (f()) {
            compareTo = org.apache.thrift.b.f(this.e, gVar.e);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(g()).compareTo(Boolean.valueOf(gVar.g()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (g()) {
            compareTo = org.apache.thrift.b.a(this.f, gVar.f);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(an()).compareTo(Boolean.valueOf(gVar.an()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (an()) {
            compareTo = org.apache.thrift.b.a(this.g, gVar.g);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(i()).compareTo(Boolean.valueOf(gVar.i()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (i()) {
            compareTo = org.apache.thrift.b.f(this.h, gVar.h);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(j()).compareTo(Boolean.valueOf(gVar.j()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (j()) {
            compareTo = org.apache.thrift.b.f(this.i, gVar.i);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(l()).compareTo(Boolean.valueOf(gVar.l()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (l()) {
            compareTo = org.apache.thrift.b.a(this.j, gVar.j);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(gl()).compareTo(Boolean.valueOf(gVar.gl()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (gl()) {
            compareTo = org.apache.thrift.b.f(this.k, gVar.k);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        return 0;
    }

    public void b(b bVar) {
        gm();
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
        if (this.e != null) {
            bVar.a(yf);
            bVar.a(this.e);
            bVar.b();
        }
        if (this.f != null && g()) {
            bVar.a(yg);
            this.f.b(bVar);
            bVar.b();
        }
        bVar.a(yh);
        bVar.a(this.g);
        bVar.b();
        if (this.h != null && i()) {
            bVar.a(yi);
            bVar.a(this.h);
            bVar.b();
        }
        if (this.i != null && j()) {
            bVar.a(yj);
            bVar.a(this.i);
            bVar.b();
        }
        if (this.j != null && l()) {
            bVar.a(yP);
            bVar.a(new org.apache.thrift.protocol.g((byte) 11, this.j.size()));
            for (String a : this.j) {
                bVar.a(a);
            }
            bVar.e();
            bVar.b();
        }
        if (this.k != null && gl()) {
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

    public boolean c() {
        return this.c != null;
    }

    public /* synthetic */ int compareTo(Object obj) {
        return b((g) obj);
    }

    public boolean d() {
        return this.d != null;
    }

    public String e() {
        return this.e;
    }

    public boolean equals(Object obj) {
        return (obj != null && (obj instanceof g)) ? a((g) obj) : false;
    }

    public boolean f() {
        return this.e != null;
    }

    public boolean g() {
        return this.f != null;
    }

    public List gk() {
        return this.j;
    }

    public boolean gl() {
        return this.k != null;
    }

    public void gm() {
        if (this.c == null) {
            throw new TProtocolException("Required field 'id' was not present! Struct: " + toString());
        } else if (this.d == null) {
            throw new TProtocolException("Required field 'appId' was not present! Struct: " + toString());
        } else if (this.e == null) {
            throw new TProtocolException("Required field 'cmdName' was not present! Struct: " + toString());
        }
    }

    public int hashCode() {
        return 0;
    }

    public boolean i() {
        return this.h != null;
    }

    public boolean j() {
        return this.i != null;
    }

    public boolean l() {
        return this.j != null;
    }

    public String m() {
        return this.k;
    }

    public String toString() {
        Object obj = null;
        StringBuilder stringBuilder = new StringBuilder("XmPushActionCommandResult(");
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
        if (g()) {
            stringBuilder.append(", ");
            stringBuilder.append("request:");
            if (this.f == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.f);
            }
        }
        stringBuilder.append(", ");
        stringBuilder.append("errorCode:");
        stringBuilder.append(this.g);
        if (i()) {
            stringBuilder.append(", ");
            stringBuilder.append("reason:");
            if (this.h == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.h);
            }
        }
        if (j()) {
            stringBuilder.append(", ");
            stringBuilder.append("packageName:");
            if (this.i == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.i);
            }
        }
        if (l()) {
            stringBuilder.append(", ");
            stringBuilder.append("cmdArgs:");
            if (this.j == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.j);
            }
        }
        if (gl()) {
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
