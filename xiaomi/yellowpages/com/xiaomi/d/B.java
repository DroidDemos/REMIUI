package com.xiaomi.d;

import com.alipay.mobilesecuritysdk.constant.ConfigConstant;
import com.alipay.sdk.cons.GlobalDefine;

public class B {
    public static final B KA;
    public static final B vd;
    private String c;

    static {
        vd = new B(GlobalDefine.g);
        KA = new B(ConfigConstant.LOG_JSON_STR_ERROR);
    }

    private B(String str) {
        this.c = str;
    }

    public static B co(String str) {
        if (str != null) {
            String toLowerCase = str.toLowerCase();
            if (KA.toString().equals(toLowerCase)) {
                return KA;
            }
            if (vd.toString().equals(toLowerCase)) {
                return vd;
            }
        }
        return null;
    }

    public String toString() {
        return this.c;
    }
}
