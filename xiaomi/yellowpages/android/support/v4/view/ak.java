package android.support.v4.view;

import android.support.v4.view.a.i;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;

/* compiled from: AccessibilityDelegateCompat */
class ak implements z {
    final /* synthetic */ I LV;
    final /* synthetic */ Q rf;

    ak(I i, Q q) {
        this.LV = i;
        this.rf = q;
    }

    public boolean dispatchPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
        return this.rf.dispatchPopulateAccessibilityEvent(view, accessibilityEvent);
    }

    public void onInitializeAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
        this.rf.onInitializeAccessibilityEvent(view, accessibilityEvent);
    }

    public void b(View view, Object obj) {
        this.rf.a(view, new i(obj));
    }

    public void onPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
        this.rf.onPopulateAccessibilityEvent(view, accessibilityEvent);
    }

    public boolean onRequestSendAccessibilityEvent(ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent) {
        return this.rf.onRequestSendAccessibilityEvent(viewGroup, view, accessibilityEvent);
    }

    public void sendAccessibilityEvent(View view, int i) {
        this.rf.sendAccessibilityEvent(view, i);
    }

    public void sendAccessibilityEventUnchecked(View view, AccessibilityEvent accessibilityEvent) {
        this.rf.sendAccessibilityEventUnchecked(view, accessibilityEvent);
    }
}
