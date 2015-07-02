package com.google.android.gms.drive;

import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class Contents implements SafeParcelable {
    public static final Creator<Contents> CREATOR;
    final ParcelFileDescriptor UJ;
    final int Ya;
    final DriveId Yb;
    final boolean Yc;
    final int mVersionCode;
    final int ve;

    static {
        CREATOR = new a();
    }

    Contents(int versionCode, ParcelFileDescriptor parcelFileDescriptor, int requestId, int mode, DriveId driveId, boolean validForConflictDetection) {
        this.mVersionCode = versionCode;
        this.UJ = parcelFileDescriptor;
        this.ve = requestId;
        this.Ya = mode;
        this.Yb = driveId;
        this.Yc = validForConflictDetection;
    }

    public int describeContents() {
        return 0;
    }

    public int getRequestId() {
        return this.ve;
    }

    public void writeToParcel(Parcel dest, int flags) {
        a.a(this, dest, flags);
    }
}
