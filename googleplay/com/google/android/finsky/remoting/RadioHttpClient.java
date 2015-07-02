package com.google.android.finsky.remoting;

import android.net.Proxy;
import android.text.TextUtils;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.toolbox.HttpStack;
import com.google.android.finsky.config.G;
import com.google.android.finsky.utils.FinskyLog;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;
import org.apache.http.HttpResponse;
import org.apache.http.entity.ByteArrayEntity;

public class RadioHttpClient<E extends HttpStack & SupportsProxy> implements HttpStack {
    private final E mHttpStack;
    private final RadioConnectionFactory mRadioConnFactory;
    private RadioConnection mRadioConnection;

    public RadioHttpClient(E httpStack, RadioConnectionFactory radioConnFactory) {
        this.mHttpStack = httpStack;
        this.mRadioConnFactory = radioConnFactory;
    }

    public HttpResponse performRequest(Request<?> request, Map<String, String> additionalHeaders) throws IOException, AuthFailureError {
        this.mRadioConnection = this.mRadioConnFactory.createNewConnection();
        try {
            this.mRadioConnection.start();
            setProxyForRequest(request);
            HttpResponse response = this.mHttpStack.performRequest(request, additionalHeaders);
            fetchEntity(response);
            throwExceptionIfError(response);
            try {
                this.mRadioConnection.stop();
            } catch (RadioConnectionException e) {
                FinskyLog.w("Unable to stop radio: %s", e.getMessage());
            }
            return response;
        } catch (RadioConnectionException e2) {
            FinskyLog.e("Unable to start radio: %s", e2.getMessage());
            throw new IOException("Unable to start radio: " + e2.getMessage());
        } catch (Throwable th) {
            try {
                this.mRadioConnection.stop();
            } catch (RadioConnectionException e22) {
                FinskyLog.w("Unable to stop radio: %s", e22.getMessage());
            }
        }
    }

    private void fetchEntity(HttpResponse response) throws IOException {
        ByteArrayOutputStream baros = new ByteArrayOutputStream();
        response.getEntity().writeTo(baros);
        baros.close();
        response.setEntity(new ByteArrayEntity(baros.toByteArray()));
    }

    private void setProxyForRequest(Request<?> request) throws IOException {
        String host = (String) G.vendingDcbProxyHost.get();
        if (host == null) {
            host = Proxy.getDefaultHost();
        }
        int port = ((Integer) G.vendingDcbProxyPort.get()).intValue();
        if (port == -1) {
            port = Proxy.getDefaultPort();
        }
        if (TextUtils.isEmpty(host) || port <= 0) {
            ((SupportsProxy) this.mHttpStack).clearProxy();
            ensureRouteToHost(request.getUrl());
            return;
        }
        ensureRouteToHost(host);
        ((SupportsProxy) this.mHttpStack).setProxy(host, port);
    }

    private void ensureRouteToHost(String url) throws IOException {
        try {
            this.mRadioConnection.ensureRouteToHost(url);
        } catch (RadioConnectionException e) {
            throw new IOException(e.getMessage());
        }
    }

    private void throwExceptionIfError(HttpResponse response) throws IOException {
        int responseCode = response.getStatusLine().getStatusCode();
        if (responseCode != 200) {
            throw new IOException("Unexpected HTTP status code from carrier: " + responseCode + " " + response.getStatusLine().getReasonPhrase());
        }
    }
}
