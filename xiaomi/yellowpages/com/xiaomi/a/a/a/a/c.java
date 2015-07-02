package com.xiaomi.a.a.a.a;

import com.alipay.mobilesecuritysdk.constant.ConfigConstant;
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
import org.apache.thrift.meta_data.StructMetaData;
import org.apache.thrift.protocol.TProtocolException;
import org.apache.thrift.protocol.b;
import org.apache.thrift.protocol.e;
import org.apache.thrift.protocol.f;

public class c implements Serializable, Cloneable, TBase {
    public static final Map fe;
    private static final f ff;
    private static final e fg;
    private static final e fh;
    private static final e fi;
    private static final e fp;
    private com.xiaomi.a.a.a.a g;
    private String h;
    private b i;
    private f j;

    public enum a {
        COMMON((short) 1, "common"),
        CATEGORY((short) 2, "category"),
        HTTP_API((short) 3, "httpApi"),
        PASSPORT((short) 4, "passport");
        
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
        ff = new f("HttpLog");
        fg = new e("common", (byte) 12, (short) 1);
        fh = new e("category", (byte) 11, (short) 2);
        fi = new e("httpApi", (byte) 12, (short) 3);
        fp = new e("passport", (byte) 12, (short) 4);
        Map enumMap = new EnumMap(a.class);
        enumMap.put(a.COMMON, new FieldMetaData("common", (byte) 1, new StructMetaData((byte) 12, com.xiaomi.a.a.a.a.class)));
        enumMap.put(a.CATEGORY, new FieldMetaData("category", (byte) 1, new FieldValueMetaData((byte) 11)));
        enumMap.put(a.HTTP_API, new FieldMetaData("httpApi", (byte) 2, new StructMetaData((byte) 12, b.class)));
        enumMap.put(a.PASSPORT, new FieldMetaData("passport", (byte) 2, new StructMetaData((byte) 12, f.class)));
        fe = Collections.unmodifiableMap(enumMap);
        FieldMetaData.a(c.class, fe);
    }

    public c() {
        this.h = ConfigConstant.WIRELESS_FILENAME;
    }

    public c a(b bVar) {
        this.i = bVar;
        return this;
    }

    public c a(com.xiaomi.a.a.a.a aVar) {
        this.g = aVar;
        return this;
    }

    public void a(b bVar) {
        bVar.bq();
        while (true) {
            e br = bVar.br();
            if (br.b == (byte) 0) {
                bVar.am();
                e();
                return;
            }
            switch (br.An) {
                case TransactionXMLFile.MODE_WORLD_READABLE /*1*/:
                    if (br.b != (byte) 12) {
                        org.apache.thrift.protocol.c.a(bVar, br.b);
                        break;
                    }
                    this.g = new com.xiaomi.a.a.a.a();
                    this.g.a(bVar);
                    break;
                case TransactionXMLFile.MODE_WORLD_WRITEABLE /*2*/:
                    if (br.b != (byte) 11) {
                        org.apache.thrift.protocol.c.a(bVar, br.b);
                        break;
                    } else {
                        this.h = bVar.bD();
                        break;
                    }
                case WindowData.d /*3*/:
                    if (br.b != (byte) 12) {
                        org.apache.thrift.protocol.c.a(bVar, br.b);
                        break;
                    }
                    this.i = new b();
                    this.i.a(bVar);
                    break;
                case Base64.CRLF /*4*/:
                    if (br.b != (byte) 12) {
                        org.apache.thrift.protocol.c.a(bVar, br.b);
                        break;
                    }
                    this.j = new f();
                    this.j.a(bVar);
                    break;
                default:
                    org.apache.thrift.protocol.c.a(bVar, br.b);
                    break;
            }
            bVar.j();
        }
    }

    public boolean a() {
        return this.g != null;
    }

    public boolean a(c cVar) {
        if (cVar != null) {
            boolean a = a();
            boolean a2 = cVar.a();
            if (!(a || a2) || (a && a2 && this.g.b(cVar.g))) {
                a = b();
                a2 = cVar.b();
                if (!(a || a2) || (a && a2 && this.h.equals(cVar.h))) {
                    a = c();
                    a2 = cVar.c();
                    if (!(a || a2) || (a && a2 && this.i.b(cVar.i))) {
                        a = d();
                        a2 = cVar.d();
                        if (!(a || a2) || (a && a2 && this.j.a(cVar.j))) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public int b(c cVar) {
        if (!getClass().equals(cVar.getClass())) {
            return getClass().getName().compareTo(cVar.getClass().getName());
        }
        int compareTo = Boolean.valueOf(a()).compareTo(Boolean.valueOf(cVar.a()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (a()) {
            compareTo = org.apache.thrift.b.a(this.g, cVar.g);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(b()).compareTo(Boolean.valueOf(cVar.b()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (b()) {
            compareTo = org.apache.thrift.b.f(this.h, cVar.h);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(c()).compareTo(Boolean.valueOf(cVar.c()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (c()) {
            compareTo = org.apache.thrift.b.a(this.i, cVar.i);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(d()).compareTo(Boolean.valueOf(cVar.d()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (d()) {
            compareTo = org.apache.thrift.b.a(this.j, cVar.j);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        return 0;
    }

    public void b(b bVar) {
        e();
        bVar.a(ff);
        if (this.g != null) {
            bVar.a(fg);
            this.g.b(bVar);
            bVar.b();
        }
        if (this.h != null) {
            bVar.a(fh);
            bVar.a(this.h);
            bVar.b();
        }
        if (this.i != null && c()) {
            bVar.a(fi);
            this.i.b(bVar);
            bVar.b();
        }
        if (this.j != null && d()) {
            bVar.a(fp);
            this.j.b(bVar);
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
        return b((c) obj);
    }

    public boolean d() {
        return this.j != null;
    }

    public void e() {
        if (this.g == null) {
            throw new TProtocolException("Required field 'common' was not present! Struct: " + toString());
        } else if (this.h == null) {
            throw new TProtocolException("Required field 'category' was not present! Struct: " + toString());
        }
    }

    public boolean equals(Object obj) {
        return (obj != null && (obj instanceof c)) ? a((c) obj) : false;
    }

    public int hashCode() {
        return 0;
    }

    public c t(String str) {
        this.h = str;
        return this;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("HttpLog(");
        stringBuilder.append("common:");
        if (this.g == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.g);
        }
        stringBuilder.append(", ");
        stringBuilder.append("category:");
        if (this.h == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.h);
        }
        if (c()) {
            stringBuilder.append(", ");
            stringBuilder.append("httpApi:");
            if (this.i == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.i);
            }
        }
        if (d()) {
            stringBuilder.append(", ");
            stringBuilder.append("passport:");
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
