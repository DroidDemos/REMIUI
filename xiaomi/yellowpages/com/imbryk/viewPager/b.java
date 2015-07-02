package com.imbryk.viewPager;

import android.support.v4.view.af;

/* compiled from: LoopViewPager */
class b implements af {
    private float vS;
    private float vT;
    final /* synthetic */ LoopViewPager vU;

    b(LoopViewPager loopViewPager) {
        this.vU = loopViewPager;
        this.vS = -1.0f;
        this.vT = -1.0f;
    }

    public void onPageSelected(int i) {
        int F = this.vU.tE.F(i);
        if (this.vT != ((float) F)) {
            this.vT = (float) F;
            if (this.vU.tD != null) {
                this.vU.tD.onPageSelected(F);
            }
        }
    }

    public void onPageScrolled(int i, float f, int i2) {
        if (this.vU.tE != null) {
            int F = this.vU.tE.F(i);
            if (f == 0.0f && this.vS == 0.0f && this.vU.tE.ey() > 1 && (i == 0 || i == this.vU.tE.getCount() - 1)) {
                this.vU.setCurrentItem(F, false);
            }
            i = F;
        }
        this.vS = f;
        if (this.vU.tD == null) {
            return;
        }
        if (i != this.vU.tE.ey() - 1) {
            this.vU.tD.onPageScrolled(i, f, i2);
        } else if (((double) f) > 0.5d) {
            this.vU.tD.onPageScrolled(0, 0.0f, 0);
        } else {
            this.vU.tD.onPageScrolled(i, 0.0f, 0);
        }
    }

    public void onPageScrollStateChanged(int i) {
        if (this.vU.tE != null) {
            int b = super.getCurrentItem();
            int F = this.vU.tE.F(b);
            if (i == 0 && this.vU.tE.ey() > 1 && (b == 0 || b == this.vU.tE.getCount() - 1)) {
                this.vU.setCurrentItem(F, false);
            }
        }
        if (this.vU.tD != null) {
            this.vU.tD.onPageScrollStateChanged(i);
        }
    }
}
