package com.google.android.wallet.instrumentmanager.pub.analytics;

import android.util.Log;
import com.google.android.wallet.instrumentmanager.pub.analytics.events.InstrumentManagerBackgroundEvent;
import com.google.android.wallet.instrumentmanager.pub.analytics.events.InstrumentManagerClickEvent;
import com.google.android.wallet.instrumentmanager.pub.analytics.events.InstrumentManagerImpressionEvent;

public final class InstrumentManagerAnalyticsEventDispatcher {
    private static InstrumentManagerAnalyticsEventListener sListener;

    public static void setEventListener(InstrumentManagerAnalyticsEventListener listener) {
        sListener = listener;
    }

    public static void sendBackgroundEvent(InstrumentManagerBackgroundEvent e) {
        if (sListener != null) {
            sListener.onBackgroundEvent(e);
        } else if (Log.isLoggable("ImAnalyticsDispatcher", 2)) {
            Log.d("ImAnalyticsDispatcher", "No listener found for sending background event of type " + e.backgroundEventType);
        }
    }

    public static void sendClickEvent(InstrumentManagerClickEvent e) {
        if (sListener != null) {
            sListener.onClickEvent(e);
        } else if (Log.isLoggable("ImAnalyticsDispatcher", 2)) {
            Log.d("ImAnalyticsDispatcher", "No listener found for sending click event from the clicked element " + ((InstrumentManagerUiElement) e.clickPath.get(0)).elementId);
        }
    }

    public static void sendImpressionEvent(InstrumentManagerImpressionEvent e) {
        if (sListener != null) {
            sListener.onImpressionEvent(e);
        } else if (Log.isLoggable("ImAnalyticsDispatcher", 2)) {
            Log.d("ImAnalyticsDispatcher", "No listener found for sending the following impression event " + e.toString());
        }
    }
}
