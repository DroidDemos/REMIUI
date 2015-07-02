package com.miui.yellowpage.business.recharge;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.a.a.a;
import com.miui.yellowpage.activity.BaseActivity;
import com.miui.yellowpage.ui.cs;
import com.miui.yellowpage.utils.c;

/* compiled from: PaymentFragment */
public class s extends cs {
    private Bundle cd;

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        aT();
    }

    private void aT() {
        this.cd = getArguments();
        if (this.cd != null) {
            String string = this.cd.getString("security_pay_key");
            String string2 = this.cd.getString("pay_type");
            if (!TextUtils.isEmpty(string)) {
                if ("mipay".equals(string2)) {
                    Bundle bundle = new Bundle();
                    bundle.putBoolean("skipSuccess", true);
                    a.s(this.mActivity).a(this, string, bundle);
                } else if ("alipay".equals(string2)) {
                    c.a(this.mActivity, new I(), string);
                } else {
                    ((BaseActivity) this.mActivity).showFragment("MiPayPaymentResultFragment", null, this.cd, false);
                }
            }
        }
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        if (i == 20140424) {
            ((BaseActivity) this.mActivity).showFragment("MiPayPaymentResultFragment", null, this.cd, false);
        } else {
            super.onActivityResult(i, i2, intent);
        }
    }
}
