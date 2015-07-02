package com.xiaomi.e.a;

import com.alipay.mobilesecuritysdk.constant.ConfigConstant;
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
import org.apache.thrift.protocol.TProtocolException;
import org.apache.thrift.protocol.b;
import org.apache.thrift.protocol.c;
import org.apache.thrift.protocol.e;
import org.apache.thrift.protocol.f;

public class d implements Serializable, Cloneable, TBase {
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
    public boolean e;
    private BitSet m;

    public enum a {
        CHANNEL_ID((short) 1, "channelId"),
        USER_ID((short) 2, "userId"),
        SERVER((short) 3, "server"),
        RESOURCE((short) 4, "resource"),
        IS_PREVIEW((short) 5, "isPreview");
        
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
        gw = new f("Target");
        fr = new e("channelId", (byte) 10, (short) 1);
        fs = new e("userId", (byte) 11, (short) 2);
        fT = new e("server", (byte) 11, (short) 3);
        fU = new e("resource", (byte) 11, (short) 4);
        fV = new e("isPreview", (byte) 2, (short) 5);
        Map enumMap = new EnumMap(a.class);
        enumMap.put(a.CHANNEL_ID, new FieldMetaData("channelId", (byte) 1, new FieldValueMetaData((byte) 10)));
        enumMap.put(a.USER_ID, new FieldMetaData("userId", (byte) 1, new FieldValueMetaData((byte) 11)));
        enumMap.put(a.SERVER, new FieldMetaData("server", (byte) 2, new FieldValueMetaData((byte) 11)));
        enumMap.put(a.RESOURCE, new FieldMetaData("resource", (byte) 2, new FieldValueMetaData((byte) 11)));
        enumMap.put(a.IS_PREVIEW, new FieldMetaData("isPreview", (byte) 2, new FieldValueMetaData((byte) 2)));
        ev = Collections.unmodifiableMap(enumMap);
        FieldMetaData.a(d.class, ev);
    }

    public d() {
        this.m = new BitSet(2);
        this.a = 5;
        this.c = "xiaomi.com";
        this.d = ConfigConstant.WIRELESS_FILENAME;
        this.e = false;
    }

    public void a(b bVar) {
        bVar.bq();
        while (true) {
            e br = bVar.br();
            if (br.b == (byte) 0) {
                bVar.am();
                if (a()) {
                    f();
                    return;
                }
                throw new TProtocolException("Required field 'channelId' was not found in serialized data! Struct: " + toString());
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
                    if (br.b != (byte) 2) {
                        c.a(bVar, br.b);
                        break;
                    }
                    this.e = bVar.bx();
                    b(true);
                    break;
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
        bVar.a(fr);
        bVar.a(this.a);
        bVar.b();
        if (this.b != null) {
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
        if (e()) {
            bVar.a(fV);
            bVar.a(this.e);
            bVar.b();
        }
        bVar.c();
        bVar.a();
    }

    public void b(boolean z) {
        this.m.set(1, z);
    }

    public boolean b() {
        return this.b != null;
    }

    public boolean b(d dVar) {
        if (dVar != null && this.a == dVar.a) {
            boolean b = b();
            boolean b2 = dVar.b();
            if (!(b || b2) || (b && b2 && this.b.equals(dVar.b))) {
                b = c();
                b2 = dVar.c();
                if (!(b || b2) || (b && b2 && this.c.equals(dVar.c))) {
                    b = d();
                    b2 = dVar.d();
                    if (!(b || b2) || (b && b2 && this.d.equals(dVar.d))) {
                        b = e();
                        b2 = dVar.e();
                        if (!(b || b2) || (b && b2 && this.e == dVar.e)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public int c(d dVar) {
        if (!getClass().equals(dVar.getClass())) {
            return getClass().getName().compareTo(dVar.getClass().getName());
        }
        int compareTo = Boolean.valueOf(a()).compareTo(Boolean.valueOf(dVar.a()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (a()) {
            compareTo = org.apache.thrift.b.a(this.a, dVar.a);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(b()).compareTo(Boolean.valueOf(dVar.b()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (b()) {
            compareTo = org.apache.thrift.b.f(this.b, dVar.b);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(c()).compareTo(Boolean.valueOf(dVar.c()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (c()) {
            compareTo = org.apache.thrift.b.f(this.c, dVar.c);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(d()).compareTo(Boolean.valueOf(dVar.d()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (d()) {
            compareTo = org.apache.thrift.b.f(this.d, dVar.d);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(e()).compareTo(Boolean.valueOf(dVar.e()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (e()) {
            compareTo = org.apache.thrift.b.a(this.e, dVar.e);
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
        return c((d) obj);
    }

    public boolean d() {
        return this.d != null;
    }

    public boolean e() {
        return this.m.get(1);
    }

    public boolean equals(Object obj) {
        return (obj != null && (obj instanceof d)) ? b((d) obj) : false;
    }

    public void f() {
        if (this.b == null) {
            throw new TProtocolException("Required field 'userId' was not present! Struct: " + toString());
        }
    }

    public int hashCode() {
        return 0;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("Target(");
        stringBuilder.append("channelId:");
        stringBuilder.append(this.a);
        stringBuilder.append(", ");
        stringBuilder.append("userId:");
        if (this.b == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.b);
        }
        if (c()) {
            stringBuilder.append(", ");
            stringBuilder.append("server:");
            if (this.c == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.c);
            }
        }
        if (d()) {
            stringBuilder.append(", ");
            stringBuilder.append("resource:");
            if (this.d == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.d);
            }
        }
        if (e()) {
            stringBuilder.append(", ");
            stringBuilder.append("isPreview:");
            stringBuilder.append(this.e);
        }
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
