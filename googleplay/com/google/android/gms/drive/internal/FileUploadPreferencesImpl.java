package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class FileUploadPreferencesImpl implements SafeParcelable {
    public static final Creator<FileUploadPreferencesImpl> CREATOR;
    int aaJ;
    int aaK;
    boolean aaL;
    final int mVersionCode;

    static {
        CREATOR = new ab();
    }

    FileUploadPreferencesImpl(int versionCode, int networkTypePreference, int batteryUsagePreference, boolean allowRoaming) {
        this.mVersionCode = versionCode;
        this.aaJ = networkTypePreference;
        this.aaK = batteryUsagePreference;
        this.aaL = allowRoaming;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        ab.a(this, parcel, flags);
    }
}
