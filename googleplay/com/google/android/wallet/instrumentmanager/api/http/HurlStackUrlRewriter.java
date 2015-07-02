package com.google.android.wallet.instrumentmanager.api.http;

import android.content.Context;
import com.android.volley.toolbox.HurlStack.UrlRewriter;
import com.google.android.volley.UrlTools;

public class HurlStackUrlRewriter implements UrlRewriter {
    private final Context mAppContext;

    public HurlStackUrlRewriter(Context appContext) {
        this.mAppContext = appContext;
    }

    public String rewriteUrl(String originalUrl) {
        return UrlTools.rewrite(this.mAppContext, originalUrl);
    }
}
