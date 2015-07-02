package com.google.android.gms.gcm.nts;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class PendingCallback implements Parcelable {
    public static final Creator<PendingCallback> CREATOR;
    final IBinder Wn;

    static {
        CREATOR = new Creator<PendingCallback>() {
            public /* synthetic */ Object createFromParcel(Parcel x0) {
                return fa(x0);
            }

            public PendingCallback fa(Parcel parcel) {
                return new PendingCallback(parcel);
            }

            public PendingCallback[] hi(int i) {
                return new PendingCallback[i];
            }

            public /* synthetic */ Object[] newArray(int x0) {
                return hi(x0);
            }
        };
    }

    public PendingCallback(Parcel in) {
        this.Wn = in.readStrongBinder();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeStrongBinder(this.Wn);
    }
}
