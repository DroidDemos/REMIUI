package com.xiaomi.account.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import com.xiaomi.account.R;
import com.xiaomi.accountsdk.account.XMPassport;
import com.xiaomi.passport.Constants;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import miui.app.Activity;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;

public class AuthorizeActivity extends Activity {
    public static final String ACCOUNT_OAUTH_BASE;
    private static final String AUTHORIZE_PATH;
    private static final String EXTRA_RESPONSE_TYPE = "extra_response_type";
    private static final String EXTRA_SCOPE = "extra_scope";
    private static final String EXTRA_SKIP_CONFIRM = "extra_skip_confirm";
    private static final String EXTRA_STATE = "extra_state";
    private static final String LOCALE_KEY_IN_URL = "_locale";
    public static int RESULT_CANCEL = 0;
    public static int RESULT_FAIL = 0;
    public static int RESULT_SUCCESS = 0;
    private static final String TAG = "AuthorizeActivity";
    private static final String UTF8 = "UTF-8";
    private static HashMap<Locale, String> locale2UrlParamMap;
    private WebSettings mSettings;
    private String mUrl;
    private WebView mWebView;

    protected interface onAuthActivityInterface {
        void onAuthorizeFailed(Bundle bundle);

        void onAuthorizeSucceeded(Bundle bundle);

        void onCancelled();
    }

    static {
        locale2UrlParamMap = new HashMap();
        locale2UrlParamMap.put(Locale.SIMPLIFIED_CHINESE, "zh_CN");
        locale2UrlParamMap.put(Locale.CHINA, "zh_CN");
        locale2UrlParamMap.put(Locale.PRC, "zh_CN");
        locale2UrlParamMap.put(Locale.TRADITIONAL_CHINESE, "zh_TW");
        locale2UrlParamMap.put(Locale.TAIWAN, "zh_TW");
        locale2UrlParamMap.put(Locale.ENGLISH, "en");
        locale2UrlParamMap.put(Locale.UK, "en");
        locale2UrlParamMap.put(Locale.US, "en");
        ACCOUNT_OAUTH_BASE = XMPassport.URL_ACCOUNT_OAUTH_BASE;
        AUTHORIZE_PATH = ACCOUNT_OAUTH_BASE + "authorize";
        RESULT_SUCCESS = -1;
        RESULT_FAIL = 1;
        RESULT_CANCEL = 0;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(createView());
        this.mSettings = this.mWebView.getSettings();
        this.mSettings.setJavaScriptEnabled(true);
        this.mSettings.setSavePassword(false);
        this.mSettings.setSaveFormData(false);
        this.mUrl = AUTHORIZE_PATH + "?" + parseBundle(addLocaleIfNeeded(getIntent().getBundleExtra("url_param")));
        this.mWebView.loadUrl(this.mUrl);
        this.mWebView.setWebViewClient(new WebViewClient() {
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                String currentUrl = new String(url);
                int index = currentUrl.indexOf(63);
                String param;
                if (index > 0) {
                    param = currentUrl.substring(index + 1);
                    if (param.startsWith("code=") || param.contains("&code=")) {
                        AuthorizeActivity.this.setResultAndFinsih(AuthorizeActivity.RESULT_SUCCESS, currentUrl);
                        return;
                    } else if (param.startsWith("error=") || param.contains("&error=")) {
                        AuthorizeActivity.this.setResultAndFinsih(AuthorizeActivity.RESULT_FAIL, currentUrl);
                        return;
                    } else {
                        return;
                    }
                }
                index = currentUrl.indexOf(35);
                if (index > 0) {
                    param = currentUrl.substring(index + 1);
                    if (param.startsWith("access_token=") || param.contains("&access_token=")) {
                        AuthorizeActivity.this.setResultAndFinsih(AuthorizeActivity.RESULT_SUCCESS, currentUrl.replace("#", "?"));
                    } else if (param.startsWith("error=") || param.contains("&error=")) {
                        AuthorizeActivity.this.setResultAndFinsih(AuthorizeActivity.RESULT_FAIL, currentUrl.replace("#", "?"));
                    }
                }
            }

            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return super.shouldOverrideUrlLoading(view, url);
            }

            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }
        });
        setTitle(R.string.authorize_title);
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
        } else {
            setResultAndFinsih(RESULT_CANCEL, null);
        }
    }

    private void setResultAndFinsih(int resultCode, String url) {
        Intent intent = new Intent();
        intent.putExtras(parseUrl(url));
        setResult(resultCode, intent);
        finish();
    }

    private Bundle parseUrl(String url) {
        Bundle b = new Bundle();
        if (url != null) {
            try {
                for (NameValuePair pair : URLEncodedUtils.parse(new URI(url), UTF8)) {
                    if (!(TextUtils.isEmpty(pair.getName()) || TextUtils.isEmpty(pair.getValue()))) {
                        b.putString(pair.getName(), pair.getValue());
                    }
                }
            } catch (URISyntaxException e) {
                Log.e(TAG, "parse url exception", e);
            }
        }
        return b;
    }

    private String parseBundle(Bundle parameters) {
        if (parameters == null) {
            return "";
        }
        List<NameValuePair> list = new ArrayList();
        for (String key : parameters.keySet()) {
            String value = parameters.getString(key);
            if (!(TextUtils.isEmpty(key) || TextUtils.isEmpty(value))) {
                list.add(new BasicNameValuePair(key, value));
            }
        }
        return URLEncodedUtils.format(list, UTF8);
    }

    private Bundle addLocaleIfNeeded(Bundle bundle) {
        if (!(bundle == null || bundle.containsKey(LOCALE_KEY_IN_URL))) {
            Locale defaultLocale = Locale.getDefault();
            if (locale2UrlParamMap.containsKey(defaultLocale)) {
                bundle.putString(LOCALE_KEY_IN_URL, (String) locale2UrlParamMap.get(defaultLocale));
            }
        }
        return bundle;
    }

    private static String getStringInBundle(Bundle options, String key, String defaultValue) {
        String val = options.getString(key);
        return val != null ? val : defaultValue;
    }

    public static Intent getAuthorizeIntent(Context context, String clientId, String redirectUri, Bundle options) {
        Intent intent = new Intent(context, AuthorizeActivity.class);
        Bundle basicBundle = new Bundle();
        basicBundle.putString("client_id", String.valueOf(clientId));
        basicBundle.putString("redirect_uri", redirectUri);
        basicBundle.putString("response_type", getStringInBundle(options, EXTRA_RESPONSE_TYPE, "token"));
        if (!TextUtils.isEmpty(options.getString(EXTRA_SCOPE))) {
            basicBundle.putString("scope", options.getString(EXTRA_SCOPE));
        }
        if (!TextUtils.isEmpty(options.getString(EXTRA_STATE))) {
            basicBundle.putString("state", options.getString(EXTRA_STATE));
        }
        basicBundle.putString("skip_confirm", getStringInBundle(options, EXTRA_SKIP_CONFIRM, Constants.FALSE));
        intent.putExtra("url_param", basicBundle);
        return intent;
    }
}
