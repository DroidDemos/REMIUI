package com.google.protobuf.nano.android;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.protobuf.nano.MessageNano;

public abstract class ParcelableMessageNano extends MessageNano implements Parcelable {
    public static final Creator<ParcelableMessageNano> CREATOR;

    static {
        CREATOR = new Creator<ParcelableMessageNano>() {
            public ParcelableMessageNano createFromParcel(Parcel in) {
                return (ParcelableMessageNano) ParcelingUtil.createFromParcel(in);
            }

            public ParcelableMessageNano[] newArray(int size) {
                return new ParcelableMessageNano[size];
            }
        };
    }
}
