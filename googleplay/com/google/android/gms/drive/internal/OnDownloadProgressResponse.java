package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class OnDownloadProgressResponse implements SafeParcelable {
    public static final Creator<OnDownloadProgressResponse> CREATOR;
    final long aaV;
    final long aaW;
    final int mVersionCode;

    static {
        CREATOR = new an();
    }

    OnDownloadProgressResponse(int versionCode, long bytesLoaded, long bytesExpected) {
        this.mVersionCode = versionCode;
        this.aaV = bytesLoaded;
        this.aaW = bytesExpected;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        an.a(this, dest, flags);
    }
}
