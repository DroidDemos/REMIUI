package com.xiaomi.a.a.a;

import com.alipay.mobilesecuritysdk.constant.ConfigConstant;
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
import org.apache.thrift.TBase;
import org.apache.thrift.meta_data.FieldMetaData;
import org.apache.thrift.meta_data.FieldValueMetaData;
import org.apache.thrift.protocol.b;
import org.apache.thrift.protocol.c;
import org.apache.thrift.protocol.e;
import org.apache.thrift.protocol.f;

public class a implements Serializable, Cloneable, TBase {
    public static final Map ev;
    private static final e fT;
    private static final e fU;
    private static final e fV;
    private static final e fr;
    private static final e fs;
    private static final f gw;
    public long a;
    public String b;
    public String c;
    public String d;
    public String e;
    private BitSet m;

    public enum a {
        UUID((short) 1, "uuid"),
        TIME((short) 2, MiniDefine.E),
        CLIENT_IP((short) 3, "clientIp"),
        SERVER_IP((short) 4, "serverIp"),
        SERVER_HOST((short) 5, "serverHost");
        
        private static final Map ev;
        private final short g;
        private final String h;

        static {
            ev = new HashMap();
            Iterator it = EnumSet.allOf(a.class).iterator();
            while (it.hasNext()) {
                a aVar = (a) it.next();
                ev.put(aVar.a(), aVar);
            }
        }

        private a(short s, String str) {
            this.g = s;
            this.h = str;
        }

        public String a() {
            return this.h;
        }
    }

    static {
        gw = new f("Common");
        fr = new e("uuid", (byte) 10, (short) 1);
        fs = new e(MiniDefine.E, (byte) 11, (short) 2);
        fT = new e("clientIp", (byte) 11, (short) 3);
        fU = new e("serverIp", (byte) 11, (short) 4);
        fV = new e("serverHost", (byte) 11, (short) 5);
        Map enumMap = new EnumMap(a.class);
        enumMap.put(a.UUID, new FieldMetaData("uuid", (byte) 2, new FieldValueMetaData((byte) 10)));
        enumMap.put(a.TIME, new FieldMetaData(MiniDefine.E, (byte) 2, new FieldValueMetaData((byte) 11)));
        enumMap.put(a.CLIENT_IP, new FieldMetaData("clientIp", (byte) 2, new FieldValueMetaData((byte) 11)));
        enumMap.put(a.SERVER_IP, new FieldMetaData("serverIp", (byte) 2, new FieldValueMetaData((byte) 11)));
        enumMap.put(a.SERVER_HOST, new FieldMetaData("serverHost", (byte) 2, new FieldValueMetaData((byte) 11)));
        ev = Collections.unmodifiableMap(enumMap);
        FieldMetaData.a(a.class, ev);
    }

    public a() {
        this.m = new BitSet(1);
        this.a = 0;
        this.b = ConfigConstant.WIRELESS_FILENAME;
        this.c = ConfigConstant.WIRELESS_FILENAME;
        this.d = ConfigConstant.WIRELESS_FILENAME;
        this.e = ConfigConstant.WIRELESS_FILENAME;
    }

    public void a(b bVar) {
        bVar.bq();
        while (true) {
            e br = bVar.br();
            if (br.b == (byte) 0) {
                bVar.am();
                f();
                return;
            }
            switch (br.An) {
                case TransactionXMLFile.MODE_WORLD_READABLE /*1*/:
                    if (br.b != (byte) 10) {
                        c.a(bVar, br.b);
                        break;
                    }
                    this.a = bVar.bB();
                    a(true);
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
                    if (br.b != (byte) 11) {
                        c.a(bVar, br.b);
                        break;
                    } else {
                        this.e = bVar.bD();
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
        this.m.set(0, z);
    }

    public boolean a() {
        return this.m.get(0);
    }

    public void b(b bVar) {
        f();
        bVar.a(gw);
        if (a()) {
            bVar.a(fr);
            bVar.a(this.a);
            bVar.b();
        }
        if (this.b != null && b()) {
            bVar.a(fs);
            bVar.a(this.b);
            bVar.b();
        }
        if (this.c != null && c()) {
            bVar.a(fT);
            bVar.a(this.c);
            bVar.b();
        }
        if (this.d != null && d()) {
            bVar.a(fU);
            bVar.a(this.d);
            bVar.b();
        }
        if (this.e != null && e()) {
            bVar.a(fV);
            bVar.a(this.e);
            bVar.b();
        }
        bVar.c();
        bVar.a();
    }

    public boolean b() {
        return this.b != null;
    }

    public boolean b(a aVar) {
        if (aVar != null) {
            boolean a = a();
            boolean a2 = aVar.a();
            if (!(a || a2) || (a && a2 && this.a == aVar.a)) {
                a = b();
                a2 = aVar.b();
                if (!(a || a2) || (a && a2 && this.b.equals(aVar.b))) {
                    a = c();
                    a2 = aVar.c();
                    if (!(a || a2) || (a && a2 && this.c.equals(aVar.c))) {
                        a = d();
                        a2 = aVar.d();
                        if (!(a || a2) || (a && a2 && this.d.equals(aVar.d))) {
                            a = e();
                            a2 = aVar.e();
                            if (!(a || a2) || (a && a2 && this.e.equals(aVar.e))) {
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    public int c(a aVar) {
        if (!getClass().equals(aVar.getClass())) {
            return getClass().getName().compareTo(aVar.getClass().getName());
        }
        int compareTo = Boolean.valueOf(a()).compareTo(Boolean.valueOf(aVar.a()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (a()) {
            compareTo = org.apache.thrift.b.a(this.a, aVar.a);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(b()).compareTo(Boolean.valueOf(aVar.b()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (b()) {
            compareTo = org.apache.thrift.b.f(this.b, aVar.b);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(c()).compareTo(Boolean.valueOf(aVar.c()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (c()) {
            compareTo = org.apache.thrift.b.f(this.c, aVar.c);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(d()).compareTo(Boolean.valueOf(aVar.d()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (d()) {
            compareTo = org.apache.thrift.b.f(this.d, aVar.d);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(e()).compareTo(Boolean.valueOf(aVar.e()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (e()) {
            compareTo = org.apache.thrift.b.f(this.e, aVar.e);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        return 0;
    }

    public boolean c() {
        return this.c != null;
    }

    public /* synthetic */ int compareTo(Object obj) {
        return c((a) obj);
    }

    public boolean d() {
        return this.d != null;
    }

    public boolean e() {
        return this.e != null;
    }

    public boolean equals(Object obj) {
        return (obj != null && (obj instanceof a)) ? b((a) obj) : false;
    }

    public void f() {
    }

    public int hashCode() {
        return 0;
    }

    public String toString() {
        Object obj = null;
        StringBuilder stringBuilder = new StringBuilder("Common(");
        Object obj2 = 1;
        if (a()) {
            stringBuilder.append("uuid:");
            stringBuilder.append(this.a);
            obj2 = null;
        }
        if (b()) {
            if (obj2 == null) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("time:");
            if (this.b == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.b);
            }
            obj2 = null;
        }
        if (c()) {
            if (obj2 == null) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("clientIp:");
            if (this.c == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.c);
            }
            obj2 = null;
        }
        if (d()) {
            if (obj2 == null) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("serverIp:");
            if (this.d == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.d);
            }
        } else {
            obj = obj2;
        }
        if (e()) {
            if (obj == null) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("serverHost:");
            if (this.e == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.e);
            }
        }
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
