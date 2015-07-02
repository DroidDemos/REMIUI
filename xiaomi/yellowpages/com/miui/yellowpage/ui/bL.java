package com.miui.yellowpage.ui;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import com.miui.yellowpage.activity.BaseActivity;
import com.miui.yellowpage.base.utils.Log;

@SuppressLint({"HandlerLeak"})
/* compiled from: PaymentFragment */
class bL extends Handler {
    final /* synthetic */ T zg;

    private bL(T t) {
        this.zg = t;
    }

    public void handleMessage(Message message) {
        if (this.zg.mActivity != null && !this.zg.mActivity.isFinishing()) {
            Log.d("PaymentFragment", "alipay result : " + message);
            if (message.what == 1) {
                ((BaseActivity) this.zg.mActivity).showFragment("PaymentResultFragment", null, this.zg.cd, false);
            }
        }
    }
}
