package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.kl;
import java.util.List;

public final class RawDataSet implements SafeParcelable {
    public static final Creator<RawDataSet> CREATOR;
    final int agR;
    final int agT;
    final List<RawDataPoint> agU;
    final boolean agn;
    final int mVersionCode;

    static {
        CREATOR = new n();
    }

    RawDataSet(int versionCode, int dataSourceIndex, int dataTypeIndex, List<RawDataPoint> rawDataPoints, boolean serverHasMoreData) {
        this.mVersionCode = versionCode;
        this.agR = dataSourceIndex;
        this.agT = dataTypeIndex;
        this.agU = rawDataPoints;
        this.agn = serverHasMoreData;
    }

    public RawDataSet(DataSet dataSet, List<DataSource> uniqueDataSources, List<DataType> uniqueDataTypes) {
        this.mVersionCode = 2;
        this.agU = dataSet.i(uniqueDataSources);
        this.agn = dataSet.serverHasMoreData();
        this.agR = s.a(dataSet.getDataSource(), uniqueDataSources);
        this.agT = s.a(dataSet.getDataType(), uniqueDataTypes);
    }

    private boolean a(RawDataSet rawDataSet) {
        return this.agR == rawDataSet.agR && this.agT == rawDataSet.agT && this.agn == rawDataSet.agn && kl.equal(this.agU, rawDataSet.agU);
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object o) {
        return this == o || ((o instanceof RawDataSet) && a((RawDataSet) o));
    }

    public int hashCode() {
        return kl.hashCode(Integer.valueOf(this.agR), Integer.valueOf(this.agT));
    }

    public String toString() {
        return String.format("RawDataSet{%s@[%s, %s]}", new Object[]{Integer.valueOf(this.agR), Integer.valueOf(this.agT), this.agU});
    }

    public void writeToParcel(Parcel parcel, int flags) {
        n.a(this, parcel, flags);
    }
}
