package com.miui.yellowpage.activity;

import android.view.View;
import android.view.View.OnClickListener;

/* compiled from: QuickYellowPageActivity */
class I implements OnClickListener {
    final /* synthetic */ QuickYellowPageActivity bU;

    I(QuickYellowPageActivity quickYellowPageActivity) {
        this.bU = quickYellowPageActivity;
    }

    public void onClick(View view) {
        this.bU.HC.setCurrentItem(0, true);
    }
}
