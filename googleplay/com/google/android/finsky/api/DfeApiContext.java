package com.google.android.finsky.api;

import android.accounts.Account;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.android.vending.R;
import com.android.volley.AuthFailureError;
import com.android.volley.Cache;
import com.android.volley.toolbox.AndroidAuthenticator;
import com.google.android.finsky.analytics.FinskyEventLog;
import com.google.android.finsky.experiments.Experiments;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.Utils;
import com.google.android.gms.ads.identifier.AdIdProvider;
import com.google.android.volley.UrlTools;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class DfeApiContext {
    private AdIdProvider mAdIdProvider;
    private final AndroidAuthenticator mAuthenticator;
    private final Cache mCache;
    private final Context mContext;
    private final FinskyEventLog mEventLogger;
    private final Experiments mExperiments;
    private boolean mHasPerformedInitialTokenInvalidation;
    private final Map<String, String> mHeaders;
    private final boolean mIsDfeRequestLogEnabled;
    private String mLastAuthToken;
    private NetworkStateProvider mNetworkStateProvider;
    private final DfeNotificationManager mNotificationManager;

    public Context getContext() {
        return this.mContext;
    }

    public static DfeApiContext create(Context context, Cache cache, Experiments experiments, DfeNotificationManager dfeNotificationManager, String accountName, int contentFilterLevel, AdIdProvider adIdProvider, FinskyEventLog eventLogger) {
        Account findAccount = AccountHandler.findAccount(accountName, context);
        AndroidAuthenticator authenticator = new AndroidAuthenticator(context, account, (String) DfeApiConfig.authTokenType.get());
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
            return new DfeApiContext(context, authenticator, cache, experiments, dfeNotificationManager, pi.versionName, pi.versionCode, 3, Locale.getDefault(), ((TelephonyManager) context.getSystemService("phone")).getSimOperator(), (String) DfeApiConfig.clientId.get(), (String) DfeApiConfig.loggingId.get(), contentFilterLevel, adIdProvider, eventLogger);
        } catch (Throwable e) {
            throw new RuntimeException("Can't find our own package", e);
        }
    }

    public static DfeApiContext createNonAuthenticated(Context context, Cache cache) {
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
            return new DfeApiContext(context, null, cache, null, null, pi.versionName, pi.versionCode, 3, Locale.getDefault(), ((TelephonyManager) context.getSystemService("phone")).getSimOperator(), (String) DfeApiConfig.clientId.get(), (String) DfeApiConfig.loggingId.get(), 0, null, null);
        } catch (Throwable e) {
            throw new RuntimeException("Can't find our own package", e);
        }
    }

    protected DfeApiContext(Context context, AndroidAuthenticator authenticator, Cache cache, Experiments experiments, DfeNotificationManager notificationManager, String appVersionString, int appVersionCode, int apiVersion, Locale locale, String mccmnc, String clientId, String loggingId, int contentFilterLevel, AdIdProvider adIdProvider, FinskyEventLog eventLogger) {
        this.mHeaders = new HashMap();
        if (((Long) DfeApiConfig.androidId.get()).longValue() == 0) {
            FinskyLog.w("Unexpected android-id = 0", new Object[0]);
        }
        this.mContext = context;
        this.mAuthenticator = authenticator;
        this.mCache = cache;
        this.mNotificationManager = notificationManager;
        this.mExperiments = experiments;
        this.mAdIdProvider = adIdProvider;
        this.mHeaders.put("X-DFE-Device-Id", Long.toHexString(((Long) DfeApiConfig.androidId.get()).longValue()));
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
        this.mHeaders.put("User-Agent", makeUserAgentString(appVersionString, appVersionCode, apiVersion));
        this.mHeaders.put("X-DFE-Filter-Level", String.valueOf(contentFilterLevel));
        this.mEventLogger = eventLogger;
        this.mNetworkStateProvider = new NetworkStateInfo(this.mContext);
        boolean z = this.mExperiments != null && this.mExperiments.isEnabled("cl:ENABLE_DFE_REQUEST_LOGGING");
        this.mIsDfeRequestLogEnabled = z;
        checkUrlRules();
    }

    private void checkUrlRules() {
        String uriString = DfeApi.BASE_URI.toString();
        String rewritten = UrlTools.rewrite(this.mContext, uriString);
        if (rewritten == null) {
            throw new RuntimeException("BASE_URI blocked by UrlRules: " + uriString);
        }
        Utils.checkUrlIsSecure(rewritten);
    }

    private String makeUserAgentString(String appVersionString, int appVersionCode, int apiVersion) {
        int isWideScreen;
        String device = sanitizeHeaderValue(Build.DEVICE);
        String hardware = sanitizeHeaderValue(Build.HARDWARE);
        String product = sanitizeHeaderValue(Build.PRODUCT);
        String platformVersionRelease = sanitizeHeaderValue(VERSION.RELEASE);
        String model = sanitizeHeaderValue(Build.MODEL);
        String buildId = sanitizeHeaderValue(Build.ID);
        if (this.mContext.getResources().getBoolean(R.bool.use_wide_details_layout)) {
            isWideScreen = 1;
        } else {
            isWideScreen = 0;
        }
        return String.format(Locale.US, "Android-Finsky/%s (api=%d,versionCode=%d,sdk=%d,device=%s,hardware=%s,product=%s,platformVersionRelease=%s,model=%s,buildId=%s,isWideScreen=%d)", new Object[]{appVersionString, Integer.valueOf(apiVersion), Integer.valueOf(appVersionCode), Integer.valueOf(VERSION.SDK_INT), device, hardware, product, platformVersionRelease, model, buildId, Integer.valueOf(isWideScreen)});
    }

    static String sanitizeHeaderValue(String value) {
        return Uri.encode(value).replace("(", "").replace(")", "");
    }

    public Account getAccount() {
        return this.mAuthenticator == null ? null : this.mAuthenticator.getAccount();
    }

    public String getAccountName() {
        Account currentAccount = getAccount();
        return currentAccount == null ? null : currentAccount.name;
    }

    public Experiments getExperiments() {
        return this.mExperiments;
    }

    public String getPublicAndroidId() {
        return this.mAdIdProvider == null ? null : this.mAdIdProvider.getPublicAndroidId();
    }

    public String getAdId() {
        return this.mAdIdProvider == null ? null : this.mAdIdProvider.getAdId();
    }

    public Boolean isLimitAdTrackingEnabled() {
        return this.mAdIdProvider == null ? null : this.mAdIdProvider.isLimitAdTrackingEnabled();
    }

    public void invalidateAuthToken() {
        if (this.mLastAuthToken != null) {
            if (this.mAuthenticator != null) {
                this.mAuthenticator.invalidateAuthToken(this.mLastAuthToken);
            }
            this.mLastAuthToken = null;
        }
    }

    public Cache getCache() {
        return this.mCache;
    }

    public DfeNotificationManager getNotificationManager() {
        return this.mNotificationManager;
    }

    public synchronized Map<String, String> getHeaders() throws AuthFailureError {
        Map<String, String> headers;
        if (!this.mHasPerformedInitialTokenInvalidation) {
            invalidateAuthToken();
            this.mHasPerformedInitialTokenInvalidation = true;
        }
        headers = new HashMap();
        headers.putAll(this.mHeaders);
        if (this.mExperiments != null) {
            if (this.mExperiments.hasEnabledExperiments()) {
                headers.put("X-DFE-Enabled-Experiments", this.mExperiments.getEnabledExperimentsHeaderValue());
            }
            if (this.mExperiments.hasUnsupportedExperiments()) {
                headers.put("X-DFE-Unsupported-Experiments", this.mExperiments.getUnsupportedExperimentsHeaderValue());
            }
        }
        if (this.mAuthenticator != null) {
            this.mLastAuthToken = this.mAuthenticator.getAuthToken();
            headers.put("Authorization", "GoogleLogin auth=" + this.mLastAuthToken);
        }
        return headers;
    }

    public boolean isDfeRequestLogEnabled() {
        return this.mIsDfeRequestLogEnabled;
    }

    public FinskyEventLog getFinskyEventLogger() {
        return this.mEventLogger;
    }

    public NetworkInfo getCurrentNetworkInfo() {
        return this.mNetworkStateProvider.getCurrentNetworkInfo();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[DfeApiContext headers={");
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
