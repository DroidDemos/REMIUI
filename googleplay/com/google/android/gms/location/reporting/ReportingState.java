package com.google.android.gms.location.reporting;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.kl;
import com.google.android.gms.internal.pu;

public class ReportingState implements SafeParcelable {
    public static final d CREATOR;
    private final int axF;
    private final int axG;
    private final boolean axH;
    private final boolean axI;
    private final boolean axJ;
    private final int axK;
    private final Integer axL;
    private final int mVersionCode;

    public static final class Setting {
        public static int sanitize(int setting) {
            return com.google.android.gms.location.reporting.Reporting.Setting.sanitize(setting);
        }
    }

    static {
        CREATOR = new d();
    }

    public ReportingState(int versionCode, int reportingEnabled, int historyEnabled, boolean allowed, boolean active, boolean deferToMaps, int expectedOptInResult, Integer deviceTag) {
        this.mVersionCode = versionCode;
        this.axF = reportingEnabled;
        this.axG = historyEnabled;
        this.axH = allowed;
        this.axI = active;
        this.axJ = deferToMaps;
        this.axK = expectedOptInResult;
        this.axL = deviceTag;
    }

    public int describeContents() {
        d dVar = CREATOR;
        return 0;
    }

    public boolean equals(Object o) {
        if (!(o instanceof ReportingState)) {
            return false;
        }
        ReportingState reportingState = (ReportingState) o;
        return this.axF == reportingState.axF && this.axG == reportingState.axG && this.axH == reportingState.axH && this.axI == reportingState.axI && this.axJ == reportingState.axJ && this.axK == reportingState.axK && kl.equal(this.axL, reportingState.axL);
    }

    public int getExpectedOptInResult() {
        return OptInResult.sanitize(this.axK);
    }

    public int getHistoryEnabled() {
        return Setting.sanitize(this.axG);
    }

    public int getReportingEnabled() {
        return Setting.sanitize(this.axF);
    }

    int getVersionCode() {
        return this.mVersionCode;
    }

    public int hashCode() {
        return kl.hashCode(Integer.valueOf(this.axF), Integer.valueOf(this.axG), Boolean.valueOf(this.axH), Boolean.valueOf(this.axI), Boolean.valueOf(this.axJ), Integer.valueOf(this.axK), this.axL);
    }

    public boolean isActive() {
        return this.axI;
    }

    public boolean isAllowed() {
        return this.axH;
    }

    public boolean isDeferringToMaps() {
        return this.axJ;
    }

    Integer pJ() {
        return this.axL;
    }

    public String toString() {
        return "ReportingState{mReportingEnabled=" + this.axF + ", mHistoryEnabled=" + this.axG + ", mAllowed=" + this.axH + ", mActive=" + this.axI + ", mDefer=" + this.axJ + ", mExpectedOptInResult=" + this.axK + ", mVersionCode=" + this.mVersionCode + ", mDeviceTag=" + pu.d(this.axL) + '}';
    }

    public void writeToParcel(Parcel out, int flags) {
        d dVar = CREATOR;
        d.a(this, out, flags);
    }
}
