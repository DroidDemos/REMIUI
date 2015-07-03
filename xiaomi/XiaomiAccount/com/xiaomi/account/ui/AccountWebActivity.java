package com.xiaomi.account.ui;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import com.xiaomi.account.utils.CloudHelper;
import miui.accounts.ExtraAccountManager;
import miui.app.Activity;

public class AccountWebActivity extends Activity {
    public static final String EXTRA_REQUEST_URL = "extra_request_url";
    private static final String TAG;
    private WebView mWebView;

    static {
        TAG = AccountWebActivity.class.getSimpleName();
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(createView());
        Account account = ExtraAccountManager.getXiaomiAccount(this);
        if (account != null) {
            String passtoken = AccountManager.get(this).getPassword(account);
            CookieSyncManager.createInstance(this);
            CookieManager cookieMgr = CookieManager.getInstance();
            cookieMgr.removeAllCookie();
            cookieMgr.setCookie(CloudHelper.ACCOUNT_URL_BASE, getCookie("userId", account.name));
            cookieMgr.setCookie(CloudHelper.ACCOUNT_URL_BASE, getCookie("passToken", passtoken));
            CookieSyncManager.getInstance().sync();
        }
        WebSettings settings = this.mWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setSavePassword(false);
        settings.setSaveFormData(false);
        String requestUrl = getIntent().getStringExtra(EXTRA_REQUEST_URL);
        this.mWebView.setWebViewClient(new WebViewClient() {
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                if (url.contains("#closewebview") && !AccountWebActivity.this.isFinishing()) {
                    AccountWebActivity.this.finish();
                }
            }

            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                if (url.contains("#closewebview") && !AccountWebActivity.this.isFinishing()) {
                    AccountWebActivity.this.finish();
                }
            }
        });
        this.mWebView.setWebChromeClient(new WebChromeClient() {
            public void onCloseWindow(WebView w) {
                super.onCloseWindow(w);
                if (!AccountWebActivity.this.isFinishing()) {
                    AccountWebActivity.this.finish();
                }
            }
        });
        this.mWebView.loadUrl(requestUrl);
    }

    private static String getCookie(String name, String value) {
        return name + "=" + value + "; domain = account.xiaomi.com; path=/";
    }

    private View createView() {
        LinearLayout linear = new LinearLayout(this);
        linear.setLayoutParams(new LayoutParams(-1, -1));
        linear.setOrientation(1);
        this.mWebView = new WebView(this);
        linear.addView(this.mWebView, new LayoutParams(-1, -1));
        return linear;
    }

    public void onBackPressed() {
        if (this.mWebView.canGoBack()) {
            this.mWebView.goBack();
            return;
        }
        setResult(0, null);
        finish();
    }
}
