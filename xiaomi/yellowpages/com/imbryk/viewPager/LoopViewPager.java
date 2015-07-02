package com.imbryk.viewPager;

import android.content.Context;
import android.support.v4.view.D;
import android.support.v4.view.ViewPager;
import android.support.v4.view.af;
import android.util.AttributeSet;

public class LoopViewPager extends ViewPager {
    af tD;
    private a tE;
    private boolean tF;
    private af tG;

    public void a(D d) {
        this.tE = new a(d);
        this.tE.v(this.tF);
        super.a(this.tE);
        setCurrentItem(0, false);
        if (d != null) {
            d.registerDataSetObserver(new c(this));
        }
    }

    public D aW() {
        return this.tE != null ? this.tE.ez() : this.tE;
    }

    public int getCurrentItem() {
        return this.tE != null ? this.tE.F(super.getCurrentItem()) : 0;
    }

    public void setCurrentItem(int i, boolean z) {
        super.setCurrentItem(this.tE.G(i), z);
    }

    public void setCurrentItem(int i) {
        if (getCurrentItem() != i) {
            setCurrentItem(i, true);
        }
    }

    public void a(af afVar) {
        this.tD = afVar;
    }

    public LoopViewPager(Context context) {
        super(context);
        this.tF = false;
        this.tG = new b(this);
        init();
    }

    public LoopViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.tF = false;
        this.tG = new b(this);
        init();
    }

    private void init() {
        super.a(this.tG);
    }
}
