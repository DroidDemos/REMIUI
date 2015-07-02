package com.google.android.gms.lockbox;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class LockboxOptInOptions implements SafeParcelable {
    public static final a CREATOR;
    int aya;
    int ayb;
    final int mVersionCode;

    static {
        CREATOR = new a();
    }

    LockboxOptInOptions(int versionCode, int optInWebAndAppHistory, int optInDeviceStateAndContent) {
        this.mVersionCode = versionCode;
        this.aya = optInWebAndAppHistory;
        this.ayb = optInDeviceStateAndContent;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        a.a(this, parcel, flags);
    }
}
