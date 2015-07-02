package com.miui.yellowpage.utils;

import android.content.Context;
import android.text.TextUtils;
import com.alipay.mobilesecuritysdk.constant.ConfigConstant;
import com.miui.yellowpage.base.request.HttpRequest;
import com.miui.yellowpage.base.utils.HostManager;
import com.miui.yellowpage.base.utils.Log;
import com.miui.yellowpage.base.utils.Network;
import com.miui.yellowpage.base.utils.Preference;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ServiceMonitor */
public class G {
    public static boolean b(Context context, JSONObject jSONObject) {
        boolean z = true;
        if (jSONObject == null) {
            return false;
        }
        try {
            if (jSONObject.has("webMonitor")) {
                int parseInt = Integer.parseInt(jSONObject.getString("webMonitor"));
                Log.d("ServiceMonitor", "set webMonitor value as " + parseInt);
                Preference.setBoolean(context, "pref_monitor_third_party_service", parseInt != 0);
            }
        } catch (Exception e) {
            e.printStackTrace();
            z = false;
        }
        return z;
    }

    public static boolean ac(Context context) {
        return Preference.getBoolean(context, "pref_monitor_third_party_service", false);
    }

    public static void G(Context context, String str) {
        int i;
        HttpURLConnection httpURLConnection;
        Exception exception;
        Throwable th;
        HttpURLConnection httpURLConnection2 = null;
        if (context != null && !TextUtils.isEmpty(str)) {
            Object obj;
            int i2 = -1;
            try {
                HttpURLConnection httpURLConnection3 = (HttpURLConnection) new URL(str).openConnection();
                try {
                    httpURLConnection3.setRequestMethod("HEAD");
                    httpURLConnection3.setRequestProperty("Accept-Encoding", ConfigConstant.WIRELESS_FILENAME);
                    httpURLConnection3.connect();
                    i2 = httpURLConnection3.getResponseCode();
                    Log.d("ServiceMonitor", "response code:" + i2);
                    if (httpURLConnection3 != null) {
                        httpURLConnection3.disconnect();
                        obj = null;
                        i = i2;
                    } else {
                        obj = null;
                        i = i2;
                    }
                } catch (Exception e) {
                    Exception exception2 = e;
                    i = i2;
                    httpURLConnection = httpURLConnection3;
                    exception = exception2;
                    try {
                        exception.printStackTrace();
                        obj = exception.toString();
                        if (httpURLConnection != null) {
                            httpURLConnection.disconnect();
                        }
                        if (i / 100 != 4) {
                        }
                        a(context, str, i, obj);
                    } catch (Throwable th2) {
                        th = th2;
                        httpURLConnection2 = httpURLConnection;
                        if (httpURLConnection2 != null) {
                            httpURLConnection2.disconnect();
                        }
                        throw th;
                    }
                } catch (Throwable th3) {
                    Throwable th4 = th3;
                    httpURLConnection2 = httpURLConnection3;
                    th = th4;
                    if (httpURLConnection2 != null) {
                        httpURLConnection2.disconnect();
                    }
                    throw th;
                }
            } catch (Exception e2) {
                exception = e2;
                i = -1;
                httpURLConnection = null;
                exception.printStackTrace();
                obj = exception.toString();
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
                if (i / 100 != 4) {
                }
                a(context, str, i, obj);
            } catch (Throwable th5) {
                th = th5;
                if (httpURLConnection2 != null) {
                    httpURLConnection2.disconnect();
                }
                throw th;
            }
            if (i / 100 != 4 || i / 100 == 5 || !TextUtils.isEmpty(obj)) {
                a(context, str, i, obj);
            }
        }
    }

    private static void a(Context context, String str, int i, String str2) {
        HttpRequest httpRequest = new HttpRequest(context, HostManager.getThirdPartyServiceMonitorUrl(), 0);
        httpRequest.setRequestCache(false);
        httpRequest.setRequireLocId(true);
        httpRequest.setRequestMethod("POST");
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("url", str);
            jSONObject.put("httpcode", String.valueOf(i));
            jSONObject.put("ne", String.valueOf(Network.getActiveNetworkType(context)));
            if (!TextUtils.isEmpty(str2)) {
                jSONObject.put("description", str2);
            }
            httpRequest.addParam("data", jSONObject.toString());
            try {
                Log.d("ServiceMonitor", "upload result:" + httpRequest.requestServer());
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }
}
