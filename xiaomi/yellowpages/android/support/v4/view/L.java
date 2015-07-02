package android.support.v4.view;

import android.os.Bundle;
import android.support.v4.view.a.C;
import android.support.v4.view.a.i;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;

/* compiled from: AccessibilityDelegateCompat */
interface L {
    C a(Object obj, View view);

    Object a(Q q);

    void a(Object obj, View view, int i);

    void a(Object obj, View view, i iVar);

    boolean a(Object obj, View view, int i, Bundle bundle);

    boolean a(Object obj, View view, AccessibilityEvent accessibilityEvent);

    boolean a(Object obj, ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent);

    Object aq();

    void b(Object obj, View view, AccessibilityEvent accessibilityEvent);

    void c(Object obj, View view, AccessibilityEvent accessibilityEvent);

    void d(Object obj, View view, AccessibilityEvent accessibilityEvent);
}
