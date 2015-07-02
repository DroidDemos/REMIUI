package android.support.v4.view;

import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;

/* compiled from: AccessibilityDelegateCompatIcs */
public interface z {
    void b(View view, Object obj);

    boolean dispatchPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent);

    void onInitializeAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent);

    void onPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent);

    boolean onRequestSendAccessibilityEvent(ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent);

    void sendAccessibilityEvent(View view, int i);

    void sendAccessibilityEventUnchecked(View view, AccessibilityEvent accessibilityEvent);
}
