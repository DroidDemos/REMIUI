package com.miui.yellowpage.providers.yellowpage;

import android.content.Context;
import android.text.TextUtils;
import miui.telephony.PhoneNumberUtils;
import miui.telephony.PhoneNumberUtils.PhoneNumber;
import miui.yellowpage.YellowPageUtils;

/* compiled from: YellowPageNumberUtils */
public class o {
    public static String getNormalizedNumber(Context context, String str) {
        return getNormalizedNumber(context, str, true, "86");
    }

    public static String getNormalizedNumber(Context context, String str, boolean z, String str2) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        String normalizeNumber = PhoneNumberUtils.normalizeNumber(str);
        if (ce(normalizeNumber)) {
            String userAreaCode = YellowPageUtils.getUserAreaCode(context);
            if (TextUtils.isEmpty(userAreaCode)) {
                return normalizeNumber;
            }
            if (z) {
                return "+86" + userAreaCode + str;
            }
            return userAreaCode + str;
        }
        PhoneNumber parse = PhoneNumber.parse(str);
        if (!TextUtils.isEmpty(str2)) {
            parse.setDefaultCountryCode(str2);
        }
        normalizeNumber = parse.getNormalizedNumber(z, true);
        if (cg(normalizeNumber)) {
            if (z) {
                normalizeNumber = "+86" + parse.getAreaCode() + normalizeNumber;
            } else {
                normalizeNumber = parse.getAreaCode() + normalizeNumber;
            }
        }
        parse.recycle();
        return normalizeNumber;
    }

    private static boolean ce(String str) {
        boolean z = true;
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        if (TextUtils.isEmpty(str)) {
            z = false;
        } else {
            PhoneNumber parse = PhoneNumber.parse(str);
            if (str.length() == 7 || str.length() == 8) {
                if (!parse.isChineseNumber() || !TextUtils.isEmpty(parse.getAreaCode()) || parse.isServiceNumber() || parse.isSmsPrefix()) {
                    z = false;
                }
            } else if (!(cg(parse.getNormalizedNumber(true, true)) && TextUtils.isEmpty(parse.getAreaCode()))) {
                z = false;
            }
            parse.recycle();
        }
        return z;
    }

    public static boolean cf(String str) {
        boolean z = false;
        PhoneNumber parse = PhoneNumber.parse(str);
        if (parse.isServiceNumber() && !TextUtils.equals(parse.getEffectiveNumber(), PhoneNumberUtils.normalizeNumber(str))) {
            z = true;
        }
        parse.recycle();
        return z;
    }

    private static boolean cg(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return str.startsWith("96");
    }
}
