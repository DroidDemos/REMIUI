package com.xiaomi.d.c;

import com.alipay.mobilesecuritysdk.constant.ConfigConstant;
import com.alipay.sdk.cons.GlobalDefine;

public class a {
    public static final a gU;
    public static final a gV;
    public static final a gW;
    public static final a gX;
    private String e;

    static {
        gU = new a("get");
        gV = new a("set");
        gW = new a(GlobalDefine.g);
        gX = new a(ConfigConstant.LOG_JSON_STR_ERROR);
    }

    private a(String str) {
        this.e = str;
    }

    public static a E(String str) {
        if (str != null) {
            String toLowerCase = str.toLowerCase();
            if (gU.toString().equals(toLowerCase)) {
                return gU;
            }
            if (gV.toString().equals(toLowerCase)) {
                return gV;
            }
            if (gX.toString().equals(toLowerCase)) {
                return gX;
            }
            if (gW.toString().equals(toLowerCase)) {
                return gW;
            }
        }
        return null;
    }

    public String toString() {
        return this.e;
    }
}
