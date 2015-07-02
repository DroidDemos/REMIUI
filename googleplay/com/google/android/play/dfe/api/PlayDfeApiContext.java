package com.google.android.play.dfe.api;

import android.accounts.Account;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.android.volley.AuthFailureError;
import com.android.volley.Cache;
import com.android.volley.toolbox.AndroidAuthenticator;
import com.google.android.play.utils.PlayCommonLog;
import com.google.android.play.utils.PlayUtils;
import com.google.android.play.utils.config.PlayG;
import com.google.android.volley.UrlTools;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class PlayDfeApiContext {
    private final AndroidAuthenticator mAuthenticator;
    private final Cache mCache;
    private final Context mContext;
    private boolean mHasPerformedInitialTokenInvalidation;
    private final Map<String, String> mHeaders;
    private String mLastAuthToken;

    public static PlayDfeApiContext create(Context context, Cache cache, Account account) {
        AndroidAuthenticator authenticator = new AndroidAuthenticator(context, account, (String) PlayG.authTokenType.get());
        try {
            PackageManager pm = context.getPackageManager();
            String appPackageName = context.getPackageName();
            return new PlayDfeApiContext(context, authenticator, cache, appPackageName, pm.getPackageInfo(appPackageName, 0).versionName, 4, Locale.getDefault(), ((TelephonyManager) context.getSystemService("phone")).getSimOperator(), (String) PlayG.clientId.get(), (String) PlayG.loggingId.get());
        } catch (NameNotFoundException e) {
            throw new RuntimeException("Can't find our own package", e);
        }
    }

    protected PlayDfeApiContext(Context context, AndroidAuthenticator authenticator, Cache cache, String appPackageName, String appVersionString, int apiVersion, Locale locale, String mccmnc, String clientId, String loggingId) {
        this.mHeaders = new HashMap();
        this.mContext = context;
        this.mAuthenticator = authenticator;
        this.mCache = cache;
        this.mHeaders.put("X-DFE-Device-Id", Long.toHexString(((Long) PlayG.androidId.get()).longValue()));
        this.mHeaders.put("Accept-Language", locale.getLanguage() + "-" + locale.getCountry());
        if (!TextUtils.isEmpty(mccmnc)) {
            this.mHeaders.put("X-DFE-MCCMNC", mccmnc);
        }
        if (!TextUtils.isEmpty(clientId)) {
            this.mHeaders.put("X-DFE-Client-Id", clientId);
        }
        if (!TextUtils.isEmpty(clientId)) {
            this.mHeaders.put("X-DFE-Logging-Id", loggingId);
        }
        this.mHeaders.put("User-Agent", makeUserAgentString(appPackageName, appVersionString, apiVersion));
        checkUrlRules();
    }

    private void checkUrlRules() {
        String uriString = PlayDfeApi.BASE_URI.toString();
        String rewritten = UrlTools.rewrite(this.mContext, uriString);
        if (rewritten == null) {
            throw new RuntimeException("BASE_URI blocked by UrlRules: " + uriString);
        }
        checkUrlIsSecure(rewritten);
    }

    private static void checkUrlIsSecure(String url) {
        try {
            URL parsed = new URL(url);
            if (!parsed.getProtocol().toLowerCase().equals("https") && !parsed.getHost().toLowerCase().endsWith("corp.google.com") && !parsed.getHost().startsWith("192.168.0")) {
                if (parsed.getHost().startsWith("127.0.0") && PlayUtils.isTestDevice()) {
                    return;
                }
                throw new RuntimeException("Insecure URL: " + url);
            }
        } catch (MalformedURLException e) {
            PlayCommonLog.d("Cannot parse URL: " + url, new Object[0]);
        }
    }

    private String makeUserAgentString(String packageName, String versionString, int apiVersion) {
        String device = sanitizeHeaderValue(Build.DEVICE);
        String hardware = sanitizeHeaderValue(Build.HARDWARE);
        String product = sanitizeHeaderValue(Build.PRODUCT);
        return String.format(Locale.US, "Android-%s/%s (api=%d,sdk=%d,device=%s,hardware=%s,product=%s)", new Object[]{packageName, versionString, Integer.valueOf(apiVersion), Integer.valueOf(VERSION.SDK_INT), device, hardware, product});
    }

    static String sanitizeHeaderValue(String value) {
        return Uri.encode(value).replace("(", "").replace(")", "");
    }

    public Account getAccount() {
        return this.mAuthenticator.getAccount();
    }

    public String getAccountName() {
        Account currentAccount = getAccount();
        return currentAccount == null ? null : currentAccount.name;
    }

    public void invalidateAuthToken() {
        if (this.mLastAuthToken != null) {
            this.mAuthenticator.invalidateAuthToken(this.mLastAuthToken);
            this.mLastAuthToken = null;
        }
    }

    public Cache getCache() {
        return this.mCache;
    }

    public synchronized Map<String, String> getHeaders() throws AuthFailureError {
        Map<String, String> headers;
        if (!this.mHasPerformedInitialTokenInvalidation) {
            invalidateAuthToken();
            this.mHasPerformedInitialTokenInvalidation = true;
        }
        this.mLastAuthToken = this.mAuthenticator.getAuthToken();
        headers = new HashMap();
        headers.putAll(this.mHeaders);
        headers.put("Authorization", "GoogleLogin auth=" + this.mLastAuthToken);
        return headers;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[PlayDfeApiContext headers={");
        boolean first = true;
        for (String header : this.mHeaders.keySet()) {
            if (first) {
                first = false;
            } else {
                sb.append(", ");
            }
            sb.append(header).append(": ").append((String) this.mHeaders.get(header));
        }
        sb.append("}]");
        return sb.toString();
    }
}
