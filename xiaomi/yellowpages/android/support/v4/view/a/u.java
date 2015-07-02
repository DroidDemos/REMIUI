package android.support.v4.view.a;

import android.os.Bundle;
import java.util.ArrayList;
import java.util.List;

/* compiled from: AccessibilityNodeProviderCompat */
class u implements e {
    final /* synthetic */ k DN;
    final /* synthetic */ C mN;

    u(k kVar, C c) {
        this.DN = kVar;
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

    public Object m(int i) {
        i ag = this.mN.ag(i);
        if (ag == null) {
            return null;
        }
        return ag.cu();
    }
}
