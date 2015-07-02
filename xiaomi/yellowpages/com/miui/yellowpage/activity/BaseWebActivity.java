package com.miui.yellowpage.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import com.miui.yellowpage.base.utils.Log;
import com.miui.yellowpage.ui.bw;

public abstract class BaseWebActivity extends BaseActivity {
    private static final String TAG = "BaseWebActivity";
    protected View mCustomView;
    private String mTitle;
    private String mUrl;
    protected bw mWebFragment;

    protected abstract void customizeActionBar();

    protected abstract int getContentViewResId();

    protected abstract int getWebFragmentResId();

    protected abstract void onMenuHome();

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(getContentViewResId());
        if (parseIntent(getIntent())) {
            this.mWebFragment = (bw) getFragmentManager().findFragmentById(getWebFragmentResId());
            customizeActionBar();
            initTitleBar();
            this.mWebFragment.loadUrl(this.mUrl);
            configureBanner();
            return;
        }
        finish();
    }

    protected boolean hasCustomContentView() {
        return true;
    }

    protected String getBannerLocation() {
        return this.mUrl;
    }

    public void onBackPressed() {
        if (!this.mWebFragment.onBackPressed()) {
            super.onBackPressed();
        }
    }

    protected boolean parseIntent(Intent intent) {
        this.mUrl = intent.getStringExtra("web_url");
        this.mTitle = intent.getStringExtra("web_title");
        String stringExtra = intent.getStringExtra("source");
        if (TextUtils.isEmpty(this.mUrl)) {
            Uri data = intent.getData();
            if (data != null && TextUtils.equals(data.getScheme(), "yellowpage")) {
                if (TextUtils.isEmpty(data.getQueryParameter("ypref")) && !TextUtils.isEmpty(stringExtra)) {
                    Log.d(TAG, "parseIntent append ypref info with source " + stringExtra);
                    data.buildUpon().appendQueryParameter("ypref", stringExtra);
                }
                this.mUrl = data.getQuery();
                stringExtra = "url=";
                if (!TextUtils.isEmpty(this.mUrl) && this.mUrl.startsWith(stringExtra)) {
                    this.mUrl = this.mUrl.substring(stringExtra.length());
                }
            }
        }
        if (!TextUtils.isEmpty(this.mUrl)) {
            return true;
        }
        Log.e(TAG, "url should not be null");
        return false;
    }

    private void initTitleBar() {
        this.mActionBar.setHomeButtonEnabled(true);
        if (!TextUtils.isEmpty(this.mTitle)) {
            setTitle(this.mTitle);
        }
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != 16908332) {
            return super.onOptionsItemSelected(menuItem);
        }
        onMenuHome();
        return true;
    }
}
