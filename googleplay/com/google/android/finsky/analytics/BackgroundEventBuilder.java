package com.google.android.finsky.analytics;

import android.text.TextUtils;
import com.google.android.finsky.analytics.PlayStore.AppData;
import com.google.android.finsky.analytics.PlayStore.AuthContext;
import com.google.android.finsky.analytics.PlayStore.CreditCardEntryAction;
import com.google.android.finsky.analytics.PlayStore.DeviceFeature;
import com.google.android.finsky.analytics.PlayStore.NlpRepairStatus;
import com.google.android.finsky.analytics.PlayStore.PlayStoreBackgroundActionEvent;
import com.google.android.finsky.analytics.PlayStore.PlayStoreRpcReport;
import com.google.android.finsky.analytics.PlayStore.PlayStoreSessionData;
import com.google.android.finsky.analytics.PlayStore.ReviewData;
import com.google.android.finsky.analytics.PlayStore.SearchSuggestionReport;
import com.google.android.finsky.analytics.PlayStore.WidgetEventData;
import com.google.android.finsky.analytics.PlayStore.WifiAutoUpdateAttempt;

public class BackgroundEventBuilder {
    private final PlayStoreBackgroundActionEvent event;

    public BackgroundEventBuilder(int type) {
        this.event = FinskyEventLog.obtainPlayStoreBackgroundActionEvent();
        this.event.type = type;
        this.event.hasType = true;
    }

    public PlayStoreBackgroundActionEvent build() {
        return this.event;
    }

    public BackgroundEventBuilder setDocument(String document) {
        if (!TextUtils.isEmpty(document)) {
            this.event.document = document;
            this.event.hasDocument = true;
        }
        return this;
    }

    public BackgroundEventBuilder setReason(String reason) {
        if (!TextUtils.isEmpty(reason)) {
            this.event.reason = reason;
            this.event.hasReason = true;
        }
        return this;
    }

    public BackgroundEventBuilder setErrorCode(int errorCode) {
        if (errorCode != 0) {
            this.event.errorCode = errorCode;
            this.event.hasErrorCode = true;
        }
        return this;
    }

    public BackgroundEventBuilder setExceptionType(String exceptionType) {
        if (!TextUtils.isEmpty(exceptionType)) {
            this.event.exceptionType = exceptionType;
            this.event.hasExceptionType = true;
        }
        return this;
    }

    public BackgroundEventBuilder setExceptionType(Throwable throwable) {
        if (throwable != null) {
            setExceptionType(throwable.getClass().getSimpleName());
        }
        return this;
    }

    public BackgroundEventBuilder setServerLogsCookie(byte[] serverLogsCookie) {
        if (serverLogsCookie != null) {
            this.event.serverLogsCookie = serverLogsCookie;
            this.event.hasServerLogsCookie = true;
        }
        return this;
    }

    public BackgroundEventBuilder setOfferType(int offerType) {
        if (offerType != 0) {
            this.event.offerType = offerType;
            this.event.hasOfferType = true;
        }
        return this;
    }

    public BackgroundEventBuilder setFromSetting(Integer fromSetting) {
        if (fromSetting != null) {
            this.event.fromSetting = fromSetting.intValue();
            this.event.hasFromSetting = true;
        }
        return this;
    }

    public BackgroundEventBuilder setToSetting(Integer toSetting) {
        if (toSetting != null) {
            this.event.toSetting = toSetting.intValue();
            this.event.hasToSetting = true;
        }
        return this;
    }

    public BackgroundEventBuilder setSessionInfo(PlayStoreSessionData sessionInfo) {
        this.event.sessionInfo = sessionInfo;
        return this;
    }

    public BackgroundEventBuilder setAppData(AppData appData) {
        this.event.appData = appData;
        return this;
    }

    public BackgroundEventBuilder setServerLatencyMs(long serverLatencyMs) {
        if (serverLatencyMs >= 0) {
            this.event.serverLatencyMs = serverLatencyMs;
            this.event.hasServerLatencyMs = true;
        }
        return this;
    }

    public BackgroundEventBuilder setClientLatencyMs(long clientLatencyMs) {
        if (clientLatencyMs >= 0) {
            this.event.clientLatencyMs = clientLatencyMs;
            this.event.hasClientLatencyMs = true;
        }
        return this;
    }

    public BackgroundEventBuilder setNlpRepairStatus(NlpRepairStatus nlpRepairStatus) {
        this.event.nlpRepairStatus = nlpRepairStatus;
        return this;
    }

    public BackgroundEventBuilder setOperationSuccess(boolean operationSuccess) {
        this.event.operationSuccess = operationSuccess;
        this.event.hasOperationSuccess = true;
        return this;
    }

    public BackgroundEventBuilder setHost(String host) {
        if (!TextUtils.isEmpty(host)) {
            this.event.host = host;
            this.event.hasHost = true;
        }
        return this;
    }

    public BackgroundEventBuilder setWidgetEventData(WidgetEventData widgetEventData) {
        this.event.widgetEventData = widgetEventData;
        return this;
    }

    public BackgroundEventBuilder setWifiAutoUpdateAttempt(WifiAutoUpdateAttempt wifiAutoUpdateAttempt) {
        this.event.wifiAutoUpdateAttempt = wifiAutoUpdateAttempt;
        return this;
    }

    public BackgroundEventBuilder setAttempts(int attempts) {
        if (attempts >= 0) {
            this.event.attempts = attempts;
            this.event.hasAttempts = true;
        }
        return this;
    }

    public BackgroundEventBuilder setOfferCheckoutFlowRequired(boolean offerCheckoutFlowRequired) {
        this.event.offerCheckoutFlowRequired = offerCheckoutFlowRequired;
        this.event.hasOfferCheckoutFlowRequired = true;
        return this;
    }

    public BackgroundEventBuilder setSearchSuggestionReport(SearchSuggestionReport searchSuggestionReport) {
        this.event.searchSuggestionReport = searchSuggestionReport;
        return this;
    }

    public BackgroundEventBuilder setCallingPackage(String callingPackage) {
        if (!TextUtils.isEmpty(callingPackage)) {
            this.event.callingPackage = callingPackage;
            this.event.hasCallingPackage = true;
        }
        return this;
    }

    public BackgroundEventBuilder setReviewData(ReviewData reviewData) {
        this.event.reviewData = reviewData;
        return this;
    }

    public BackgroundEventBuilder setAuthContext(AuthContext authContext) {
        this.event.authContext = authContext;
        return this;
    }

    public BackgroundEventBuilder setDeviceFeatures(DeviceFeature deviceFeature) {
        this.event.deviceFeature = deviceFeature;
        return this;
    }

    public BackgroundEventBuilder setRpcReport(PlayStoreRpcReport rpcReport) {
        this.event.rpcReport = rpcReport;
        return this;
    }

    public BackgroundEventBuilder setCreditCardEntryAction(CreditCardEntryAction action) {
        this.event.creditCardEntryAction = action;
        return this;
    }
}
