package com.google.android.wallet.instrumentmanager.api.http;

import android.os.SystemClock;
import com.android.volley.Request;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;

public abstract class BackgroundEventRequest<T> extends Request<T> {
    private long mEndTimeMs;
    private final Listener<T> mResponseListener;
    private final long mStartTimeMs;

    public abstract int getBackgroundEventReceivedType();

    public BackgroundEventRequest(int method, String url, Listener<T> responseListener, ErrorListener errorListener) {
        super(method, url, errorListener);
        this.mResponseListener = responseListener;
        this.mStartTimeMs = SystemClock.elapsedRealtime();
    }

    public void deliverResponse(T response) {
        this.mEndTimeMs = SystemClock.elapsedRealtime();
        this.mResponseListener.onResponse(response);
    }

    public void deliverError(VolleyError error) {
        this.mEndTimeMs = SystemClock.elapsedRealtime();
        super.deliverError(error);
    }

    public long getClientLatencyMs() {
        return this.mEndTimeMs - this.mStartTimeMs;
    }
}
