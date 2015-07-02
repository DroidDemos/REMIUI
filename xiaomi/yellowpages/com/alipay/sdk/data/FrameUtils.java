package com.alipay.sdk.data;

import android.text.TextUtils;
import com.alipay.mobilesecuritysdk.constant.ConfigConstant;
import com.alipay.sdk.cons.GlobalConstants;
import com.alipay.sdk.cons.GlobalDefine;
import com.alipay.sdk.cons.MiniDefine;
import com.alipay.sdk.exception.UnZipException;
import com.alipay.sdk.sys.GlobalContext;
import com.alipay.sdk.tid.TidInfo;
import com.alipay.sdk.util.DeviceInfo;
import com.alipay.sdk.util.JsonUtils;
import com.alipay.sdk.util.LogUtils;
import com.alipay.sdk.util.Utils;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.zip.GZIPInputStream;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.message.BasicHeader;
import org.json.JSONObject;

public class FrameUtils {
    private static final String a = "Msp-Param";

    public static Request a(InteractionData interactionData, String str, JSONObject jSONObject, boolean z) {
        GlobalContext a = GlobalContext.a();
        TidInfo d = TidInfo.d();
        JSONObject a2 = JsonUtils.a(null, jSONObject);
        try {
            a2.put(MiniDefine.ak, d.a());
            a2.put(GlobalDefine.b, a.c().a(d));
            a2.put(GlobalDefine.e, Utils.b(a.b()));
            a2.put(GlobalDefine.f, Utils.a(a.b()));
            a2.put(GlobalDefine.d, str);
            a2.put(GlobalDefine.m, "2014052600006128");
            a2.put(GlobalDefine.l, a.k());
            a2.put(GlobalDefine.p, TidInfo.f());
        } catch (Object e) {
            LogUtils.a(e);
        }
        Request a3 = a(a2, z);
        if (a3 != null) {
            a(interactionData, a3, str);
        }
        return a3;
    }

    private static Request a(JSONObject jSONObject, boolean z) {
        Envelope envelope = new Envelope();
        envelope.b(GlobalConstants.b);
        envelope.c("com.alipay.mobilecashier");
        envelope.d("/cashier/main");
        envelope.e("4.0.2");
        if (jSONObject == null) {
            return null;
        }
        Request request = new Request(envelope, jSONObject, null);
        request.a(z);
        return request;
    }

    private static void a(InteractionData interactionData, Request request, String str) {
        Object obj = null;
        if (!TextUtils.isEmpty(str)) {
            String[] split = str.split("&");
            if (split.length != 0) {
                Object obj2 = null;
                Object obj3 = null;
                Object obj4 = null;
                for (String str2 : split) {
                    if (TextUtils.isEmpty(obj4)) {
                        obj4 = a(str2);
                    }
                    if (TextUtils.isEmpty(obj3)) {
                        obj3 = b(str2);
                    }
                    if (TextUtils.isEmpty(obj2)) {
                        obj2 = c(str2);
                    }
                    if (TextUtils.isEmpty(obj)) {
                        obj = e(str2);
                    }
                }
                StringBuilder stringBuilder = new StringBuilder();
                if (!TextUtils.isEmpty(obj4)) {
                    stringBuilder.append("biz_type=" + obj4 + ";");
                }
                if (!TextUtils.isEmpty(obj3)) {
                    stringBuilder.append("biz_no=" + obj3 + ";");
                }
                if (!TextUtils.isEmpty(obj2)) {
                    stringBuilder.append("trade_no=" + obj2 + ";");
                }
                if (!TextUtils.isEmpty(obj)) {
                    stringBuilder.append("app_userid=" + obj + ";");
                }
                if (stringBuilder.length() != 0) {
                    String stringBuilder2 = stringBuilder.toString();
                    if (stringBuilder2.endsWith(";")) {
                        stringBuilder2 = stringBuilder2.substring(0, stringBuilder2.length() - 1);
                    }
                    interactionData.a(new Header[]{new BasicHeader(a, stringBuilder2)});
                    request.a(interactionData);
                }
            }
        }
    }

    private static String a(String str) {
        if (str.contains("biz_type")) {
            return d(str);
        }
        return null;
    }

    private static String b(String str) {
        if (str.contains("biz_no")) {
            return d(str);
        }
        return null;
    }

    private static String c(String str) {
        if (!str.contains("trade_no") || str.startsWith("out_trade_no")) {
            return null;
        }
        return d(str);
    }

    private static String d(String str) {
        String[] split = str.split("=");
        if (split.length <= 1) {
            return null;
        }
        String str2 = split[1];
        if (str2.contains("\"")) {
            return str2.replaceAll("\"", ConfigConstant.WIRELESS_FILENAME);
        }
        return str2;
    }

    private static String e(String str) {
        if (str.contains("app_userid")) {
            return d(str);
        }
        return null;
    }

    public static void a(InteractionData interactionData, HttpResponse httpResponse) {
        Header[] headers = httpResponse.getHeaders(a);
        if (interactionData != null && headers.length > 0) {
            interactionData.a(headers);
        }
    }

    public static byte[] a(byte[] bArr) {
        try {
            InputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
            GZIPInputStream gZIPInputStream = new GZIPInputStream(byteArrayInputStream);
            byte[] bArr2 = new byte[4096];
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            while (true) {
                int read = gZIPInputStream.read(bArr2, 0, bArr2.length);
                if (read != -1) {
                    byteArrayOutputStream.write(bArr2, 0, read);
                } else {
                    bArr2 = byteArrayOutputStream.toByteArray();
                    byteArrayOutputStream.flush();
                    byteArrayOutputStream.close();
                    gZIPInputStream.close();
                    byteArrayInputStream.close();
                    return bArr2;
                }
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            throw new UnZipException("UnsupportedEncodingException");
        } catch (IOException e2) {
            e2.printStackTrace();
            throw new UnZipException("IOException");
        }
    }

    public static Request a() {
        Envelope envelope = new Envelope();
        envelope.b(GlobalConstants.b);
        envelope.c("com.alipay.mobilecashier");
        envelope.d("/device/findAccount");
        envelope.e("3.0.0");
        GlobalContext a = GlobalContext.a();
        TidInfo d = TidInfo.d();
        JSONObject jSONObject = new JSONObject();
        try {
            if (TextUtils.isEmpty(d.a())) {
                d.b(TidInfo.f());
            } else {
                jSONObject.put(MiniDefine.ak, d.a());
            }
            jSONObject.put(GlobalDefine.l, a.k());
            jSONObject.put(GlobalDefine.m, "2014052600006128");
            jSONObject.put(GlobalDefine.p, TidInfo.f());
            jSONObject.put("imei", DeviceInfo.a(a.b()).c());
            jSONObject.put("imsi", DeviceInfo.a(a.b()).b());
        } catch (Object e) {
            LogUtils.a(e);
        }
        return new Request(envelope, jSONObject, null);
    }
}
