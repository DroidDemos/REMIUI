package com.xiaomi.a.a.a.a;

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
import org.apache.thrift.protocol.TProtocolException;
import org.apache.thrift.protocol.b;
import org.apache.thrift.protocol.c;
import org.apache.thrift.protocol.e;
import org.apache.thrift.protocol.f;

public class d implements Serializable, Cloneable, TBase {
    public static final Map fe;
    private static final f ff;
    private static final e fg;
    private static final e fh;
    private static final e fi;
    private static final e fp;
    private static final e fq;
    private static final e fr;
    private static final e fs;
    private String j;
    private int k;
    private int l;
    private long m;
    private int n;
    private Map o;
    private Map p;
    private BitSet q;

    public enum a {
        IP((short) 1, "ip"),
        FAILED_COUNT((short) 2, "failed_count"),
        SUCCESS_COUNT((short) 3, "success_count"),
        DURATION((short) 4, "duration"),
        SIZE((short) 5, MiniDefine.q),
        EXP_INFO((short) 6, "exp_info"),
        HTTP_INFO((short) 7, "http_info");
        
        private static final Map h;
        private final short i;
        private final String j;

        static {
            h = new HashMap();
            Iterator it = EnumSet.allOf(a.class).iterator();
            while (it.hasNext()) {
                a aVar = (a) it.next();
                h.put(aVar.a(), aVar);
            }
        }

        private a(short s, String str) {
            this.i = s;
            this.j = str;
        }

        public String a() {
            return this.j;
        }
    }

    static {
        ff = new f("LandNodeInfo");
        fg = new e("ip", (byte) 11, (short) 1);
        fh = new e("failed_count", (byte) 8, (short) 2);
        fi = new e("success_count", (byte) 8, (short) 3);
        fp = new e("duration", (byte) 10, (short) 4);
        fq = new e(MiniDefine.q, (byte) 8, (short) 5);
        fr = new e("exp_info", (byte) 13, (short) 6);
        fs = new e("http_info", (byte) 13, (short) 7);
        Map enumMap = new EnumMap(a.class);
        enumMap.put(a.IP, new FieldMetaData("ip", (byte) 1, new FieldValueMetaData((byte) 11)));
        enumMap.put(a.FAILED_COUNT, new FieldMetaData("failed_count", (byte) 1, new FieldValueMetaData((byte) 8)));
        enumMap.put(a.SUCCESS_COUNT, new FieldMetaData("success_count", (byte) 1, new FieldValueMetaData((byte) 8)));
        enumMap.put(a.DURATION, new FieldMetaData("duration", (byte) 1, new FieldValueMetaData((byte) 10)));
        enumMap.put(a.SIZE, new FieldMetaData(MiniDefine.q, (byte) 1, new FieldValueMetaData((byte) 8)));
        enumMap.put(a.EXP_INFO, new FieldMetaData("exp_info", (byte) 2, new MapMetaData((byte) 13, new FieldValueMetaData((byte) 11), new FieldValueMetaData((byte) 8))));
        enumMap.put(a.HTTP_INFO, new FieldMetaData("http_info", (byte) 2, new MapMetaData((byte) 13, new FieldValueMetaData((byte) 8), new FieldValueMetaData((byte) 8))));
        fe = Collections.unmodifiableMap(enumMap);
        FieldMetaData.a(d.class, fe);
    }

    public d() {
        this.q = new BitSet(4);
    }

    public d a(Map map) {
        this.o = map;
        return this;
    }

    public void a(b bVar) {
        bVar.bq();
        while (true) {
            e br = bVar.br();
            if (br.b == (byte) 0) {
                bVar.am();
                if (!b()) {
                    throw new TProtocolException("Required field 'failed_count' was not found in serialized data! Struct: " + toString());
                } else if (!c()) {
                    throw new TProtocolException("Required field 'success_count' was not found in serialized data! Struct: " + toString());
                } else if (!d()) {
                    throw new TProtocolException("Required field 'duration' was not found in serialized data! Struct: " + toString());
                } else if (e()) {
                    am();
                    return;
                } else {
                    throw new TProtocolException("Required field 'size' was not found in serialized data! Struct: " + toString());
                }
            }
            org.apache.thrift.protocol.d bs;
            int i;
            switch (br.An) {
                case TransactionXMLFile.MODE_WORLD_READABLE /*1*/:
                    if (br.b != (byte) 11) {
                        c.a(bVar, br.b);
                        break;
                    } else {
                        this.j = bVar.bD();
                        break;
                    }
                case TransactionXMLFile.MODE_WORLD_WRITEABLE /*2*/:
                    if (br.b != (byte) 8) {
                        c.a(bVar, br.b);
                        break;
                    }
                    this.k = bVar.bA();
                    a(true);
                    break;
                case WindowData.d /*3*/:
                    if (br.b != (byte) 8) {
                        c.a(bVar, br.b);
                        break;
                    }
                    this.l = bVar.bA();
                    b(true);
                    break;
                case Base64.CRLF /*4*/:
                    if (br.b != (byte) 10) {
                        c.a(bVar, br.b);
                        break;
                    }
                    this.m = bVar.bB();
                    e(true);
                    break;
                case WindowData.f /*5*/:
                    if (br.b != (byte) 8) {
                        c.a(bVar, br.b);
                        break;
                    }
                    this.n = bVar.bA();
                    f(true);
                    break;
                case WindowData.g /*6*/:
                    if (br.b != (byte) 13) {
                        c.a(bVar, br.b);
                        break;
                    }
                    bs = bVar.bs();
                    this.o = new HashMap(bs.c * 2);
                    for (i = 0; i < bs.c; i++) {
                        this.o.put(bVar.bD(), Integer.valueOf(bVar.bA()));
                    }
                    bVar.ao();
                    break;
                case WindowData.h /*7*/:
                    if (br.b != (byte) 13) {
                        c.a(bVar, br.b);
                        break;
                    }
                    bs = bVar.bs();
                    this.p = new HashMap(bs.c * 2);
                    for (i = 0; i < bs.c; i++) {
                        this.p.put(Integer.valueOf(bVar.bA()), Integer.valueOf(bVar.bA()));
                    }
                    bVar.ao();
                    break;
                default:
                    c.a(bVar, br.b);
                    break;
            }
            bVar.j();
        }
    }

    public void a(boolean z) {
        this.q.set(0, z);
    }

    public boolean a() {
        return this.j != null;
    }

    public boolean a(d dVar) {
        if (dVar != null) {
            boolean a = a();
            boolean a2 = dVar.a();
            if ((!(a || a2) || (a && a2 && this.j.equals(dVar.j))) && this.k == dVar.k && this.l == dVar.l && this.m == dVar.m && this.n == dVar.n) {
                a = f();
                a2 = dVar.f();
                if (!(a || a2) || (a && a2 && this.o.equals(dVar.o))) {
                    a = g();
                    a2 = dVar.g();
                    if (!(a || a2) || (a && a2 && this.p.equals(dVar.p))) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void am() {
        if (this.j == null) {
            throw new TProtocolException("Required field 'ip' was not present! Struct: " + toString());
        }
    }

    public int b(d dVar) {
        if (!getClass().equals(dVar.getClass())) {
            return getClass().getName().compareTo(dVar.getClass().getName());
        }
        int compareTo = Boolean.valueOf(a()).compareTo(Boolean.valueOf(dVar.a()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (a()) {
            compareTo = org.apache.thrift.b.f(this.j, dVar.j);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(b()).compareTo(Boolean.valueOf(dVar.b()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (b()) {
            compareTo = org.apache.thrift.b.a(this.k, dVar.k);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(c()).compareTo(Boolean.valueOf(dVar.c()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (c()) {
            compareTo = org.apache.thrift.b.a(this.l, dVar.l);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(d()).compareTo(Boolean.valueOf(dVar.d()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (d()) {
            compareTo = org.apache.thrift.b.a(this.m, dVar.m);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(e()).compareTo(Boolean.valueOf(dVar.e()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (e()) {
            compareTo = org.apache.thrift.b.a(this.n, dVar.n);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(f()).compareTo(Boolean.valueOf(dVar.f()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (f()) {
            compareTo = org.apache.thrift.b.a(this.o, dVar.o);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(g()).compareTo(Boolean.valueOf(dVar.g()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (g()) {
            compareTo = org.apache.thrift.b.a(this.p, dVar.p);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        return 0;
    }

    public void b(b bVar) {
        am();
        bVar.a(ff);
        if (this.j != null) {
            bVar.a(fg);
            bVar.a(this.j);
            bVar.b();
        }
        bVar.a(fh);
        bVar.a(this.k);
        bVar.b();
        bVar.a(fi);
        bVar.a(this.l);
        bVar.b();
        bVar.a(fp);
        bVar.a(this.m);
        bVar.b();
        bVar.a(fq);
        bVar.a(this.n);
        bVar.b();
        if (this.o != null && f()) {
            bVar.a(fr);
            bVar.a(new org.apache.thrift.protocol.d((byte) 11, (byte) 8, this.o.size()));
            for (Entry entry : this.o.entrySet()) {
                bVar.a((String) entry.getKey());
                bVar.a(((Integer) entry.getValue()).intValue());
            }
            bVar.d();
            bVar.b();
        }
        if (this.p != null && g()) {
            bVar.a(fs);
            bVar.a(new org.apache.thrift.protocol.d((byte) 8, (byte) 8, this.p.size()));
            for (Entry entry2 : this.p.entrySet()) {
                bVar.a(((Integer) entry2.getKey()).intValue());
                bVar.a(((Integer) entry2.getValue()).intValue());
            }
            bVar.d();
            bVar.b();
        }
        bVar.c();
        bVar.a();
    }

    public void b(boolean z) {
        this.q.set(1, z);
    }

    public boolean b() {
        return this.q.get(0);
    }

    public boolean c() {
        return this.q.get(1);
    }

    public /* synthetic */ int compareTo(Object obj) {
        return b((d) obj);
    }

    public boolean d() {
        return this.q.get(2);
    }

    public d e(int i) {
        this.k = i;
        a(true);
        return this;
    }

    public d e(long j) {
        this.m = j;
        e(true);
        return this;
    }

    public void e(boolean z) {
        this.q.set(2, z);
    }

    public boolean e() {
        return this.q.get(3);
    }

    public boolean equals(Object obj) {
        return (obj != null && (obj instanceof d)) ? a((d) obj) : false;
    }

    public d f(int i) {
        this.l = i;
        b(true);
        return this;
    }

    public void f(boolean z) {
        this.q.set(3, z);
    }

    public boolean f() {
        return this.o != null;
    }

    public d g(int i) {
        this.n = i;
        f(true);
        return this;
    }

    public boolean g() {
        return this.p != null;
    }

    public int hashCode() {
        return 0;
    }

    public d s(String str) {
        this.j = str;
        return this;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("LandNodeInfo(");
        stringBuilder.append("ip:");
        if (this.j == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.j);
        }
        stringBuilder.append(", ");
        stringBuilder.append("failed_count:");
        stringBuilder.append(this.k);
        stringBuilder.append(", ");
        stringBuilder.append("success_count:");
        stringBuilder.append(this.l);
        stringBuilder.append(", ");
        stringBuilder.append("duration:");
        stringBuilder.append(this.m);
        stringBuilder.append(", ");
        stringBuilder.append("size:");
        stringBuilder.append(this.n);
        if (f()) {
            stringBuilder.append(", ");
            stringBuilder.append("exp_info:");
            if (this.o == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.o);
            }
        }
        if (g()) {
            stringBuilder.append(", ");
            stringBuilder.append("http_info:");
            if (this.p == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.p);
            }
        }
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
