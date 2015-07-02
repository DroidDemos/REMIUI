package com.google.android.gms.location.reporting;

import android.accounts.Account;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.kl;
import com.google.android.gms.internal.pu;

public class UploadRequest implements SafeParcelable {
    public static final e CREATOR;
    private final Account CS;
    private final String axM;
    private final long axN;
    private final long axO;
    private final long axP;
    private final String axQ;
    private final int mVersionCode;

    static {
        CREATOR = new e();
    }

    public UploadRequest(int versionCode, Account account, String reason, long durationMillis, long movingLatencyMillis, long stationaryLatencyMillis, String appSpecificKey) {
        this.mVersionCode = versionCode;
        this.CS = account;
        this.axM = reason;
        this.axN = durationMillis;
        this.axO = movingLatencyMillis;
        this.axP = stationaryLatencyMillis;
        this.axQ = appSpecificKey;
    }

    public int describeContents() {
        e eVar = CREATOR;
        return 0;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UploadRequest)) {
            return false;
        }
        UploadRequest uploadRequest = (UploadRequest) o;
        return this.CS.equals(uploadRequest.CS) && this.axM.equals(uploadRequest.axM) && kl.equal(Long.valueOf(this.axN), Long.valueOf(uploadRequest.axN)) && this.axO == uploadRequest.axO && this.axP == uploadRequest.axP && kl.equal(this.axQ, uploadRequest.axQ);
    }

    public Account getAccount() {
        return this.CS;
    }

    public String getAppSpecificKey() {
        return this.axQ;
    }

    public long getDurationMillis() {
        return this.axN;
    }

    public long getMovingLatencyMillis() {
        return this.axO;
    }

    public String getReason() {
        return this.axM;
    }

    public long getStationaryLatencyMillis() {
        return this.axP;
    }

    public int getVersionCode() {
        return this.mVersionCode;
    }

    public int hashCode() {
        return kl.hashCode(this.CS, this.axM, Long.valueOf(this.axN), Long.valueOf(this.axO), Long.valueOf(this.axP), this.axQ);
    }

    public String toString() {
        return "UploadRequest{mVersionCode=" + this.mVersionCode + ", mAccount=" + pu.a(this.CS) + ", mReason='" + this.axM + '\'' + ", mDurationMillis=" + this.axN + ", mMovingLatencyMillis=" + this.axO + ", mStationaryLatencyMillis=" + this.axP + ", mAppSpecificKey='" + this.axQ + '\'' + '}';
    }

    public void writeToParcel(Parcel out, int flags) {
        e eVar = CREATOR;
        e.a(this, out, flags);
    }
}
