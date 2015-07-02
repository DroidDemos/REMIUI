package com.miui.yellowpage.ui;

import android.content.Intent;
import com.miui.yellowpage.R;
import com.miui.yellowpage.activity.InternalWebActivity;
import com.miui.yellowpage.utils.s;

/* compiled from: RechargeFragment */
class aF implements s {
    final /* synthetic */ aL ie;

    aF(aL aLVar) {
        this.ie = aLVar;
    }

    public void onClick() {
        Intent intent = new Intent(this.ie.mActivity, InternalWebActivity.class);
        intent.putExtra("web_title", this.ie.getString(R.string.recharge_pay_hint_license));
        intent.putExtra("web_url", "http://web.huangye.miui.com/portal/phone-recharge-note.html");
        this.ie.startActivity(intent);
    }
}
