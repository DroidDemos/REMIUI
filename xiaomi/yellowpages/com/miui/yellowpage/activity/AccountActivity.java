package com.miui.yellowpage.activity;

import android.os.Bundle;
import com.miui.yellowpage.ui.aT;

public class AccountActivity extends BaseActivity {
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getFragmentManager().beginTransaction().add(16908290, new aT()).commit();
    }

    protected boolean supportsBanner() {
        return false;
    }

    protected boolean requireLogin() {
        return true;
    }
}
