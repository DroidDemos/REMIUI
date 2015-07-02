package com.alipay.sdk.util;

public enum NetConnectionType {
    WIFI(0, "WIFI"),
    NETWORK_TYPE_1(1, "unicom2G"),
    NETWORK_TYPE_2(2, "mobile2G"),
    NETWORK_TYPE_4(4, "telecom2G"),
    NETWORK_TYPE_5(5, "telecom3G"),
    NETWORK_TYPE_6(6, "telecom3G"),
    NETWORK_TYPE_12(12, "telecom3G"),
    NETWORK_TYPE_8(8, "unicom3G"),
    NETWORK_TYPE_3(3, "unicom3G"),
    NETWORK_TYPE_13(13, "LTE"),
    NETWORK_TYPE_11(11, "IDEN"),
    NETWORK_TYPE_9(9, "HSUPA"),
    NETWORK_TYPE_10(10, "HSPA"),
    NETWORK_TYPE_15(15, "HSPAP"),
    NONE(-1, "none");
    
    private int p;
    private String q;

    private NetConnectionType(int i, String str) {
        this.p = i;
        this.q = str;
    }

    public final int a() {
        return this.p;
    }

    public final String b() {
        return this.q;
    }

    public final void a(String str) {
        this.q = str;
    }

    public static NetConnectionType a(int i) {
        for (NetConnectionType netConnectionType : values()) {
            if (netConnectionType.a() == i) {
                return netConnectionType;
            }
        }
        return NONE;
    }
}
