package com.google.android.gms.wearable.internal;

import android.content.IntentFilter;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.wearable.internal.ae.a;

public class b implements SafeParcelable {
    public static final Creator<b> CREATOR;
    public final ae aWt;
    public final IntentFilter[] aWu;
    final int mVersionCode;

    static {
        CREATOR = new c();
    }

    b(int i, IBinder iBinder, IntentFilter[] intentFilterArr) {
        this.mVersionCode = i;
        if (iBinder != null) {
            this.aWt = a.es(iBinder);
        } else {
            this.aWt = null;
        }
        this.aWu = intentFilterArr;
    }

    public int describeContents() {
        return 0;
    }

    IBinder ve() {
        return this.aWt == null ? null : this.aWt.asBinder();
    }

    public void writeToParcel(Parcel dest, int flags) {
        c.a(this, dest, flags);
    }
}
