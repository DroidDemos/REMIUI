package android.support.v4.view.a;

import android.graphics.Rect;
import android.view.accessibility.AccessibilityNodeInfo;

/* compiled from: AccessibilityNodeInfoCompatIcs */
class p {
    p() {
    }

    public static void a(Object obj, int i) {
        ((AccessibilityNodeInfo) obj).addAction(i);
    }

    public static int g(Object obj) {
        return ((AccessibilityNodeInfo) obj).getActions();
    }

    public static void a(Object obj, Rect rect) {
        ((AccessibilityNodeInfo) obj).getBoundsInParent(rect);
    }

    public static void b(Object obj, Rect rect) {
        ((AccessibilityNodeInfo) obj).getBoundsInScreen(rect);
    }

    public static CharSequence h(Object obj) {
        return ((AccessibilityNodeInfo) obj).getClassName();
    }

    public static CharSequence i(Object obj) {
        return ((AccessibilityNodeInfo) obj).getContentDescription();
    }

    public static CharSequence j(Object obj) {
        return ((AccessibilityNodeInfo) obj).getPackageName();
    }

    public static CharSequence k(Object obj) {
        return ((AccessibilityNodeInfo) obj).getText();
    }

    public static boolean l(Object obj) {
        return ((AccessibilityNodeInfo) obj).isCheckable();
    }

    public static boolean m(Object obj) {
        return ((AccessibilityNodeInfo) obj).isChecked();
    }

    public static boolean n(Object obj) {
        return ((AccessibilityNodeInfo) obj).isClickable();
    }

    public static boolean o(Object obj) {
        return ((AccessibilityNodeInfo) obj).isEnabled();
    }

    public static boolean p(Object obj) {
        return ((AccessibilityNodeInfo) obj).isFocusable();
    }

    public static boolean q(Object obj) {
        return ((AccessibilityNodeInfo) obj).isFocused();
    }

    public static boolean r(Object obj) {
        return ((AccessibilityNodeInfo) obj).isLongClickable();
    }

    public static boolean s(Object obj) {
        return ((AccessibilityNodeInfo) obj).isPassword();
    }

    public static boolean t(Object obj) {
        return ((AccessibilityNodeInfo) obj).isScrollable();
    }

    public static boolean u(Object obj) {
        return ((AccessibilityNodeInfo) obj).isSelected();
    }

    public static void a(Object obj, CharSequence charSequence) {
        ((AccessibilityNodeInfo) obj).setClassName(charSequence);
    }

    public static void a(Object obj, boolean z) {
        ((AccessibilityNodeInfo) obj).setScrollable(z);
    }
}
