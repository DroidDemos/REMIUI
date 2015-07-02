package com.google.android.gms.drive.realtime.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class ParcelableIndexReference implements SafeParcelable {
    public static final Creator<ParcelableIndexReference> CREATOR;
    final String ael;
    final boolean aem;
    final int mIndex;
    final int mVersionCode;

    static {
        CREATOR = new ah();
    }

    ParcelableIndexReference(int versionCode, String objectId, int index, boolean canBeDeleted) {
        this.mVersionCode = versionCode;
        this.ael = objectId;
        this.mIndex = index;
        this.aem = canBeDeleted;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        ah.a(this, dest, flags);
    }
}
