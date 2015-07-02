package com.google.android.wallet.instrumentmanager.pub.analytics.events;

import com.google.android.wallet.instrumentmanager.pub.analytics.CreditCardEntryAction;

public final class InstrumentManagerBackgroundEvent {
    public final int attempts;
    public final int backgroundEventType;
    public final long clientLatencyMs;
    public final CreditCardEntryAction creditCardEntryAction;
    public final String exceptionType;
    public final byte[] integratorLogToken;
    public final int resultCode;
    public final long serverLatencyMs;

    public InstrumentManagerBackgroundEvent(int backgroundEventType, int resultCode, String exceptionType, long clientLatencyMs, long serverLatencyMs, int attempts, byte[] integratorLogToken) {
        this.backgroundEventType = backgroundEventType;
        this.resultCode = resultCode;
        this.exceptionType = exceptionType;
        this.clientLatencyMs = clientLatencyMs;
        this.serverLatencyMs = serverLatencyMs;
        this.attempts = attempts;
        this.integratorLogToken = integratorLogToken;
        this.creditCardEntryAction = null;
    }

    public InstrumentManagerBackgroundEvent(CreditCardEntryAction creditCardEntryAction, byte[] integratorLogToken) {
        this.backgroundEventType = 770;
        this.resultCode = 0;
        this.exceptionType = null;
        this.clientLatencyMs = -1;
        this.serverLatencyMs = -1;
        this.integratorLogToken = integratorLogToken;
        this.creditCardEntryAction = creditCardEntryAction;
        this.attempts = 0;
    }
}
