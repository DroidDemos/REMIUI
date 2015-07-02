package com.miui.yellowpage.activity;

import android.graphics.Bitmap;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.miui.yellowpage.base.utils.Log;

/* compiled from: BaseActivity */
class ak extends WebViewClient {
    private boolean Aw;
    final /* synthetic */ BaseActivity ti;

    private ak(BaseActivity baseActivity) {
        this.ti = baseActivity;
    }

    public boolean shouldOverrideUrlLoading(WebView webView, String str) {
        return false;
    }

    public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
        super.onPageStarted(webView, str, bitmap);
        this.Aw = true;
        Log.d("BaseActivity", "load url:" + str);
    }

    public void onPageFinished(WebView webView, String str) {
        super.onPageFinished(webView, str);
        Log.d("BaseActivity", "loaded url:" + str);
        if (this.Aw) {
            this.ti.mBannerView.setVisibility(0);
        }
    }

    public void onReceivedError(WebView webView, int i, String str, String str2) {
        super.onReceivedError(webView, i, str, str2);
        this.Aw = false;
        Log.d("BaseActivity", "received error:" + str);
    }
}
