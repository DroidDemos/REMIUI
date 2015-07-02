package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.fitness.data.DataSource;

public class nl implements SafeParcelable {
    public static final Creator<nl> CREATOR;
    private final DataSource aga;
    private final int mVersionCode;

    static {
        CREATOR = new nm();
    }

    nl(int i, DataSource dataSource) {
        this.mVersionCode = i;
        this.aga = dataSource;
    }

    public int describeContents() {
        return 0;
    }

    public DataSource getDataSource() {
        return this.aga;
    }

    int getVersionCode() {
        return this.mVersionCode;
    }

    public String toString() {
        return String.format("ApplicationUnregistrationRequest{%s}", new Object[]{this.aga});
    }

    public void writeToParcel(Parcel parcel, int flags) {
        nm.a(this, parcel, flags);
    }
}
