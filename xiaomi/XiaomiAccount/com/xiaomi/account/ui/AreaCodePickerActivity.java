package com.xiaomi.account.ui;

import android.os.Bundle;
import com.xiaomi.account.R;
import com.xiaomi.account.utils.FriendlyFragmentUtils;
import com.xiaomi.passport.ui.AreaCodePickerFragment;
import miui.app.ActionBar;
import miui.app.Activity;
import miui.os.Build;

public class AreaCodePickerActivity extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        if (!Build.IS_TABLET) {
            setRequestedOrientation(1);
        }
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.setTitle(R.string.micloud_area_code_title);
        }
        FriendlyFragmentUtils.addFragment(getFragmentManager(), 16908290, new AreaCodePickerFragment());
    }
}
