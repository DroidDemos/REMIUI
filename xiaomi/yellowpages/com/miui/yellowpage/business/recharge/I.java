package com.miui.yellowpage.business.recharge;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import com.miui.yellowpage.activity.BaseActivity;
import com.miui.yellowpage.base.utils.Log;

@SuppressLint({"HandlerLeak"})
/* compiled from: PaymentFragment */
class I extends Handler {
    final /* synthetic */ s Ha;

    private I(s sVar) {
        this.Ha = sVar;
    }

    public void handleMessage(Message message) {
        if (this.Ha.mActivity != null && !this.Ha.mActivity.isFinishing()) {
            Log.d("MiPayPaymentFragment", "alipay result : " + message);
            if (message.what == 1) {
                ((BaseActivity) this.Ha.mActivity).showFragment("MiPayPaymentResultFragment", null, this.Ha.cd, false);
            }
        }
    }
}
