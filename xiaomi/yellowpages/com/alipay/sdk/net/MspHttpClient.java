package com.alipay.sdk.net;

import android.os.Build.VERSION;
import com.alipay.sdk.cons.MiniDefine;
import com.alipay.sdk.util.LogUtils;
import java.util.concurrent.TimeUnit;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.params.HttpClientParams;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.params.ConnPerRouteBean;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.scheme.SocketFactory;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HttpContext;

public final class MspHttpClient {
    public static final String a = "msp";
    private static MspHttpClient b;
    private final DefaultHttpClient c;

    private MspHttpClient(HttpParams httpParams) {
        this.c = new DefaultHttpClient(httpParams);
    }

    private MspHttpClient(ClientConnectionManager clientConnectionManager, HttpParams httpParams) {
        this.c = new DefaultHttpClient(clientConnectionManager, httpParams);
    }

    public static MspHttpClient a() {
        if (b == null) {
            HttpParams basicHttpParams = new BasicHttpParams();
            HttpProtocolParams.setVersion(basicHttpParams, HttpVersion.HTTP_1_1);
            HttpConnectionParams.setStaleCheckingEnabled(basicHttpParams, true);
            basicHttpParams.setBooleanParameter("http.protocol.expect-continue", false);
            ConnManagerParams.setMaxTotalConnections(basicHttpParams, 50);
            ConnManagerParams.setMaxConnectionsPerRoute(basicHttpParams, new ConnPerRouteBean(30));
            ConnManagerParams.setTimeout(basicHttpParams, 1000);
            HttpConnectionParams.setConnectionTimeout(basicHttpParams, 20000);
            HttpConnectionParams.setSoTimeout(basicHttpParams, 20000);
            HttpConnectionParams.setSocketBufferSize(basicHttpParams, 16384);
            HttpProtocolParams.setUseExpectContinue(basicHttpParams, false);
            HttpClientParams.setRedirecting(basicHttpParams, true);
            HttpClientParams.setAuthenticating(basicHttpParams, false);
            HttpProtocolParams.setUserAgent(basicHttpParams, a);
            try {
                SocketFactory socketFactory = SSLSocketFactory.getSocketFactory();
                socketFactory.setHostnameVerifier(SSLSocketFactory.STRICT_HOSTNAME_VERIFIER);
                Scheme scheme = new Scheme(MiniDefine.aQ, socketFactory, 443);
                Scheme scheme2 = new Scheme("http", PlainSocketFactory.getSocketFactory(), 80);
                SchemeRegistry schemeRegistry = new SchemeRegistry();
                schemeRegistry.register(scheme);
                schemeRegistry.register(scheme2);
                b = new MspHttpClient(new ThreadSafeClientConnManager(basicHttpParams, schemeRegistry), basicHttpParams);
            } catch (Object e) {
                LogUtils.a(e);
                LogUtils.e("\u4e0d\u5e94\u8be5\u6267\u884c\u5230\u7684\u5730\u65b9");
                b = new MspHttpClient(basicHttpParams);
            }
        }
        return b;
    }

    public void b() {
        ClientConnectionManager e = e();
        if (e != null) {
            e.closeExpiredConnections();
            if (VERSION.SDK_INT >= 9) {
                e.closeIdleConnections(30, TimeUnit.MINUTES);
            }
        }
    }

    public void c() {
        ClientConnectionManager e = e();
        if (e != null) {
            e.shutdown();
            b = null;
        }
    }

    public HttpParams d() {
        return this.c.getParams();
    }

    public ClientConnectionManager e() {
        return this.c.getConnectionManager();
    }

    public HttpResponse a(HttpUriRequest httpUriRequest) {
        try {
            return this.c.execute(httpUriRequest);
        } catch (Throwable e) {
            throw new Exception(e);
        }
    }

    public HttpResponse a(HttpUriRequest httpUriRequest, HttpContext httpContext) {
        try {
            return this.c.execute(httpUriRequest, httpContext);
        } catch (Throwable e) {
            throw new Exception(e);
        }
    }

    public HttpResponse a(HttpHost httpHost, HttpRequest httpRequest) {
        try {
            return this.c.execute(httpHost, httpRequest);
        } catch (Throwable e) {
            throw new Exception(e);
        }
    }

    public HttpResponse a(HttpHost httpHost, HttpRequest httpRequest, HttpContext httpContext) {
        try {
            return this.c.execute(httpHost, httpRequest, httpContext);
        } catch (Throwable e) {
            throw new Exception(e);
        }
    }

    public Object a(HttpUriRequest httpUriRequest, ResponseHandler responseHandler) {
        try {
            return this.c.execute(httpUriRequest, responseHandler);
        } catch (Throwable e) {
            throw new Exception(e);
        }
    }

    public Object a(HttpUriRequest httpUriRequest, ResponseHandler responseHandler, HttpContext httpContext) {
        try {
            return this.c.execute(httpUriRequest, responseHandler, httpContext);
        } catch (Throwable e) {
            throw new Exception(e);
        }
    }

    public Object a(HttpHost httpHost, HttpRequest httpRequest, ResponseHandler responseHandler) {
        try {
            return this.c.execute(httpHost, httpRequest, responseHandler);
        } catch (Throwable e) {
            throw new Exception(e);
        }
    }

    public Object a(HttpHost httpHost, HttpRequest httpRequest, ResponseHandler responseHandler, HttpContext httpContext) {
        try {
            return this.c.execute(httpHost, httpRequest, responseHandler, httpContext);
        } catch (Throwable e) {
            throw new Exception(e);
        }
    }
}
