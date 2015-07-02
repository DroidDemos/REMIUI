package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.drive.DrivePreferences;

public class SetDrivePreferencesRequest implements SafeParcelable {
    public static final Creator<SetDrivePreferencesRequest> CREATOR;
    final DrivePreferences aaE;
    final int mVersionCode;

    static {
        CREATOR = new bd();
    }

    SetDrivePreferencesRequest(int versionCode, DrivePreferences prefs) {
        this.mVersionCode = versionCode;
        this.aaE = prefs;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        bd.a(this, dest, flags);
    }
}
