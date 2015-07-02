package com.xiaomi.kenai.jbosh;

import android.content.Context;
import android.net.Uri;
import android.net.Uri.Builder;
import com.xiaomi.channel.commonutils.logger.MyLog;
import com.xiaomi.channel.commonutils.network.Network;
import com.xiaomi.channel.commonutils.string.CloudCoder;
import java.net.SocketException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

final class ApacheHTTPResponse implements HTTPResponse {
    private static final String CHARSET = "UTF-8";
    private static final String CONTENT_TYPE = "application/octet-stream";
    private static final String HEADER_CONTENT_SIGNATURE = "X-Content-Sig";
    private static final int MAX_RETRY = 3;
    private static final String SIGNATURE_SALT = "xm-http-bind";
    private AbstractBody body;
    private final HttpClient client;
    private final HttpContext context;
    private final Lock lock;
    private HttpPost post;
    private long rid;
    private boolean sent;
    private int statusCode;
    private BOSHException toThrow;

    ApacheHTTPResponse(HttpClient client, BOSHClientConfig cfg, CMSessionParams params, AbstractBody request, Context context) {
        this.lock = new ReentrantLock();
        this.client = client;
        this.context = new BasicHttpContext();
        this.sent = false;
        try {
            String xml = request.toXML();
            this.rid = Long.parseLong(request.getAttribute(Attributes.RID));
            String randomQuery = String.valueOf((int) (Math.random() * 1000.0d));
            String signature = URLEncoder.encode(CloudCoder.hash4SHA1("xm-http-bind&" + xml));
            Builder builder;
            if (Network.isCmwap(context)) {
                String host = cfg.getURI().getHost();
                builder = Uri.parse(Network.getCMWapUrl(cfg.getURI().toURL())).buildUpon();
                builder.appendQueryParameter("t", randomQuery);
                this.post = new HttpPost(builder.build().toString());
                this.post.addHeader(com.xiaomi.network.Network.CMWAP_HEADER_HOST_KEY, host);
            } else {
                builder = Uri.parse(cfg.getURI().toString()).buildUpon();
                builder.appendQueryParameter("t", randomQuery);
                this.post = new HttpPost(builder.build().toString());
            }
            this.post.addHeader(HEADER_CONTENT_SIGNATURE, signature);
            this.post.addHeader("Connection", "Keep-Alive");
            ByteArrayEntity entity = new ByteArrayEntity(GZIPCodec.encode(xml.getBytes(CHARSET)));
            entity.setContentType(CONTENT_TYPE);
            this.post.setEntity(entity);
        } catch (Exception e) {
            this.toThrow = new BOSHException("Could not generate request", e);
        }
    }

    public void abort() {
        if (this.post != null) {
            this.post.abort();
            this.toThrow = new BOSHException("HTTP request aborted");
        }
    }

    public AbstractBody getBody() throws InterruptedException, BOSHException {
        if (this.toThrow != null) {
            throw this.toThrow;
        }
        this.lock.lock();
        try {
            if (!this.sent) {
                awaitResponse();
            }
            this.lock.unlock();
            return this.body;
        } catch (Throwable th) {
            this.lock.unlock();
        }
    }

    public int getHTTPStatus() throws InterruptedException, BOSHException {
        if (this.toThrow != null) {
            throw this.toThrow;
        }
        this.lock.lock();
        try {
            if (!this.sent) {
                awaitResponse();
            }
            this.lock.unlock();
            return this.statusCode;
        } catch (Throwable th) {
            this.lock.unlock();
        }
    }

    private synchronized void awaitResponse() throws BOSHException {
        int retry = 0;
        MyLog.v("SMACK-BOSH: requesting, rid=" + this.rid + " url=" + this.post.getURI().toString());
        BOSHException t = null;
        while (retry < MAX_RETRY) {
            try {
                HttpResponse httpResp = this.client.execute(this.post, this.context);
                byte[] data = EntityUtils.toByteArray(httpResp.getEntity());
                int responseCode = httpResp.getStatusLine().getStatusCode();
                MyLog.v("SMACK-BOSH: get server response, code=" + responseCode);
                if (responseCode != 200 || data == null || !httpResp.containsHeader(HEADER_CONTENT_SIGNATURE)) {
                    abort();
                    break;
                }
                data = GZIPCodec.decode(data);
                String serverSig = URLDecoder.decode(httpResp.getLastHeader(HEADER_CONTENT_SIGNATURE).getValue());
                AbstractBody serverBody = StaticBody.fromString(new String(data, CHARSET));
                String signature = CloudCoder.hash4SHA1("xm-http-bind&" + serverBody.toXML());
                if (!signature.equals(serverSig)) {
                    MyLog.e("SMACK-BOSH: the server signature doesn't match, drop the response. received " + serverSig + ", expected " + signature);
                    this.toThrow = new BOSHException("signature mismatch");
                    abort();
                    break;
                }
                this.body = serverBody;
                MyLog.v("SMACK-BOSH: server response, rid=" + this.rid);
                this.statusCode = responseCode;
                this.sent = true;
                t = null;
                break;
            } catch (Exception ex) {
                if (ex instanceof SocketException) {
                    t = new BOSHException("Could not obtain response", ex);
                    MyLog.e("SMACK-BOSH: request error, retry=" + retry, ex);
                    retry++;
                } else {
                    abort();
                    this.toThrow = new BOSHException("Could not obtain response", ex);
                    throw this.toThrow;
                }
            }
        }
        if (retry == MAX_RETRY) {
            abort();
            this.toThrow = t;
            throw this.toThrow;
        }
    }

    public long getRid() {
        return this.rid;
    }
}
