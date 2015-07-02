package android.support.v4.view;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;

/* compiled from: AccessibilityDelegateCompatJellyBean */
public interface H {
    void b(View view, Object obj);

    boolean dispatchPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent);

    Object j(View view);

    void onInitializeAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent);

    void onPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent);

    boolean onRequestSendAccessibilityEvent(ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent);

    boolean performAccessibilityAction(View view, int i, Bundle bundle);

    void sendAccessibilityEvent(View view, int i);

    void sendAccessibilityEventUnchecked(View view, AccessibilityEvent accessibilityEvent);
}
