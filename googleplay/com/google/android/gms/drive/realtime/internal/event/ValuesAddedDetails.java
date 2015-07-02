package com.google.android.gms.drive.realtime.internal.event;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class ValuesAddedDetails implements SafeParcelable {
    public static final Creator<ValuesAddedDetails> CREATOR;
    final int aeL;
    final int aeM;
    final String afb;
    final int afc;
    final int mIndex;
    final int mVersionCode;

    static {
        CREATOR = new i();
    }

    ValuesAddedDetails(int versionCode, int index, int valueIndex, int valueCount, String movedFromId, int movedFromIndex) {
        this.mVersionCode = versionCode;
        this.mIndex = index;
        this.aeL = valueIndex;
        this.aeM = valueCount;
        this.afb = movedFromId;
        this.afc = movedFromIndex;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        i.a(this, dest, flags);
    }
}
