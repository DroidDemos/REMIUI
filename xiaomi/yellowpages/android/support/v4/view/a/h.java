package android.support.v4.view.a;

import android.os.Bundle;
import java.util.ArrayList;
import java.util.List;

/* compiled from: AccessibilityNodeProviderCompat */
class h implements w {
    final /* synthetic */ C mN;
    final /* synthetic */ x mO;

    h(x xVar, C c) {
        this.mO = xVar;
        this.mN = c;
    }

    public boolean performAction(int i, int i2, Bundle bundle) {
        return this.mN.performAction(i, i2, bundle);
    }

    public List findAccessibilityNodeInfosByText(String str, int i) {
        List findAccessibilityNodeInfosByText = this.mN.findAccessibilityNodeInfosByText(str, i);
        List arrayList = new ArrayList();
        int size = findAccessibilityNodeInfosByText.size();
        for (int i2 = 0; i2 < size; i2++) {
            arrayList.add(((i) findAccessibilityNodeInfosByText.get(i2)).cu());
        }
        return arrayList;
    }

    public Object l(int i) {
        i af = this.mN.af(i);
        if (af == null) {
            return null;
        }
        return af.cu();
    }
}
