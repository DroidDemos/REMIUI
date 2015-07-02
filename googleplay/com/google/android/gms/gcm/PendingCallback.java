package com.google.android.gms.gcm;

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
                return eY(x0);
            }

            public PendingCallback eY(Parcel parcel) {
                return new PendingCallback(parcel);
            }

            public PendingCallback[] hg(int i) {
                return new PendingCallback[i];
            }

            public /* synthetic */ Object[] newArray(int x0) {
                return hg(x0);
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
