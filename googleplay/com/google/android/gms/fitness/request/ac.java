package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.fitness.request.l.a;

public class ac implements SafeParcelable {
    public static final Creator<ac> CREATOR;
    private final l ait;
    private final int mVersionCode;

    static {
        CREATOR = new ad();
    }

    ac(int i, IBinder iBinder) {
        this.mVersionCode = i;
        this.ait = a.bV(iBinder);
    }

    public int describeContents() {
        return 0;
    }

    int getVersionCode() {
        return this.mVersionCode;
    }

    public IBinder mf() {
        return this.ait.asBinder();
    }

    public void writeToParcel(Parcel parcel, int flags) {
        ad.a(this, parcel, flags);
    }
}
