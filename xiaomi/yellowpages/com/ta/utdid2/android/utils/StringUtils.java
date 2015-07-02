package com.ta.utdid2.android.utils;

import com.alipay.mobilesecuritysdk.constant.ConfigConstant;

public class StringUtils {
    public static boolean isEmpty(String str) {
        if (str == null || str.length() <= 0) {
            return true;
        }
        return false;
    }

    public static String convertObjectToString(Object obj) {
        if (obj == null) {
            return ConfigConstant.WIRELESS_FILENAME;
        }
        if (obj instanceof String) {
            return ((String) obj).toString();
        }
        if (obj instanceof Integer) {
            return ConfigConstant.WIRELESS_FILENAME + ((Integer) obj).intValue();
        }
        if (obj instanceof Long) {
            return ConfigConstant.WIRELESS_FILENAME + ((Long) obj).longValue();
        }
        if (obj instanceof Double) {
            return ConfigConstant.WIRELESS_FILENAME + ((Double) obj).doubleValue();
        }
        if (obj instanceof Float) {
            return ConfigConstant.WIRELESS_FILENAME + ((Float) obj).floatValue();
        }
        if (obj instanceof Short) {
            return ConfigConstant.WIRELESS_FILENAME + ((Short) obj).shortValue();
        }
        if (obj instanceof Byte) {
            return ConfigConstant.WIRELESS_FILENAME + ((Byte) obj).byteValue();
        }
        if (obj instanceof Boolean) {
            return ((Boolean) obj).toString();
        }
        if (obj instanceof Character) {
            return ((Character) obj).toString();
        }
        return obj.toString();
    }

    public static int hashCode(String str) {
        int i = 0;
        if (str.length() <= 0) {
            return 0;
        }
        char[] toCharArray = str.toCharArray();
        int i2 = 0;
        while (i < toCharArray.length) {
            i2 = (i2 * 31) + toCharArray[i];
            i++;
        }
        return i2;
    }
}
