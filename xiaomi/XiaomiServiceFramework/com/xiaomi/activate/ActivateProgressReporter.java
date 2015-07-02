package com.xiaomi.activate;

import android.content.Context;
import com.xiaomi.accountsdk.utils.CloudCoder;
import com.xiaomi.activate.ActivateLiveReportService.LiveReportInfo;
import com.xiaomi.activate.ActivateLiveReportService.LiveReportInfo.ErrorReason;
import com.xiaomi.activate.ActivateUploadDiagnosisService.DiagnosisInfo;
import com.xiaomi.activate.ActivateUploadDiagnosisService.DiagnosisInfo.Category;
import com.xiaomi.miui.analyticstracker.AnalyticsTracker;

public class ActivateProgressReporter {
    AnalyticsTracker mAnalytics;
    DiagnosisInfo mDiagnosisInfo;
    LiveReportInfo mLiveReportInfo;

    void init(Context context, int simIndex) {
        this.mAnalytics = AnalyticsTracker.getInstance();
        this.mAnalytics.startSession(context);
        this.mLiveReportInfo = new LiveReportInfo();
        this.mDiagnosisInfo = new DiagnosisInfo();
    }

    void destroy(Context context, int simIndex) {
        if (this.mLiveReportInfo.isValidErrorInfo()) {
            ActivateLiveReportService.reportActivationFailure(context, this.mLiveReportInfo);
        }
        if (this.mDiagnosisInfo.isValidErrorInfo()) {
            ActivateUploadDiagnosisService.saveDiagnosisInfo(simIndex, this.mDiagnosisInfo);
        }
        this.mDiagnosisInfo = null;
        this.mLiveReportInfo = null;
        if (this.mAnalytics != null) {
            this.mAnalytics.endSession();
            this.mAnalytics = null;
        }
    }

    public void trackEvent(String eventId) {
        AnalyticsHelper.trackEvent(this.mAnalytics, eventId);
    }

    public void trackEvent(String eventId, Object obj) {
        AnalyticsHelper.trackEvent(this.mAnalytics, eventId, obj);
    }

    public void trackTimedEvent(String eventId) {
        AnalyticsHelper.trackTimedEvent(this.mAnalytics, eventId);
    }

    public void endTimedEvent(String eventId) {
        AnalyticsHelper.endTimedEvent(this.mAnalytics, eventId);
    }

    public void setLiveReportErrorReason(String reason) {
        this.mLiveReportInfo.setErrorReason(ErrorReason.GET_VKEY_FAIL);
    }

    public void setLiveReportDetails(String details) {
        this.mLiveReportInfo.setDetails(details);
    }

    public void setDiagnosisInfoCategory(int category) {
        this.mDiagnosisInfo.setCategory(Category.FAIL_SEND_SMS);
    }

    public boolean hasDiagnosisInfoCategory() {
        return this.mDiagnosisInfo.hasCategory();
    }

    public void setSimOperatorPattern(String pattern) {
        this.mLiveReportInfo.setSimOperatorPattern(pattern);
        this.mDiagnosisInfo.setHashedOperatorPattern(CloudCoder.hashDeviceInfo(pattern));
    }

    public void setGateway(String gateway) {
        this.mLiveReportInfo.setGateway(gateway);
        this.mDiagnosisInfo.setGateway(gateway);
    }
}
