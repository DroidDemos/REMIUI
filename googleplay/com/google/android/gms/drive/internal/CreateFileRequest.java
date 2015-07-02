package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.drive.Contents;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;
import com.google.android.gms.internal.kn;

public class CreateFileRequest implements SafeParcelable {
    public static final Creator<CreateFileRequest> CREATOR;
    final String Ym;
    final Contents Ze;
    final MetadataBundle Zk;
    final Integer Zl;
    final DriveId Zm;
    final boolean Zn;
    final int Zo;
    final int Zp;
    final int mVersionCode;

    static {
        CREATOR = new k();
    }

    CreateFileRequest(int versionCode, DriveId parentDriveId, MetadataBundle metadata, Contents contentsReference, Integer fileType, boolean sendEventOnCompletion, String trackingTag, int createStrategy, int openContentsRequestId) {
        if (!(contentsReference == null || openContentsRequestId == 0)) {
            kn.b(contentsReference.getRequestId() == openContentsRequestId, (Object) "inconsistent contents reference");
        }
        if ((fileType == null || fileType.intValue() == 0) && contentsReference == null && openContentsRequestId == 0) {
            throw new IllegalArgumentException("Need a valid contents");
        }
        this.mVersionCode = versionCode;
        this.Zm = (DriveId) kn.k(parentDriveId);
        this.Zk = (MetadataBundle) kn.k(metadata);
        this.Ze = contentsReference;
        this.Zl = fileType;
        this.Ym = trackingTag;
        this.Zo = createStrategy;
        this.Zn = sendEventOnCompletion;
        this.Zp = openContentsRequestId;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        k.a(this, dest, flags);
    }
}
