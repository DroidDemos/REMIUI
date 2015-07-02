package com.xiaomi.f.a.d;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import com.alipay.mobilesecuritysdk.constant.ConfigConstant;
import com.miui.yellowpage.ui.bw;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;
import org.apache.http.NameValuePair;

public class a {
    public static final Pattern gf;
    public static final Pattern gg;
    public static final Pattern gh;

    static {
        gf = Pattern.compile("([^\\s;]+)(.*)");
        gg = Pattern.compile("(.*?charset\\s*=[^a-zA-Z0-9]*)([-a-zA-Z0-9]+)(.*)", 2);
        gh = Pattern.compile("(\\<\\?xml\\s+.*?encoding\\s*=[^a-zA-Z0-9]*)([-a-zA-Z0-9]+)(.*)", 2);
    }

    public static int Y(Context context) {
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            if (connectivityManager == null) {
                return -1;
            }
            try {
                NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
                return activeNetworkInfo == null ? -1 : activeNetworkInfo.getType();
            } catch (Exception e) {
                return -1;
            }
        } catch (Exception e2) {
            return -1;
        }
    }

    public static boolean Z(Context context) {
        return Y(context) >= 0;
    }

    public static String a(Context context, String str, List list) {
        return a(context, str, list, null, null, null);
    }

    public static String a(Context context, String str, List list, c cVar, String str2, String str3) {
        OutputStream outputStream;
        BufferedReader bufferedReader;
        IOException e;
        OutputStream outputStream2;
        Throwable th;
        Throwable th2;
        BufferedReader bufferedReader2;
        int i = 0;
        OutputStream outputStream3 = null;
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("url");
        }
        try {
            HttpURLConnection c = c(context, new URL(str));
            c.setConnectTimeout(bw.FILE_CHOOSER_RESULT_CODE);
            c.setReadTimeout(15000);
            c.setRequestMethod("POST");
            if (!TextUtils.isEmpty(str2)) {
                c.setRequestProperty("User-Agent", str2);
            }
            if (str3 != null) {
                c.setRequestProperty("Cookie", str3);
            }
            String b = b(list);
            if (b == null) {
                throw new IllegalArgumentException("nameValuePairs");
            }
            String headerField;
            c.setDoOutput(true);
            byte[] bytes = b.getBytes();
            outputStream = c.getOutputStream();
            try {
                outputStream.write(bytes, 0, bytes.length);
                outputStream.flush();
                outputStream.close();
                int responseCode = c.getResponseCode();
                Log.d("com.xiaomi.common.Network", "Http POST Response Code: " + responseCode);
                if (cVar != null) {
                    cVar.a = responseCode;
                    if (cVar.iJ == null) {
                        cVar.iJ = new HashMap();
                    }
                    while (true) {
                        b = c.getHeaderFieldKey(i);
                        headerField = c.getHeaderField(i);
                        if (b == null && headerField == null) {
                            break;
                        }
                        cVar.iJ.put(b, headerField);
                        i = (i + 1) + 1;
                    }
                }
                bufferedReader = new BufferedReader(new InputStreamReader(new b(c.getInputStream())));
            } catch (IOException e2) {
                e = e2;
                outputStream2 = outputStream;
                bufferedReader = null;
                outputStream3 = outputStream2;
                try {
                    throw e;
                } catch (Throwable th3) {
                    th = th3;
                    outputStream2 = outputStream3;
                    outputStream3 = bufferedReader;
                    outputStream = outputStream2;
                }
            } catch (Throwable th4) {
                th = th4;
                try {
                    throw new IOException(th);
                } catch (Throwable th5) {
                    th = th5;
                    outputStream2 = outputStream;
                    th2 = th;
                    bufferedReader2 = outputStream3;
                    outputStream3 = outputStream2;
                    if (outputStream3 != null) {
                        try {
                            outputStream3.close();
                        } catch (Throwable th6) {
                            Log.e("com.xiaomi.common.Network", "error while closing strean", th6);
                            throw th2;
                        }
                    }
                    if (bufferedReader2 != null) {
                        bufferedReader2.close();
                    }
                    throw th2;
                }
            }
            try {
                String readLine;
                StringBuffer stringBuffer = new StringBuffer();
                headerField = System.getProperty("line.separator");
                for (readLine = bufferedReader.readLine(); readLine != null; readLine = bufferedReader.readLine()) {
                    stringBuffer.append(readLine);
                    stringBuffer.append(headerField);
                }
                readLine = stringBuffer.toString();
                bufferedReader.close();
                return readLine;
            } catch (IOException e3) {
                e = e3;
                throw e;
            } catch (Throwable th62) {
                Throwable th7 = th62;
                bufferedReader2 = bufferedReader;
                th2 = th7;
                if (outputStream3 != null) {
                    outputStream3.close();
                }
                if (bufferedReader2 != null) {
                    bufferedReader2.close();
                }
                throw th2;
            }
        } catch (IOException e4) {
            e = e4;
            bufferedReader = null;
        } catch (Throwable th622) {
            th2 = th622;
            bufferedReader2 = null;
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
                return (TextUtils.isEmpty(extraInfo) || extraInfo.length() < 3 || extraInfo.contains("ctwap")) ? false : extraInfo.regionMatches(true, extraInfo.length() - 3, "wap", 0, 3);
            } catch (Exception e) {
                return false;
            }
        } catch (Exception e2) {
            return false;
        }
    }

    public static HttpURLConnection c(Context context, URL url) {
        if (c(context)) {
            return (HttpURLConnection) url.openConnection(new Proxy(Type.HTTP, new InetSocketAddress("10.0.0.200", 80)));
        }
        if (!b(context)) {
            return (HttpURLConnection) url.openConnection();
        }
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(a(url)).openConnection();
        httpURLConnection.addRequestProperty("X-Online-Host", url.getHost());
        return httpURLConnection;
    }

    public static boolean c(Context context) {
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

    public static boolean e(Context context) {
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
                return 1 == activeNetworkInfo.getType();
            } catch (Exception e) {
                return false;
            }
        } catch (Exception e2) {
            return false;
        }
    }

    public static String f(Context context) {
        if (e(context)) {
            return ConfigConstant.JSON_SECTION_WIFI;
        }
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            if (connectivityManager == null) {
                return ConfigConstant.WIRELESS_FILENAME;
            }
            try {
                NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
                return activeNetworkInfo == null ? ConfigConstant.WIRELESS_FILENAME : (activeNetworkInfo.getTypeName() + "-" + activeNetworkInfo.getSubtypeName() + "-" + activeNetworkInfo.getExtraInfo()).toLowerCase();
            } catch (Exception e) {
                return ConfigConstant.WIRELESS_FILENAME;
            }
        } catch (Exception e2) {
            return ConfigConstant.WIRELESS_FILENAME;
        }
    }
}
