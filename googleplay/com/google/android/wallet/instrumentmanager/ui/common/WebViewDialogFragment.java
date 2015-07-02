package com.google.android.wallet.instrumentmanager.ui.common;

import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.google.android.wallet.instrumentmanager.R;

public class WebViewDialogFragment extends BaseInstrumentManagerDialogFragment implements OnKeyListener, OnTouchListener {
    TextView mErrorText;
    private boolean mPageLoaded;
    ProgressBar mProgressBar;
    WebView mWebView;

    private static class WebViewWithKeyboardSupport extends WebView {
        private WebViewWithKeyboardSupport(Context context) {
            super(context);
        }

        public boolean onCheckIsTextEditor() {
            return true;
        }
    }

    public static WebViewDialogFragment newInstance(String url, int themeResourceId) {
        Bundle args = BaseInstrumentManagerDialogFragment.createArgs(themeResourceId);
        args.putString("url", url);
        WebViewDialogFragment fragment = new WebViewDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("pageLoaded", this.mPageLoaded);
        if (this.mPageLoaded) {
            this.mWebView.saveState(outState);
        }
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            this.mPageLoaded = savedInstanceState.getBoolean("pageLoaded");
        }
        ViewGroup rootView = (ViewGroup) getThemedLayoutInflater().inflate(R.layout.view_web_view, null, false);
        final String initialUrl = getArguments().getString("url");
        this.mProgressBar = (ProgressBar) rootView.findViewById(R.id.progress_bar);
        this.mErrorText = (TextView) rootView.findViewById(R.id.error_msg);
        this.mWebView = new WebViewWithKeyboardSupport(getThemedContext());
        this.mWebView.setLayoutParams(new LayoutParams(-2, -2));
        this.mWebView.setVisibility(8);
        this.mWebView.setOnKeyListener(this);
        if (VERSION.SDK_INT <= 10) {
            this.mWebView.setOnTouchListener(this);
        }
        WebSettings webSettings = this.mWebView.getSettings();
        webSettings.setSupportZoom(true);
        webSettings.setJavaScriptEnabled(true);
        webSettings.setAllowFileAccess(false);
        webSettings.setBuiltInZoomControls(true);
        if (VERSION.SDK_INT >= 11) {
            webSettings.setDisplayZoomControls(false);
        }
        if (!this.mPageLoaded || this.mWebView.restoreState(savedInstanceState) == null) {
            this.mPageLoaded = false;
            this.mWebView.loadUrl(initialUrl);
        }
        this.mWebView.setWebViewClient(new WebViewClient() {
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                if (url.equals(initialUrl)) {
                    WebViewDialogFragment.this.mProgressBar.setVisibility(0);
                    WebViewDialogFragment.this.mErrorText.setVisibility(8);
                    view.setVisibility(8);
                }
            }

            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                WebViewDialogFragment.this.mProgressBar.setVisibility(8);
                WebViewDialogFragment.this.mErrorText.setVisibility(8);
                view.setVisibility(0);
                WebViewDialogFragment.this.mPageLoaded = true;
            }

            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);
                WebViewDialogFragment.this.mProgressBar.setVisibility(8);
                WebViewDialogFragment.this.mErrorText.setVisibility(0);
                view.setVisibility(8);
                WebViewDialogFragment.this.mPageLoaded = false;
            }
        });
        rootView.addView(this.mWebView);
        this.mWebView.requestFocus();
        return new Builder(getActivity()).setView(rootView).setPositiveButton(R.string.wallet_im_close, null).create();
    }

    public boolean onKey(View view, int keyCode, KeyEvent event) {
        if (keyCode != 4 || !this.mWebView.canGoBack()) {
            return false;
        }
        this.mWebView.goBack();
        return true;
    }

    public boolean onTouch(View view, MotionEvent event) {
        switch (event.getAction()) {
            case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
            case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                if (!view.hasFocus()) {
                    view.requestFocus();
                    break;
                }
                break;
        }
        return false;
    }
}
