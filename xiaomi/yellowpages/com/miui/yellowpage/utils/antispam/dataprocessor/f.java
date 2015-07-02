package com.miui.yellowpage.utils.antispam.dataprocessor;

import com.alipay.mobilesecuritysdk.deviceID.Profile;

/* compiled from: Purger */
public class f {
    public static String W(String str) {
        if (str == null || str.length() == 0) {
            return Profile.devicever;
        }
        if (str.startsWith("+")) {
            str = str.substring(1);
        }
        if (str.length() == 0) {
            return Profile.devicever;
        }
        return str;
    }
}
