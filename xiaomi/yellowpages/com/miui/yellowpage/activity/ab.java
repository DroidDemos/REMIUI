package com.miui.yellowpage.activity;

import android.graphics.Rect;
import android.support.v4.view.Z;
import android.view.View;
import android.widget.RelativeLayout.LayoutParams;

/* compiled from: QuickYellowPageActivity */
class ab extends Z {
    final /* synthetic */ QuickYellowPageActivity bU;

    private ab(QuickYellowPageActivity quickYellowPageActivity) {
        this.bU = quickYellowPageActivity;
    }

    public void onPageSelected(int i) {
        View a = this.bU.ad(i);
        this.bU.HI.requestChildRectangleOnScreen(a, new Rect(0, 0, a.getWidth(), a.getHeight()), false);
        this.bU.ae(i);
    }

    public void onPageScrolled(int i, float f, int i2) {
        if (this.bU.HE != null && this.bU.HE.length != 0) {
            LayoutParams layoutParams = (LayoutParams) this.bU.HJ.getLayoutParams();
            float f2 = (((float) this.bU.HE[i]) * f) + 0.0f;
            for (int i3 = 1; i3 <= i; i3++) {
                f2 += (float) this.bU.HE[i3 - 1];
            }
            layoutParams.leftMargin = (int) f2;
            this.bU.HJ.setLayoutParams(layoutParams);
        }
    }
}
