package com.google.android.finsky.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.webkit.WebView;
import com.android.vending.R;

public class WebViewDialog extends FragmentActivity {
    public static Intent getIntent(Context context, int titleResId, String url) {
        Intent intent = new Intent(context, WebViewDialog.class);
        intent.putExtra("android.intent.extra.TITLE", titleResId);
        intent.putExtra("android.intent.extra.STREAM", url);
        return intent;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(getIntent().getIntExtra("android.intent.extra.TITLE", R.string.app_name));
        setContentView(R.layout.activity_licenses);
        ((WebView) findViewById(R.id.content)).loadUrl(getIntent().getStringExtra("android.intent.extra.STREAM"));
    }
}
