package com.google.android.vending.verifier.api;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import com.android.volley.Request;
import com.android.volley.Response.ErrorListener;
import com.google.android.finsky.FinskyApp;
import com.google.protobuf.nano.MessageNano;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public abstract class BaseVerificationRequest<T, U extends MessageNano> extends Request<T> {
    protected final U mRequest;

    public BaseVerificationRequest(String url, ErrorListener listener, U request) {
        super(1, url, listener);
        this.mRequest = request;
    }

    public Map<String, String> getHeaders() {
        Map<String, String> headerMap = new HashMap();
        headerMap.put("User-Agent", makeUserAgentString());
        headerMap.put("Connection", "close");
        return headerMap;
    }

    public String getBodyContentType() {
        return "application/x-protobuffer";
    }

    public byte[] getBody() {
        return MessageNano.toByteArray(this.mRequest);
    }

    private String makeUserAgentString() {
        Context context = FinskyApp.get();
        String device = sanitizeHeaderValue(Build.DEVICE);
        String hardware = sanitizeHeaderValue(Build.HARDWARE);
        String product = sanitizeHeaderValue(Build.PRODUCT);
        String buildType = sanitizeHeaderValue(Build.TYPE);
        String buildId = sanitizeHeaderValue(Build.ID);
        try {
            PackageInfo pi = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return String.format(Locale.US, "Android-Finsky/%s (versionCode=%d,sdk=%d,device=%s,hardware=%s,product=%s,build=%s:%s)", new Object[]{pi.versionName, Integer.valueOf(pi.versionCode), Integer.valueOf(VERSION.SDK_INT), device, hardware, product, buildId, buildType});
        } catch (NameNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    }

    private static String sanitizeHeaderValue(String value) {
        return Uri.encode(value).replace("(", "").replace(")", "");
    }
}
