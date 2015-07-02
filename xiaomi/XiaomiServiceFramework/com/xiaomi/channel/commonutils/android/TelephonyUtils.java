package com.xiaomi.channel.commonutils.android;

import android.content.Context;
import android.telephony.TelephonyManager;

public class TelephonyUtils {
    public static boolean isChinaMobile(Context context) {
        String operator = ((TelephonyManager) context.getSystemService("phone")).getSimOperator();
        return "46000".equals(operator) || "46002".equals(operator) || "46007".equals(operator);
    }

    public static boolean isChinaUnicom(Context context) {
        return "46001".equals(((TelephonyManager) context.getSystemService("phone")).getSimOperator());
    }

    public static boolean isChinaTelecom(Context context) {
        return "46003".equals(((TelephonyManager) context.getSystemService("phone")).getSimOperator());
    }
}
