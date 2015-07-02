package com.miui.yellowpage.activity;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

/* compiled from: QuickYellowPageActivity */
class M implements OnTouchListener {
    final /* synthetic */ QuickYellowPageActivity bU;

    M(QuickYellowPageActivity quickYellowPageActivity) {
        this.bU = quickYellowPageActivity;
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        this.bU.hide(true);
        return true;
    }
}
