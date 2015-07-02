package com.google.android.finsky.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class AppCrashProxy extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        intent.setPackage("com.google.android.feedback");
        intent.setComponent(null);
        startActivity(intent);
        finish();
    }
}
