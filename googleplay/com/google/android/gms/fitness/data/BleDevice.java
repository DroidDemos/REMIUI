package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.kl;
import com.google.android.gms.internal.ml;
import java.util.Collections;
import java.util.List;

public class BleDevice implements SafeParcelable {
    public static final Creator<BleDevice> CREATOR;
    private final String agh;
    private final List<String> agi;
    private final List<DataType> agj;
    private final String mName;
    private final int mVersionCode;

    static {
        CREATOR = new b();
    }

    BleDevice(int versionCode, String address, String name, List<String> profiles, List<DataType> dataTypes) {
        this.mVersionCode = versionCode;
        this.agh = address;
        this.mName = name;
        this.agi = Collections.unmodifiableList(profiles);
        this.agj = Collections.unmodifiableList(dataTypes);
    }

    private boolean a(BleDevice bleDevice) {
        return this.mName.equals(bleDevice.mName) && this.agh.equals(bleDevice.agh) && ml.a(bleDevice.agi, this.agi) && ml.a(this.agj, bleDevice.agj);
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object o) {
        return o == this || ((o instanceof BleDevice) && a((BleDevice) o));
    }

    public String getAddress() {
        return this.agh;
    }

    public List<DataType> getDataTypes() {
        return this.agj;
    }

    public String getName() {
        return this.mName;
    }

    public List<String> getSupportedProfiles() {
        return this.agi;
    }

    int getVersionCode() {
        return this.mVersionCode;
    }

    public int hashCode() {
        return kl.hashCode(this.mName, this.agh, this.agi, this.agj);
    }

    public String toString() {
        return kl.j(this).a("name", this.mName).a("address", this.agh).a("dataTypes", this.agj).a("supportedProfiles", this.agi).toString();
    }

    public void writeToParcel(Parcel parcel, int flags) {
        b.a(this, parcel, flags);
    }
}
