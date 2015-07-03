package com.xiaomi.passport;

import android.content.res.Resources;
import android.util.DisplayMetrics;

public class Build extends android.os.Build {
    public static final boolean IS_MIPAD;
    public static final boolean IS_N7;
    public static final boolean IS_TABLET;

    static {
        IS_MIPAD = "mocha".equals(DEVICE);
        IS_N7 = "flo".equals(DEVICE);
        IS_TABLET = isTablet();
    }

    private static boolean isTablet() {
        if (IS_N7 || IS_MIPAD) {
            return true;
        }
        DisplayMetrics dm = Resources.getSystem().getDisplayMetrics();
        if (((int) ((((float) Math.min(dm.widthPixels, dm.heightPixels)) / dm.density) + 0.5f)) < 600) {
            return false;
        }
        return true;
    }
}
