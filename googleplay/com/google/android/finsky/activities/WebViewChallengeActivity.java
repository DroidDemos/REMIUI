package com.google.android.finsky.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import com.android.vending.R;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.analytics.BackgroundEventBuilder;
import com.google.android.finsky.analytics.FinskyEventLog;
import com.google.android.finsky.analytics.PlayStore.PlayStoreUiElementInfo;
import com.google.android.finsky.layout.ButtonBar;
import com.google.android.finsky.layout.ButtonBar.ClickListener;
import com.google.android.finsky.layout.actionbar.ActionBarHelper;
import com.google.android.finsky.layout.play.GenericUiElementNode;
import com.google.android.finsky.protos.ChallengeProto.WebViewChallenge;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.ParcelableProto;
import java.io.ByteArrayInputStream;

public class WebViewChallengeActivity extends ActionBarActivity implements ClickListener {
    private WebViewChallenge mChallenge;
    private boolean mIsFirstPageLoad;
    private FinskyEventLog mLogger;
    private final FakeNavigationManager mNavigationManager;
    private GenericUiElementNode mNode;
    private WebView mWebView;

    public WebViewChallengeActivity() {
        this.mNavigationManager = new FakeNavigationManager(this) {
            public boolean goUp() {
                WebViewChallengeActivity.this.cancel(null);
                return true;
            }
        };
        this.mIsFirstPageLoad = true;
    }

    public static Intent createIntent(String accountName, String backupTitle, WebViewChallenge webViewChallenge) {
        Intent intent = new Intent(FinskyApp.get(), WebViewChallengeActivity.class);
        intent.putExtra("authAccount", accountName);
        intent.putExtra("backupTitle", backupTitle);
        intent.putExtra("challenge", ParcelableProto.forProto(webViewChallenge));
        return intent;
    }

    protected void onCreate(Bundle savedInstanceState) {
        String title;
        this.mChallenge = (WebViewChallenge) ParcelableProto.getProtoFromIntent(getIntent(), "challenge");
        if (this.mChallenge.hasTitle) {
            title = this.mChallenge.title;
        } else {
            title = getIntent().getStringExtra("backupTitle");
        }
        if (TextUtils.isEmpty(title)) {
            requestWindowFeature(1);
        }
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.dialog_when_large);
        Toolbar toolbar = (Toolbar) findViewById(R.id.action_bar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }
        getLayoutInflater().inflate(R.layout.billing_webviewchallenge, (FrameLayout) findViewById(R.id.content_frame));
        this.mLogger = FinskyApp.get().getEventLogger(getIntent().getStringExtra("authAccount"));
        ActionBarHelper actionBarHelper = new ActionBarHelper(this.mNavigationManager, this);
        actionBarHelper.updateCurrentBackendId(0, false);
        actionBarHelper.updateActionBarMode(false);
        ButtonBar buttonBar = (ButtonBar) findViewById(R.id.button_bar);
        buttonBar.setPositiveButtonVisible(false);
        if (this.mChallenge.hasCancelButtonDisplayLabel) {
            buttonBar.setNegativeButtonTitle(this.mChallenge.cancelButtonDisplayLabel);
        } else {
            buttonBar.setVisibility(8);
        }
        buttonBar.setClickListener(this);
        if (!TextUtils.isEmpty(title)) {
            actionBarHelper.updateDefaultTitle(title);
            setTitle(title);
        }
        this.mWebView = (WebView) findViewById(R.id.webview);
        this.mWebView.setOnTouchListener(new OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case com.google.android.wallet.instrumentmanager.R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                    case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                        if (!v.hasFocus()) {
                            v.requestFocus();
                            break;
                        }
                        break;
                }
                return false;
            }
        });
        this.mWebView.getSettings().setJavaScriptEnabled(true);
        this.mWebView.getSettings().setAllowFileAccess(false);
        this.mWebView.setWebViewClient(new WebViewClient() {
            public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
                if (WebViewChallengeActivity.this.checkUrlAndLog(url)) {
                    return super.shouldInterceptRequest(view, url);
                }
                return new WebResourceResponse("text/plain", "UTF-8", new ByteArrayInputStream(("Blocked non-HTTPS resource: " + url).getBytes()));
            }

            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (WebViewChallengeActivity.this.checkUrlAndLog(url)) {
                    return super.shouldOverrideUrlLoading(view, url);
                }
                WebViewChallengeActivity.this.cancel(null);
                return true;
            }

            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                if (!WebViewChallengeActivity.this.checkUrlAndLog(url)) {
                    view.stopLoading();
                    WebViewChallengeActivity.this.cancel(null);
                } else if (url.matches(WebViewChallengeActivity.this.mChallenge.targetUrlRegexp)) {
                    WebViewChallengeActivity.this.onTargetUrlMatch(url);
                } else if (WebViewChallengeActivity.this.mChallenge.hasCancelUrlRegexp && url.matches(WebViewChallengeActivity.this.mChallenge.cancelUrlRegexp)) {
                    WebViewChallengeActivity.this.cancel(url);
                } else {
                    super.onPageStarted(view, url, favicon);
                    if (WebViewChallengeActivity.this.mIsFirstPageLoad) {
                        WebViewChallengeActivity.this.findViewById(R.id.progress_bar).setVisibility(0);
                        view.setVisibility(8);
                        WebViewChallengeActivity.this.mIsFirstPageLoad = false;
                    }
                }
            }

            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                WebViewChallengeActivity.this.findViewById(R.id.progress_bar).setVisibility(8);
                view.setVisibility(0);
            }
        });
        String startUrl = this.mChallenge.startUrl;
        PlayStoreUiElementInfo clientCookie = null;
        String hostName = Uri.parse(startUrl).getHost();
        if (!TextUtils.isEmpty(hostName)) {
            clientCookie = new PlayStoreUiElementInfo();
            clientCookie.host = hostName;
            clientCookie.hasHost = true;
        }
        this.mNode = new GenericUiElementNode(316, null, clientCookie, null);
        if (savedInstanceState == null) {
            this.mLogger.logPathImpression(0, this.mNode);
            this.mWebView.loadUrl(startUrl);
            return;
        }
        Bundle b = savedInstanceState.getBundle("webview_state");
        if (b != null) {
            this.mWebView.restoreState(b);
        }
    }

    private boolean checkUrlAndLog(String url) {
        Uri uri = Uri.parse(url);
        if ("https".equalsIgnoreCase(uri.getScheme()) || "data".equalsIgnoreCase(uri.getScheme())) {
            return true;
        }
        FinskyLog.e("Detected non-HTTPS resource from host: %s", uri.getHost());
        this.mLogger.logBackgroundEvent(new BackgroundEventBuilder(504).setHost(host).build());
        return false;
    }

    private void onTargetUrlMatch(String url) {
        FinskyLog.d("Matched URL: %s", url);
        PlayStoreUiElementInfo clientCookie = null;
        String hostName = Uri.parse(url).getHost();
        if (!TextUtils.isEmpty(hostName)) {
            clientCookie = new PlayStoreUiElementInfo();
            clientCookie.host = hostName;
            clientCookie.hasHost = true;
        }
        this.mLogger.logClickEventWithClientCookie(267, clientCookie, this.mNode);
        Intent intent = new Intent();
        Bundle challengeResponse = new Bundle();
        challengeResponse.putString(this.mChallenge.responseTargetUrlParam, url);
        intent.putExtra("challenge_response", challengeResponse);
        setResult(-1, intent);
        finish();
    }

    public void onPositiveButtonClick() {
    }

    public void onNegativeButtonClick() {
        cancel(null);
    }

    private void cancel(String url) {
        PlayStoreUiElementInfo clientCookie = null;
        if (url != null) {
            clientCookie = new PlayStoreUiElementInfo();
            clientCookie.host = Uri.parse(url).getHost();
            clientCookie.hasHost = true;
        }
        this.mLogger.logClickEventWithClientCookie(268, clientCookie, this.mNode);
        setResult(0);
        finish();
    }

    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Bundle b = new Bundle();
        this.mWebView.saveState(b);
        outState.putBundle("webview_state", b);
    }

    public void onBackPressed() {
        if (this.mWebView.canGoBack()) {
            this.mWebView.goBack();
        } else {
            onNegativeButtonClick();
        }
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() != 16908332) {
            return super.onOptionsItemSelected(item);
        }
        onNegativeButtonClick();
        return true;
    }
}
