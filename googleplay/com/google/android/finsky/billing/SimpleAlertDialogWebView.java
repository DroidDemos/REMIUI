package com.google.android.finsky.billing;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.AttributeSet;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import com.android.vending.R;
import com.google.android.finsky.activities.SimpleAlertDialog.ConfigurableView;

public class SimpleAlertDialogWebView extends FrameLayout implements ConfigurableView {
    private String mUrl;

    public SimpleAlertDialogWebView(Context context) {
        super(context);
    }

    public SimpleAlertDialogWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SimpleAlertDialogWebView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void configureView(Bundle arguments) {
        this.mUrl = arguments.getString("url_key");
        WebView webView = (WebView) findViewById(R.id.billing_url_webview);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.loadUrl(this.mUrl);
        webView.setWebViewClient(new WebViewClient() {
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                if (url.equals(SimpleAlertDialogWebView.this.mUrl)) {
                    SimpleAlertDialogWebView.this.findViewById(R.id.loading_indicator).setVisibility(0);
                    view.setVisibility(8);
                }
            }

            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                SimpleAlertDialogWebView.this.findViewById(R.id.loading_indicator).setVisibility(8);
                view.setVisibility(0);
            }

            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);
                view.setVisibility(8);
                SimpleAlertDialogWebView.this.findViewById(R.id.loading_indicator).setVisibility(8);
                SimpleAlertDialogWebView.this.findViewById(R.id.error_msg).setVisibility(8);
            }
        });
    }

    public Bundle getResult() {
        return null;
    }
}
