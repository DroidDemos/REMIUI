package com.xiaomi.passport.ui;

import android.app.ActionBar;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.xiaomi.account.R;
import com.xiaomi.passport.Constants;
import com.xiaomi.passport.widget.AlertDialog.Builder;
import com.xiaomi.passport.widget.ProgressDialog;

public class LicenseActivity extends BaseActivity {
    private static final String CN = "cn";
    private static final int DIALOG_HOST_UNREACHABLE = 1;
    private static final int DIALOG_LOADING = 0;
    private static final String EN = "en";
    public static final int PRIVACY_POLICY = 0;
    private static final int[] TITLE;
    private static final String TW = "tw";
    private static final String[] URL;
    private static final String URL_PRIVACY_POLICY = "http://www.miui.com/res/doc/privacy/%s.html";
    private static final String URL_USER_AGREEMENT = "http://www.miui.com/res/doc/eula/%s.html";
    public static final int USER_AGREEMENT = 1;
    private OnCancelListener mOnCancelListener;
    private OnClickListener mOnClickListener;
    private String mTitle;
    private String mUrl;
    private WebView mWebView;
    private WebViewClient mWebViewClient;

    public LicenseActivity() {
        this.mOnCancelListener = new OnCancelListener() {
            public void onCancel(DialogInterface dialog) {
                LicenseActivity.this.finish();
            }
        };
        this.mOnClickListener = new OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                LicenseActivity.this.finish();
            }
        };
        this.mWebViewClient = new WebViewClient() {
            private boolean mHasLoadFirstPage;

            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                LicenseActivity.this.showDialog(LicenseActivity.PRIVACY_POLICY);
            }

            public void onPageFinished(WebView view, String url) {
                LicenseActivity.this.dismissDialog(LicenseActivity.PRIVACY_POLICY);
            }

            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                LicenseActivity.this.onReceivedError();
            }

            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (this.mHasLoadFirstPage) {
                    Intent intent = new Intent("android.intent.action.VIEW");
                    intent.setData(Uri.parse(url));
                    try {
                        LicenseActivity.this.startActivity(intent);
                        return true;
                    } catch (ActivityNotFoundException e) {
                        return true;
                    }
                }
                this.mHasLoadFirstPage = true;
                return false;
            }
        };
    }

    static {
        URL = new String[]{URL_PRIVACY_POLICY, URL_USER_AGREEMENT};
        TITLE = new int[]{R.string.passport_privacy_policy, R.string.passport_user_agreement};
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.passport_license_activity);
        this.mWebView = (WebView) findViewById(R.id.web_view);
        this.mWebView.getSettings().setJavaScriptEnabled(true);
        this.mWebView.setWebViewClient(this.mWebViewClient);
        this.mWebView.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 100) {
                    LicenseActivity.this.dismissDialog(LicenseActivity.PRIVACY_POLICY);
                }
            }
        });
        int licenseType = getIntent().getIntExtra(Constants.LICENSE_TYPE, -1);
        if (isValidLicenseType(licenseType)) {
            this.mTitle = getString(TITLE[licenseType]);
            this.mUrl = getUrl(licenseType, getResources().getConfiguration().locale.toString());
            this.mWebView.loadUrl(this.mUrl);
            ActionBar actionBar = getActionBar();
            if (actionBar != null) {
                actionBar.setTitle(this.mTitle);
                return;
            }
            return;
        }
        finish();
    }

    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case PRIVACY_POLICY /*0*/:
                Dialog progressDialog = new ProgressDialog(this);
                progressDialog.setMessage(getString(R.string.passport_loading));
                progressDialog.setProgressStyle(PRIVACY_POLICY);
                progressDialog.getWindow().setGravity(80);
                return progressDialog;
            case USER_AGREEMENT /*1*/:
                Builder builder = new Builder(this);
                String string = getString(R.string.passport_license_host_unreachable);
                Object[] objArr = new Object[USER_AGREEMENT];
                objArr[PRIVACY_POLICY] = this.mUrl;
                builder.setMessage(String.format(string, objArr)).setTitle(this.mTitle).setNeutralButton(17039370, this.mOnClickListener).setOnCancelListener(this.mOnCancelListener);
                return builder.create();
            default:
                return null;
        }
    }

    private boolean isValidLicenseType(int licenseType) {
        return licenseType >= 0 && licenseType <= TITLE.length;
    }

    private String getUrl(int licenseType, String language) {
        String str;
        Object[] objArr;
        if (language.toLowerCase().contains(CN)) {
            str = URL[licenseType];
            objArr = new Object[USER_AGREEMENT];
            objArr[PRIVACY_POLICY] = CN;
            return String.format(str, objArr);
        } else if (language.toLowerCase().contains(TW)) {
            str = URL[licenseType];
            objArr = new Object[USER_AGREEMENT];
            objArr[PRIVACY_POLICY] = TW;
            return String.format(str, objArr);
        } else {
            str = URL[licenseType];
            objArr = new Object[USER_AGREEMENT];
            objArr[PRIVACY_POLICY] = EN;
            return String.format(str, objArr);
        }
    }

    private void onReceivedError() {
        this.mWebView.setVisibility(8);
        dismissDialog(PRIVACY_POLICY);
        showDialog(USER_AGREEMENT);
    }

    public void onBackPressed() {
        if (this.mWebView.canGoBack()) {
            this.mWebView.goBack();
        } else {
            super.onBackPressed();
        }
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() != 16908332) {
            return super.onOptionsItemSelected(item);
        }
        finish();
        return true;
    }
}
