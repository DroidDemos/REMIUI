package com.google.android.gms.fitness.request;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.internal.kl;
import com.google.android.gms.internal.kl.a;
import java.util.Collections;
import java.util.List;

public class DataSourcesRequest implements SafeParcelable {
    public static final Creator<DataSourcesRequest> CREATOR;
    private final List<DataType> agj;
    private final List<Integer> ahZ;
    private final boolean aia;
    private final int mVersionCode;

    static {
        CREATOR = new h();
    }

    DataSourcesRequest(int versionCode, List<DataType> dataTypes, List<Integer> dataSourceTypes, boolean includeDbOnlySources) {
        this.mVersionCode = versionCode;
        this.agj = dataTypes;
        this.ahZ = dataSourceTypes;
        this.aia = includeDbOnlySources;
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

    public boolean lR() {
        return this.aia;
    }

    List<Integer> lS() {
        return this.ahZ;
    }

    public String toString() {
        a a = kl.j(this).a("dataTypes", this.agj).a("sourceTypes", this.ahZ);
        if (this.aia) {
            a.a("includeDbOnlySources", "true");
        }
        return a.toString();
    }

    public void writeToParcel(Parcel parcel, int flags) {
        h.a(this, parcel, flags);
    }
}
