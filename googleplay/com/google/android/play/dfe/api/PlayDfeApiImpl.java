package com.google.android.play.dfe.api;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.google.android.finsky.protos.PlusProfile.PlusProfileResponse;
import com.google.android.play.utils.config.PlayG;

public class PlayDfeApiImpl implements PlayDfeApi {
    private static final float PLUS_PROFILE_BG_BACKOFF_MULT;
    private static final int PLUS_PROFILE_BG_MAX_RETRIES;
    private static final int PLUS_PROFILE_BG_TIMEOUT_MS;
    private final PlayDfeApiContext mApiContext;
    private final RequestQueue mQueue;

    static {
        PLUS_PROFILE_BG_TIMEOUT_MS = ((Integer) PlayG.plusProfileBgTimeoutMs.get()).intValue();
        PLUS_PROFILE_BG_MAX_RETRIES = ((Integer) PlayG.plusProfileBgMaxRetries.get()).intValue();
        PLUS_PROFILE_BG_BACKOFF_MULT = ((Float) PlayG.plusProfileBgBackoffMult.get()).floatValue();
    }

    public PlayDfeApiImpl(RequestQueue queue, PlayDfeApiContext apiContext) {
        this.mQueue = queue;
        this.mApiContext = apiContext;
    }

    public Request<?> getPlusProfile(Listener<PlusProfileResponse> listener, ErrorListener errorListener, boolean isForegroundRequest) {
        DfeRequest<PlusProfileResponse> request = new DfeRequest(PLUS_PROFILE_URI.toString(), this.mApiContext, PlusProfileResponse.class, listener, errorListener);
        if (!isForegroundRequest) {
            request.setRetryPolicy(new DfeRetryPolicy(PLUS_PROFILE_BG_TIMEOUT_MS, PLUS_PROFILE_BG_MAX_RETRIES, PLUS_PROFILE_BG_BACKOFF_MULT, this.mApiContext));
        }
        return this.mQueue.add(request);
    }

    public void invalidatePlusProfile(boolean fullExpire) {
        this.mQueue.add(new DfeClearCacheRequest(this.mApiContext.getCache(), new DfeRequest(PLUS_PROFILE_URI.toString(), this.mApiContext, PlusProfileResponse.class, null, null).getCacheKey(), fullExpire, null));
    }
}
