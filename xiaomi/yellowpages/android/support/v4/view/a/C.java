package android.support.v4.view.a;

import android.os.Build.VERSION;
import android.os.Bundle;
import java.util.List;

/* compiled from: AccessibilityNodeProviderCompat */
public class C {
    private static final a Ly;
    private final Object Lz;

    static {
        if (VERSION.SDK_INT >= 19) {
            Ly = new k();
        } else if (VERSION.SDK_INT >= 16) {
            Ly = new x();
        } else {
            Ly = new r();
        }
    }

    public C() {
        this.Lz = Ly.a(this);
    }

    public C(Object obj) {
        this.Lz = obj;
    }

    public Object ix() {
        return this.Lz;
    }

    public i af(int i) {
        return null;
    }

    public boolean performAction(int i, int i2, Bundle bundle) {
        return false;
    }

    public List findAccessibilityNodeInfosByText(String str, int i) {
        return null;
    }

    public i ag(int i) {
        return null;
    }
}
