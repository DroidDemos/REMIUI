package com.miui.yellowpage.ui;

import android.app.AlertDialog.Builder;
import android.view.View;
import android.view.View.OnClickListener;
import com.miui.yellowpage.R;

/* compiled from: ExpressOrderItemFragment */
class da implements OnClickListener {
    final /* synthetic */ ExpressOrderItemFragment hJ;

    private da(ExpressOrderItemFragment expressOrderItemFragment) {
        this.hJ = expressOrderItemFragment;
    }

    public void onClick(View view) {
        new Builder(this.hJ.mActivity).setTitle(R.string.express_cancel_order).setNegativeButton(17039360, null).setPositiveButton(17039370, new cP(this)).create().show();
    }
}
