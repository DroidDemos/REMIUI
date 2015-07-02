package android.support.v4.view;

import android.support.v4.view.a.i;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;

/* compiled from: AccessibilityDelegateCompat */
class I extends c {
    I() {
    }

    public Object aq() {
        return m.bd();
    }

    public Object a(Q q) {
        return m.a(new ak(this, q));
    }

    public boolean a(Object obj, View view, AccessibilityEvent accessibilityEvent) {
        return m.a(obj, view, accessibilityEvent);
    }

    public void b(Object obj, View view, AccessibilityEvent accessibilityEvent) {
        m.b(obj, view, accessibilityEvent);
    }

    public void a(Object obj, View view, i iVar) {
        m.a(obj, view, iVar.cu());
    }

    public void c(Object obj, View view, AccessibilityEvent accessibilityEvent) {
        m.c(obj, view, accessibilityEvent);
    }

    public boolean a(Object obj, ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent) {
        return m.a(obj, viewGroup, view, accessibilityEvent);
    }

    public void a(Object obj, View view, int i) {
        m.a(obj, view, i);
    }

    public void d(Object obj, View view, AccessibilityEvent accessibilityEvent) {
        m.d(obj, view, accessibilityEvent);
    }
}
