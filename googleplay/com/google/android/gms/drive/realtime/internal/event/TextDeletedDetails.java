package com.google.android.gms.drive.realtime.internal.event;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class TextDeletedDetails implements SafeParcelable {
    public static final Creator<TextDeletedDetails> CREATOR;
    final int aeZ;
    final int mIndex;
    final int mVersionCode;

    static {
        CREATOR = new f();
    }

    TextDeletedDetails(int versionCode, int index, int stringDataIndex) {
        this.mVersionCode = versionCode;
        this.mIndex = index;
        this.aeZ = stringDataIndex;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        f.a(this, dest, flags);
    }
}
