package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;

public class UpdateMetadataRequest implements SafeParcelable {
    public static final Creator<UpdateMetadataRequest> CREATOR;
    final DriveId Zc;
    final MetadataBundle Zd;
    final int mVersionCode;

    static {
        CREATOR = new bi();
    }

    UpdateMetadataRequest(int versionCode, DriveId id, MetadataBundle metadataChangeSet) {
        this.mVersionCode = versionCode;
        this.Zc = id;
        this.Zd = metadataChangeSet;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        bi.a(this, dest, flags);
    }
}
