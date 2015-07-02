package com.google.android.gms.drive;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class DrivePreferences implements SafeParcelable {
    public static final Creator<DrivePreferences> CREATOR;
    final boolean Yl;
    final int mVersionCode;

    static {
        CREATOR = new c();
    }

    DrivePreferences(int versionCode, boolean syncOverWifiOnly) {
        this.mVersionCode = versionCode;
        this.Yl = syncOverWifiOnly;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        c.a(this, parcel, flags);
    }
}
