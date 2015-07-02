package com.alipay.sdk.app;

public class Result {
    private static String a;

    public static void a(String str) {
        a = str;
    }

    public static String a() {
        return a;
    }

    public static String a(int i, String str, String str2) {
        return "resultStatus={" + i + "};memo={" + str + "};result={" + str2 + "}";
    }
}
