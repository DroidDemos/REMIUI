package com.miui.yellowpage.activity;

import android.os.Bundle;
import com.miui.yellowpage.R;
import com.miui.yellowpage.ui.dq;
import miui.app.Activity;

public class SettingActivity extends Activity {
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.settings);
        getFragmentManager().beginTransaction().add(R.id.switcher_container, new dq()).commit();
    }
}
