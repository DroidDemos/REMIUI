package com.xiaomi.a.a.a.a;

import com.alipay.sdk.cons.MiniDefine;
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
import org.apache.thrift.protocol.f;

public class g implements Serializable, Cloneable, TBase {
    public static final Map fe;
    private static final f ff;
    private static final e fg;
    private static final e fh;
    private String e;
    private List f;

    public enum a {
        HOST((short) 1, MiniDefine.aL),
        LAND_NODE_INFO((short) 2, "land_node_info");
        
        private static final Map nz;
        private final short d;
        private final String e;

        static {
            nz = new HashMap();
            Iterator it = EnumSet.allOf(a.class).iterator();
            while (it.hasNext()) {
                a aVar = (a) it.next();
                nz.put(aVar.a(), aVar);
            }
        }

        private a(short s, String str) {
            this.d = s;
            this.e = str;
        }

        public String a() {
            return this.e;
        }
    }

    static {
        ff = new f("PassportHostInfo");
        fg = new e(MiniDefine.aL, (byte) 11, (short) 1);
        fh = new e("land_node_info", (byte) 15, (short) 2);
        Map enumMap = new EnumMap(a.class);
        enumMap.put(a.HOST, new FieldMetaData(MiniDefine.aL, (byte) 1, new FieldValueMetaData((byte) 11)));
        enumMap.put(a.LAND_NODE_INFO, new FieldMetaData("land_node_info", (byte) 1, new ListMetaData((byte) 15, new StructMetaData((byte) 12, h.class))));
        fe = Collections.unmodifiableMap(enumMap);
        FieldMetaData.a(g.class, fe);
    }

    public void a(b bVar) {
        bVar.bq();
        while (true) {
            e br = bVar.br();
            if (br.b == (byte) 0) {
                bVar.am();
                c();
                return;
            }
            switch (br.An) {
                case TransactionXMLFile.MODE_WORLD_READABLE /*1*/:
                    if (br.b != (byte) 11) {
                        c.a(bVar, br.b);
                        break;
                    } else {
                        this.e = bVar.bD();
                        break;
                    }
                case TransactionXMLFile.MODE_WORLD_WRITEABLE /*2*/:
                    if (br.b != (byte) 15) {
                        c.a(bVar, br.b);
                        break;
                    }
                    org.apache.thrift.protocol.g bt = bVar.bt();
                    this.f = new ArrayList(bt.b);
                    for (int i = 0; i < bt.b; i++) {
                        h hVar = new h();
                        hVar.a(bVar);
                        this.f.add(hVar);
                    }
                    bVar.bu();
                    break;
                default:
                    c.a(bVar, br.b);
                    break;
            }
            bVar.j();
        }
    }

    public boolean a() {
        return this.e != null;
    }

    public boolean a(g gVar) {
        if (gVar != null) {
            boolean a = a();
            boolean a2 = gVar.a();
            if (!(a || a2) || (a && a2 && this.e.equals(gVar.e))) {
                a = b();
                a2 = gVar.b();
                if (!(a || a2) || (a && a2 && this.f.equals(gVar.f))) {
                    return true;
                }
            }
        }
        return false;
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
            compareTo = org.apache.thrift.b.f(this.e, gVar.e);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(b()).compareTo(Boolean.valueOf(gVar.b()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (b()) {
            compareTo = org.apache.thrift.b.a(this.f, gVar.f);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        return 0;
    }

    public void b(b bVar) {
        c();
        bVar.a(ff);
        if (this.e != null) {
            bVar.a(fg);
            bVar.a(this.e);
            bVar.b();
        }
        if (this.f != null) {
            bVar.a(fh);
            bVar.a(new org.apache.thrift.protocol.g((byte) 12, this.f.size()));
            for (h b : this.f) {
                b.b(bVar);
            }
            bVar.e();
            bVar.b();
        }
        bVar.c();
        bVar.a();
    }

    public boolean b() {
        return this.f != null;
    }

    public void c() {
        if (this.e == null) {
            throw new TProtocolException("Required field 'host' was not present! Struct: " + toString());
        } else if (this.f == null) {
            throw new TProtocolException("Required field 'land_node_info' was not present! Struct: " + toString());
        }
    }

    public /* synthetic */ int compareTo(Object obj) {
        return b((g) obj);
    }

    public boolean equals(Object obj) {
        return (obj != null && (obj instanceof g)) ? a((g) obj) : false;
    }

    public int hashCode() {
        return 0;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("PassportHostInfo(");
        stringBuilder.append("host:");
        if (this.e == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.e);
        }
        stringBuilder.append(", ");
        stringBuilder.append("land_node_info:");
        if (this.f == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.f);
        }
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
