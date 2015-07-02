package com.google.android.wallet.instrumentmanager.common.util;

import android.content.Context;
import android.os.Build.VERSION;
import android.view.accessibility.AccessibilityManager;

public final class AndroidUtils {
    public static boolean isAccessibilityEnabled(Context context) {
        return ((AccessibilityManager) context.getSystemService("accessibility")).isEnabled();
    }

    public static boolean isTouchAccessibilityEnabled(Context context) {
        AccessibilityManager accessibilityManager = (AccessibilityManager) context.getSystemService("accessibility");
        return VERSION.SDK_INT >= 14 ? accessibilityManager.isTouchExplorationEnabled() : accessibilityManager.isEnabled();
    }
}
