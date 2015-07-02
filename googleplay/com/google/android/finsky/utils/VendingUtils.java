package com.google.android.finsky.utils;

import android.content.Context;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.Pair;
import android.view.WindowManager;

public class VendingUtils {
    private static volatile boolean sSystemWasUpgraded;

    public static Pair<Integer, Integer> getScreenDimensions(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService("window");
        DisplayMetrics metrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(metrics);
        return new Pair(Integer.valueOf(Math.min(metrics.widthPixels, metrics.heightPixels)), Integer.valueOf(Math.max(metrics.widthPixels, metrics.heightPixels)));
    }

    static {
        sSystemWasUpgraded = false;
    }

    public static boolean wasSystemUpgraded() {
        if (sSystemWasUpgraded) {
            return true;
        }
        String currentFingerprint = Build.FINGERPRINT;
        if (currentFingerprint.equals((String) VendingPreferences.LAST_BUILD_FINGERPRINT.get())) {
            return false;
        }
        sSystemWasUpgraded = true;
        VendingPreferences.LAST_BUILD_FINGERPRINT.put(currentFingerprint);
        return true;
    }
}
