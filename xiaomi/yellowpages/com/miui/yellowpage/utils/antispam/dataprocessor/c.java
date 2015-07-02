package com.miui.yellowpage.utils.antispam.dataprocessor;

import com.alipay.mobilesecuritysdk.deviceID.Profile;
import com.alipay.sdk.cons.GlobalConstants;
import java.util.regex.Pattern;

/* compiled from: Wrapper */
public class c {
    private static final Pattern hQ;

    static {
        hQ = Pattern.compile("\\d*");
    }

    public static String G(String str) {
        if (str == null || str.length() == 0 || !hQ.matcher(str).matches()) {
            str = Profile.devicever;
        }
        return GlobalConstants.d + str;
    }

    public static String H(String str) {
        return str.substring(1);
    }
}
