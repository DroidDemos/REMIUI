package com.google.android.libraries.happiness;

import android.content.Context;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.DialogFragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.webkit.ConsoleMessage;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

public class HatsSurveyManager {
    private static final String TAG;
    protected HatsSurveyClientImpl mClient;
    private final Context mContext;
    private HatsSurveyDialog mDialog;
    private final Handler mHandler;
    private final int mLayout;
    private final int mLayoutId;
    protected HatsSurveyParams mParams;
    protected final WebView mWebView;

    protected static class HatsSurveyClientImpl implements HatsSurveyClient {
        private boolean isComplete;
        private final HatsSurveyClient mClient;
        private final Handler mHandler;
        private final WebView mWebView;

        public HatsSurveyClientImpl(HatsSurveyClient client, Handler handler, WebView webView) {
            this.isComplete = false;
            this.mClient = client;
            this.mHandler = handler;
            this.mWebView = webView;
        }

        public boolean isComplete() {
            return this.isComplete;
        }

        @JavascriptInterface
        public void onWindowError() {
            this.isComplete = false;
            this.mHandler.post(new Runnable() {
                public void run() {
                    HatsSurveyClientImpl.this.mClient.onWindowError();
                }
            });
        }

        @JavascriptInterface
        public void onSurveyReady() {
            this.isComplete = false;
            this.mHandler.post(new Runnable() {
                public void run() {
                    HatsSurveyClientImpl.this.mClient.onSurveyReady();
                }
            });
        }

        @JavascriptInterface
        public void onSurveyComplete(final boolean justAnswered, final boolean unused) {
            this.isComplete = true;
            this.mHandler.post(new Runnable() {
                public void run() {
                    HatsSurveyClientImpl.this.mClient.onSurveyComplete(justAnswered, unused);
                }
            });
        }

        @JavascriptInterface
        public void onSurveyResponse(final String response, final String surveyId) {
            if (response.contains("t=a")) {
                this.mHandler.post(new Runnable() {
                    public void run() {
                        HatsSurveyClientImpl.this.mClient.onSurveyResponse(response, surveyId);
                    }
                });
            }
        }

        @JavascriptInterface
        public void onSurveyCanceled() {
            this.mHandler.post(new Runnable() {
                public void run() {
                    HatsSurveyClientImpl.this.mClient.onSurveyCanceled();
                }
            });
        }
    }

    static {
        TAG = HatsSurveyManager.class.getSimpleName();
    }

    public HatsSurveyManager(Context context, HatsSurveyClient client, HatsSurveyParams params, int layout, int layoutId) {
        if (context == null) {
            throw new NullPointerException();
        } else if (client == null) {
            throw new NullPointerException();
        } else if (params == null) {
            throw new NullPointerException();
        } else {
            this.mContext = context;
            this.mWebView = new WebView(this.mContext);
            this.mHandler = new Handler(this.mContext.getMainLooper());
            this.mParams = params;
            this.mClient = new HatsSurveyClientImpl(client, this.mHandler, this.mWebView);
            this.mLayout = layout;
            this.mLayoutId = layoutId;
            configureWebView();
            configureCookieManager();
        }
    }

    private void configureCookieManager() {
        String hostPrefix = "";
        String rawUrl = this.mParams.getParam("survey_url");
        if (rawUrl != null) {
            try {
                String host = new URL(rawUrl).getHost().toLowerCase();
                int offset = host.indexOf("google.");
                if (offset < 0) {
                    offset = 0;
                }
                hostPrefix = "." + host.substring(offset);
                SimpleDateFormat sdf = new SimpleDateFormat("EEE, dd-MMM-yyyy HH:mm:ss zzz", Locale.US);
                sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
                Calendar expiresCal = Calendar.getInstance();
                expiresCal.add(1, 1);
                String expires = sdf.format(Long.valueOf(expiresCal.getTimeInMillis()));
                String zwieback = this.mParams.getParam("zwieback_cookie");
                if (zwieback != null) {
                    if (!zwieback.startsWith("NID=")) {
                        zwieback = "NID=" + zwieback;
                    }
                    CookieSyncManager.createInstance(this.mWebView.getContext().getApplicationContext());
                    CookieManager cookieManager = CookieManager.getInstance();
                    cookieManager.setAcceptCookie(true);
                    cookieManager.setCookie(hostPrefix, zwieback + "; expires=" + expires + "; path=/; domain=" + hostPrefix + "; HttpOnly");
                    CookieSyncManager.getInstance().sync();
                }
            } catch (MalformedURLException e) {
                throw new IllegalArgumentException("Value for HatsSurveyParams.SURVEY_URL_KEY is an invalid URL: " + rawUrl);
            }
        }
    }

    private void configureWebView() {
        WebSettings settings = this.mWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDatabaseEnabled(false);
        settings.setDomStorageEnabled(true);
        String userAgent = this.mParams.getParam("user_agent");
        if (userAgent != null) {
            settings.setUserAgentString(userAgent);
        }
        this.mWebView.addJavascriptInterface(this.mClient, "_402m_native");
        this.mWebView.setOnLongClickListener(new OnLongClickListener() {
            public boolean onLongClick(View v) {
                return true;
            }
        });
        this.mWebView.setWebChromeClient(new WebChromeClient() {
            public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
                return true;
            }
        });
        this.mWebView.setWebViewClient(new WebViewClient() {
            public void onLoadResource(WebView view, String url) {
                super.onLoadResource(view, url);
            }

            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Log.w(HatsSurveyManager.TAG, description + " : " + failingUrl);
            }
        });
    }

    public DialogFragment getSurveyDialog() {
        if (this.mDialog == null) {
            this.mDialog = new HatsSurveyDialog();
            Bundle args = new Bundle();
            args.putInt("HatsSurveyDialog.HATS_SURVEY_LAYOUT", this.mLayout);
            args.putInt("HatsSurveyDialog.HATS_SURVEY_LAYOUT_ID", this.mLayoutId);
            this.mDialog.setArguments(args);
            this.mDialog.setOnCancelAction(new Runnable() {
                public void run() {
                    if (!HatsSurveyManager.this.mClient.isComplete()) {
                        HatsSurveyManager.this.declineSurvey();
                        HatsSurveyManager.this.mClient.onSurveyCanceled();
                    }
                }
            });
            this.mDialog.setWebView(this.mWebView);
            this.mDialog.setStyle(2, 16973913);
        }
        return this.mDialog;
    }

    protected String surveyScriptTag(String siteId) {
        String surveyUrl = this.mParams.getParam("survey_url");
        return String.format("<script src=\"%s?site=%s&force_http=1\"></script>", new Object[]{surveyUrl, siteId});
    }

    private String wrapNativeCallbackJS(String callback, String[] paramNames) {
        String params = paramNames == null ? "" : TextUtils.join(", ", Arrays.asList(paramNames));
        return String.format("_402m['%s'] = function(%s) { _402m_native.%s(%s); };\n", new Object[]{callback, params, callback, params});
    }

    private String wrappedResponseCallback() {
        return "_402m['onSurveyResponse'] = function(response) {var surveyId = _402.params.svyid;_402m_native.onSurveyResponse(response, surveyId);};\n";
    }

    public void requestSurvey() {
        if (VERSION.SDK_INT >= 11) {
            this.mWebView.onResume();
        }
        this.mWebView.loadUrl("about:blank");
        String siteId = this.mParams.getParam("site_id");
        this.mWebView.loadData("<!doctype><html><head><meta name=\"viewport\" content=\"initial-scale=1.0,user-scalable=no\"><script>_402m = {};" + wrapNativeCallbackJS("onWindowError", null) + "window.onerror=function(){_402m.onWindowError();};" + wrapNativeCallbackJS("onSurveyReady", null) + wrapNativeCallbackJS("onSurveyComplete", new String[]{"justAnswered", "unused"}) + wrapNativeCallbackJS("onSurveyCanceled", null) + wrappedResponseCallback() + this.mParams.toJS("_402m") + "</script>" + surveyScriptTag(siteId) + "</head><body></body></html>", "text/html", null);
    }

    public void declineSurvey() {
        this.mWebView.loadUrl("javascript:try { _402.close(true) } catch(e) {}");
    }
}
