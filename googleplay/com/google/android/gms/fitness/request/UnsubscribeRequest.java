package com.google.android.gms.fitness.request;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.internal.kl;

public class UnsubscribeRequest implements SafeParcelable {
    public static final Creator<UnsubscribeRequest> CREATOR;
    private final DataType afZ;
    private final DataSource aga;
    private final int mVersionCode;

    static {
        CREATOR = new ah();
    }

    UnsubscribeRequest(int versionCode, DataType dataType, DataSource dataSource) {
        this.mVersionCode = versionCode;
        this.afZ = dataType;
        this.aga = dataSource;
    }

    private boolean a(UnsubscribeRequest unsubscribeRequest) {
        return kl.equal(this.aga, unsubscribeRequest.aga) && kl.equal(this.afZ, unsubscribeRequest.afZ);
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object o) {
        return this == o || ((o instanceof UnsubscribeRequest) && a((UnsubscribeRequest) o));
    }

    public DataSource getDataSource() {
        return this.aga;
    }

    public DataType getDataType() {
        return this.afZ;
    }

    int getVersionCode() {
        return this.mVersionCode;
    }

    public int hashCode() {
        return kl.hashCode(this.aga, this.afZ);
    }

    public void writeToParcel(Parcel parcel, int flags) {
        ah.a(this, parcel, flags);
    }
}
