package com.google.android.wallet.instrumentmanager.api.http;

import android.content.Context;
import android.os.Build;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.toolbox.HurlStack;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.HttpResponse;

public class GoogleHurlStack extends HurlStack {
    private static final String USER_AGENT;

    static {
        USER_AGENT = "unused/0 (" + Build.DEVICE + " " + Build.ID + "); gzip";
    }

    public GoogleHurlStack(Context appContext) {
        super(new HurlStackUrlRewriter(appContext));
    }

    public HttpResponse performRequest(Request<?> request, Map<String, String> additionalHeaders) throws IOException, AuthFailureError {
        Map<String, String> additionalHeadersWithUserAgent;
        if (additionalHeaders == null) {
            additionalHeadersWithUserAgent = Collections.singletonMap("User-Agent", USER_AGENT);
        } else if (additionalHeaders.containsKey("User-Agent")) {
            additionalHeadersWithUserAgent = additionalHeaders;
        } else {
            additionalHeadersWithUserAgent = new HashMap(additionalHeaders);
            additionalHeadersWithUserAgent.put("User-Agent", USER_AGENT);
        }
        return super.performRequest(request, additionalHeadersWithUserAgent);
    }
}
