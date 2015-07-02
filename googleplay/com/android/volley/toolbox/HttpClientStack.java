package com.android.volley.toolbox;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.google.android.wallet.instrumentmanager.R;
import java.io.IOException;
import java.net.URI;
import java.util.Map;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.client.methods.HttpOptions;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpTrace;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

public class HttpClientStack implements HttpStack {
    protected final HttpClient mClient;

    public static final class HttpPatch extends HttpEntityEnclosingRequestBase {
        public HttpPatch(String uri) {
            setURI(URI.create(uri));
        }

        public String getMethod() {
            return "PATCH";
        }
    }

    public HttpClientStack(HttpClient client) {
        this.mClient = client;
    }

    private static void addHeaders(HttpUriRequest httpRequest, Map<String, String> headers) {
        for (String key : headers.keySet()) {
            httpRequest.setHeader(key, (String) headers.get(key));
        }
    }

    public HttpResponse performRequest(Request<?> request, Map<String, String> additionalHeaders) throws IOException, AuthFailureError {
        HttpUriRequest httpRequest = createHttpRequest(request, additionalHeaders);
        addHeaders(httpRequest, additionalHeaders);
        addHeaders(httpRequest, request.getHeaders());
        onPrepareRequest(httpRequest);
        HttpParams httpParams = httpRequest.getParams();
        int timeoutMs = request.getTimeoutMs();
        HttpConnectionParams.setConnectionTimeout(httpParams, 5000);
        HttpConnectionParams.setSoTimeout(httpParams, timeoutMs);
        return this.mClient.execute(httpRequest);
    }

    static HttpUriRequest createHttpRequest(Request<?> request, Map<String, String> map) throws AuthFailureError {
        HttpPost postRequest;
        switch (request.getMethod()) {
            case -1:
                byte[] postBody = request.getPostBody();
                if (postBody == null) {
                    return new HttpGet(request.getUrl());
                }
                postRequest = new HttpPost(request.getUrl());
                postRequest.addHeader("Content-Type", request.getPostBodyContentType());
                postRequest.setEntity(new ByteArrayEntity(postBody));
                return postRequest;
            case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                return new HttpGet(request.getUrl());
            case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                postRequest = new HttpPost(request.getUrl());
                postRequest.addHeader("Content-Type", request.getBodyContentType());
                setEntityIfNonEmptyBody(postRequest, request);
                return postRequest;
            case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                HttpEntityEnclosingRequestBase putRequest = new HttpPut(request.getUrl());
                putRequest.addHeader("Content-Type", request.getBodyContentType());
                setEntityIfNonEmptyBody(putRequest, request);
                return putRequest;
            case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                return new HttpDelete(request.getUrl());
            case R.styleable.WalletImFormEditText_required /*4*/:
                return new HttpHead(request.getUrl());
            case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                return new HttpOptions(request.getUrl());
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                return new HttpTrace(request.getUrl());
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                HttpEntityEnclosingRequestBase patchRequest = new HttpPatch(request.getUrl());
                patchRequest.addHeader("Content-Type", request.getBodyContentType());
                setEntityIfNonEmptyBody(patchRequest, request);
                return patchRequest;
            default:
                throw new IllegalStateException("Unknown request method.");
        }
    }

    private static void setEntityIfNonEmptyBody(HttpEntityEnclosingRequestBase httpRequest, Request<?> request) throws AuthFailureError {
        byte[] body = request.getBody();
        if (body != null) {
            httpRequest.setEntity(new ByteArrayEntity(body));
        }
    }

    protected void onPrepareRequest(HttpUriRequest request) throws IOException {
    }
}
