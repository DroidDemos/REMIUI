package com.google.android.gms.fitness.request;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.fitness.data.BleDevice;

public class b implements SafeParcelable {
    public static final Creator<b> CREATOR;
    private final String ahM;
    private final BleDevice ahN;
    private final int mVersionCode;

    static {
        CREATOR = new c();
    }

    b(int i, String str, BleDevice bleDevice) {
        this.mVersionCode = i;
        this.ahM = str;
        this.ahN = bleDevice;
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

    public BleDevice lJ() {
        return this.ahN;
    }

    public String toString() {
        return String.format("ClaimBleDeviceRequest{%s %s}", new Object[]{this.ahM, this.ahN});
    }

    public void writeToParcel(Parcel parcel, int flags) {
        c.a(this, parcel, flags);
    }
}
