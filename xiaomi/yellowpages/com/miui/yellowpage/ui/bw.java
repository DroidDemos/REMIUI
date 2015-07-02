package com.miui.yellowpage.ui;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JsResult;
import android.webkit.WebBackForwardList;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.miui.yellowpage.R;
import com.miui.yellowpage.base.utils.BaseWebEvent;
import com.miui.yellowpage.base.utils.Files;
import com.miui.yellowpage.base.utils.Log;
import com.miui.yellowpage.base.utils.ThreadPool;
import com.miui.yellowpage.base.utils.WebService;
import com.miui.yellowpage.base.utils.WebViewConfigurator;
import com.miui.yellowpage.utils.H;
import com.miui.yellowpage.widget.pulltorefresh.PullToRefreshBase.Mode;
import com.miui.yellowpage.widget.pulltorefresh.PullToRefreshWebView;
import com.miui.yellowpage.widget.pulltorefresh.a;
import miui.os.Build;

/* compiled from: BaseWebFragment */
public abstract class bw extends cs {
    protected static final int BACKGROUND_WORK_DELAY_IN_MILLIS = 500;
    protected static final String BLANK_SCREEN_URL = "about:blank";
    public static final int FILE_CHOOSER_RESULT_CODE = 10000;
    protected static final int INIT_PROGRESS_VALUE = 10;
    private static String INJECTED_JS_CONTENT = null;
    private static final String TAG = "BaseWebFragment";
    private JsResult mCurJsResult;
    protected Handler mHandler;
    protected PullToRefreshWebView mPullToRefreshWebView;
    protected H mUploadHandler;
    protected BaseWebEvent mWebEvent;
    private BroadcastReceiver mWebResBroadcastReceiver;
    protected WebView mWebView;

    protected abstract a getOnRefreshListener();

    public bw() {
        this.mWebResBroadcastReceiver = new cV(this);
    }

    static {
        WebView.setWebContentsDebuggingEnabled(Build.IS_DEBUGGABLE);
    }

    protected void onPageForwardOrBackward() {
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ThreadPool.execute(new cX(this));
        this.mActivity.getApplicationContext().registerReceiver(this.mWebResBroadcastReceiver, new IntentFilter("com.miui.yellowpage.web_page_resources_updated"));
        this.mHandler = new Handler();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        int onGetViewId = onGetViewId();
        if (onGetViewId <= 0) {
            throw new IllegalArgumentException("You must provide a valid layout");
        }
        View inflate = layoutInflater.inflate(onGetViewId, viewGroup, false);
        this.mPullToRefreshWebView = (PullToRefreshWebView) inflate.findViewById(R.id.browser);
        if (this.mPullToRefreshWebView == null) {
            throw new IllegalArgumentException("As a web view fragment, you must provide a webview named browser");
        }
        this.mPullToRefreshWebView.a(getOnRefreshListener());
        this.mWebView = (WebView) this.mPullToRefreshWebView.fs();
        onInitWebViewSettings();
        return inflate;
    }

    public void onResume() {
        super.onResume();
        this.mWebView.onResume();
    }

    public void onPause() {
        super.onPause();
        if (this.mCurJsResult != null) {
            this.mCurJsResult.cancel();
            this.mCurJsResult = null;
        }
        this.mWebView.onPause();
    }

    public void onDestroy() {
        super.onDestroy();
        this.mActivity.getApplicationContext().unregisterReceiver(this.mWebResBroadcastReceiver);
        ((ViewGroup) this.mWebView.getParent()).removeView(this.mWebView);
        this.mWebView.destroy();
        this.mWebView = null;
    }

    protected int onGetViewId() {
        return R.layout.base_web_view;
    }

    protected WebChromeClient onCreateWebChromeClient() {
        return new C(this);
    }

    protected WebViewClient onCreateWebViewClient() {
        return new bi(this);
    }

    protected void onInitWebViewSettings() {
        WebViewConfigurator.configure(this.mActivity, this.mWebView);
        WebChromeClient onCreateWebChromeClient = onCreateWebChromeClient();
        if (onCreateWebChromeClient != null) {
            this.mWebView.setWebChromeClient(onCreateWebChromeClient);
        }
        WebViewClient onCreateWebViewClient = onCreateWebViewClient();
        if (onCreateWebViewClient != null) {
            this.mWebView.setWebViewClient(onCreateWebViewClient);
        }
        this.mWebView.setOverScrollMode(2);
        this.mWebView.requestFocus();
        this.mWebView.setDownloadListener(new be());
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        if (i == FILE_CHOOSER_RESULT_CODE) {
            this.mUploadHandler.a(i2, intent);
        } else {
            super.onActivityResult(i, i2, intent);
        }
    }

    public void reload() {
        Log.d(TAG, "reload");
        this.mWebView.reload();
    }

    protected void onNetworkConnected() {
        reload();
    }

    public boolean onBackPressed() {
        return goBack();
    }

    protected boolean goBack() {
        Log.d(TAG, "goBack");
        if (!this.mWebView.canGoBack()) {
            return false;
        }
        WebBackForwardList copyBackForwardList = this.mWebView.copyBackForwardList();
        int stepsToGoBack = stepsToGoBack();
        int currentIndex = copyBackForwardList.getCurrentIndex();
        if (stepsToGoBack > currentIndex) {
            return false;
        }
        CharSequence title = copyBackForwardList.getItemAtIndex(currentIndex - stepsToGoBack).getTitle();
        if (!TextUtils.isEmpty(title)) {
            this.mActivity.setTitle(title);
        }
        this.mWebView.goBackOrForward(-stepsToGoBack);
        return true;
    }

    private int stepsToGoBack() {
        int i = 1;
        WebBackForwardList copyBackForwardList = this.mWebView.copyBackForwardList();
        int currentIndex = copyBackForwardList.getCurrentIndex();
        for (int i2 = 0; i2 <= currentIndex; i2++) {
            CharSequence url = copyBackForwardList.getItemAtIndex(currentIndex - i2).getUrl();
            if (!BLANK_SCREEN_URL.equalsIgnoreCase(url) && TextUtils.equals(this.mWebView.getUrl(), url)) {
                break;
            }
            i++;
        }
        return i;
    }

    public void loadUrl(String str) {
        if (this.mWebView == null || TextUtils.isEmpty(str)) {
            Log.e(TAG, "The url should not be null, load nothing");
            return;
        }
        Log.d(TAG, "loadUrl: " + str);
        this.mWebView.loadUrl(str);
        doSomeBackgroundWorks(str);
    }

    private void doSomeBackgroundWorks(String str) {
        this.mHandler.postDelayed(new cZ(this, str), 500);
    }

    private void injectOpenJS() {
        Log.d(TAG, "Inject open js ");
        if (INJECTED_JS_CONTENT == null) {
            INJECTED_JS_CONTENT = String.format("javascript:(function() { try { %s } catch(e) { MiuiYellowPageApi.log(e.message); } }())", new Object[]{Files.getUriContentString(this.mActivity, WebService.INJECTION_JS_URI)});
        }
        if (!TextUtils.isEmpty(INJECTED_JS_CONTENT) && this.mWebView != null) {
            this.mWebView.evaluateJavascript(INJECTED_JS_CONTENT, null);
        }
    }

    protected void disablePullToRefresh() {
        this.mPullToRefreshWebView.a(Mode.DISABLED);
    }

    protected void enablePullToRefresh() {
        this.mPullToRefreshWebView.a(Mode.PULL_FROM_START);
    }
}
