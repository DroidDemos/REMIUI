package android.support.v4.view;

import android.os.Bundle;
import android.support.v4.view.a.C;
import android.view.View;

/* compiled from: AccessibilityDelegateCompat */
class a extends I {
    a() {
    }

    public Object a(Q q) {
        return T.a(new s(this, q));
    }

    public C a(Object obj, View view) {
        Object b = T.b(obj, view);
        if (b != null) {
            return new C(b);
        }
        return null;
    }

    public boolean a(Object obj, View view, int i, Bundle bundle) {
        return T.a(obj, view, i, bundle);
    }
}
