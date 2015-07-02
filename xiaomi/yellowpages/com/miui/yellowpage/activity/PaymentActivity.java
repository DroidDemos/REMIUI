package com.miui.yellowpage.activity;

import android.os.Bundle;
import com.miui.yellowpage.R;
import com.miui.yellowpage.ui.T;
import com.miui.yellowpage.ui.aq;
import com.miui.yellowpage.ui.cs;

public class PaymentActivity extends BaseActivity {
    private cs mB;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        showFragment("PaymentFragment", getString(R.string.recharge_title), getIntent().getExtras(), false);
    }

    protected cs newFragmentByTag(String str) {
        if ("PaymentFragment".equals(str)) {
            return new T();
        }
        if ("PaymentResultFragment".equals(str)) {
            return new aq();
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
