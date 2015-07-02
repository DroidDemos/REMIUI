package android.support.v4.view;

import android.os.Bundle;
import android.support.v4.view.a.c;
import android.support.v4.view.a.i;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;

/* compiled from: ViewPager */
class J extends Q {
    final /* synthetic */ ViewPager rT;

    J(ViewPager viewPager) {
        this.rT = viewPager;
    }

    public void onInitializeAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(view, accessibilityEvent);
        accessibilityEvent.setClassName(ViewPager.class.getName());
        c ap = c.ap();
        ap.setScrollable(gC());
        if (accessibilityEvent.getEventType() == 4096 && this.rT.jw != null) {
            ap.setItemCount(this.rT.jw.getCount());
            ap.setFromIndex(this.rT.jx);
            ap.setToIndex(this.rT.jx);
        }
    }

    public void a(View view, i iVar) {
        super.a(view, iVar);
        iVar.setClassName(ViewPager.class.getName());
        iVar.setScrollable(gC());
        if (this.rT.canScrollHorizontally(1)) {
            iVar.addAction(4096);
        }
        if (this.rT.canScrollHorizontally(-1)) {
            iVar.addAction(8192);
        }
    }

    public boolean performAccessibilityAction(View view, int i, Bundle bundle) {
        if (super.performAccessibilityAction(view, i, bundle)) {
            return true;
        }
        switch (i) {
            case 4096:
                if (!this.rT.canScrollHorizontally(1)) {
                    return false;
                }
                this.rT.setCurrentItem(this.rT.jx + 1);
                return true;
            case 8192:
                if (!this.rT.canScrollHorizontally(-1)) {
                    return false;
                }
                this.rT.setCurrentItem(this.rT.jx - 1);
                return true;
            default:
                return false;
        }
    }

    private boolean gC() {
        return this.rT.jw != null && this.rT.jw.getCount() > 1;
    }
}
