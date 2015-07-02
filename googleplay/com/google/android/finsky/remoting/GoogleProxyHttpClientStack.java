package com.google.android.finsky.remoting;

import android.content.Context;
import com.google.android.volley.GoogleHttpClientStack;
import org.apache.http.HttpHost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.params.ConnRouteParams;

public class GoogleProxyHttpClientStack extends GoogleHttpClientStack implements SupportsProxy {
    private String proxyHost;
    private int proxyPort;

    public GoogleProxyHttpClientStack(Context context) {
        super(context);
    }

    public void clearProxy() {
        this.proxyHost = null;
        this.proxyPort = -1;
    }

    public void setProxy(String host, int port) {
        this.proxyHost = host;
        this.proxyPort = port;
    }

    protected void onPrepareRequest(HttpUriRequest request) {
        if (this.proxyHost != null && this.proxyPort > 0) {
            ConnRouteParams.setDefaultProxy(request.getParams(), new HttpHost(this.proxyHost, this.proxyPort));
        }
    }
}
