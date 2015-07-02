package com.miui.yellowpage.ui;

import android.content.Intent;
import com.miui.yellowpage.R;
import com.miui.yellowpage.activity.InternalWebActivity;
import com.miui.yellowpage.utils.s;

/* compiled from: RechargeOrderDetailFragment */
class cA implements s {
    final /* synthetic */ af dz;

    cA(af afVar) {
        this.dz = afVar;
    }

    public void onClick() {
        Intent intent = new Intent(this.dz.mActivity, InternalWebActivity.class);
        intent.putExtra("web_title", this.dz.getString(R.string.recharge_pay_hint_license));
        intent.putExtra("web_url", "http://web.huangye.miui.com/portal/phone-recharge-note.html");
        this.dz.startActivity(intent);
    }
}
