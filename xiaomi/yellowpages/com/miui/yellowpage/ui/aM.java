package com.miui.yellowpage.ui;

import android.view.View;
import android.view.View.OnClickListener;
import com.miui.yellowpage.base.model.Enrolling;

/* compiled from: EnrollingListItem */
class aM implements OnClickListener {
    final /* synthetic */ Enrolling rk;
    final /* synthetic */ EnrollingListItem rl;

    aM(EnrollingListItem enrollingListItem, Enrolling enrolling) {
        this.rl = enrollingListItem;
        this.rk = enrolling;
    }

    public void onClick(View view) {
        this.rl.b(this.rk);
    }
}
