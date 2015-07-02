package com.google.android.gms.internal;

import android.content.Context;
import android.view.View;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
import java.io.File;

@fd
public final class gr {
    public static void a(Context context, WebSettings webSettings) {
        File cacheDir = context.getCacheDir();
        if (cacheDir != null) {
            webSettings.setAppCachePath(cacheDir.getAbsolutePath());
            webSettings.setAppCacheMaxSize(0);
            webSettings.setAppCacheEnabled(true);
        }
        webSettings.setDatabasePath(context.getDatabasePath("com.google.android.gms.ads.db").getAbsolutePath());
        webSettings.setDatabaseEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setDisplayZoomControls(false);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setSupportZoom(true);
    }

    public static void a(Window window) {
        window.setFlags(16777216, 16777216);
    }

    public static void a(WebView webView) {
        webView.onPause();
    }

    public static void b(WebView webView) {
        webView.onResume();
    }

    public static void i(View view) {
        view.setLayerType(1, null);
    }

    public static void j(View view) {
        view.setLayerType(0, null);
    }
}
