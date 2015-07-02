package com.google.android.finsky.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class QSBSuggestionsActivity2 extends Activity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        if ("android.intent.action.VIEW".equals(intent.getAction())) {
            intent.setClass(this, MainActivity.class);
            startActivity(intent);
        }
        finish();
    }
}
