package com.xiaomi.d.e;

import android.text.TextUtils;
import com.xiaomi.f.a.a.a;
import java.util.Random;

public class g {
    private static final char[] Js;
    private static final char[] Jt;
    private static final char[] Ju;
    private static final char[] Jv;
    private static Random Jw;
    private static char[] Jx;
    private static final char[] cp;

    static {
        Js = "&quot;".toCharArray();
        cp = "&apos;".toCharArray();
        Jt = "&amp;".toCharArray();
        Ju = "&lt;".toCharArray();
        Jv = "&gt;".toCharArray();
        Jw = new Random();
        Jx = "0123456789abcdefghijklmnopqrstuvwxyz0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
    }

    public static String a(int i) {
        if (i < 1) {
            return null;
        }
        char[] cArr = new char[i];
        for (int i2 = 0; i2 < cArr.length; i2++) {
            cArr[i2] = Jx[Jw.nextInt(71)];
        }
        return new String(cArr);
    }

    public static String a(String str) {
        int i = 0;
        if (str == null) {
            return null;
        }
        char[] toCharArray = str.toCharArray();
        int length = toCharArray.length;
        StringBuilder stringBuilder = new StringBuilder((int) (1.3d * ((double) length)));
        int i2 = 0;
        while (i2 < length) {
            char c = toCharArray[i2];
            if (c <= '>') {
                if (c == '<') {
                    if (i2 > i) {
                        stringBuilder.append(toCharArray, i, i2 - i);
                    }
                    i = i2 + 1;
                    stringBuilder.append(Ju);
                } else if (c == '>') {
                    if (i2 > i) {
                        stringBuilder.append(toCharArray, i, i2 - i);
                    }
                    i = i2 + 1;
                    stringBuilder.append(Jv);
                } else if (c == '&') {
                    if (i2 > i) {
                        stringBuilder.append(toCharArray, i, i2 - i);
                    }
                    if (length <= i2 + 5 || toCharArray[i2 + 1] != '#' || !Character.isDigit(toCharArray[i2 + 2]) || !Character.isDigit(toCharArray[i2 + 3]) || !Character.isDigit(toCharArray[i2 + 4]) || toCharArray[i2 + 5] != ';') {
                        i = i2 + 1;
                        stringBuilder.append(Jt);
                    }
                } else if (c == '\"') {
                    if (i2 > i) {
                        stringBuilder.append(toCharArray, i, i2 - i);
                    }
                    i = i2 + 1;
                    stringBuilder.append(Js);
                } else if (c == '\'') {
                    if (i2 > i) {
                        stringBuilder.append(toCharArray, i, i2 - i);
                    }
                    i = i2 + 1;
                    stringBuilder.append(cp);
                }
            }
            i2++;
        }
        if (i == 0) {
            return str;
        }
        if (i2 > i) {
            stringBuilder.append(toCharArray, i, i2 - i);
        }
        return stringBuilder.toString();
    }

    public static String a(byte[] bArr) {
        return String.valueOf(a.d(bArr));
    }

    public static boolean a(char c) {
        return (c >= ' ' && c <= '\ud7ff') || ((c >= '\ue000' && c <= '\ufffd') || ((c >= '\u0000' && c <= '\uffff') || c == '\t' || c == '\n' || c == '\r'));
    }

    public static final String b(String str) {
        return c(c(c(c(c(str, "&lt;", "<"), "&gt;", ">"), "&quot;", "\""), "&apos;", "'"), "&amp;", "&");
    }

    public static String c(String str) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        StringBuilder stringBuilder = new StringBuilder(str.length());
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            if (a(charAt)) {
                stringBuilder.append(charAt);
            }
        }
        return stringBuilder.toString();
    }

    public static final String c(String str, String str2, String str3) {
        if (str == null) {
            return null;
        }
        int indexOf = str.indexOf(str2, 0);
        if (indexOf < 0) {
            return str;
        }
        char[] toCharArray = str.toCharArray();
        char[] toCharArray2 = str3.toCharArray();
        int length = str2.length();
        StringBuilder stringBuilder = new StringBuilder(toCharArray.length);
        stringBuilder.append(toCharArray, 0, indexOf).append(toCharArray2);
        indexOf += length;
        int i = indexOf;
        while (true) {
            indexOf = str.indexOf(str2, indexOf);
            if (indexOf > 0) {
                stringBuilder.append(toCharArray, i, indexOf - i).append(toCharArray2);
                indexOf += length;
                i = indexOf;
            } else {
                stringBuilder.append(toCharArray, i, toCharArray.length - i);
                return stringBuilder.toString();
            }
        }
    }
}
