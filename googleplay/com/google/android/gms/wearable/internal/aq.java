package com.google.android.gms.wearable.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.wearable.internal.ae.a;

public class aq implements SafeParcelable {
    public static final Creator<aq> CREATOR;
    public final ae aWt;
    final int mVersionCode;

    static {
        CREATOR = new ar();
    }

    aq(int i, IBinder iBinder) {
        this.mVersionCode = i;
        if (iBinder != null) {
            this.aWt = a.es(iBinder);
        } else {
            this.aWt = null;
        }
    }

    public int describeContents() {
        return 0;
    }

    IBinder ve() {
        return this.aWt == null ? null : this.aWt.asBinder();
    }

    public void writeToParcel(Parcel dest, int flags) {
        ar.a(this, dest, flags);
    }
}
