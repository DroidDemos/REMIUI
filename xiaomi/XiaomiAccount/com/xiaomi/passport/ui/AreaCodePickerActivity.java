package com.xiaomi.passport.ui;

import android.app.ActionBar;
import android.os.Bundle;
import com.xiaomi.account.R;
import com.xiaomi.passport.Build;
import com.xiaomi.passport.utils.FriendlyFragmentUtils;

public class AreaCodePickerActivity extends BaseActivity {
    public void onCreate(Bundle savedInstanceState) {
        if (Build.IS_TABLET) {
            setTheme(R.style.Passport.Theme.Light.Dialog);
        } else {
            setRequestedOrientation(1);
        }
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.setTitle(R.string.passport_area_code_title);
        }
        FriendlyFragmentUtils.addFragment(getFragmentManager(), 16908290, new AreaCodePickerFragment());
    }
}
