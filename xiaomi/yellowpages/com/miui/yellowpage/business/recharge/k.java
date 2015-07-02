package com.miui.yellowpage.business.recharge;

import android.content.Intent;
import com.miui.yellowpage.R;
import com.miui.yellowpage.activity.InternalWebActivity;
import com.miui.yellowpage.utils.s;

/* compiled from: RechargeFragment */
class k implements s {
    final /* synthetic */ D dw;

    k(D d) {
        this.dw = d;
    }

    public void onClick() {
        Intent intent = new Intent(this.dw.mActivity, InternalWebActivity.class);
        intent.putExtra("web_title", this.dw.getString(R.string.recharge_pay_hint_license));
        intent.putExtra("web_url", "http://web.huangye.miui.com/portal/phone-recharge-note.html");
        this.dw.startActivity(intent);
    }
}
