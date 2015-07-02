package com.alipay.sdk.net;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Proxy;
import android.os.Build.VERSION;
import android.text.TextUtils;
import com.alipay.sdk.cons.MiniDefine;
import com.alipay.sdk.data.InteractionData;
import com.alipay.sdk.exception.NetErrorException;
import com.alipay.sdk.sys.GlobalContext;
import com.alipay.sdk.util.DeviceInfo;
import com.alipay.sdk.util.LogUtils;
import com.alipay.sdk.util.NetConnectionType;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.zip.GZIPOutputStream;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.message.BasicHeader;
import org.apache.http.params.HttpParams;

public class MspClient {
    private Context a;
    private String b;

    public MspClient(Context context) {
        this(context, null);
    }

    public MspClient(Context context, String str) {
        this.a = context;
        this.b = str;
    }

    public void a(String str) {
        this.b = str;
    }

    public String a() {
        return this.b;
    }

    public URL b() {
        try {
            return new URL(this.b);
        } catch (Object e) {
            LogUtils.a(e);
            return null;
        }
    }

    private static ByteArrayEntity a(InteractionData interactionData, String str, boolean z) {
        String str2 = null;
        if (interactionData != null) {
            str2 = interactionData.c();
            if (!TextUtils.isEmpty(interactionData.d())) {
                str = interactionData.d() + "=" + str;
            }
        }
        if (TextUtils.isEmpty(str2)) {
            str2 = InteractionData.a;
        }
        byte[] bytes = str.getBytes("utf-8");
        if (z) {
            OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
            gZIPOutputStream.write(bytes);
            gZIPOutputStream.close();
            byteArrayOutputStream.toByteArray();
            ByteArrayEntity byteArrayEntity = new ByteArrayEntity(byteArrayOutputStream.toByteArray());
            byteArrayEntity.setContentType(str2);
            byteArrayEntity.setContentEncoding("gzip");
            return byteArrayEntity;
        }
        byteArrayEntity = new ByteArrayEntity(bytes);
        byteArrayEntity.setContentType(str2);
        return byteArrayEntity;
    }

    public HttpResponse b(String str) {
        return a(str, null);
    }

    public HttpResponse a(String str, InteractionData interactionData) {
        if (DeviceInfo.b(this.a) == NetConnectionType.NONE) {
            throw new NetErrorException();
        }
        MspHttpClient a = MspHttpClient.a();
        try {
            HttpUriRequest httpGet;
            HttpParams d = a.d();
            HttpHost c = c();
            if (c != null) {
                d.setParameter("http.route.default-proxy", c);
            }
            LogUtils.e("requestUrl : " + this.b);
            if (TextUtils.isEmpty(str)) {
                httpGet = new HttpGet(this.b);
            } else {
                httpGet = new HttpPost(this.b);
                ((HttpPost) httpGet).setEntity(a(interactionData, str, false));
                httpGet.addHeader("Accept-Charset", "UTF-8");
                httpGet.addHeader("Accept-Encoding", "gzip");
                httpGet.addHeader("Connection", "Keep-Alive");
                httpGet.addHeader("Keep-Alive", "timeout=180, max=100");
            }
            if (interactionData != null) {
                ArrayList b = interactionData.b();
                if (b != null) {
                    Iterator it = b.iterator();
                    while (it.hasNext()) {
                        httpGet.addHeader((BasicHeader) it.next());
                    }
                }
            }
            if (GlobalContext.a().d()) {
                httpGet.addHeader("OS", "Android");
            }
            HttpResponse a2 = a.a(httpGet);
            Header[] headers = a2.getHeaders("X-Hostname");
            if (!(headers == null || headers.length <= 0 || headers[0] == null)) {
                LogUtils.b(a2.getHeaders("X-Hostname")[0].toString());
            }
            headers = a2.getHeaders("X-ExecuteTime");
            if (!(headers == null || headers.length <= 0 || headers[0] == null)) {
                LogUtils.b(a2.getHeaders("X-ExecuteTime")[0].toString());
            }
            return a2;
        } catch (NetErrorException e) {
            throw e;
        } catch (Object e2) {
            if (a != null) {
                a.c();
            }
            LogUtils.a(e2);
            throw new NetErrorException();
        } catch (Object e22) {
            LogUtils.a(e22);
            throw new NetErrorException();
        } catch (Object e222) {
            LogUtils.a(e222);
            throw new NetErrorException();
        } catch (Object e2222) {
            LogUtils.a(e2222);
            throw new NetErrorException();
        }
    }

    public HttpHost c() {
        if (VERSION.SDK_INT >= 11) {
            return e();
        }
        return d();
    }

    public HttpHost d() {
        NetworkInfo f = f();
        if (f == null || !f.isAvailable() || f.getType() != 0) {
            return null;
        }
        String defaultHost = Proxy.getDefaultHost();
        int defaultPort = Proxy.getDefaultPort();
        if (defaultHost != null) {
            return new HttpHost(defaultHost, defaultPort);
        }
        return null;
    }

    private HttpHost e() {
        URL b = b();
        if (b != null) {
            Object property;
            String property2;
            if (MiniDefine.aQ.equalsIgnoreCase(b.getProtocol())) {
                property = System.getProperty("https.proxyHost");
                property2 = System.getProperty("https.proxyPort");
            } else {
                property = System.getProperty("https.proxyHost");
                property2 = System.getProperty("https.proxyPort");
            }
            if (!TextUtils.isEmpty(property)) {
                return new HttpHost(property, Integer.parseInt(property2));
            }
        }
        return null;
    }

    private NetworkInfo f() {
        try {
            return ((ConnectivityManager) this.a.getSystemService("connectivity")).getActiveNetworkInfo();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
