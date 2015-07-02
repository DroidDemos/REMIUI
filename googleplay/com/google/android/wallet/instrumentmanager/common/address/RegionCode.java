package com.google.android.wallet.instrumentmanager.common.address;

import android.text.TextUtils;
import java.util.Locale;

public class RegionCode {
    public static int toRegionCode(String cldrCode) {
        if (cldrCode.length() != 2) {
            throw new IllegalArgumentException("CountryCode must have length of 2!");
        }
        if (cldrCode.equals("UK")) {
            cldrCode = "GB";
        }
        return (((Character.toUpperCase(cldrCode.charAt(0)) - 65) + 1) << 5) | ((Character.toUpperCase(cldrCode.charAt(1)) - 65) + 1);
    }

    public static String toCountryCode(int regionCode) {
        if (regionCode == 0 || (regionCode & -1024) != 0) {
            return "ZZ";
        }
        char firstChar = (char) ((((regionCode & 992) >> 5) + 65) - 1);
        char secondChar = (char) (((regionCode & 31) + 65) - 1);
        return String.format(Locale.US, "%c%c", new Object[]{Character.valueOf(firstChar), Character.valueOf(secondChar)});
    }

    public static int safeToRegionCode(String cldrCode) {
        if (TextUtils.isEmpty(cldrCode)) {
            return 0;
        }
        try {
            return toRegionCode(cldrCode);
        } catch (IllegalArgumentException e) {
            return getUnknown();
        }
    }

    public static int getUnknown() {
        return 858;
    }
}
