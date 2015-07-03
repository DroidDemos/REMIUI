package com.xiaomi.passport.ui;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.xiaomi.account.Constants;
import com.xiaomi.account.R;

public class NotificationActivity extends BaseActivity {
    private static final String LOGIN_END = "login-end";
    private static final String NEED_RELOGIN = "need-relogin";
    private static final String PASS_INFO = "passInfo";
    private static final String TAG = "NotificationActivity";
    private CookieManager cookieMgr;
    private WebView mWebView;

    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.Passport.Theme.Main);
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.setTitle(R.string.passport_app_name);
        }
        this.mWebView = new WebView(this);
        String notificationUrl = getIntent().getStringExtra(Constants.EXTRA_NOTIFICATON_URL);
        CookieSyncManager.createInstance(this);
        this.cookieMgr = CookieManager.getInstance();
        this.cookieMgr.removeAllCookie();
        WebSettings webSetting = this.mWebView.getSettings();
        webSetting.setJavaScriptEnabled(true);
        webSetting.setBuiltInZoomControls(true);
        webSetting.setSupportZoom(true);
        this.mWebView.loadUrl(notificationUrl);
        this.mWebView.setWebViewClient(new WebViewClient() {
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                String cookieStr = NotificationActivity.this.cookieMgr.getCookie(url);
                if (!TextUtils.isEmpty(cookieStr) && cookieStr.contains(NotificationActivity.PASS_INFO)) {
                    if ((cookieStr.contains(NotificationActivity.LOGIN_END) && cookieStr.contains(NotificationActivity.NEED_RELOGIN)) || cookieStr.contains(NotificationActivity.NEED_RELOGIN)) {
                        NotificationActivity.this.setResult(0);
                        NotificationActivity.this.finish();
                    } else if (cookieStr.contains(NotificationActivity.LOGIN_END)) {
                        Intent intent = new Intent();
                        intent.putExtra("location", url);
                        NotificationActivity.this.setResult(-1, intent);
                        NotificationActivity.this.finish();
                    }
                }
                return super.shouldOverrideUrlLoading(view, url);
            }
        });
        setContentView(this.mWebView);
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (!this.mWebView.canGoBack() || keyCode != 4) {
            return super.onKeyDown(keyCode, event);
        }
        this.mWebView.goBack();
        return true;
    }
}
