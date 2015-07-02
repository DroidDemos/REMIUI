package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.drive.DriveId;

public class OpenFileIntentSenderRequest implements SafeParcelable {
    public static final Creator<OpenFileIntentSenderRequest> CREATOR;
    final String Yv;
    final String[] Yw;
    final DriveId Yx;
    final int mVersionCode;

    static {
        CREATOR = new ba();
    }

    OpenFileIntentSenderRequest(int versionCode, String title, String[] mimeTypes, DriveId startFolder) {
        this.mVersionCode = versionCode;
        this.Yv = title;
        this.Yw = mimeTypes;
        this.Yx = startFolder;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        ba.a(this, dest, flags);
    }
}
