package android.support.v4.view.accessibility;

import android.view.View;
import android.view.accessibility.AccessibilityRecord;

class AccessibilityRecordCompatIcs {
    public static Object obtain() {
        return AccessibilityRecord.obtain();
    }

    public static void setFromIndex(Object record, int fromIndex) {
        ((AccessibilityRecord) record).setFromIndex(fromIndex);
    }

    public static void setItemCount(Object record, int itemCount) {
        ((AccessibilityRecord) record).setItemCount(itemCount);
    }

    public static void setScrollable(Object record, boolean scrollable) {
        ((AccessibilityRecord) record).setScrollable(scrollable);
    }

    public static void setSource(Object record, View source) {
        ((AccessibilityRecord) record).setSource(source);
    }

    public static void setToIndex(Object record, int toIndex) {
        ((AccessibilityRecord) record).setToIndex(toIndex);
    }
}
