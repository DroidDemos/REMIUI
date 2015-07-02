package com.google.android.gms.drive.realtime.internal.event;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class ObjectChangedDetails implements SafeParcelable {
    public static final Creator<ObjectChangedDetails> CREATOR;
    final int aeL;
    final int aeM;
    final int mVersionCode;

    static {
        CREATOR = new a();
    }

    ObjectChangedDetails(int versionCode, int valueIndex, int valueCount) {
        this.mVersionCode = versionCode;
        this.aeL = valueIndex;
        this.aeM = valueCount;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        a.a(this, dest, flags);
    }
}
