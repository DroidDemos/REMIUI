package com.xiaomi.b.a;

import android.content.Context;
import com.alipay.sdk.cons.MiniDefine;
import com.miui.yellowpage.ui.bw;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.apache.http.HttpHost;
import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.scheme.SocketFactory;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;

final class E implements a {
    private final Lock oG;
    private HttpClient oI;
    private N oQ;

    E() {
        this.oG = new ReentrantLock();
        HttpClient.class.getName();
    }

    private HttpClient b(N n) {
        HttpClient defaultHttpClient;
        synchronized (this) {
            HttpParams basicHttpParams = new BasicHttpParams();
            ConnManagerParams.setMaxTotalConnections(basicHttpParams, 100);
            HttpProtocolParams.setVersion(basicHttpParams, HttpVersion.HTTP_1_1);
            HttpProtocolParams.setUseExpectContinue(basicHttpParams, false);
            if (!(n == null || n.f() == null || n.g() == 0)) {
                basicHttpParams.setParameter("http.route.default-proxy", new HttpHost(n.f(), n.g()));
            }
            SchemeRegistry schemeRegistry = new SchemeRegistry();
            schemeRegistry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
            SocketFactory socketFactory = SSLSocketFactory.getSocketFactory();
            socketFactory.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
            schemeRegistry.register(new Scheme(MiniDefine.aQ, socketFactory, 443));
            defaultHttpClient = new DefaultHttpClient(new ThreadSafeClientConnManager(basicHttpParams, schemeRegistry), basicHttpParams);
            defaultHttpClient.setHttpRequestRetryHandler(new DefaultHttpRequestRetryHandler(0, false));
        }
        return defaultHttpClient;
    }

    public b a(e eVar, z zVar, Context context) {
        this.oG.lock();
        HttpClient httpClient;
        try {
            if (this.oI == null) {
                this.oI = b(this.oQ);
            }
            httpClient = this.oI;
            HttpParams params = httpClient.getParams();
            HttpConnectionParams.setConnectionTimeout(params, bw.FILE_CHOOSER_RESULT_CODE);
            if (eVar != null) {
                HttpConnectionParams.setSoTimeout(params, (int) (1000 * ((long) (((Integer) eVar.cb().cw()).intValue() + 30))));
            }
            N n = this.oQ;
            return new x(httpClient, n, eVar, zVar, context);
        } finally {
            httpClient = this.oG;
            httpClient.unlock();
        }
    }

    public void a() {
        this.oG.lock();
        try {
            if (this.oI != null) {
                this.oI.getConnectionManager().shutdown();
            }
            this.oQ = null;
            this.oI = null;
            this.oG.unlock();
        } catch (Throwable th) {
            this.oQ = null;
            this.oI = null;
            this.oG.unlock();
        }
    }

    public void a(N n) {
        this.oG.lock();
        try {
            this.oQ = n;
            this.oI = b(n);
        } finally {
            this.oG.unlock();
        }
    }
}
