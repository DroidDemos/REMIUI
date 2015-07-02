package com.xiaomi.kenai.jbosh;

import android.content.Context;
import com.xiaomi.network.UploadHostStatHelper;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.apache.http.HttpHost;
import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;

final class ApacheHTTPSender implements HTTPSender {
    private BOSHClientConfig cfg;
    private HttpClient httpClient;
    private final Lock lock;

    ApacheHTTPSender() {
        this.lock = new ReentrantLock();
        HttpClient.class.getName();
    }

    public void init(BOSHClientConfig session) {
        this.lock.lock();
        try {
            this.cfg = session;
            this.httpClient = initHttpClient(session);
        } finally {
            this.lock.unlock();
        }
    }

    public void destroy() {
        this.lock.lock();
        try {
            if (this.httpClient != null) {
                this.httpClient.getConnectionManager().shutdown();
            }
            this.cfg = null;
            this.httpClient = null;
            this.lock.unlock();
        } catch (Throwable th) {
            this.cfg = null;
            this.httpClient = null;
            this.lock.unlock();
        }
    }

    public HTTPResponse send(CMSessionParams params, AbstractBody body, Context context) {
        this.lock.lock();
        try {
            if (this.httpClient == null) {
                this.httpClient = initHttpClient(this.cfg);
            }
            HttpClient mClient = this.httpClient;
            HttpParams httpParams = mClient.getParams();
            HttpConnectionParams.setConnectionTimeout(httpParams, UploadHostStatHelper.UPLOAD_RATIO_MULTIPLE);
            if (params != null) {
                HttpConnectionParams.setSoTimeout(httpParams, (int) (((long) (((Integer) params.getWait().getValue()).intValue() + 30)) * 1000));
            }
            BOSHClientConfig mCfg = this.cfg;
            return new ApacheHTTPResponse(mClient, mCfg, params, body, context);
        } finally {
            this.lock.unlock();
        }
    }

    private synchronized HttpClient initHttpClient(BOSHClientConfig config) {
        DefaultHttpClient defaultHttpClient;
        HttpParams params = new BasicHttpParams();
        ConnManagerParams.setMaxTotalConnections(params, 100);
        HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
        HttpProtocolParams.setUseExpectContinue(params, false);
        if (!(config == null || config.getProxyHost() == null || config.getProxyPort() == 0)) {
            params.setParameter("http.route.default-proxy", new HttpHost(config.getProxyHost(), config.getProxyPort()));
        }
        SchemeRegistry schemeRegistry = new SchemeRegistry();
        schemeRegistry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
        defaultHttpClient = new DefaultHttpClient(new ThreadSafeClientConnManager(params, schemeRegistry), params);
        defaultHttpClient.setHttpRequestRetryHandler(new DefaultHttpRequestRetryHandler(0, false));
        return defaultHttpClient;
    }
}
