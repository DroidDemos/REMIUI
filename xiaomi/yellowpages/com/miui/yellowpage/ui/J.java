package com.miui.yellowpage.ui;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import com.miui.yellowpage.utils.x;

/* compiled from: RechargeFragment */
class J implements OnTouchListener {
    final /* synthetic */ aL ie;

    J(aL aLVar) {
        this.ie = aLVar;
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        x.a(this.ie.qo, false);
        if (!(view == this.ie.mRootView || this.ie.qH)) {
            this.ie.qH = true;
        }
        return false;
    }
}
