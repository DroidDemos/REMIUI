package com.miui.yellowpage.ui;

import android.view.View;
import android.view.View.OnClickListener;
import com.miui.yellowpage.model.ExpressOrder.Cargo;

/* compiled from: ExpressOrderItemFragment */
class ds implements OnClickListener {
    final /* synthetic */ Cargo Im;
    final /* synthetic */ ExpressOrderItemFragment hJ;

    ds(ExpressOrderItemFragment expressOrderItemFragment, Cargo cargo) {
        this.hJ = expressOrderItemFragment;
        this.Im = cargo;
    }

    public void onClick(View view) {
        this.hJ.a(this.Im);
    }
}
