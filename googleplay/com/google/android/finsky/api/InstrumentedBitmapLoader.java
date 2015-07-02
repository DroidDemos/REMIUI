package com.google.android.finsky.api;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.net.NetworkInfo;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.google.android.finsky.analytics.FinskyEventLog;
import com.google.android.finsky.experiments.Experiments;
import com.google.android.play.image.BitmapLoader;
import com.google.android.play.image.BitmapLoader.DebugImageRequest;
import com.google.android.play.image.TentativeGcRunner;

public class InstrumentedBitmapLoader extends BitmapLoader {
    private final FinskyEventLog mEventLogger;
    private final boolean mIsDfeRequestLogEnabled;
    private NetworkStateProvider mNetworkStateProvider;

    public class InstrumentedDebugImageRequest extends DebugImageRequest {
        private FinskyEventLog mEventLogger;
        private boolean mIsDfeRequestLogEnabled;
        private NetworkStateProvider mNetworkStateProvider;
        private long mNetworkTimeMs;
        private int mResponseBodySizeBytes;
        private NetworkInfo mStartNetworkInfo;

        public InstrumentedDebugImageRequest(boolean isDfeRequestLogEnabled, FinskyEventLog eventLogger, NetworkStateProvider networkStateProvider, String url, int maxWidth, int maxHeight, Config decodeConfig, Listener<Bitmap> bitmapListener, ErrorListener errorListener) {
            super(url, maxWidth, maxHeight, decodeConfig, bitmapListener, errorListener);
            this.mIsDfeRequestLogEnabled = isDfeRequestLogEnabled;
            if (this.mIsDfeRequestLogEnabled) {
                this.mEventLogger = eventLogger;
                this.mNetworkStateProvider = networkStateProvider;
                this.mStartNetworkInfo = networkStateProvider.getCurrentNetworkInfo();
            }
        }

        protected Response<Bitmap> parseNetworkResponse(NetworkResponse response) {
            if (this.mIsDfeRequestLogEnabled) {
                this.mNetworkTimeMs = response.networkTimeMs;
                this.mResponseBodySizeBytes = response.data.length;
            }
            return super.parseNetworkResponse(response);
        }

        public void deliverError(VolleyError error) {
            super.deliverError(error);
            if (this.mIsDfeRequestLogEnabled) {
                this.mNetworkTimeMs = error.getNetworkTimeMs();
                logNetworkEvent(false, error);
            }
        }

        protected void deliverResponse(Bitmap response) {
            super.deliverResponse(response);
            boolean wasCacheHit = this.mNetworkTimeMs <= 0;
            if (this.mIsDfeRequestLogEnabled && !wasCacheHit) {
                logNetworkEvent(true, null);
            }
        }

        private void logNetworkEvent(boolean wasSuccessful, VolleyError volleyError) {
            this.mEventLogger.logRpcReport(getUrl(), this.mNetworkTimeMs, getServerLatencyMs(), getRetryPolicy().getCurrentRetryCount() + 1, getRetryPolicy().getCurrentTimeout(), getRetryPolicy() instanceof DefaultRetryPolicy ? ((DefaultRetryPolicy) getRetryPolicy()).getBackoffMultiplier() : 0.0f, wasSuccessful, volleyError, this.mStartNetworkInfo, this.mNetworkStateProvider.getCurrentNetworkInfo(), this.mResponseBodySizeBytes);
        }

        private long getServerLatencyMs() {
            return 0;
        }
    }

    public InstrumentedBitmapLoader(Experiments experiments, FinskyEventLog eventLogger, NetworkStateProvider networkStateProvider, RequestQueue queue, int screenWidthPx, int screenHeightPx, TentativeGcRunner tentativeGcRunner) {
        super(queue, screenWidthPx, screenHeightPx, tentativeGcRunner);
        this.mIsDfeRequestLogEnabled = experiments.isEnabled("cl:ENABLE_DFE_REQUEST_LOGGING");
        this.mEventLogger = eventLogger;
        this.mNetworkStateProvider = networkStateProvider;
    }

    protected DebugImageRequest createImageRequest(String url, int requestWidth, int requestHeight, Config decodeConfig, Listener<Bitmap> bitmapListener, ErrorListener errorListener) {
        return new InstrumentedDebugImageRequest(this.mIsDfeRequestLogEnabled, this.mEventLogger, this.mNetworkStateProvider, url, requestWidth, requestHeight, decodeConfig, bitmapListener, errorListener);
    }
}
