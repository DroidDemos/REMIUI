package com.google.android.gms.location.reporting;

import android.accounts.Account;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.pu;

public class GmmSettings implements SafeParcelable {
    public static final a CREATOR;
    private final Account CS;
    private final long axB;
    private final boolean axC;
    private final int mVersionCode;

    static {
        CREATOR = new a();
    }

    public GmmSettings(int versionCode, long readMillis, Account account, boolean reportingSelected) {
        this.mVersionCode = versionCode;
        this.axB = readMillis;
        this.CS = account;
        this.axC = reportingSelected;
    }

    public int describeContents() {
        a aVar = CREATOR;
        return 0;
    }

    public boolean equals(Object o) {
        if (!(o instanceof GmmSettings)) {
            return false;
        }
        GmmSettings gmmSettings = (GmmSettings) o;
        if (this.axB == gmmSettings.axB && this.axC == gmmSettings.axC && this.mVersionCode == gmmSettings.mVersionCode && this.CS == null) {
            return gmmSettings.CS == null;
        } else {
            return this.CS.equals(gmmSettings.CS);
        }
    }

    public Account getAccount() {
        return this.CS;
    }

    public long getReadMillis() {
        return this.axB;
    }

    int getVersionCode() {
        return this.mVersionCode;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((this.CS != null ? this.CS.hashCode() : 0) + (((this.mVersionCode * 31) + ((int) (this.axB ^ (this.axB >>> 32)))) * 31)) * 31;
        if (this.axC) {
            i = 1;
        }
        return hashCode + i;
    }

    public boolean isReportingSelected() {
        return this.axC;
    }

    public String toString() {
        return "GmmSettings{mVersionCode=" + this.mVersionCode + ", mValueReadMillis=" + this.axB + ", mAccount=" + pu.a(this.CS) + ", mReportingSelected=" + this.axC + '}';
    }

    public void writeToParcel(Parcel out, int flags) {
        a aVar = CREATOR;
        a.a(this, out, flags);
    }
}
