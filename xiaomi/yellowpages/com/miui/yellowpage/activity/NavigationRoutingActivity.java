package com.miui.yellowpage.activity;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;

public class NavigationRoutingActivity extends Activity {
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        try {
            startActivity(new Intent().setComponent(new ComponentName("com.android.contacts", "com.android.contacts.activities.YellowPageNavigation")));
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
        }
        finish();
    }
}
