package com.xiaomi.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import com.xiaomi.push.service.PushServiceConstants;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class Network {
    public static final String CMWAP_GATEWAY = "10.0.0.172";
    public static final String CMWAP_HEADER_HOST_KEY = "X-Online-Host";
    public static final int CMWAP_PORT = 80;
    public static final int CONNECTION_TIMEOUT = 10000;
    public static final Pattern ContentTypePattern_Charset;
    public static final Pattern ContentTypePattern_MimeType;
    public static final Pattern ContentTypePattern_XmlEncoding;
    private static final String LogTag = "com.xiaomi.common.Network";
    public static final int READ_TIMEOUT = 15000;
    public static final String RESPONSE_BODY = "RESPONSE_BODY";
    public static final String RESPONSE_CODE = "RESPONSE_CODE";
    public static final String USER_AGENT = "User-Agent";
    public static final String UserAgent_PC_Chrome = "Mozilla/5.0 (Windows; U; Windows NT 6.1; en-US) AppleWebKit/534.3 (KHTML, like Gecko) Chrome/6.0.464.0 Safari/534.3";
    public static final String UserAgent_PC_Chrome_6_0_464_0 = "Mozilla/5.0 (Windows; U; Windows NT 6.1; en-US) AppleWebKit/534.3 (KHTML, like Gecko) Chrome/6.0.464.0 Safari/534.3";

    public static final class DoneHandlerInputStream extends FilterInputStream {
        private boolean done;

        public DoneHandlerInputStream(InputStream stream) {
            super(stream);
        }

        public int read(byte[] bytes, int offset, int count) throws IOException {
            if (!this.done) {
                int result = super.read(bytes, offset, count);
                if (result != -1) {
                    return result;
                }
            }
            this.done = true;
            return -1;
        }
    }

    private static class DownloadTask extends AsyncTask<Void, Void, Boolean> {
        private boolean bOnlyWifi;
        private PostDownloadHandler handler;
        private Context mContext;
        private OutputStream output;
        private String url;

        public DownloadTask(String url, OutputStream output, PostDownloadHandler handler) {
            this(url, output, handler, false, null);
        }

        public DownloadTask(String url, OutputStream output, PostDownloadHandler handler, boolean bOnlyWifi, Context context) {
            this.url = url;
            this.output = output;
            this.handler = handler;
            this.bOnlyWifi = bOnlyWifi;
            this.mContext = context;
        }

        protected Boolean doInBackground(Void... params) {
            return Boolean.valueOf(Network.downloadFile(this.url, this.output, this.bOnlyWifi, this.mContext));
        }

        protected void onPostExecute(Boolean result) {
            this.handler.OnPostDownload(result.booleanValue());
        }
    }

    public static class HttpHeaderInfo {
        public Map<String, String> AllHeaders;
        public String ContentType;
        public int ResponseCode;
        public String UserAgent;
        public String realUrl;
    }

    public interface PostDownloadHandler {
        void OnPostDownload(boolean z);
    }

    public static InputStream downloadXmlAsStream(Context context, URL url) throws IOException {
        return downloadXmlAsStream(context, url, null, null, null, null);
    }

    public static InputStream downloadXmlAsStream(Context context, URL url, String userAgent, String cookie) throws IOException {
        return downloadXmlAsStream(context, url, userAgent, cookie, null, null);
    }

    public static InputStream downloadXmlAsStream(Context context, URL url, String userAgent, String cookie, Map<String, String> requestHdrs, HttpHeaderInfo responseHdrs) throws IOException {
        if (context == null) {
            throw new IllegalArgumentException("context");
        } else if (url == null) {
            throw new IllegalArgumentException("url");
        } else {
            URL newUrl = url;
            try {
                HttpURLConnection.setFollowRedirects(true);
                HttpURLConnection conn = getHttpUrlConnection(context, newUrl);
                conn.setConnectTimeout(CONNECTION_TIMEOUT);
                conn.setReadTimeout(READ_TIMEOUT);
                if (!TextUtils.isEmpty(userAgent)) {
                    conn.setRequestProperty(USER_AGENT, userAgent);
                }
                if (cookie != null) {
                    conn.setRequestProperty("Cookie", cookie);
                }
                if (requestHdrs != null) {
                    for (String key : requestHdrs.keySet()) {
                        conn.setRequestProperty(key, (String) requestHdrs.get(key));
                    }
                }
                if (responseHdrs != null) {
                    if (url.getProtocol().equals("http") || url.getProtocol().equals("https")) {
                        responseHdrs.ResponseCode = conn.getResponseCode();
                        if (responseHdrs.AllHeaders == null) {
                            responseHdrs.AllHeaders = new HashMap();
                        }
                        int i = 0;
                        while (true) {
                            String name = conn.getHeaderFieldKey(i);
                            String value = conn.getHeaderField(i);
                            if (name == null && value == null) {
                                break;
                            }
                            if (!(TextUtils.isEmpty(name) || TextUtils.isEmpty(value))) {
                                responseHdrs.AllHeaders.put(name.toLowerCase(), value);
                            }
                            i++;
                        }
                    }
                }
                return new DoneHandlerInputStream(conn.getInputStream());
            } catch (IOException e) {
                throw e;
            } catch (Throwable e2) {
                IOException iOException = new IOException(e2);
            }
        }
    }

    public static InputStream downloadXmlAsStreamWithoutRedirect(URL url, String userAgent, String cookie) throws IOException {
        InputStream responseStream = null;
        try {
            HttpURLConnection.setFollowRedirects(false);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(CONNECTION_TIMEOUT);
            conn.setReadTimeout(READ_TIMEOUT);
            if (!TextUtils.isEmpty(userAgent)) {
                conn.setRequestProperty(USER_AGENT, userAgent);
            }
            if (cookie != null) {
                conn.setRequestProperty("Cookie", cookie);
            }
            int resCode = conn.getResponseCode();
            if (resCode < 300 || resCode >= 400) {
                responseStream = conn.getInputStream();
            }
            return new DoneHandlerInputStream(responseStream);
        } catch (IOException e) {
            throw e;
        } catch (Throwable e2) {
            IOException iOException = new IOException(e2);
        }
    }

    public static String downloadXml(Context context, URL url) throws IOException {
        return downloadXml(context, url, null, "UTF-8", null);
    }

    public static String downloadXml(Context context, URL url, String userAgent, String encoding, String cookie) throws IOException {
        IOException e;
        StringBuilder stringBuilder;
        Throwable th;
        Throwable e2;
        InputStream responseStream = null;
        try {
            StringBuilder sbReponse = new StringBuilder(1024);
            try {
                responseStream = downloadXmlAsStream(context, url, userAgent, cookie);
                BufferedReader reader = new BufferedReader(new InputStreamReader(responseStream, encoding), 1024);
                while (true) {
                    String line = reader.readLine();
                    if (line == null) {
                        break;
                    }
                    sbReponse.append(line);
                    sbReponse.append("\r\n");
                }
                if (responseStream != null) {
                    try {
                        responseStream.close();
                    } catch (IOException e3) {
                        Log.e(LogTag, "Failed to close responseStream" + e3.toString());
                    }
                }
                return sbReponse.toString();
            } catch (IOException e4) {
                e3 = e4;
                stringBuilder = sbReponse;
                try {
                    throw e3;
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                stringBuilder = sbReponse;
                if (responseStream != null) {
                    try {
                        responseStream.close();
                    } catch (IOException e32) {
                        Log.e(LogTag, "Failed to close responseStream" + e32.toString());
                    }
                }
                throw th;
            }
        } catch (IOException e5) {
            e32 = e5;
            throw e32;
        } catch (Throwable th4) {
            e2 = th4;
            throw new IOException(e2);
        }
    }

    public static String downloadXml(Context context, URL url, String userAgent, String cookie, Map<String, String> requestHdrs) throws IOException {
        IOException e;
        StringBuilder stringBuilder;
        Throwable th;
        Throwable e2;
        InputStream responseStream = null;
        try {
            StringBuilder sbReponse = new StringBuilder(1024);
            try {
                responseStream = downloadXmlAsStream(context, url, userAgent, cookie, requestHdrs, null);
                BufferedReader reader = new BufferedReader(new InputStreamReader(responseStream, "UTF-8"), 1024);
                while (true) {
                    String line = reader.readLine();
                    if (line == null) {
                        break;
                    }
                    sbReponse.append(line);
                    sbReponse.append("\r\n");
                }
                if (responseStream != null) {
                    try {
                        responseStream.close();
                    } catch (IOException e3) {
                        Log.e(LogTag, "Failed to close responseStream" + e3.toString());
                    }
                }
                return sbReponse.toString();
            } catch (IOException e4) {
                e3 = e4;
                stringBuilder = sbReponse;
                try {
                    throw e3;
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                stringBuilder = sbReponse;
                if (responseStream != null) {
                    try {
                        responseStream.close();
                    } catch (IOException e32) {
                        Log.e(LogTag, "Failed to close responseStream" + e32.toString());
                    }
                }
                throw th;
            }
        } catch (IOException e5) {
            e32 = e5;
            throw e32;
        } catch (Throwable th4) {
            e2 = th4;
            throw new IOException(e2);
        }
    }

    public static String tryDetectCharsetEncoding(URL url, String userAgent) throws IOException {
        IOException e;
        Throwable th;
        Throwable e2;
        if (url == null) {
            throw new IllegalArgumentException("url");
        }
        HttpURLConnection.setFollowRedirects(true);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setConnectTimeout(5000);
        conn.setReadTimeout(READ_TIMEOUT);
        if (!TextUtils.isEmpty(userAgent)) {
            conn.setRequestProperty(USER_AGENT, userAgent);
        }
        String ret = null;
        String contentType = conn.getContentType();
        if (!TextUtils.isEmpty(contentType)) {
            String charset;
            Matcher matcher = ContentTypePattern_Charset.matcher(contentType);
            if (matcher.matches() && matcher.groupCount() >= 3) {
                charset = matcher.group(2);
                if (!TextUtils.isEmpty(charset)) {
                    ret = charset;
                    Log.v(LogTag, "HTTP charset detected is: " + ret);
                }
            }
            if (TextUtils.isEmpty(ret)) {
                matcher = ContentTypePattern_MimeType.matcher(contentType);
                if (matcher.matches() && matcher.groupCount() >= 2) {
                    String mimetype = matcher.group(1);
                    if (!TextUtils.isEmpty(mimetype)) {
                        mimetype = mimetype.toLowerCase();
                        if (mimetype.startsWith("application/") && (mimetype.startsWith("application/xml") || mimetype.endsWith("+xml"))) {
                            InputStream inputStream = null;
                            try {
                                InputStream responseStream = new DoneHandlerInputStream(conn.getInputStream());
                                try {
                                    String aLine;
                                    BufferedReader reader = new BufferedReader(new InputStreamReader(responseStream));
                                    do {
                                        aLine = reader.readLine();
                                        if (aLine == null) {
                                            break;
                                        }
                                        aLine = aLine.trim();
                                    } while (aLine.length() == 0);
                                    matcher = ContentTypePattern_XmlEncoding.matcher(aLine);
                                    if (matcher.matches() && matcher.groupCount() >= 3) {
                                        charset = matcher.group(2);
                                        if (!TextUtils.isEmpty(charset)) {
                                            ret = charset;
                                            Log.v(LogTag, "XML charset detected is: " + ret);
                                        }
                                    }
                                    if (responseStream != null) {
                                        responseStream.close();
                                    }
                                } catch (IOException e3) {
                                    e = e3;
                                    inputStream = responseStream;
                                    try {
                                        throw e;
                                    } catch (Throwable th2) {
                                        th = th2;
                                    }
                                } catch (Throwable th3) {
                                    th = th3;
                                    inputStream = responseStream;
                                    if (inputStream != null) {
                                        inputStream.close();
                                    }
                                    throw th;
                                }
                            } catch (IOException e4) {
                                e = e4;
                                throw e;
                            } catch (Throwable th4) {
                                e2 = th4;
                                throw new IOException(e2);
                            }
                        }
                    }
                }
            }
        }
        return ret;
    }

    static {
        ContentTypePattern_MimeType = Pattern.compile("([^\\s;]+)(.*)");
        ContentTypePattern_Charset = Pattern.compile("(.*?charset\\s*=[^a-zA-Z0-9]*)([-a-zA-Z0-9]+)(.*)", 2);
        ContentTypePattern_XmlEncoding = Pattern.compile("(\\<\\?xml\\s+.*?encoding\\s*=[^a-zA-Z0-9]*)([-a-zA-Z0-9]+)(.*)", 2);
    }

    public static InputStream getHttpPostAsStream(URL url, String data, Map<String, String> headers, String userAgent, String cookie) throws IOException {
        if (url == null) {
            throw new IllegalArgumentException("url");
        }
        URL newUrl = url;
        try {
            HttpURLConnection.setFollowRedirects(true);
            HttpURLConnection conn = (HttpURLConnection) newUrl.openConnection();
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(READ_TIMEOUT);
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            if (!TextUtils.isEmpty(userAgent)) {
                conn.setRequestProperty(USER_AGENT, userAgent);
            }
            if (!TextUtils.isEmpty(cookie)) {
                conn.setRequestProperty("Cookie", cookie);
            }
            conn.getOutputStream().write(data.getBytes());
            conn.getOutputStream().flush();
            conn.getOutputStream().close();
            headers.put("ResponseCode", conn.getResponseCode() + "");
            int i = 0;
            while (true) {
                String name = conn.getHeaderFieldKey(i);
                String value = conn.getHeaderField(i);
                if (name == null && value == null) {
                    return conn.getInputStream();
                }
                headers.put(name, value);
                i++;
            }
        } catch (IOException e) {
            throw e;
        } catch (Throwable e2) {
            IOException iOException = new IOException(e2);
        }
    }

    public static HttpHeaderInfo getHttpHeaderInfo(String urlString, String userAgent, String cookie) {
        try {
            URL url = new URL(urlString);
            if (!url.getProtocol().equals("http") && !url.getProtocol().equals("https")) {
                return null;
            }
            HttpURLConnection.setFollowRedirects(false);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            if (urlString.indexOf("wap") == -1) {
                conn.setConnectTimeout(5000);
                conn.setReadTimeout(5000);
            } else {
                conn.setConnectTimeout(READ_TIMEOUT);
                conn.setReadTimeout(READ_TIMEOUT);
            }
            if (!TextUtils.isEmpty(userAgent)) {
                conn.setRequestProperty(USER_AGENT, userAgent);
            }
            if (cookie != null) {
                conn.setRequestProperty("Cookie", cookie);
            }
            HttpHeaderInfo ret = new HttpHeaderInfo();
            ret.ResponseCode = conn.getResponseCode();
            ret.UserAgent = userAgent;
            int i = 0;
            while (true) {
                String name = conn.getHeaderFieldKey(i);
                String value = conn.getHeaderField(i);
                if (name == null && value == null) {
                    return ret;
                }
                if (name != null && name.equals("content-type")) {
                    ret.ContentType = value;
                }
                if (name != null && name.equals(PushServiceConstants.EXTENSION_ELEMENT_LOCATION)) {
                    URI uri = new URI(value);
                    if (!uri.isAbsolute()) {
                        uri = new URI(urlString).resolve(uri);
                    }
                    ret.realUrl = uri.toString();
                }
                i++;
            }
        } catch (MalformedURLException e) {
            Log.e(LogTag, "Failed to transform URL", e);
            return null;
        } catch (IOException e2) {
            Log.e(LogTag, "Failed to get mime type", e2);
            return null;
        } catch (URISyntaxException e3) {
            Log.e(LogTag, "Failed to parse URI", e3);
            return null;
        } catch (Throwable th) {
            Log.e(LogTag, "Failed to get http head info");
            return null;
        }
    }

    public static String fromParamListToString(List<NameValuePair> nameValuePairs) {
        StringBuffer params = new StringBuffer();
        for (NameValuePair pair : nameValuePairs) {
            try {
                if (pair.getValue() != null) {
                    params.append(URLEncoder.encode(pair.getName(), "UTF-8"));
                    params.append("=");
                    params.append(URLEncoder.encode(pair.getValue(), "UTF-8"));
                    params.append("&");
                }
            } catch (UnsupportedEncodingException e) {
                Log.d(LogTag, "Failed to convert from param list to string: " + e.toString());
                Log.d(LogTag, "pair: " + pair.toString());
                return null;
            }
        }
        if (params.length() > 0) {
            params = params.deleteCharAt(params.length() - 1);
        }
        return params.toString();
    }

    public static JSONObject doHttpPostWithResponseStatus(Context context, String url, List<NameValuePair> nameValuePairs, Map<String, String> map, String userAgent, String cookie) {
        if (context == null) {
            throw new IllegalArgumentException("context");
        } else if (TextUtils.isEmpty(url)) {
            throw new IllegalArgumentException("url");
        } else {
            JSONObject result = new JSONObject();
            BasicHttpParams httpParameters = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpParameters, CONNECTION_TIMEOUT);
            HttpConnectionParams.setSoTimeout(httpParameters, READ_TIMEOUT);
            if (!TextUtils.isEmpty(userAgent)) {
                HttpProtocolParams.setUserAgent(httpParameters, userAgent);
            }
            if (!TextUtils.isEmpty(cookie)) {
                httpParameters.setParameter("Cookie", cookie);
            }
            HttpClient httpClient = new DefaultHttpClient(httpParameters);
            try {
                HttpPost httpPost;
                if (isCmwap(context)) {
                    URL _url = new URL(url);
                    String cmwapUrl = getCMWapUrl(_url);
                    String host = _url.getHost();
                    httpPost = new HttpPost(cmwapUrl);
                    httpPost.addHeader(CMWAP_HEADER_HOST_KEY, host);
                } else {
                    httpPost = new HttpPost(url);
                }
                if (!(nameValuePairs == null || nameValuePairs.size() == 0)) {
                    httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
                }
                HttpResponse response = httpClient.execute(httpPost);
                String strResponseBody = "";
                int responseCode = response.getStatusLine().getStatusCode();
                HttpEntity body = response.getEntity();
                if (body != null) {
                    strResponseBody = EntityUtils.toString(body);
                }
                result.put(RESPONSE_CODE, responseCode);
                result.put(RESPONSE_BODY, strResponseBody);
                if (!(result.has(RESPONSE_CODE) && result.has(RESPONSE_BODY))) {
                    result.remove(RESPONSE_CODE);
                    result.remove(RESPONSE_BODY);
                }
            } catch (ParseException e) {
                Log.e(LogTag, "doHttpPostWithResponseStatus", e);
                if (!(result.has(RESPONSE_CODE) && result.has(RESPONSE_BODY))) {
                    result.remove(RESPONSE_CODE);
                    result.remove(RESPONSE_BODY);
                }
            } catch (IOException e2) {
                Log.e(LogTag, "doHttpPostWithResponseStatus", e2);
                if (!(result.has(RESPONSE_CODE) && result.has(RESPONSE_BODY))) {
                    result.remove(RESPONSE_CODE);
                    result.remove(RESPONSE_BODY);
                }
            } catch (JSONException e3) {
                Log.e(LogTag, "doHttpPostWithResponseStatus", e3);
                if (!(result.has(RESPONSE_CODE) && result.has(RESPONSE_BODY))) {
                    result.remove(RESPONSE_CODE);
                    result.remove(RESPONSE_BODY);
                }
            } catch (Throwable th) {
                if (!(result.has(RESPONSE_CODE) && result.has(RESPONSE_BODY))) {
                    result.remove(RESPONSE_CODE);
                    result.remove(RESPONSE_BODY);
                }
            }
            return result;
        }
    }

    public static String doHttpPost(Context context, String url, List<NameValuePair> nameValuePairs) throws IOException {
        return doHttpPost(context, url, nameValuePairs, null, null, null);
    }

    public static String doHttpPost(Context context, String url, List<NameValuePair> nameValuePairs, Map<String, String> headers, String userAgent, String cookie) throws IOException {
        if (TextUtils.isEmpty(url)) {
            throw new IllegalArgumentException("url");
        }
        try {
            HttpURLConnection conn = getHttpUrlConnection(context, new URL(url));
            conn.setConnectTimeout(CONNECTION_TIMEOUT);
            conn.setReadTimeout(READ_TIMEOUT);
            conn.setRequestMethod("POST");
            if (!TextUtils.isEmpty(userAgent)) {
                conn.setRequestProperty(USER_AGENT, userAgent);
            }
            if (cookie != null) {
                conn.setRequestProperty("Cookie", cookie);
            }
            String strParams = fromParamListToString(nameValuePairs);
            if (strParams == null) {
                throw new IllegalArgumentException("nameValuePairs");
            }
            conn.setDoOutput(true);
            byte[] b = strParams.getBytes();
            conn.getOutputStream().write(b, 0, b.length);
            conn.getOutputStream().flush();
            conn.getOutputStream().close();
            Log.d(LogTag, "Http POST Response Code: " + conn.getResponseCode());
            BufferedReader rd = new BufferedReader(new InputStreamReader(new DoneHandlerInputStream(conn.getInputStream())));
            StringBuffer tempStr = new StringBuffer();
            String crlf = System.getProperty("line.separator");
            for (String tempLine = rd.readLine(); tempLine != null; tempLine = rd.readLine()) {
                tempStr.append(tempLine);
                tempStr.append(crlf);
            }
            String responseContent = tempStr.toString();
            rd.close();
            if (headers != null) {
                int i = 0;
                while (true) {
                    String name = conn.getHeaderFieldKey(i);
                    String value = conn.getHeaderField(i);
                    if (name == null && value == null) {
                        break;
                    }
                    headers.put(name, value);
                    i = (i + 1) + 1;
                }
            }
            return responseContent;
        } catch (IOException e) {
            throw e;
        } catch (Throwable e2) {
            IOException iOException = new IOException(e2);
        }
    }

    public static void beginDownloadFile(String url, OutputStream output, PostDownloadHandler handler) {
        new DownloadTask(url, output, handler).execute(new Void[0]);
    }

    public static void beginDownloadFile(String url, OutputStream output, Context context, boolean bOnlyWifi, PostDownloadHandler handler) {
        new DownloadTask(url, output, handler, bOnlyWifi, context).execute(new Void[0]);
    }

    public static boolean downloadFile(String urlStr, OutputStream output) {
        return downloadFile(urlStr, output, false, null);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean downloadFile(java.lang.String r11, java.io.OutputStream r12, boolean r13, android.content.Context r14) {
        /*
        r7 = 1;
        r8 = 0;
        r0 = 0;
        r5 = 0;
        r6 = new java.net.URL;	 Catch:{ IOException -> 0x004d, Throwable -> 0x0072 }
        r6.<init>(r11);	 Catch:{ IOException -> 0x004d, Throwable -> 0x0072 }
        r2 = r6.openConnection();	 Catch:{ IOException -> 0x004d, Throwable -> 0x0072 }
        r2 = (java.net.HttpURLConnection) r2;	 Catch:{ IOException -> 0x004d, Throwable -> 0x0072 }
        r9 = 10000; // 0x2710 float:1.4013E-41 double:4.9407E-320;
        r2.setConnectTimeout(r9);	 Catch:{ IOException -> 0x004d, Throwable -> 0x0072 }
        r9 = 15000; // 0x3a98 float:2.102E-41 double:7.411E-320;
        r2.setReadTimeout(r9);	 Catch:{ IOException -> 0x004d, Throwable -> 0x0072 }
        r9 = 1;
        java.net.HttpURLConnection.setFollowRedirects(r9);	 Catch:{ IOException -> 0x004d, Throwable -> 0x0072 }
        r2.connect();	 Catch:{ IOException -> 0x004d, Throwable -> 0x0072 }
        r5 = r2.getInputStream();	 Catch:{ IOException -> 0x004d, Throwable -> 0x0072 }
        r9 = 1024; // 0x400 float:1.435E-42 double:5.06E-321;
        r1 = new byte[r9];	 Catch:{ IOException -> 0x004d, Throwable -> 0x0072 }
    L_0x0028:
        r3 = r5.read(r1);	 Catch:{ IOException -> 0x004d, Throwable -> 0x0072 }
        r9 = -1;
        if (r3 == r9) goto L_0x003e;
    L_0x002f:
        r9 = 0;
        r12.write(r1, r9, r3);	 Catch:{ IOException -> 0x004d, Throwable -> 0x0072 }
        if (r13 == 0) goto L_0x0028;
    L_0x0035:
        if (r14 == 0) goto L_0x0028;
    L_0x0037:
        r9 = isWifi(r14);	 Catch:{ IOException -> 0x004d, Throwable -> 0x0072 }
        if (r9 != 0) goto L_0x0028;
    L_0x003d:
        r0 = 1;
    L_0x003e:
        if (r0 != 0) goto L_0x004b;
    L_0x0040:
        if (r5 == 0) goto L_0x0045;
    L_0x0042:
        r5.close();	 Catch:{ IOException -> 0x00a4 }
    L_0x0045:
        if (r12 == 0) goto L_0x004a;
    L_0x0047:
        r12.close();	 Catch:{ IOException -> 0x00a6 }
    L_0x004a:
        return r7;
    L_0x004b:
        r7 = r8;
        goto L_0x0040;
    L_0x004d:
        r4 = move-exception;
        r7 = "com.xiaomi.common.Network";
        r9 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0098 }
        r9.<init>();	 Catch:{ all -> 0x0098 }
        r10 = "error while download file";
        r9 = r9.append(r10);	 Catch:{ all -> 0x0098 }
        r9 = r9.append(r4);	 Catch:{ all -> 0x0098 }
        r9 = r9.toString();	 Catch:{ all -> 0x0098 }
        android.util.Log.e(r7, r9);	 Catch:{ all -> 0x0098 }
        if (r5 == 0) goto L_0x006b;
    L_0x0068:
        r5.close();	 Catch:{ IOException -> 0x00a8 }
    L_0x006b:
        if (r12 == 0) goto L_0x0070;
    L_0x006d:
        r12.close();	 Catch:{ IOException -> 0x00aa }
    L_0x0070:
        r7 = r8;
        goto L_0x004a;
    L_0x0072:
        r4 = move-exception;
        r7 = "com.xiaomi.common.Network";
        r9 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0098 }
        r9.<init>();	 Catch:{ all -> 0x0098 }
        r10 = "error while download file";
        r9 = r9.append(r10);	 Catch:{ all -> 0x0098 }
        r9 = r9.append(r4);	 Catch:{ all -> 0x0098 }
        r9 = r9.toString();	 Catch:{ all -> 0x0098 }
        android.util.Log.e(r7, r9);	 Catch:{ all -> 0x0098 }
        if (r5 == 0) goto L_0x0090;
    L_0x008d:
        r5.close();	 Catch:{ IOException -> 0x00ac }
    L_0x0090:
        if (r12 == 0) goto L_0x0070;
    L_0x0092:
        r12.close();	 Catch:{ IOException -> 0x0096 }
        goto L_0x0070;
    L_0x0096:
        r7 = move-exception;
        goto L_0x0070;
    L_0x0098:
        r7 = move-exception;
        if (r5 == 0) goto L_0x009e;
    L_0x009b:
        r5.close();	 Catch:{ IOException -> 0x00ae }
    L_0x009e:
        if (r12 == 0) goto L_0x00a3;
    L_0x00a0:
        r12.close();	 Catch:{ IOException -> 0x00b0 }
    L_0x00a3:
        throw r7;
    L_0x00a4:
        r8 = move-exception;
        goto L_0x0045;
    L_0x00a6:
        r8 = move-exception;
        goto L_0x004a;
    L_0x00a8:
        r7 = move-exception;
        goto L_0x006b;
    L_0x00aa:
        r7 = move-exception;
        goto L_0x0070;
    L_0x00ac:
        r7 = move-exception;
        goto L_0x0090;
    L_0x00ae:
        r8 = move-exception;
        goto L_0x009e;
    L_0x00b0:
        r8 = move-exception;
        goto L_0x00a3;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.network.Network.downloadFile(java.lang.String, java.io.OutputStream, boolean, android.content.Context):boolean");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean downloadFile(java.lang.String r14, java.io.OutputStream r15, android.content.Context r16) {
        /*
        r3 = 0;
        r10 = new java.net.URL;	 Catch:{ IOException -> 0x0062, Throwable -> 0x00ae }
        r10.<init>(r14);	 Catch:{ IOException -> 0x0062, Throwable -> 0x00ae }
        r11 = isCmwap(r16);	 Catch:{ IOException -> 0x0062, Throwable -> 0x00ae }
        if (r11 == 0) goto L_0x00a1;
    L_0x000c:
        r11 = 0;
        java.net.HttpURLConnection.setFollowRedirects(r11);	 Catch:{ IOException -> 0x0062, Throwable -> 0x00ae }
        r2 = getCMWapUrl(r10);	 Catch:{ IOException -> 0x0062, Throwable -> 0x00ae }
        r6 = r10.getHost();	 Catch:{ IOException -> 0x0062, Throwable -> 0x00ae }
        r11 = new java.net.URL;	 Catch:{ IOException -> 0x0062, Throwable -> 0x00ae }
        r11.<init>(r2);	 Catch:{ IOException -> 0x0062, Throwable -> 0x00ae }
        r11 = r11.openConnection();	 Catch:{ IOException -> 0x0062, Throwable -> 0x00ae }
        r0 = r11;
        r0 = (java.net.HttpURLConnection) r0;	 Catch:{ IOException -> 0x0062, Throwable -> 0x00ae }
        r3 = r0;
        r11 = "X-Online-Host";
        r3.setRequestProperty(r11, r6);	 Catch:{ IOException -> 0x0062, Throwable -> 0x00ae }
        r9 = r3.getResponseCode();	 Catch:{ IOException -> 0x0062, Throwable -> 0x00ae }
    L_0x002e:
        r11 = 300; // 0x12c float:4.2E-43 double:1.48E-321;
        if (r9 < r11) goto L_0x0042;
    L_0x0032:
        r11 = 400; // 0x190 float:5.6E-43 double:1.976E-321;
        if (r9 >= r11) goto L_0x0042;
    L_0x0036:
        r11 = "location";
        r8 = r3.getHeaderField(r11);	 Catch:{ IOException -> 0x0062, Throwable -> 0x00ae }
        r11 = android.text.TextUtils.isEmpty(r8);	 Catch:{ IOException -> 0x0062, Throwable -> 0x00ae }
        if (r11 == 0) goto L_0x007d;
    L_0x0042:
        r11 = 10000; // 0x2710 float:1.4013E-41 double:4.9407E-320;
        r3.setConnectTimeout(r11);	 Catch:{ IOException -> 0x0062, Throwable -> 0x00ae }
        r11 = 15000; // 0x3a98 float:2.102E-41 double:7.411E-320;
        r3.setReadTimeout(r11);	 Catch:{ IOException -> 0x0062, Throwable -> 0x00ae }
        r3.connect();	 Catch:{ IOException -> 0x0062, Throwable -> 0x00ae }
        r7 = r3.getInputStream();	 Catch:{ IOException -> 0x0062, Throwable -> 0x00ae }
        r11 = 1024; // 0x400 float:1.435E-42 double:5.06E-321;
        r1 = new byte[r11];	 Catch:{ IOException -> 0x0062, Throwable -> 0x00ae }
    L_0x0057:
        r4 = r7.read(r1);	 Catch:{ IOException -> 0x0062, Throwable -> 0x00ae }
        if (r4 <= 0) goto L_0x00c8;
    L_0x005d:
        r11 = 0;
        r15.write(r1, r11, r4);	 Catch:{ IOException -> 0x0062, Throwable -> 0x00ae }
        goto L_0x0057;
    L_0x0062:
        r5 = move-exception;
        r11 = "com.xiaomi.common.Network";
        r12 = new java.lang.StringBuilder;
        r12.<init>();
        r13 = "error while download file";
        r12 = r12.append(r13);
        r12 = r12.append(r5);
        r12 = r12.toString();
        android.util.Log.e(r11, r12);
    L_0x007b:
        r11 = 0;
    L_0x007c:
        return r11;
    L_0x007d:
        r10 = new java.net.URL;	 Catch:{ IOException -> 0x0062, Throwable -> 0x00ae }
        r10.<init>(r8);	 Catch:{ IOException -> 0x0062, Throwable -> 0x00ae }
        r2 = getCMWapUrl(r10);	 Catch:{ IOException -> 0x0062, Throwable -> 0x00ae }
        r6 = r10.getHost();	 Catch:{ IOException -> 0x0062, Throwable -> 0x00ae }
        r11 = new java.net.URL;	 Catch:{ IOException -> 0x0062, Throwable -> 0x00ae }
        r11.<init>(r2);	 Catch:{ IOException -> 0x0062, Throwable -> 0x00ae }
        r11 = r11.openConnection();	 Catch:{ IOException -> 0x0062, Throwable -> 0x00ae }
        r0 = r11;
        r0 = (java.net.HttpURLConnection) r0;	 Catch:{ IOException -> 0x0062, Throwable -> 0x00ae }
        r3 = r0;
        r11 = "X-Online-Host";
        r3.setRequestProperty(r11, r6);	 Catch:{ IOException -> 0x0062, Throwable -> 0x00ae }
        r9 = r3.getResponseCode();	 Catch:{ IOException -> 0x0062, Throwable -> 0x00ae }
        goto L_0x002e;
    L_0x00a1:
        r11 = r10.openConnection();	 Catch:{ IOException -> 0x0062, Throwable -> 0x00ae }
        r0 = r11;
        r0 = (java.net.HttpURLConnection) r0;	 Catch:{ IOException -> 0x0062, Throwable -> 0x00ae }
        r3 = r0;
        r11 = 1;
        java.net.HttpURLConnection.setFollowRedirects(r11);	 Catch:{ IOException -> 0x0062, Throwable -> 0x00ae }
        goto L_0x0042;
    L_0x00ae:
        r5 = move-exception;
        r11 = "com.xiaomi.common.Network";
        r12 = new java.lang.StringBuilder;
        r12.<init>();
        r13 = "error while download file";
        r12 = r12.append(r13);
        r12 = r12.append(r5);
        r12 = r12.toString();
        android.util.Log.e(r11, r12);
        goto L_0x007b;
    L_0x00c8:
        r7.close();	 Catch:{ IOException -> 0x0062, Throwable -> 0x00ae }
        r15.close();	 Catch:{ IOException -> 0x0062, Throwable -> 0x00ae }
        r11 = 1;
        goto L_0x007c;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.network.Network.downloadFile(java.lang.String, java.io.OutputStream, android.content.Context):boolean");
    }

    public static String uploadFile(String url, File file, String fileKey) throws IOException {
        FileInputStream fileInputStream;
        IOException e;
        Throwable th;
        Throwable e2;
        if (!file.exists()) {
            return null;
        }
        String filename = file.getName();
        String lineEnd = "\r\n";
        String twoHyphens = "--";
        String boundary = "*****";
        FileInputStream fileInputStream2 = null;
        DataOutputStream dataOutputStream = null;
        BufferedReader rd = null;
        try {
            HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
            conn.setReadTimeout(READ_TIMEOUT);
            conn.setConnectTimeout(CONNECTION_TIMEOUT);
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setUseCaches(false);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setRequestProperty("Content-Type", "multipart/form-data;boundary=*****");
            conn.setFixedLengthStreamingMode(((filename.length() + 77) + ((int) file.length())) + fileKey.length());
            DataOutputStream dos = new DataOutputStream(conn.getOutputStream());
            try {
                dos.writeBytes("--*****\r\n");
                dos.writeBytes("Content-Disposition: form-data; name=\"" + fileKey + "\";filename=\"" + file.getName() + "\"" + "\r\n");
                dos.writeBytes("\r\n");
                fileInputStream = new FileInputStream(file);
            } catch (IOException e3) {
                e = e3;
                dataOutputStream = dos;
                try {
                    throw e;
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                dataOutputStream = dos;
                if (fileInputStream2 != null) {
                    try {
                        fileInputStream2.close();
                    } catch (IOException e4) {
                        Log.e(LogTag, "error while closing strean", e4);
                        throw th;
                    }
                }
                if (dataOutputStream != null) {
                    dataOutputStream.close();
                }
                if (rd != null) {
                    rd.close();
                }
                throw th;
            }
            try {
                byte[] buffer = new byte[1024];
                while (true) {
                    int bytesRead = fileInputStream.read(buffer);
                    if (bytesRead == -1) {
                        break;
                    }
                    dos.write(buffer, 0, bytesRead);
                    dos.flush();
                }
                dos.writeBytes("\r\n");
                dos.writeBytes("--");
                dos.writeBytes("*****");
                dos.writeBytes("--");
                dos.writeBytes("\r\n");
                dos.flush();
                StringBuffer sb = new StringBuffer();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new DoneHandlerInputStream(conn.getInputStream())));
                while (true) {
                    try {
                        String line = bufferedReader.readLine();
                        if (line == null) {
                            break;
                        }
                        sb.append(line);
                    } catch (IOException e5) {
                        e4 = e5;
                        rd = bufferedReader;
                        dataOutputStream = dos;
                        fileInputStream2 = fileInputStream;
                    } catch (Throwable th4) {
                        th = th4;
                        rd = bufferedReader;
                        dataOutputStream = dos;
                        fileInputStream2 = fileInputStream;
                    }
                }
                String stringBuffer = sb.toString();
                if (fileInputStream != null) {
                    try {
                        fileInputStream.close();
                    } catch (IOException e42) {
                        Log.e(LogTag, "error while closing strean", e42);
                        return stringBuffer;
                    }
                }
                if (dos != null) {
                    dos.close();
                }
                if (bufferedReader == null) {
                    return stringBuffer;
                }
                bufferedReader.close();
                return stringBuffer;
            } catch (IOException e6) {
                e42 = e6;
                dataOutputStream = dos;
                fileInputStream2 = fileInputStream;
                throw e42;
            } catch (Throwable th5) {
                th = th5;
                dataOutputStream = dos;
                fileInputStream2 = fileInputStream;
                if (fileInputStream2 != null) {
                    fileInputStream2.close();
                }
                if (dataOutputStream != null) {
                    dataOutputStream.close();
                }
                if (rd != null) {
                    rd.close();
                }
                throw th;
            }
        } catch (IOException e7) {
            e42 = e7;
            throw e42;
        } catch (Throwable th6) {
            e2 = th6;
            throw new IOException(e2);
        }
    }

    public static int getActiveNetworkType(Context context) {
        try {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService("connectivity");
            if (cm == null) {
                return -1;
            }
            try {
                NetworkInfo info = cm.getActiveNetworkInfo();
                if (info != null) {
                    return info.getType();
                }
                return -1;
            } catch (Exception e) {
                return -1;
            }
        } catch (Exception e2) {
            return -1;
        }
    }

    public static String getActiveNetworkName(Context context) {
        String defaultValue = "null";
        try {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService("connectivity");
            if (cm == null) {
                return defaultValue;
            }
            try {
                NetworkInfo info = cm.getActiveNetworkInfo();
                if (info == null) {
                    return defaultValue;
                }
                if (TextUtils.isEmpty(info.getSubtypeName())) {
                    return info.getTypeName();
                }
                return String.format("%s-%s", new Object[]{info.getTypeName(), info.getSubtypeName()});
            } catch (Exception e) {
                return defaultValue;
            }
        } catch (Exception e2) {
            return defaultValue;
        }
    }

    public static boolean isWifi(Context context) {
        return getActiveNetworkType(context) == 1;
    }

    public static boolean isCmwap(Context context) {
        if (!"CN".equalsIgnoreCase(((TelephonyManager) context.getSystemService("phone")).getSimCountryIso())) {
            return false;
        }
        try {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService("connectivity");
            if (cm == null) {
                return false;
            }
            try {
                NetworkInfo info = cm.getActiveNetworkInfo();
                if (info == null) {
                    return false;
                }
                String extraInfo = info.getExtraInfo();
                if (TextUtils.isEmpty(extraInfo) || extraInfo.length() < 3 || extraInfo.contains("ctwap")) {
                    return false;
                }
                return extraInfo.regionMatches(true, extraInfo.length() - 3, "wap", 0, 3);
            } catch (Exception e) {
                return false;
            }
        } catch (Exception e2) {
            return false;
        }
    }

    public static boolean isCtwap(Context context) {
        if (!"CN".equalsIgnoreCase(((TelephonyManager) context.getSystemService("phone")).getSimCountryIso())) {
            return false;
        }
        try {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService("connectivity");
            if (cm == null) {
                return false;
            }
            try {
                NetworkInfo info = cm.getActiveNetworkInfo();
                if (info == null) {
                    return false;
                }
                String extraInfo = info.getExtraInfo();
                if (TextUtils.isEmpty(extraInfo) || extraInfo.length() < 3 || !extraInfo.contains("ctwap")) {
                    return false;
                }
                return true;
            } catch (Exception e) {
                return false;
            }
        } catch (Exception e2) {
            return false;
        }
    }

    public static HttpURLConnection getHttpUrlConnection(Context context, URL url) throws IOException {
        if (isCtwap(context)) {
            return (HttpURLConnection) url.openConnection(new Proxy(Type.HTTP, new InetSocketAddress("10.0.0.200", CMWAP_PORT)));
        }
        if (!isCmwap(context)) {
            return (HttpURLConnection) url.openConnection();
        }
        HttpURLConnection conn = (HttpURLConnection) new URL(getCMWapUrl(url)).openConnection();
        conn.addRequestProperty(CMWAP_HEADER_HOST_KEY, url.getHost());
        return conn;
    }

    public static String getCMWapUrl(URL oriUrl) {
        StringBuilder gatewayBuilder = new StringBuilder();
        gatewayBuilder.append(oriUrl.getProtocol()).append("://").append(CMWAP_GATEWAY).append(oriUrl.getPath());
        if (!TextUtils.isEmpty(oriUrl.getQuery())) {
            gatewayBuilder.append("?").append(oriUrl.getQuery());
        }
        return gatewayBuilder.toString();
    }
}
