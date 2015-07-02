package com.google.android.wallet.instrumentmanager.pub.analytics;

import com.google.android.wallet.instrumentmanager.pub.analytics.events.InstrumentManagerBackgroundEvent;
import com.google.android.wallet.instrumentmanager.pub.analytics.events.InstrumentManagerClickEvent;
import com.google.android.wallet.instrumentmanager.pub.analytics.events.InstrumentManagerImpressionEvent;

public interface InstrumentManagerAnalyticsEventListener {
    void onBackgroundEvent(InstrumentManagerBackgroundEvent instrumentManagerBackgroundEvent);

    void onClickEvent(InstrumentManagerClickEvent instrumentManagerClickEvent);

    void onImpressionEvent(InstrumentManagerImpressionEvent instrumentManagerImpressionEvent);
}
