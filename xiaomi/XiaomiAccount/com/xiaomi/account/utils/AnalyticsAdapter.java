package com.xiaomi.account.utils;

import android.content.Context;
import com.xiaomi.miui.analyticstracker.AnalyticsTracker;
import java.util.Map;
import miui.analytics.Analytics;

public class AnalyticsAdapter extends AnalyticsTracker {
    private final Analytics mAnalytics;

    public AnalyticsAdapter(Analytics analytics) {
        this.mAnalytics = analytics;
    }

    public synchronized void startSession(Context context) {
        this.mAnalytics.startSession(context);
    }

    public synchronized void endSession() {
        this.mAnalytics.endSession();
    }

    public void trackError(String errorId, String message, String errorClass) {
        this.mAnalytics.trackError(errorId, message, errorClass);
    }

    public void trackEvent(String eventId) {
        this.mAnalytics.trackEvent(eventId);
    }

    public void trackEvent(String eventId, long value) {
        this.mAnalytics.trackEvent(eventId, value);
    }

    public void trackEvent(String eventId, Map parameters) {
        this.mAnalytics.trackEvent(eventId, parameters);
    }

    public void trackEvent(String eventId, Map<String, String> parameters, long value) {
        this.mAnalytics.trackEvent(eventId, parameters, value);
    }

    public void trackTimedEvent(String eventId, boolean timed) {
        this.mAnalytics.trackTimedEvent(eventId, timed);
    }

    public void trackEvent(String eventId, Object parameter) {
        this.mAnalytics.trackEvent(eventId, parameter);
    }

    public void trackTimedEvent(String eventId, boolean timed, long value) {
        this.mAnalytics.trackTimedEvent(eventId, timed, value);
    }

    public void trackTimedEvent(String eventId, Map<String, String> parameters, boolean timed) {
        this.mAnalytics.trackTimedEvent(eventId, parameters, timed);
    }

    public void trackTimedEvent(String eventId, Map<String, String> parameters, boolean timed, long value) {
        this.mAnalytics.trackTimedEvent(eventId, parameters, timed, value);
    }

    public void endTimedEvent(String eventId) {
        this.mAnalytics.endTimedEvent(eventId);
    }

    public void onTrackPageView() {
        this.mAnalytics.onTrackPageView();
    }

    public void setUseHttps(boolean useHttps) {
        throw new RuntimeException("Miui Analytics doesn't support this functionality");
    }
}
