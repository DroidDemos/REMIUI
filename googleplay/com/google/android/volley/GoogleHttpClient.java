package com.google.android.volley;

import android.content.ContentResolver;
import android.content.Context;
import android.net.TrafficStats;
import android.os.Build;
import android.os.Process;
import android.os.SystemClock;
import android.util.EventLog;
import android.util.Log;
import com.google.android.gsf.Gservices;
import com.google.android.pseudonymous_http.PseudonymousCookieSource;
import com.google.android.pseudonymous_http.PseudonymousCookieSource.Helper;
import com.google.android.volley.guava.NetworkStatsEntity;
import com.google.android.volley.guava.UrlRules;
import com.google.android.volley.guava.UrlRules.Rule;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URI;
import java.net.URISyntaxException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.ProtocolException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.LayeredSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.scheme.SocketFactory;
import org.apache.http.impl.client.EntityEnclosingRequestWrapper;
import org.apache.http.impl.client.RequestWrapper;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HttpContext;

public class GoogleHttpClient implements HttpClient {
    private final String mAppName;
    private final AndroidHttpClient mClient;
    private final ThreadLocal<Boolean> mConnectionAllocated;
    private final CookieSourceApplier mCookieSourceApplier;
    private final ContentResolver mResolver;

    public static class BlockedRequestException extends IOException {
        BlockedRequestException(Rule rule) {
            super("Blocked by rule: " + rule.mName);
        }
    }

    private final class CookieSourceApplier {
        private final AndroidHttpClient mClient;
        private final PseudonymousCookieSource mCookieSource;

        private CookieSourceApplier(AndroidHttpClient client, PseudonymousCookieSource cookieSource) {
            this.mClient = client;
            this.mCookieSource = cookieSource;
        }

        private HttpResponse execute(HttpUriRequest request, HttpContext context) throws IOException {
            return Helper.updateFromResponseCookie(request, this.mClient.execute(Helper.setRequestCookie(request, this.mCookieSource)), this.mCookieSource);
        }

        private HttpResponse execute(HttpHost target, HttpRequest request) throws IOException {
            return Helper.updateFromResponseCookie(GoogleHttpClient.wrapRequest(request), this.mClient.execute(target, Helper.setRequestCookie(GoogleHttpClient.wrapRequest(request), this.mCookieSource)), this.mCookieSource);
        }

        private HttpResponse execute(HttpHost target, HttpRequest request, HttpContext context) throws IOException {
            return Helper.updateFromResponseCookie(GoogleHttpClient.wrapRequest(request), this.mClient.execute(target, Helper.setRequestCookie(GoogleHttpClient.wrapRequest(request), this.mCookieSource), context), this.mCookieSource);
        }

        private <T> T execute(HttpUriRequest request, ResponseHandler<T> responseHandler) throws IOException {
            return this.mClient.execute(Helper.setRequestCookie(request, this.mCookieSource), new SetCookie(responseHandler, request, this.mCookieSource));
        }

        private <T> T execute(HttpUriRequest request, ResponseHandler<T> responseHandler, HttpContext context) throws IOException, ClientProtocolException {
            return this.mClient.execute(request, new SetCookie(responseHandler, request, this.mCookieSource), context);
        }

        private <T> T execute(HttpHost target, HttpRequest request, ResponseHandler<T> responseHandler) throws IOException, ClientProtocolException {
            HttpUriRequest wrappedRequest = GoogleHttpClient.wrapRequest(request);
            return this.mClient.execute(target, Helper.setRequestCookie(wrappedRequest, this.mCookieSource), new SetCookie(responseHandler, wrappedRequest, this.mCookieSource));
        }

        private <T> T execute(HttpHost target, HttpRequest request, ResponseHandler<T> responseHandler, HttpContext context) throws IOException, ClientProtocolException {
            HttpUriRequest wrappedRequest = GoogleHttpClient.wrapRequest(request);
            return this.mClient.execute(target, Helper.setRequestCookie(wrappedRequest, this.mCookieSource), new SetCookie(responseHandler, wrappedRequest, this.mCookieSource), context);
        }
    }

    private final class SetCookie<T> implements ResponseHandler<T> {
        private final PseudonymousCookieSource mCookieSource;
        private final ResponseHandler<T> mHandler;
        private final HttpUriRequest mRequest;

        private SetCookie(ResponseHandler<T> handler, HttpUriRequest request, PseudonymousCookieSource cookieSource) {
            this.mHandler = handler;
            this.mRequest = request;
            this.mCookieSource = cookieSource;
        }

        public T handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
            return this.mHandler.handleResponse(Helper.updateFromResponseCookie(this.mRequest, response, this.mCookieSource));
        }
    }

    private class WrappedSocketFactory implements SocketFactory {
        private SocketFactory mDelegate;

        private WrappedSocketFactory(SocketFactory delegate) {
            this.mDelegate = delegate;
        }

        public final Socket createSocket() throws IOException {
            return this.mDelegate.createSocket();
        }

        public final boolean isSecure(Socket s) {
            return this.mDelegate.isSecure(s);
        }

        public final Socket connectSocket(Socket s, String h, int p, InetAddress la, int lp, HttpParams params) throws IOException {
            GoogleHttpClient.this.mConnectionAllocated.set(Boolean.TRUE);
            return this.mDelegate.connectSocket(s, h, p, la, lp, params);
        }
    }

    private class WrappedLayeredSocketFactory extends WrappedSocketFactory implements LayeredSocketFactory {
        private LayeredSocketFactory mDelegate;

        private WrappedLayeredSocketFactory(LayeredSocketFactory sf) {
            super(sf);
            this.mDelegate = sf;
        }

        public final Socket createSocket(Socket s, String host, int port, boolean autoClose) throws IOException {
            return this.mDelegate.createSocket(s, host, port, autoClose);
        }
    }

    public GoogleHttpClient(Context context, String appAndVersion, boolean gzipCapable) {
        this(context, appAndVersion, gzipCapable, null);
    }

    public GoogleHttpClient(Context context, String appAndVersion, boolean gzipCapable, PseudonymousCookieSource cookieSource) {
        this.mConnectionAllocated = new ThreadLocal();
        String userAgent = appAndVersion + " (" + Build.DEVICE + " " + Build.ID + ")";
        if (gzipCapable) {
            userAgent = userAgent + "; gzip";
        }
        this.mClient = AndroidHttpClient.newInstance(userAgent, context);
        this.mCookieSourceApplier = new CookieSourceApplier(this.mClient, cookieSource);
        this.mResolver = context.getContentResolver();
        this.mAppName = appAndVersion;
        SchemeRegistry registry = getConnectionManager().getSchemeRegistry();
        for (String name : registry.getSchemeNames()) {
            Scheme scheme = registry.unregister(name);
            SocketFactory sf = scheme.getSocketFactory();
            if (sf instanceof LayeredSocketFactory) {
                sf = new WrappedLayeredSocketFactory((LayeredSocketFactory) sf);
            } else {
                sf = new WrappedSocketFactory(sf);
            }
            registry.register(new Scheme(name, sf, scheme.getDefaultPort()));
        }
    }

    public HttpResponse executeWithoutRewriting(HttpUriRequest request, HttpContext context) throws IOException {
        long start = SystemClock.elapsedRealtime();
        long elapsed;
        int reused;
        try {
            HttpResponse response;
            this.mConnectionAllocated.set(null);
            if (Gservices.getBoolean(this.mResolver, "http_stats", false)) {
                int uid = Process.myUid();
                long startTx = TrafficStats.getUidTxBytes(uid);
                long startRx = TrafficStats.getUidRxBytes(uid);
                response = this.mCookieSourceApplier.execute(request, context);
                HttpEntity origEntity = response == null ? null : response.getEntity();
                if (origEntity != null) {
                    long now = SystemClock.elapsedRealtime();
                    response.setEntity(new NetworkStatsEntity(origEntity, this.mAppName, uid, startTx, startRx, now - start, now));
                }
            } else {
                response = this.mCookieSourceApplier.execute(request, context);
            }
            try {
                elapsed = SystemClock.elapsedRealtime() - start;
                reused = (this.mConnectionAllocated.get() != null || response.getStatusLine().getStatusCode() < 0) ? 0 : 1;
                r20 = new Object[4];
                r20[2] = this.mAppName;
                r20[3] = Integer.valueOf(reused);
                EventLog.writeEvent(203002, r20);
            } catch (Exception e) {
                Log.e("GoogleHttpClient", "Error recording stats", e);
            }
            return response;
        } catch (Throwable th) {
            try {
                elapsed = SystemClock.elapsedRealtime() - start;
                reused = (this.mConnectionAllocated.get() != null || -1 < null) ? 0 : 1;
                r21 = new Object[4];
                r21[2] = this.mAppName;
                r21[3] = Integer.valueOf(reused);
                EventLog.writeEvent(203002, r21);
            } catch (Exception e2) {
                Log.e("GoogleHttpClient", "Error recording stats", e2);
            }
        }
    }

    public String rewriteURI(String original) {
        return UrlRules.getRules(this.mResolver).matchRule(original).apply(original);
    }

    public HttpResponse execute(HttpUriRequest request, HttpContext context) throws IOException {
        String original = request.getURI().toString();
        Rule rule = UrlRules.getRules(this.mResolver).matchRule(original);
        String rewritten = rule.apply(original);
        if (rewritten == null) {
            Log.w("GoogleHttpClient", "Blocked by " + rule.mName + ": " + original);
            throw new BlockedRequestException(rule);
        } else if (rewritten == original) {
            return executeWithoutRewriting(request, context);
        } else {
            try {
                URI uri = new URI(rewritten);
                RequestWrapper wrapper = wrapRequest(request);
                wrapper.setURI(uri);
                return executeWithoutRewriting(wrapper, context);
            } catch (URISyntaxException e) {
                throw new RuntimeException("Bad URL from rule: " + rule.mName, e);
            }
        }
    }

    private static RequestWrapper wrapRequest(HttpUriRequest request) throws IOException {
        try {
            RequestWrapper wrapped;
            if (request instanceof HttpEntityEnclosingRequest) {
                wrapped = new EntityEnclosingRequestWrapper((HttpEntityEnclosingRequest) request);
            } else {
                wrapped = new RequestWrapper(request);
            }
            wrapped.resetHeaders();
            return wrapped;
        } catch (ProtocolException e) {
            throw new ClientProtocolException(e);
        }
    }

    public HttpParams getParams() {
        return this.mClient.getParams();
    }

    public ClientConnectionManager getConnectionManager() {
        return this.mClient.getConnectionManager();
    }

    public HttpResponse execute(HttpUriRequest request) throws IOException {
        return execute(request, (HttpContext) null);
    }

    public HttpResponse execute(HttpHost target, HttpRequest request) throws IOException {
        return this.mCookieSourceApplier.execute(target, request);
    }

    public HttpResponse execute(HttpHost target, HttpRequest request, HttpContext context) throws IOException {
        return this.mCookieSourceApplier.execute(target, request, context);
    }

    public <T> T execute(HttpUriRequest request, ResponseHandler<? extends T> responseHandler) throws IOException, ClientProtocolException {
        return this.mCookieSourceApplier.execute(request, (ResponseHandler) responseHandler);
    }

    public <T> T execute(HttpUriRequest request, ResponseHandler<? extends T> responseHandler, HttpContext context) throws IOException, ClientProtocolException {
        return this.mCookieSourceApplier.execute(request, (ResponseHandler) responseHandler, context);
    }

    public <T> T execute(HttpHost target, HttpRequest request, ResponseHandler<? extends T> responseHandler) throws IOException, ClientProtocolException {
        return this.mCookieSourceApplier.execute(target, request, (ResponseHandler) responseHandler);
    }

    public <T> T execute(HttpHost target, HttpRequest request, ResponseHandler<? extends T> responseHandler, HttpContext context) throws IOException, ClientProtocolException {
        return this.mCookieSourceApplier.execute(target, request, responseHandler, context);
    }

    public void enableCurlLogging(String name, int level) {
        this.mClient.enableCurlLogging(name, level);
    }

    private static RequestWrapper wrapRequest(HttpRequest request) throws IOException {
        try {
            RequestWrapper wrapped;
            if (request instanceof HttpEntityEnclosingRequest) {
                wrapped = new EntityEnclosingRequestWrapper((HttpEntityEnclosingRequest) request);
            } else {
                wrapped = new RequestWrapper(request);
            }
            wrapped.resetHeaders();
            return wrapped;
        } catch (ProtocolException e) {
            throw new ClientProtocolException(e);
        }
    }
}
