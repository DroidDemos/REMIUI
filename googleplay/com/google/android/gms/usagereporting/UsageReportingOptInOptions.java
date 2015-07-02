package com.google.android.gms.usagereporting;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class UsageReportingOptInOptions implements SafeParcelable {
    public static final a CREATOR;
    int aSR;
    final int mVersionCode;

    static {
        CREATOR = new a();
    }

    UsageReportingOptInOptions(int versionCode, int optInUsageReporting) {
        this.mVersionCode = versionCode;
        this.aSR = optInUsageReporting;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        a.a(this, parcel, flags);
    }
}
