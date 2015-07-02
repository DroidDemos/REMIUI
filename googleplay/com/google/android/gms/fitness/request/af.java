package com.google.android.gms.fitness.request;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class af implements SafeParcelable {
    public static final Creator<af> CREATOR;
    private final String ahM;
    private final int mVersionCode;

    static {
        CREATOR = new ag();
    }

    af(int i, String str) {
        this.mVersionCode = i;
        this.ahM = str;
    }

    public int describeContents() {
        return 0;
    }

    public String getDeviceAddress() {
        return this.ahM;
    }

    int getVersionCode() {
        return this.mVersionCode;
    }

    public String toString() {
        return String.format("UnclaimBleDeviceRequest{%s}", new Object[]{this.ahM});
    }

    public void writeToParcel(Parcel parcel, int flags) {
        ag.a(this, parcel, flags);
    }
}
