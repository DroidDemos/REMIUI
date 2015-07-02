package com.miui.yellowpage.business.recharge;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import com.miui.yellowpage.utils.x;

/* compiled from: RechargeFragment */
class J implements OnTouchListener {
    final /* synthetic */ D dw;

    J(D d) {
        this.dw = d;
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        x.a(this.dw.qo, false);
        if (!(view == this.dw.mRootView || this.dw.qH)) {
            this.dw.qH = true;
        }
        return false;
    }
}
