package com.google.android.gms.internal;

import android.content.Context;
import android.webkit.WebSettings;

@fd
public final class gt {
    public static void a(Context context, WebSettings webSettings) {
        gr.a(context, webSettings);
        webSettings.setMediaPlaybackRequiresUserGesture(false);
    }

    public static String getDefaultUserAgent(Context context) {
        return WebSettings.getDefaultUserAgent(context);
    }
}
