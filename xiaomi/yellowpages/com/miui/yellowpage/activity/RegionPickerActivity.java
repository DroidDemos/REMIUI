package com.miui.yellowpage.activity;

import android.os.Bundle;
import com.miui.yellowpage.ui.RegionPickerFragment;
import com.miui.yellowpage.ui.cs;

public class RegionPickerActivity extends BaseActivity {
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        showFragment("RegionPickerFragment", null, getIntent().getExtras(), false);
    }

    protected cs newFragmentByTag(String str) {
        if ("RegionPickerFragment".equals(str)) {
            return new RegionPickerFragment();
        }
        return null;
    }

    protected boolean requireNetworking() {
        return false;
    }
}
