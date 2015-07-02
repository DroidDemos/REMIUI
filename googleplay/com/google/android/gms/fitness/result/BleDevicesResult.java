package com.google.android.gms.fitness.result;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.fitness.data.BleDevice;
import com.google.android.gms.internal.kl;
import java.util.Collections;
import java.util.List;

public class BleDevicesResult implements Result, SafeParcelable {
    public static final Creator<BleDevicesResult> CREATOR;
    private final Status EU;
    private final List<BleDevice> aix;
    private final int mVersionCode;

    static {
        CREATOR = new a();
    }

    BleDevicesResult(int versionCode, List<BleDevice> bleDevices, Status status) {
        this.mVersionCode = versionCode;
        this.aix = Collections.unmodifiableList(bleDevices);
        this.EU = status;
    }

    private boolean b(BleDevicesResult bleDevicesResult) {
        return this.EU.equals(bleDevicesResult.EU) && kl.equal(this.aix, bleDevicesResult.aix);
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object that) {
        return this == that || ((that instanceof BleDevicesResult) && b((BleDevicesResult) that));
    }

    public List<BleDevice> getClaimedBleDevices() {
        return this.aix;
    }

    public Status getStatus() {
        return this.EU;
    }

    int getVersionCode() {
        return this.mVersionCode;
    }

    public int hashCode() {
        return kl.hashCode(this.EU, this.aix);
    }

    public String toString() {
        return kl.j(this).a("status", this.EU).a("bleDevices", this.aix).toString();
    }

    public void writeToParcel(Parcel dest, int flags) {
        a.a(this, dest, flags);
    }
}
