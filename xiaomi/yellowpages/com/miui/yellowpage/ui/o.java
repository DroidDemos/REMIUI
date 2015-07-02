package com.miui.yellowpage.ui;

import android.content.Intent;
import com.miui.yellowpage.R;
import com.miui.yellowpage.activity.InternalWebActivity;
import com.miui.yellowpage.utils.s;

/* compiled from: PaymentResultFragment */
class o implements s {
    final /* synthetic */ aq fy;

    o(aq aqVar) {
        this.fy = aqVar;
    }

    public void onClick() {
        Intent intent = new Intent(this.fy.mActivity, InternalWebActivity.class);
        intent.putExtra("web_title", this.fy.getString(R.string.payment_failed_common_problems));
        intent.putExtra("web_url", "http://faq.xiaomi.cn/?note/view/19.html");
        this.fy.startActivity(intent);
    }
}
