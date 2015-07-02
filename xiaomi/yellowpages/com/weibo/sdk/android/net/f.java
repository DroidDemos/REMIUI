package com.weibo.sdk.android.net;

import android.text.TextUtils;
import com.alipay.mobilesecuritysdk.constant.ConfigConstant;
import com.alipay.sdk.cons.MiniDefine;
import com.miui.yellowpage.ui.bw;
import com.weibo.sdk.android.WeiboException;
import com.weibo.sdk.android.a.a;
import com.weibo.sdk.android.a.b;
import com.weibo.sdk.android.d;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.KeyStore;
import java.util.zip.GZIPInputStream;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.scheme.SocketFactory;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;

/* compiled from: HttpManager */
public class f {
    private static final String BOUNDARY;
    private static final String FP;
    private static final String FQ;

    static {
        BOUNDARY = ht();
        FP = "--" + BOUNDARY;
        FQ = "--" + BOUNDARY + "--";
    }

    public static String a(String str, String str2, d dVar, String str3) {
        HttpUriRequest httpUriRequest = null;
        String str4 = ConfigConstant.WIRELESS_FILENAME;
        try {
            HttpClient hs = hs();
            hs.getParams().setParameter("http.route.default-proxy", NetStateManager.if());
            if (str2.equals("GET")) {
                httpUriRequest = new HttpGet(new StringBuilder(String.valueOf(str)).append("?").append(a.a(dVar)).toString());
            } else if (str2.equals("POST")) {
                HttpPost httpPost = new HttpPost(str);
                byte[] bArr = (byte[]) null;
                String value = dVar.getValue("content-type");
                OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                if (TextUtils.isEmpty(str3)) {
                    if (value != null) {
                        dVar.remove("content-type");
                        httpPost.setHeader("Content-Type", value);
                    } else {
                        httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");
                    }
                    byteArrayOutputStream.write(a.b(dVar).getBytes("UTF-8"));
                } else {
                    a(byteArrayOutputStream, dVar);
                    httpPost.setHeader("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);
                    b.bd(str3);
                    a(byteArrayOutputStream, str3);
                }
                bArr = byteArrayOutputStream.toByteArray();
                byteArrayOutputStream.close();
                httpPost.setEntity(new ByteArrayEntity(bArr));
                Object obj = httpPost;
            } else if (str2.equals("DELETE")) {
                httpUriRequest = new HttpDelete(str);
            }
            HttpResponse execute = hs.execute(httpUriRequest);
            int statusCode = execute.getStatusLine().getStatusCode();
            if (statusCode == ConfigConstant.RESPONSE_CODE) {
                return b(execute);
            }
            throw new WeiboException(b(execute), statusCode);
        } catch (Exception e) {
            throw new WeiboException(e);
        }
    }

    private static HttpClient hs() {
        try {
            KeyStore instance = KeyStore.getInstance(KeyStore.getDefaultType());
            instance.load(null, null);
            SocketFactory dVar = new d(instance);
            dVar.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
            HttpParams basicHttpParams = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(basicHttpParams, bw.FILE_CHOOSER_RESULT_CODE);
            HttpConnectionParams.setSoTimeout(basicHttpParams, bw.FILE_CHOOSER_RESULT_CODE);
            HttpProtocolParams.setVersion(basicHttpParams, HttpVersion.HTTP_1_1);
            HttpProtocolParams.setContentCharset(basicHttpParams, "UTF-8");
            SchemeRegistry schemeRegistry = new SchemeRegistry();
            schemeRegistry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
            schemeRegistry.register(new Scheme(MiniDefine.aQ, dVar, 443));
            ClientConnectionManager threadSafeClientConnManager = new ThreadSafeClientConnManager(basicHttpParams, schemeRegistry);
            HttpConnectionParams.setConnectionTimeout(basicHttpParams, ConfigConstant.SO_TIMEOUT);
            HttpConnectionParams.setSoTimeout(basicHttpParams, 20000);
            return new DefaultHttpClient(threadSafeClientConnManager, basicHttpParams);
        } catch (Exception e) {
            return new DefaultHttpClient();
        }
    }

    private static void a(OutputStream outputStream, d dVar) {
        String str = ConfigConstant.WIRELESS_FILENAME;
        int i = 0;
        while (i < dVar.size()) {
            String J = dVar.J(i);
            StringBuilder stringBuilder = new StringBuilder(10);
            stringBuilder.setLength(0);
            stringBuilder.append(FP).append("\r\n");
            stringBuilder.append("content-disposition: form-data; name=\"").append(J).append("\"\r\n\r\n");
            stringBuilder.append(dVar.getValue(J)).append("\r\n");
            try {
                outputStream.write(stringBuilder.toString().getBytes());
                i++;
            } catch (Exception e) {
                throw new WeiboException(e);
            }
        }
    }

    private static void a(OutputStream outputStream, String str) {
        Exception e;
        Throwable th;
        if (str != null) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(FP).append("\r\n");
            stringBuilder.append("Content-Disposition: form-data; name=\"pic\"; filename=\"").append("news_image").append("\"\r\n");
            stringBuilder.append("Content-Type: ").append("image/png").append("\r\n\r\n");
            FileInputStream fileInputStream;
            try {
                outputStream.write(stringBuilder.toString().getBytes());
                fileInputStream = new FileInputStream(str);
                try {
                    byte[] bArr = new byte[ConfigConstant.MAX_LOG_SIZE];
                    while (true) {
                        int read = fileInputStream.read(bArr);
                        if (read == -1) {
                            break;
                        }
                        outputStream.write(bArr, 0, read);
                    }
                    outputStream.write("\r\n".getBytes());
                    outputStream.write(("\r\n" + FQ).getBytes());
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (Exception e2) {
                            throw new WeiboException(e2);
                        }
                    }
                } catch (IOException e3) {
                    e2 = e3;
                }
            } catch (IOException e4) {
                e2 = e4;
                fileInputStream = null;
                try {
                    throw new WeiboException(e2);
                } catch (Throwable th2) {
                    th = th2;
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (Exception e22) {
                            throw new WeiboException(e22);
                        }
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                fileInputStream = null;
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                throw th;
            }
        }
    }

    private static String b(HttpResponse httpResponse) {
        try {
            InputStream inputStream;
            InputStream content = httpResponse.getEntity().getContent();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            Header firstHeader = httpResponse.getFirstHeader("Content-Encoding");
            if (firstHeader == null || firstHeader.getValue().toLowerCase().indexOf("gzip") <= -1) {
                inputStream = content;
            } else {
                inputStream = new GZIPInputStream(content);
            }
            byte[] bArr = new byte[512];
            while (true) {
                int read = inputStream.read(bArr);
                if (read == -1) {
                    return new String(byteArrayOutputStream.toByteArray(), "UTF-8");
                }
                byteArrayOutputStream.write(bArr, 0, read);
            }
        } catch (IllegalStateException e) {
            return ConfigConstant.WIRELESS_FILENAME;
        } catch (IOException e2) {
            return ConfigConstant.WIRELESS_FILENAME;
        }
    }

    static String ht() {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 1; i < 12; i++) {
            long currentTimeMillis = System.currentTimeMillis() + ((long) i);
            if (currentTimeMillis % 3 == 0) {
                stringBuffer.append(((char) ((int) currentTimeMillis)) % 9);
            } else if (currentTimeMillis % 3 == 1) {
                stringBuffer.append((char) ((int) ((currentTimeMillis % 26) + 65)));
            } else {
                stringBuffer.append((char) ((int) ((currentTimeMillis % 26) + 97)));
            }
        }
        return stringBuffer.toString();
    }
}
