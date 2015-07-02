package android.support.v4.view.a;

import android.os.Build.VERSION;

/* compiled from: AccessibilityRecordCompat */
public class c {
    private static final j fY;
    private final Object fZ;

    static {
        if (VERSION.SDK_INT >= 16) {
            fY = new o();
        } else if (VERSION.SDK_INT >= 15) {
            fY = new n();
        } else if (VERSION.SDK_INT >= 14) {
            fY = new s();
        } else {
            fY = new y();
        }
    }

    public c(Object obj) {
        this.fZ = obj;
    }

    public static c ap() {
        return new c(fY.dp());
    }

    public void setScrollable(boolean z) {
        fY.a(this.fZ, z);
    }

    public void setItemCount(int i) {
        fY.b(this.fZ, i);
    }

    public void setFromIndex(int i) {
        fY.c(this.fZ, i);
    }

    public void setToIndex(int i) {
        fY.d(this.fZ, i);
    }

    public int hashCode() {
        return this.fZ == null ? 0 : this.fZ.hashCode();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        c cVar = (c) obj;
        if (this.fZ == null) {
            if (cVar.fZ != null) {
                return false;
            }
            return true;
        } else if (this.fZ.equals(cVar.fZ)) {
            return true;
        } else {
            return false;
        }
    }
}
