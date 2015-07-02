package com.xiaomi.b.a;

import com.alipay.mobilesecuritysdk.constant.ConfigConstant;
import java.io.Serializable;

public class an implements Serializable {
    private static final String a;
    private String b;
    private String c;
    private String d;

    static {
        a = ConfigConstant.WIRELESS_FILENAME.intern();
    }

    public an(String str, String str2) {
        this(str, str2, a);
    }

    public an(String str, String str2, String str3) {
        this.b = str == null ? a : str.intern();
        if (str2 == null) {
            throw new IllegalArgumentException("invalid QName local part");
        }
        this.c = str2.intern();
        if (str3 == null) {
            throw new IllegalArgumentException("invalid QName prefix");
        }
        this.d = str3.intern();
    }

    public String a() {
        return this.b;
    }

    public String b() {
        return this.c;
    }

    public String c() {
        return this.d;
    }

    public final boolean equals(Object obj) {
        return obj == this ? true : !(obj instanceof an) ? false : this.b == ((an) obj).b && this.c == ((an) obj).c;
    }

    public final int hashCode() {
        return this.b.hashCode() ^ this.c.hashCode();
    }

    public String toString() {
        return this.b == a ? this.c : '{' + this.b + '}' + this.c;
    }
}
