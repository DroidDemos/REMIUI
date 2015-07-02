package com.google.android.gms.drive.realtime.internal.event;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class ValuesRemovedDetails implements SafeParcelable {
    public static final Creator<ValuesRemovedDetails> CREATOR;
    final int aeL;
    final int aeM;
    final String afd;
    final int afe;
    final int mIndex;
    final int mVersionCode;

    static {
        CREATOR = new j();
    }

    ValuesRemovedDetails(int versionCode, int index, int valueIndex, int valueCount, String movedToId, int movedToIndex) {
        this.mVersionCode = versionCode;
        this.mIndex = index;
        this.aeL = valueIndex;
        this.aeM = valueCount;
        this.afd = movedToId;
        this.afe = movedToIndex;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        j.a(this, dest, flags);
    }
}
