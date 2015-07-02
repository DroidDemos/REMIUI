package com.miui.yellowpage.ui;

import android.view.View;
import android.view.View.OnClickListener;
import com.miui.yellowpage.activity.BaseActivity;

/* compiled from: FlowOfPackageConfirmFragment */
class bU implements OnClickListener {
    final /* synthetic */ a BD;

    bU(a aVar) {
        this.BD = aVar;
    }

    public void onClick(View view) {
        ((BaseActivity) this.BD.mActivity).showFragment("FlowOfPackageResultFragment", null, this.BD.cd, true);
    }
}
