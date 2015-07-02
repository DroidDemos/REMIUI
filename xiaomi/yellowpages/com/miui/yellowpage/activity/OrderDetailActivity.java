package com.miui.yellowpage.activity;

import android.os.Bundle;
import com.miui.yellowpage.R;
import com.miui.yellowpage.ui.af;
import com.miui.yellowpage.ui.cs;

public class OrderDetailActivity extends BaseActivity {
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        showFragment("RechargeOrderDetailFragment", getString(R.string.recharge_title), getIntent().getExtras(), false);
    }

    protected cs newFragmentByTag(String str) {
        if ("RechargeOrderDetailFragment".equals(str)) {
            return new af();
        }
        return null;
    }
}
