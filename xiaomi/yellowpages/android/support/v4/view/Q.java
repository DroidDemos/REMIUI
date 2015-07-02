package android.support.v4.view;

import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.v4.view.a.C;
import android.support.v4.view.a.i;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;

/* compiled from: AccessibilityDelegateCompat */
public class Q {
    private static final L Ch;
    private static final Object Ci;
    final Object Cj;

    static {
        if (VERSION.SDK_INT >= 16) {
            Ch = new a();
        } else if (VERSION.SDK_INT >= 14) {
            Ch = new I();
        } else {
            Ch = new c();
        }
        Ci = Ch.aq();
    }

    public Q() {
        this.Cj = Ch.a(this);
    }

    Object gQ() {
        return this.Cj;
    }

    public void sendAccessibilityEvent(View view, int i) {
        Ch.a(Ci, view, i);
    }

    public void sendAccessibilityEventUnchecked(View view, AccessibilityEvent accessibilityEvent) {
        Ch.d(Ci, view, accessibilityEvent);
    }

    public boolean dispatchPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
        return Ch.a(Ci, view, accessibilityEvent);
    }

    public void onPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
        Ch.c(Ci, view, accessibilityEvent);
    }

    public void onInitializeAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
        Ch.b(Ci, view, accessibilityEvent);
    }

    public void a(View view, i iVar) {
        Ch.a(Ci, view, iVar);
    }

    public boolean onRequestSendAccessibilityEvent(ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent) {
        return Ch.a(Ci, viewGroup, view, accessibilityEvent);
    }

    public C m(View view) {
        return Ch.a(Ci, view);
    }

    public boolean performAccessibilityAction(View view, int i, Bundle bundle) {
        return Ch.a(Ci, view, i, bundle);
    }
}
