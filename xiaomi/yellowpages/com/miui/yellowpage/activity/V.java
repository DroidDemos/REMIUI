package com.miui.yellowpage.activity;

import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import com.miui.yellowpage.R;
import com.miui.yellowpage.utils.x;

/* compiled from: RechargeActivity */
class V implements OnClickListener {
    final /* synthetic */ RechargeActivity uL;

    V(RechargeActivity rechargeActivity) {
        this.uL = rechargeActivity;
    }

    public void onClick(View view) {
        if (this.uL.mB == null || !(this.uL.mB == null || TextUtils.equals("RechargeOrderListFragment", this.uL.mB.getTag()))) {
            this.uL.mD = this.uL.mActionBar.getTitle().toString();
            x.a(view, false);
            this.uL.showFragment("RechargeOrderListFragment", this.uL.getString(R.string.recharge_history), null, true);
        }
    }
}
