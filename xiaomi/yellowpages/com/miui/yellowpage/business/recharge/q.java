package com.miui.yellowpage.business.recharge;

import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import com.miui.yellowpage.R;
import com.miui.yellowpage.utils.x;

/* compiled from: RechargeActivity */
class q implements OnClickListener {
    final /* synthetic */ RechargeActivity mF;

    q(RechargeActivity rechargeActivity) {
        this.mF = rechargeActivity;
    }

    public void onClick(View view) {
        if (this.mF.mB == null || !(this.mF.mB == null || TextUtils.equals("MiPayRechargeOrderListFragment", this.mF.mB.getTag()))) {
            this.mF.mD = this.mF.mActionBar.getTitle().toString();
            x.a(view, false);
            this.mF.showFragment("MiPayRechargeOrderListFragment", this.mF.getString(R.string.recharge_history), null, true);
        }
    }
}
