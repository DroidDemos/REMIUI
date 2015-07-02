package com.alipay.sdk.net;

import android.content.Context;
import android.text.TextUtils;
import com.alipay.sdk.cons.MiniDefine;
import com.alipay.sdk.data.FrameUtils;
import com.alipay.sdk.data.InteractionData;
import com.alipay.sdk.data.Request;
import com.alipay.sdk.data.Response;
import com.alipay.sdk.encrypt.Base64;
import com.alipay.sdk.encrypt.MD5;
import com.alipay.sdk.encrypt.TriDes;
import com.alipay.sdk.exception.AppErrorException;
import com.alipay.sdk.exception.FailOperatingException;
import com.alipay.sdk.exception.NetErrorException;
import com.alipay.sdk.exception.UnZipException;
import com.alipay.sdk.protocol.FrameData;
import com.alipay.sdk.protocol.FrameFactoryManager;
import com.alipay.sdk.sys.GlobalContext;
import com.alipay.sdk.util.LogUtils;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.json.JSONException;
import org.json.JSONObject;

public class RequestWrapper {
    private int a;
    private InteractionData b;

    public RequestWrapper(InteractionData interactionData) {
        this.a = 0;
        this.b = interactionData;
    }

    public RequestWrapper() {
        this.a = 0;
    }

    public FrameData a(Context context, Request request, boolean z) {
        Response response = new Response();
        JSONObject a = a(context, request, response);
        if (a.optBoolean("gzip")) {
            JSONObject optJSONObject = a.optJSONObject(MiniDefine.d);
            if (optJSONObject != null && optJSONObject.has("quickpay")) {
                try {
                    byte[] a2 = FrameUtils.a(Base64.a(optJSONObject.optString("quickpay")));
                    if (TextUtils.equals(MD5.a(a2), a.optString(MiniDefine.aW))) {
                        a.put(MiniDefine.d, new JSONObject(new String(a2, "utf-8")));
                    } else {
                        throw new UnZipException("client md5  not equal server md5");
                    }
                } catch (UnsupportedEncodingException e) {
                    throw new UnZipException("unzip byte array unsupport encoding");
                } catch (UnZipException e2) {
                    throw e2;
                } catch (JSONException e3) {
                    throw new UnZipException("unzip string not jsonObject");
                }
            }
        }
        response.a(false);
        LogUtils.a("responsestring decoded " + a);
        FrameData frameData = new FrameData(request, response);
        frameData.a(a);
        if (z) {
            return frameData;
        }
        return FrameFactoryManager.a(frameData);
    }

    public JSONObject a(Context context, Request request, Response response) {
        String i = GlobalContext.i();
        try {
            String a = a(context, request.b(), request.a(i).toString(), request.d(), response);
            response.a(Calendar.getInstance().getTimeInMillis());
            if (request.e()) {
                return a(context, request, response, i, a);
            }
            return a(request, response, a);
        } catch (NetErrorException e) {
            throw e;
        } catch (FailOperatingException e2) {
            throw e2;
        } catch (AppErrorException e3) {
            throw e3;
        } catch (Object e4) {
            LogUtils.a(e4);
            throw new NetErrorException();
        }
    }

    protected String a(String str) {
        InputStream fileInputStream;
        Exception e;
        Throwable th;
        String str2 = null;
        try {
            fileInputStream = new FileInputStream(str);
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
                char[] cArr = new char[2048];
                StringBuilder stringBuilder = new StringBuilder();
                while (true) {
                    int read = bufferedReader.read(cArr);
                    if (read <= 0) {
                        break;
                    }
                    stringBuilder.append(cArr, 0, read);
                }
                bufferedReader.close();
                str2 = stringBuilder.toString();
                if (fileInputStream != null) {
                    try {
                        fileInputStream.close();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                }
            } catch (Exception e3) {
                e = e3;
                try {
                    e.printStackTrace();
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (IOException e22) {
                            e22.printStackTrace();
                        }
                    }
                    return str2;
                } catch (Throwable th2) {
                    th = th2;
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (IOException e222) {
                            e222.printStackTrace();
                        }
                    }
                    throw th;
                }
            }
        } catch (Exception e4) {
            e = e4;
            Object obj = str2;
            e.printStackTrace();
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            return str2;
        } catch (Throwable th3) {
            fileInputStream = str2;
            th = th3;
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            throw th;
        }
        return str2;
    }

    private JSONObject a(Context context, Request request, Response response, String str, String str2) {
        JSONObject a = a(str2, request, response);
        if (response.i() == Response.a && this.a < 3) {
            this.a++;
            return a(context, request, response);
        } else if (response.i() != 0) {
            throw new FailOperatingException(response.j());
        } else {
            this.a = 0;
            Object optString = a.optString("res_data");
            if (TextUtils.isEmpty(optString)) {
                throw new AppErrorException(getClass(), "response data is empty");
            }
            String b = TriDes.b(str, optString);
            LogUtils.c("respData:" + b);
            return new JSONObject(b);
        }
    }

    private JSONObject a(Request request, Response response, String str) {
        JSONObject a = a(str, request, response);
        LogUtils.c("respData:" + a.toString());
        return a;
    }

    private String a(Context context, String str, String str2, InteractionData interactionData, Response response) {
        try {
            HttpResponse a = RequestUtils.a(context, str, str2, interactionData);
            StatusLine statusLine = a.getStatusLine();
            response.a(statusLine.getStatusCode());
            response.a(statusLine.getReasonPhrase());
            FrameUtils.a(this.b, a);
            return RequestUtils.a(a);
        } catch (Exception e) {
            throw new NetErrorException();
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private org.json.JSONObject a(java.lang.String r5, com.alipay.sdk.data.Request r6, com.alipay.sdk.data.Response r7) {
        /*
        r4 = this;
        r3 = 1000; // 0x3e8 float:1.401E-42 double:4.94E-321;
        r2 = 503; // 0x1f7 float:7.05E-43 double:2.485E-321;
        r0 = new org.json.JSONObject;
        r0.<init>(r5);
        r1 = "data";
        r0 = r0.optJSONObject(r1);
        if (r0 == 0) goto L_0x006b;
    L_0x0011:
        r1 = "code";
        r1 = r0.optInt(r1, r2);
        r7.a(r1);
        r1 = "error_msg";
        r2 = "";
        r1 = r0.optString(r1, r2);
        r7.a(r1);
        r1 = "params";
        r0 = r0.optJSONObject(r1);
        if (r0 == 0) goto L_0x0063;
    L_0x002d:
        r1 = r7.i();
        if (r1 != r3) goto L_0x0036;
    L_0x0033:
        r4.a(r0);
    L_0x0036:
        r1 = new com.alipay.sdk.data.Envelope;
        r1.<init>();
        r2 = "next_api_name";
        r2 = r0.optString(r2);
        r1.d(r2);
        r2 = "next_api_version";
        r2 = r0.optString(r2);
        r1.e(r2);
        r2 = "next_namespace";
        r2 = r0.optString(r2);
        r1.c(r2);
        r2 = "next_request_url";
        r2 = r0.optString(r2);
        r1.b(r2);
        r7.a(r1);
    L_0x0062:
        return r0;
    L_0x0063:
        r0 = r7.i();
        if (r0 != r3) goto L_0x0069;
    L_0x0069:
        r0 = 0;
        goto L_0x0062;
    L_0x006b:
        r7.a(r2);
        r0 = "";
        r7.a(r0);
        goto L_0x0069;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.sdk.net.RequestWrapper.a(java.lang.String, com.alipay.sdk.data.Request, com.alipay.sdk.data.Response):org.json.JSONObject");
    }

    private void a(JSONObject jSONObject) {
        String optString = jSONObject.optString("public_key");
        if (!TextUtils.isEmpty(optString)) {
            GlobalContext.a().c().a(optString);
        }
    }
}
