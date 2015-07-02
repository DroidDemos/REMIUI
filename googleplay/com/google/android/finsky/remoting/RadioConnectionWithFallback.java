package com.google.android.finsky.remoting;

public class RadioConnectionWithFallback implements RadioConnection {
    private final RadioConnection mFallback;
    private final RadioConnection mPrimary;
    private boolean mUseFallback;

    public RadioConnectionWithFallback(RadioConnection primary, RadioConnection fallback) {
        this.mPrimary = primary;
        this.mFallback = fallback;
        this.mUseFallback = false;
    }

    public void start() throws RadioConnectionException {
        try {
            this.mPrimary.start();
        } catch (RadioConnectionException e) {
            this.mUseFallback = true;
            this.mFallback.start();
        }
    }

    public void stop() throws RadioConnectionException {
        if (this.mUseFallback) {
            this.mFallback.stop();
        } else {
            this.mPrimary.stop();
        }
    }

    public void ensureRouteToHost(String url) throws RadioConnectionException {
        if (this.mUseFallback) {
            this.mFallback.ensureRouteToHost(url);
        } else {
            this.mPrimary.ensureRouteToHost(url);
        }
    }
}
