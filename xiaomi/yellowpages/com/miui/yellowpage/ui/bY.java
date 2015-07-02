package com.miui.yellowpage.ui;

import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.os.Bundle;
import android.webkit.WebView;

/* compiled from: BaseWebFragment */
class bY implements AccountManagerCallback {
    final /* synthetic */ WebView BN;
    final /* synthetic */ bi BO;

    bY(bi biVar, WebView webView) {
        this.BO = biVar;
        this.BN = webView;
    }

    public void run(AccountManagerFuture accountManagerFuture) {
        try {
            Bundle bundle = (Bundle) accountManagerFuture.getResult();
            if (!this.BO.this$0.mActivity.isFinishing()) {
                this.BN.loadUrl(bundle.getString("authtoken"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
