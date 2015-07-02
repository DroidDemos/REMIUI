package com.miui.yellowpage.business.recharge;

import android.content.Intent;
import com.miui.yellowpage.R;
import com.miui.yellowpage.activity.InternalWebActivity;
import com.miui.yellowpage.utils.s;

/* compiled from: PaymentResultFragment */
class a implements s {
    final /* synthetic */ C cX;

    a(C c) {
        this.cX = c;
    }

    public void onClick() {
        Intent intent = new Intent(this.cX.mActivity, InternalWebActivity.class);
        intent.putExtra("web_title", this.cX.getString(R.string.payment_failed_common_problems));
        intent.putExtra("web_url", "http://faq.xiaomi.cn/?note/view/19.html");
        this.cX.startActivity(intent);
    }
}
