package android.support.v4.view.a;

import android.os.Bundle;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityNodeProvider;
import java.util.List;

/* compiled from: AccessibilityNodeProviderCompatKitKat */
final class B extends AccessibilityNodeProvider {
    final /* synthetic */ e KS;

    B(e eVar) {
        this.KS = eVar;
    }

    public AccessibilityNodeInfo createAccessibilityNodeInfo(int i) {
        return (AccessibilityNodeInfo) this.KS.l(i);
    }

    public List findAccessibilityNodeInfosByText(String str, int i) {
        return this.KS.findAccessibilityNodeInfosByText(str, i);
    }

    public boolean performAction(int i, int i2, Bundle bundle) {
        return this.KS.performAction(i, i2, bundle);
    }

    public AccessibilityNodeInfo findFocus(int i) {
        return (AccessibilityNodeInfo) this.KS.m(i);
    }
}
