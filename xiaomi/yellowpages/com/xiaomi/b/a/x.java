package com.xiaomi.b.a;

import android.content.Context;
import android.net.Uri;
import android.net.Uri.Builder;
import com.alipay.mobilesecuritysdk.constant.ConfigConstant;
import com.xiaomi.f.a.a.b;
import com.xiaomi.f.a.d.a;
import java.net.SocketException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

final class x implements b {
    private long e;
    private boolean f;
    private int i;
    private final Lock oG;
    private final HttpContext oH;
    private final HttpClient oI;
    private HttpPost oJ;
    private aa oK;
    private z oL;

    x(HttpClient httpClient, N n, e eVar, z zVar, Context context) {
        this.oG = new ReentrantLock();
        this.oI = httpClient;
        this.oH = new BasicHttpContext();
        this.f = false;
        try {
            String b = zVar.b();
            this.e = Long.parseLong(zVar.a(K.pL));
            String valueOf = String.valueOf((int) (1000.0d * Math.random()));
            String encode = URLEncoder.encode(b.a("xm-http-bind&" + b));
            if (a.b(context)) {
                String host = n.cL().getHost();
                Builder buildUpon = Uri.parse(a.a(n.cL().toURL())).buildUpon();
                buildUpon.appendQueryParameter("t", valueOf);
                this.oJ = new HttpPost(buildUpon.build().toString());
                this.oJ.addHeader("X-Online-Host", host);
            } else {
                Builder buildUpon2 = Uri.parse(n.cL().toString()).buildUpon();
                buildUpon2.appendQueryParameter("t", valueOf);
                this.oJ = new HttpPost(buildUpon2.build().toString());
            }
            this.oJ.addHeader("X-Content-Sig", encode);
            this.oJ.addHeader("Connection", "Keep-Alive");
            HttpEntity byteArrayEntity = new ByteArrayEntity(g.a(b.getBytes("UTF-8")));
            byteArrayEntity.setContentType("application/octet-stream");
            this.oJ.setEntity(byteArrayEntity);
        } catch (Throwable e) {
            this.oK = new aa("Could not generate request", e);
        }
    }

    private void e() {
        synchronized (this) {
            com.xiaomi.f.a.c.b.b("SMACK-BOSH: requesting, rid=" + this.e + " url=" + this.oJ.getURI().toString());
            int i = 0;
            aa aaVar = null;
            while (i < 3) {
                try {
                    HttpResponse execute = this.oI.execute(this.oJ, this.oH);
                    byte[] toByteArray = EntityUtils.toByteArray(execute.getEntity());
                    int statusCode = execute.getStatusLine().getStatusCode();
                    com.xiaomi.f.a.c.b.b("SMACK-BOSH: get server response, code=" + statusCode);
                    if (statusCode != ConfigConstant.RESPONSE_CODE || toByteArray == null || !execute.containsHeader("X-Content-Sig")) {
                        a();
                        break;
                    }
                    toByteArray = g.g(toByteArray);
                    String decode = URLDecoder.decode(execute.getLastHeader("X-Content-Sig").getValue());
                    z Z = m.Z(new String(toByteArray, "UTF-8"));
                    String a = b.a("xm-http-bind&" + Z.b());
                    if (!a.equals(decode)) {
                        com.xiaomi.f.a.c.b.c("SMACK-BOSH: the server signature doesn't match, drop the response. received " + decode + ", expected " + a);
                        this.oK = new aa("signature mismatch");
                        a();
                        break;
                    }
                    this.oL = Z;
                    com.xiaomi.f.a.c.b.b("SMACK-BOSH: server response, rid=" + this.e);
                    this.i = statusCode;
                    this.f = true;
                    aaVar = null;
                    break;
                } catch (Throwable e) {
                    if (e instanceof SocketException) {
                        aa aaVar2 = new aa("Could not obtain response", e);
                        com.xiaomi.f.a.c.b.b("SMACK-BOSH: request error, retry=" + i, e);
                        i++;
                        aaVar = aaVar2;
                    } else {
                        a();
                        this.oK = new aa("Could not obtain response", e);
                        throw this.oK;
                    }
                }
            }
            if (i == 3) {
                a();
                this.oK = aaVar;
                throw this.oK;
            }
        }
    }

    public void a() {
        if (this.oJ != null) {
            this.oJ.abort();
            this.oK = new aa("HTTP request aborted");
        }
    }

    public z bV() {
        if (this.oK != null) {
            throw this.oK;
        }
        this.oG.lock();
        try {
            if (!this.f) {
                e();
            }
            this.oG.unlock();
            return this.oL;
        } catch (Throwable th) {
            this.oG.unlock();
        }
    }

    public long bW() {
        return this.e;
    }

    public int c() {
        if (this.oK != null) {
            throw this.oK;
        }
        this.oG.lock();
        try {
            if (!this.f) {
                e();
            }
            this.oG.unlock();
            return this.i;
        } catch (Throwable th) {
            this.oG.unlock();
        }
    }
}
