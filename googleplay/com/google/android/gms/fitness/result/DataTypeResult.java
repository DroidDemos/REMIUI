package com.google.android.gms.fitness.result;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.internal.kl;

public class DataTypeResult implements Result, SafeParcelable {
    public static final Creator<DataTypeResult> CREATOR;
    private final Status EU;
    private final DataType afZ;
    private final int mVersionCode;

    static {
        CREATOR = new d();
    }

    DataTypeResult(int versionCode, Status status, DataType dataType) {
        this.mVersionCode = versionCode;
        this.EU = status;
        this.afZ = dataType;
    }

    private boolean b(DataTypeResult dataTypeResult) {
        return this.EU.equals(dataTypeResult.EU) && kl.equal(this.afZ, dataTypeResult.afZ);
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object that) {
        return this == that || ((that instanceof DataTypeResult) && b((DataTypeResult) that));
    }

    public DataType getDataType() {
        return this.afZ;
    }

    public Status getStatus() {
        return this.EU;
    }

    int getVersionCode() {
        return this.mVersionCode;
    }

    public int hashCode() {
        return kl.hashCode(this.EU, this.afZ);
    }

    public String toString() {
        return kl.j(this).a("status", this.EU).a("dataType", this.afZ).toString();
    }

    public void writeToParcel(Parcel dest, int flags) {
        d.a(this, dest, flags);
    }
}
