package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class ab implements SafeParcelable {
    public static final Creator<ab> CREATOR;
    public final al aWT;
    public final int statusCode;
    public final int versionCode;

    static {
        CREATOR = new ac();
    }

    ab(int i, int i2, al alVar) {
        this.versionCode = i;
        this.statusCode = i2;
        this.aWT = alVar;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        ac.a(this, dest, flags);
    }
}
