package com.xiaomi.account.ui;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.xiaomi.account.R;
import com.xiaomi.account.data.AsyncTaskResult;
import com.xiaomi.account.data.SnsAccountInfo;
import com.xiaomi.account.utils.CloudHelper;
import com.xiaomi.account.utils.SysHelper;
import com.xiaomi.accountsdk.account.data.ExtendedAuthToken;
import com.xiaomi.accountsdk.request.AccessDeniedException;
import com.xiaomi.accountsdk.request.AuthenticationFailureException;
import com.xiaomi.accountsdk.request.InvalidResponseException;
import com.xiaomi.passport.Constants;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import miui.accounts.ExtraAccountManager;
import miui.app.ActionBar;
import miui.app.Activity;
import miui.cloud.CloudManager;
import miui.external.SdkConstants.SdkReturnCode;
import miui.telephony.exception.IllegalDeviceException;

public class SnsWebViewActivity extends Activity {
    public static final String EXTRA_SHOW_SNS_ACCOUNT_AFTER_BINDING_SUCCESS = "extra_show_sns_account_after_binding_success";
    private static final String TAG = "SnsWebViewActivity";
    private Account mAccount;
    private AsyncTask<Void, Void, String> mBindXiaomiAccountTask;
    private AsyncTask<String, Void, GetAccessTokenResult> mGetAccessTokenTask;
    private ProgressBar mLoadingProgressBar;
    private boolean mShowSnsAccount;
    private SnsAccountInfo mSnsAccountInfo;
    private WebView mWebView;
    WebViewClient mWebViewClient;

    private class GetAccessTokenResult extends AsyncTaskResult {
        public String mAccessToken;

        public GetAccessTokenResult(int exceptionType, String accessToken) {
            super(exceptionType);
            this.mAccessToken = accessToken;
        }
    }

    public SnsWebViewActivity() {
        this.mWebViewClient = new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (Uri.parse(CloudHelper.URL_SNS_BIND_FINISH).getPath().equals(Uri.parse(url).getPath())) {
                    SnsWebViewActivity.this.getAccessToken();
                    SnsWebViewActivity.this.removeCookie();
                }
                return super.shouldOverrideUrlLoading(view, url);
            }

            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                SnsWebViewActivity.this.showLoadingProgressBar();
            }

            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                SnsWebViewActivity.this.showWebView();
            }
        };
    }

    public static void start(Context context, String snsType, boolean showSnsAccount) {
        Intent intent = new Intent(context, SnsWebViewActivity.class);
        intent.putExtra("extra_sns_type", snsType);
        intent.putExtra(EXTRA_SHOW_SNS_ACCOUNT_AFTER_BINDING_SUCCESS, showSnsAccount);
        context.startActivity(intent);
    }

    private void bindXiaomiAccount() {
        if (this.mBindXiaomiAccountTask != null) {
            this.mBindXiaomiAccountTask.cancel(true);
        }
        this.mBindXiaomiAccountTask = new AsyncTask<Void, Void, String>() {
            protected String doInBackground(Void... params) {
                try {
                    return CloudManager.getHashedDeviceId(SnsWebViewActivity.this);
                } catch (IllegalDeviceException e) {
                    Log.e(SnsWebViewActivity.TAG, "error when get device id", e);
                    return null;
                }
            }

            protected void onPostExecute(String result) {
                Context context = SnsWebViewActivity.this;
                if (result == null) {
                    Toast.makeText(context, R.string.error_invalid_dev_id, 0).show();
                    return;
                }
                String passToken = AccountManager.get(context).getPassword(SnsWebViewActivity.this.mAccount);
                ExtendedAuthToken extPass = ExtendedAuthToken.parse(passToken);
                if (extPass != null) {
                    passToken = extPass.authToken;
                }
                HashMap<String, String> params = new HashMap();
                String userId = SnsWebViewActivity.this.mAccount.name;
                params.put("userId", userId);
                params.put("display", SnsWebViewActivity.this.getDisplay());
                params.put("appid", SnsWebViewActivity.this.mSnsAccountInfo.getAppId());
                params.put("sid", SnsWebViewActivity.this.mSnsAccountInfo.getServiceId());
                String fullUrl = CloudHelper.URL_SNS_BIND_AUTH + "?" + SnsWebViewActivity.this.getQueryString(params, "&");
                CookieSyncManager.createInstance(context);
                CookieManager cookieMgr = CookieManager.getInstance();
                cookieMgr.removeAllCookie();
                cookieMgr.setCookie(fullUrl, "userId=" + userId);
                cookieMgr.setCookie(fullUrl, "passToken=" + passToken);
                cookieMgr.setCookie(fullUrl, "deviceId=" + result);
                CookieSyncManager.getInstance().sync();
                SnsWebViewActivity.this.mWebView.loadUrl(fullUrl);
            }
        };
        this.mBindXiaomiAccountTask.execute(new Void[0]);
    }

    private String getDisplay() {
        return this.mSnsAccountInfo.getType().equals(SnsAccountInfo.SINA_SNS_TYPE) ? "mobile" : "wap";
    }

    private String getQueryString(Map<String, String> params, CharSequence delimiter) {
        ArrayList<String> sortedParamsList = new ArrayList();
        for (Entry<String, String> entry : params.entrySet()) {
            sortedParamsList.add(String.format("%s=%s", new Object[]{entry.getKey(), entry.getValue()}));
        }
        return TextUtils.join(delimiter, sortedParamsList);
    }

    private void getAccessToken() {
        if (this.mGetAccessTokenTask == null) {
            this.mGetAccessTokenTask = new AsyncTask<String, Void, GetAccessTokenResult>() {
                protected GetAccessTokenResult doInBackground(String... params) {
                    int exceptionType = 0;
                    String accessToken = null;
                    try {
                        AccountManager am = AccountManager.get(SnsWebViewActivity.this);
                        ExtendedAuthToken serviceToken = ExtendedAuthToken.parse(SysHelper.getAuthToken(am, SnsWebViewActivity.this.mAccount, Constants.PASSPORT_API_SID));
                        accessToken = CloudHelper.getBindedAccessToken(SnsWebViewActivity.this.mAccount.name, am.getUserData(SnsWebViewActivity.this.mAccount, Constants.KEY_ENCRYPTED_USER_ID), SnsWebViewActivity.this.mSnsAccountInfo.getSnsType(), serviceToken.authToken, serviceToken.security);
                    } catch (IOException e) {
                        Log.e(SnsWebViewActivity.TAG, "IOException", e);
                        exceptionType = 2;
                    } catch (AuthenticationFailureException e2) {
                        Log.e(SnsWebViewActivity.TAG, "AuthenticationFailureException", e2);
                        exceptionType = 3;
                    } catch (InvalidResponseException e3) {
                        Log.e(SnsWebViewActivity.TAG, "InvalidResponseException", e3);
                        exceptionType = 3;
                    } catch (AccessDeniedException e4) {
                        Log.e(SnsWebViewActivity.TAG, "AccessDeniedException", e4);
                        exceptionType = 4;
                    } catch (Exception e5) {
                        Log.e(SnsWebViewActivity.TAG, "Exception", e5);
                        exceptionType = 5;
                    }
                    return new GetAccessTokenResult(exceptionType, accessToken);
                }

                protected void onPostExecute(GetAccessTokenResult result) {
                    SnsWebViewActivity.this.mGetAccessTokenTask = null;
                    if (result.hasException()) {
                        Toast.makeText(SnsWebViewActivity.this, result.getExceptionReason(), 0).show();
                    } else if (!TextUtils.isEmpty(result.mAccessToken)) {
                        AccountManager.get(SnsWebViewActivity.this).setUserData(SnsWebViewActivity.this.mAccount, SnsWebViewActivity.this.mSnsAccountInfo.getAccessTokenKey(), result.mAccessToken);
                        if (SnsWebViewActivity.this.mShowSnsAccount) {
                            SnsAccountActivity.start(SnsWebViewActivity.this, SnsWebViewActivity.this.mSnsAccountInfo.getType());
                        }
                        SnsWebViewActivity.this.setResult(-1);
                        SnsWebViewActivity.this.finish();
                    }
                }
            };
            this.mGetAccessTokenTask.execute(new String[0]);
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.weibo_webview);
        this.mLoadingProgressBar = (ProgressBar) findViewById(R.id.weibo_loading);
        this.mWebView = (WebView) findViewById(R.id.weibo_webview);
        showWebView();
        this.mWebView.getSettings().setJavaScriptEnabled(true);
        this.mWebView.setWebViewClient(this.mWebViewClient);
        Intent intent = getIntent();
        int bindSnsType = intent.getIntExtra(Constants.EXTRA_BIND_SNS_TYPE, -1);
        if (bindSnsType != -1) {
            this.mAccount = ExtraAccountManager.getXiaomiAccount(this);
            if (this.mAccount != null) {
                switch (bindSnsType) {
                    case SdkReturnCode.LOW_SDK_VERSION /*1*/:
                        this.mSnsAccountInfo = SnsAccountInfo.newSnsAccountInfo(SnsAccountInfo.SINA_SNS_TYPE);
                        break;
                    default:
                        Log.v(TAG, "unknown bind type: " + bindSnsType);
                        break;
                }
                this.mShowSnsAccount = false;
            }
        } else {
            this.mAccount = ExtraAccountManager.getXiaomiAccount(this);
            if (this.mAccount == null) {
                Log.i(TAG, "no xiaomi account");
                finish();
                return;
            }
            this.mSnsAccountInfo = SnsAccountInfo.newSnsAccountInfo(intent.getStringExtra("extra_sns_type"));
            this.mShowSnsAccount = intent.getBooleanExtra(EXTRA_SHOW_SNS_ACCOUNT_AFTER_BINDING_SUCCESS, true);
        }
        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.setTitle(this.mSnsAccountInfo.getTitleResId());
        }
        bindXiaomiAccount();
    }

    protected void onDestroy() {
        if (this.mGetAccessTokenTask != null) {
            this.mGetAccessTokenTask.cancel(true);
            this.mGetAccessTokenTask = null;
        }
        if (this.mBindXiaomiAccountTask != null) {
            this.mBindXiaomiAccountTask.cancel(true);
            this.mBindXiaomiAccountTask = null;
        }
        removeCookie();
        super.onDestroy();
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode != 4 || !this.mWebView.canGoBack()) {
            return super.onKeyDown(keyCode, event);
        }
        this.mWebView.goBack();
        return true;
    }

    private void showLoadingProgressBar() {
        if (this.mWebView.getVisibility() == 0) {
            this.mWebView.setVisibility(8);
        }
        if (this.mLoadingProgressBar.getVisibility() != 0) {
            this.mLoadingProgressBar.setVisibility(0);
        }
    }

    private void showWebView() {
        if (this.mLoadingProgressBar.getVisibility() == 0) {
            this.mLoadingProgressBar.setVisibility(8);
        }
        if (this.mWebView.getVisibility() != 0) {
            this.mWebView.setVisibility(0);
        }
    }

    private void removeCookie() {
        CookieSyncManager.createInstance(this);
        CookieManager.getInstance().removeAllCookie();
        CookieSyncManager.getInstance().sync();
    }
}
