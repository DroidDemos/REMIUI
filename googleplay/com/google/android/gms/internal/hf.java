package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class hf implements Parcelable {
    @Deprecated
    public static final Creator<hf> CREATOR;
    private String CB;
    private String CC;
    private String mValue;

    static {
        CREATOR = new Creator<hf>() {
            @Deprecated
            public hf[] E(int i) {
                return new hf[i];
            }

            public /* synthetic */ Object createFromParcel(Parcel x0) {
                return k(x0);
            }

            @Deprecated
            public hf k(Parcel parcel) {
                return new hf(parcel);
            }

            public /* synthetic */ Object[] newArray(int x0) {
                return E(x0);
            }
        };
    }

    @Deprecated
    hf(Parcel parcel) {
        readFromParcel(parcel);
    }

    @Deprecated
    private void readFromParcel(Parcel in) {
        this.CB = in.readString();
        this.CC = in.readString();
        this.mValue = in.readString();
    }

    @Deprecated
    public int describeContents() {
        return 0;
    }

    @Deprecated
    public void writeToParcel(Parcel out, int flags) {
        out.writeString(this.CB);
        out.writeString(this.CC);
        out.writeString(this.mValue);
    }
}
