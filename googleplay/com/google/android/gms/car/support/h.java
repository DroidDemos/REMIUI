package com.google.android.gms.car.support;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

final class h implements Parcelable {
    public static final Creator<h> CREATOR;
    FragmentState[] Ns;
    c[] Nt;
    int[] mAdded;

    static {
        CREATOR = new Creator<h>() {
            public h bE(Parcel parcel) {
                return new h(parcel);
            }

            public /* synthetic */ Object createFromParcel(Parcel x0) {
                return bE(x0);
            }

            public h[] cw(int i) {
                return new h[i];
            }

            public /* synthetic */ Object[] newArray(int x0) {
                return cw(x0);
            }
        };
    }

    public h(Parcel parcel) {
        this.Ns = (FragmentState[]) parcel.createTypedArray(FragmentState.CREATOR);
        this.mAdded = parcel.createIntArray();
        this.Nt = (c[]) parcel.createTypedArray(c.CREATOR);
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedArray(this.Ns, flags);
        dest.writeIntArray(this.mAdded);
        dest.writeTypedArray(this.Nt, flags);
    }
}
