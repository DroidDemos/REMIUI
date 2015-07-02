package android.support.v4.view;

import android.os.Bundle;
import android.support.v4.view.a.C;
import android.support.v4.view.a.i;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;

/* compiled from: AccessibilityDelegateCompat */
class s implements H {
    final /* synthetic */ Q rf;
    final /* synthetic */ a rg;

    s(a aVar, Q q) {
        this.rg = aVar;
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

    public Object j(View view) {
        C m = this.rf.m(view);
        return m != null ? m.ix() : null;
    }

    public boolean performAccessibilityAction(View view, int i, Bundle bundle) {
        return this.rf.performAccessibilityAction(view, i, bundle);
    }
}
