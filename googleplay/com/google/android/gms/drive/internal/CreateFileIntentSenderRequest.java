package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;

public class CreateFileIntentSenderRequest implements SafeParcelable {
    public static final Creator<CreateFileIntentSenderRequest> CREATOR;
    final String Yv;
    final DriveId Yx;
    final MetadataBundle Zk;
    final Integer Zl;
    final int mVersionCode;
    final int ve;

    static {
        CREATOR = new j();
    }

    CreateFileIntentSenderRequest(int versionCode, MetadataBundle metadata, int requestId, String title, DriveId startFolder, Integer fileType) {
        this.mVersionCode = versionCode;
        this.Zk = metadata;
        this.ve = requestId;
        this.Yv = title;
        this.Yx = startFolder;
        this.Zl = fileType;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        j.a(this, dest, flags);
    }
}
