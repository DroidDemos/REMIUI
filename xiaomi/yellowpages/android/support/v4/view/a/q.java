package android.support.v4.view.a;

import android.view.accessibility.AccessibilityRecord;

/* compiled from: AccessibilityRecordCompatIcs */
class q {
    q() {
    }

    public static Object dp() {
        return AccessibilityRecord.obtain();
    }

    public static void c(Object obj, int i) {
        ((AccessibilityRecord) obj).setFromIndex(i);
    }

    public static void b(Object obj, int i) {
        ((AccessibilityRecord) obj).setItemCount(i);
    }

    public static void a(Object obj, boolean z) {
        ((AccessibilityRecord) obj).setScrollable(z);
    }

    public static void d(Object obj, int i) {
        ((AccessibilityRecord) obj).setToIndex(i);
    }
}
