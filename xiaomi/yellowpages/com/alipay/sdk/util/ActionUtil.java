package com.alipay.sdk.util;

import android.content.Context;
import android.text.TextUtils;
import com.alipay.mobilesecuritysdk.constant.ConfigConstant;
import com.alipay.sdk.cons.MiniDefine;
import com.alipay.sdk.sys.GlobalContext;
import com.alipay.sdk.tid.TidInfo;

public class ActionUtil {
    private static String a;
    private static String b;

    static {
        a = "#@";
        b = "@#";
    }

    public static String[] a(String str) {
        int indexOf = str.indexOf(40) + 1;
        int lastIndexOf = str.lastIndexOf(41);
        if (indexOf == 0 || lastIndexOf == -1) {
            return null;
        }
        String[] split = str.substring(indexOf, lastIndexOf).split(",");
        if (split != null) {
            for (indexOf = 0; indexOf < split.length; indexOf++) {
                if (!TextUtils.isEmpty(split[indexOf])) {
                    split[indexOf] = split[indexOf].trim();
                    split[indexOf] = split[indexOf].replaceAll("'", ConfigConstant.WIRELESS_FILENAME).replaceAll("\"", ConfigConstant.WIRELESS_FILENAME);
                }
            }
        }
        return split;
    }

    public static void b(String str) {
        String[] a = a(str);
        if (a.length == 3 && TextUtils.equals(MiniDefine.ak, a[0])) {
            Context b = GlobalContext.a().b();
            TidInfo d = TidInfo.d();
            if (!TextUtils.isEmpty(a[1]) && !TextUtils.isEmpty(a[2])) {
                d.a(a[1]);
                d.b(a[2]);
                d.a(b);
            }
        }
    }

    public static String c(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        if (str.contains(a) || str.contains(b)) {
            return str.substring(str.indexOf(a) + a.length(), str.lastIndexOf(b));
        }
        return null;
    }

    public static String a(String str, String str2) {
        int indexOf = str.indexOf(a);
        if (indexOf <= 0) {
            return str;
        }
        StringBuilder stringBuilder = new StringBuilder();
        int lastIndexOf = str.lastIndexOf(b);
        Object substring = str.substring(0, indexOf);
        if (!TextUtils.isEmpty(substring)) {
            stringBuilder.append(substring);
        }
        stringBuilder.append(" " + str2);
        if (lastIndexOf > 0 && str.length() > b.length() + lastIndexOf) {
            stringBuilder.append(str.substring(b.length() + lastIndexOf));
        }
        return stringBuilder.toString();
    }
}
