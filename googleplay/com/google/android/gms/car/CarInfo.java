package com.google.android.gms.car;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class CarInfo implements SafeParcelable {
    public static final Creator<CarInfo> CREATOR;
    public int headUnitProtocolMajorVersionNumber;
    public int headUnitProtocolMinorVersionNumber;
    final int mVersionCode;
    public String manufacturer;
    public String model;
    public String modelYear;
    public String vehicleId;

    static {
        CREATOR = new g();
    }

    public CarInfo() {
        this.mVersionCode = 2;
    }

    public CarInfo(int versionCode, String manufacturer, String model, String modelYear, String vehicleId, int headUnitProtocolMajorVersionNumber, int headUnitProtocolMinorVersionNumber) {
        this.mVersionCode = versionCode;
        this.manufacturer = manufacturer;
        this.model = model;
        this.modelYear = modelYear;
        this.vehicleId = vehicleId;
        this.headUnitProtocolMajorVersionNumber = headUnitProtocolMajorVersionNumber;
        this.headUnitProtocolMinorVersionNumber = headUnitProtocolMinorVersionNumber;
    }

    public int describeContents() {
        return 0;
    }

    public int getVersionCode() {
        return this.mVersionCode;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.modelYear);
        stringBuilder.append(" ");
        stringBuilder.append(this.manufacturer);
        stringBuilder.append(" ");
        stringBuilder.append(this.model);
        stringBuilder.append(" ");
        stringBuilder.append(this.vehicleId);
        return stringBuilder.toString();
    }

    public void writeToParcel(Parcel dest, int flags) {
        g.a(this, dest, flags);
    }
}
