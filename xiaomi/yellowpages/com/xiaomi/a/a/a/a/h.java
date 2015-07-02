package com.xiaomi.a.a.a.a;

import com.alipay.sdk.protocol.WindowData;
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
import org.apache.thrift.protocol.TProtocolException;
import org.apache.thrift.protocol.b;
import org.apache.thrift.protocol.c;
import org.apache.thrift.protocol.e;
import org.apache.thrift.protocol.f;

public class h implements Serializable, Cloneable, TBase {
    public static final Map fe;
    private static final f ff;
    private static final e fg;
    private static final e fh;
    private static final e fi;
    private int f;
    private int g;
    private int h;
    private BitSet i;

    public enum a {
        IP((short) 1, "ip"),
        EID((short) 2, "eid"),
        RT((short) 3, "rt");
        
        private static final Map cm;
        private final short e;
        private final String f;

        static {
            cm = new HashMap();
            Iterator it = EnumSet.allOf(a.class).iterator();
            while (it.hasNext()) {
                a aVar = (a) it.next();
                cm.put(aVar.a(), aVar);
            }
        }

        private a(short s, String str) {
            this.e = s;
            this.f = str;
        }

        public String a() {
            return this.f;
        }
    }

    static {
        ff = new f("PassportLandNodeInfo");
        fg = new e("ip", (byte) 8, (short) 1);
        fh = new e("eid", (byte) 8, (short) 2);
        fi = new e("rt", (byte) 8, (short) 3);
        Map enumMap = new EnumMap(a.class);
        enumMap.put(a.IP, new FieldMetaData("ip", (byte) 1, new FieldValueMetaData((byte) 8)));
        enumMap.put(a.EID, new FieldMetaData("eid", (byte) 1, new FieldValueMetaData((byte) 8)));
        enumMap.put(a.RT, new FieldMetaData("rt", (byte) 1, new FieldValueMetaData((byte) 8)));
        fe = Collections.unmodifiableMap(enumMap);
        FieldMetaData.a(h.class, fe);
    }

    public h() {
        this.i = new BitSet(3);
    }

    public void a(b bVar) {
        bVar.bq();
        while (true) {
            e br = bVar.br();
            if (br.b == (byte) 0) {
                bVar.am();
                if (!a()) {
                    throw new TProtocolException("Required field 'ip' was not found in serialized data! Struct: " + toString());
                } else if (!b()) {
                    throw new TProtocolException("Required field 'eid' was not found in serialized data! Struct: " + toString());
                } else if (c()) {
                    d();
                    return;
                } else {
                    throw new TProtocolException("Required field 'rt' was not found in serialized data! Struct: " + toString());
                }
            }
            switch (br.An) {
                case TransactionXMLFile.MODE_WORLD_READABLE /*1*/:
                    if (br.b != (byte) 8) {
                        c.a(bVar, br.b);
                        break;
                    }
                    this.f = bVar.bA();
                    a(true);
                    break;
                case TransactionXMLFile.MODE_WORLD_WRITEABLE /*2*/:
                    if (br.b != (byte) 8) {
                        c.a(bVar, br.b);
                        break;
                    }
                    this.g = bVar.bA();
                    b(true);
                    break;
                case WindowData.d /*3*/:
                    if (br.b != (byte) 8) {
                        c.a(bVar, br.b);
                        break;
                    }
                    this.h = bVar.bA();
                    e(true);
                    break;
                default:
                    c.a(bVar, br.b);
                    break;
            }
            bVar.j();
        }
    }

    public void a(boolean z) {
        this.i.set(0, z);
    }

    public boolean a() {
        return this.i.get(0);
    }

    public boolean a(h hVar) {
        return hVar != null && this.f == hVar.f && this.g == hVar.g && this.h == hVar.h;
    }

    public int b(h hVar) {
        if (!getClass().equals(hVar.getClass())) {
            return getClass().getName().compareTo(hVar.getClass().getName());
        }
        int compareTo = Boolean.valueOf(a()).compareTo(Boolean.valueOf(hVar.a()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (a()) {
            compareTo = org.apache.thrift.b.a(this.f, hVar.f);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(b()).compareTo(Boolean.valueOf(hVar.b()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (b()) {
            compareTo = org.apache.thrift.b.a(this.g, hVar.g);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(c()).compareTo(Boolean.valueOf(hVar.c()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (c()) {
            compareTo = org.apache.thrift.b.a(this.h, hVar.h);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        return 0;
    }

    public void b(b bVar) {
        d();
        bVar.a(ff);
        bVar.a(fg);
        bVar.a(this.f);
        bVar.b();
        bVar.a(fh);
        bVar.a(this.g);
        bVar.b();
        bVar.a(fi);
        bVar.a(this.h);
        bVar.b();
        bVar.c();
        bVar.a();
    }

    public void b(boolean z) {
        this.i.set(1, z);
    }

    public boolean b() {
        return this.i.get(1);
    }

    public boolean c() {
        return this.i.get(2);
    }

    public /* synthetic */ int compareTo(Object obj) {
        return b((h) obj);
    }

    public void d() {
    }

    public void e(boolean z) {
        this.i.set(2, z);
    }

    public boolean equals(Object obj) {
        return (obj != null && (obj instanceof h)) ? a((h) obj) : false;
    }

    public int hashCode() {
        return 0;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("PassportLandNodeInfo(");
        stringBuilder.append("ip:");
        stringBuilder.append(this.f);
        stringBuilder.append(", ");
        stringBuilder.append("eid:");
        stringBuilder.append(this.g);
        stringBuilder.append(", ");
        stringBuilder.append("rt:");
        stringBuilder.append(this.h);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
