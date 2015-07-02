package com.google.android.finsky.activities;

import android.text.style.URLSpan;
import android.view.View;

public abstract class UrlSpanHandler extends URLSpan {
    String mUrl;

    public abstract void onUrlClick(View view, String str);

    public UrlSpanHandler(String url) {
        super(url);
        this.mUrl = url;
    }

    public void onClick(View widget) {
        onUrlClick(widget, this.mUrl);
    }
}
