package android.support.v4.view.a;

import android.graphics.Rect;
import android.os.Build.VERSION;
import com.ta.utdid2.android.utils.Base64;
import com.ta.utdid2.core.persistent.TransactionXMLFile;

/* compiled from: AccessibilityNodeInfoCompat */
public class i {
    private static final l oE;
    private final Object oF;

    static {
        if (VERSION.SDK_INT >= 19) {
            oE = new v();
        } else if (VERSION.SDK_INT >= 18) {
            oE = new t();
        } else if (VERSION.SDK_INT >= 16) {
            oE = new z();
        } else if (VERSION.SDK_INT >= 14) {
            oE = new g();
        } else {
            oE = new m();
        }
    }

    public i(Object obj) {
        this.oF = obj;
    }

    public Object cu() {
        return this.oF;
    }

    public int getActions() {
        return oE.g(this.oF);
    }

    public void addAction(int i) {
        oE.a(this.oF, i);
    }

    public void getBoundsInParent(Rect rect) {
        oE.a(this.oF, rect);
    }

    public void getBoundsInScreen(Rect rect) {
        oE.b(this.oF, rect);
    }

    public boolean isCheckable() {
        return oE.l(this.oF);
    }

    public boolean isChecked() {
        return oE.m(this.oF);
    }

    public boolean isFocusable() {
        return oE.p(this.oF);
    }

    public boolean isFocused() {
        return oE.q(this.oF);
    }

    public boolean isSelected() {
        return oE.u(this.oF);
    }

    public boolean isClickable() {
        return oE.n(this.oF);
    }

    public boolean isLongClickable() {
        return oE.r(this.oF);
    }

    public boolean isEnabled() {
        return oE.o(this.oF);
    }

    public boolean isPassword() {
        return oE.s(this.oF);
    }

    public boolean isScrollable() {
        return oE.t(this.oF);
    }

    public void setScrollable(boolean z) {
        oE.a(this.oF, z);
    }

    public CharSequence getPackageName() {
        return oE.j(this.oF);
    }

    public CharSequence getClassName() {
        return oE.h(this.oF);
    }

    public void setClassName(CharSequence charSequence) {
        oE.a(this.oF, charSequence);
    }

    public CharSequence getText() {
        return oE.k(this.oF);
    }

    public CharSequence getContentDescription() {
        return oE.i(this.oF);
    }

    public String getViewIdResourceName() {
        return oE.v(this.oF);
    }

    public int hashCode() {
        return this.oF == null ? 0 : this.oF.hashCode();
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
        i iVar = (i) obj;
        if (this.oF == null) {
            if (iVar.oF != null) {
                return false;
            }
            return true;
        } else if (this.oF.equals(iVar.oF)) {
            return true;
        } else {
            return false;
        }
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(super.toString());
        Rect rect = new Rect();
        getBoundsInParent(rect);
        stringBuilder.append("; boundsInParent: " + rect);
        getBoundsInScreen(rect);
        stringBuilder.append("; boundsInScreen: " + rect);
        stringBuilder.append("; packageName: ").append(getPackageName());
        stringBuilder.append("; className: ").append(getClassName());
        stringBuilder.append("; text: ").append(getText());
        stringBuilder.append("; contentDescription: ").append(getContentDescription());
        stringBuilder.append("; viewId: ").append(getViewIdResourceName());
        stringBuilder.append("; checkable: ").append(isCheckable());
        stringBuilder.append("; checked: ").append(isChecked());
        stringBuilder.append("; focusable: ").append(isFocusable());
        stringBuilder.append("; focused: ").append(isFocused());
        stringBuilder.append("; selected: ").append(isSelected());
        stringBuilder.append("; clickable: ").append(isClickable());
        stringBuilder.append("; longClickable: ").append(isLongClickable());
        stringBuilder.append("; enabled: ").append(isEnabled());
        stringBuilder.append("; password: ").append(isPassword());
        stringBuilder.append("; scrollable: " + isScrollable());
        stringBuilder.append("; [");
        int actions = getActions();
        while (actions != 0) {
            int numberOfTrailingZeros = 1 << Integer.numberOfTrailingZeros(actions);
            actions &= numberOfTrailingZeros ^ -1;
            stringBuilder.append(z(numberOfTrailingZeros));
            if (actions != 0) {
                stringBuilder.append(", ");
            }
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    private static String z(int i) {
        switch (i) {
            case TransactionXMLFile.MODE_WORLD_READABLE /*1*/:
                return "ACTION_FOCUS";
            case TransactionXMLFile.MODE_WORLD_WRITEABLE /*2*/:
                return "ACTION_CLEAR_FOCUS";
            case Base64.CRLF /*4*/:
                return "ACTION_SELECT";
            case Base64.URL_SAFE /*8*/:
                return "ACTION_CLEAR_SELECTION";
            case Base64.NO_CLOSE /*16*/:
                return "ACTION_CLICK";
            case 32:
                return "ACTION_LONG_CLICK";
            case 64:
                return "ACTION_ACCESSIBILITY_FOCUS";
            case 128:
                return "ACTION_CLEAR_ACCESSIBILITY_FOCUS";
            case 256:
                return "ACTION_NEXT_AT_MOVEMENT_GRANULARITY";
            case 512:
                return "ACTION_PREVIOUS_AT_MOVEMENT_GRANULARITY";
            case 1024:
                return "ACTION_NEXT_HTML_ELEMENT";
            case 2048:
                return "ACTION_PREVIOUS_HTML_ELEMENT";
            case 4096:
                return "ACTION_SCROLL_FORWARD";
            case 8192:
                return "ACTION_SCROLL_BACKWARD";
            case 16384:
                return "ACTION_COPY";
            case 32768:
                return "ACTION_PASTE";
            case 65536:
                return "ACTION_CUT";
            case 131072:
                return "ACTION_SET_SELECTION";
            default:
                return "ACTION_UNKNOWN";
        }
    }
}
