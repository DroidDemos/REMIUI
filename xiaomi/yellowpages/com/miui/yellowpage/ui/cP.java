package com.miui.yellowpage.ui;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

/* compiled from: ExpressOrderItemFragment */
class cP implements OnClickListener {
    final /* synthetic */ da Fa;

    cP(da daVar) {
        this.Fa = daVar;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        this.Fa.hJ.mRequestLoader.a(new dE(this.Fa.hJ, null));
        this.Fa.hJ.mRequestLoader.a(this.Fa.hJ.F(), new EditResult());
    }
}
