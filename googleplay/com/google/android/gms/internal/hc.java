package com.google.android.gms.internal;

import android.content.Context;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@fd
public class hc extends ha {
    public hc(gz gzVar, boolean z) {
        super(gzVar, z);
    }

    protected WebResourceResponse d(Context context, String str, String str2) throws IOException {
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str2).openConnection();
        WebResourceResponse webResourceResponse = true;
        try {
            gn.a(context, str, true, httpURLConnection, true);
            httpURLConnection.addRequestProperty("Cache-Control", "max-stale=3600");
            httpURLConnection.connect();
            webResourceResponse = new WebResourceResponse("application/javascript", "UTF-8", new ByteArrayInputStream(gn.a(new InputStreamReader(httpURLConnection.getInputStream())).getBytes("UTF-8")));
            return webResourceResponse;
        } finally {
            httpURLConnection.disconnect();
        }
    }

    public WebResourceResponse shouldInterceptRequest(WebView webView, String url) {
        try {
            if (!"mraid.js".equalsIgnoreCase(new File(url).getName())) {
                return super.shouldInterceptRequest(webView, url);
            }
            if (webView instanceof gz) {
                gz gzVar = (gz) webView;
                gzVar.dD().cg();
                if (gzVar.ac().os) {
                    gw.v("shouldInterceptRequest(https://googleads.g.doubleclick.net/mads/static/mad/sdk/native/mraid/v2/mraid_app_interstitial.js)");
                    return d(gzVar.getContext(), this.mr.dG().wR, "https://googleads.g.doubleclick.net/mads/static/mad/sdk/native/mraid/v2/mraid_app_interstitial.js");
                } else if (gzVar.dH()) {
                    gw.v("shouldInterceptRequest(https://googleads.g.doubleclick.net/mads/static/mad/sdk/native/mraid/v2/mraid_app_expanded_banner.js)");
                    return d(gzVar.getContext(), this.mr.dG().wR, "https://googleads.g.doubleclick.net/mads/static/mad/sdk/native/mraid/v2/mraid_app_expanded_banner.js");
                } else {
                    gw.v("shouldInterceptRequest(https://googleads.g.doubleclick.net/mads/static/mad/sdk/native/mraid/v2/mraid_app_banner.js)");
                    return d(gzVar.getContext(), this.mr.dG().wR, "https://googleads.g.doubleclick.net/mads/static/mad/sdk/native/mraid/v2/mraid_app_banner.js");
                }
            }
            gw.w("Tried to intercept request from a WebView that wasn't an AdWebView.");
            return super.shouldInterceptRequest(webView, url);
        } catch (IOException e) {
            gw.w("Could not fetch MRAID JS. " + e.getMessage());
            return super.shouldInterceptRequest(webView, url);
        }
    }
}
