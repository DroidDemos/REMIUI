package com.miui.yellowpage.activity;

import android.os.Bundle;
import com.alipay.sdk.cons.MiniDefine;
import com.miui.yellowpage.ui.ExpressAddressListFragment;
import com.miui.yellowpage.ui.cs;

public class ExpressAddressListActivity extends BaseActivity {
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            extras = new Bundle();
        }
        extras.putString(MiniDefine.i, getIntent().getAction());
        showFragment("ExpressAddressListFragment", null, extras, false);
    }

    protected cs newFragmentByTag(String str) {
        if ("ExpressAddressListFragment".equals(str)) {
            return new ExpressAddressListFragment();
        }
        return null;
    }

    protected boolean requireLogin() {
        return true;
    }
}
