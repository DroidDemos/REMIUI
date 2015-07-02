package com.miui.yellowpage.ui;

import android.os.Bundle;
import android.text.TextUtils;
import com.miui.yellowpage.utils.c;

/* compiled from: PaymentFragment */
public class T extends cs {
    private Bundle cd;

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        aT();
    }

    private void aT() {
        this.cd = getArguments();
        if (this.cd != null) {
            Object string = this.cd.getString("security_pay_key");
            if (!TextUtils.isEmpty(string)) {
                c.a(this.mActivity, new bL(), string);
            }
        }
    }
}
