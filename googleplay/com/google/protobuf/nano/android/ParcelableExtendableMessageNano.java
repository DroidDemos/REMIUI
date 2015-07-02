package com.google.protobuf.nano.android;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.protobuf.nano.ExtendableMessageNano;

public abstract class ParcelableExtendableMessageNano<M extends ExtendableMessageNano<M>> extends ExtendableMessageNano<M> implements Parcelable {
    public static final Creator<ParcelableExtendableMessageNano<?>> CREATOR;

    static {
        CREATOR = new Creator<ParcelableExtendableMessageNano<?>>() {
            public ParcelableExtendableMessageNano<?> createFromParcel(Parcel in) {
                return (ParcelableExtendableMessageNano) ParcelingUtil.createFromParcel(in);
            }

            public ParcelableExtendableMessageNano<?>[] newArray(int size) {
                return new ParcelableExtendableMessageNano[size];
            }
        };
    }
}
