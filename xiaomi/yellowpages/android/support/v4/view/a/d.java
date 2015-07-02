package android.support.v4.view.a;

import android.os.Bundle;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityNodeProvider;
import java.util.List;

/* compiled from: AccessibilityNodeProviderCompatJellyBean */
final class d extends AccessibilityNodeProvider {
    final /* synthetic */ w hs;

    d(w wVar) {
        this.hs = wVar;
    }

    public AccessibilityNodeInfo createAccessibilityNodeInfo(int i) {
        return (AccessibilityNodeInfo) this.hs.l(i);
    }

    public List findAccessibilityNodeInfosByText(String str, int i) {
        return this.hs.findAccessibilityNodeInfosByText(str, i);
    }

    public boolean performAction(int i, int i2, Bundle bundle) {
        return this.hs.performAction(i, i2, bundle);
    }
}
