package com.google.android.gms.gcm;

import android.os.Parcel;
import android.os.Parcelable;

public abstract class Task implements Parcelable {
    private final String arH;
    private final boolean arI;
    private final boolean arJ;
    private final String mTag;

    Task() {
        this.arH = null;
        this.mTag = null;
        this.arI = false;
        this.arJ = false;
    }

    Task(Parcel in) {
        boolean z = true;
        this.arH = in.readString();
        this.mTag = in.readString();
        this.arI = in.readInt() == 1;
        if (in.readInt() != 1) {
            z = false;
        }
        this.arJ = z;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int i2 = 1;
        parcel.writeString(this.arH);
        parcel.writeString(this.mTag);
        parcel.writeInt(this.arI ? 1 : 0);
        if (!this.arJ) {
            i2 = 0;
        }
        parcel.writeInt(i2);
    }
}
