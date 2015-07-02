package com.miui.yellowpage.activity;

import android.os.Bundle;
import com.miui.yellowpage.R;
import com.miui.yellowpage.ui.cs;
import com.miui.yellowpage.ui.dh;

public class ExpressCompanyListActivity extends ExpressBaseActivity {
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        showFragment("ExpressCompanyListFragment", getString(R.string.express_company_pricing_details), null, false);
    }

    protected cs newFragmentByTag(String str) {
        if ("ExpressCompanyListFragment".equals(str)) {
            return new dh();
        }
        return null;
    }
}
