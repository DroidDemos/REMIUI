package org.apache.thrift.protocol;

import com.alipay.mobilesecuritysdk.constant.ConfigConstant;

public class e {
    public final short An;
    public final String a;
    public final byte b;

    public e() {
        this(ConfigConstant.WIRELESS_FILENAME, (byte) 0, (short) 0);
    }

    public e(String str, byte b, short s) {
        this.a = str;
        this.b = b;
        this.An = s;
    }

    public String toString() {
        return "<TField name:'" + this.a + "' type:" + this.b + " field-id:" + this.An + ">";
    }
}
