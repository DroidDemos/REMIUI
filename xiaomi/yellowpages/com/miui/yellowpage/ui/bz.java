package com.miui.yellowpage.ui;

import android.view.View;
import android.view.View.OnClickListener;

/* compiled from: SendExpressResultFragment */
class bz implements OnClickListener {
    final /* synthetic */ dn ho;

    bz(dn dnVar) {
        this.ho = dnVar;
    }

    public void onClick(View view) {
        this.ho.JP.startActivity(ExpressOrderItemFragment.j(this.ho.mInternalId));
    }
}
