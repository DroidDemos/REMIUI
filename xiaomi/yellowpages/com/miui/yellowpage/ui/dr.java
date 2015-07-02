package com.miui.yellowpage.ui;

import android.content.Intent;
import android.net.Uri.Builder;
import android.view.View;
import android.view.View.OnClickListener;
import com.miui.yellowpage.model.ExpressOrder.Cargo;

/* compiled from: ExpressOrderItemFragment */
class dr implements OnClickListener {
    final /* synthetic */ Cargo Im;
    final /* synthetic */ ExpressOrderItemFragment hJ;

    dr(ExpressOrderItemFragment expressOrderItemFragment, Cargo cargo) {
        this.hJ = expressOrderItemFragment;
        this.Im = cargo;
    }

    public void onClick(View view) {
        Intent intent = new Intent("android.intent.action.VIEW");
        Builder builder = new Builder();
        builder.scheme("yellowpage");
        builder.authority("inquiry_history");
        builder.appendQueryParameter("order", this.Im.iP());
        builder.appendQueryParameter("bizCode", this.Im.ed());
        builder.appendQueryParameter("logistics_name", this.Im.ee());
        intent.setData(builder.build());
        this.hJ.startActivity(intent);
    }
}
