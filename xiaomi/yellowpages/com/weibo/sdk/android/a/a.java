package com.weibo.sdk.android.a;

import android.os.Bundle;
import android.text.TextUtils;
import com.alipay.mobilesecuritysdk.constant.ConfigConstant;
import com.weibo.sdk.android.d;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: Utility */
public class a {
    private static char[] qZ;
    private static byte[] ra;

    static {
        qZ = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".toCharArray();
        ra = new byte[256];
    }

    public static String a(d dVar) {
        if (dVar == null) {
            return ConfigConstant.WIRELESS_FILENAME;
        }
        StringBuilder stringBuilder = new StringBuilder();
        Object obj = 1;
        for (int i = 0; i < dVar.size(); i++) {
            if (obj != null) {
                obj = null;
            } else {
                stringBuilder.append("&");
            }
            String J = dVar.J(i);
            if (dVar.getValue(J) == null) {
                com.b.a.a.a.a.i("encodeUrl", "key:" + J + " 's value is null");
            } else {
                stringBuilder.append(new StringBuilder(String.valueOf(URLEncoder.encode(dVar.J(i)))).append("=").append(URLEncoder.encode(dVar.getValue(i))).toString());
            }
            com.b.a.a.a.a.i("encodeUrl", stringBuilder.toString());
        }
        return stringBuilder.toString();
    }

    public static String b(d dVar) {
        int i = 0;
        if (dVar == null || c(dVar)) {
            return ConfigConstant.WIRELESS_FILENAME;
        }
        StringBuilder stringBuilder = new StringBuilder();
        int i2 = 0;
        while (i < dVar.size()) {
            String J = dVar.J(i);
            if (i2 != 0) {
                stringBuilder.append("&");
            }
            try {
                stringBuilder.append(URLEncoder.encode(J, "UTF-8")).append("=").append(URLEncoder.encode(dVar.getValue(J), "UTF-8"));
            } catch (UnsupportedEncodingException e) {
            }
            i2++;
            i++;
        }
        return stringBuilder.toString();
    }

    private static boolean c(d dVar) {
        if (dVar == null || dVar.size() == 0) {
            return true;
        }
        return false;
    }

    private static boolean a(File file, int i) {
        int i2 = 1;
        if (i < 1) {
            i = 5;
        }
        boolean z = false;
        if (file != null) {
            while (!z && i2 <= r4 && file.isFile() && file.exists()) {
                z = file.delete();
                if (!z) {
                    i2++;
                }
            }
        }
        return z;
    }

    private static void a(File file) {
        if (file != null && !file.exists() && !file.mkdirs()) {
            throw new RuntimeException("fail to make " + file.getAbsolutePath());
        }
    }

    private static void b(File file) {
        if (file != null && !d(file)) {
            throw new RuntimeException(file.getAbsolutePath() + " doesn't be created!");
        }
    }

    private static void c(File file) {
        if (file != null && file.exists() && !file.delete()) {
            throw new RuntimeException(file.getAbsolutePath() + " doesn't be deleted!");
        }
    }

    private static boolean d(File file) {
        boolean z = false;
        if (file != null) {
            f(file);
            if (file.exists()) {
                c(file);
            }
            try {
                z = file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return z;
    }

    private static boolean c(String str, int i) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return a(new File(str), i);
    }

    private static boolean ay(String str) {
        return c(str, 0);
    }

    private static boolean e(File file) {
        return file != null && file.exists();
    }

    private static boolean az(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return e(new File(str));
    }

    private static void f(File file) {
        if (file != null) {
            File parentFile = file.getParentFile();
            if (parentFile != null && !parentFile.exists()) {
                a(parentFile);
            }
        }
    }

    private static void g(File file) {
        if (file != null && !file.exists()) {
            f(file);
            b(file);
        }
    }

    private static void aA(String str) {
        if (str != null) {
            g(new File(str));
        }
    }

    public static Bundle aB(String str) {
        Bundle bundle = new Bundle();
        if (str != null && str.indexOf("{") >= 0) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                bundle.putString(ConfigConstant.LOG_JSON_STR_ERROR, jSONObject.optString(ConfigConstant.LOG_JSON_STR_ERROR));
                bundle.putString("error_code", jSONObject.optString("error_code"));
                bundle.putString("error_description", jSONObject.optString("error_description"));
            } catch (JSONException e) {
                bundle.putString(ConfigConstant.LOG_JSON_STR_ERROR, "JSONExceptionerror");
            }
        }
        return bundle;
    }
}
