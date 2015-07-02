package com.xiaomi.c;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import com.alipay.sdk.cons.MiniDefine;
import com.miui.yellowpage.ui.bw;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import org.apache.http.NameValuePair;

public class c {
    public static final Pattern gf;
    public static final Pattern gg;
    public static final Pattern gh;

    static {
        gf = Pattern.compile("([^\\s;]+)(.*)");
        gg = Pattern.compile("(.*?charset\\s*=[^a-zA-Z0-9]*)([-a-zA-Z0-9]+)(.*)", 2);
        gh = Pattern.compile("(\\<\\?xml\\s+.*?encoding\\s*=[^a-zA-Z0-9]*)([-a-zA-Z0-9]+)(.*)", 2);
    }

    public static InputStream a(Context context, URL url, String str, String str2) {
        return a(context, url, str, str2, null, null);
    }

    public static InputStream a(Context context, URL url, String str, String str2, Map map, g gVar) {
        if (context == null) {
            throw new IllegalArgumentException("context");
        } else if (url == null) {
            throw new IllegalArgumentException("url");
        } else {
            try {
                HttpURLConnection.setFollowRedirects(true);
                HttpURLConnection b = b(context, url);
                b.setConnectTimeout(bw.FILE_CHOOSER_RESULT_CODE);
                b.setReadTimeout(15000);
                if (!TextUtils.isEmpty(str)) {
                    b.setRequestProperty("User-Agent", str);
                }
                if (str2 != null) {
                    b.setRequestProperty("Cookie", str2);
                }
                if (map != null) {
                    for (String str3 : map.keySet()) {
                        b.setRequestProperty(str3, (String) map.get(str3));
                    }
                }
                if (gVar != null) {
                    if (url.getProtocol().equals("http") || url.getProtocol().equals(MiniDefine.aQ)) {
                        gVar.a = b.getResponseCode();
                        if (gVar.iJ == null) {
                            gVar.iJ = new HashMap();
                        }
                        int i = 0;
                        while (true) {
                            Object headerFieldKey = b.getHeaderFieldKey(i);
                            CharSequence headerField = b.getHeaderField(i);
                            if (headerFieldKey == null && headerField == null) {
                                break;
                            }
                            if (!(TextUtils.isEmpty(headerFieldKey) || TextUtils.isEmpty(headerField))) {
                                gVar.iJ.put(headerFieldKey.toLowerCase(), headerField);
                            }
                            i++;
                        }
                    }
                }
                return new d(b.getInputStream());
            } catch (IOException e) {
                throw e;
            } catch (Throwable th) {
                IOException iOException = new IOException(th);
            }
        }
    }

    public static String a(Context context, String str, List list) {
        return a(context, str, list, null, null, null);
    }

    public static String a(Context context, String str, List list, Map map, String str2, String str3) {
        int i = 0;
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("url");
        }
        try {
            HttpURLConnection b = b(context, new URL(str));
            b.setConnectTimeout(bw.FILE_CHOOSER_RESULT_CODE);
            b.setReadTimeout(15000);
            b.setRequestMethod("POST");
            if (!TextUtils.isEmpty(str2)) {
                b.setRequestProperty("User-Agent", str2);
            }
            if (str3 != null) {
                b.setRequestProperty("Cookie", str3);
            }
            String b2 = b(list);
            if (b2 == null) {
                throw new IllegalArgumentException("nameValuePairs");
            }
            b.setDoOutput(true);
            byte[] bytes = b2.getBytes();
            b.getOutputStream().write(bytes, 0, bytes.length);
            b.getOutputStream().flush();
            b.getOutputStream().close();
            Log.d("com.xiaomi.common.Network", "Http POST Response Code: " + b.getResponseCode());
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new d(b.getInputStream())));
            StringBuffer stringBuffer = new StringBuffer();
            String property = System.getProperty("line.separator");
            for (b2 = bufferedReader.readLine(); b2 != null; b2 = bufferedReader.readLine()) {
                stringBuffer.append(b2);
                stringBuffer.append(property);
            }
            b2 = stringBuffer.toString();
            bufferedReader.close();
            if (map != null) {
                while (true) {
                    String headerFieldKey = b.getHeaderFieldKey(i);
                    String headerField = b.getHeaderField(i);
                    if (headerFieldKey == null && headerField == null) {
                        break;
                    }
                    map.put(headerFieldKey, headerField);
                    i = (i + 1) + 1;
                }
            }
            return b2;
        } catch (IOException e) {
            throw e;
        } catch (Throwable th) {
            IOException iOException = new IOException(th);
        }
    }

    public static String a(Context context, URL url) {
        return a(context, url, null, "UTF-8", null);
    }

    public static String a(Context context, URL url, String str, String str2, String str3) {
        InputStream inputStream = null;
        try {
            StringBuilder stringBuilder = new StringBuilder(1024);
            inputStream = a(context, url, str, str3);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, str2), 1024);
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    break;
                }
                stringBuilder.append(readLine);
                stringBuilder.append("\r\n");
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    Log.e("com.xiaomi.common.Network", "Failed to close responseStream" + e.toString());
                }
            }
            return stringBuilder.toString();
        } catch (IOException e2) {
            throw e2;
        } catch (Throwable th) {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e3) {
                    Log.e("com.xiaomi.common.Network", "Failed to close responseStream" + e3.toString());
                }
            }
        }
    }

    public static String a(URL url) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(url.getProtocol()).append("://").append("10.0.0.172").append(url.getPath());
        if (!TextUtils.isEmpty(url.getQuery())) {
            stringBuilder.append("?").append(url.getQuery());
        }
        return stringBuilder.toString();
    }

    public static boolean a(Context context) {
        if (!"CN".equalsIgnoreCase(((TelephonyManager) context.getSystemService("phone")).getSimCountryIso())) {
            return false;
        }
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            if (connectivityManager == null) {
                return false;
            }
            try {
                NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
                if (activeNetworkInfo == null) {
                    return false;
                }
                String extraInfo = activeNetworkInfo.getExtraInfo();
                return (TextUtils.isEmpty(extraInfo) || extraInfo.length() < 3 || extraInfo.contains("ctwap")) ? false : extraInfo.regionMatches(true, extraInfo.length() - 3, "wap", 0, 3);
            } catch (Exception e) {
                return false;
            }
        } catch (Exception e2) {
            return false;
        }
    }

    public static String b(List list) {
        StringBuffer stringBuffer = new StringBuffer();
        for (NameValuePair nameValuePair : list) {
            try {
                if (nameValuePair.getValue() != null) {
                    stringBuffer.append(URLEncoder.encode(nameValuePair.getName(), "UTF-8"));
                    stringBuffer.append("=");
                    stringBuffer.append(URLEncoder.encode(nameValuePair.getValue(), "UTF-8"));
                    stringBuffer.append("&");
                }
            } catch (UnsupportedEncodingException e) {
                Log.d("com.xiaomi.common.Network", "Failed to convert from param list to string: " + e.toString());
                Log.d("com.xiaomi.common.Network", "pair: " + nameValuePair.toString());
                return null;
            }
        }
        return (stringBuffer.length() > 0 ? stringBuffer.deleteCharAt(stringBuffer.length() - 1) : stringBuffer).toString();
    }

    public static HttpURLConnection b(Context context, URL url) {
        if (b(context)) {
            return (HttpURLConnection) url.openConnection(new Proxy(Type.HTTP, new InetSocketAddress("10.0.0.200", 80)));
        }
        if (!a(context)) {
            return (HttpURLConnection) url.openConnection();
        }
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(a(url)).openConnection();
        httpURLConnection.addRequestProperty("X-Online-Host", url.getHost());
        return httpURLConnection;
    }

    public static boolean b(Context context) {
        if (!"CN".equalsIgnoreCase(((TelephonyManager) context.getSystemService("phone")).getSimCountryIso())) {
            return false;
        }
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            if (connectivityManager == null) {
                return false;
            }
            try {
                NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
                if (activeNetworkInfo == null) {
                    return false;
                }
                String extraInfo = activeNetworkInfo.getExtraInfo();
                return (TextUtils.isEmpty(extraInfo) || extraInfo.length() < 3) ? false : extraInfo.contains("ctwap");
            } catch (Exception e) {
                return false;
            }
        } catch (Exception e2) {
            return false;
        }
    }
}
