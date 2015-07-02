package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.drive.Contents;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;

public class CloseContentsAndUpdateMetadataRequest implements SafeParcelable {
    public static final Creator<CloseContentsAndUpdateMetadataRequest> CREATOR;
    final String Ym;
    final boolean Yn;
    final DriveId Zc;
    final MetadataBundle Zd;
    final Contents Ze;
    final int Zf;
    final int mVersionCode;

    static {
        CREATOR = new f();
    }

    CloseContentsAndUpdateMetadataRequest(int versionCode, DriveId id, MetadataBundle metadataChangeSet, Contents contentsReference, boolean notifyOnCompletion, String trackingTag, int commitStrategy) {
        this.mVersionCode = versionCode;
        this.Zc = id;
        this.Zd = metadataChangeSet;
        this.Ze = contentsReference;
        this.Yn = notifyOnCompletion;
        this.Ym = trackingTag;
        this.Zf = commitStrategy;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        f.a(this, dest, flags);
    }
}
