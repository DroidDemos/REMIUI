package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.fitness.data.DataType;
import java.util.Collections;
import java.util.List;

public class nj implements SafeParcelable {
    public static final Creator<nj> CREATOR;
    private final List<DataType> agj;
    private final int mVersionCode;

    static {
        CREATOR = new nk();
    }

    nj(int i, List<DataType> list) {
        this.mVersionCode = i;
        this.agj = list;
    }

    public int describeContents() {
        return 0;
    }

    public List<DataType> getDataTypes() {
        return Collections.unmodifiableList(this.agj);
    }

    int getVersionCode() {
        return this.mVersionCode;
    }

    public String toString() {
        return kl.j(this).a("dataTypes", this.agj).toString();
    }

    public void writeToParcel(Parcel parcel, int flags) {
        nk.a(this, parcel, flags);
    }
}
