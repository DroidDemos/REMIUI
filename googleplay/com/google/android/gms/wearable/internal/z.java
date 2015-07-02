package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class z implements SafeParcelable {
    public static final Creator<z> CREATOR;
    public final ParcelFileDescriptor aWS;
    public final int statusCode;
    public final int versionCode;

    static {
        CREATOR = new aa();
    }

    z(int i, int i2, ParcelFileDescriptor parcelFileDescriptor) {
        this.versionCode = i;
        this.statusCode = i2;
        this.aWS = parcelFileDescriptor;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        aa.a(this, dest, flags | 1);
    }
}
