package android.support.v4.view;

import android.view.View;
import android.view.View.AccessibilityDelegate;

class ViewCompatICS {
    public static boolean canScrollHorizontally(View v, int direction) {
        return v.canScrollHorizontally(direction);
    }

    public static boolean canScrollVertically(View v, int direction) {
        return v.canScrollVertically(direction);
    }

    public static void setAccessibilityDelegate(View v, Object delegate) {
        v.setAccessibilityDelegate((AccessibilityDelegate) delegate);
    }
}
