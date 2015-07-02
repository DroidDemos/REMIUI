package com.google.android.gms.drive.realtime.internal.event;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class ValuesSetDetails implements SafeParcelable {
    public static final Creator<ValuesSetDetails> CREATOR;
    final int aeL;
    final int aeM;
    final int mIndex;
    final int mVersionCode;

    static {
        CREATOR = new k();
    }

    ValuesSetDetails(int versionCode, int index, int valueIndex, int valueCount) {
        this.mVersionCode = versionCode;
        this.mIndex = index;
        this.aeL = valueIndex;
        this.aeM = valueCount;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        k.a(this, dest, flags);
    }
}
