package com.google.android.finsky.api;

import android.os.Handler;
import android.os.Looper;
import com.android.volley.Cache;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Request.Priority;
import com.android.volley.Response;

public class DfeClearCacheRequest extends Request<Object> {
    private final Cache mCache;
    private final String mCacheKey;
    private final Runnable mCallback;
    private final boolean mFullExpire;

    public DfeClearCacheRequest(Cache cache, String key, boolean fullExpire, Runnable callback) {
        super(0, null, null);
        this.mCache = cache;
        this.mCacheKey = key;
        this.mFullExpire = fullExpire;
        this.mCallback = callback;
    }

    public boolean isCanceled() {
        this.mCache.invalidate(this.mCacheKey, this.mFullExpire);
        if (this.mCallback != null) {
            new Handler(Looper.getMainLooper()).postAtFrontOfQueue(this.mCallback);
        }
        return true;
    }

    public Priority getPriority() {
        return Priority.IMMEDIATE;
    }

    protected Response<Object> parseNetworkResponse(NetworkResponse response) {
        return null;
    }

    protected void deliverResponse(Object response) {
    }
}
