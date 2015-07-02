package com.miui.yellowpage.ui;

import android.app.AlertDialog.Builder;
import android.text.TextUtils;
import android.webkit.GeolocationPermissions.Callback;
import android.webkit.JsResult;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import com.miui.yellowpage.utils.H;

/* compiled from: BaseWebFragment */
public class C extends WebChromeClient {
    final /* synthetic */ bw this$0;

    public C(bw bwVar) {
        this.this$0 = bwVar;
    }

    public void onGeolocationPermissionsShowPrompt(String str, Callback callback) {
        callback.invoke(str, true, false);
    }

    public boolean onJsConfirm(WebView webView, String str, String str2, JsResult jsResult) {
        Builder builder = new Builder(this.this$0.mActivity);
        CharSequence title = webView.getTitle();
        if (!TextUtils.isEmpty(title)) {
            builder.setTitle(title);
        }
        builder.setMessage(str2);
        builder.setPositiveButton(17039370, new dM(this, jsResult));
        builder.setNegativeButton(17039360, new dN(this, jsResult));
        builder.setOnCancelListener(new dI(this, jsResult));
        this.this$0.mCurJsResult = jsResult;
        builder.show();
        return true;
    }

    public boolean onJsAlert(WebView webView, String str, String str2, JsResult jsResult) {
        Builder builder = new Builder(this.this$0.mActivity);
        CharSequence title = webView.getTitle();
        if (!TextUtils.isEmpty(title)) {
            builder.setTitle(title);
        }
        builder.setMessage(str2);
        builder.setPositiveButton(17039370, new dJ(this, jsResult));
        builder.setOnCancelListener(new dH(this, jsResult));
        builder.show();
        this.this$0.mCurJsResult = jsResult;
        return true;
    }

    public void onReceivedTitle(WebView webView, String str) {
        super.onReceivedTitle(webView, str);
        this.this$0.injectOpenJS();
    }

    public void openFileChooser(ValueCallback valueCallback, String str, String str2) {
        this.this$0.mUploadHandler = new H(this.this$0);
        this.this$0.mUploadHandler.openFileChooser(valueCallback, str, str2);
    }
}
