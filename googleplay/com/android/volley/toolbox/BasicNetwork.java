package com.android.volley.toolbox;

import com.android.volley.Cache.Entry;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RetryPolicy;
import com.android.volley.ServerError;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.StatusLine;
import org.apache.http.impl.cookie.DateUtils;

public class BasicNetwork implements Network {
    protected static final boolean DEBUG;
    private static int DEFAULT_POOL_SIZE;
    private static int SLOW_REQUEST_THRESHOLD_MS;
    protected final HttpStack mHttpStack;
    protected final ByteArrayPool mPool;

    static {
        DEBUG = VolleyLog.DEBUG;
        SLOW_REQUEST_THRESHOLD_MS = 3000;
        DEFAULT_POOL_SIZE = 4096;
    }

    public BasicNetwork(HttpStack httpStack) {
        this(httpStack, new ByteArrayPool(DEFAULT_POOL_SIZE));
    }

    public BasicNetwork(HttpStack httpStack, ByteArrayPool pool) {
        this.mHttpStack = httpStack;
        this.mPool = pool;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.android.volley.NetworkResponse performRequest(com.android.volley.Request<?> r27) throws com.android.volley.VolleyError {
        /*
        r26 = this;
        r24 = android.os.SystemClock.elapsedRealtime();
    L_0x0004:
        r22 = 0;
        r23 = 0;
        r6 = java.util.Collections.emptyMap();
        r21 = new java.util.HashMap;	 Catch:{ SocketTimeoutException -> 0x015c, ConnectTimeoutException -> 0x00c5, MalformedURLException -> 0x00d6, IOException -> 0x00f6 }
        r21.<init>();	 Catch:{ SocketTimeoutException -> 0x015c, ConnectTimeoutException -> 0x00c5, MalformedURLException -> 0x00d6, IOException -> 0x00f6 }
        r3 = r27.getCacheEntry();	 Catch:{ SocketTimeoutException -> 0x015c, ConnectTimeoutException -> 0x00c5, MalformedURLException -> 0x00d6, IOException -> 0x00f6 }
        r0 = r26;
        r1 = r21;
        r0.addCacheHeaders(r1, r3);	 Catch:{ SocketTimeoutException -> 0x015c, ConnectTimeoutException -> 0x00c5, MalformedURLException -> 0x00d6, IOException -> 0x00f6 }
        r0 = r26;
        r3 = r0.mHttpStack;	 Catch:{ SocketTimeoutException -> 0x015c, ConnectTimeoutException -> 0x00c5, MalformedURLException -> 0x00d6, IOException -> 0x00f6 }
        r0 = r27;
        r1 = r21;
        r22 = r3.performRequest(r0, r1);	 Catch:{ SocketTimeoutException -> 0x015c, ConnectTimeoutException -> 0x00c5, MalformedURLException -> 0x00d6, IOException -> 0x00f6 }
        r12 = r22.getStatusLine();	 Catch:{ SocketTimeoutException -> 0x015c, ConnectTimeoutException -> 0x00c5, MalformedURLException -> 0x00d6, IOException -> 0x00f6 }
        r14 = r12.getStatusCode();	 Catch:{ SocketTimeoutException -> 0x015c, ConnectTimeoutException -> 0x00c5, MalformedURLException -> 0x00d6, IOException -> 0x00f6 }
        r3 = r22.getAllHeaders();	 Catch:{ SocketTimeoutException -> 0x015c, ConnectTimeoutException -> 0x00c5, MalformedURLException -> 0x00d6, IOException -> 0x00f6 }
        r6 = convertHeaders(r3);	 Catch:{ SocketTimeoutException -> 0x015c, ConnectTimeoutException -> 0x00c5, MalformedURLException -> 0x00d6, IOException -> 0x00f6 }
        r3 = 304; // 0x130 float:4.26E-43 double:1.5E-321;
        if (r14 != r3) goto L_0x0075;
    L_0x003c:
        r20 = r27.getCacheEntry();	 Catch:{ SocketTimeoutException -> 0x015c, ConnectTimeoutException -> 0x00c5, MalformedURLException -> 0x00d6, IOException -> 0x00f6 }
        if (r20 != 0) goto L_0x0054;
    L_0x0042:
        r3 = new com.android.volley.NetworkResponse;	 Catch:{ SocketTimeoutException -> 0x015c, ConnectTimeoutException -> 0x00c5, MalformedURLException -> 0x00d6, IOException -> 0x00f6 }
        r4 = 304; // 0x130 float:4.26E-43 double:1.5E-321;
        r5 = 0;
        r7 = 1;
        r16 = android.os.SystemClock.elapsedRealtime();	 Catch:{ SocketTimeoutException -> 0x015c, ConnectTimeoutException -> 0x00c5, MalformedURLException -> 0x00d6, IOException -> 0x00f6 }
        r8 = r16 - r24;
        r3.<init>(r4, r5, r6, r7, r8);	 Catch:{ SocketTimeoutException -> 0x015c, ConnectTimeoutException -> 0x00c5, MalformedURLException -> 0x00d6, IOException -> 0x00f6 }
        r11 = r23;
    L_0x0053:
        return r3;
    L_0x0054:
        r0 = r20;
        r3 = r0.responseHeaders;	 Catch:{ SocketTimeoutException -> 0x015c, ConnectTimeoutException -> 0x00c5, MalformedURLException -> 0x00d6, IOException -> 0x00f6 }
        r3.putAll(r6);	 Catch:{ SocketTimeoutException -> 0x015c, ConnectTimeoutException -> 0x00c5, MalformedURLException -> 0x00d6, IOException -> 0x00f6 }
        r7 = new com.android.volley.NetworkResponse;	 Catch:{ SocketTimeoutException -> 0x015c, ConnectTimeoutException -> 0x00c5, MalformedURLException -> 0x00d6, IOException -> 0x00f6 }
        r8 = 304; // 0x130 float:4.26E-43 double:1.5E-321;
        r0 = r20;
        r9 = r0.data;	 Catch:{ SocketTimeoutException -> 0x015c, ConnectTimeoutException -> 0x00c5, MalformedURLException -> 0x00d6, IOException -> 0x00f6 }
        r0 = r20;
        r10 = r0.responseHeaders;	 Catch:{ SocketTimeoutException -> 0x015c, ConnectTimeoutException -> 0x00c5, MalformedURLException -> 0x00d6, IOException -> 0x00f6 }
        r11 = 1;
        r4 = android.os.SystemClock.elapsedRealtime();	 Catch:{ SocketTimeoutException -> 0x015c, ConnectTimeoutException -> 0x00c5, MalformedURLException -> 0x00d6, IOException -> 0x00f6 }
        r12 = r4 - r24;
        r7.<init>(r8, r9, r10, r11, r12);	 Catch:{ SocketTimeoutException -> 0x015c, ConnectTimeoutException -> 0x00c5, MalformedURLException -> 0x00d6, IOException -> 0x00f6 }
        r11 = r23;
        r3 = r7;
        goto L_0x0053;
    L_0x0075:
        r3 = r22.getEntity();	 Catch:{ SocketTimeoutException -> 0x015c, ConnectTimeoutException -> 0x00c5, MalformedURLException -> 0x00d6, IOException -> 0x00f6 }
        if (r3 == 0) goto L_0x00af;
    L_0x007b:
        r3 = r22.getEntity();	 Catch:{ SocketTimeoutException -> 0x015c, ConnectTimeoutException -> 0x00c5, MalformedURLException -> 0x00d6, IOException -> 0x00f6 }
        r0 = r26;
        r11 = r0.entityToBytes(r3);	 Catch:{ SocketTimeoutException -> 0x015c, ConnectTimeoutException -> 0x00c5, MalformedURLException -> 0x00d6, IOException -> 0x00f6 }
    L_0x0085:
        r4 = android.os.SystemClock.elapsedRealtime();	 Catch:{ SocketTimeoutException -> 0x00a0, ConnectTimeoutException -> 0x0159, MalformedURLException -> 0x0157, IOException -> 0x0155 }
        r8 = r4 - r24;
        r7 = r26;
        r10 = r27;
        r7.logSlowRequests(r8, r10, r11, r12);	 Catch:{ SocketTimeoutException -> 0x00a0, ConnectTimeoutException -> 0x0159, MalformedURLException -> 0x0157, IOException -> 0x0155 }
        r3 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
        if (r14 < r3) goto L_0x009a;
    L_0x0096:
        r3 = 299; // 0x12b float:4.19E-43 double:1.477E-321;
        if (r14 <= r3) goto L_0x00b3;
    L_0x009a:
        r3 = new java.io.IOException;	 Catch:{ SocketTimeoutException -> 0x00a0, ConnectTimeoutException -> 0x0159, MalformedURLException -> 0x0157, IOException -> 0x0155 }
        r3.<init>();	 Catch:{ SocketTimeoutException -> 0x00a0, ConnectTimeoutException -> 0x0159, MalformedURLException -> 0x0157, IOException -> 0x0155 }
        throw r3;	 Catch:{ SocketTimeoutException -> 0x00a0, ConnectTimeoutException -> 0x0159, MalformedURLException -> 0x0157, IOException -> 0x0155 }
    L_0x00a0:
        r2 = move-exception;
    L_0x00a1:
        r3 = "socket";
        r4 = new com.android.volley.TimeoutError;
        r4.<init>();
        r0 = r27;
        attemptRetryOnException(r3, r0, r4);
        goto L_0x0004;
    L_0x00af:
        r3 = 0;
        r11 = new byte[r3];	 Catch:{ SocketTimeoutException -> 0x015c, ConnectTimeoutException -> 0x00c5, MalformedURLException -> 0x00d6, IOException -> 0x00f6 }
        goto L_0x0085;
    L_0x00b3:
        r13 = new com.android.volley.NetworkResponse;	 Catch:{ SocketTimeoutException -> 0x00a0, ConnectTimeoutException -> 0x0159, MalformedURLException -> 0x0157, IOException -> 0x0155 }
        r17 = 0;
        r4 = android.os.SystemClock.elapsedRealtime();	 Catch:{ SocketTimeoutException -> 0x00a0, ConnectTimeoutException -> 0x0159, MalformedURLException -> 0x0157, IOException -> 0x0155 }
        r18 = r4 - r24;
        r15 = r11;
        r16 = r6;
        r13.<init>(r14, r15, r16, r17, r18);	 Catch:{ SocketTimeoutException -> 0x00a0, ConnectTimeoutException -> 0x0159, MalformedURLException -> 0x0157, IOException -> 0x0155 }
        r3 = r13;
        goto L_0x0053;
    L_0x00c5:
        r2 = move-exception;
        r11 = r23;
    L_0x00c8:
        r3 = "connection";
        r4 = new com.android.volley.TimeoutError;
        r4.<init>();
        r0 = r27;
        attemptRetryOnException(r3, r0, r4);
        goto L_0x0004;
    L_0x00d6:
        r2 = move-exception;
        r11 = r23;
    L_0x00d9:
        r3 = new java.lang.RuntimeException;
        r4 = new java.lang.StringBuilder;
        r4.<init>();
        r5 = "Bad URL ";
        r4 = r4.append(r5);
        r5 = r27.getUrl();
        r4 = r4.append(r5);
        r4 = r4.toString();
        r3.<init>(r4, r2);
        throw r3;
    L_0x00f6:
        r2 = move-exception;
        r11 = r23;
    L_0x00f9:
        r14 = 0;
        r13 = 0;
        if (r22 == 0) goto L_0x0143;
    L_0x00fd:
        r3 = r22.getStatusLine();
        r14 = r3.getStatusCode();
        r3 = "Unexpected response code %d for %s";
        r4 = 2;
        r4 = new java.lang.Object[r4];
        r5 = 0;
        r7 = java.lang.Integer.valueOf(r14);
        r4[r5] = r7;
        r5 = 1;
        r7 = r27.getUrl();
        r4[r5] = r7;
        com.android.volley.VolleyLog.e(r3, r4);
        if (r11 == 0) goto L_0x014f;
    L_0x011d:
        r13 = new com.android.volley.NetworkResponse;
        r17 = 0;
        r4 = android.os.SystemClock.elapsedRealtime();
        r18 = r4 - r24;
        r15 = r11;
        r16 = r6;
        r13.<init>(r14, r15, r16, r17, r18);
        r3 = 401; // 0x191 float:5.62E-43 double:1.98E-321;
        if (r14 == r3) goto L_0x0135;
    L_0x0131:
        r3 = 403; // 0x193 float:5.65E-43 double:1.99E-321;
        if (r14 != r3) goto L_0x0149;
    L_0x0135:
        r3 = "auth";
        r4 = new com.android.volley.AuthFailureError;
        r4.<init>(r13);
        r0 = r27;
        attemptRetryOnException(r3, r0, r4);
        goto L_0x0004;
    L_0x0143:
        r3 = new com.android.volley.NoConnectionError;
        r3.<init>(r2);
        throw r3;
    L_0x0149:
        r3 = new com.android.volley.ServerError;
        r3.<init>(r13);
        throw r3;
    L_0x014f:
        r3 = new com.android.volley.NetworkError;
        r3.<init>(r13);
        throw r3;
    L_0x0155:
        r2 = move-exception;
        goto L_0x00f9;
    L_0x0157:
        r2 = move-exception;
        goto L_0x00d9;
    L_0x0159:
        r2 = move-exception;
        goto L_0x00c8;
    L_0x015c:
        r2 = move-exception;
        r11 = r23;
        goto L_0x00a1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.volley.toolbox.BasicNetwork.performRequest(com.android.volley.Request):com.android.volley.NetworkResponse");
    }

    private void logSlowRequests(long requestLifetime, Request<?> request, byte[] responseContents, StatusLine statusLine) {
        if (DEBUG || requestLifetime > ((long) SLOW_REQUEST_THRESHOLD_MS)) {
            String str = "HTTP response for request=<%s> [lifetime=%d], [size=%s], [rc=%d], [retryCount=%s]";
            Object[] objArr = new Object[5];
            objArr[0] = request;
            objArr[1] = Long.valueOf(requestLifetime);
            objArr[2] = responseContents != null ? Integer.valueOf(responseContents.length) : "null";
            objArr[3] = Integer.valueOf(statusLine.getStatusCode());
            objArr[4] = Integer.valueOf(request.getRetryPolicy().getCurrentRetryCount());
            VolleyLog.d(str, objArr);
        }
    }

    private static void attemptRetryOnException(String logPrefix, Request<?> request, VolleyError exception) throws VolleyError {
        RetryPolicy retryPolicy = request.getRetryPolicy();
        int oldTimeout = request.getTimeoutMs();
        try {
            retryPolicy.retry(exception);
            request.addMarker(String.format("%s-retry [timeout=%s]", new Object[]{logPrefix, Integer.valueOf(oldTimeout)}));
        } catch (VolleyError e) {
            request.addMarker(String.format("%s-timeout-giveup [timeout=%s]", new Object[]{logPrefix, Integer.valueOf(oldTimeout)}));
            throw e;
        }
    }

    private void addCacheHeaders(Map<String, String> headers, Entry entry) {
        if (entry != null) {
            if (entry.etag != null) {
                headers.put("If-None-Match", entry.etag);
            }
            if (entry.serverDate > 0) {
                headers.put("If-Modified-Since", DateUtils.formatDate(new Date(entry.serverDate)));
            }
        }
    }

    private byte[] entityToBytes(HttpEntity entity) throws IOException, ServerError {
        PoolingByteArrayOutputStream bytes = new PoolingByteArrayOutputStream(this.mPool, (int) entity.getContentLength());
        byte[] buffer = null;
        try {
            InputStream in = entity.getContent();
            if (in == null) {
                throw new ServerError();
            }
            buffer = this.mPool.getBuf(1024);
            while (true) {
                int count = in.read(buffer);
                if (count == -1) {
                    break;
                }
                bytes.write(buffer, 0, count);
            }
            byte[] toByteArray = bytes.toByteArray();
            return toByteArray;
        } finally {
            try {
                entity.consumeContent();
            } catch (IOException e) {
                VolleyLog.v("Error occured when calling consumingContent", new Object[0]);
            }
            this.mPool.returnBuf(buffer);
            bytes.close();
        }
    }

    protected static Map<String, String> convertHeaders(Header[] headers) {
        Map<String, String> result = new TreeMap(String.CASE_INSENSITIVE_ORDER);
        for (int i = 0; i < headers.length; i++) {
            result.put(headers[i].getName(), headers[i].getValue());
        }
        return result;
    }
}
