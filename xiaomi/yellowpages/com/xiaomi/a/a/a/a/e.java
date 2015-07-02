package com.xiaomi.a.a.a.a;

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
import org.apache.thrift.TBase;
import org.apache.thrift.meta_data.FieldMetaData;
import org.apache.thrift.meta_data.FieldValueMetaData;
import org.apache.thrift.protocol.b;
import org.apache.thrift.protocol.c;
import org.apache.thrift.protocol.f;

public class e implements Serializable, Cloneable, TBase {
    public static final Map fe;
    private static final f ff;
    private static final org.apache.thrift.protocol.e fg;
    private static final org.apache.thrift.protocol.e fh;
    private static final org.apache.thrift.protocol.e fi;
    private static final org.apache.thrift.protocol.e fp;
    private String g;
    private String h;
    private String i;
    private String j;

    public enum a {
        CONTRY((short) 1, "contry"),
        PROVINCE((short) 2, "province"),
        CITY((short) 3, "city"),
        ISP((short) 4, "isp");
        
        private static final Map e;
        private final short f;
        private final String g;

        static {
            e = new HashMap();
            Iterator it = EnumSet.allOf(a.class).iterator();
            while (it.hasNext()) {
                a aVar = (a) it.next();
                e.put(aVar.a(), aVar);
            }
        }

        private a(short s, String str) {
            this.f = s;
            this.g = str;
        }

        public String a() {
            return this.g;
        }
    }

    static {
        ff = new f("Location");
        fg = new org.apache.thrift.protocol.e("contry", (byte) 11, (short) 1);
        fh = new org.apache.thrift.protocol.e("province", (byte) 11, (short) 2);
        fi = new org.apache.thrift.protocol.e("city", (byte) 11, (short) 3);
        fp = new org.apache.thrift.protocol.e("isp", (byte) 11, (short) 4);
        Map enumMap = new EnumMap(a.class);
        enumMap.put(a.CONTRY, new FieldMetaData("contry", (byte) 2, new FieldValueMetaData((byte) 11)));
        enumMap.put(a.PROVINCE, new FieldMetaData("province", (byte) 2, new FieldValueMetaData((byte) 11)));
        enumMap.put(a.CITY, new FieldMetaData("city", (byte) 2, new FieldValueMetaData((byte) 11)));
        enumMap.put(a.ISP, new FieldMetaData("isp", (byte) 2, new FieldValueMetaData((byte) 11)));
        fe = Collections.unmodifiableMap(enumMap);
        FieldMetaData.a(e.class, fe);
    }

    public void a(b bVar) {
        bVar.bq();
        while (true) {
            org.apache.thrift.protocol.e br = bVar.br();
            if (br.b == (byte) 0) {
                bVar.am();
                e();
                return;
            }
            switch (br.An) {
                case TransactionXMLFile.MODE_WORLD_READABLE /*1*/:
                    if (br.b != (byte) 11) {
                        c.a(bVar, br.b);
                        break;
                    } else {
                        this.g = bVar.bD();
                        break;
                    }
                case TransactionXMLFile.MODE_WORLD_WRITEABLE /*2*/:
                    if (br.b != (byte) 11) {
                        c.a(bVar, br.b);
                        break;
                    } else {
                        this.h = bVar.bD();
                        break;
                    }
                case WindowData.d /*3*/:
                    if (br.b != (byte) 11) {
                        c.a(bVar, br.b);
                        break;
                    } else {
                        this.i = bVar.bD();
                        break;
                    }
                case Base64.CRLF /*4*/:
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

    public boolean a() {
        return this.g != null;
    }

    public boolean a(e eVar) {
        if (eVar != null) {
            boolean a = a();
            boolean a2 = eVar.a();
            if (!(a || a2) || (a && a2 && this.g.equals(eVar.g))) {
                a = b();
                a2 = eVar.b();
                if (!(a || a2) || (a && a2 && this.h.equals(eVar.h))) {
                    a = c();
                    a2 = eVar.c();
                    if (!(a || a2) || (a && a2 && this.i.equals(eVar.i))) {
                        a = d();
                        a2 = eVar.d();
                        if (!(a || a2) || (a && a2 && this.j.equals(eVar.j))) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
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
            compareTo = org.apache.thrift.b.f(this.g, eVar.g);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(b()).compareTo(Boolean.valueOf(eVar.b()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (b()) {
            compareTo = org.apache.thrift.b.f(this.h, eVar.h);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(c()).compareTo(Boolean.valueOf(eVar.c()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (c()) {
            compareTo = org.apache.thrift.b.f(this.i, eVar.i);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(d()).compareTo(Boolean.valueOf(eVar.d()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (d()) {
            compareTo = org.apache.thrift.b.f(this.j, eVar.j);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        return 0;
    }

    public void b(b bVar) {
        e();
        bVar.a(ff);
        if (this.g != null && a()) {
            bVar.a(fg);
            bVar.a(this.g);
            bVar.b();
        }
        if (this.h != null && b()) {
            bVar.a(fh);
            bVar.a(this.h);
            bVar.b();
        }
        if (this.i != null && c()) {
            bVar.a(fi);
            bVar.a(this.i);
            bVar.b();
        }
        if (this.j != null && d()) {
            bVar.a(fp);
            bVar.a(this.j);
            bVar.b();
        }
        bVar.c();
        bVar.a();
    }

    public boolean b() {
        return this.h != null;
    }

    public boolean c() {
        return this.i != null;
    }

    public /* synthetic */ int compareTo(Object obj) {
        return b((e) obj);
    }

    public boolean d() {
        return this.j != null;
    }

    public void e() {
    }

    public boolean equals(Object obj) {
        return (obj != null && (obj instanceof e)) ? a((e) obj) : false;
    }

    public int hashCode() {
        return 0;
    }

    public e o(String str) {
        this.g = str;
        return this;
    }

    public e p(String str) {
        this.h = str;
        return this;
    }

    public e q(String str) {
        this.i = str;
        return this;
    }

    public e r(String str) {
        this.j = str;
        return this;
    }

    public String toString() {
        Object obj = null;
        StringBuilder stringBuilder = new StringBuilder("Location(");
        Object obj2 = 1;
        if (a()) {
            stringBuilder.append("contry:");
            if (this.g == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.g);
            }
            obj2 = null;
        }
        if (b()) {
            if (obj2 == null) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("province:");
            if (this.h == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.h);
            }
            obj2 = null;
        }
        if (c()) {
            if (obj2 == null) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("city:");
            if (this.i == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.i);
            }
        } else {
            obj = obj2;
        }
        if (d()) {
            if (obj == null) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("isp:");
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
