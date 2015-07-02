package com.miui.yellowpage.ui;

import android.view.View;
import android.view.View.OnClickListener;

/* compiled from: RechargeOrderDetailFragment */
class cz implements OnClickListener {
    final /* synthetic */ af dz;

    cz(af afVar) {
        this.dz = afVar;
    }

    public void onClick(View view) {
        this.dz.mRequestLoader.a(this.dz.S(this.dz.mId), new aK(this.dz));
    }
}
