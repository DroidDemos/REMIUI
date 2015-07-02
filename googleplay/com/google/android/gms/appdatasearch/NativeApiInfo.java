package com.google.android.gms.appdatasearch;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class NativeApiInfo implements SafeParcelable {
    public static final q CREATOR;
    public final String downloadManagerFilename;
    final int mVersionCode;
    public final String sharedLibAbsoluteFilename;
    public final String sharedLibExtensionAbsoluteFilename;

    static {
        CREATOR = new q();
    }

    NativeApiInfo(int versionCode, String filename, String extensionFilename, String downloadManagerFilename) {
        this.mVersionCode = versionCode;
        this.sharedLibAbsoluteFilename = filename;
        this.sharedLibExtensionAbsoluteFilename = extensionFilename;
        this.downloadManagerFilename = downloadManagerFilename;
    }

    public int describeContents() {
        q qVar = CREATOR;
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        q qVar = CREATOR;
        q.a(this, out, flags);
    }
}
