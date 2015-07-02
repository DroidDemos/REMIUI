package com.google.android.gms.fitness.request;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.fitness.data.DataType;

public class ListSubscriptionsRequest implements SafeParcelable {
    public static final Creator<ListSubscriptionsRequest> CREATOR;
    private final DataType afZ;
    private final int mVersionCode;

    static {
        CREATOR = new m();
    }

    ListSubscriptionsRequest(int versionCode, DataType dataType) {
        this.mVersionCode = versionCode;
        this.afZ = dataType;
    }

    public int describeContents() {
        return 0;
    }

    public DataType getDataType() {
        return this.afZ;
    }

    int getVersionCode() {
        return this.mVersionCode;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        m.a(this, parcel, flags);
    }
}
