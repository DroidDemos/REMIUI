package com.xiaomi.account.ui;

import android.os.Bundle;
import com.xiaomi.account.utils.FriendlyFragmentUtils;
import miui.app.Activity;

public class SnsListActivity extends Activity {
    private final String TAG;

    public SnsListActivity() {
        this.TAG = "SnsListActivity";
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FriendlyFragmentUtils.addFragment(getFragmentManager(), 16908290, new SnsListFragment());
    }
}
