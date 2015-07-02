package com.miui.yellowpage.activity;

import android.os.Bundle;

public class DummyActivity extends BaseActivity {
    protected boolean supportsBanner() {
        return false;
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        finish();
    }
}
