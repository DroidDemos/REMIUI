package com.google.android.gms.fitness.result;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.internal.kl;
import java.util.Collections;
import java.util.List;

public class DataSourcesResult implements Result, SafeParcelable {
    public static final Creator<DataSourcesResult> CREATOR;
    private final Status EU;
    private final List<DataSource> ahO;
    private final int mVersionCode;

    static {
        CREATOR = new c();
    }

    DataSourcesResult(int versionCode, List<DataSource> dataSources, Status status) {
        this.mVersionCode = versionCode;
        this.ahO = Collections.unmodifiableList(dataSources);
        this.EU = status;
    }

    private boolean b(DataSourcesResult dataSourcesResult) {
        return this.EU.equals(dataSourcesResult.EU) && kl.equal(this.ahO, dataSourcesResult.ahO);
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object that) {
        return this == that || ((that instanceof DataSourcesResult) && b((DataSourcesResult) that));
    }

    public List<DataSource> getDataSources() {
        return this.ahO;
    }

    public Status getStatus() {
        return this.EU;
    }

    int getVersionCode() {
        return this.mVersionCode;
    }

    public int hashCode() {
        return kl.hashCode(this.EU, this.ahO);
    }

    public String toString() {
        return kl.j(this).a("status", this.EU).a("dataSets", this.ahO).toString();
    }

    public void writeToParcel(Parcel dest, int flags) {
        c.a(this, dest, flags);
    }
}
