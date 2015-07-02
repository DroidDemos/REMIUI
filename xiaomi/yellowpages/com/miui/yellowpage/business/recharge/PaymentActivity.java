package com.miui.yellowpage.business.recharge;

import android.os.Bundle;
import com.miui.yellowpage.R;
import com.miui.yellowpage.activity.BaseActivity;
import com.miui.yellowpage.ui.cs;

public class PaymentActivity extends BaseActivity {
    private cs mB;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        showFragment("MiPayPaymentFragment", getString(R.string.recharge_title), getIntent().getExtras(), false);
    }

    protected cs newFragmentByTag(String str) {
        if ("MiPayPaymentFragment".equals(str)) {
            return new s();
        }
        if ("MiPayPaymentResultFragment".equals(str)) {
            return new C();
        }
        return null;
    }

    public cs showFragment(String str, String str2, Bundle bundle, boolean z) {
        this.mB = super.showFragment(str, str2, bundle, z);
        return this.mB;
    }

    public void onBackPressed() {
        if (this.mB != null && !this.mB.onBackPressed()) {
            super.onBackPressed();
        }
    }
}
