package com.google.android.volley;

import android.content.Context;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpClientStack;

public class GoogleHttpClientStack extends HttpClientStack {
    private final GoogleHttpClient mGoogleHttpClient;

    public GoogleHttpClientStack(Context context) {
        this(context, false);
    }

    public GoogleHttpClientStack(Context context, boolean enableSensitiveLogging) {
        this(new GoogleHttpClient(context, "unused/0", true), enableSensitiveLogging);
    }

    private GoogleHttpClientStack(GoogleHttpClient httpClient, boolean enableSensitiveLogging) {
        super(httpClient);
        this.mGoogleHttpClient = httpClient;
        if (enableSensitiveLogging) {
            httpClient.enableCurlLogging(VolleyLog.TAG, 2);
        }
    }
}
