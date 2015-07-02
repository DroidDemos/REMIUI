package com.xiaomi.push.service;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.os.Build;
import android.os.Build.VERSION;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.alipay.mobilesecuritysdk.deviceID.Profile;
import com.xiaomi.f.a.a.c;
import com.xiaomi.f.a.b.a;
import com.xiaomi.f.a.c.b;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

public class z {
    private static String b;
    private static String c;
    private static A vA;

    static {
        b = null;
        c = null;
    }

    public static A V(Context context) {
        A a = null;
        synchronized (z.class) {
            try {
                if (vA != null) {
                    a = vA;
                } else {
                    SharedPreferences sharedPreferences = context.getSharedPreferences("mipush_account", 0);
                    Object string = sharedPreferences.getString("uuid", null);
                    Object string2 = sharedPreferences.getString("token", null);
                    Object string3 = sharedPreferences.getString("security", null);
                    String string4 = sharedPreferences.getString("app_id", null);
                    String string5 = sharedPreferences.getString("app_token", null);
                    String string6 = sharedPreferences.getString("package_name", null);
                    Object string7 = sharedPreferences.getString("device_id", null);
                    if (!TextUtils.isEmpty(string7) && string7.startsWith("a-")) {
                        string7 = c(context);
                        sharedPreferences.edit().putString("device_id", string7).commit();
                    }
                    if (!(TextUtils.isEmpty(string) || TextUtils.isEmpty(string2) || TextUtils.isEmpty(string3))) {
                        CharSequence c = c(context);
                        if (TextUtils.isEmpty(c) || TextUtils.isEmpty(r7) || r7.equals(c)) {
                            vA = new A(string, string2, string3, string4, string5, string6);
                            a = vA;
                        } else {
                            b.c("erase the old account.");
                            W(context);
                        }
                    }
                }
            } catch (Throwable th) {
                Class cls = z.class;
            }
        }
        return a;
    }

    public static void W(Context context) {
        context.getSharedPreferences("mipush_account", 0).edit().clear().commit();
    }

    public static String a() {
        if (a.b()) {
            return "http://10.237.12.17:9085/pass/register";
        }
        return "https://" + (a.a() ? "sandbox.xmpush.xiaomi.com" : "register.xmpush.xiaomi.com") + "/pass/register";
    }

    private static void a(Context context, A a) {
        Editor edit = context.getSharedPreferences("mipush_account", 0).edit();
        edit.putString("uuid", a.a);
        edit.putString("security", a.c);
        edit.putString("token", a.b);
        edit.putString("app_id", a.d);
        edit.putString("package_name", a.f);
        edit.putString("app_token", a.e);
        edit.putString("device_id", c(context));
        edit.commit();
    }

    public static A b(Context context, String str, String str2, String str3) {
        A a = null;
        synchronized (z.class) {
            String str4;
            PackageInfo packageInfo;
            List arrayList = new ArrayList();
            arrayList.add(new BasicNameValuePair("devid", b(context)));
            String str5 = e(context) ? "1000271" : str2;
            try {
                String str6 = e(context) ? "420100086271" : str3;
                str4 = e(context) ? "com.xiaomi.xmsf" : str;
                arrayList.add(new BasicNameValuePair("appid", str5));
                arrayList.add(new BasicNameValuePair("apptoken", str6));
                packageInfo = context.getPackageManager().getPackageInfo(str4, 16384);
            } catch (Throwable e) {
                b.a(e);
                packageInfo = null;
            } catch (Throwable th) {
                Class cls = z.class;
            }
            arrayList.add(new BasicNameValuePair("appversion", packageInfo != null ? String.valueOf(packageInfo.versionCode) : Profile.devicever));
            arrayList.add(new BasicNameValuePair("sdkversion", "2"));
            arrayList.add(new BasicNameValuePair("packagename", str4));
            arrayList.add(new BasicNameValuePair("model", Build.MODEL));
            arrayList.add(new BasicNameValuePair("imei", f(context)));
            arrayList.add(new BasicNameValuePair("os", VERSION.RELEASE + "-" + VERSION.INCREMENTAL));
            String a2 = com.xiaomi.f.a.d.a.a(context, a(), arrayList);
            if (!TextUtils.isEmpty(a2)) {
                JSONObject jSONObject = new JSONObject(a2);
                if (jSONObject.getInt("code") == 0) {
                    JSONObject jSONObject2 = jSONObject.getJSONObject("data");
                    a = new A(jSONObject2.getString("userId") + "@xiaomi.com/an" + c.a(6), jSONObject2.getString("token"), jSONObject2.getString("ssecurity"), str5, str6, str4);
                    a(context, a);
                    vA = a;
                } else {
                    L.a(context, jSONObject.getInt("code"), jSONObject.optString("description"));
                    b.a(a2);
                }
            }
        }
        return a;
    }

    protected static String b(Context context) {
        String str = null;
        if (b == null) {
            String string;
            String f = f(context);
            try {
                string = Secure.getString(context.getContentResolver(), "android_id");
            } catch (Throwable e) {
                b.a(e);
                string = null;
            }
            if (VERSION.SDK_INT > 8) {
                str = Build.SERIAL;
            }
            b = "a-" + c.a(f + string + str);
        }
        return b;
    }

    public static String c(Context context) {
        String str;
        synchronized (z.class) {
            String string;
            try {
                if (c != null) {
                    str = c;
                } else {
                    string = Secure.getString(context.getContentResolver(), "android_id");
                    c = c.a(string + (VERSION.SDK_INT > 8 ? Build.SERIAL : null));
                    str = c;
                }
            } catch (Throwable e) {
                b.a(e);
                string = null;
            } catch (Throwable th) {
                Class cls = z.class;
            }
        }
        return str;
    }

    private static boolean e(Context context) {
        return context.getPackageName().equals("com.xiaomi.xmsf");
    }

    private static String f(Context context) {
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            String deviceId = telephonyManager.getDeviceId();
            int i = 10;
            while (deviceId == null) {
                int i2 = i - 1;
                if (i <= 0) {
                    break;
                }
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                }
                deviceId = telephonyManager.getDeviceId();
                i = i2;
            }
            return deviceId;
        } catch (Throwable e2) {
            b.a(e2);
            return null;
        }
    }
}
