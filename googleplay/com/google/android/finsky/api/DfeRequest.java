package com.google.android.finsky.api;

import android.net.NetworkInfo;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import com.android.volley.AuthFailureError;
import com.android.volley.Cache;
import com.android.volley.Cache.Entry;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.RetryPolicy;
import com.android.volley.ServerError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.android.finsky.protos.Notifications.Notification;
import com.google.android.finsky.protos.Response.Payload;
import com.google.android.finsky.protos.Response.ResponseWrapper;
import com.google.android.finsky.protos.ResponseMessages.PreFetch;
import com.google.android.finsky.protos.ResponseMessages.ServerCommands;
import com.google.android.finsky.protos.ResponseMessages.ServerMetadata;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.Utils;
import com.google.android.play.dfe.api.DfeResponseVerifier;
import com.google.android.play.dfe.api.DfeResponseVerifier.DfeResponseVerifierException;
import com.google.android.play.dfe.utils.NanoProtoHelper;
import com.google.protobuf.nano.InvalidProtocolBufferNanoException;
import com.google.protobuf.nano.MessageNano;
import com.google.protobuf.nano.MessageNanoPrinter;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.GZIPInputStream;

public class DfeRequest<T extends MessageNano> extends Request<ResponseWrapper> {
    private static final boolean DEBUG;
    private static final boolean PROTO_DEBUG;
    private boolean mAllowMultipleResponses;
    private final DfeApiContext mApiContext;
    private boolean mAvoidBulkCancel;
    private Map<String, String> mExtraHeaders;
    private boolean mIsDfeRequestLogEnabled;
    private Listener<T> mListener;
    private long mNetworkTimeMs;
    private int mResponseBodySizeBytes;
    private final Class<T> mResponseClass;
    private boolean mResponseDelivered;
    private DfeResponseVerifier mResponseVerifier;
    private long mServerLatencyMs;
    private NetworkInfo mStartNetworkInfo;

    static {
        DEBUG = FinskyLog.DEBUG;
        PROTO_DEBUG = Log.isLoggable("DfeProto", 2);
    }

    public DfeRequest(String url, DfeApiContext apiContext, Class<T> responseClass, Listener<T> listener, ErrorListener errorListener) {
        this(0, url, apiContext, responseClass, listener, errorListener);
    }

    public DfeRequest(int method, String url, DfeApiContext apiContext, Class<T> responseClass, Listener<T> listener, ErrorListener errorListener) {
        super(method, Uri.withAppendedPath(DfeApi.BASE_URI, url).toString(), errorListener);
        this.mAllowMultipleResponses = false;
        this.mServerLatencyMs = -1;
        this.mAvoidBulkCancel = false;
        if (TextUtils.isEmpty(url)) {
            FinskyLog.wtf("Empty DFE URL", new Object[0]);
        }
        setShouldCache(!((Boolean) DfeApiConfig.skipAllCaches.get()).booleanValue());
        setRetryPolicy(new DfeRetryPolicy(apiContext));
        this.mApiContext = apiContext;
        this.mListener = listener;
        this.mResponseClass = responseClass;
        this.mIsDfeRequestLogEnabled = this.mApiContext.isDfeRequestLogEnabled();
        if (this.mIsDfeRequestLogEnabled) {
            this.mStartNetworkInfo = this.mApiContext.getCurrentNetworkInfo();
        }
    }

    public void setAvoidBulkCancel() {
        this.mAvoidBulkCancel = true;
    }

    public boolean getAvoidBulkCancel() {
        return this.mAvoidBulkCancel;
    }

    public void setAllowMultipleResponses(boolean allow) {
        this.mAllowMultipleResponses = allow;
    }

    public void setRequireAuthenticatedResponse(DfeResponseVerifier responseVerifier) {
        this.mResponseVerifier = responseVerifier;
    }

    public String getUrl() {
        char c;
        char c2 = '&';
        String url = super.getUrl();
        String overrideCountry = (String) DfeApiConfig.ipCountryOverride.get();
        if (!TextUtils.isEmpty(overrideCountry)) {
            StringBuilder append = new StringBuilder().append(url);
            if (url.indexOf(63) != -1) {
                c = '&';
            } else {
                c = '?';
            }
            url = append.append(c).toString() + "ipCountryOverride=" + overrideCountry;
        }
        String mccmnc = (String) DfeApiConfig.mccMncOverride.get();
        if (!TextUtils.isEmpty(mccmnc)) {
            append = new StringBuilder().append(url);
            if (url.indexOf(63) != -1) {
                c = '&';
            } else {
                c = '?';
            }
            url = append.append(c).toString() + "mccmncOverride=" + mccmnc;
        }
        if (((Boolean) DfeApiConfig.skipAllCaches.get()).booleanValue()) {
            append = new StringBuilder().append(url);
            if (url.indexOf(63) != -1) {
                c = '&';
            } else {
                c = '?';
            }
            url = append.append(c).toString() + "skipCache=true";
        }
        if (((Boolean) DfeApiConfig.showStagingData.get()).booleanValue()) {
            append = new StringBuilder().append(url);
            if (url.indexOf(63) != -1) {
                c = '&';
            } else {
                c = '?';
            }
            url = append.append(c).toString() + "showStagingData=true";
        }
        if (!((Boolean) DfeApiConfig.prexDisabled.get()).booleanValue()) {
            return url;
        }
        StringBuilder append2 = new StringBuilder().append(url);
        if (url.indexOf(63) == -1) {
            c2 = '?';
        }
        return append2.append(c2).toString() + "p13n=false";
    }

    public void addExtraHeader(String header, String value) {
        if (this.mExtraHeaders == null) {
            this.mExtraHeaders = new HashMap();
        }
        this.mExtraHeaders.put(header, value);
    }

    public Map<String, String> getHeaders() throws AuthFailureError {
        Map<String, String> headers = this.mApiContext.getHeaders();
        if (this.mExtraHeaders != null) {
            headers.putAll(this.mExtraHeaders);
        }
        if (this.mResponseVerifier != null) {
            try {
                headers.put("X-DFE-Signature-Request", this.mResponseVerifier.getSignatureRequest());
            } catch (DfeResponseVerifierException e) {
                FinskyLog.d("Couldn't create signature request: %s", e);
                cancel();
            }
        }
        RetryPolicy retryPolicy = getRetryPolicy();
        String requestParamsValue = "timeoutMs=" + retryPolicy.getCurrentTimeout();
        int retryAttempt = retryPolicy.getCurrentRetryCount();
        if (retryAttempt > 0) {
            requestParamsValue = requestParamsValue + "; retryAttempt=" + retryAttempt;
        }
        headers.put("X-DFE-Request-Params", requestParamsValue);
        return headers;
    }

    public Response<ResponseWrapper> parseNetworkResponse(NetworkResponse response) {
        if (this.mIsDfeRequestLogEnabled && response.headers != null && response.headers.containsKey("X-DFE-Content-Length")) {
            this.mResponseBodySizeBytes = Integer.parseInt((String) response.headers.get("X-DFE-Content-Length"));
        }
        this.mNetworkTimeMs = response.networkTimeMs;
        if (DEBUG) {
            int contentLength = 0;
            if (response.headers != null && response.headers.containsKey("X-DFE-Content-Length")) {
                contentLength = Integer.parseInt((String) response.headers.get("X-DFE-Content-Length")) / 1024;
            }
            FinskyLog.v("Parsed response for url=[%s] contentLength=[%d KB]", getUrl(), Integer.valueOf(contentLength));
        }
        ResponseWrapper wrapper = parseWrapperAndVerifySignature(response, false);
        if (wrapper == null) {
            return Response.error(new ParseError(response));
        }
        if (PROTO_DEBUG) {
            logProtoResponse(wrapper);
        }
        Response<ResponseWrapper> error = handleServerCommands(wrapper);
        if (error != null) {
            return error;
        }
        Entry cacheEntry;
        if (wrapper.serverMetadata != null) {
            ServerMetadata metadata = wrapper.serverMetadata;
            if (metadata.hasLatencyMillis) {
                this.mServerLatencyMs = metadata.latencyMillis;
            }
        }
        handleNotifications(wrapper);
        if (this.mResponseVerifier != null) {
            cacheEntry = null;
        } else {
            cacheEntry = parseCacheHeaders(response);
        }
        if (cacheEntry != null) {
            stripForCache(wrapper, cacheEntry);
        }
        Response<ResponseWrapper> wrappedResponse = Response.success(wrapper, cacheEntry);
        FinskyLog.logTiming("DFE response %s", getUrl());
        return wrappedResponse;
    }

    private void logProtoResponse(ResponseWrapper wrapper) {
        String regexp = (String) DfeApiConfig.protoLogUrlRegexp.get();
        if (getUrl().matches(regexp)) {
            synchronized (MessageNanoPrinter.class) {
                Log.v("DfeProto", "Response for " + getUrl());
                for (String logLine : MessageNanoPrinter.print(wrapper).split("\n")) {
                    Log.v("DfeProto", "| " + logLine);
                }
            }
            return;
        }
        Log.v("DfeProto", "Url does not match regexp: url=" + getUrl() + " / regexp=" + regexp);
    }

    void stripForCache(ResponseWrapper wrapper, Entry rootEntry) {
        if (wrapper.preFetch.length >= 1 || wrapper.commands != null || wrapper.notification.length >= 1) {
            Cache cache = this.mApiContext.getCache();
            long now = System.currentTimeMillis();
            for (PreFetch prefetch : wrapper.preFetch) {
                Entry entry = new Entry();
                entry.data = prefetch.response;
                entry.etag = prefetch.etag;
                entry.serverDate = rootEntry.serverDate;
                entry.ttl = prefetch.ttl + now;
                entry.softTtl = prefetch.softTtl + now;
                cache.put(makeCacheKey(Uri.withAppendedPath(DfeApi.BASE_URI, prefetch.url).toString()), entry);
            }
            wrapper.preFetch = PreFetch.emptyArray();
            wrapper.commands = null;
            wrapper.notification = Notification.emptyArray();
            rootEntry.data = MessageNano.toByteArray(wrapper);
        }
    }

    private ResponseWrapper parseWrapperAndVerifySignature(NetworkResponse response, boolean manuallyUnzip) {
        try {
            String signatureResponse = getSignatureResponse(response);
            if (manuallyUnzip) {
                return parseWrapperAndVerifySignatureFromIs(new GZIPInputStream(new ByteArrayInputStream(response.data)), signatureResponse);
            }
            return parseWrapperAndVerifyFromBytes(response, signatureResponse);
        } catch (InvalidProtocolBufferNanoException e) {
            if (!manuallyUnzip) {
                return parseWrapperAndVerifySignature(response, true);
            }
            FinskyLog.d("Cannot parse response as ResponseWrapper proto.", new Object[0]);
            return null;
        } catch (IOException e2) {
            FinskyLog.w("IOException while manually unzipping request.", new Object[0]);
            return null;
        } catch (DfeResponseVerifierException e3) {
            addMarker("signature-verification-failed");
            FinskyLog.e("Could not verify request: %s, exception %s", this, e3);
            return null;
        }
    }

    private ResponseWrapper parseWrapperAndVerifyFromBytes(NetworkResponse response, String signatureResponse) throws InvalidProtocolBufferNanoException, DfeResponseVerifierException {
        ResponseWrapper parsedResponse = ResponseWrapper.parseFrom(response.data);
        if (this.mResponseVerifier != null) {
            this.mResponseVerifier.verify(response.data, signatureResponse);
            addMarker("signature-verification-succeeded");
        }
        return parsedResponse;
    }

    private ResponseWrapper parseWrapperAndVerifySignatureFromIs(InputStream in, String signatureResponse) throws IOException, DfeResponseVerifierException {
        byte[] uncompressedResponse = Utils.readBytes(in);
        ResponseWrapper result = ResponseWrapper.parseFrom(uncompressedResponse);
        if (this.mResponseVerifier != null) {
            this.mResponseVerifier.verify(uncompressedResponse, signatureResponse);
        }
        return result;
    }

    private String getSignatureResponse(NetworkResponse networkResponse) {
        return (String) networkResponse.headers.get("X-DFE-Signature-Response");
    }

    public static Entry parseCacheHeaders(NetworkResponse response) {
        Entry entry = HttpHeaderParser.parseCacheHeaders(response);
        if (entry == null) {
            return null;
        }
        long now = System.currentTimeMillis();
        try {
            String softTtlHeader = (String) response.headers.get("X-DFE-Soft-TTL");
            if (softTtlHeader != null) {
                entry.softTtl = Long.parseLong(softTtlHeader) + now;
            }
            String hardTtlHeader = (String) response.headers.get("X-DFE-Hard-TTL");
            if (hardTtlHeader != null) {
                entry.ttl = Long.parseLong(hardTtlHeader) + now;
            }
        } catch (NumberFormatException e) {
            FinskyLog.d("Invalid TTL: %s", response.headers);
            entry.softTtl = 0;
            entry.ttl = 0;
        }
        entry.ttl = Math.max(entry.ttl, entry.softTtl);
        return entry;
    }

    protected VolleyError parseNetworkError(VolleyError volleyError) {
        if (!(volleyError instanceof ServerError) || volleyError.networkResponse == null) {
            return volleyError;
        }
        ResponseWrapper wrapper = parseWrapperAndVerifySignature(volleyError.networkResponse, false);
        if (wrapper != null) {
            return handleServerCommands(wrapper).error;
        }
        return volleyError;
    }

    private Response<ResponseWrapper> handleServerCommands(ResponseWrapper wrapper) {
        if (wrapper.commands == null) {
            return null;
        }
        ServerCommands commands = wrapper.commands;
        if (commands.hasLogErrorStacktrace) {
            FinskyLog.d("%s", commands.logErrorStacktrace);
        }
        if (commands.clearCache) {
            this.mApiContext.getCache().clear();
        }
        if (commands.hasDisplayErrorMessage) {
            return Response.error(new DfeServerError(commands.displayErrorMessage));
        }
        return null;
    }

    public void handleNotifications(ResponseWrapper wrapper) {
        if (this.mApiContext.getNotificationManager() != null && wrapper.notification.length != 0) {
            for (Notification notification : wrapper.notification) {
                this.mApiContext.getNotificationManager().processNotification(notification);
            }
        }
    }

    public void deliverResponse(ResponseWrapper responseWrapper) {
        boolean wasCacheHit = false;
        try {
            T response = NanoProtoHelper.getParsedResponseFromWrapper(responseWrapper.payload, Payload.class, this.mResponseClass);
            if (response == null) {
                FinskyLog.e("Null parsed response for request=[%s]", this);
                deliverError(new VolleyError());
            } else if (this.mAllowMultipleResponses || !this.mResponseDelivered) {
                this.mListener.onResponse(response);
                this.mResponseDelivered = true;
                if (this.mNetworkTimeMs <= 0) {
                    wasCacheHit = true;
                }
                if (this.mIsDfeRequestLogEnabled && !wasCacheHit) {
                    logNetworkEvent(true, null);
                }
            } else {
                FinskyLog.d("Not delivering second response for request=[%s]", this);
            }
        } catch (Throwable e) {
            FinskyLog.e("Null wrapper parsed for request=[%s]", this);
            deliverError(new ParseError(e));
        }
    }

    private void logNetworkEvent(boolean wasSuccessful, VolleyError volleyError) {
        this.mApiContext.getFinskyEventLogger().logRpcReport(getUrl(), this.mNetworkTimeMs, getServerLatencyMs(), getRetryPolicy().getCurrentRetryCount() + 1, getRetryPolicy().getCurrentTimeout(), getRetryPolicy() instanceof DfeRetryPolicy ? ((DfeRetryPolicy) getRetryPolicy()).getBackoffMultiplier() : 0.0f, wasSuccessful, volleyError, this.mStartNetworkInfo, this.mApiContext.getCurrentNetworkInfo(), this.mResponseBodySizeBytes);
    }

    public void deliverError(VolleyError error) {
        if (this.mIsDfeRequestLogEnabled) {
            this.mNetworkTimeMs = error.getNetworkTimeMs();
            logNetworkEvent(false, error);
        }
        if (error instanceof AuthFailureError) {
            this.mApiContext.invalidateAuthToken();
        }
        if (this.mResponseDelivered) {
            FinskyLog.d("Not delivering error response for request=[%s], error=[%s] because response already delivered.", this, error);
            return;
        }
        super.deliverError(error);
    }

    private String makeCacheKey(String url) {
        return url + "/account=" + this.mApiContext.getAccountName();
    }

    public String getCacheKey() {
        return makeCacheKey(super.getUrl());
    }

    public long getServerLatencyMs() {
        return this.mServerLatencyMs;
    }
}
